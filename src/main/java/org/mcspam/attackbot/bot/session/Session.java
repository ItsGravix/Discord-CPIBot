package org.mcspam.attackbot.bot.session;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.mcspam.attackbot.main.BotUser;

/**
 * Created by origi on 12/16/2017.
 */
public abstract class Session {
    private String sessionName;

    private BotUser botUser;

    public Session(String sessionName, BotUser botUser) {
        this.sessionName = sessionName;
        this.botUser = botUser;
    }

    public abstract void handle(String[] args, Message inputMessage, MessageChannel channel);

    public String getSessionName() {
        return sessionName;
    }

    public BotUser getBotUser() {
        return botUser;
    }

    public void finishSession() {
        getBotUser().getSessionManager().removeActiveSession();
    }
}
