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


public class AggressiveFortifyStrategyTest {

    private Map map;
    private FortifyStrategy aggressive;
    private Country country1;
    private Country country4;
    private Country country2;
    private Country country3;

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

    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        int[] expectedCounts = new int[]{10, 20, 10, 30};
        aggressive.execute(map, new ArrayList<>());
        assertArrayEquals(expectedCounts, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
    }

    @Test
    public void OneCountry_ShouldDoNothing() {
        aggressive.execute(map, Collections.singletonList(country2));
        assertEquals(20, country2.getArmy());
    }

    @Test
    public void TwoCountryWithOneStrong_ShouldFortify() {
        aggressive.execute(map, Arrays.asList(country1, country2));
        assertEquals(1, country1.getArmy());
        assertEquals(29, country2.getArmy());
    }


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


    @Test
    public void ThreeCountryWithOneStrong_ShouldFortifySecondBest() {
        aggressive.execute(map, Arrays.asList(country1, country2, country4));
        assertEquals(30, country4.getArmy());
        assertEquals(29, country2.getArmy());
        assertEquals(1, country1.getArmy());
    }

}
