package net.cubedserver.webservices.exception;

import net.cubedserver.webservices.dto.BannedPlayer;

public class InvalidBannedPlayerException extends RuntimeException {

    public InvalidBannedPlayerException(BannedPlayer player) {
        super("Banned player " + player.toString() + " is invalid. Please review before sending again.");
    }
}
