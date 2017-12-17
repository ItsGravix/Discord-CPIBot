package org.mcspam.attackbot.module.modules;

import org.mcspam.attackbot.Main;
import org.mcspam.attackbot.module.AbstractModule;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by origi on 12/16/2017.
 */

// I'll make it better later lol -Gravix
public class ConfigModule extends AbstractModule {
    private static File configFile = new File("config.properties");

    public ConfigModule() {
        super("BotConfig");
    }

    @Override
    public void onEnable() {
        if(!configFile.exists()) {
            // First time setup
            format("SYSTEM", "First time setup, creating config and shutting down...");

            try {
                configFile.createNewFile();
                Properties properties = new Properties();
                FileWriter fileWriter = new FileWriter(configFile);

                // Discord
                properties.setProperty("DISCORD_TOKEN", "");

                // Database
                properties.setProperty("BOT_DATABASE_IP", "");
                properties.setProperty("BOT_DATABASE_PORT", "");
                properties.setProperty("BOT_DATABASE_USER", "");
                properties.setProperty("BOT_DATABASE_PASSWORD", "");
                properties.setProperty("BOT_DATABASE_NAME", "");

                properties.store(fileWriter, "Discord Bot Settings");
                fileWriter.close();

                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        try {
            FileReader reader = new FileReader(configFile);
            Properties properties = new Properties();
            properties.load(reader);

            // Read value
            String value = properties.getProperty(key);
            reader.close();

            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
