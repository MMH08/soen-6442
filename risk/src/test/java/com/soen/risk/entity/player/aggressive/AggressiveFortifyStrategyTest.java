/**
 * 
 */
package com.soen.risk.entity.player.aggressive;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.*;
import org.junit.Assert.*;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.entity.player.human.HumanFortifyStrategy;
import com.soen.risk.interactor.GamePlay;

/**
 * @author fly
 *
 */
//Need to rewrite 
public class AggressiveFortifyStrategyTest {
	Map map;
	public AggressiveFortifyStrategy aggfortifyStrategy;
	ArrayList<Country> allowedCountries;
	Path  parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
    String relativePath = FileSystems.getDefault().getSeparator() + "fixture" + FileSystems.getDefault().getSeparator() + "createnew.map";
    static  GamePlay gamePlay;
    static Game game;
    static Player player;
    ArrayList players=new ArrayList<>();
    
    
	
	@Before
	public void setUp(){
			map=new Map();
			player=new Player("Player1","AGGRESSIVE");
			players.add(player);
			game=new Game(map, players);
			aggfortifyStrategy=new AggressiveFortifyStrategy();
			map.load(parentPath+relativePath);
			map.findByCountryName("Country1").setArmy(10);
			map.findByCountryName("Country2").setArmy(20);
			map.findByCountryName("Country3").setArmy(7);
			map.findByCountryName("Country4").setArmy(13);
			allowedCountries=new ArrayList();
			allowedCountries.add(map.findByCountryName("Country2"));
			
			ArrayList tempList=new ArrayList();
			tempList.add(map.findByCountryName("Country3"));
			tempList.add(map.findByCountryName("Country4"));
			map.setCountries(tempList);
			game.setMap(map);
			gamePlay.setInstance(gamePlay);
			
	}
	
	@Test
	public void executeTest(){
		/*aggfortifyStrategy.execute(map, allowedCountries);
		
		Assert.assertEquals(25, map.findByCountryName("Country2").getArmy());*/
	}
	
	
}
