package us.gravitycube.cpibot.bot.commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.commands.user.AttackCmd;
import us.gravitycube.cpibot.bot.commands.user.HelpCmd;
import us.gravitycube.cpibot.bot.commands.user.SayCmd;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.utils.LogUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandManager extends LogUtils {

    private List<BotCommand> commands = new ArrayList<>();

    public CommandManager() {
        // Register Commands
        registerCommand(new HelpCmd());
        registerCommand(new SayCmd());
        registerCommand(new AttackCmd());
    }

    public void runCommand(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        try {
            BotCommand botCommand = getCommand(args);

            if (botCommand != null) {
                if (user.getRank().getLevel() < botCommand.getRequiredRank().getLevel()) {
                    channel.sendMessage("You don't have permission to use this command!").queue();
                    return;
                }

                botCommand.execute(args, channel, user, inputMessage);
            } else {
                if (args[0].equals("") || args[0].equals(" ") || args[0].contains("ㅤ") || args[0].length() >= 10) {
                    channel.sendMessage("Please enter a valid command! Type !help to view all commands").queue();
                } else {
                    channel.sendMessage("The command " + args[0] + " does not exist! Type !help to view all commands").queue();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void runCommand(String[] args) {
        BotCommand botCommand = getCommand(args);
        if (botCommand != null) {
            botCommand.execute(args);
        } else {
            if (args[0].equals("") || args[0].equals(" ") || args[0].contains("ㅤ") || args[0].length() >= 10) {
                log("Please enter a valid command! Type 'help' to view all commands");
            } else {
                log("The command " + args[0] + " does not exist! Type 'help' to view all commands");
            }
        }
    }

    public BotCommand getCommand(String[] args) {
        for (BotCommand command : commands) {
            //LogUtils.log(args[0] + " | " + command.getCommandName());
            if (args[0].equalsIgnoreCase(command.getCommandName())) {
                return command;
            } else {
                for (String s : command.getAliases()) {
                    if (s.equalsIgnoreCase(args[0])) {
                        return command;
                    }
                }
            }
        }
        return null;
    }

    public BotCommand getCommand(String args) {
        for (BotCommand command : commands) {
            if (args.equalsIgnoreCase(command.getCommandName())) {
                return command;
            } else {
                for (String s : command.getAliases()) {
                    if (s.equalsIgnoreCase(args)) {
                        return command;
                    }
                }
            }
        }
        return null;
    }

    private void registerCommand(BotCommand botCommand) {
        commands.add(botCommand);
        debug("CommandRegistered","The command '"+botCommand.getCommandName()+"' has been registered!");
    }

    public List<BotCommand> getCommands() {
        return commands;
    }
}
