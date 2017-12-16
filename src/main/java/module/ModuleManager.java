package module;

import com.operabot.module.modules.CommandsModule;
import com.operabot.module.modules.ConsoleCommandsModule;
import com.operabot.module.modules.LoggerModule;
import com.operabot.module.modules.SkypeModule;
import com.operabot.utils.MainUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager extends MainUtils {

    private List<AbstractModule> modules = new ArrayList<>();
    private long totalTime;

    private static LoggerModule loggerModule;
    private static SkypeModule skypeModule;
    private static CommandsModule commandsModule;
    private static ConsoleCommandsModule consoleCommandsModule;

    public void start() {
        totalTime = System.currentTimeMillis();
        registerModule(loggerModule = new LoggerModule());
        registerModule(skypeModule = new SkypeModule("operabotbeta@gmail.com", "fjNDJS8231J"));
        registerModule(commandsModule = new CommandsModule());
        registerModule(consoleCommandsModule = new ConsoleCommandsModule());
        format("SYSTEM", "Done (" + (System.currentTimeMillis() - totalTime) + " ms) For help, type '" + "help'");
    }

    private void registerModule(AbstractModule module) {
        modules.add(module);
        module.onEnable();
    }

    public static LoggerModule getLoggerModule() {
        return loggerModule;
    }

    public static SkypeModule getSkypeModule() {
        return skypeModule;
    }

    public static CommandsModule getCommandsModule() {
        return commandsModule;
    }

    public static ConsoleCommandsModule getConsoleCommandsModule() {
        return consoleCommandsModule;
    }
}
