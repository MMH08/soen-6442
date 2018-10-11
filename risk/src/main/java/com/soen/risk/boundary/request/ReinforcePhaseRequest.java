package com.soen.risk.boundary.request;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReinforcePhaseRequest {
    private ArrayList<Integer> armyCounts;

    public ReinforcePhaseRequest(String... args) {
        armyCounts = new ArrayList<>();
        StringTokenizer armyCountToken = new StringTokenizer(args[0], ",");
        while (armyCountToken.hasMoreTokens()) {
            armyCounts.add(Integer.parseInt(armyCountToken.nextToken()));
        }
    }

    public ArrayList<Integer> getArmyCounts() {
        return armyCounts;
    }
}
