package net.cubedserver.webservices.exception;

import net.cubedserver.webservices.dto.OpPlayer;

public class InvalidOpPlayerException extends RuntimeException {

    public InvalidOpPlayerException(OpPlayer player) {
        super("Op player " + player.toString() + " is invalid. Please review before sending again.");
    }
}
