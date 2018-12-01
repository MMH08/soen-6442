package com.soen.risk.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * The Class PlayerTest.
 *
 * @author Amit, Varun
 */
public class PlayerTest {

    /**
     * The player.
     */
    private Player player;

    /**
     * The country 0.
     */
    private Country country0;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        player = new Player("player_1", "HUMAN");
        country0 = new Country(0, "country0");
        player.addCountry(country0);
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.CAVALRY, Card.INFANT)));
    }


    /**
     * Adds the country should update size of countries.
     */
    @Test
    public void addCountry() {
        player.addCountry(new Country(1, "country1"));
        assertEquals(2, player.getCountries().size());
    }

    /**
     * Removes the country should update the size of countries.
     */
    @Test
    public void removeCountry() {
        player.removeCountry(country0);
        assertEquals(0, player.getCountries().size());
    }

    /**
     * Next country to assign army should return the empty country.
     */
    @Test
    public void nextCountryToAssignArmy() {
        assertEquals(country0, player.nextCountryToAssignArmy());
    }

    /**
     * Allocate initial army should assign army capacity to all players.
     */
    @Test
    public void allocateInitialArmy() {
        player.allocateInitialArmy();
        assertNotEquals(0, player.getArmyCapacity());
    }

    /**
     * Removes the card should update the card collection for the given player.
     */
    @Test
    public void removeCard() {
        player.removeCard(new String[]{"Artillery", "Cavalry"});
        assertEquals(1, player.getCards().size());
    }

    /**
     * Adds the random card should update the card collection of the given player.
     */
    @Test
    public void addRandomCard() {
        player.addRandomCard();
        assertEquals(4, player.getCards().size());
    }

    /**
     * Reset exchange army should set exchange army count to 0.
     */
    @Test
    public void resetExchangeArmy() {
        assertEquals(0, player.getExchangeArmy());
    }

    /**
     * Less than five should return false.
     */
    @Test
    public void LessThanFive_ShouldReturnFalse() {
        assertFalse(player.isCardExchangeEnabled());
    }

    /**
     * Five greater than five should return true.
     */
    @Test
    public void FiveGreaterThanFive_ShouldReturnTrue() {
        player.addRandomCard();
        player.addRandomCard();
        assertTrue(player.isCardExchangeEnabled());
    }

    /**
     * Three same type card should return true.
     */
    @Test
    public void ThreeSameTypeCard_ShouldReturnTrue() {
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.INFANT, Card.INFANT, Card.ARTILLERY)));
        assertTrue(player.isCardExchangeEnabled());
    }

    /**
     * Three different type card should return true.
     */
    @Test
    public void ThreeDifferentTypeCard_ShouldReturnTrue() {
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.CAVALRY, Card.INFANT, Card.ARTILLERY)));
        assertTrue(player.isCardExchangeEnabled());
    }


}