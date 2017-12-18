package us.gravitycube.cpibot.module.modules;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import us.gravitycube.cpibot.module.AbstractModule;

import java.io.File;
import java.io.IOException;

public class LoggerModule extends AbstractModule {

    public LoggerModule() {
        super("Logger");
    }

    private static Logger logger;

    @Override
    public void onEnable() {
        saveOldLog();
        setLogger();
    }

    private void setLogger() {
        try {
            logger = Logger.getLogger("OperaBot");
            logger.addAppender(new ConsoleAppender(new PatternLayout("%d [%p] %c{1} - %m%n")));
            logger.addAppender(new FileAppender(new PatternLayout("%d [%p] %c{1} - %m%n"), "log.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveOldLog() {
        File logFile = new File("log.log");

        if (logFile.exists()) {
            File file = new File("logs" + File.separator);
            file.mkdirs();
            logFile.renameTo(new File("logs" + File.separator + "log-" + System.currentTimeMillis()));
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
