package net.glowstone;

import net.glowstone.io.persistence.BanList;
import org.bukkit.Server;

public interface IGlowServer extends Server {

   BanList getBanList();

}
