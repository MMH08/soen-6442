package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.CreateMapRequest;
import com.soen.risk.boundary.response.CreateMapResponse;
import com.soen.risk.interactor.MapBuilder;

/**
 * The Class CreateMap.
 */
public class CreateMap implements Usecase {

    /** The request. */
    private CreateMapRequest request;
    
    /** The response. */
    private CreateMapResponse response;

    /**
     * Instantiates a new creates the map.
     *
     * @param args the args
     */
    public CreateMap(String... args) {
        request = new CreateMapRequest(args);
        response = new CreateMapResponse();
    }

    /* (non-Javadoc)
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public CreateMapResponse execute() {
        MapBuilder mapBuilder = new MapBuilder(request.getName(), request.getContinents(), request.getControlValues(),
                request.getCountries(), request.getCountryOwners(), request.getConnections());
        mapBuilder.build();
        String generatedFilePath = mapBuilder.save(request.getDownloadFolder());
        response.setFilePath(generatedFilePath);
        return response;
    }

}
