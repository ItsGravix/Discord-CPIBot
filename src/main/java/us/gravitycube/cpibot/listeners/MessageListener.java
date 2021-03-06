package us.gravitycube.cpibot.listeners;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import us.gravitycube.cpibot.bot.session.Session;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.main.BotUserManager;
import us.gravitycube.cpibot.module.modules.CommandsModule;
import us.gravitycube.cpibot.utils.LogUtils;

import java.sql.SQLException;

/**
 * Created by origi on 12/12/2017.
 */
public class MessageListener extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot())
            return;

        try {
            String message = event.getMessage().getContentRaw();

            User author = event.getAuthor();
            BotUser botUser = BotUserManager.getUser(author); // Get or Load the User

            MessageChannel channel = event.getChannel();
            Guild guild = null;

            if (channel.getType() == ChannelType.PRIVATE)
                guild = event.getGuild();

            LogUtils.format("Message Received", "(" + (guild == null ? channel.getName() : guild.getName()) + ") " + author.getName() + "#" + author.getDiscriminator() + ": " + message);

            // Setup or Handle Active Session
            botUser.setupNeededSessions();
            Session activeSession = botUser.getSessionManager().getActiveSession();

            if (activeSession != null) {
                activeSession.handle(message.split(" "), event.getMessage(), event.getChannel());
                return;
            }

            // Handle Command
            if (message.startsWith("!")) {
                message = message.replaceFirst("!", "");
                String[] args = message.split(" ");

                CommandsModule.getCommandManager().runCommand(args, channel, botUser, event.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
