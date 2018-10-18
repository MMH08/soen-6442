package com.soen.risk.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.util.Assert;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapTest {

	Map map;
	String Filename = "/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map";
    @Test
    public void addCountry() {
        Country country = new Country(13, "Africa");
        map.addCountry(country);
        assertNotNull(map.getCountries());
        //assertEquals(map.get);

    }

    @Test
    public void load() {
        map.load(Filename);
        assertEquals(8, map.getContinents().size());
        assertEquals(99, map.getCountries().size());
    }

    @Before
    public void setUp() throws Exception {
    	map = new Map();
    }

    @After
    public void tearDown() throws Exception {
    	
    }

    @Test
    public void addContinent() {
    	Continent c = new Continent("Cont-Cont");
    	c.setControlValue(3);
    	map.addContinent(c);
    	assertNotNull(map.getContinents());
    }


    @Test
    public void findByCountryName() {
    	Country country = new Country(0, "Africa");
    	Country country1 = new Country(1, "India");
    	Country country2 = new Country(2, "Bangladesh");
        map.addCountry(country);
        map.addCountry(country1);
        map.addCountry(country2);
        assertNotNull(map.getCountries());
    	assertEquals(country1, map.findByCountryName("India"));
    }

    @Test
    public void findByContinentName() {
    	Continent c = new Continent("Cont-Cont");
    	c.setControlValue(3);
    	Continent c1 = new Continent("Cont-Cont2");
    	c1.setControlValue(4);
    	Continent c2 = new Continent("Cont-Cont3");
    	c2.setControlValue(5);
    	map.addContinent(c);
    	map.addContinent(c1);
    	map.addContinent(c2);
    	assertNotNull(map.findByContinentName("Cont-Cont2"));
    }


    @Test
    public void map_name_creation() {
    	map.load(Filename);
    	assertNotNull(map.getListCountry());
    }
    

    @Test
    public void map_country_object_creation() {
    	map.load(Filename);
    	map.map_country_object_creation();
    	assertNotNull(map.getAdjCountry());
    }

    @Test
    public void isValidCountryDuplicacy() {
    	Country c1= new Country(0,"A");
    	Country c2 = new Country(1,"B");
    	Country c3 = new Country(2,"C");
    	Country c4 = new Country(3,"D");
    	Country c5= new Country(4,"B");
    	map.addCountry(c1);
    	map.addCountry(c2);
    	map.addCountry(c3);
    	map.addCountry(c4);
    	map.addCountry(c5);
    	assertTrue(map.checkCountryDuplicacy());
    }
    
    @Test
    public void isValidContinentDuplicacy() {
    	Continent c1= new Continent("A");
    	c1.setControlValue(11);
    	Continent c2= new Continent("B");
    	c2.setControlValue(13);
    	Continent c3= new Continent("C");
    	c3.setControlValue(15);
    	Continent c4= new Continent("D");
    	c4.setControlValue(17);
    	Continent c5= new Continent("B");
    	c5.setControlValue(18);
    	map.addContinent(c1);
    	map.addContinent(c2);
    	map.addContinent(c3);
    	map.addContinent(c4);
    	map.addContinent(c5);
    	assertTrue(map.checkContinentDuplicacy());
    }
    
    @Test
    public void isValidIsolatedCountry()
    {
    	Country c1 = new Country(0,"A");
    	Country c2 = new Country(1,"B");
    	Country c3 = new Country(2,"C");
    	Country c4 = new Country(3,"D");
    	Country c5 = new Country(4,"E");
    	map.addCountry(c1);
    	map.addCountry(c2);
    	map.addCountry(c3);
    	map.addCountry(c4);
    	map.addCountry(c5);
    	String s[] = {c1.getName(),c2.getName(),c3.getName(),c4.getName()};
    	map.map_name_creation(s);
    	String s1[] = {c2.getName(),c3.getName(),c4.getName(),c1.getName()};
    	String s2[] = {c3.getName(),c4.getName(),c1.getName(),c2.getName()};
    	String s3[] = {c4.getName(),c3.getName(),c2.getName(),c1.getName()};
    	String s4[] = {c5.getName()};
    	map.map_name_creation(s1);
    	map.map_name_creation(s2);
    	map.map_name_creation(s3);
    	map.map_name_creation(s4);
    	map.map_country_object_creation();
    	assertTrue(map.checkIsolatedCountry());
    	
    }
}