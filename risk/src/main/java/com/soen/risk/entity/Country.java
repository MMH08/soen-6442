package com.soen.risk.entity;

import java.util.Observable;

/**
 * <h2>Country Class</h2>
 * <p>This class contains all details of a country i.e. Number of armies of countries, name, x and y axis, id.</p>
 *
 * @author Amit Sachdeva
 * @version 1.0.2
 * @since 2018-10-30
 */
public class Country extends Observable {
    private String name;
    private String coordinateX;
    private String coordinateY;
    private int army = 0;
    private int id;

    // -------------------------------------------------------------
    /*
     * Constructor of Country with Country id and name
     * @param id-ID of a country
     * @param name-Name of a country
     */
    public Country(int id, String name) {
        this.setName(name);
        this.setId(id);
    }

    public void addArmy(int army) {
        setArmy(this.army + army);
    }

    // -------------------------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getArmy() {
        return army;
    }

    public void setArmy(int army) {
        this.army = army;
        this.setChanged();
        this.notifyObservers(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }


    public boolean isEmpty() {
        return this.army == 0;
    }
}
