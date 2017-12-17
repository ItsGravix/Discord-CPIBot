package org.mcspam.attackbot.main;

import net.dv8tion.jda.core.entities.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by origi on 12/17/2017.
 */
public class BotUserManager {
    private final static ConcurrentHashMap<User, BotUser> loadedUsers = new ConcurrentHashMap<>();

    public static BotUser getUser(User discordUser) {
        if (loadedUsers.containsKey(discordUser)) {
            return loadedUsers.get(discordUser);
        } else {
            loadedUsers.put(discordUser, new BotUser(discordUser));
            return loadedUsers.get(discordUser);
        }
    }
}
