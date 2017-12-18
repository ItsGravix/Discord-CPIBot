package us.gravitycube.cpibot.bot.commands.user;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.commands.BotCommand;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.utils.StringUtils;

public class SayCmd extends BotCommand {

    public SayCmd() {
        super("say", new String[]{}, "The bot will say the text", " <text>", Rank.USER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        if (args.length >= 2) {
            String messageToSay = StringUtils.join(args, 1, " ");
            channel.sendMessage(messageToSay).queue();

            if (messageToSay.startsWith("!") || messageToSay.startsWith("/"))
                user.getDiscordUser().openPrivateChannel().queue((privateChannel) ->
                        privateChannel.sendMessage("Nice try.").queue());
        } else {
            channel.sendMessage(getUsageFormatted());
        }
    }

    @Override
    public void execute(String[] args) {
        log("This command can't be executed by Console!");
    }
}
