package com.soen.risk.entity;

public enum Phase {
    STARTUP("startupPhase"), REINFORCE("reinforcePhase"), ATTACK("attackPhase"), FORTIFY("fortifyPhase");
    private final String name;

    private Phase(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
