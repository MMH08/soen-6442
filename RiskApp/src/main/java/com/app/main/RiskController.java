/**
 * 
 */
package com.app.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author fly
 *
 */
@Controller
public class RiskController 
{
	
	private static List<Continent> continents = new ArrayList<Continent>();

	static {
		continents.add(new Continent(234, "Asia"));
		continents.add(new Continent(456, "Aus"));
		continents.add(new Continent(45678, "Eur") );
		continents.add(new Continent(76, "Africa"));
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView get() {
		
		ContinentForm continentForm = new ContinentForm();
		continentForm.setContinents(continents);
		System.out.println("in 1");
		
		return new ModelAndView("index" , "continentForm", continentForm);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("continentForm") ContinentForm continentForm)
	{
		/*System.out.println("In controller");
		System.out.println(continentForm.getContinents());*/
		List<Continent> continents = continentForm.getContinents();
		
		/*if(null != continents && continents.size() > 0) 
		{
			RiskController.continents = continents;
			for (Continent continent : continents) {
				System.out.println( continent.getControlValue()+","+continent.getContinentName());
			}
		}*/
		
		return new ModelAndView("cont", "continentForm", continentForm);
	}
	
	
	@RequestMapping(value = "/saveCountries", method = RequestMethod.POST)
	public ModelAndView saveCountries(@ModelAttribute("continentForm") ContinentForm continentForm)
	{
		
		List<Continent> continents = continentForm.getContinents();
		List<Country> countries=continentForm.getCountryList();
		
		List<String> tempcontNames=new ArrayList<>();
		List<String> tempcountNames=new ArrayList<>();
		ArrayList<String> tempcountryNames=new ArrayList<>();
		LinkedHashMap<String, String> tempMap=new LinkedHashMap<>();
		String[] strArray=new String[100];
		
		
		//Extracting continents and countries
		for(Continent cont:continents)
		{
			tempcontNames.add(cont.getContinentName());
		}
		for(Country cont:countries)
		{
			tempcountNames.add(cont.getCountryName()); 
			strArray=(cont.getCountryName()).split(",");
			for (String s:strArray)
			{
				tempcountryNames.add(s); //Getting all countryNames for dropdown
			}
			
		}
		
		//Pushing both lists into Map listOfCountries
		for (int i=0; i<tempcontNames.size(); i++) 
		{
			tempMap.put(tempcontNames.get(i), tempcountNames.get(i));   
			
		}
		
		
		/*for (String l:tempcountryNames)
		{
			System.out.println(l+"*");
		}*/
		
		
		
		
		continentForm.setListOfCountries(tempMap);
		continentForm.setCountryNames(tempcountryNames);
		
		return new ModelAndView("adj_countries", "continentForm", continentForm);
	}
	
	@RequestMapping(value = "/addAdj", method = RequestMethod.POST)
	public ModelAndView saveAdjCountries(@ModelAttribute("continentForm") ContinentForm continentForm)
	{
		List<String> countryNames = continentForm.getCountryNames();
		List<Country> countries=continentForm.getCountryList();
		
		List<String> tempcountNames=new ArrayList<>();
		LinkedHashMap<String, String> tempMap=new LinkedHashMap<>();
		
		for(Country c:countries)
		{
			tempcountNames.add(c.getCountryName());
		}
		
		for (int i=0; i<countryNames.size(); i++) 
		{
			tempMap.put(countryNames.get(i), tempcountNames.get(i));   
			System.out.println(countryNames.get(i)+"-->"+tempcountNames.get(i));
			
		}
		
		continentForm.setAdjCountries(tempMap);
		
		return new ModelAndView("success", "continentForm", continentForm);
		
	}
	
	
	
	
	
	@RequestMapping("/countries")
	public String getCountries(@RequestParam("country") String[] country, Model model) 
	
	{
		model.addAttribute("country", country);
		return "country";
	   }
	
	  
	
	
}
