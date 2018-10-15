package com.soen.risk.boundary.usecase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.soen.risk.entity.Continent;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Game;
import com.soen.risk.entity.Map;
import com.soen.risk.entity.Player;

public class EditMap {
	static Logger logger = Logger.getLogger(Game.class.getName());
	private List<Player> players;
	
	public ArrayList<String> Retrieve(String filename) {
        // Builder pattern - startup phase 1
        Map map = new Map();
        map.load(filename);
        return allCountries(map);
        
    }
	public ArrayList<String> RetrieveContinent(String filename) {
        // Builder pattern - startup phase 1
        Map map = new Map();
        map.load(filename);
        return allContinents(map);
        
    }
	public ArrayList<String> allCountries(Map map) {
        ArrayList<String> cnames = new ArrayList<>();
        for (Country country : map.getCountries()) {
        	System.out.println(country.getName());
        	cnames.add(country.getName());
            
        }
        return cnames;
    }
	
	public ArrayList<String> allContinents(Map map) {
        ArrayList<String> continentnames = new ArrayList<>();
        for (Continent continent : map.getContinents()) {
        	System.out.println(continent.getName());
        	continentnames.add(continent.getName());
            
        }
        return continentnames;
    }
	
	public String Update(String filename,String name,String value) {
		try
        {
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "", oldtext = "";
        while((line = reader.readLine()) != null)
            {
            oldtext += line + "\r\n";
        }
        reader.close();
        String newtext = oldtext.replaceAll(name, value);
        FileWriter writer = new FileWriter(filename);
        writer.write(newtext);
        writer.close();
    }
    catch (IOException ioe)
        {
        ioe.printStackTrace();
    }
        return "hi";
        
    }

}
