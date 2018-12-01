package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.TournamentRequest;
import com.soen.risk.boundary.response.TournamentResponse;

import java.util.logging.Level;

/**
 * Assumption: player behavior is not human.
 */
public class StartTournament implements Usecase {

    /**
     * The response.
     */
    private TournamentResponse response;

    /**
     * The request.
     */
    private TournamentRequest request;

    /**
     * Instantiates a new start tournament.
     *
     * @param args the args
     */
    public StartTournament(String... args) {
        request = new TournamentRequest(args);
    }

    /**
     * Execute the tournament mode in loop.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public TournamentResponse execute() {
        for (String fileName : request.getFilenames()) {
            for (int countOfgame = 0; countOfgame < request.getCountOfGames(); countOfgame++) {
                logger.log(Level.INFO, "----------- start game " + countOfgame + " -------------");

                //usecase
                StartGame game = new StartGame(fileName, request.getPlayerNames(), request.getBehaviors());
                game.execute();

                //game driver
                GameDriver gameDriver = new GameDriver();
                gameDriver.execute();
                logger.log(Level.INFO, "----------- end game " + countOfgame + " -------------");

//                // todo : add more conditions to break
//                if (gameDriverResponse.getGameEnd()) {
//
//                    GamePlay.setInstance(null); // reset gamePlay
//                    break;
//                }

                // todo : store the result
            }
        }
        return response;
    }
}
