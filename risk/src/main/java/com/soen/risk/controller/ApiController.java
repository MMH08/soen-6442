package com.soen.risk.controller;

import com.soen.risk.boundary.response.*;
import com.soen.risk.boundary.usecase.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/create01")
    public String createMapDetail() {
        return "create01";
    }

    @RequestMapping(value = "/create02", method = RequestMethod.POST)
    public ModelAndView createContinent(@RequestParam("name") String name,
                                  @RequestParam("numberOfContinent") String numberOfContinent,
                                  @RequestParam("numberOfCountry") String numberOfCountry,
                                        @RequestParam("downloadFolder") String downloadFolder) {
        ModelAndView model = new ModelAndView("create02");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        return model;
    }

    @RequestMapping(value="/create03", method = RequestMethod.POST)
    public ModelAndView createCountry(@RequestParam("name") String name,
                                @RequestParam("numberOfContinent") String numberOfContinent,
                                @RequestParam("numberOfCountry") String numberOfCountry,
                                      @RequestParam("downloadFolder") String downloadFolder,
                                @RequestParam("continentNames") String continentNames,
                                @RequestParam("controlValues") String controlValues) {
        ModelAndView model = new ModelAndView("create03");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        model.addObject("continentNames", continentNames);
        model.addObject("controlValues", controlValues);
        return model;
    }

    @RequestMapping(value="/create04", method = RequestMethod.POST)
    public ModelAndView createConnection(@RequestParam("name") String name,
                                   @RequestParam("numberOfContinent") String numberOfContinent,
                                   @RequestParam("numberOfCountry") String numberOfCountry,
                                         @RequestParam("downloadFolder") String downloadFolder,
                                   @RequestParam("continentNames") String continentNames,
                                   @RequestParam("controlValues") String controlValues,
                                   @RequestParam("countryNames") String countryNames,
                                   @RequestParam("continentOfCountries") String continentOfCountries) {
        ModelAndView model = new ModelAndView("create04");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        model.addObject("continentNames", continentNames);
        model.addObject("controlValues", controlValues);
        model.addObject("countryNames", countryNames);
        model.addObject("continentOfCountries", continentOfCountries);
        return model;
    }

    @RequestMapping(value = "/createSubmit", method = RequestMethod.POST)
    public String createSubmit(@RequestParam("name") String name,
                               @RequestParam("numberOfContinent") String numberOfContinent,
                               @RequestParam("numberOfCountry") String numberOfCountry,
                               @RequestParam("downloadFolder") String downloadFolder,
                               @RequestParam("continentNames") String continentNames,
                               @RequestParam("controlValues") String controlValues,
                               @RequestParam("countryNames") String countryNames,
                               @RequestParam("continentOfCountries") String continentOfCountries,
                               @RequestParam("connectionString") String connectionString){
        CreateMap usecase = new CreateMap(name, continentNames, controlValues, countryNames, continentOfCountries, connectionString, downloadFolder);
        usecase.execute();
        return "redirect:/";
    }


    // -----------------------------------------------------------------------------------------------------------------



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

    @RequestMapping("/fortifyPhase/moveArmy")
    public String moveFortifyArmy() {
        MoveFortifyArmy usecase = new MoveFortifyArmy();
        FortifyPhaseResponse response = usecase.execute();
        return "redirect:/phaseResolver";
    }


}
