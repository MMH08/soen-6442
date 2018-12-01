package com.soen.risk.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Queue;

import static org.junit.Assert.*;


public class GameTest {

    private Game game;
    private ArrayList<Player> players;

    @Before
    public void setUp() {
        Map map = new Map();
        map.load("./fixture/demo.map");
        players = new ArrayList<>();
        game = new Game(map, players);
    }

    @Test
    public void TwoPlayer_ShouldNotEndGame(){
        players.add(new Player("Player_1", "HUMAN"));
        players.add(new Player("Player_2", "HUMAN"));
        assertFalse(game.isEndNear());
    }

    @Test
    public void OnePlayer_ShouldEndGame(){
        players.add(new Player("Player_1", "HUMAN"));
        assertTrue(game.isEndNear());
    }


}