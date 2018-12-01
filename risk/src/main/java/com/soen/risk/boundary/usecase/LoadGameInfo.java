package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.LoadGameInfoRequest;
import com.soen.risk.boundary.response.LoadGameInfoResponse;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;

/**
 * The Class LoadGameInfo.
 *
 * @author Hina
 */
public class LoadGameInfo implements Usecase {

    /**
     * The request.
     */
    private LoadGameInfoRequest request;

    /**
     * The response.
     */
    private LoadGameInfoResponse response;

    /**
     * Instantiates a new load game info.
     *
     * @param args the args
     */
    public LoadGameInfo(String... args) {
        response = new LoadGameInfoResponse();
        request = new LoadGameInfoRequest(args);
    }

    /**
     * Load the saved games for the input tag in UI.
     *
     * @see com.soen.risk.boundary.Usecase#execute()
     */
    @Override
    public LoadGameInfoResponse execute() {
        Path parentPath = FileSystems.getDefault().getPath(".").toAbsolutePath();
        String relativePath = FileSystems.getDefault().getSeparator() + "saved-games";
        File directory = new File(parentPath + relativePath);
        logger.log(Level.INFO, Arrays.toString(directory.listFiles()));
        response.setFilenames(directory.listFiles());
        return response;
    }
}
