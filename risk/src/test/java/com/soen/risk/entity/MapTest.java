package com.soen.risk.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MapTest {


    @Test
    public void addCountry() {
        Map map = new Map();
        Country country = new Country(13, "Africa");
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

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addContinent() {
    }


    @Test
    public void connect() {
    }

    @Test
    public void findByCountryName() {
    }

    @Test
    public void findByContinentName() {
    }

    @Test
    public void save() {
    }

    @Test
    public void map_name_creation() {
    }

    @Test
    public void map_country_object_creation() {
    }

    @Test
    public void isValid() {
    }
}