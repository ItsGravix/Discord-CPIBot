package org.mcspam.attackbot.main;

import net.dv8tion.jda.core.entities.User;
import org.mcspam.attackbot.bot.ranks.Rank;
import org.mcspam.attackbot.bot.session.SessionManager;
import org.mcspam.attackbot.bot.session.sessions.LoginSession;
import org.mcspam.attackbot.main.mcspam.MCSpamUser;
import org.mcspam.attackbot.module.modules.DatabaseModule;
import org.mcspam.attackbot.utils.LogUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by origi on 12/16/2017.
 */
public class BotUser {
    private Connection conn = DatabaseModule.getBotDatabase().conn;

    private User discordUser;
    private MCSpamUser mcSpamUser;
    private SessionManager sessionManager;

    public BotUser(User discordUser) {
        this.discordUser = discordUser;
        this.sessionManager = new SessionManager();

        try {
            // Add or Load User
            if (isInDatabase()) {
                loadFromDatabase();
            } else {
                addToDatabase();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupNeededSessions() throws SQLException {
        if (sessionManager.getActiveSession() != null)
            return;

        if (mcSpamUser == null || !isMCSpamUserSet()) {
            sessionManager.setActiveSession(new LoginSession(this));
            return;
        }
    }

    public void setMCSpamUser(MCSpamUser mcSpamUser) throws SQLException {
        this.mcSpamUser = mcSpamUser;

        String query = "UPDATE users SET mcspam_username = ? WHERE user_id = ?";

        // Create PreparedStatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString (1, mcSpamUser.getUsername());
        preparedStmt.setString (2, discordUser.getId());

        preparedStmt.execute();

        LogUtils.log(preparedStmt.toString());
    }

    public boolean isMCSpamUserSet() throws SQLException {
        PreparedStatement preparedStatement;

        String query = "SELECT * FROM users WHERE user_id = ?";

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, discordUser.getId());

        // Execute PreparedStatement
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return rs.getString("mcspam_username") != null;
        }

        preparedStatement.close();
        return false;
    }

    public Rank getRank() throws SQLException {
        PreparedStatement preparedStatement;

        String query = "SELECT * FROM users WHERE user_id = ?";

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, discordUser.getId());

        // Execute PreparedStatement
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return Rank.fromLevel(rs.getInt("rank"));
        }

        preparedStatement.close();
        return null;
    }

    public void addToDatabase() throws SQLException {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        String query = " insert into users (id, user_id, user_tag, mcspam_username, rank, date_created)"
                + " values (NULL, ?, ?, NULL, ?, ?)";

        // Create PreparedStatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString (1, discordUser.getId());
        preparedStmt.setString (2, discordUser.getName() + "#" + discordUser.getDiscriminator());
        preparedStmt.setInt(3, Rank.USER.getLevel());
        preparedStmt.setDate(4, startDate);

        preparedStmt.execute();
    }

    public void loadFromDatabase() throws SQLException {
        PreparedStatement preparedStatement;

        String query = "SELECT * FROM users WHERE user_id = ?";

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, discordUser.getId());

        // Execute PreparedStatement
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            // Get Data
            String mcSpamUsername = rs.getString("mcspam_username");

            // Load Data
            mcSpamUser = new MCSpamUser(this, mcSpamUsername);
        }

        preparedStatement.close();
    }

    public boolean isInDatabase() throws SQLException {
        PreparedStatement preparedStatement;

        String query = "SELECT * FROM users WHERE user_id = ?";

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, discordUser.getId());

        // Execute PreparedStatement
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return true;
        }

        preparedStatement.close();
        return false;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public User getDiscordUser() {
        return discordUser;
    }

    public MCSpamUser getMcSpamUser() {
        return mcSpamUser;
    }
}
