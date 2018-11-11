package com.soen.risk.entity;
/**
 * <h3>Enum for Phase Names</h3>
 * @author Hina
 * @since 05-11-2018
 * @@version 1.0.2
 *
 */
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
