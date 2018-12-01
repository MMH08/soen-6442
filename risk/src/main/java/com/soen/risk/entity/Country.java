package com.soen.risk.entity;

import java.io.Serializable;
import java.util.Observable;

/**
 * <h2>Country Class</h2>
 * <p>This class contains all details of a country i.e. Number of armies of countries, name, x and y axis, id.</p>
 *
 * @author Amit Sachdeva
 * @version 1.0.2
 * @since 2018-10-30
 */
public class Country extends Observable implements Serializable {
    
    /** The name. */
    private String name;
    
    /** The coordinate X. */
    private String coordinateX;
    
    /** The coordinate Y. */
    private String coordinateY;
    
    /** The army. */
    private int army = 0;
    
    /** The id. */
    private int id;

    // -------------------------------------------------------------
    /**
     * Instantiates a new country.
     *
     * @param id the id
     * @param name the name
     */
    /*
     * Constructor of Country with Country id and name
     * @param id-ID of a country
     * @param name-Name of a country
     */
    public Country(int id, String name) {
        this.setName(name);
        this.setId(id);
    }

    /**
     * Adds the army.
     *
     * @param army the army
     */
    public void addArmy(int army) {
        setArmy(this.army + army);
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    // -------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the army.
     *
     * @return the army
     */
    public int getArmy() {
        return army;
    }

    /**
     * Sets the army.
     *
     * @param army the new army
     */
    public void setArmy(int army) {
        this.army = army;
        this.setChanged();
        this.notifyObservers(this);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the coordinate X.
     *
     * @return the coordinate X
     */
    public String getCoordinateX() {
        return coordinateX;
    }

    /**
     * Sets the coordinate X.
     *
     * @param coordinateX the new coordinate X
     */
    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Gets the coordinate Y.
     *
     * @return the coordinate Y
     */
    public String getCoordinateY() {
        return coordinateY;
    }

    /**
     * Sets the coordinate Y.
     *
     * @param coordinateY the new coordinate Y
     */
    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }


    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return this.army == 0;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }
}
