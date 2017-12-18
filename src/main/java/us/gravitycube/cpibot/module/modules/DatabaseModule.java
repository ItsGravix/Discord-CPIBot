package us.gravitycube.cpibot.module.modules;

import us.gravitycube.cpibot.database.Database;
import us.gravitycube.cpibot.module.AbstractModule;

public class DatabaseModule extends AbstractModule {

    public DatabaseModule() {
        super("Database");
    }

    private static Database botDatabase;

    @Override
    public void onEnable() {
        connectAll();
    }

    public void connectAll() {
        botDatabase = new Database(
                ConfigModule.getProperty("BOT_DATABASE_IP"),
                ConfigModule.getProperty("BOT_DATABASE_PORT"),
                ConfigModule.getProperty("BOT_DATABASE_USER"),
                ConfigModule.getProperty("BOT_DATABASE_PASSWORD"),
                ConfigModule.getProperty("BOT_DATABASE_NAME"));
        if (!botDatabase.connect()) { System.exit(1); }
    }

    public static Database getBotDatabase() {
        return botDatabase;
    }
}
