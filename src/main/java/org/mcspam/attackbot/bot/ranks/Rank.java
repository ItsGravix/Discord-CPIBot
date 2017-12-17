package org.mcspam.attackbot.bot.ranks;

public enum Rank {

    USER(0),
    HELPER(1),
    MODERATOR(2),
    ADMIN(3),
    OWNER(4);

    private int level;

    Rank(int level) {
        this.level = level;
    }

    public static Rank fromLevel(int level) {
        for (Rank rank : Rank.values()) {
            if (rank.getLevel() == level) {
                return rank;
            }
        }
        return null;
    }

    public int getLevel() {
        return level;
    }
}
