package com.soen.risk.boundary.request;

import com.soen.risk.boundary.Request;

public class EditMapRequest implements Request {
    private String fileName;

    public EditMapRequest(String... args){
            setFileName(args[0]);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
