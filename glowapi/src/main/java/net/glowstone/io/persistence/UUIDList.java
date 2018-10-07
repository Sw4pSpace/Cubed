package net.glowstone.io.persistence;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.UUID;

public interface UUIDList {

    List<PlayerProfile> getProfiles();

    boolean containsUuid(UUID uuid);

    boolean containsProfile(PlayerProfile profile);

    void add(OfflinePlayer player);

    void remove(PlayerProfile profile);

}
