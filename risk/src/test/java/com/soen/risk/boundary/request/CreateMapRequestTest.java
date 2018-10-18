package com.soen.risk.boundary.request;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CreateMapRequestTest {


    private static CreateMapRequest cmr;

    @BeforeClass
    public static void setUp() throws Exception {
        cmr = new CreateMapRequest("3dmap", "Continent1,Continent2", "10,20", "Country1,Country2", "Continent1,Continent2", "Country2|Country1", "/home");
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        Assert.assertEquals("3dmap", cmr.getName());
    }

    @Test
    public void getContinents() {
        List<String> x = Arrays.asList("Continent1", "Continent2");
        Assert.assertArrayEquals(x.toArray(), cmr.getContinents().toArray());
    }

    @Test
    public void getCountries() {
        List<String> x = Arrays.asList("Country1", "Country2");
        Assert.assertArrayEquals(x.toArray(), cmr.getCountries().toArray());
    }

    @Test
    public void getControlValues() {
        List<Integer> x = Arrays.asList(10, 20);
        Assert.assertArrayEquals(x.toArray(), cmr.getControlValues().toArray());
    }

    @Test
    public void getCountryOwners() {
        List<String> x = Arrays.asList("Continent1", "Continent2");
        Assert.assertArrayEquals(x.toArray(), cmr.getCountryOwners().toArray());
    }

    @Test
    public void getConnections() {
        List<List<String>> x = Arrays.asList(Arrays.asList("Country2"), Arrays.asList("Country1"));
        Assert.assertArrayEquals(x.toArray(), cmr.getConnections().toArray());
    }
}