package com.soen.risk.entity.player.human;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


public class HumanAttackStrategyTest {
    private Map map;
    private HumanAttackStrategy human;
    private Country country1;
    private Country country4;
    private Country country2;
    private Country country3;
    private HashMap<Country, Country> expectedLoss;
    private List<Country> expectedWon;

    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        country1 = map.findByCountryName("Country1");
        country1.setArmy(1);
        country2 = map.findByCountryName("Country2");
        country2.setArmy(2);
        country3 = map.findByCountryName("Country3");
        country3.setArmy(1);
        country4 = map.findByCountryName("Country4");
        country4.setArmy(3);
        expectedLoss = new HashMap<>();
        expectedWon = new ArrayList<>();
    }

    @Test
    public void SkipAttack_ShouldDoNothing() {
        human = new HumanAttackStrategy(country1, country2, 0, 0, 1, 0);
        human.execute(map, Collections.singletonList(country1));
        assertTrue(human.isComplete());
        int[] expectedCounts = new int[]{1, 2, 1, 3};
        assertArrayEquals(expectedCounts, new int[]{country1.getArmy(), country2.getArmy(), country3.getArmy(), country4.getArmy()});
    }

    @Test
    public void AttackingWithTwoArmy_ShouldAttackTwice() {
        human = new HumanAttackStrategy(country1, country2, 3, 2, 0, 0);
        human.execute(map, Collections.singletonList(country1));
        assertEquals(2, human.getAttackCounter());
        assertFalse(human.isComplete());
    }

    @Test
    public void AttackingWithThreeArmyTwoDice_ShouldAttackTwice() {
        human = new HumanAttackStrategy(country4, country2, 2, 2, 0, 0);
        human.execute(map, Collections.singletonList(country4));
        assertEquals(2, human.getAttackCounter());
        assertFalse(human.isComplete());
    }

    @Test
    public void AttackingWithThreeArmyThreeDice_ShouldAttackOnce() {
        human = new HumanAttackStrategy(country4, country3, 3, 2, 0, 0);
        human.execute(map, Collections.singletonList(country4));
        assertEquals(1, human.getAttackCounter());
        assertFalse(human.isComplete());
    }

    @Test
    public void AttackingWithThreeArmyTwoDice_ShouldAttackOnce() {
        human = new HumanAttackStrategy(country4, country3, 2, 2, 0, 0);
        human.execute(map, Collections.singletonList(country4));
        assertEquals(1, human.getAttackCounter());
        assertFalse(human.isComplete());
    }

    @Test
    public void AttackingWithAlloutMode_ShouldLoseOrWin() {
        human = new HumanAttackStrategy(country4, country2, 0, 0, 0, 1);
        human.execute(map, Collections.singletonList(country4));
        expectedWon.add(country2);
        expectedLoss.put(country4, country2);
        assertFalse(human.isComplete());
        if (human.getWon().isEmpty()) {
            assertEquals(expectedLoss, human.getLost());
        } else {
            assertEquals(expectedWon, human.getWon());
        }
    }

}
