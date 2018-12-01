package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.FortifyStrategy;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * The Class AggressiveFortifyStrategyTest.
 *
 * @author Manmeet
 */
public class AggressiveFortifyStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The aggressive.
     */
    private FortifyStrategy aggressive;

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
        aggressive = new AggressiveFortifyStrategy();
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
     * Zero country should change nothing.
     */
    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        int[] expectedCounts = new int[]{10, 20, 10, 30};
        aggressive.execute(map, new ArrayList<>());
        assertArrayEquals(expectedCounts, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
    }

    /**
     * One country should do nothing.
     */
    @Test
    public void OneCountry_ShouldDoNothing() {
        aggressive.execute(map, Collections.singletonList(country2));
        assertEquals(20, country2.getArmy());
    }

    /**
     * Two country with one strong should fortify.
     */
    @Test
    public void TwoCountryWithOneStrong_ShouldFortify() {
        aggressive.execute(map, Arrays.asList(country1, country2));
        assertEquals(1, country1.getArmy());
        assertEquals(29, country2.getArmy());
    }


    /**
     * Two country with one strong no connection should not fortify.
     */
    @Test
    public void TwoCountryWithOneStrongNoConnection_ShouldNotFortify() {
        aggressive.execute(map, Arrays.asList(country1, country4));
        assertEquals(10, country1.getArmy());
        assertEquals(30, country4.getArmy());
    }

    /**
     * Three countries with one strong should fortify one strong, depends on sort which ever comes first.
     */
    @Test
    public void ThreeCountryWithOneStrong_ShouldFortifyOneStrong() {
        aggressive.execute(map, Arrays.asList(country1, country2, country3));
        assertEquals(29, country2.getArmy());
        assertTrue((1 == country3.getArmy() && 10 == country1.getArmy()) ||
                (1 == country1.getArmy() && 10 == country3.getArmy()));
    }


    /**
     * Three country with one strong should fortify second best.
     */
    @Test
    public void ThreeCountryWithOneStrong_ShouldFortifySecondBest() {
        aggressive.execute(map, Arrays.asList(country1, country2, country4));
        assertEquals(30, country4.getArmy());
        assertEquals(29, country2.getArmy());
        assertEquals(1, country1.getArmy());
    }

}
