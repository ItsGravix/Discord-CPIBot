package org.mcspam.attackbot.bot.session;

import org.mcspam.attackbot.bot.session.sessions.LoginSession;
import org.mcspam.attackbot.main.BotUser;
import org.mcspam.attackbot.module.AbstractModule;
import org.mcspam.attackbot.module.modules.*;
import org.mcspam.attackbot.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private Session activeSession = null;

    public void setActiveSession(Session activeSession) {
        this.activeSession = activeSession;
    }

    public void removeActiveSession() { this.activeSession = null; }

    public Session getActiveSession() {
        return activeSession;
    }
}
