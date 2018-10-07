package com.soen.risk.controller;

import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;
import com.soen.risk.interactor.PhaseFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk")
public class ApiController {

    @RequestMapping("/createMap")
    public void createMap() {
        Map map = new Map();
    }

    @RequestMapping("/GamePlay")
    public Game play(@RequestParam("filename") String filename, @RequestParam("players") int countOfPlayers) {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.build(filename, countOfPlayers);
        return gamePlay.getGame();
    }

    @RequestMapping("/addArmy")
    public Game addArmyToOwnCountry(@RequestParam("armyCount") int armyCount, @RequestParam("country") String countryName) {
        Phase phase = PhaseFactory.build("startup");
        phase.execute();
        return phase.getGame();
    }

    @RequestMapping("/nextMove")
    public void nextMove() {
        GamePlay.getInstance();
    }

}
