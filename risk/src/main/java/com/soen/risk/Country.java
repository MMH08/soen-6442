
public class Country {
	private String name;
	private String coordinateX;
	private String coordinateY;
	private int army;
	private int id;
	// -------------------------------------------------------------
	public Country(int id, String name) {
		this.setName(name);
		this.setId(id);
	}
	
	// -------------------------------------------------------------
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}
	public int getArmy() {
		return army;
	}

	public void setArmy(int army) {
		this.army = army;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}

	public String getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
}
