package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class LoadGameInfoResponse.
 */
public class LoadGameInfoResponse implements Response {

    /** The filenames. */
    private List<String> filenames;

    /**
     * Instantiates a new load game info response.
     */
    public LoadGameInfoResponse() {
        filenames = new ArrayList<>();
    }

    /**
     * Gets the filenames.
     *
     * @return the filenames
     */
    public List<String> getFilenames() {
        return filenames;
    }

    /**
     * Adds the filename.
     *
     * @param filename the filename
     */
    private void addFilename(String filename) {
        this.filenames.add(filename);
    }

    /**
     * Sets the filenames.
     *
     * @param filenames the new filenames
     */
    public void setFilenames(File[] filenames) {
        for (File filename : filenames) {
            addFilename(filename.getName());
        }
    }
}
