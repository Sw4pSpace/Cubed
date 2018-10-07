package net.glowstone;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.glowstone.io.persistence.*;
import net.glowstone.util.config.ServerConfig;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;

/**
 * Guice module for Glowserver
 *
 * @author jdesive
 */
public class GlowModule extends AbstractModule {

    private final ServerConfig serverConfig;

    public GlowModule(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    protected void configure() {
        // Bind classes here
        //bind(GlowServer.class).toInstance(new GlowServer());
        //bind(Logger.class).toInstance(GlowServer.logger);

        bind(PersistenceManager.class).to(PersistenceManagerImpl.class);

        bind(OpsList.class).to(OpsListImpl.class);
        bind(WhiteList.class).to(WhiteListImpl.class);

        /* JNDI */
        try {
            InitialContext ic = new InitialContext();

            bindConstant().annotatedWith(Names.named("DATABASE_URL")).to(serverConfig.getString(ServerConfig.Key.DATABASE_URL));
            bindConstant().annotatedWith(Names.named("DATABASE_USER")).to(serverConfig.getString(ServerConfig.Key.DATABASE_USER));
            bindConstant().annotatedWith(Names.named("DATABASE_PASS")).to(serverConfig.getString(ServerConfig.Key.DATABASE_PASS));
            bindConstant().annotatedWith(Names.named("DATABASE_CLASS")).to(serverConfig.getString(ServerConfig.Key.DATABASE_CLASS));

        } catch (NamingException e) {
            GlowServer.logger.log(Level.SEVERE, "GlowModule JNDI variables are missing or invalid", e);
        }
    }
}
