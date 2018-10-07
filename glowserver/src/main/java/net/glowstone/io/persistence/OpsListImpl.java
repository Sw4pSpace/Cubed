package net.glowstone.io.persistence;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import net.glowstone.entity.meta.profile.GlowPlayerProfile;
import net.glowstone.entity.meta.profile.ProfileCache;
import org.bukkit.OfflinePlayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class OpsListImpl implements OpsList {

    private PersistenceManager persistenceManager;

    @Inject
    public OpsListImpl(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    /**
     * Returns a {@link GlowPlayerProfile} for each player whose UUID is in the list file.
     *
     * @return a list of {@link GlowPlayerProfile} instances
     */
    @Override
    public List<PlayerProfile> getProfiles() {

        // SELECT uuid FROM ops;
        List<PlayerProfile> playerProfiles = Lists.newArrayList();
        String query = "SELECT uuid FROM ops;";
        DatabaseResult<ResultSet> databaseResult = persistenceManager.executeQuery(query, Lists.newArrayList());
        ResultSet result = databaseResult.getResults();
        try {
            if(result.first()) {

                String uuid = result.getString("uuid");
                playerProfiles.add(ProfileCache.getProfile(UUID.fromString(uuid)).get());

            }
        } catch (SQLException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseResult.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return playerProfiles;
    }

    /**
     * Searches for a UUID.
     *
     * @param uuid the UUID to search for
     * @return true if the UUID is present; false otherwise
     */
    @Override
    public boolean containsUuid(UUID uuid) {

        // Get rows from SELECT * FROM ops WHERE uuid = uuid;
        String query = "SELECT uuid FROM ops WHERE uuid = \'?\';";
        DatabaseResult<ResultSet> databaseResult = persistenceManager.executeQuery(query, Lists.newArrayList(uuid.toString()));
        ResultSet result = databaseResult.getResults();
        boolean flag = false;

        try {
            if(!result.isClosed() && result.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                databaseResult.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }

    /**
     * Checks whether the player with a given UUID is in this list.
     *
     * @param profile the player whose UUID will be looked up
     * @return whether the player is on this list
     */
    @Override
    public boolean containsProfile(PlayerProfile profile) {
        return containsUuid(profile.getId());
    }

    /**
     * If the given player is not already on this list, adds that player and saves the change to
     * disk.
     *
     * @param player the player to add
     */
    @Override
    public void add(OfflinePlayer player) {
        String query = "INSERT INTO ops (name, uuid) VALUES (\'?\', \'?\');";
        DatabaseResult<Integer> databaseResult = persistenceManager.executeUpdate(query, Lists.newArrayList(player.getName(), player.getUniqueId().toString()));
        if(databaseResult.getResults() != 1) {
            // TODO log that there was an error saving player to ops table
        }
        try {
            databaseResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * If the given player is on this list, removes that player and saves the change to disk.
     *
     * @param profile the player to remove
     */
    @Override
    public void remove(PlayerProfile profile) {
        String query = "DELETE FROM ops WHERE uuid = \'?\';";
        DatabaseResult<Integer> databaseResult = persistenceManager.executeUpdate(query, Lists.newArrayList(profile.getId().toString()));
        if(databaseResult.getResults() != 1) {
            // TODO log that there was an error deleting player to ops table
        }
        try {
            databaseResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
