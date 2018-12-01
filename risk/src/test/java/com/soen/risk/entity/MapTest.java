package com.soen.risk.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class MapTest.
 *
 * @author Amit
 */
public class MapTest {

    /**
     * The map.
     */
    private Map map;

    /**
     * The country 0.
     */
    private Country country0;

    /**
     * The country 1.
     */
    private Country country1;

    /**
     * The country 2.
     */
    private Country country2;

    /**
     * The continent 0.
     */
    private Continent continent0;

    /**
     * The continent 1.
     */
    private Continent continent1;

    /**
     * The continent 2.
     */
    private Continent continent2;


    /**
     * Set up.
     */
    @Before
    public void setUp() {
        map = new Map();
        country0 = new Country(0, "country0");
        country1 = new Country(1, "country1");
        country2 = new Country(2, "country2");
        continent0 = new Continent("continent0");
        continent1 = new Continent("continent1");
        continent2 = new Continent("continent2");
    }

    /**
     * Adds the country should enter one country in map.
     */
    @Test
    public void AddCountry_ShouldEnterOneCountryInMap() {
        map.addCountry(country0);
        assertEquals(1, map.getCountries().size());

    }

    /**
     * Load file should add countries and continents.
     */
    @Test
    public void LoadFile_ShouldAddCountriesAndContinents() {
        map.load("./fixture/valid-map-1.map");
        assertTrue(map.isValid());
        assertEquals(2, map.getContinents().size());
        assertEquals(4, map.getCountries().size());
    }

    /**
     * Adds the continent should enter one continent in map.
     */
    @Test
    public void AddContinent_ShouldEnterOneContinentInMap() {
        map.addContinent(continent0);
        assertEquals(1, map.getContinents().size());
    }

    /**
     * Find by country name should return country object.
     */
    @Test
    public void FindByCountryName_ShouldReturnCountryObject() {
        map.addCountry(country0);
        map.addCountry(country1);
        map.addCountry(country2);
        assertEquals(country1, map.findByCountryName("country1"));
    }

    /**
     * Find by continent name should return continent object.
     */
    @Test
    public void findByContinentName_ShouldReturnContinentObject() {
        map.addContinent(continent0);
        map.addContinent(continent1);
        map.addContinent(continent2);
        assertEquals(continent1, map.findByContinentName("continent1"));
    }


    /**
     * Load map with zero country should return invalid map.
     */
    @Test
    public void LoadMapWithZeroCountry_ShouldReturnInvalidMap() {
        map.load("./fixture/invalid-zero-country.map");
        assertFalse(map.isValid());
    }

    /**
     * Load map with duplicate country should return invalid map.
     */
    @Test
    public void LoadMapWithDuplicateCountry_ShouldReturnInvalidMap() {
        map.load("./fixture/invalid-duplicate-country.map");
        assertFalse(map.isValid());
    }

    /**
     * Load map with duplicate continent should return invalid map.
     */
    @Test
    public void LoadMapWithDuplicateContinent_ShouldReturnInvalidMap() {
        map.load("./fixture/invalid-duplicate-continent.map");
        assertFalse(map.isValid());
    }

    /**
     * Load map with isolated country should return invalid map.
     */
    @Test
    public void LoadMapWithIsolatedCountry_ShouldReturnInvalidMap() {
        map.load("./fixture/invalid-isolated-country.map");
        assertFalse(map.isValid());
    }

    /**
     * Load map with disconnected subgraph should return invalid map.
     */
    @Test
    public void LoadMapWithDisconnectedSubgraph_ShouldReturnInvalidMap() {
        map.load("./fixture/invalid-disconnected-subgraph.map");
        assertFalse(map.isValid());
    }

    /**
     * Load 3 d cliff map should return valid map.
     */
    @Test
    public void Load3dCliffMap_ShouldReturnValidMap() {
        map.load("./fixture/3D Cliff.map");
        assertTrue(map.isValid());
    }

    /**
     * Load world map should return valid map.
     */
    @Test
    public void LoadWorldMap_ShouldReturnValidMap() {
        map.load("./fixture/World.map");
        assertTrue(map.isValid());
    }

    /**
     * Load twin volcano map should return invalid map.
     */
    @Test
    public void LoadTwinVolcanoMap_ShouldReturnInvalidMap() {
        map.load("./fixture/Twin Volcano.map");
        assertFalse(map.isValid());
    }
}