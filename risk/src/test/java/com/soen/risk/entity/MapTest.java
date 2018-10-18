package com.soen.risk.entity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MapTest {

    @Test
    public void addContinent() {
    }

    @Test
    public void connect() {
    }

    @Test
    public void addCountry() 
    {
    	Map map=new Map();
    	Country country=new Country(13,"Africa");
    	map.addCountry(country);
    	assertNotNull(map.getCountries());
    	//assertEquals(map.get);
    	
    }

    @Test
    public void load() {
        Map map = new Map();
        map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map");
        assertEquals(8, map.getContinents().size());
        assertEquals(99, map.getCountries().size());
    }

    @Test
    public void save()
    {
        

    }
}