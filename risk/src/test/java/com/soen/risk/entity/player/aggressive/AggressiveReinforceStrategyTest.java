package com.soen.risk.entity.player.aggressive;

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
 * The Class AggressiveReinforceStrategyTest.
 *
 * @author Manmeet
 */
public class AggressiveReinforceStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The aggressive.
     */
    private ReinforceStrategy aggressive;

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
        aggressive = new AggressiveReinforceStrategy();
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
        aggressive.execute(map, new ArrayList<Country>());
        assertEquals(10, country1.getArmy());
    }


    /**
     * One country should add reinforce army.
     */
    @Test
    public void OneCountry_ShouldAddReinforceArmy() {
        int reinforceArmy = ReinforceStrategy.calculateArmyCount(map, Collections.singletonList(country1));
        aggressive.execute(map, Collections.singletonList(country1));
        assertEquals(10 + reinforceArmy, country1.getArmy());
    }

    /**
     * One strong country should add reinforce army to one.
     */
    @Test
    public void OneStrongCountry_ShouldAddReinforceArmyToOne() {
        int reinforceArmy = ReinforceStrategy.calculateArmyCount(map, Arrays.asList(country1, country2));
        aggressive.execute(map, Arrays.asList(country1, country2));
        assertEquals(20 + reinforceArmy, country2.getArmy());
    }

    /**
     * Two strong country should add reinforce army to one.
     */
    @Test
    public void TwoStrongCountry_ShouldAddReinforceArmyToOne() {
        int reinforceArmy = ReinforceStrategy.calculateArmyCount(map, Arrays.asList(country1, country3));
        aggressive.execute(map, Arrays.asList(country1, country3));
        assertEquals(10 + reinforceArmy, country1.getArmy());
    }

}
