package net.glowstone.net.handler.play.player;

import com.flowpowered.network.MessageHandler;
import java.util.logging.Level;
import net.glowstone.GlowServer;
import net.glowstone.net.GlowSession;
import net.glowstone.net.message.play.player.AdvancementTabMessage;

public class AdvancementTabHandler implements MessageHandler<GlowSession, AdvancementTabMessage> {

    @Override
    public void handle(GlowSession session, AdvancementTabMessage message) {
        if (message.getAction() == AdvancementTabMessage.ACTION_CLOSE) {
            GlowServer.logger.trace("Received AdvancementTabHandler (close)");
        } else {
            GlowServer.logger.trace("Received AdvancementTabHandler (open:{})", message.getTabId());
        }
    }
}
