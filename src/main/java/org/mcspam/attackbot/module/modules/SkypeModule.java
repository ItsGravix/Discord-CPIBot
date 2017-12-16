package org.mcspam.attackbot.module.modules;

import com.operabot.listeners.*;
import com.operabot.module.AbstractModule;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.handler.ErrorHandler;
import com.samczsun.skype4j.internal.LiveLoginHelper;
import com.samczsun.skype4j.internal.client.MSFTSkypeClient;
import org.apache.log4j.Logger;

public class SkypeModule extends AbstractModule {

    private Logger skype4jLogger;
    private ErrorHandler errorHandler;

    private static MSFTSkypeClient skype;

    public SkypeModule(String email, String password) {
        super("Skype");

        try {
            String skypeToken = LiveLoginHelper.getSkypeToken(email, password);
            String skypeId = LiveLoginHelper.getSkypeID(email, password);
            skype = (MSFTSkypeClient) new MSFTSkypeClient.Builder(skypeToken, skypeId)
                    .withLogger(skype4jLogger)
                    .withExceptionHandler(errorHandler)
                    .withAllResources().build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        try {
            format("SYSTEM", "Entering to the skype profile...");
            skype.login();
            format("SYSTEM", "Skype profile has been loaded! (" + getMSUsed() + " ms)");
        } catch (Exception e) {
            format("ERROR", "Could initialize the skype profile...");
            format("ERROR", e.getMessage());
            System.exit(0);
        }
        try {
            format("SYSTEM", "Setting skype profile...");
            skype.subscribe();
            skype.setVisibility(Visibility.ONLINE);
            format("SYSTEM", "Skype profile is ready! (" + getMSUsed() + " ms)");
        } catch (Exception e) {
            format("ERROR", "Could init skype profile...");
            format("ERROR", e.getMessage());
            System.exit(0);
        }
        format("SYSTEM", "Registering events...");
        registerEvents(skype);
        format("SYSTEM", "Events registered in " + getMSUsed() + " ms");
    }

    private void registerEvents(Skype skype) {
        skype.getEventDispatcher().registerListener(new gotMessage());
        skype.getEventDispatcher().registerListener(new gotCalled());
        skype.getEventDispatcher().registerListener(new gotContactRequest());
        skype.getEventDispatcher().registerListener(new onGroupUserAdd());
        skype.getEventDispatcher().registerListener(new onGroupMultiUserAdd());
    }

    public static Skype getSkype() {
        return skype;
    }
}
