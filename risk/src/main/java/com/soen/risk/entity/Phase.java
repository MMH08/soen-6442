package com.soen.risk.entity;

public enum Phase {
    STARTUP("Startup Phase"), REINFORCE("Reinforce Phase"), ATTACK("Attack Phase"), FORTIFY("Foritification Phase");
    private final String name;

    private Phase(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
