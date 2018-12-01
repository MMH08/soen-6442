package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * The Class CheaterAttackStrategyTest.
 *
 * @author Hina, varun
 */
public class CheaterAttackStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The cheater.
     */
    private AttackStrategy cheater;

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
        cheater = new CheaterAttackStrategy();
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
     * Zero country should change nothing.
     */
    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        cheater.execute(map, new ArrayList<>());
        assertEquals(new ArrayList(), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

    /**
     * One country should capture neighbor.
     */
    @Test
    public void OneCountry_ShouldCaptureNeighbor() {
        cheater.execute(map, Collections.singletonList(country2));
        assertEquals(Collections.singletonList(country1), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

    /**
     * One country should capture all neighbor.
     */
    @Test
    public void OneCountry_ShouldCaptureAllNeighbor() {
        cheater.execute(map, Collections.singletonList(country1));
        assertEquals(Arrays.asList(country2, country3), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

}
