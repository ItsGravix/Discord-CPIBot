package us.gravitycube.cpibot.module.modules;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import us.gravitycube.cpibot.listeners.MessageListener;
import us.gravitycube.cpibot.listeners.ReadyListener;
import us.gravitycube.cpibot.module.AbstractModule;

public class DiscordModule extends AbstractModule {
    private static JDA jda;
    private static String botToken;

    public DiscordModule(String token) {
        super("Discord");

        botToken = token;
    }

    @Override
    public void onEnable() {
        try {
            format("SYSTEM", "Connecting Discord Bot...");

            JDABuilder builder = new JDABuilder(AccountType.BOT)
                    .setToken(botToken)
                    .setAutoReconnect(true);

            jda = builder.buildBlocking();

            format("SYSTEM", "Bot has been connected! (" + getMSUsed() + " ms)");
        } catch (Exception e) {
            format("ERROR", "Could not connect Bot...");
            format("ERROR", e.getMessage());
            System.exit(0);
        }

        try {
            format("SYSTEM", "Setting Bot's profile...");
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.getPresence().setGame(Game.playing("cpibotting.org | !help"));
            format("SYSTEM", "Bot's profile has been set! (" + getMSUsed() + " ms)");
        } catch (Exception e) {
            format("ERROR", "Could not init Bot's profile...");
            format("ERROR", e.getMessage());
            System.exit(0);
        }

        format("SYSTEM", "Registering events...");
        registerEvents(jda);
        format("SYSTEM", "Events registered! (" + getMSUsed() + " ms)");
    }

    private void registerEvents(JDA jda) {
        jda.addEventListener(new MessageListener());
        jda.addEventListener(new ReadyListener());
    }

    public static JDA getJda() {
        return jda;
    }
}
