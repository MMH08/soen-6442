package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CheaterFortifyStrategyTest {

	private Map map;
	private FortifyStrategy cheater;
	private Country country1;
	private Country country4;
	private Country country2;
	private Country country3;

	@Before
	public void setUp() {
		map = new Map();
		map.load("./fixture/demo.map");
		cheater = new CheaterFortifyStrategy();
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
	public void ZeroCountry_ShouldChangeNothing() {
		int[] expectedCounts = new int[]{10, 20, 40, 30};
		cheater.execute(map, new ArrayList<>());
		assertArrayEquals(expectedCounts, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
	}

	@Test
	public void OneCountrySurroundedByOpponent_ShouldDouble(){
		cheater.execute(map, Collections.singletonList(country2));
		assertEquals(40, country2.getArmy());
	}

	@Test
	public void TwoCountryWithOneSurroundedByOpponent_ShouldDouble(){
		cheater.execute(map, Arrays.asList(country1, country2));
		assertEquals(20, country1.getArmy());
		assertEquals(20, country2.getArmy());

	}
}
