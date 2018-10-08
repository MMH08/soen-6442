package com.soen.risk.interactor.phase;

import com.soen.risk.entity.*;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

public class StartupPhase extends Phase {

    public StartupPhase(){
        this.setName("startupPhase");
    }

    public void execute(String countryName, int armyCount) {
       Map m = GamePlay.getInstance().getGame().getMap();
       for(Country c: m.getCountries())
       {
    	   if(c.getName().equals(countryName))
    	   {
    		   c.setArmy(armyCount);
    		   break;
    	   }
       }

    }
}
