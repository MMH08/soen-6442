package com.soen.risk.controller;

import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/risk")
public class ApiController {

    @RequestMapping("/createMap")
    public void createMap() {
        Map map = new Map();
    }

    @RequestMapping("/GamePlay")
    public String play(@RequestParam("filename") String filename, @RequestParam("players") int countOfPlayers) {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.build(filename, countOfPlayers);
        return "gameplay";
    }

    @RequestMapping("/phaseResolver")
    public String phaseResolver() {
        GamePlay gamePlay = GamePlay.getInstance();
        return "redirect:/" + gamePlay.getCurrentPhase().getName();
    }

    @RequestMapping("/startupPhase")
    public ModelAndView startupPhase() {
        ModelAndView model = new ModelAndView("startupphase");
        Player player = GamePlay.getInstance().getCurrentPlayer();
        model.addObject("armyCapacity", player.getArmyCapacity());
        model.addObject("playerName", player.getName());
        model.addObject("countryName", player.nextCountryToAssignArmy());
        return model;
    }

    @RequestMapping("/reinforcePhase")
    public String reinforcePhase() {
        Player player = GamePlay.getInstance().getCurrentPlayer();
        return "reinforcephase";
    }

    @RequestMapping("/fortifyPhase")
    public String fortifyPhase() {
        Player player = GamePlay.getInstance().getCurrentPlayer();
        return "fortifyphase";
    }

    @RequestMapping("/addArmy")
    public void addArmyToOwnCountry(@RequestParam("armyCount") int armyCount,
                                    @RequestParam("country") String countryName) {
    }

    @RequestMapping("/nextMove")
    public void nextMove() {
        GamePlay.getInstance();
    }

}
