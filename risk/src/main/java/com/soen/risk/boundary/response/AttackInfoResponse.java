package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;
import com.soen.risk.entity.Country;
import com.soen.risk.views.DominationView;
import com.soen.risk.views.PhaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Class AttackInfoResponse.
 */
public class AttackInfoResponse implements Response {

    /** The country names. */
    private List<String> countryNames;
    
    /** The all country names. */
    private HashMap<String, ArrayList<String>> allCountryNames;

    /** The phase view. */
    private PhaseView phaseView;
    
    /** The domination view. */
    private DominationView dominationView;

    /**
     * Instantiates a new attack info response.
     */
    public AttackInfoResponse() {
        countryNames = new ArrayList<>();
        allCountryNames = new HashMap<>();
    }

    /**
     * Gets the country names.
     *
     * @return the country names
     */
    public List<String> getCountryNames() {
        return countryNames;
    }

    /**
     * Sets the country names.
     *
     * @param countries the new country names
     */
    public void setCountryNames(List<Country> countries) {
        for (Country country : countries)
            this.countryNames.add(country.getName());
    }

    /**
     * Gets the all country names.
     *
     * @return the all country names
     */
    public HashMap<String, ArrayList<String>> getAllCountryNames() {
        return allCountryNames;
    }

    /**
     * Sets the all country names.
     *
     * @param allCountryNames the all country names
     */
    public void setAllCountryNames(HashMap<String, ArrayList<String>> allCountryNames) {
        this.allCountryNames = allCountryNames;
    }

    /**
     * Gets the phase view.
     *
     * @return the phase view
     */
    public PhaseView getPhaseView() {
        return phaseView;
    }

    /**
     * Sets the phase view.
     *
     * @param phaseView the new phase view
     */
    public void setPhaseView(PhaseView phaseView) {
        this.phaseView = phaseView;
    }

    /**
     * Gets the domination view.
     *
     * @return the domination view
     */
    public DominationView getDominationView() {
        return dominationView;
    }

    /**
     * Sets the domination view.
     *
     * @param dominationView the new domination view
     */
    public void setDominationView(DominationView dominationView) {
        this.dominationView = dominationView;
    }

}
