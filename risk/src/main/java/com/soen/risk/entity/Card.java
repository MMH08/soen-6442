package com.soen.risk.entity;

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
