package main;

public class Ias {

	private String name;
	private String language;
	private int threat;
	private String debility;
	private String country;
	private String precision;
	private String type;
	private int id;
	
	public Ias(String name, String language, int threat, String debility, String country, String precision,
			String type, int id) {
		super();
		this.name = name;
		this.language = language;
		this.threat = threat;
		this.debility = debility;
		this.country = country;
		this.precision = precision;
		this.type = type;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getThreat() {
		return threat;
	}
	public void setThreat(int threat) {
		this.threat = threat;
	}
	public String getDebility() {
		return debility;
	}
	public void setDebility(String debility) {
		this.debility = debility;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Nombre= " + name + ", Lenguaje= " + language + ", Nivel de Amenaza= " + threat + " debilidad= "
				+ debility + "\n Pais de origen= " + country + ", Precision de la ia= " + precision + ", Tipo de ia= " + type + "\n id=" + id;
	}
}
