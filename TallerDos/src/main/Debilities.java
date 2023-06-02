package main;

public class Debilities {

	private String debility;
	private int hierarchy;
	
	public Debilities(String debilidad, int hierarchy) {
		super();
		this.debility = debilidad;
		this.hierarchy = hierarchy;
	}

	public String getDebility() {
		return debility;
	}

	public void setDebility(String debilidad) {
		this.debility = debilidad;
	}

	public int gethierarchy() {
		return hierarchy;
	}

	public void sethierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}

	public String toString() {
		return "Debilities [debilidad=" + debility + ", jerarquia=" + hierarchy + "]";
	}
}
