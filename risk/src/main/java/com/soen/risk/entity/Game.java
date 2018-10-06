package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Game {
	private int countOfPlayers;
	private Map map;
	private List<Player> players;
	private String[] phases = { "reinforce", "attack", "fortify" };

	/**
	 * @param map
	 * @param countOfPlayers
	 */
	public Game(Map map, int countOfPlayers) {
		this.map = map;
		this.countOfPlayers = countOfPlayers;
		this.players = new ArrayList<>();
	}

	/**
	 * @param p
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	// -------------------------------------------------------------

	/**
	 * @return
	 */
	public boolean isNotOver() {
		//return (map.fetchOwners().size() == 1) ? false: true;	
		return false;
	}
	// -------------------------------------------------------------

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void assignArmy(Player p) {
		// TODO Auto-generated method stub
		
	}

    public String[] getPhases() {
        return phases;
    }

    public void setPhases(String[] phases) {
        this.phases = phases;
    }
}
