package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.PhaseResolverRequest;
import com.soen.risk.boundary.response.GameDriverResponse;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Phase;
import com.soen.risk.entity.PlayerType;
import com.soen.risk.entity.player.aggressive.AggressiveAttackStrategy;
import com.soen.risk.entity.player.aggressive.AggressiveFortifyStrategy;
import com.soen.risk.entity.player.aggressive.AggressiveReinforceStrategy;
import com.soen.risk.entity.player.aggressive.AggressiveStartupStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentAttackStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentFortifyStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentReinforceStrategy;
import com.soen.risk.entity.player.benevolent.BenevolentStartupStrategy;
import com.soen.risk.entity.player.cheater.CheaterAttackStrategy;
import com.soen.risk.entity.player.cheater.CheaterFortifyStrategy;
import com.soen.risk.entity.player.cheater.CheaterReinforceStrategy;
import com.soen.risk.entity.player.cheater.CheaterStartupStrategy;
import com.soen.risk.entity.player.random.RandomAttackStrategy;
import com.soen.risk.entity.player.random.RandomFortifyStrategy;
import com.soen.risk.entity.player.random.RandomReinforceStrategy;
import com.soen.risk.entity.player.random.RandomStartupStrategy;
import com.soen.risk.interactor.GamePlay;

import java.util.logging.Level;

/**
 * The Class GameDriver.
 */
public class GameDriver implements Usecase {

    /**
     * The request.
     */
    private PhaseResolverRequest request;

    /**
     * The response.
     */
    private GameDriverResponse response;

    /**
     * Instantiates a new phase resolver.
     *
     * @param args the args
     */
    public GameDriver(String... args) {
        request = new PhaseResolverRequest(args);
        response = new GameDriverResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public GameDriverResponse execute() {
        Game game = GamePlay.getInstance().getGame();

        while (isLoopActive(game)) {
            if (game.getCurrentPhase().equals(Phase.STARTUP)) {
                if (game.getCurrentPlayer().getType().equals(PlayerType.CHEATER)) {
                    game.getCurrentPlayer().setStartupStrategy(new CheaterStartupStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.AGGRESSIVE)) {
                    game.getCurrentPlayer().setStartupStrategy(new AggressiveStartupStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.BENEVOLENT)) {
                    game.getCurrentPlayer().setStartupStrategy(new BenevolentStartupStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.RANDOM)) {
                    game.getCurrentPlayer().setStartupStrategy(new RandomStartupStrategy());
                }
                game.executeStartupPhase();
                if(game.getCurrentPlayer().getType().equals(PlayerType.HUMAN)) break; // re-evaluate if the player is human or not
            }
            if (game.getCurrentPhase().equals(Phase.REINFORCE)) {
                if (game.getCurrentPlayer().getType().equals(PlayerType.CHEATER)) {
                    game.getCurrentPlayer().setReinforceStrategy(new CheaterReinforceStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.AGGRESSIVE)) {
                    game.getCurrentPlayer().setReinforceStrategy(new AggressiveReinforceStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.BENEVOLENT)) {
                    game.getCurrentPlayer().setReinforceStrategy(new BenevolentReinforceStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.RANDOM)) {
                    game.getCurrentPlayer().setReinforceStrategy(new RandomReinforceStrategy());
                }
                game.executeReinforcePhase();
            }
            if (game.getCurrentPhase().equals(Phase.ATTACK)) {
                if (game.getCurrentPlayer().getType().equals(PlayerType.CHEATER)) {
                    game.getCurrentPlayer().setAttackStrategy(new CheaterAttackStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.AGGRESSIVE)) {
                    game.getCurrentPlayer().setAttackStrategy(new AggressiveAttackStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.BENEVOLENT)) {
                    game.getCurrentPlayer().setAttackStrategy(new BenevolentAttackStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.RANDOM)) {
                    game.getCurrentPlayer().setAttackStrategy(new RandomAttackStrategy());
                }
                game.executeAttackPhase();
            }
            if (game.getCurrentPhase().equals(Phase.FORTIFY)) {
                if (game.getCurrentPlayer().getType().equals(PlayerType.CHEATER)) {
                    game.getCurrentPlayer().setFortifyStrategy(new CheaterFortifyStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.AGGRESSIVE)) {
                    game.getCurrentPlayer().setFortifyStrategy(new AggressiveFortifyStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.BENEVOLENT)) {
                    game.getCurrentPlayer().setFortifyStrategy(new BenevolentFortifyStrategy());
                } else if (game.getCurrentPlayer().getType().equals(PlayerType.RANDOM)) {
                    game.getCurrentPlayer().setFortifyStrategy(new RandomFortifyStrategy());
                }
                game.executeFortificationPhase();
            }
        }
        response.setPhaseName(game.getCurrentPhase().toString());
        response.setGameEnd(game.isEndNear());
        return response;
    }

    private boolean isLoopActive(Game game) {
        if(game.isEndNear()) {
            logger.log(Level.INFO, "Exiting the game");
            return false;
        }
        if(game.getCurrentPlayer().getType().equals(PlayerType.HUMAN))
            return false;
        return true;
    }
}
