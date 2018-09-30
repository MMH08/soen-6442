package com.soen.risk.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private Map map;
	private List<Player> players;
	
	public Game(Map map) {
		this.map = map;
		this.players = new ArrayList<>();
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	// -------------------------------------------------------------
	
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
	

}
