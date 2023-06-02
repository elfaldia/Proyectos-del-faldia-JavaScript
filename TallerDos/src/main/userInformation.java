package main;

public class userInformation {

	private int id;
	private String name, lastName;
	private int experience;
	private String country,city;
	
	public userInformation(int id, String name, String lastName, int experience, String county, String city) {
		
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.experience = experience;
		this.country = county;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String toString() {
		return "datosUsuario [id=" + id + ", nombre=" + name + ", apellido=" + lastName + ", añosExperiencia="
				+ experience + ", pais=" + country + ", ciudad=" + city + "]";
	}
}
