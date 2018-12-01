package com.soen.risk.entity.player.cheater;

import com.soen.risk.entity.AttackStrategy;
import com.soen.risk.entity.Country;
import com.soen.risk.entity.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CheaterAttackStrategyTest {

    private Map map;
    private AttackStrategy cheater;
    private Country country1;
    private Country country4;
    private Country country2;
    private Country country3;

    @Before
    public void setUp() {
        map = new Map();
        map.load("./fixture/demo.map");
        cheater = new CheaterAttackStrategy();
        country1 = map.findByCountryName("Country1");
        country1.setArmy(10);
        country2 = map.findByCountryName("Country2");
        country2.setArmy(20);
        country3 = map.findByCountryName("Country3");
        country3.setArmy(40);
        country4 = map.findByCountryName("Country4");
        country4.setArmy(30);

    }

    @Test
    public void ZeroCountry_ShouldChangeNothing() {
        cheater.execute(map, new ArrayList<>());
        assertEquals(new ArrayList(), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

    @Test
    public void OneCountry_ShouldCaptureNeighbor(){
        cheater.execute(map, Collections.singletonList(country2));
        assertEquals(Collections.singletonList(country1), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

    @Test
    public void OneCountry_ShouldCaptureAllNeighbor(){
        cheater.execute(map, Collections.singletonList(country1));
        assertEquals(Arrays.asList(country2, country3), cheater.getWon());
        assertEquals(new HashMap<>(), cheater.getLost());
    }

}
