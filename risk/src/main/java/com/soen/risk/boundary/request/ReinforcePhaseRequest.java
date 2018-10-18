package com.soen.risk.boundary.request;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The Class ReinforcePhaseRequest.
 */
public class ReinforcePhaseRequest {
    
    /** The army counts. */
    private ArrayList<Integer> armyCounts;

    /**
     * Instantiates a new reinforce phase request.
     *
     * @param args the args
     */
    public ReinforcePhaseRequest(String... args) {
        armyCounts = new ArrayList<>();
        StringTokenizer armyCountToken = new StringTokenizer(args[0], ",");
        while (armyCountToken.hasMoreTokens()) {
            armyCounts.add(Integer.parseInt(armyCountToken.nextToken()));
        }
    }

    /**
     * Gets the army counts.
     *
     * @return the army counts
     */
    public ArrayList<Integer> getArmyCounts() {
        return armyCounts;
    }
}
