package main;

public class Debilidades {

	private String debilidad;
	private int jerarquia;
	
	public Debilidades(String debilidad, int jerarquia) {
		super();
		this.debilidad = debilidad;
		this.jerarquia = jerarquia;
	}

	public String getDebilidad() {
		return debilidad;
	}

	public void setDebilidad(String debilidad) {
		this.debilidad = debilidad;
	}

	public int getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(int jerarquia) {
		this.jerarquia = jerarquia;
	}

	public String toString() {
		return "Debilidades [debilidad=" + debilidad + ", jerarquia=" + jerarquia + "]";
	}
}
