package net.glowstone.webservices.dto;

public class Location {

    private String world;
    private double x;
    private double z;
    private double y;
    private float pitch;
    private float yaw;

    public Location(org.bukkit.Location location) {
        this.world = location.getWorld().getUID().toString();
        this.x = location.getX();
        this.z = location.getZ();
        this.y = location.getY();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }
}
