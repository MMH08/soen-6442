package com.soen.risk.entity.player.random;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * The Class RandomAttackStrategyTest.
 *
 * @author varun
 */
public class RandomAttackStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The random.
     */
    private RandomAttackStrategy random;

    /**
     * The country 1.
     */
    private Country country1;

    /**
     * The country 4.
     */
    private Country country4;

    /**
     * The country 2.
     */
    private Country country2;

    /**
     * The country 3.
     */
    private Country country3;

    /**
     * The expected loss.
     */
    private HashMap<Country, Country> expectedLoss;

    /**
     * The expected won.
     */
    private List<Country> expectedWon;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        random = new RandomAttackStrategy();
        country1 = map.findByCountryName("country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("country3");
        country3.setArmy(10);
        country4 = map.findByCountryName("country4");
        country4.setArmy(30);
        expectedLoss = new HashMap<>();
        expectedWon = new ArrayList<>();
    }

    /**
     * Zero country should change nothing.
     */
    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        random.execute(map, new ArrayList<>());
        assertEquals(new ArrayList(), random.getWon());
        assertEquals(new HashMap<>(), random.getLost());
        assertEquals(0, random.getAttackCounter());
    }

    /**
     * One country should attack one opponent.
     */
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
