package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String name;
	private int armyCapacity;
	private List<Country> countries;
	
	// -------------------------------------------------------------
	public Player(String name) {
		this.setName(name);
		this.countries = new ArrayList<>();
	}
	
	public void addCountry(Country c) {
		this.countries.add(c);
	}
	
	// -------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArmyCapacity() {
		return armyCapacity;
	}

	public void setArmyCapacity(int armyCapacity) {
		this.armyCapacity = armyCapacity;
	}

//	public List<Country> getCountries() {
//		return countries;
//	}
//
//	public void setCountries(List<Country> countries) {
//		this.countries = countries;
//	}

}
