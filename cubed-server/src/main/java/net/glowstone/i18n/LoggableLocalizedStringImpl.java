package net.glowstone.i18n;

import java.util.ResourceBundle;
import net.glowstone.GlowServer;
import org.slf4j.Logger;
import org.slf4j.event.Level;

class LoggableLocalizedStringImpl extends LocalizedStringImpl
    implements LoggableLocalizedString {

    private final Level logLevel;

    private final Logger logger;

    LoggableLocalizedStringImpl(String key, Level logLevel) {
        super(key);
        this.logLevel = logLevel;
        this.logger = GlowServer.logger;
    }

    LoggableLocalizedStringImpl(String key, Level logLevel,
                                ResourceBundle resourceBundle,
                                Logger logger) {
        super(key, resourceBundle);
        this.logLevel = logLevel;
        this.logger = logger;
    }

    @Override
    public void log() {
        switch (logLevel) {
            case TRACE:
                logger.trace(get());
                break;
            case ERROR:
                logger.error(get());
                break;
            case DEBUG:
                logger.debug(get());
                break;
            case WARN:
                logger.warn(get());
                break;
            default:
            case INFO:
                logger.info(get());
                break;
        }
    }

    @Override
    public void log(Object... args) {
        switch (logLevel) {
            case TRACE:
                logger.trace(get(args));
                break;
            case ERROR:
                logger.error(get(args));
                break;
            case DEBUG:
                logger.debug(get(args));
                break;
            case WARN:
                logger.warn(get(args));
                break;
            default:
            case INFO:
                logger.info(get(args));
                break;
        }
    }

    @Override
    public void log(Throwable ex) {
        switch (logLevel) {
            case TRACE:
                logger.trace(get(), ex);
                break;
            case ERROR:
                logger.error(get(), ex);
                break;
            case DEBUG:
                logger.debug(get(), ex);
                break;
            case WARN:
                logger.warn(get(), ex);
                break;
            default:
            case INFO:
                logger.info(get(), ex);
                break;
        }
    }

    @Override
    public void log(Throwable ex, Object... args) {
        switch (logLevel) {
            case TRACE:
                logger.trace(get(args), ex);
                break;
            case ERROR:
                logger.error(get(args), ex);
                break;
            case DEBUG:
                logger.debug(get(args), ex);
                break;
            case WARN:
                logger.warn(get(args), ex);
                break;
            default:
            case INFO:
                logger.info(get(args), ex);
                break;
        }
    }
}
