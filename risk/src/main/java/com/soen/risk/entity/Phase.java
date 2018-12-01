package com.soen.risk.entity;


/**
 * <h3>Enum for Phase Names</h3>.
 *
 * @author Hina
 * @since 05-11-2018
 * @@version 1.0.2
 */
public enum Phase {
    
    /** The startup. */
    STARTUP("startupPhase"), 
 /** The reinforce. */
 REINFORCE("reinforcePhase"), 
 /** The attack. */
 ATTACK("attackPhase"), 
 /** The fortify. */
 FORTIFY("fortifyPhase");
    
    /** The name. */
    private final String name;

    /**
     * Instantiates a new phase.
     *
     * @param name the name
     */
    private Phase(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.name;
    }

}
