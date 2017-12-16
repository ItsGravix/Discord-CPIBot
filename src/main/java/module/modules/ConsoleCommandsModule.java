package module.modules;

import com.operabot.module.AbstractModule;

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
                    format("ConsoleCommands", "Writed: " + next);
                    CommandsModule.getCommandManager().runCommand(command);
                }
            } catch (Exception e) {
                format("ERROR", e.getMessage());
            }
        }).start();
    }
}
