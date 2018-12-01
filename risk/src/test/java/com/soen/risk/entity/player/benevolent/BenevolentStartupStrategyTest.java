package com.soen.risk.entity.player.benevolent;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.StartupStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The Class BenevolentStartupStrategyTest.
 *
 * @author Nivetha
 */
public class BenevolentStartupStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The benevolent.
     */
    private StartupStrategy benevolent;

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
     * Set up.
     */
    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        benevolent = new BenevolentStartupStrategy();
        country1 = map.findByCountryName("country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("country3");
        country3.setArmy(40);
        country4 = map.findByCountryName("country4");
        country4.setArmy(30);

    }

    /**
     * Zero initial army should add one army.
     */
    @Test
    public void ZeroInitialArmy_ShouldAddOneArmy() {
        country1.setArmy(0);
        int expectedCount = country1.getArmy() + 1;
        benevolent.execute(country1, 10);
        assertEquals(expectedCount, country1.getArmy());
    }

    /**
     * With initial army should add one army.
     */
    @Test
    public void WithInitialArmy_ShouldAddOneArmy() {
        int expectedCount = country1.getArmy() + 1;
        benevolent.execute(country1, 20);
        assertEquals(expectedCount, country1.getArmy());
    }

}
