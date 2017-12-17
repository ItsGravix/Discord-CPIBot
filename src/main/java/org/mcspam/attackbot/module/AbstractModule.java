package org.mcspam.attackbot.module;

import org.mcspam.attackbot.utils.LogUtils;

public abstract class AbstractModule extends LogUtils {

    private String moduleName;
    public long currentTime;

    public AbstractModule(String moduleName) {
        this.moduleName = moduleName;
        currentTime = System.currentTimeMillis();
    }

    public long getMSUsed() {
        long tempTime = currentTime;
        currentTime = System.currentTimeMillis();
        return (System.currentTimeMillis() - tempTime);
    }

    public abstract void onEnable();

    public String getModuleName() {
        return moduleName;
    }
}
