import java.util.*;
public class APPProject {
	static Main_Map m;
	public static void loading_map() {
		m = new Main_Map();
		m.reading_map("\\X:\\Masters of Applied computer Science\\Fall 2018\\Advance Programming Practices\\Project\\world_map.map");
	}
	
	public static Main_Map get_Map_Object()
	{
		return m;
	}
	
	public void add_Country_by_User()
	{
		Scanner s1 = new Scanner(System.in);
		AddNewCountry a1 = new AddNewCountry();
		System.out.println("**************Start Adding Country*****************");
		while(true)
		{
			a1.addCountry();
			m.addNewContinent(a1.getNewContinent());
			m.addNewCountry(a1.getNewCountry());
			System.out.println("Want to Add more Country 1.Yes 0.No");
			int option = s1.nextInt();
			if(option == 0)
				break;
		}	
		m.create_map_object_function();
		m.print_map();
	}
	public static void main(String[] args) {
		
		APPProject app_project = new APPProject();
		app_project.loading_map();
		app_project.add_Country_by_User();
	}

}
