package com.soen.risk.entity;

/*
 * Card Names that can be used for CardExchangeView
 * @author Nivetha
 * @since 07-11-2018
 * @version 1.0.2
 */

/**
 * The Enum Card.
 */
public enum Card {
    
    /** The infant. */
    INFANT("Infant"), 
 /** The cavalry. */
 CAVALRY("Cavalry"), 
 /** The artillery. */
 ARTILLERY("Artillery");
    
    /** The name. */
    private final String name;

    /**
     * Instantiates a new card.
     *
     * @param name the name
     */
    private Card(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.name;
    }
}
