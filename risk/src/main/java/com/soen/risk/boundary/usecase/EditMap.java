package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.EditMapRequest;
import com.soen.risk.boundary.response.EditMapResponse;
import com.soen.risk.entity.Map;

import java.util.logging.Level;

/**
 * The Class EditMap.
 */
public class EditMap implements Usecase {

    /**
     * The request.
     */
    private EditMapRequest request;

    /**
     * The response.
     */
    private EditMapResponse response;

    /**
     * Instantiates a new edits the map.
     *
     * @param args the args
     */
    public EditMap(String... args) {
        request = new EditMapRequest(args);
        response = new EditMapResponse();
    }


    /**
     * Use case responsible to load map by loading the filename
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public EditMapResponse execute() {
        Map map = new Map();
        map.load(request.getFileName()); // TODO: move these 3 lines into separate code file, repeated in Game play to load the map
        if (map.isValid()) {
            logger.log(Level.INFO, "Map found to be valid.");
            response.setName(map.getName());
            response.setCountryNames(map.getCountries());
            response.setControlValues(map.getContinents());
            response.setContinentNames(map.getContinents());
            response.setContinentOfCountries(map.getCountries(), map.getContinents());
            response.setNumberOfContinent(map.getNumberOfContinents());
            response.setNumberOfCountry(map.getNumberOfCountries());
            response.setConnectionString(map.getAdjCountry());
        }
        return response;
    }
}
