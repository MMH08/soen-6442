package com.soen.risk.entity;

/**
 *
 */
public class Country {
	private String name;
	private String coordinateX;
	private String coordinateY;
	private int army;

	/**
	 *
	 *
	 * @param name
	 */
	// -------------------------------------------------------------
	public Country(String name) {
		this.setName(name);
	}
	
	// -------------------------------------------------------------

	public int getArmy() {
		return army;
	}

	public void setArmy(int army) {
		this.army = army;
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


}
