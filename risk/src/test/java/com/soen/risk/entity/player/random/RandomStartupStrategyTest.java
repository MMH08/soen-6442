package com.soen.risk.entity.player.random;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.StartupStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The Class RandomStartupStrategyTest.
 *
 * @author varun
 */
public class RandomStartupStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The random.
     */
    private StartupStrategy random;

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
        random = new RandomStartupStrategy();
        country1 = map.findByCountryName("country1");
        country4 = map.findByCountryName("country4");
    }

    /**
     * Zero initial army should add one army.
     */
    @Test
    public void ZeroInitialArmy_ShouldAddOneArmy() {
        country1.setArmy(0);
        random.execute(country1, 10);
        assertEquals(1, country1.getArmy());
    }

    /**
     * With initial army should add one army.
     */
    @Test
    public void WithInitialArmy_ShouldAddOneArmy() {
        int expectedCount = country1.getArmy() + 1;
        random.execute(country1, 11);
        assertEquals(expectedCount, country1.getArmy());
    }
}
