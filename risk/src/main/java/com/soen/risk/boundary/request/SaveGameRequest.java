package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

/**
 * @author Hina
 *
 */
public class SaveGameRequest implements Request {
    private String filename;

    public SaveGameRequest(String... args){
        filename = args[0];
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
