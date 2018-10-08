package net.glowstone.io.persistence;

import lombok.Getter;
import net.glowstone.GlowWorld;
import net.glowstone.io.*;
import net.glowstone.io.anvil.AnvilChunkIoService;
import net.glowstone.io.data.WorldFunctionIoService;
import net.glowstone.io.json.JsonPlayerStatisticIoService;
import net.glowstone.io.nbt.NbtPlayerDataService;
import net.glowstone.io.nbt.NbtScoreboardIoService;
import net.glowstone.io.nbt.NbtStructureDataService;
import net.glowstone.io.nbt.NbtWorldMetadataService;

import java.io.File;

public class GlowWorldStorageProvider {

    private GlowWorld world;

    @Getter
    private AnvilChunkIoService chunkIoService;

    @Getter
    private NbtWorldMetadataService metadataService;

    @Getter
    private StructureDataService structureDataService;

/*
    @Getter(lazy = true)
    private final PlayerDataService playerDataService = new NbtPlayerDataService(world.getServer(), new File(folder, "playerdata"));

    @Getter(lazy = true)
    private final ScoreboardIoService scoreboardIoService = new NbtScoreboardIoService(world.getServer(), new File(folder, "data"));

    @Getter(lazy = true)
    private final JsonPlayerStatisticIoService playerStatisticIoService = new JsonPlayerStatisticIoService(world.getServer(), new File(folder, "stats"));

    @Getter(lazy = true)
    private final FunctionIoService functionIoService = new WorldFunctionIoService(world, dataDir);
*/

    /**
     *
     */
    public GlowWorldStorageProvider() {
    }


    public void setWorld(GlowWorld world) {
        if (this.world != null) {
            throw new IllegalArgumentException("World is already set");
        }
        this.world = world;
/*        chunkIoService = new AnvilChunkIoService(folder);
        metadataService = new NbtWorldMetadataService(world, folder);
        structureDataService = new NbtStructureDataService(world, dataDir);*/
    }

}
