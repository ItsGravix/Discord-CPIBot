package org.mcspam.attackbot.bot.session.sessions;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.mcspam.attackbot.bot.session.Session;
import org.mcspam.attackbot.main.BotUser;
import org.mcspam.attackbot.main.mcspam.MCSpamUser;
import org.mcspam.attackbot.utils.LogUtils;

import java.sql.SQLException;

/**
 * Created by origi on 12/16/2017.
 */
public class LoginSession extends Session {
    private boolean askedForUsername = false;

    public LoginSession(BotUser botUser) {
        super("Login", botUser);
    }

    @Override
    public void handle(String[] args, Message inputMessage, MessageChannel channel) {
        String message = inputMessage.getContentRaw();

        try {
            // Checks
            if (getBotUser().isMCSpamUserSet()) {
                finishSession();
                return;
            }

            if (channel.getType() != ChannelType.PRIVATE) {
                if (!message.startsWith("!"))
                    return;
                channel.sendMessage("Please check the message I sent you via PM!").queue();
                askForUsername();
            } else {
                if (message.startsWith("!"))
                    return;
                if (!this.askedForUsername) {
                    LogUtils.log("beep");
                    askForUsername();
                    return;
                }

                long msgID = channel.sendMessage("Logging in to MCSpam.org...").complete().getIdLong();
                getBotUser().setMCSpamUser(new MCSpamUser(getBotUser(), message));

                channel.editMessageById(msgID, "You have logged in to MCSpam.org successfully! You can now use the bot commands normally.").queue();
                finishSession();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void askForUsername() {
        this.askedForUsername = true;
        getBotUser().getDiscordUser().openPrivateChannel().queue((privateChannel) ->
                privateChannel.sendMessage("What's your MCSpam.org username? (send it here)").queue());
    }
}