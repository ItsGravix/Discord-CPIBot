package us.gravitycube.cpibot.bot.commands.user;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.commands.BotCommand;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.bot.session.sessions.AttackSession;
import us.gravitycube.cpibot.main.BotUser;

public class AttackCmd extends BotCommand {

    public AttackCmd() {
        super("attack", new String[]{}, "Bot attacks Club Penguin Island", Rank.OWNER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        if (args.length == 1) {
            // Start Attack
            user.getSessionManager().setActiveSession(new AttackSession(user));
        } else {
            channel.sendMessage(getUsageFormatted());
        }
    }

    @Override
    public void execute(String[] args) {
        log("This command can't be executed by Console!");
    }
}
