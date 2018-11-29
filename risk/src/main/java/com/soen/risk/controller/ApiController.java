package com.soen.risk.controller;

import com.soen.risk.boundary.response.*;
import com.soen.risk.boundary.usecase.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Class ApiController.
 */
@Controller
public class ApiController {

    /**
     * The logger.
     */
    public static Logger logger = Logger.getLogger(ApiController.class.getName());

    /**
     * Index.
     *
     * @return the string
     */
    @RequestMapping("")
    public String index() {
        return "index";
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates the map detail.
     *
     * @return the string
     */
    @RequestMapping("/create01")
    public String createMapDetail() {
        return "create01";
    }

    /**
     * Creates the continent.
     *
     * @param name              the name
     * @param numberOfContinent the number of continent
     * @param numberOfCountry   the number of country
     * @param downloadFolder    the download folder
     * @return the model and view
     */
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

    /**
     * Creates the country.
     *
     * @param name              the name
     * @param numberOfContinent the number of continent
     * @param numberOfCountry   the number of country
     * @param downloadFolder    the download folder
     * @param continentNames    the continent names
     * @param controlValues     the control values
     * @return the model and view
     */
    @RequestMapping(value = "/create03", method = RequestMethod.POST)
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

    /**
     * Creates the connection.
     *
     * @param name                 the name
     * @param numberOfContinent    the number of continent
     * @param numberOfCountry      the number of country
     * @param downloadFolder       the download folder
     * @param continentNames       the continent names
     * @param controlValues        the control values
     * @param countryNames         the country names
     * @param continentOfCountries the continent of countries
     * @return the model and view
     */
    @RequestMapping(value = "/create04", method = RequestMethod.POST)
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

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Load edit map detail.
     *
     * @return the string
     */
    @RequestMapping("/edit00")
    public String loadEditMapDetail() {
        return "edit00";
    }

    /**
     * Edits the map detail.
     *
     * @param filename the filename
     * @return the model and view
     */
    @RequestMapping(value = "/edit01", method = RequestMethod.POST)
    public ModelAndView editMapDetail(@RequestParam("filename") String filename) {
        EditMap usecase = new EditMap(filename);
        EditMapResponse response = usecase.execute();
        ModelAndView model = new ModelAndView("edit01");
        model.addObject("name", response.getName());
        model.addObject("numberOfContinent", response.getNumberOfContinent());
        model.addObject("numberOfCountry", response.getNumberOfCountry());
        model.addObject("continentNames", response.getContinentNames());
        model.addObject("controlValues", response.getControlValues());
        model.addObject("countryNames", response.getCountryNames());
        model.addObject("continentOfCountries", response.getContinentOfCountries());
        model.addObject("connectionString", response.getConnectionString());
        return model;
    }

    /**
     * Edits the continent.
     *
     * @param name                 the name
     * @param numberOfContinent    the number of continent
     * @param numberOfCountry      the number of country
     * @param downloadFolder       the download folder
     * @param continentNames       the continent names
     * @param controlValues        the control values
     * @param countryNames         the country names
     * @param continentOfCountries the continent of countries
     * @param connectionString     the connection string
     * @return the model and view
     */
    @RequestMapping(value = "/edit02", method = RequestMethod.POST)
    public ModelAndView editContinent(@RequestParam("name") String name,
                                      @RequestParam("numberOfContinent") String numberOfContinent,
                                      @RequestParam("numberOfCountry") String numberOfCountry,
                                      @RequestParam("downloadFolder") String downloadFolder,
                                      @RequestParam("continentNames") String continentNames,
                                      @RequestParam("controlValues") String controlValues,
                                      @RequestParam("countryNames") String countryNames,
                                      @RequestParam("continentOfCountries") String continentOfCountries,
                                      @RequestParam("connectionString") String connectionString) {
        ModelAndView model = new ModelAndView("edit02");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        model.addObject("continentNames", continentNames);
        model.addObject("controlValues", controlValues);
        model.addObject("countryNames", countryNames);
        model.addObject("continentOfCountries", continentOfCountries);
        model.addObject("connectionString", connectionString);
        return model;
    }

    /**
     * Edits the country.
     *
     * @param name                 the name
     * @param numberOfContinent    the number of continent
     * @param numberOfCountry      the number of country
     * @param downloadFolder       the download folder
     * @param continentNames       the continent names
     * @param controlValues        the control values
     * @param countryNames         the country names
     * @param continentOfCountries the continent of countries
     * @param connectionString     the connection string
     * @return the model and view
     */
    @RequestMapping(value = "/edit03", method = RequestMethod.POST)
    public ModelAndView editCountry(@RequestParam("name") String name,
                                    @RequestParam("numberOfContinent") String numberOfContinent,
                                    @RequestParam("numberOfCountry") String numberOfCountry,
                                    @RequestParam("downloadFolder") String downloadFolder,
                                    @RequestParam("continentNames") String continentNames,
                                    @RequestParam("controlValues") String controlValues,
                                    @RequestParam("countryNames") String countryNames,
                                    @RequestParam("continentOfCountries") String continentOfCountries,
                                    @RequestParam("connectionString") String connectionString) {
        ModelAndView model = new ModelAndView("edit03");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        model.addObject("continentNames", continentNames);
        model.addObject("controlValues", controlValues);
        model.addObject("countryNames", countryNames);
        model.addObject("continentOfCountries", continentOfCountries);
        model.addObject("connectionString", connectionString);
        return model;
    }

    /**
     * Edits the connection.
     *
     * @param name                 the name
     * @param numberOfContinent    the number of continent
     * @param numberOfCountry      the number of country
     * @param downloadFolder       the download folder
     * @param continentNames       the continent names
     * @param controlValues        the control values
     * @param countryNames         the country names
     * @param continentOfCountries the continent of countries
     * @param connectionString     the connection string
     * @return the model and view
     */
    @RequestMapping(value = "/edit04", method = RequestMethod.POST)
    public ModelAndView editConnection(@RequestParam("name") String name,
                                       @RequestParam("numberOfContinent") String numberOfContinent,
                                       @RequestParam("numberOfCountry") String numberOfCountry,
                                       @RequestParam("downloadFolder") String downloadFolder,
                                       @RequestParam("continentNames") String continentNames,
                                       @RequestParam("controlValues") String controlValues,
                                       @RequestParam("countryNames") String countryNames,
                                       @RequestParam("continentOfCountries") String continentOfCountries,
                                       @RequestParam("connectionString") String connectionString) {
        ModelAndView model = new ModelAndView("edit04");
        model.addObject("name", name);
        model.addObject("numberOfContinent", numberOfContinent);
        model.addObject("numberOfCountry", numberOfCountry);
        model.addObject("downloadFolder", downloadFolder);
        model.addObject("continentNames", continentNames);
        model.addObject("controlValues", controlValues);
        model.addObject("countryNames", countryNames);
        model.addObject("continentOfCountries", continentOfCountries);
        model.addObject("connectionString", connectionString);
        return model;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates the submit.
     *
     * @param name                 the name
     * @param numberOfContinent    the number of continent
     * @param numberOfCountry      the number of country
     * @param downloadFolder       the download folder
     * @param continentNames       the continent names
     * @param controlValues        the control values
     * @param countryNames         the country names
     * @param continentOfCountries the continent of countries
     * @param connectionString     the connection string
     * @return the string
     */
    @RequestMapping(value = "/createSubmit", method = RequestMethod.POST)
    public String createSubmit(@RequestParam("name") String name,
                               @RequestParam("numberOfContinent") String numberOfContinent,
                               @RequestParam("numberOfCountry") String numberOfCountry,
                               @RequestParam("downloadFolder") String downloadFolder,
                               @RequestParam("continentNames") String continentNames,
                               @RequestParam("controlValues") String controlValues,
                               @RequestParam("countryNames") String countryNames,
                               @RequestParam("continentOfCountries") String continentOfCountries,
                               @RequestParam("connectionString") String connectionString) {
        CreateMap usecase = new CreateMap(name, continentNames, controlValues, countryNames, continentOfCountries, connectionString, downloadFolder);
        usecase.execute();
        return "redirect:/";
    }


    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/singleGame01")
    public String singleGame01() {
        return "singlegame01";
    }

    @RequestMapping("/singleGame02")
    public ModelAndView singleGame02(@RequestParam("players") String countOfPlayers) {
        ModelAndView model = new ModelAndView("/singlegame02");
        model.addObject("countOfPlayers", countOfPlayers);
        return model;
    }

    @RequestMapping("/singleGamePlay")
    public String startGame(@RequestParam("filename") String filename,
                            @RequestParam("names") String playerNames,
                            @RequestParam("behaviors") String playerBehaviors) {
        StartGame usecase = new StartGame(filename, playerNames, playerBehaviors);
        usecase.execute();
        return "redirect:/gameDriver";
    }


    /**
     * Phase resolver.
     *
     * @return the string
     */
    @RequestMapping("/gameDriver")
    public String gameDriver() {
        GameDriver usecase = new GameDriver();
        GameDriverResponse response = usecase.execute();
        logger.log(Level.INFO, "redirecting to phase - " + response.getPhaseName());
        return "redirect:/" + response.getPhaseName();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @RequestMapping("/tournament01")
    public String tournament01() {
        return "tournament01";
    }

    @RequestMapping("/tournament02")
    public ModelAndView tournament02(@RequestParam("players") String countOfPlayers,
                                     @RequestParam("maps") String countOfMaps,
                                     @RequestParam("games") String countOfGames) {
        ModelAndView model = new ModelAndView("/tournament02");
        model.addObject("countOfPlayers", countOfPlayers);
        model.addObject("countOfMaps", countOfMaps);
        model.addObject("countOfGames", countOfGames);
        return model;
    }

    @RequestMapping("/tournamentPlay")
    public String tournamentPlay(@RequestParam("filenames") String filenames,
                                 @RequestParam("names") String playerNames,
                                 @RequestParam("behaviors") String behaviors,
                                 @RequestParam("turns") String turns,
                                 @RequestParam("games") String countOfGames) {
        StartTournament usecase = new StartTournament(filenames, playerNames, behaviors, turns, countOfGames);
        usecase.execute();
        return "redirect:/tournamentDriver";
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Startup phase.
     *
     * @return the model and view
     */
    @RequestMapping("/startupPhase")
    public ModelAndView startupPhase() {
        ModelAndView model = new ModelAndView("startupphase");

        StartupInfo usecase = new StartupInfo();
        StartupInfoResponse response = usecase.execute();
        model.addObject("phaseView", response.getPhaseView());
        model.addObject("dominationView", response.getDominationView());
        model.addObject("armyCapacity", response.getArmyCapacity());
        model.addObject("countryName", response.getCountryName());
        return model;
    }

    /**
     * Adds the army to own country.
     *
     * @param armyCount   the army count
     * @param countryName the country name
     * @return the string
     */
    @RequestMapping("/startupPhase/addArmy")
    public String addArmyToOwnCountry(@RequestParam("armyCount") String armyCount,
                                      @RequestParam("country") String countryName) {

        AddStartupArmy usecase = new AddStartupArmy(armyCount, countryName);
        StartupPhaseResponse response = usecase.execute();
        return "redirect:/gameDriver";
    }

    /**
     * Reinforce phase.
     *
     * @return the model and view
     */
    @RequestMapping("/reinforcePhase")
    public ModelAndView reinforcePhase() {
        ModelAndView model = new ModelAndView("reinforcephase");

        ReinforceInfo usecase = new ReinforceInfo();
        ReinforceInfoResponse response = usecase.execute();

        model.addObject("phaseView", response.getPhaseView());
        model.addObject("dominationView", response.getDominationView());
        model.addObject("reinforceArmyCount", response.getReinforceArmyCapacity());
        model.addObject("countries", response.getCountries());
        model.addObject("cards", response.getCards());
        model.addObject("cardExchangeEnabled", response.isCardExchangeEnabled());
        model.addObject("cardExchangeView", response.getCardExchangeView());
        model.addObject("endGame", response.isEndGame());
        return model;
    }

    /**
     * Adds the reinforce army.
     *
     * @param armyCounts the army counts
     * @return the string
     */
    @RequestMapping("/reinforcePhase/addArmy")
    public String addReinforceArmy(@RequestParam("armyCounts") String armyCounts) {
        AddReinforceArmy usecase = new AddReinforceArmy(armyCounts);
        usecase.execute();
        return "redirect:/gameDriver";
    }


    /**
     * Card exchange view.
     *
     * @param cards the cards to be exchanged
     * @return the string
     */
    @RequestMapping("/cardExchange")
    public String ExchangeCards(@RequestParam("cards") String cards) {
        CardExchange usecase = new CardExchange(cards);
        usecase.execute();
        return "redirect:/gameDriver";
    }

    /**
     * Attack phase.
     *
     * @return the string
     */
    @RequestMapping("/attackPhase")
    public ModelAndView attackPhase() {
        ModelAndView model = new ModelAndView("attackphase");

        AttackInfo usecase = new AttackInfo();
        AttackInfoResponse response = usecase.execute();
        model.addObject("countryNames", response.getCountryNames());
        model.addObject("allCountryNames", response.getAllCountryNames());
        model.addObject("phaseView", response.getPhaseView());
        model.addObject("dominationView", response.getDominationView());
        return model;
    }

    @RequestMapping("/attackPhase/attack")
    public String attachPhaseExecute(@RequestParam(value = "attackingCountry", defaultValue = "null") String attackingCountry,
                                     @RequestParam(value = "attackingDiceCount", defaultValue = "0") String attackingDiceCount,
                                     @RequestParam(value = "defendingCountry", defaultValue = "null") String defendingCountry,
                                     @RequestParam(value = "defendingDiceCount", defaultValue = "0") String defendingDiceCount,
                                     @RequestParam(value = "skipAttack", defaultValue = "0") String skipAttack,
                                     @RequestParam(value = "allOutMode", defaultValue = "0") String allOutMode) {
        ExecuteAttackPhase usecase = new ExecuteAttackPhase(attackingCountry, attackingDiceCount, defendingCountry,
                defendingDiceCount, skipAttack, allOutMode);
        usecase.execute();
        return "redirect:/gameDriver";
    }


    /**
     * Fortify phase.
     *
     * @return the model and view
     */
    @RequestMapping("/fortifyPhase")
    public ModelAndView fortifyPhase() {
        ModelAndView model = new ModelAndView("fortifyphase");
        FortifyInfo usecase = new FortifyInfo();
        FortifyInfoResponse response = usecase.execute();
        model.addObject("phaseView", response.getPhaseView());
        model.addObject("dominationView", response.getDominationView());
        model.addObject("countryNames", response.getCountryNames());
        model.addObject("isEnd", response.isEndGame());
        return model;
    }

    /**
     * Move fortify army.
     *
     * @param startCountry the start country
     * @param endCountry   the end country
     * @param armyCount    the army count
     * @return the string
     */
    @RequestMapping("/fortifyPhase/moveArmy")
    public String moveFortifyArmy(@RequestParam("startCountry") String startCountry,
                                  @RequestParam("endCountry") String endCountry,
                                  @RequestParam("armyCount") String armyCount) {
        MoveFortifyArmy usecase = new MoveFortifyArmy(startCountry, endCountry, armyCount);
        FortifyPhaseResponse response = usecase.execute();
        return "redirect:/gameDriver";
    }

    @RequestMapping("/endGame")
    public String endGame() {
        EndGame usecase = new EndGame();
        usecase.execute();
        return "redirect:/";
    }

    @RequestMapping("/saveGame")
    public String saveGame() {
        return "savegame";
    }

    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public String createSaveGameFile(@RequestParam("fileName") String fileName) {
        SaveGame usecase = new SaveGame(fileName);
        usecase.execute();
        return "redirect:/";
    }

    @RequestMapping("/loadGame")
    public ModelAndView loadGameInfo() {
        ModelAndView model = new ModelAndView("loadgame");
        LoadGameInfo usecase = new LoadGameInfo();
        LoadGameInfoResponse response = usecase.execute();
        model.addObject("filenames", response.getFilenames());
        return model;
    }

    @RequestMapping(value = "/loadGame", method = RequestMethod.POST)
    public String loadGame(@RequestParam("filename") String filename){
        LoadGame usecase = new LoadGame(filename);
        usecase.execute();
        return "redirect:/gameDriver";
    }
}
