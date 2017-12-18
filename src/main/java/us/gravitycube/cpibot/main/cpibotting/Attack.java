package us.gravitycube.cpibot.main.cpibotting;

import me.legit.cpi.api.APIKey;
import me.legit.cpi.api.enums.World;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.main.FollowBot;
import us.gravitycube.cpibot.utils.CPIUtils;
import us.gravitycube.cpibot.utils.LogUtils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by origi on 12/16/2017.
 */
public class Attack {
    // Every Session has a set of Bots and API Keys
    private ArrayList<FollowBot> bots = new ArrayList<>();
    private ArrayList<APIKey> apiKeys = new ArrayList<>();

    public void connectAllBots(World world) {
        final int[] connected = {0};
        for (FollowBot bot : getDisconnectedBots()) {
            APIKey apiKey = getAvailableAPIKey();
            apiKey.addUsingKey();
            new Thread(() -> {
                Double doubleX = (ThreadLocalRandom.current().nextDouble(-10, 25));
                Double doubleZ = ThreadLocalRandom.current().nextDouble(-10, 25);

                bot.connect(world, apiKey, doubleX.floatValue(), 5f, doubleZ.floatValue());
                connected[0]++;

                LogUtils.debug("ATTACK", "Bots Connected: " + connected[0] + "/" + getDisconnectedBots().size());
            }).start();
        }
    }

    public boolean containsBot(FollowBot followBot) { return bots.contains(followBot); }

    public ArrayList<FollowBot> getAllBots() { return bots; }

    public void addBot(FollowBot followBot) { bots.add(followBot); }

    public ArrayList<FollowBot> getConnectedBots() {
        ArrayList<FollowBot> connectedBots = new ArrayList<>();
        for (FollowBot bot : bots) {
            if (bot.client.connected) connectedBots.add(bot);
        }
        return connectedBots;
    }

    public ArrayList<FollowBot> getDisconnectedBots() {
        ArrayList<FollowBot> disconnectedBots = new ArrayList<>();
        for (FollowBot bot : bots) {
            if (!bot.client.connected) disconnectedBots.add(bot);
        }
        return disconnectedBots;
    }

    public APIKey getAvailableAPIKey() {
        APIKey availableKey = null;

        if (apiKeys.size() == 0) { addApiKey(CPIUtils.generateAPIKey()); }

        for (APIKey apiKey : apiKeys) {
            if (apiKey.getUsingKey() < 11) {
                availableKey = apiKey;
                break;
            }
        }

        if (availableKey == null) {
            return addApiKey(CPIUtils.generateAPIKey());
        }

        return availableKey;
    }

    private APIKey addApiKey(String key) {
        APIKey apiKey = new APIKey(key);
        apiKeys.add(apiKey);
        return apiKey;
    }

    public ArrayList<APIKey> getApiKeys() {
        return apiKeys;
    }
}
