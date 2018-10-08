package net.glowstone;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.glowstone.io.persistence.*;
import net.glowstone.util.config.ServerConfig;

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
        //bind(Logger.class).toInstance(GlowServer.logger); // Guice provides a logger from @InjectLogger

        bind(ServerConfig.class).toInstance(serverConfig);

        bind(PersistenceManager.class).to(PersistenceManagerImpl.class);

        bind(OpsList.class).to(OpsListImpl.class);
        bind(WhiteList.class).to(WhiteListImpl.class);
        bind(BanList.class).to(BanListImpl.class);

        bindConstant().annotatedWith(Names.named("DATABASE_URL")).to(serverConfig.getString(ServerConfig.Key.DATABASE_URL));
        bindConstant().annotatedWith(Names.named("DATABASE_USER")).to(serverConfig.getString(ServerConfig.Key.DATABASE_USER));
        bindConstant().annotatedWith(Names.named("DATABASE_PASS")).to(serverConfig.getString(ServerConfig.Key.DATABASE_PASS));
        bindConstant().annotatedWith(Names.named("DATABASE_CLASS")).to(serverConfig.getString(ServerConfig.Key.DATABASE_CLASS));
    }
}
