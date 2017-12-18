package us.gravitycube.cpibot.utils;

import me.legit.cpi.util.BotHelper;

import java.io.IOException;

/**
 * Created by origi on 12/9/2017.
 */
public class CPIUtils {
    public static String generateAPIKey() {
        try {
            return BotHelper.requestAPIKey();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
