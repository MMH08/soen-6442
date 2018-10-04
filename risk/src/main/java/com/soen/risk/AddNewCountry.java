import java.util.*;
public class AddNewCountry {
	
	String country;
	String continent;
	public void addCountry()
	{
		Scanner s1 = new Scanner(System.in);
		country = "";
		continent = "";
		System.out.println("Enter Country Name");
		country = country + s1.nextLine();
		
		System.out.println("Enter coordinate X");
		country = country + ',' + s1.nextLine();
		
		System.out.println("Enter Coordinate Y");
		country = country + ',' + s1.nextLine();
		
		System.out.println("Enter Continent");
		String temp = s1.nextLine();
		country = country + ',' + temp;
		continent = temp;
		
		System.out.println("Enter Control Value of Continent");
		String temp1 = s1.nextLine();
		continent = continent + "=" + temp1;
		
		System.out.println("Enter Neighbouring Countries");
		Scanner s2 = new Scanner(System.in);
		while(true)
		{
			System.out.println("Would you like to enter country 1.Yes 0.No");
			int option = s2.nextInt();
			if(option == 1)
			{
				Scanner s3 = new Scanner(System.in);
				System.out.println("Enter Country");
				String temp_con = s3.nextLine();
				country = country + ',' + temp_con;
			}
			else
			{
				break;
			}
		}
	}
	public String getNewCountry()
	{
		return country;
	}
	public String getNewContinent()
	{
		return continent;
	}
}
