package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class CreateMapResponse.
 */
public class CreateMapResponse implements Response {
    
    /** The file path. */
    private String filePath;

    /**
     * Gets the file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the file path.
     *
     * @param filePath the new file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
