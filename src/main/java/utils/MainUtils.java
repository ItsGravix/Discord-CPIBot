package utils;

public class MainUtils {

    private boolean debug = true;

    public void debug(String format, String debug) {
        if(this.debug){
            LoggerModule.getLogger().log(Priority.INFO, "[DEBUG] [" + format + "] " + debug);
        }
    }

    public String log(String text) {
        String string = text;
        LoggerModule.getLogger().log(Priority.INFO, string);
        return string;
    }

    public String format(String format, String text) {
        return log("[" + format + "] " + text);
    }

}
