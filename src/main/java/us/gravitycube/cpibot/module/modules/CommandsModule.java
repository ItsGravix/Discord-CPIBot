package us.gravitycube.cpibot.module.modules;

import us.gravitycube.cpibot.bot.commands.CommandManager;
import us.gravitycube.cpibot.module.AbstractModule;

public class CommandsModule extends AbstractModule {

    public CommandsModule() {
        super("Commands");
    }

    private static CommandManager commandManager;

    @Override
    public void onEnable() {
        format("SYSTEM", "Loading Command Manager...");

        commandManager = new CommandManager();

        format("SYSTEM", "Command Manager loaded! (" + getMSUsed() + " ms)");
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
