package org.mcspam.attackbot.main.mcspam;

/**
 * Created by origi on 12/16/2017.
 */
public class Attack {
    public String userID;

    public String host;
    public int port;
    public int time;
    public String protocol;

    public Attack(String userID, String host, int port, int time, String protocol) {
        this.userID = userID;

        this.host = host;
        this.port = port;
        this.time = time;
        this.protocol = protocol;
    }
}
