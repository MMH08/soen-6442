package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class RandomAttackStrategyTest {

    private Map map;
    private RandomAttackStrategy random;
    private Country country1;
    private Country country4;
    private Country country2;
    private Country country3;
    private HashMap<Country, Country> expectedLoss;
    private List<Country> expectedWon;

    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        random = new RandomAttackStrategy();
        country1 = map.findByCountryName("Country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("Country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("Country3");
        country3.setArmy(10);
        country4 = map.findByCountryName("Country4");
        country4.setArmy(30);
        expectedLoss = new HashMap<>();
        expectedWon = new ArrayList<>();
    }

    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        random.execute(map, new ArrayList<>());
        assertEquals(new ArrayList(), random.getWon());
        assertEquals(new HashMap<>(), random.getLost());
        assertEquals(0, random.getAttackCounter());
    }

    @Test
    public void OneCountry_ShouldAttackOneOpponent() {
        expectedLoss.put(country2, country1);
        expectedWon.add(country1);
        random.execute(map, Collections.singletonList(country2));

        if (random.getWon().isEmpty() && random.getLost().isEmpty()) {
            assertNotEquals(0, random.getAttackCounter());
        } else if (!random.getWon().isEmpty()) {
            assertEquals(expectedWon, random.getWon());
        } else if (!random.getLost().isEmpty()) {
            assertEquals(expectedLoss, random.getLost());
        }
    }
}
