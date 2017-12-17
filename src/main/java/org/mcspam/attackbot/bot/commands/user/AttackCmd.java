package org.mcspam.attackbot.bot.commands.user;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import org.mcspam.attackbot.bot.commands.BotCommand;
import org.mcspam.attackbot.bot.ranks.Rank;
import org.mcspam.attackbot.main.BotUser;
import org.mcspam.attackbot.main.mcspam.MCSpamUser;
import org.mcspam.attackbot.utils.LogUtils;
import org.mcspam.attackbot.utils.StringUtils;

public class AttackCmd extends BotCommand {

    public AttackCmd() {
        super("attack", new String[]{}, "Bot attacks a Minecraft server with MCSpam", Rank.OWNER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        if (args.length == 1) {
            // Start Attack
            user.getMcSpamUser().sendAttack("45.35.84.102", "25550", "5", "340");
        } else {
            channel.sendMessage(getUsageFormatted());
        }
    }

    @Override
    public void execute(String[] args) {
        log("This command can't be executed by Console!");
    }
}
