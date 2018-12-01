package com.soen.risk.entity.player.human;

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
 * The Class HumanReinforceStrategyTest.
 *
 * @author Amit
 */
public class HumanReinforceStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The human.
     */
    private ReinforceStrategy human;

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
     * Zero country should change nothing.
     */
    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        human = new HumanReinforceStrategy(new ArrayList<>());
        human.execute(map, new ArrayList<>());
        assertEquals(10, country1.getArmy());
    }


    /**
     * One country should add reinforce army.
     */
    @Test
    public void OneCountry_ShouldAddReinforceArmy() {
        human = new HumanReinforceStrategy(Collections.singletonList(10));
        human.execute(map, Collections.singletonList(country1));
        assertEquals(10 + 10, country1.getArmy());
    }

    /**
     * Two country should add reinforce army to both.
     */
    @Test
    public void TwoCountry_ShouldAddReinforceArmyToBoth() {
        human = new HumanReinforceStrategy(Arrays.asList(10, 20));
        human.execute(map, Arrays.asList(country1, country2));
        assertEquals(20, country1.getArmy());
        assertEquals(40, country2.getArmy());
    }

}
