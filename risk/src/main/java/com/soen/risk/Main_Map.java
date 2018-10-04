//Pending
/*upper vla sman nu file kita store krke rkhna h
[Map]
author=_ MIKE CRUSH _
image=_65_ PLANET BUSTER.bmp
wrap=yes
scroll=none
warn=yes
*/

//Avoid count go for something else in reading map
//Check Duplicacy of country while adding
//Check if map is empty

import java.io.*;
import java.util.*;
public class Main_Map {
	ArrayList<Continent> continent_list;
	ArrayList<Country> country_list;
	ArrayList<String> temp_data;
	int continent_count = 0;
	int country_count=0;
	LinkedList list_country;
	LinkedList country_object;
	public Main_Map(){
		continent_list = new ArrayList<Continent>();
		country_list = new ArrayList<Country>();
		continent_count = 0;
		country_count = 0;
		list_country = new LinkedList<LinkedList>();
		
	}
	// Reading file and creating map
	public void reading_map(String filePath)
	{
		
		try {
			Scanner reading_file = new Scanner(new FileReader(filePath));
			int count =0;
			int flag_continent = 0, flag_country = 0;
			while(reading_file.hasNext())
			{
				
				String temp = reading_file.nextLine();
				if(count == 2)
				{
					flag_continent = 0;
				}
				
				if(!temp.equals("") && flag_continent == 1)
				{
					this.addNewContinent(temp);					
										
				}
				else if(flag_country == 1)
				{
					this.addNewCountry(temp);
				}
				
				if(temp.equals(""))
				{
					count++;
				}
				else if(temp.equals("[Continents]"))
				{
					flag_continent = 1;
				}
				else if(temp.equals("[Territories]"))
				{
					flag_country = 1;
				}
				
				
			}
			reading_file.close();
			this.create_map_object_function();
			this.print_map();
		}
		catch(Exception e)
		{
			System.out.println("System Error");
		}
		
	}
	
	//Add continent in Linked List
	public void addNewContinent(String temp)
	{
		String split_continent[] = temp.split("=");
		if(this.checkContinentDuplicacy(split_continent[0]))
		{
			return;
		}
		Continent cont = new Continent(continent_count,split_continent[0]);
		cont.setControlValue(Integer.valueOf(split_continent[1]));
		continent_list.add(cont);
		continent_count++;
	}
	public boolean checkContinentDuplicacy(String con)
	{
		Iterator il = continent_list.iterator();
		while(il.hasNext())
		{
			Continent c1 = (Continent)il.next();
			if(c1.getName().equals(con))
			{
				return true;
			}
		}
		return false;
	}
	
	
	//Add country in Linked List
	public void addNewCountry(String temp)
	{
		String split_country[] = temp.split(",");
		Country coun = new Country(country_count,split_country[0]);
		coun.setCoordinateX(split_country[1]);
		coun.setCoordinateY(split_country[2]);
		Iterator il = continent_list.iterator();
		String temp_continent_name = split_country[3];
		while(il.hasNext())
		{
			Continent temp_cont = (Continent)il.next();
			if(temp_cont.getName().equals(temp_continent_name))
			{
				temp_cont.addCountry(coun);
				break;
			}
		}
		country_list.add(coun);
		String arr[] = new String[split_country.length-3];
		arr[0] = split_country[0];
		for(int i=4;i<split_country.length;i++)
		{
			arr[i-3] = split_country[i];
		}
		this.map_name_creation(arr);
		country_count++;
	}
	
	
	
	//Function to create 2D linked List on basis of country name;
	public void map_name_creation(String s[])
	{
		LinkedList temp_list = new LinkedList<String>();	
		
		int i = 0;
		while(i<s.length)
		{
			temp_list.add(s[i]);
			i++;
		}
		list_country.add(temp_list);
	}
	
	//Function to intialize map country object creation
	public void create_map_object_function()
	{
		country_object = new LinkedList<LinkedList>();
		this.map_country_object_creation();
	}
	
	//Function to create 2D linked list on basis of country object
	Country retrieve_country_object(String s)
	{
		Iterator il = country_list.iterator();
		while(il.hasNext())
		{
			Country c = (Country)il.next();
			if(c.getName().equals(s))
			{
				return c;
			}
		}
		return null;
	}
	void map_country_object_creation()
	{
		
		Iterator il = list_country.iterator();
		while(il.hasNext())
		{
			LinkedList l1 = (LinkedList)il.next();
			LinkedList temp = new LinkedList<Country>();
			Iterator il2 = l1.iterator();
			while(il2.hasNext())
			{
				String s = (String)il2.next();
				temp.add(retrieve_country_object(s));
			}
			country_object.add(temp);
		}
		
	}
		
	// Just to print map using country object Linked List
	void print_map()
	{
		System.out.println("Map");
		Iterator il = country_object.iterator();
		while(il.hasNext())
		{
			LinkedList l1 = (LinkedList)il.next();
			Iterator il2 = l1.iterator();
			while(il2.hasNext())
			{
				Country c = (Country)il2.next();
				System.out.print(c.getName());
			}
			System.out.println();
		}
		
		System.out.println("Continent");
		il = continent_list.iterator();
		while(il.hasNext())
		{
			Continent c1 = (Continent)il.next();
			System.out.println(c1.getName());
		}
	}
	
	//Get map on basis of name
	public LinkedList getMap()
	{
		return list_country;
	}
	
	//Get map on basis of country object
	public LinkedList getMapCountryObject()
	{
		return country_object;
	}
	
	public int getCountriesCount()
	{
		return country_count;
	}
	
	public int getContinentCount()
	{
		return continent_count;
	}
	
	
	
}
