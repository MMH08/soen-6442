import java.util.*;


public class Continent {
	private String name;
	private int controlValue;
	private List<Country> countries;
	private int id;

	// -------------------------------------------------------------

	public Continent(int id, String name) {
		this.id = id;
		this.name = name;
		this.countries = new ArrayList<>();
	}

	public void addCountry(Country country) {
		this.countries.add(country);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	// -------------------------------------------------------------


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getControlValue() {
		return controlValue;
	}

	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
