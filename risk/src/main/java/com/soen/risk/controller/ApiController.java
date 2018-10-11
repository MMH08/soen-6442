package com.soen.risk.controller;

import com.soen.risk.boundary.response.*;
import com.soen.risk.boundary.usecase.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/createMap")
    public void createMap() {
        //Map map = new Map();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/GamePlay")
    public String startGame(@RequestParam("filename") String filename, @RequestParam("players") String countOfPlayers) {
        StartGame usecase = new StartGame(filename, countOfPlayers);
        usecase.execute();
        return "redirect:/phaseResolver";
    }

    @RequestMapping("/phaseResolver")
    public String phaseResolver() {
        PhaseResolver usecase = new PhaseResolver();
        PhaseResolverResponse response = usecase.execute();
        return "redirect:/" + response.getPhaseName();
    }

    @RequestMapping("/startupPhase")
    public ModelAndView startupPhase() {
        ModelAndView model = new ModelAndView("startupphase");

        StartupInfo usecase = new StartupInfo();
        StartupInfoResponse response = usecase.execute();
        model.addObject("armyCapacity", response.getArmyCapacity());
        model.addObject("playerName", response.getPlayerName());
        model.addObject("countryName", response.getCountryName());
        model.addObject("countries", response.getCountries());
        return model;
    }

    @RequestMapping("/startupPhase/addArmy")
    public String addArmyToOwnCountry(@RequestParam("armyCount") String armyCount,
                                      @RequestParam("country") String countryName) {

        AddStartupArmy usecase = new AddStartupArmy(armyCount, countryName);
        StartupPhaseResponse response = usecase.execute();
        return "redirect:/phaseResolver";
    }

    @RequestMapping("/reinforcePhase")
    public ModelAndView reinforcePhase() {
        ModelAndView model = new ModelAndView("reinforcephase");

        ReinforceInfo usecase = new ReinforceInfo();
        ReinforceInfoResponse response = usecase.execute();
        model.addObject("reinforceArmyCount", response.getReinforceArmyCapacity());
        model.addObject("playerName", response.getPlayerName());
        model.addObject("countries", response.getCountries());
        return model;
    }

    @RequestMapping("/reinforcePhase/addArmy")
    public String addReinforceArmy(@RequestParam("armyCounts") String armyCounts) {
        AddReinforceArmy usecase = new AddReinforceArmy(armyCounts);
        usecase.execute();
        return "redirect:/phaseResolver";
    }

    @RequestMapping("/fortifyPhase")
    public ModelAndView fortifyPhase() {
        ModelAndView model = new ModelAndView("fortifyphase");
        FortifyInfo usecase = new FortifyInfo();
        FortifyInfoResponse response = usecase.execute();
        model.addObject("playerName", response.getPlayerName());
        return model;
    }

}
