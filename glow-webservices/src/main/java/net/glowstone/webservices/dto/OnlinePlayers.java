package net.glowstone.webservices.dto;

import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class OnlinePlayers {

    private int playerCount;
    private int maxPlayers;
    private List<Player> onlinePlayers;

    public OnlinePlayers() {
        Collection<? extends org.bukkit.entity.Player> players = Bukkit.getOnlinePlayers();
        this.playerCount = players.size();
        this.maxPlayers = Bukkit.getMaxPlayers();
        onlinePlayers = players.stream().map(p -> new Player(p)).collect(Collectors.toList());
    }
}
