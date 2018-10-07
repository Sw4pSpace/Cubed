package net.glowstone;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Guice module for Glowserver
 *
 * @author jdesive
 */
public class GlowModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind classes here
        //bind(GlowServer.class).toInstance(new GlowServer());
    }
}
