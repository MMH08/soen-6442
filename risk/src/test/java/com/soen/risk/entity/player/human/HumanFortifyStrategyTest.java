package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * The Class HumanFortifyStrategyTest.
 *
 * @author Amit
 */
public class HumanFortifyStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The human.
     */
    private FortifyStrategy human;

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
     * Sets the up.
     */
    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        country1 = map.findByCountryName("country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("country3");
        country3.setArmy(10);
        country4 = map.findByCountryName("country4");
        country4.setArmy(30);
    }

    /**
     * Same country without army should change nothing.
     */
    @Test
    public void SameCountryWithoutArmy_ShouldChangeNothing() {
        int[] expectedCounts = new int[]{10, 20, 10, 30};
        human = new HumanFortifyStrategy(country1, country1, 0);
        human.execute(map, Collections.singletonList(country1));
        assertArrayEquals(expectedCounts, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
    }

    /**
     * Same country with army should do nothing.
     */
    @Test
    public void SameCountryWithArmy_ShouldDoNothing() {
        human = new HumanFortifyStrategy(country2, country2, 10);
        human.execute(map, Collections.singletonList(country2));
        assertEquals(20, country2.getArmy());
    }

    /**
     * Two country should fortify.
     */
    @Test
    public void TwoCountry_ShouldFortify() {
        human = new HumanFortifyStrategy(country2, country1, 4);
        human.execute(map, Arrays.asList(country1, country2));
        assertEquals(14, country1.getArmy());
        assertEquals(16, country2.getArmy());
    }

    /**
     * Two country with no connection should not fortify.
     */
    @Test
    public void TwoCountryWithNoConnection_ShouldNotFortify() {
        human = new HumanFortifyStrategy(country4, country1, 10);
        human.execute(map, Arrays.asList(country1, country4));
        assertEquals(10, country1.getArmy());
        assertEquals(30, country4.getArmy());
    }

    /**
     * Three country with connection should fortify.
     */
    @Test
    public void ThreeCountryWithConnection_ShouldFortify() {
        human = new HumanFortifyStrategy(country2, country3, 10);
        human.execute(map, Arrays.asList(country1, country2, country3));
        assertEquals(10, country1.getArmy());
        assertEquals(10, country2.getArmy());
        assertEquals(20, country3.getArmy());
    }


}
