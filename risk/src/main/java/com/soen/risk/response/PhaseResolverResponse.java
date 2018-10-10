package com.soen.risk.response;

import com.soen.risk.boundary.Response;

public class PhaseResolverResponse implements Response {
    private String phaseName;

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }
}
