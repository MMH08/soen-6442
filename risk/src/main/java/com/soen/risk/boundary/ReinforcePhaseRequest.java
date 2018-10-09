package com.soen.risk.boundary;

import java.util.HashMap;
import java.util.StringTokenizer;

public class ReinforcePhaseRequest {
    private String countryName;
    private int armyCount;
    HashMap<String,Integer> hm;
    
    public ReinforcePhaseRequest(String countryNames, String armyCounts){
  
    	StringTokenizer countryNameToken = new StringTokenizer(countryNames, ",");
    	StringTokenizer armyCountToken = new StringTokenizer(armyCounts, ",");
    	hm=new HashMap<String,Integer>();  
        while (countryNameToken.hasMoreTokens()) {
        	while (armyCountToken.hasMoreTokens())
        	{
        		hm.put(countryNameToken.nextToken(), Integer.parseInt(armyCountToken.nextToken()));
        	
        	}         
        }
    }

    public HashMap<String,Integer> getMap()
    {
    	return hm;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void setArmyCount(int armyCount) {
        this.armyCount = armyCount;
    }
}
