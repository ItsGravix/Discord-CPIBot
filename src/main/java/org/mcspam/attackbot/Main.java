package org.mcspam.attackbot;

import org.mcspam.attackbot.module.ModuleManager;

/**
 * Created by origi on 12/16/2017.
 */
public class Main {

    private static Main i;
    private ModuleManager moduleManager;

    public Main() {
        i = this;

        moduleManager = new ModuleManager();
        moduleManager.start();
    }

    public static void main(String[] args) {
        new Main();
    }

    public static Main getI() { return i; }

}
