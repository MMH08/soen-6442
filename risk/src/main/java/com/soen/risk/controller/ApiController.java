package com.soen.risk.controller;

import com.soen.risk.boundary.ReinforcePhaseRequest;
import com.soen.risk.boundary.StartupPhaseRequest;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.phase.ReinforcePhase;
import com.soen.risk.interactor.phase.StartupPhase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

    @RequestMapping("/createMap")
    public void createMap() {
        Map map = new Map();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/GamePlay")
    public String play(@RequestParam("filename") String filename, @RequestParam("players") int countOfPlayers) {
        GamePlay gamePlay = GamePlay.getInstance();
        gamePlay.build(filename, countOfPlayers);
        return "redirect:/phaseResolver";
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
        model.addObject("countryName", player.nextCountryToAssignArmy().getName());
        return model;
    }

    @RequestMapping("/startupPhase/addArmy")
    public String addArmyToOwnCountry(@RequestParam("armyCount") int armyCount,
                                      @RequestParam("country") String countryName) {
        StartupPhaseRequest request = new StartupPhaseRequest(countryName, armyCount);
        StartupPhase phase = new StartupPhase();
        phase.execute(request);
        return "redirect:/phaseResolver";
    }

    @RequestMapping("/reinforcePhase")
    public ModelAndView reinforcePhase() {
        ModelAndView model = new ModelAndView("reinforcephase");
        GamePlay gamePlay = GamePlay.getInstance();
        Player player = gamePlay.getCurrentPlayer();
        model.addObject("playerName", player.getName());
        model.addObject("reinforceArmyCount", player.getReinforceArmyCapacity(gamePlay.getGame().getMap()));
        model.addObject("countryNames", player.getCountries());
        return model;
    }

    @RequestMapping("/reinforcePhase/addArmy")
    public String addReinforceArmy(@RequestParam("countryNames") String countryNames, @RequestParam("armyCounts") String armyCounts) {
        ReinforcePhaseRequest request = new ReinforcePhaseRequest(countryNames, armyCounts);
        new ReinforcePhase().execute(request);
        return "redirect:/phaseResolver";
    }

    @RequestMapping("/fortifyPhase")
    public ModelAndView fortifyPhase() {
        ModelAndView model = new ModelAndView("fortifyphase");
        Player player = GamePlay.getInstance().getCurrentPlayer();
        return model;
    }

}
