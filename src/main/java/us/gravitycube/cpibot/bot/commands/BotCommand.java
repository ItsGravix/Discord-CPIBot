package us.gravitycube.cpibot.bot.commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.utils.LogUtils;

public abstract class BotCommand extends LogUtils {

    private String commandName;
    private String[] aliases;
    private String description;
    private String usage;
    private Rank requiredRank;

    public BotCommand(String commandName, String[] aliases, String description, Rank requiredRank) {
        this.commandName = commandName;
        this.aliases = aliases;
        this.description = description;
        this.usage = "";
        this.requiredRank = requiredRank;
    }

    public BotCommand(String commandName, String[] aliases, String description, String usage, Rank requiredRank) {
        this(commandName, aliases, description, requiredRank);
        this.usage = usage;
    }

    public void setRequiredRank(Rank requiredRank) {
        this.requiredRank = requiredRank;
    }

    public Rank getRequiredRank() {
        return requiredRank;
    }

    public abstract void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage);

    public abstract void execute(String[] args);

    public String getUsageFormatted() {
        return "Usage !" + getCommandName() + " " + getUsage();
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }
}
