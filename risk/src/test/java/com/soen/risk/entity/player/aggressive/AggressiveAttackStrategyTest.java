package com.soen.risk.entity.player.aggressive;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The Class AggressiveAttackStrategyTest.
 *
 * @author Manmeet, varun
 */
public class AggressiveAttackStrategyTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The aggressive.
     */
    private AttackStrategy aggressive;

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
     * The expected loss.
     */
    private HashMap<Country, Country> expectedLoss;

    /**
     * The expected won.
     */
    private List<Country> expectedWon;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        aggressive = new AggressiveAttackStrategy();
        country1 = map.findByCountryName("country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("country3");
        country3.setArmy(10);
        country4 = map.findByCountryName("country4");
        country4.setArmy(30);
        expectedLoss = new HashMap<>();
        expectedWon = new ArrayList<>();
    }

    /**
     * One country should attack one opponent.
     */
    @Test
    public void OneCountry_ShouldAttackOneOpponent() {
        expectedLoss.put(country2, country1);
        expectedWon.add(country1);
        aggressive.execute(map, Collections.singletonList(country2));

        if (aggressive.getWon().isEmpty()) {
            assertEquals(expectedLoss, aggressive.getLost());
        } else {
            assertEquals(expectedWon, aggressive.getWon());
        }
    }

    /**
     * Three country not surrounded by opponent should attack none.
     */
    @Test
    public void ThreeCountryNotSurroundedByOpponent_ShouldAttackNone() {
        aggressive.execute(map, Arrays.asList(country1, country2, country3));
        assertEquals(expectedWon, aggressive.getWon());
        assertEquals(expectedLoss, aggressive.getLost());
    }

    /**
     * One country should attack all opponent.
     */
    @Test
    public void OneCountry_ShouldAttackAllOpponent() {
        aggressive.execute(map, Collections.singletonList(country3));

        if (!aggressive.getWon().isEmpty() && aggressive.getLost().isEmpty()) {
            // case 1 - won both the opponents
            expectedWon.add(country1);
            expectedWon.add(country4);
            assertEquals(expectedWon, aggressive.getWon());
        } else if (!aggressive.getLost().isEmpty() && aggressive.getWon().isEmpty()) {
            // Case 2 - lost the first attack
            assertTrue(aggressive.getLost().get(country3) == country4 ||
                    aggressive.getLost().get(country3) == country1);
        } else {
            // Case 3 - lost the second attack
            assertTrue(aggressive.getWon().contains(country1) ||
                    aggressive.getWon().contains(country4));
            assertTrue(aggressive.getLost().get(country3) == country1 ||
                    aggressive.getLost().get(country3) == country4);
        }
    }
}


