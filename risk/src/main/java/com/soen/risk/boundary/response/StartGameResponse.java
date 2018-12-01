package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class StartGameResponse.
 */
public class StartGameResponse implements Response {
    private boolean isValid;

    public StartGameResponse(){

    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
