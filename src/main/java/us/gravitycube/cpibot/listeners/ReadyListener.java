package us.gravitycube.cpibot.listeners;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import us.gravitycube.cpibot.utils.LogUtils;

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
