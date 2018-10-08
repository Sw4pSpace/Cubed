package net.glowstone.io.persistence;

public enum Table {
    WORLD("world"),
    OPS("ops"),
    WHITELIST("whitelist"),
    BANNED("banned");


    private String tablename;

    Table(String tablename) {
        this.tablename = tablename;
    }

    public String getName() {
        return tablename;
    }
}
