package net.glowstone;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    private static GlowServer server;
    private static AtomicBoolean generateConfigOnly = new AtomicBoolean(false);

    public static void main(String[] args) {
        ServerConfig serverConfig = parseArguments(args);
        Injector guiceInjector = Guice.createInjector(new GlowModule(serverConfig));

        server = guiceInjector.getInstance(GlowServer.class);

        server.init();

        ConfigurationSerialization.registerClass(GlowOfflinePlayer.class);
        GlowPotionEffect.register();
        GlowEnchantment.register();
        GlowDispenser.register();

        if(generateConfigOnly.get()){
            ConsoleMessages.Info.CONFIG_ONLY_DONE.log();
        }else {
            server.run();
        }
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
                generateConfigOnly.set(true);
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
        File configFile = new File(configDir, configFileName);

        return new ServerConfig(configDir, configFile, parameters);
    }

}
