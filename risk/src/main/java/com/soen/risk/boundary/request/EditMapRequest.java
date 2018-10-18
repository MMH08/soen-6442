package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

/**
 * The Class EditMapRequest.
 */
public class EditMapRequest implements Request {
    
    /** The file name. */
    private String fileName;

    /**
     * Instantiates a new edits the map request.
     *
     * @param args the args
     */
    public EditMapRequest(String... args){
            setFileName(args[0]);
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
