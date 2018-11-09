package com.soen.risk.entity;

/*
 * Card Names that can be used for CardExchangeView
 * @author Nivedha
 * @since 07-11-2018
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
