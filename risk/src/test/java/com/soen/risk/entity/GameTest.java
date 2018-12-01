package com.soen.risk.entity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * The Class GameTest.
 *
 * @author Varun
 */
public class GameTest {

    /**
     * The game.
     */
    private Game game;

    /**
     * The players.
     */
    private ArrayList<Player> players;

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        Map map = new Map();
        map.load("./fixture/demo.map");
        players = new ArrayList<>();
        game = new Game(map, players);
    }

    /**
     * Two player should not end game.
     */
    @Test
    public void TwoPlayer_ShouldNotEndGame() {
        players.add(new Player("Player_1", "HUMAN"));
        players.add(new Player("Player_2", "HUMAN"));
        assertFalse(game.isEndNear());
    }

    /**
     * One player should end game.
     */
    @Test
    public void OnePlayer_ShouldEndGame() {
        players.add(new Player("Player_1", "HUMAN"));
        assertTrue(game.isEndNear());
    }


}