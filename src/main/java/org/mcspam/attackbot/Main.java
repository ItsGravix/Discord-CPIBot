package org.mcspam.attackbot;

/**
 * Created by origi on 12/16/2017.
 */
public class Main {

    private static Main i;

    public Main() {
        i = this;
    }

    public static void main(String[] args) {
        new Main();
    }

    public static Main getI() { return i; }
}
