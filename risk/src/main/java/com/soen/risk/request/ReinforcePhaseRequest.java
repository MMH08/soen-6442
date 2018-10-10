package com.soen.risk.request;

import java.util.HashMap;
import java.util.StringTokenizer;

public class ReinforcePhaseRequest {
    private HashMap<String, Integer> hm;

    public ReinforcePhaseRequest(String countryNames, String armyCounts) {

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
