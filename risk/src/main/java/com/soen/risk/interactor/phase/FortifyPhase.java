package com.soen.risk.interactor.phase;

import java.util.*;

import com.soen.risk.entity.Country;
import com.soen.risk.entity.Player;
import com.soen.risk.interactor.GamePlay;
import com.soen.risk.interactor.Phase;

public class FortifyPhase implements Phase {
    private String name;

    public FortifyPhase() {
        this.name = "fortifyPhase";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void begin() {
    	Player p = GamePlay.getInstance().getCurrentPlayer();
    	if(p.getCountries().size() == 0)
    	{
    		GamePlay.getInstance().getGame().dropPlayer(p);    		
    	}

    }

    @Override
    public void execute() {

    }

    @Override
    public void exit() {

    }

    @Override
    public String getName() {
        return this.name;
    }
    public void createCountryArray()
    {
    	LinkedList<LinkedList> ll = GamePlay.getInstance().getGame().getMap().getAdjCountry();
    	LinkedList<Integer> adj[] = new LinkedList[ll.size()];
    	for(int i=0;i<ll.size();i++)
    	{
    		adj[i] = new LinkedList();
    	}
    	for(LinkedList<Country> ll1 : ll)
    	{
    		int index = ll1.get(0).getId();
    		int j=0;
    		for(Country c: ll1)
    		{
    			if(j!=0)
    			{
    				adj[index].add(c.getId());
    			}
    			j++;
    			
    		}
    	}
    	
    }
}
