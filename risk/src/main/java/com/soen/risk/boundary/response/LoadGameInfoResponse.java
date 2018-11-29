package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadGameInfoResponse implements Response {

    private List<String> filenames;

    public LoadGameInfoResponse() {
        filenames = new ArrayList<>();
    }

    public List<String> getFilenames() {
        return filenames;
    }

    private void addFilename(String filename) {
        this.filenames.add(filename);
    }

    public void setFilenames(File[] filenames) {
        for (File filename : filenames) {
            addFilename(filename.getName());
        }
    }
}
