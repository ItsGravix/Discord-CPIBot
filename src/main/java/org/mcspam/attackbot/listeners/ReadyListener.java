package org.mcspam.attackbot.listeners;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.mcspam.attackbot.utils.LogUtils;

/**
 * Created by origi on 12/12/2017.
 */
public class ReadyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event)
    {
        int count = event.getJDA().getGuilds().size();

        LogUtils.log("Bot running on " + count + " servers!");
    }
}
