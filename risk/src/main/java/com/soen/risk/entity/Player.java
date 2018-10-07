package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Player {
	private String name;
	private int armyCapacity;
	private List<Country> countries;

	/**
	 *
	 */
	// -------------------------------------------------------------
	public Player(int nameSuffix) {
		this.name = "Player_" + String.valueOf(nameSuffix);
		this.countries = new ArrayList<>();
	}

	/**
	 * @param c
	 */
	public void addCountry(Country c) {
		this.countries.add(c);
	}

	public List<Country> getCountries()
	{
		return this.countries;
	}

	/**
	 *
	 *
	 * @return
	 */
	public String nextCountryToAssignArmy(){
		return null;
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

}
