package us.gravitycube.cpibot.module;

import org.mcspam.attackbot.module.modules.*;
import us.gravitycube.cpibot.utils.LogUtils;
import us.gravitycube.cpibot.module.modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager extends LogUtils {

    private List<AbstractModule> modules = new ArrayList<>();
    private long totalTime;

    private static LoggerModule loggerModule;
    private static ConfigModule configModule;
    private static DiscordModule discordModule;
    private static CommandsModule commandsModule;
    private static ConsoleCommandsModule consoleCommandsModule;
    private static DatabaseModule databaseModule;

    public void start() {
        totalTime = System.currentTimeMillis();

        registerModule(loggerModule = new LoggerModule());
        registerModule(configModule = new ConfigModule());
        registerModule(discordModule = new DiscordModule(configModule.getProperty("DISCORD_TOKEN")));
        registerModule(commandsModule = new CommandsModule());
        registerModule(consoleCommandsModule = new ConsoleCommandsModule());
        registerModule(databaseModule = new DatabaseModule());

        format("SYSTEM", "Done (" + (System.currentTimeMillis() - totalTime) + " ms) For help, type '" + "help'");
    }

    private void registerModule(AbstractModule module) {
        modules.add(module);
        module.onEnable();
    }

    public static LoggerModule getLoggerModule() {
        return loggerModule;
    }

    public static ConfigModule getConfigModule() {
        return configModule;
    }

    public static DiscordModule getSkypeModule() {
        return discordModule;
    }

    public static CommandsModule getCommandsModule() {
        return commandsModule;
    }

    public static ConsoleCommandsModule getConsoleCommandsModule() {
        return consoleCommandsModule;
    }

    public static DatabaseModule getDatabaseModule() {
        return databaseModule;
    }
}
