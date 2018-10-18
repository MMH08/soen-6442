package com.soen.risk.entity;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void addContinent() {
    }

    @Test
    public void connect() {
    }

    @Test
    public void addCountry() {
    }

    @Test
    public void load() {
        Map map = new Map();
        map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map");
        assertEquals(8, map.getContinents().size());
        assertEquals(99, map.getCountries().size());
    }

    @Test
    public void save() {
    }
    
    @Test
  public void isValid(){
  	//To check continent and country duplication
      Map map = new Map();
      map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map"); 
      boolean bvalid = map.isValid();
      assertFalse(bvalid);
  }
  @Test
  public void isValid2(){
  	//To check if all adjacent countries are valid countries
      Map map = new Map();
      map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map"); 
      boolean bvalid = map.isValid();
      assertFalse(bvalid);
  }
  @Test
  public void isValid3() {
  	//To check if no country is isolated
      Map map = new Map();
      map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map"); 
      boolean bvalid = map.isValid();
      assertFalse(bvalid);
  }
  @Test
  public void isValid4(){
  	//Perfect map
      Map map = new Map();
      map.load("/home/varun/Downloads/001_I72_Ghtroc 720/001_I72_Ghtroc 720.map");
      boolean bvalid = map.isValid();
      assertTrue(bvalid);																																									
  }

}