package com.soen.risk.entity.player.benevolent;


import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.StartupStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class BenevolentAttackStrategyTest {
	private Map map;
	private AttackStrategy benevolent;
	private Country country1;
	private Country country4;
	private Country country2;
	private Country country3;

	@Before
	public void setUp() {
		map = new Map();
		map.load("./fixture/demo.map");
		benevolent = new BenevolentAttackStrategy();
		country1 = map.findByCountryName("country1");
		country1.setArmy(10);
		country2 = map.findByCountryName("country2");
		country2.setArmy(20);
		country3 = map.findByCountryName("country3");
		country3.setArmy(40);
		country4 = map.findByCountryName("country4");
		country4.setArmy(30);

	}

	@Test
	public void AttackPhase_ShouldDoNothing(){
		benevolent.execute(map, Arrays.asList(country1, country2));
		assertArrayEquals(new int[]{10, 20, 40, 30}, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
		assertEquals(new ArrayList<>(), benevolent.getWon());
		assertEquals(new HashMap<>(), benevolent.getLost());
	}


}
