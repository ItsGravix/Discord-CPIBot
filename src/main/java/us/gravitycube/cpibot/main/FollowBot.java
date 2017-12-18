package us.gravitycube.cpibot.main;

import me.legit.cpi.api.APIKey;
import me.legit.cpi.api.Bot;
import me.legit.cpi.api.enums.World;

/**
 * Created by origi on 12/9/2017.
 */
public class FollowBot {
    public Bot client = new Bot();
    public World world;
    private APIKey apiKey;

    public String username;
    public String password;

    public float offsetX = 0;
    public float offsetY = 0;
    public float offsetZ = 0;

    public FollowBot(String username, String password) {
        this.username = username;
        this.password = password;

        client.initialize(username, password);
    }

    public void connect(World world, APIKey apiKey, float x, float y, float z) {
        this.apiKey = apiKey;
        offsetX = x;
        offsetY = y;
        offsetZ = z;

        client.connect(apiKey.getKey(), world, offsetX, offsetY, offsetZ);
    }
}