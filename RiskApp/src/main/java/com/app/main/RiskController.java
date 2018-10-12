/**
 * 
 */
package com.app.main;

import java.util.ArrayList;
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
		
		/*if(null != continents && continents.size() > 0) 
		{
			RiskController.continents = continents;
			for (Continent continent : continents) {
				System.out.println( continent.getControlValue()+","+continent.getContinentName());
			}
		}*/
		
		return new ModelAndView("cont", "continentForm", continentForm);
	}
	
	
	
	
	/*@RequestMapping("/")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav=new ModelAndView("index");
		
		//mav.addObject("continentForm", new Continent());
		mav.addObject("continentForm", new ContinentForm());
		
		return mav;
	   }*/
	
	@RequestMapping("/cont")
	public String getContinents(@RequestParam("continent[]") String[] continent, Model model) 
	
	{
		System.out.println("In controller");
		model.addAttribute("continent", continent);
		return "cont";
	   }
	
	
	@RequestMapping("/countries")
	public String getCountries(@RequestParam("country") String[] country, Model model) 
	
	{
		model.addAttribute("country", country);
		return "country";
	   }
	
	  
	
	
}
