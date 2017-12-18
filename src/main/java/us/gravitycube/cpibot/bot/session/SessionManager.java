package us.gravitycube.cpibot.bot.session;

import org.mcspam.attackbot.module.modules.*;

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
