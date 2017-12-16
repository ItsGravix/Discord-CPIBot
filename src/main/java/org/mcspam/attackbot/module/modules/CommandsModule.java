package org.mcspam.attackbot.module.modules;

import com.operabot.commands.CommandManager;
import com.operabot.module.AbstractModule;

public class CommandsModule extends AbstractModule {

    public CommandsModule() {
        super("Commands");
    }

    private static CommandManager commandManager;

    @Override
    public void onEnable() {
        format("SYSTEM", "Starting the command manager");

        commandManager = new CommandManager();

        format("SYSTEM", "Command Manager loaded! (" + getMSUsed() + " ms)");
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }
}
