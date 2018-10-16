package net.cubedserver.webservices.exception;

import net.cubedserver.webservices.dto.WhiteListPlayer;

public class InvalidWhiteListPlayerException extends RuntimeException {

    public InvalidWhiteListPlayerException(WhiteListPlayer player) {
        super("WhiteList player " + player.toString() + " is invalid. Please review before sending again.");
    }
}
