package net.glowstone.block.itemtype;

import net.glowstone.EventFactory;
import net.glowstone.block.GlowBlock;
import net.glowstone.block.ItemTable;
import net.glowstone.block.blocktype.BlockTnt;
import net.glowstone.entity.GlowPlayer;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import static org.bukkit.Material.*;
import static org.bukkit.block.BlockFace.DOWN;
import static org.bukkit.block.BlockFace.UP;

public class ItemFlintAndSteel extends ItemTool {

    public ItemFlintAndSteel() {
        this.setPlaceAs(Material.FIRE);
    }

    @Override
    public boolean onToolRightClick(GlowPlayer player, GlowBlock target, BlockFace face,
        ItemStack holding, Vector clickedLoc, EquipmentSlot hand) {
        if (target.getType() == OBSIDIAN) {
            BlockFace portalStartDirection = isNetherPortal(target);
            if(portalStartDirection != null) {
                createNetherPortal(target, portalStartDirection);
                return true;
            }
        }
        if (target.getType() == Material.TNT) {
            fireTnt(target, player);
            return true;
        }
        if (target.isFlammable() || target.getType().isOccluding()) {
            setBlockOnFire(player, target, face, holding, clickedLoc, hand);
            return true;
        }
        return false;
    }

    private BlockFace isNetherPortal(GlowBlock target) {
        BlockFace[] ALL_DIRECTIONS = {BlockFace.SOUTH, BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST};

        GlowBlock oneUp = target.getRelative(UP);
        if (!oneUp.getType().equals(AIR)) {
            // This cannot be a portal
            return null;
        }

        for (BlockFace f : ALL_DIRECTIONS) {
            GlowBlock rel = oneUp.getRelative(f);
            if (rel.getType().equals(OBSIDIAN)) {
                // Hey look this might be a portal
                if (rel.getRelative(UP).getType().equals(OBSIDIAN) && rel.getRelative(UP).getRelative(UP).getType().equals(OBSIDIAN)) {
                    // We now have a wall
                    GlowBlock wall = rel.getRelative(UP).getRelative(UP);
                    if (wall.getRelative(f.getOppositeFace()).getRelative(UP).getType().equals(OBSIDIAN)) {
                        // C shape, can we get another to make the O?
                        if (wall.getRelative(f.getOppositeFace()).getRelative(f.getOppositeFace()).getRelative(UP).getType().equals(OBSIDIAN)) {
                            // ceiling is complete
                            GlowBlock ceil = wall.getRelative(f.getOppositeFace()).getRelative(f.getOppositeFace()).getRelative(UP);
                            if (ceil.getRelative(DOWN).getRelative(f.getOppositeFace()).getType().equals(OBSIDIAN)) {
                                GlowBlock wall2 = ceil.getRelative(DOWN).getRelative(f.getOppositeFace());
                                if (wall2.getRelative(DOWN).getType().equals(OBSIDIAN) && wall2.getRelative(DOWN).getRelative(DOWN).getType().equals(OBSIDIAN)) {
                                    // Wall 2 complete, this is looking to be a nether portal
                                    if (target.getRelative(f.getOppositeFace()).getType().equals(OBSIDIAN)) {
                                        // This is a nether portal! phew...
                                        return f;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private void createNetherPortal(GlowBlock target, BlockFace f) {
        GlowBlock oneUp = target.getRelative(UP);
        Material portalMat = PORTAL;

        oneUp.setType(portalMat);
        oneUp.getRelative(UP).setType(portalMat);
        oneUp.getRelative(UP).getRelative(UP).setType(portalMat);
        oneUp.getRelative(f.getOppositeFace()).setType(portalMat);
        oneUp.getRelative(f.getOppositeFace()).getRelative(UP).setType(portalMat);
        oneUp.getRelative(f.getOppositeFace()).getRelative(UP).getRelative(UP).setType(portalMat);
    }

    private void fireTnt(GlowBlock tnt,GlowPlayer player) {
        BlockTnt.igniteBlock(tnt, false, player);
    }

    private boolean setBlockOnFire(GlowPlayer player, GlowBlock clicked, BlockFace face,
        ItemStack holding, Vector clickedLoc, EquipmentSlot hand) {
        GlowBlock fireBlock = clicked.getRelative(face);
        if (fireBlock.getType() != Material.AIR) {
            return true;
        }

        if (!clicked.isFlammable()
            && clicked.getRelative(BlockFace.DOWN).getType() == Material.AIR) {
            return true;
        }

        BlockIgniteEvent event = EventFactory.getInstance()
            .callEvent(new BlockIgniteEvent(fireBlock, IgniteCause.FLINT_AND_STEEL, player, null));
        if (event.isCancelled()) {
            player.setItemInHand(holding);
            return false;
        }

        // clone holding to avoid decreasing of the item's amount
        ItemTable.instance().getBlock(Material.FIRE)
            .rightClickBlock(player, clicked, face, holding.clone(), clickedLoc, hand);

        return true;
    }
}
