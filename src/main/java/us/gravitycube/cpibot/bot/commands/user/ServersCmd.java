package us.gravitycube.cpibot.bot.commands.user;

import me.legit.cpi.api.enums.World;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import us.gravitycube.cpibot.bot.commands.BotCommand;
import us.gravitycube.cpibot.bot.ranks.Rank;
import us.gravitycube.cpibot.main.BotUser;
import us.gravitycube.cpibot.utils.StringUtils;

public class ServersCmd extends BotCommand {

    public ServersCmd() {
        super("servers", new String[]{}, "Will PM you the CPI server list", "", Rank.USER);
    }

    @Override
    public void execute(String[] args, MessageChannel channel, BotUser user, Message inputMessage) {
        if (args.length >= 2) {
            StringBuilder serverList = new StringBuilder(StringUtils.join(args, 1, " "));

            for (World world : World.values()) {
                serverList.append(world.worldName() + "\n");
            }

            user.getDiscordUser().openPrivateChannel().queue((privateChannel) ->
                    privateChannel.sendMessage(serverList).queue());
        } else {
            channel.sendMessage(getUsageFormatted());
        }
    }

    @Override
    public void execute(String[] args) {
        log("This command can't be executed by Console!");
    }
}
