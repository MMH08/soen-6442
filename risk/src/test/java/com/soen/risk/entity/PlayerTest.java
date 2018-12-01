package com.soen.risk.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private Country country0;

    @Before
    public void setUp() {
        player = new Player("player_1", "HUMAN");
        country0 = new Country(0, "country0");
        player.addCountry(country0);
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.CAVALRY, Card.INFANT)));
    }


    @Test
    public void addCountry() {
        player.addCountry(new Country(1, "country1"));
        assertEquals(2, player.getCountries().size());
    }

    @Test
    public void removeCountry() {
        player.removeCountry(country0);
        assertEquals(0, player.getCountries().size());
    }

    @Test
    public void nextCountryToAssignArmy() {
        assertEquals(country0, player.nextCountryToAssignArmy());
    }

    @Test
    public void allocateInitialArmy() {
        player.allocateInitialArmy();
        assertNotEquals(0, player.getArmyCapacity());
    }

    @Test
    public void removeCard() {
        player.removeCard(new String[]{"Artillery", "Cavalry"});
        assertEquals(1, player.getCards().size());
    }

    @Test
    public void addRandomCard() {
        player.addRandomCard();
        assertEquals(4, player.getCards().size());
    }

    @Test
    public void resetExchangeArmy() {
        assertEquals(0, player.getExchangeArmy());
    }

    @Test
    public void LessThanFive_ShouldReturnFalse() {
        assertFalse(player.isCardExchangeEnabled());
    }

    @Test
    public void FiveGreaterThanFive_ShouldReturnTrue(){
        player.addRandomCard();
        player.addRandomCard();
        assertTrue(player.isCardExchangeEnabled());
    }

    @Test
    public void ThreeSameTypeCard_ShouldReturnTrue(){
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.INFANT, Card.INFANT, Card.ARTILLERY)));
        assertTrue(player.isCardExchangeEnabled());
    }

    @Test
    public void ThreeDifferentTypeCard_ShouldReturnTrue(){
        player.setCards(new ArrayList<>(Arrays.asList(Card.ARTILLERY, Card.INFANT, Card.CAVALRY, Card.INFANT, Card.ARTILLERY)));
        assertTrue(player.isCardExchangeEnabled());
    }


}