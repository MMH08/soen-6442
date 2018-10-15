package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.CreateMapRequest;
import com.soen.risk.boundary.response.CreateMapResponse;
import com.soen.risk.interactor.MapBuilder;

public class CreateMap implements Usecase {

    private CreateMapRequest request;
    private CreateMapResponse response;

    public CreateMap(String... args) {
        request = new CreateMapRequest(args);
        response = new CreateMapResponse();
    }

    @Override
    public CreateMapResponse execute() {
        MapBuilder mapBuilder = new MapBuilder(request.getName(), request.getContinents(), request.getControlValues(),
                request.getCountries(), request.getCountryOwners(), request.getConnections());
        mapBuilder.build();
        String generatedFilePath = mapBuilder.save();
        response.setFilePath(generatedFilePath);
        return response;
    }

}
