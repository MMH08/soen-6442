package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

/**
 * The Class SaveGameRequest.
 *
 * @author Hina
 */
public class SaveGameRequest implements Request {
    
    /** The filename. */
    private String filename;

    /**
     * Instantiates a new save game request.
     *
     * @param args the args
     */
    public SaveGameRequest(String... args){
        filename = args[0];
    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets the filename.
     *
     * @param filename the new filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
