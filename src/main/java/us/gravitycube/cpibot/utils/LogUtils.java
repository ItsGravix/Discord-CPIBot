package us.gravitycube.cpibot.utils;

import org.apache.log4j.Priority;
import us.gravitycube.cpibot.module.modules.LoggerModule;

public class LogUtils {

    private static boolean isDebugging = true;

    public static void debug(String format, String text) {
        if(isDebugging){
            LoggerModule.getLogger().log(Priority.INFO, "[DEBUG] [" + format + "] " + text);
        }
    }

    public static String log(String text) {
        String string = text;
        LoggerModule.getLogger().log(Priority.INFO, string);
        return string;
    }

    public static String format(String format, String text) {
        return log("[" + format + "] " + text);
    }
}
