package com.soen.risk.entity;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class GameTest {

    @Test
    public void dropPlayer() 
    {
    	
    }

    @Test
    public void isNotOver() {
    }

    @Test
    public void assignArmy() {
    }

    @Test
    public void allocateInitialCountries() {
    }

    @Test
    public void allocateInitialArmy() 
    {
    }

    @Test
    public void getMap() {
    }

    @Test
    public void setMap() {
    }

    @Test
    public void getPlayers() 
    {
    	ArrayList<Player> playerList=new ArrayList<Player>();
    	Map map=new Map();
    	Game game=new Game(map,1);
    	Player player=new Player(1);
    	player.setName("manmeet");
    	player.setArmyCapacity(2);
    	playerList.add(player);
    	game.setPlayers(playerList);
    	assertNotNull(game.getPlayers());
    	
    }

   /* @Test
    public void setPlayers() 
    {
    
    	
    }
*/
    
}