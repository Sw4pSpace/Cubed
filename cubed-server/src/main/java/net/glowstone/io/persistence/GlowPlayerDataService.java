package net.glowstone.io.persistence;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import net.glowstone.GlowOfflinePlayer;
import net.glowstone.GlowServer;
import net.glowstone.entity.GlowPlayer;
import net.glowstone.i18n.ConsoleMessages;
import net.glowstone.i18n.GlowstoneMessages;
import net.glowstone.io.PlayerDataService;
import net.glowstone.io.entity.EntityStorage;
import net.glowstone.io.nbt.NbtSerialization;
import net.glowstone.util.UuidUtils;
import net.glowstone.util.nbt.CompoundTag;
import net.glowstone.util.nbt.NbtInputStream;
import net.glowstone.util.nbt.NbtOutputStream;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Standard NBT-based player data storage.
 */
public class GlowPlayerDataService implements PlayerDataService {

    private final GlowServer server;
    private final File playerDir;

    @Inject private PersistenceManager persistenceManager;

    public GlowPlayerDataService(GlowServer server, File playerDir) {
        this.server = server;
        this.playerDir = playerDir;
    }

    private File getPlayerFile(UUID uuid) {
        if (!playerDir.isDirectory() && !playerDir.mkdirs()) {
            ConsoleMessages.Warn.Io.MKDIR_FAILED.log(playerDir);
        }
        return new File(playerDir, UuidUtils.toString(uuid) + ".dat");
    }

    private void readDataImpl(GlowPlayer player, CompoundTag playerTag) {
        EntityStorage.load(player, playerTag);
    }

    @Override
    public CompletableFuture<Collection<OfflinePlayer>> getOfflinePlayers() {
        // list files in directory
        File[] files = playerDir.listFiles();
        if (files == null) {
            return CompletableFuture.completedFuture(Arrays.asList());
        }

        List<CompletableFuture<GlowOfflinePlayer>> futures = new ArrayList<>(files.length);
        for (File file : files) {
            // first, make sure it looks like a player file
            String name = file.getName();
            if (name.length() != 40 || !name.endsWith(".dat")) { // NON-NLS
                continue;
            }

            // get the UUID
            UUID uuid;
            try {
                uuid = UuidUtils.fromString(name.substring(0, 36));
            } catch (IllegalArgumentException e) {
                continue;
            }

            // creating the OfflinePlayer will read the data
            futures.add(GlowOfflinePlayer.getOfflinePlayer(server, uuid));
        }

        CompletableFuture<Void> gotAll = CompletableFuture.allOf(futures.toArray(
                new CompletableFuture[futures.size()]));

        return gotAll.thenApplyAsync((v) ->
                futures.stream().map((f) -> f.join()).collect(Collectors.toList()));
    }

    @Override
    public PlayerReader beginReadingData(UUID uuid) {
        return null;//new NbtPlayerReader(getPlayerFile(uuid));
    }

    @Override
    public void readData(GlowPlayer player) {
        File playerFile = getPlayerFile(player.getUniqueId());
        CompoundTag playerTag = new CompoundTag();
        if (playerFile.exists()) {
            try (NbtInputStream in = new NbtInputStream(new FileInputStream(playerFile))) {
                playerTag = in.readCompound();
            } catch (IOException e) {
                player.kickPlayer(GlowstoneMessages.Kick.FILE_READ.get());
                ConsoleMessages.Error.Io.PLAYER_READ.log(e, player.getName(), playerFile);
            }
        }
        readDataImpl(player, playerTag);
    }

    @Override
    public void writeData(GlowPlayer player) {
        File playerFile = getPlayerFile(player.getUniqueId());
        CompoundTag tag = new CompoundTag();
        EntityStorage.save(player, tag);
        try (NbtOutputStream out = new NbtOutputStream(new FileOutputStream(playerFile))) {
            out.writeTag(tag);
        } catch (IOException e) {
            player.kickPlayer(GlowstoneMessages.Kick.FILE_WRITE.get());
            ConsoleMessages.Error.Io.PLAYER_WRITE.log(e, player.getName(), playerFile);
        }
    }

    @SuppressWarnings("HardCodedStringLiteral")
    private class NbtPlayerReader implements PlayerReader {

        private CompoundTag tag = new CompoundTag();
        private boolean hasPlayed;

        public NbtPlayerReader(String uuid, String worldid) {

            String query = "SELECT * FROM player WHERE worldid = ? AND uuid = \'?\';";
            List<Object> parameters = Lists.newArrayList(
                    worldid,
                    uuid
            );

            ResultSet result = persistenceManager.executeQuery(query, parameters).getResults();

            try {
                if(result.first()) {
                    hasPlayed = true;

                    // Now fetch player data from database
                    //GlowSerialization.serialize(result, Player.class);


                }else{
                    hasPlayed = false;
                }
                result.close();
            } catch (SQLException  e) {
                e.printStackTrace();
            }
        }

        private void checkOpen() {
            if (tag == null) {
                throw new IllegalStateException("cannot access fields after close");
            }
        }

        @Override
        public boolean hasPlayedBefore() {
            return hasPlayed;
        }

        @Override
        public Location getLocation() {
            checkOpen();
            World world = NbtSerialization.readWorld(server, tag);
            if (world != null) {
                return NbtSerialization.listTagsToLocation(world, tag);
            }
            return null;
        }

        @Override
        public Location getBedSpawnLocation() {
            checkOpen();
            // check that all fields are present
            if (!tag.isString("SpawnWorld") || !tag.isInt("SpawnX") || !tag.isInt("SpawnY") || !tag
                .isInt("SpawnZ")) {
                return null;
            }
            // look up world
            World world = server.getWorld(tag.getString("SpawnWorld"));
            if (world == null) {
                return null;
            }
            // return location
            return new Location(world, tag.getInt("SpawnX"), tag.getInt("SpawnY"),
                tag.getInt("SpawnZ"));
        }

        @Override
        public long getFirstPlayed() {
            checkOpen();
            long[] out = {0};
            tag.readCompound("bukkit", bukkit -> bukkit.readLong("firstPlayed", x -> out[0] = x));
            return out[0];
        }

        @Override
        public long getLastPlayed() {
            checkOpen();
            long[] out = {0};
            tag.readCompound("bukkit", bukkit -> bukkit.readLong("lastPlayed", x -> out[0] = x));
            return out[0];
        }

        @Override
        public String getLastKnownName() {
            checkOpen();
            String[] out = {null};
            tag.readCompound("bukkit",
                bukkit -> bukkit.readString("lastKnownName", x -> out[0] = x));
            return out[0];
        }

        @Override
        public void readData(GlowPlayer player) {
            checkOpen();
            readDataImpl(player, tag);
        }

        @Override
        public void close() {
            tag = null;
        }
    }
}
