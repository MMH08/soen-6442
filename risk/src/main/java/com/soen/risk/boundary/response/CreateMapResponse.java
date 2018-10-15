package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

public class CreateMapResponse implements Response {
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
