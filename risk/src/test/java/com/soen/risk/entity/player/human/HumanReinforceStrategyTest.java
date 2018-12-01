package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class HumanReinforceStrategyTest {
	private Map map;
	private ReinforceStrategy human;
	private Country country1;
	private Country country4;
	private Country country2;
	private Country country3;

	@Before
	public void setUp() {
		map = new Map();
		map.load("./fixture/demo.map");
		country1 = map.findByCountryName("Country1");
		country1.setArmy(10);
		country2 = map.findByCountryName("Country2");
		country2.setArmy(20);
		country3 = map.findByCountryName("Country3");
		country3.setArmy(10);
		country4 = map.findByCountryName("Country4");
		country4.setArmy(30);
	}

	@Test
	public void ZeroCountry_ShouldChangeNothing() {
		human = new HumanReinforceStrategy(new ArrayList<>());
		human.execute(map, new ArrayList<>());
		assertEquals(10, country1.getArmy());
	}


	@Test
	public void OneCountry_ShouldAddReinforceArmy() {
		human = new HumanReinforceStrategy(Collections.singletonList(10));
		human.execute(map, Collections.singletonList(country1));
		assertEquals(10 + 10, country1.getArmy());
	}

	@Test
	public void TwoCountry_ShouldAddReinforceArmyToBoth() {
		human = new HumanReinforceStrategy(Arrays.asList(10, 20));
		human.execute(map, Arrays.asList(country1, country2));
		assertEquals(20, country1.getArmy());
		assertEquals(40, country2.getArmy());
	}

}
