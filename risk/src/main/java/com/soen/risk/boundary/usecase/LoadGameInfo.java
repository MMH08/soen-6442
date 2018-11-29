package com.soen.risk.boundary.usecase;

import com.soen.risk.boundary.Usecase;
import com.soen.risk.boundary.request.LoadGameInfoRequest;
import com.soen.risk.boundary.response.LoadGameInfoResponse;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;

public class LoadGameInfo implements Usecase {
    private LoadGameInfoRequest request;
    private LoadGameInfoResponse response;

    public LoadGameInfo(String... args) {
        response = new LoadGameInfoResponse();
        request = new LoadGameInfoRequest(args);
    }

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
