package com.soen.risk.boundary.response;

import com.soen.risk.boundary.Response;

/**
 * The Class PhaseResolverResponse.
 */
public class PhaseResolverResponse implements Response {
    
    /** The phase name. */
    private String phaseName;

    /**
     * Gets the phase name.
     *
     * @return the phase name
     */
    public String getPhaseName() {
        return phaseName;
    }

    /**
     * Sets the phase name.
     *
     * @param phaseName the new phase name
     */
    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }
}
