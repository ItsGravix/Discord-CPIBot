package us.gravitycube.cpibot.bot.session.sessions;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.session.Session;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.utils.LogUtils;

import java.sql.SQLException;

/**
 * Created by Gravix on 12/16/2017.
 */
public class AttackSession extends Session {
    private boolean botAmountAsked = false;
    private boolean serverAsked = false;

    public AttackSession(BotUser botUser) {
        super("Attack", botUser);
    }

    @Override
    public void handle(String[] args, Message inputMessage, MessageChannel channel) {
        String message = inputMessage.getContentRaw();

        if (message.startsWith("!"))
            return;

        if (!botAmountAsked) {
            askBotAmount();
            return;
        }
        if (!serverAsked) {
            askServer();
            return;
        }

        // Start Attack
        long msgID = channel.sendMessage("Great! Starting Attack...").complete().getIdLong();


        channel.editMessageById(msgID, "You have logged in to MCSpam.org successfully! You can now use the bot commands normally.").queue();
        finishSession();
    }

    private void askBotAmount() {
        this.botAmountAsked = true;
        getBotUser().getDiscordUser().openPrivateChannel().queue((privateChannel) ->
                privateChannel.sendMessage("How many bots do you want to connect? (50 max)").queue());
    }

    private void askServer() {
        this.serverAsked = true;
        getBotUser().getDiscordUser().openPrivateChannel().queue((privateChannel) ->
                privateChannel.sendMessage("What server do you want the bots to connect to? (type !servers for a list)").queue());
    }
}