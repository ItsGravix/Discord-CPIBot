package us.gravitycube.cpibot.module.modules;

import us.gravitycube.cpibot.module.AbstractModule;
import us.gravitycube.cpibot.utils.LogUtils;

import java.util.Scanner;

public class ConsoleCommandsModule extends AbstractModule {

    public ConsoleCommandsModule() {
        super("ConsoleCommands");
    }


    @Override
    public void onEnable() {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            String next;
            try {
                while ((next = scanner.nextLine()) != null) {
                    String[] command = next.trim().split(" ");
                    LogUtils.format("ConsoleCommands", "Writed: " + next);
                    CommandsModule.getCommandManager().runCommand(command);
                }
            } catch (Exception e) {
                LogUtils.format("ERROR", e.getMessage());
            }
        }).start();
    }
}
