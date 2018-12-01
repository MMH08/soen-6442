package com.soen.risk.boundary.usecase;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Phase;
import com.soen.risk.entity.Player;
import com.soen.risk.entity.player.benevolent.BenevolentAttackStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentFortifyStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentReinforceStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentStartupStrategy;
import com.soen.risk.entity.player.cheater.CheaterAttackStrategy;
import com.soen.risk.entity.player.cheater.CheaterFortifyStrategy;
import com.soen.risk.entity.player.cheater.CheaterReinforceStrategy;
import com.soen.risk.entity.player.cheater.CheaterStartupStrategy;
import com.soen.risk.interactor.GamePlay;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoadGameTest {

    private Game game;

    @Before
    public void setUp() {
        Map map = new Map();
        map.load("./fixture/demo.map");
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player_1", "BENEVOLENT"));
        players.add(new Player("Player_2", "BENEVOLENT"));
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.newGame(map, players);
        game = gamePlay.getGame();
        for (Player player : game.getPlayers()){
            player.setStartupStrategy(new BenevolentStartupStrategy());
            player.setReinforceStrategy(new BenevolentReinforceStrategy());
            player.setAttackStrategy(new BenevolentAttackStrategy());
            player.setFortifyStrategy(new BenevolentFortifyStrategy());
        }
    }

    @Test
    public void LoadGame_ShouldRetainCurrentStatus() {
        saveLoad();
        assertEquals(Phase.STARTUP, game.getCurrentPhase());
        assertEquals(game.getPlayers().get(0), game.getCurrentPlayer());
    }

    @Test
    public void ChangePhase_ShouldRetainCurrentPhase() {
        while (game.getCurrentPhase().equals(Phase.STARTUP)) {
            game.executeStartupPhase();
        }
        saveLoad();
        assertEquals(Phase.REINFORCE, game.getCurrentPhase());
    }

    @Test
    public void ChangePlayer_ShouldRetainCurrentPlayer() {
        while (game.getCurrentPhase().equals(Phase.STARTUP)) {
            game.executeStartupPhase();
        }
        Player firstTurn = game.getCurrentPlayer();
        game.executeReinforcePhase();
        game.executeAttackPhase();
        game.executeFortificationPhase();
        saveLoad();
        assertEquals(Phase.REINFORCE, game.getCurrentPhase());
        assertNotEquals(firstTurn, game.getCurrentPlayer());
    }

    private void saveLoad() {
        new SaveGame("load-test.ser").execute();
        new LoadGame("load-test.ser").execute();
        game = GamePlay.getInstance().getGame(); //update
    }

}
