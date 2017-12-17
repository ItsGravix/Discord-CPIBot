package org.mcspam.attackbot.main.mcspam;

import org.mcspam.attackbot.main.BotUser;
import org.mcspam.attackbot.module.modules.DatabaseModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by origi on 12/16/2017.
 */
public class MCSpamUser {
    private String username;

    private BotUser botUser;
    private MCSpamAPI mcSpamAPI;

    public MCSpamUser (BotUser botUser, String username) {
        this.username = username;

        this.botUser = botUser;
        this.mcSpamAPI = new MCSpamAPI();
    }

    public void sendAttack(String ip, String port, String time, String protocol) {
        mcSpamAPI.sendAttack(ip, port, time, protocol);
    }

    public ArrayList<Attack> getAttacks() {
        ArrayList<Attack> attacks = new ArrayList<>();

        Connection conn = DatabaseModule.getBotDatabase().conn;
        PreparedStatement preparedStatement;

        String query = "SELECT * FROM attacks WHERE user_id = ?";

        try {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, botUser.getDiscordUser().getId());

            // Execute PreparedStatement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String host = rs.getString("host");
                int port = rs.getInt("port");
                int time = rs.getInt("time");
                String protocol = rs.getString("protocol");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attacks;
    }

    public String getUsername() {
        return username;
    }
}
