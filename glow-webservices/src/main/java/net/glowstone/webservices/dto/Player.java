package net.glowstone.webservices.dto;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bukkit.*;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.*;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Player {

    @JsonIgnore
    private org.bukkit.entity.Player bukkitPlayer;

    private String displayName;
    private String playerListName;
    private String uniqueId;
    private Location compassTarget;

    private int protocolVersion;

    private String ipAddress;
    private String virtualHost;

    private boolean isConversing;
    private boolean isSneaking;
    private boolean isSprinting;
    private boolean isSleepingIgnored;

    public Player(org.bukkit.entity.Player player) {
        this.bukkitPlayer = player;

        this.displayName = player.getDisplayName();
        this.playerListName = player.getPlayerListName();
        this.uniqueId = player.getUniqueId().toString();
        this.compassTarget = new Location(player.getCompassTarget());
        this.ipAddress = player.getAddress().getHostName() != null ? player.getAddress().getHostName() : "";
        this.protocolVersion = player.getProtocolVersion();
        this.virtualHost = player.getVirtualHost() != null ? player.getVirtualHost().getHostName() : "";

        this.isConversing = player.isConversing();
        this.isSneaking = player.isSneaking();
        this.isSprinting = player.isSprinting();
        this.isSleepingIgnored = player.isSleepingIgnored();
    }

    
    public String getDisplayName() {
        return this.bukkitPlayer.getDisplayName();
    }

    
    public String getPlayerListName() {
        return this.bukkitPlayer.getPlayerListName();
    }

    
    public Location getCompassTarget() {
        return null;
    }

    
    public InetSocketAddress getAddress() {
        return null;
    }

    
    public int getProtocolVersion() {
        return 0;
    }

    @Nullable
    
    public InetSocketAddress getVirtualHost() {
        return null;
    }

    
    public boolean isConversing() {
        return false;
    }

    
    public boolean isSneaking() {
        return false;
    }

    
    public boolean isSprinting() {
        return false;
    }

    
    public boolean isSleepingIgnored() {
        return false;
    }

    
    public long getPlayerTime() {
        return 0;
    }

    
    public long getPlayerTimeOffset() {
        return 0;
    }

    
    public boolean isPlayerTimeRelative() {
        return false;
    }

    
    public WeatherType getPlayerWeather() {
        return null;
    }

    
    public float getExp() {
        return 0;
    }

    
    public int getLevel() {
        return 0;
    }

    
    public int getTotalExperience() {
        return 0;
    }

    
    public float getExhaustion() {
        return 0;
    }

    
    public float getSaturation() {
        return 0;
    }

    
    public int getFoodLevel() {
        return 0;
    }

    
    public boolean isOnline() {
        return false;
    }

    
    public boolean isBanned() {
        return false;
    }

    
    public boolean isWhitelisted() {
        return false;
    }

    
    public long getFirstPlayed() {
        return 0;
    }

    
    public long getLastPlayed() {
        return 0;
    }

    
    public boolean hasPlayedBefore() {
        return false;
    }

    
    public Location getBedSpawnLocation() {
        return null;
    }

    
    public boolean getAllowFlight() {
        return false;
    }

    
    public boolean isFlying() {
        return false;
    }

    
    public float getFlySpeed() {
        return 0;
    }

    
    public float getWalkSpeed() {
        return 0;
    }

    
    public Scoreboard getScoreboard() {
        return null;
    }

    
    public boolean isHealthScaled() {
        return false;
    }

    
    public double getHealthScale() {
        return 0;
    }

    
    public Entity getSpectatorTarget() {
        return null;
    }

    
    public String getLocale() {
        return null;
    }

    
    public boolean getAffectsSpawning() {
        return false;
    }

    
    public int getViewDistance() {
        return 0;
    }

    
    public PlayerResourcePackStatusEvent.Status getResourcePackStatus() {
        return null;
    }

    
    public String getResourcePackHash() {
        return null;
    }

    
    public boolean hasResourcePack() {
        return false;
    }

    
    public PlayerProfile getPlayerProfile() {
        return null;
    }

    
    public Location getLocation() {
        return null;
    }

    
    public Vector getVelocity() {
        return null;
    }

    
    public double getHeight() {
        return 0;
    }

    
    public double getWidth() {
        return 0;
    }

    
    public boolean isOnGround() {
        return false;
    }

    
    public World getWorld() {
        return null;
    }

    
    public int getEntityId() {
        return 0;
    }

    
    public int getFireTicks() {
        return 0;
    }

    
    public int getMaxFireTicks() {
        return 0;
    }

    
    public boolean isDead() {
        return false;
    }

    
    public boolean isValid() {
        return false;
    }

    
    public Entity getPassenger() {
        return null;
    }

    
    public List<Entity> getPassengers() {
        return null;
    }

    
    public boolean isEmpty() {
        return false;
    }

    
    public boolean eject() {
        return false;
    }

    
    public float getFallDistance() {
        return 0;
    }

    
    public EntityDamageEvent getLastDamageCause() {
        return null;
    }

    
    public UUID getUniqueId() {
        return null;
    }

    
    public int getTicksLived() {
        return 0;
    }

    
    public EntityType getType() {
        return null;
    }

    
    public boolean isInsideVehicle() {
        return false;
    }

    
    public boolean leaveVehicle() {
        return false;
    }

    
    public Entity getVehicle() {
        return null;
    }

    
    public boolean isCustomNameVisible() {
        return false;
    }

    
    public boolean isGlowing() {
        return false;
    }

    
    public boolean isInvulnerable() {
        return false;
    }

    
    public boolean isSilent() {
        return false;
    }

    
    public boolean hasGravity() {
        return false;
    }

    
    public int getPortalCooldown() {
        return 0;
    }

    
    public Set<String> getScoreboardTags() {
        return null;
    }

    
    public PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    
    public Location getOrigin() {
        return null;
    }

    
    public boolean fromMobSpawner() {
        return false;
    }

    
    public Chunk getChunk() {
        return null;
    }

    
    public String getName() {
        return null;
    }

    
    public PlayerInventory getInventory() {
        return null;
    }

    
    public Inventory getEnderChest() {
        return null;
    }

    
    public MainHand getMainHand() {
        return null;
    }

    
    public InventoryView getOpenInventory() {
        return null;
    }

    
    public ItemStack getItemInHand() {
        return null;
    }

    
    public ItemStack getItemOnCursor() {
        return null;
    }

    
    public int getCooldown(Material material) {
        return 0;
    }

    
    public boolean isSleeping() {
        return false;
    }

    
    public int getSleepTicks() {
        return 0;
    }

    
    public GameMode getGameMode() {
        return null;
    }

    
    public boolean isBlocking() {
        return false;
    }

    
    public double getEyeHeight() {
        return 0;
    }

    
    public Location getEyeLocation() {
        return null;
    }

    
    public int getRemainingAir() {
        return 0;
    }

    
    public int getMaximumAir() {
        return 0;
    }

    
    public int getMaximumNoDamageTicks() {
        return 0;
    }

    
    public double getLastDamage() {
        return 0;
    }

    
    public int getNoDamageTicks() {
        return 0;
    }

    
    public org.bukkit.entity.Player getKiller() {
        return null;
    }

    
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    
    public boolean getRemoveWhenFarAway() {
        return false;
    }

    
    public EntityEquipment getEquipment() {
        return null;
    }

    
    public boolean getCanPickupItems() {
        return false;
    }

    
    public boolean isLeashed() {
        return false;
    }

    
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    
    public boolean isGliding() {
        return false;
    }

    
    public boolean hasAI() {
        return false;
    }

    
    public boolean isCollidable() {
        return false;
    }

    
    public int getArrowsStuck() {
        return 0;
    }

    
    public int getShieldBlockingDelay() {
        return 0;
    }

    
    public ItemStack getActiveItem() {
        return null;
    }

    
    public int getItemUseRemainingTime() {
        return 0;
    }

    
    public int getHandRaisedTime() {
        return 0;
    }

    
    public boolean isHandRaised() {
        return false;
    }

    
    public int getExpToLevel() {
        return 0;
    }

    
    public Entity getShoulderEntityLeft() {
        return null;
    }

    
    public Entity getShoulderEntityRight() {
        return null;
    }

    
    public double getHealth() {
        return 0;
    }

    
    public double getMaxHealth() {
        return 0;
    }

    
    public String getCustomName() {
        return null;
    }

    
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    
    public boolean isOp() {
        return false;
    }

    
    public Set<String> getListeningPluginChannels() {
        return null;
    }

}
