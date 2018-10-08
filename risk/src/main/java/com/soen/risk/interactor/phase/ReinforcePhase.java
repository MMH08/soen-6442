package com.soen.risk.interactor.phase;

import com.soen.risk.entity.*;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;
import java.lang.Math;
public class ReinforcePhase extends Phase {

    public ReinforcePhase(){
        this.setName("reinforcePhase");
    }

    public void execute(String countryName, int armyCount) {
    	 Map m = GamePlay.getInstance().getGame().getMap();
         for(Country c: m.getCountries())
         {
      	   if(c.getName().equals(countryName))
      	   {
      		   c.setArmy(c.getArmy() + armyCount);
      		   break;
      	   }
         }
    }
    
    public int allocate_extra_army()
    {
    	//Check if player has all country of a continent
    	Map m = GamePlay.getInstance().getGame().getMap();
    	Player p = GamePlay.getInstance().getCurrentPlayer();
    	for(Continent ctt: m.getContinents())
    	{
    		int size = ctt.getCountries().size(); 
    		int count = 0;
    		for(Country player_countries: p.getCountries())
			{
    			for(Country continent_countries: ctt.getCountries())
    			{
	    			if(continent_countries.getName().equals(player_countries.getName()))
	    				{
	    					count++;
	    				}
    			}
    		}
    		if(size == count)
    		{
    			return ctt.getControlValue();    			
    		}   		
    		
    	}
    	
    	//If Player do not have all country of a continent
    	int number_of_countries = p.getCountries().size();
    	return (int) Math.ceil(number_of_countries/3.0);
    	
    }
}
