package com.soen.risk.entity.player.cheater;


import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.StartupStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheaterStartupStrategyTest {
    private Map map;
    private StartupStrategy cheater;
    private Country country1;
    private Country country4;

    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        cheater = new CheaterStartupStrategy();
        country1 = map.findByCountryName("country1");
        country4 = map.findByCountryName("country4");
    }

    @Test
    public void ZeroInitialArmy_ShouldAddOneArmy() {
        country1.setArmy(0);
        cheater.execute(country1, 10);
        assertEquals(1, country1.getArmy());
    }

    @Test
    public void WithInitialArmy_ShouldAddOneArmy() {
        int expectedCount = country1.getArmy() + 1;
        cheater.execute(country1, 11);
        assertEquals(expectedCount, country1.getArmy());
    }

}
