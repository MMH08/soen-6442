package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.EditMapRequest;
import com.soen.risk.boundary.response.EditMapResponse;
import com.soen.risk.entity.Map;

public class EditMap implements Usecase {
    private EditMapRequest request;
    private EditMapResponse response;

    public EditMap(String... args) {
        request = new EditMapRequest(args);
        response = new EditMapResponse();
    }


    /**
     * @return
     */
    @Override
    public EditMapResponse execute() {
        Map map = new Map();
        map.load(request.getFileName()); // TODO: move these 3 lines into separate code file, repeated in Game play to load the map
        response.setName(map.getName());
        response.setCountryNames(map.getCountries());
        response.setControlValues(map.getContinents());
        response.setContinentNames(map.getContinents());
        response.setContinentOfCountries(map.getCountries(), map.getContinents());
        response.setNumberOfContinent(map.getNumberOfContinents());
        response.setNumberOfCountry(map.getNumberOfCountries());
        response.setConnectionString(map.getAdjCountry());
        return response;
    }
}
