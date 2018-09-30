package com.soen.risk.entity;

public class Country {
	private int id;
	private String name;
	private String coordinateX;
	private String coordinateY;
	private int army;
	
	// -------------------------------------------------------------
	public Country(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	// -------------------------------------------------------------

	public int getArmy() {
		return army;
	}

	public void setArmy(int army) {
		this.army = army;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
