package org.mcspam.attackbot.module.modules;

import org.mcspam.attackbot.bot.commands.CommandManager;
import org.mcspam.attackbot.module.AbstractModule;

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
