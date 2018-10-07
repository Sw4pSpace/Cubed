package net.glowstone.generator.objects;

import com.google.common.collect.ImmutableSortedSet;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.DoublePlant;
import org.bukkit.material.MaterialData;
import org.bukkit.material.types.DoublePlantSpecies;

import java.util.SortedSet;

public class TerrainHelper {

    /**
     * Plant block types.
     */
    static SortedSet<Material> PLANT_TYPES = ImmutableSortedSet
            .of(Material.LONG_GRASS, Material.YELLOW_FLOWER, Material.RED_ROSE,
                    Material.DOUBLE_PLANT, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM);


    /**
     * Removes the grass, shrub, flower or mushroom directly above the given block, if present. Does
     * not drop an item.
     *
     * @param block the block to update
     * @return true if a plant was removed; false if none was present
     */
    static boolean killPlantAbove(Block block) {
        Block blockAbove = block.getRelative(BlockFace.UP);
        Material mat = blockAbove.getType();
        if (PLANT_TYPES.contains(mat)) {
            if (mat == Material.DOUBLE_PLANT) {
                MaterialData dataAbove = blockAbove.getState().getData();
                if (dataAbove instanceof DoublePlant
                        && ((DoublePlant) dataAbove).getSpecies()
                        == DoublePlantSpecies.PLANT_APEX) {
                    blockAbove.getRelative(BlockFace.UP)
                            .setType(Material.AIR);
                }
            }
            blockAbove.setType(Material.AIR);
            return true;
        }
        return false;
    }

}
