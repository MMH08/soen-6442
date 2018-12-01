package com.soen.risk.entity.player.aggressive;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.StartupStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AggressiveStartupStrategyTest {
	private Map map;
	private StartupStrategy aggressive;
	private Country country1;
	private Country country4;
	private Country country2;
	private Country country3;

	@Before
	public void setUp() {
		map = new Map();
		map.load("./fixture/demo.map");
		aggressive = new AggressiveStartupStrategy();
		country1 = map.findByCountryName("Country1");
		country1.setArmy(10);
		country2 = map.findByCountryName("Country2");
		country2.setArmy(20);
		country3 = map.findByCountryName("Country3");
		country3.setArmy(40);
		country4 = map.findByCountryName("Country4");
		country4.setArmy(30);

	}

	@Test
	public void ZeroInitialArmy_ShouldAddOneArmy() {
		int expectedCount = country1.getArmy() + 1;
		aggressive.execute(country1, 10);
		assertEquals(expectedCount, country1.getArmy());
	}

	@Test
	public void WithInitialArmy_ShouldAddOneArmy() {
		country1.setArmy(2);
		int expectedCount = country1.getArmy() + 1;
		aggressive.execute(country1, 3);
		assertEquals(expectedCount, country1.getArmy());
	}

}
