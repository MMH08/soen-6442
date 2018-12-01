package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.ReinforceStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


/**
 * The Class CheaterReinforceStrategyTest.
 *
 * @author Hina
 */
public class CheaterReinforceStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The cheater.
     */
    private ReinforceStrategy cheater;

    /**
     * The country 1.
     */
    private Country country1;

    /**
     * The country 4.
     */
    private Country country4;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        cheater = new CheaterReinforceStrategy();
        country1 = map.findByCountryName("country1");
        country1.setArmy(10);
        country4 = map.findByCountryName("country4");
        country4.setArmy(20);
    }

    /**
     * Zero country should change nothing.
     */
    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        int expectedCount = country1.getArmy();
        cheater.execute(map, new ArrayList<>());
        assertEquals(expectedCount, country1.getArmy());
    }

    /**
     * One country should double army count.
     */
    @Test
    public void OneCountry_ShouldDoubleArmyCount() {
        int expectedCount = country1.getArmy() * 2;
        cheater.execute(map, Collections.singletonList(country1));
        assertEquals(expectedCount, country1.getArmy());
    }

    /**
     * Two disconnected countries should double army count.
     */
    @Test
    public void TwoDisconnectedCountries_ShouldDoubleArmyCount() {
        int expectedCount1 = country1.getArmy() * 2;
        int expectedCount2 = country4.getArmy() * 2;
        cheater.execute(map, Arrays.asList(country1, country4));
        assertEquals(expectedCount1, country1.getArmy());
        assertEquals(expectedCount2, country4.getArmy());
    }
}
