package org.mcspam.attackbot.bot.commands.user;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import org.mcspam.attackbot.bot.commands.BotCommand;
import org.mcspam.attackbot.bot.ranks.Rank;
import org.mcspam.attackbot.main.BotUser;
import org.mcspam.attackbot.utils.StringUtils;

public class SayCmd extends BotCommand {

    public SayCmd() {
        super("say", new String[]{}, "The bot will say the text", " <text>", Rank.USER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        if (args.length >= 2) {
            String messageToSay = StringUtils.join(args, 1, " ");
            channel.sendMessage(messageToSay).queue();

            debug("SayCmd", "(" + user.getDiscordUser().getDiscriminator() + ") Saying: " + messageToSay);
        } else {
            channel.sendMessage(getUsageFormatted());
        }
    }

    @Override
    public void execute(String[] args) {
        log("This command can't be executed by Console!");
    }
}
