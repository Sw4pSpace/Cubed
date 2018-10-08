package net.glowstone.io.persistence;

import org.bukkit.BanEntry;

import java.util.Date;
import java.util.Set;

public interface BanList {

    void purgeBans();

    BanEntry getBanEntry(String target);

    void addBan(String target, String reason, Date expires, String source);

    Set<BanEntry> getBanEntries();

    boolean isBanned(String target);

    void pardon(String target);

}
