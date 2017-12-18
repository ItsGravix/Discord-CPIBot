package us.gravitycube.cpibot.main;

import net.dv8tion.jda.core.entities.User;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.bot.session.SessionManager;
import us.gravitycube.cpibot.main.cpibotting.Attack;
import us.gravitycube.cpibot.module.modules.DatabaseModule;
import us.gravitycube.cpibot.utils.LogUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by origi on 12/16/2017.
 */
public class BotUser {
    private Connection conn = DatabaseModule.getBotDatabase().conn;

    private User discordUser;
    private SessionManager sessionManager;

    private Attack attack;

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

        String query = " insert into users (id, user_id, user_tag, rank, date_created)"
                + " values (NULL, ?, ?, ?, ?)";

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
            //String mcSpamUsername = rs.getString("mcspam_username");

            // Load Data
            //mcSpamUser = new MCSpamUser(this, mcSpamUsername);
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

    public Attack getAttack() {
        return attack;
    }
}
