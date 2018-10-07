package net.glowstone;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.glowstone.block.entity.state.GlowDispenser;
import net.glowstone.constants.GlowEnchantment;
import net.glowstone.constants.GlowPotionEffect;
import net.glowstone.i18n.ConsoleMessages;
import net.glowstone.util.config.ServerConfig;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.jetbrains.annotations.NonNls;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;

public class Main {

    private static GlowServer server;

    public static void main(String[] args) {

        Injector guiceInjector = Guice.createInjector(new GlowModule());

        server = guiceInjector.getInstance(GlowServer.class);

        server.init(parseArguments(args));

        ConfigurationSerialization.registerClass(GlowOfflinePlayer.class);
        GlowPotionEffect.register();
        GlowEnchantment.register();
        GlowDispenser.register();

        server.run();

/*        try {
            server = createFromArguments(args);

            // we don't want to run a server when called with --version, --help or --generate-config
            if (server == null) {
                return;
            }
*//*            if (generateConfigOnly) {
                ConsoleMessages.Info.CONFIG_ONLY_DONE.log();
                return;
            }*//*

            server.run();
        } catch (SecurityException e) {
            ConsoleMessages.Error.CLASSPATH.log(e);
        } catch (Throwable t) {
            // general server startup crash
            ConsoleMessages.Error.STARTUP.log(t);
            System.exit(1);
        }*/
    }

    private static ServerConfig parseArguments(String... args) {
        Map<ServerConfig.Key, Object> parameters = new EnumMap<>(ServerConfig.Key.class);
        @NonNls String configDirName = "config";
        @NonNls String configFileName = "glowstone.yml";

        // Calculate acceptable parameters
        for (int i = 0; i < args.length; i++) {
            @NonNls String opt = args[i];

            if (opt.isEmpty() || opt.charAt(0) != '-') {

                System.err.println(ConsoleMessages.Warn.Option.INVALID.get(opt));
            }

            // Help and version
            if ("--help".equals(opt) || "-h".equals(opt) || "-?".equals(opt)) {
                System.out.println(ConsoleMessages.Info.Option.HELP.get());
                return null;
            } else if ("--version".equals(opt) || "-v".equals(opt)) {
                System.out.println(ConsoleMessages.Info.Version.GLOWSTONE.get(
                        GlowServer.class.getPackage().getImplementationVersion()
                ));
                System.out.println(ConsoleMessages.Info.Version.BUKKIT.get(
                        GlowServer.class.getPackage().getSpecificationVersion()
                ));
                System.out.println(ConsoleMessages.Info.Version.MINECRAFT_CLIENT.get(
                        GlowServer.GAME_VERSION, GlowServer.PROTOCOL_VERSION
                ));
                return null;
            } else if ("--generate-config".equals(opt)) {
                //generateConfigOnly = true;
            }

            // Below this point, options require parameters
            if (i == args.length - 1 && !"--generate-config".equals(opt)) {
                System.err.format(ConsoleMessages.Warn.Option.NO_VALUE.get(), opt);
                continue;
            }

            switch (opt) {
                case "--configdir":
                    configDirName = args[++i];
                    break;
                case "--configfile":
                    configFileName = args[++i];
                    break;
                case "--port":
                case "-p":
                    parameters.put(ServerConfig.Key.SERVER_PORT, Integer.valueOf(args[++i]));
                    break;
                case "--host":
                case "-H":
                    parameters.put(ServerConfig.Key.SERVER_IP, args[++i]);
                    break;
                case "--onlinemode":
                case "-o":
                    parameters.put(ServerConfig.Key.ONLINE_MODE, Boolean.valueOf(args[++i]));
                    break;
                case "--jline":
                    parameters.put(ServerConfig.Key.USE_JLINE, Boolean.valueOf(args[++i]));
                    break;
                case "--plugins-dir":
                case "-P":
                    parameters.put(ServerConfig.Key.PLUGIN_FOLDER, args[++i]);
                    break;
                case "--worlds-dir":
                case "-W":
                    parameters.put(ServerConfig.Key.WORLD_FOLDER, args[++i]);
                    break;
                case "--update-dir":
                case "-U":
                    parameters.put(ServerConfig.Key.UPDATE_FOLDER, args[++i]);
                    break;
                case "--max-players":
                case "-M":
                    parameters.put(ServerConfig.Key.MAX_PLAYERS, Integer.valueOf(args[++i]));
                    break;
                case "--world-name":
                case "-N":
                    parameters.put(ServerConfig.Key.LEVEL_NAME, args[++i]);
                    break;
                case "--log-pattern":
                case "-L":
                    parameters.put(ServerConfig.Key.LOG_FILE, args[++i]);
                    break;
                case "--generate-config":
                    // previously handled
                    break;
                default:
                    System.err.format(ConsoleMessages.Warn.Option.INVALID.get(), opt);
            }
        }

        File configDir = new File(configDirName);
        //worldConfig = new WorldConfig(configDir, new File(configDir, "worlds.yml"));
        File configFile = new File(configDir, configFileName);

        return new ServerConfig(configDir, configFile, parameters);
    }

}
