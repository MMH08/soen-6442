package com.soen.risk.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;
/**
 * The Class MapTest.
 */
public class MapTest {

	/** The map. */
	static Map map,map1,map2,map3,map4;
	
	/** The Filename. */
	//String path = "/home/varun/Downloads/001_I72_Ghtroc 720/";
	String path = "/home/varun/Downloads/";
    String Filename = path + "001_I72_Ghtroc 720.map";
	String invalidFile = path + "invalid.map";
    /**
     * Adds the country.
     */
    @Test
    public void addCountry() {
        Country country = new Country(13, "Africa");
        map.addCountry(country);
        assertNotNull(map.getCountries());
        //assertEquals(map.get);

    }

    /**
     * Load.
     */
    @Test
    public void load() {
        map.load(Filename);
        assertEquals(8, map.getContinents().size());
        assertEquals(99, map.getCountries().size());
    }
    @BeforeClass
    public static void set()
    {
    	map1 = new Map();
    	Country c1= new Country(0,"A");
    	Country c2 = new Country(1,"B");
    	Country c3 = new Country(2,"C");
    	Country c4 = new Country(3,"D");
    	Country c5= new Country(4,"B");
    	map1.addCountry(c1);
    	map1.addCountry(c2);
    	map1.addCountry(c3);
    	map1.addCountry(c4);
    	map1.addCountry(c5);
    	
    	map2 = new Map();
    	Continent c11= new Continent("A");
    	c11.setControlValue(11);
    	Continent c22= new Continent("B");
    	c22.setControlValue(13);
    	Continent c33= new Continent("C");
    	c33.setControlValue(15);
    	Continent c44= new Continent("D");
    	c44.setControlValue(17);
    	Continent c55= new Continent("B");
    	c55.setControlValue(18);
    	map2.addContinent(c11);
    	map2.addContinent(c22);
    	map2.addContinent(c33);
    	map2.addContinent(c44);
    	map2.addContinent(c55);
    	
    	map3 = new Map();
    	Country c111 = new Country(0,"A");
    	Country c222 = new Country(1,"B");
    	Country c333 = new Country(2,"C");
    	Country c444 = new Country(3,"D");
    	Country c555 = new Country(4,"E");
    	map3.addCountry(c111);
    	map3.addCountry(c222);
    	map3.addCountry(c333);
    	map3.addCountry(c444);
    	map3.addCountry(c555);
    	String s[] = {c111.getName(),c222.getName(),c333.getName(),c444.getName()};
    	map3.map_name_creation(s);
    	String s1[] = {c222.getName(),c333.getName(),c444.getName(),c111.getName()};
    	String s2[] = {c333.getName(),c444.getName(),c111.getName(),c222.getName()};
    	String s3[] = {c444.getName(),c333.getName(),c222.getName(),c111.getName()};
    	String s4[] = {c555.getName()};
    	map3.map_name_creation(s1);
    	map3.map_name_creation(s2);
    	map3.map_name_creation(s3);
    	map3.map_name_creation(s4);
    	map3.map_country_object_creation();
    	map4 = new Map();
    }
    /**
     * Sets the up.
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception {
    	map = new Map();
    	
    }

   

    /**
     * Adds the continent.
     */
    @Test
    public void addContinent() {
    	Continent c = new Continent("Cont-Cont");
    	c.setControlValue(3);
    	map.addContinent(c);
    	assertNotNull(map.getContinents());
    }


    /**
     * Find by country name.
     */
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

    /**
     * Find by continent name.
     */
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


    /**
     * Map name creation.
     */
    @Test
    public void map_name_creation() {
    	map.load(Filename);
    	assertNotNull(map.getListCountry());
    }
    

    /**
     * Map country object creation.
     */
    @Test
    public void map_country_object_creation() {
    	map.load(Filename);
    	map.map_country_object_creation();
    	assertNotNull(map.getAdjCountry());
    }

    /**
     * Checks if is valid country duplicacy.
     */
    @Test
    public void isValidCountryDuplicacy() {
    	
    	assertTrue(map1.checkCountryDuplicacy());
    }
   
    /**
     * Checks if is valid continent duplicacy.
     */
    @Test
    public void isValidContinentDuplicacy() {
    	
    	assertTrue(map2.checkContinentDuplicacy());
    }

    /**
     * Checks if is valid isolated country.
     */
    @Test
    public void isValidIsolatedCountry()
    {
    	
    	assertTrue(map3.checkIsolatedCountry());

    }
    @Test
    public void isValid()
    {
    	map4.load(invalidFile);
    	assertFalse(map4.isValid());
    }
}