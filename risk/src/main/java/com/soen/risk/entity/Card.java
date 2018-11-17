package com.soen.risk.entity;

/*
 * Card Names that can be used for CardExchangeView
 * @author Nivetha
 * @since 07-11-2018
 * @version 1.0.2
 */

public enum Card {
    INFANT("Infant"), CAVALRY("Cavalry"), ARTILLERY("Artillery");
    private final String name;

    private Card(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
