package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.response.TournamentResponse;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class StartTournamentTest.
 */
public class StartTournamentTest {

    /**
     * Cheater and cheater should run complete.
     */
    @Test
    public void CheaterAndCheater_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "CHEATER,CHEATER", "5", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

    /**
     * Cheater and aggressive should run complete.
     */
    @Test
    public void CheaterAndAggressive_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "CHEATER,AGGRESSIVE", "30", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

    /**
     * Cheater and random should run complete.
     */
    @Test
    public void CheaterAndRandom_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "CHEATER,RANDOM", "30", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

    /**
     * Cheater and benevolent should run complete.
     */
    @Test
    public void CheaterAndBenevolent_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "CHEATER,BENEVOLENT", "30", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

    /**
     * Random and benevolent should run complete.
     */
    @Test
    public void RandomAndBenevolent_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "RANDOM,BENEVOLENT", "30", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

    /**
     * Random and aggressive should run complete.
     */
    @Test
    public void RandomAndAggressive_ShouldRunComplete() {
        StartTournament tournament = new StartTournament("./fixture/demo.map", "Player_1,Player_2", "RANDOM,AGGRESSIVE", "30", "2");
        TournamentResponse response = tournament.execute();
        assertNotNull(response.getWinners());
        assertNotNull(response.getGameCounts());
        assertNotNull(response.getMapNames());
        assertNotEquals(0, response.getWinners().size());
    }

}