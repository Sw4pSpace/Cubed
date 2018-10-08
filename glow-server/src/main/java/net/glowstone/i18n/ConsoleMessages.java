package net.glowstone.i18n;


import org.slf4j.event.Level;

public interface ConsoleMessages {
    interface Error {
        interface Biome {
            LoggableLocalizedString UNKNOWN = new LoggableLocalizedStringImpl(
                    "console.biome.unknown", Level.ERROR
            );
        }

        interface BlockEntity {
            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.block-entity.load-failed", Level.ERROR
            );

            LoggableLocalizedString SAVE_FAILED = new LoggableLocalizedStringImpl(
                    "console.block-entity.save-failed", Level.ERROR
            );
        }

        interface Chunk {
            LoggableLocalizedString GEN_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.gen-failed", Level.ERROR);

            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.load-failed", Level.ERROR);

            LoggableLocalizedString POP_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.pop-failed", Level.ERROR);

            LoggableLocalizedString REGEN_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.regen-failed", Level.ERROR);

            LoggableLocalizedString SAVE_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.save-failed", Level.ERROR);
        }

        LoggableLocalizedString CLASSPATH = new LoggableLocalizedStringImpl(
                "console.classpath.load-failed", Level.WARN
        );

        interface Function {
            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.function.load-failed", Level.ERROR
            );
        }

        interface Import {
            LoggableLocalizedString NO_MESSAGE = new LoggableLocalizedStringImpl(
                    "console.import.failed.no-message", Level.WARN
            );

            LoggableLocalizedString WITH_MESSAGE = new LoggableLocalizedStringImpl(
                    "console.import.failed.with-message", Level.WARN
            );
        }

        interface Io {

            LoggableLocalizedString MKDIR = new LoggableLocalizedStringImpl(
                    "console.io.mkdir-failed", Level.ERROR
            );

            LoggableLocalizedString PLAYER_READ = new LoggableLocalizedStringImpl(
                    "console.io.player-read-failed", Level.ERROR);

            LoggableLocalizedString PLAYER_READ_UNKNOWN = new LoggableLocalizedStringImpl(
                    "console.io.player-read-failed-unknown", Level.ERROR);

            LoggableLocalizedString PLAYER_WRITE = new LoggableLocalizedStringImpl(
                    "console.io.player-write-failed", Level.ERROR);

            LoggableLocalizedString WORLD_READ = new LoggableLocalizedStringImpl(
                    "console.io.world-read-failed", Level.ERROR
            );
        }

        LoggableLocalizedString LOOTING_MANAGER = new LoggableLocalizedStringImpl(
                "console.looting-manager.load-failed", Level.ERROR
        );

        interface Permission {
            LoggableLocalizedString INVALID = new LoggableLocalizedStringImpl(
                    "console.permission.invalid", Level.ERROR
            ) {
                @Override
                public String get(Object... args) {
                    // This uses String.format instead of MessageFormat.format
                    // for Bukkit compatibility.
                    return String.format(get(), args);
                }
            };
        }

        interface Plugin {
            LoggableLocalizedString LOADING = new LoggableLocalizedStringImpl(
                    "console.plugin.load-failed", Level.ERROR
            );

            LoggableLocalizedString MKDIR = new LoggableLocalizedStringImpl(
                    "console.plugin.mkdir-failed", Level.ERROR
            );
        }

        interface Profile {
            LoggableLocalizedString INTERRUPTED = new LoggableLocalizedStringImpl(
                    "console.profile.interrupted", Level.ERROR
            );
        }

        interface Rcon {
            LoggableLocalizedString BIND_INTERRUPTED = new LoggableLocalizedStringImpl(
                    "console.rcon.bind-interrupted", Level.ERROR
            );
        }

        LoggableLocalizedString RELOAD = new LoggableLocalizedStringImpl(
                "console.reload-failed", Level.ERROR
        );

        LoggableLocalizedString STARTUP = new LoggableLocalizedStringImpl(
                "console.startup-failed", Level.ERROR
        );

        interface Structure {
            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.structure.load-failed", Level.ERROR
            );

            LoggableLocalizedString SAVE_FAILED = new LoggableLocalizedStringImpl(
                    "console.structure.save-failed", Level.ERROR
            );

            LoggableLocalizedString NO_DATA = new LoggableLocalizedStringImpl(
                    "console.structure.no-data", Level.ERROR
            );

            LoggableLocalizedString UNKNOWN_PIECE_TYPE = new LoggableLocalizedStringImpl(
                    "console.structure.unknown-piece-type", Level.ERROR
            );
        }

        interface Uuid {
            LoggableLocalizedString INTERRUPTED = new LoggableLocalizedStringImpl(
                    "console.uuid.interrupted", Level.ERROR
            );
        }

        interface Net {
            interface Crypt {
                LoggableLocalizedString INIT_FAILED = new LoggableLocalizedStringImpl(
                        "console.net.crypt.init-failed", Level.ERROR);

                LoggableLocalizedString RSA_INIT_FAILED = new LoggableLocalizedStringImpl(
                        "glowstone.kick.crypt.rsa-init-failed", Level.ERROR);
            }
        }
    }

    interface Info {

        interface Block {

            LoggableLocalizedString UNKNOWN_CLICKED = new LoggableLocalizedStringImpl(
                    "console.block.unknown-clicked", Level.INFO);
        }

        LoggableLocalizedString CONFIG_ONLY_DONE = new LoggableLocalizedStringImpl(
                "console.config-only-done", Level.INFO
        );

        interface Enchant {
            LoggableLocalizedString NOT_OPEN = new LoggableLocalizedStringImpl(
                    "console.enchant.not-open", Level.INFO);
        }

        interface Icon {
            LoggableLocalizedString IMPORT = new LoggableLocalizedStringImpl(
                    "console.icon.import", Level.INFO
            );
        }

        LoggableLocalizedString IMPORT = new LoggableLocalizedStringImpl(
                "console.import", Level.INFO
        );

        interface NativeTransport {
            LoggableLocalizedString EPOLL = new LoggableLocalizedStringImpl(
                    "console.native-transport.epoll", Level.INFO
            );

            LoggableLocalizedString KQUEUE = new LoggableLocalizedStringImpl(
                    "console.native-transport.kqueue", Level.INFO
            );
        }

        interface Opencl {
            LoggableLocalizedString BEST = new LoggableLocalizedStringImpl(
                    "console.opencl.best", Level.INFO
            );
            LoggableLocalizedString BEST_VERSION_TIEBREAKER = new LoggableLocalizedStringImpl(
                    "console.opencl.best.version-tiebreaker", Level.INFO
            );

            LoggableLocalizedString CPU = new LoggableLocalizedStringImpl(
                    "console.opencl.cpu", Level.INFO
            );

            LoggableLocalizedString FOUND_DEVICE = new LoggableLocalizedStringImpl(
                    "console.opencl.found-device", Level.INFO
            );

            LoggableLocalizedString INTEL_GPU = new LoggableLocalizedStringImpl(
                    "console.opencl.intel-gpu", Level.INFO
            );

            LoggableLocalizedString NO_DEVICE = new LoggableLocalizedStringImpl(
                    "console.opencl.no-device", Level.INFO
            );

            LoggableLocalizedString REQUIRED_EXTENSIONS = new LoggableLocalizedStringImpl(
                    "console.opencl.required-extensions", Level.INFO
            );

            LoggableLocalizedString REQUIRED_VERSION = new LoggableLocalizedStringImpl(
                    "console.opencl.required-version", Level.INFO
            );
        }

        interface Option {
            LoggableLocalizedString HELP = new LoggableLocalizedStringImpl(
                    "console.option.help", Level.INFO
            );
        }

        interface Plugin {
            LoggableLocalizedString COUNTS = new LoggableLocalizedStringImpl(
                    "console.plugin.counts", Level.INFO
            );

            LoggableLocalizedString SCANNING = new LoggableLocalizedStringImpl(
                    "console.plugin.scanning", Level.INFO
            );
        }

        LoggableLocalizedString PROXY = new LoggableLocalizedStringImpl(
                "console.proxy", Level.INFO
        );

        interface Proxy {
            LoggableLocalizedString ONLINE = new LoggableLocalizedStringImpl(
                    "console.proxy.online", Level.INFO
            );
        }

        LoggableLocalizedString READY = new LoggableLocalizedStringImpl(
                "console.ready", Level.INFO
        );

        LoggableLocalizedString RECIPE_COUNTS = new LoggableLocalizedStringImpl(
                "console.recipe.counts", Level.INFO
        );

        LoggableLocalizedString SAVE = new LoggableLocalizedStringImpl(
                "console.save", Level.INFO
        );

        LoggableLocalizedString SHUTDOWN = new LoggableLocalizedStringImpl(
                "console.shutdown", Level.INFO
        );

        interface Version {
            LoggableLocalizedString BUKKIT = new LoggableLocalizedStringImpl(
                    "console.version.bukkit", Level.INFO
            );

            LoggableLocalizedString GLOWSTONE = new LoggableLocalizedStringImpl(
                    "console.version.glowstone", Level.INFO
            );

            LoggableLocalizedString MINECRAFT_CLIENT = new LoggableLocalizedStringImpl(
                    "console.version.minecraft-client", Level.INFO
            );
        }

        interface Net {
            LoggableLocalizedString UNKNOWN_CLIENT_STATUS_ACTION = new LoggableLocalizedStringImpl(
                    "console.net.unknown-client-status-action", Level.INFO);
        }
    }

    interface Warn {

        interface Block {
            interface Chest {
                LoggableLocalizedString FACING = new LoggableLocalizedStringImpl(
                        "console.block.chest.facing", Level.WARN);

                LoggableLocalizedString INTERACT_WRONG_CLASS = new LoggableLocalizedStringImpl(
                        "console.block.chest.interact-wrong-class", Level.WARN);

                LoggableLocalizedString TRIPLE_ALREADY = new LoggableLocalizedStringImpl(
                        "console.block.chest.triple-already", Level.WARN);

                LoggableLocalizedString TRIPLE_END = new LoggableLocalizedStringImpl(
                        "console.block.chest.triple-end", Level.WARN);

                LoggableLocalizedString TRIPLE_MIDDLE = new LoggableLocalizedStringImpl(
                        "console.block.chest.triple-middle", Level.WARN);
            }

            interface DoubleSlab {
                LoggableLocalizedString WRONG_MATERIAL = new LoggableLocalizedStringImpl(
                        "console.block.doubleslab.wrong-material", Level.WARN);
            }

            LoggableLocalizedString WRONG_MATERIAL_DATA = new LoggableLocalizedStringImpl(
                    "console.block.wrong-material-data", Level.WARN);
        }

        interface BlockEntity {
            LoggableLocalizedString UNKNOWN = new LoggableLocalizedStringImpl(
                    "console.block-entity.unknown", Level.WARN
            );
        }

        interface Chunk {
            LoggableLocalizedString SECTION_DUP = new LoggableLocalizedStringImpl(
                    "console.chunk.section-dup", Level.WARN
            );

            LoggableLocalizedString SECTION_OOB = new LoggableLocalizedStringImpl(
                    "console.chunk.section-oob", Level.WARN
            );

            LoggableLocalizedString UNKNOWN_BLOCK_TO_TICK = new LoggableLocalizedStringImpl(
                    "console.chunk.unknown-block-to-tick", Level.WARN
            );
            LoggableLocalizedString UNLOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.chunk.unload-failed", Level.WARN);
        }

        interface Entity {
            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.entity.load-failed", Level.WARN
            );

            LoggableLocalizedString PARTICLE_INVALID = new LoggableLocalizedStringImpl(
                    "console.entity.particle-invalid", Level.WARN
            );

            LoggableLocalizedString SAVE_FAILED = new LoggableLocalizedStringImpl(
                    "console.entity.save-failed", Level.WARN
            );

            LoggableLocalizedString UNKNOWN = new LoggableLocalizedStringImpl(
                    "console.entity.unknown", Level.WARN
            );
        }

        interface Event {
            LoggableLocalizedString INTERRUPTED = new LoggableLocalizedStringImpl(
                    "console.event.interrupted", Level.WARN
            );

            LoggableLocalizedString SHUTDOWN = new LoggableLocalizedStringImpl(
                    "console.event.shutdown", Level.WARN
            );
        }

        interface Icon {
            LoggableLocalizedString LOAD_FAILED_IMPORT = new LoggableLocalizedStringImpl(
                    "console.icon.load-failed.import", Level.WARN
            );

            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.icon.load-failed", Level.WARN
            );
        }

        interface Io {
            LoggableLocalizedString JSON_STAT_UNKNOWN = new LoggableLocalizedStringImpl(
                    "console.io.json.stat-unknown", Level.WARN
            );

            LoggableLocalizedString MKDIR_FAILED = new LoggableLocalizedStringImpl(
                    "console.io.mkdir-failed", Level.WARN
            );

            LoggableLocalizedString NO_WORLD_DATA_TAG = new LoggableLocalizedStringImpl(
                    "console.io.no-world-data-tag", Level.WARN
            );

            LoggableLocalizedString REMOVING_SINGLE_PLAYER = new LoggableLocalizedStringImpl(
                    "console.io.removing-single-player", Level.WARN
            );
        }

        interface Net {
            LoggableLocalizedString MESSAGE_TOO_LONG = new LoggableLocalizedStringImpl(
                    "console.net.message-too-long", Level.WARN
            );

            LoggableLocalizedString CRAFTING_BOOK_UNSUPPORTED = new LoggableLocalizedStringImpl(
                    "console.net.crafting-book-unsupported", Level.WARN);

            LoggableLocalizedString CRAFTING_RECIPE_UNSUPPORTED = new LoggableLocalizedStringImpl(
                    "console.net.crafting-recipe-unsupported", Level.WARN);
        }

        LoggableLocalizedString OFFLINE = new LoggableLocalizedStringImpl(
                "console.offline", Level.WARN
        );

        interface Option {
            LoggableLocalizedString INVALID = new LoggableLocalizedStringImpl(
                    "console.option.invalid", Level.WARN
            );

            LoggableLocalizedString NO_VALUE = new LoggableLocalizedStringImpl(
                    "console.option.no-value", Level.WARN
            );
        }

        interface Permission {
            LoggableLocalizedString DUPLICATE = new LoggableLocalizedStringImpl(
                    "console.permission.duplicate", Level.WARN
            );
        }

        interface Plugin {
            LoggableLocalizedString NO_SPONGE = new LoggableLocalizedStringImpl(
                    "console.plugin.no-sponge", Level.WARN
            );

            LoggableLocalizedString UNRECOGNIZED = new LoggableLocalizedStringImpl(
                    "console.plugin.unrecognized", Level.WARN
            );

            LoggableLocalizedString MALFORMED_URL = new LoggableLocalizedStringImpl(
                    "console.plugin.malformed-url", Level.WARN
            );

            LoggableLocalizedString LOAD_FAILED = new LoggableLocalizedStringImpl(
                    "console.plugin.load-failed.type-detector", Level.WARN
            );

            LoggableLocalizedString BUKKIT2SPONGE = new LoggableLocalizedStringImpl(
                    "console.plugin.no-sponge.bukkit2sponge", Level.WARN
            );

            LoggableLocalizedString PERMISSION_DUPLICATE = new LoggableLocalizedStringImpl(
                    "console.plugin.permission.duplicate", Level.WARN
            );

            LoggableLocalizedString UNSUPPORTED = new LoggableLocalizedStringImpl(
                    "console.plugin.unsupported", Level.WARN
            );

            LoggableLocalizedString UNSUPPORTED_CANARY = new LoggableLocalizedStringImpl(
                    "console.plugin.unsupported.canary", Level.WARN
            );

            LoggableLocalizedString UNSUPPORTED_FORGE = new LoggableLocalizedStringImpl(
                    "console.plugin.unsupported.forge", Level.WARN
            );

            LoggableLocalizedString UNSUPPORTED_OTHER = new LoggableLocalizedStringImpl(
                    "console.plugin.unsupported.other", Level.WARN
            );

            LoggableLocalizedString UNSUPPORTED_SPONGE = new LoggableLocalizedStringImpl(
                    "console.plugin.unsupported.sponge", Level.WARN
            );
        }

        interface Profile {
            LoggableLocalizedString TIMEOUT = new LoggableLocalizedStringImpl(
                    "console.profile.timeout", Level.WARN
            );
        }

        interface Recipe {
            LoggableLocalizedString NO_DEFAULTS = new LoggableLocalizedStringImpl(
                    "console.recipe.no-defaults", Level.WARN
            );
        }

        interface Uuid {
            LoggableLocalizedString TIMEOUT = new LoggableLocalizedStringImpl(
                    "console.uuid.timeout", Level.WARN
            );
        }

        interface WorldGen {
            LoggableLocalizedString DISABLED = new LoggableLocalizedStringImpl(
                    "console.worldgen.disabled", Level.WARN
            );
        }
    }
}
