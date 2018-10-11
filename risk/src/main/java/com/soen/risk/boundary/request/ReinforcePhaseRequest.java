package com.soen.risk.boundary.request;

import java.util.HashMap;
import java.util.StringTokenizer;

public class ReinforcePhaseRequest {
    private HashMap<String, Integer> hm;
    private String countryNames;
    private String armyCounts;

    public ReinforcePhaseRequest(String... args) {
        this.countryNames = args[0];
        this.armyCounts = args[1];

        StringTokenizer countryNameToken = new StringTokenizer(countryNames, ",");
        StringTokenizer armyCountToken = new StringTokenizer(armyCounts, ",");
        hm = new HashMap<>();
        while (countryNameToken.hasMoreTokens()) {
            while (armyCountToken.hasMoreTokens()) {
                hm.put(countryNameToken.nextToken(), Integer.parseInt(armyCountToken.nextToken()));

            }
        }
    }

    public HashMap<String, Integer> getMap() {
        return hm;
    }
}
