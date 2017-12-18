package us.gravitycube.cpibot.bot.commands.user;

import net.dv8tion.jda.core.entities.*;
import us.gravitycube.cpibot.bot.commands.BotCommand;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.module.modules.CommandsModule;
import us.gravitycube.cpibot.utils.LogUtils;

public class HelpCmd extends BotCommand {

    public HelpCmd() {
        super("help", new String[]{"ayuda", "h"}, "Returns the help menu", Rank.USER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        LogUtils.log("help");

        if (args.length == 2) {
            String subCommand = args[1];
            BotCommand botCommand = CommandsModule.getCommandManager().getCommand(subCommand);
            if (botCommand != null) {
                channel.sendMessage(botCommand.getUsageFormatted()).queue();
            } else {
                sendHelpMessage(user.getDiscordUser(), channel);
            }
        } else {
            sendHelpMessage(user.getDiscordUser(), channel);
        }
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2) {
            String subCommand = args[1];
            BotCommand botCommand = CommandsModule.getCommandManager().getCommand(subCommand);
            if (botCommand != null) {
                log(botCommand.getUsageFormatted());
            } else {
                log(getHelpMessage());
            }
        } else {
            log(getHelpMessage());
        }
    }

    private void sendHelpMessage(User author, MessageChannel channel) {
        if (channel.getType() != ChannelType.PRIVATE) {
            channel.sendMessage("The User's help menu has been sent to you via private message.").queue();
        }

        author.openPrivateChannel().queue((privateChannel) ->
                privateChannel.sendMessage(getHelpMessage()).queue());
    }

    private String getHelpMessage() {
        return getCommandsText();
    }

    private String getCommandsText() {
        String text = "";
        for (BotCommand botCommand : CommandsModule.getCommandManager().getCommands()) {
            text += "» !" + botCommand.getCommandName() + botCommand.getUsage() + " - " + botCommand.getDescription() + "\n";
        }
        return text;
    }
}
