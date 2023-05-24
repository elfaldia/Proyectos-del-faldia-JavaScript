package main;

public class Ias {

	private String nombre;
	private String lenguaje;
	private int nivelAmenaza;
	private String debilidad;
	private String pais;
	private String precision;
	private String tipo;
	private int id;
	
	public Ias(String nombre, String lenguaje, int nivelAmenaza, String debilidad, String pais, String precision,
			String tipo, int id) {
		super();
		this.nombre = nombre;
		this.lenguaje = lenguaje;
		this.nivelAmenaza = nivelAmenaza;
		this.debilidad = debilidad;
		this.pais = pais;
		this.precision = precision;
		this.tipo = tipo;
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLenguaje() {
		return lenguaje;
	}
	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}
	public int getNivelAmenaza() {
		return nivelAmenaza;
	}
	public void setNivelAmenaza(int nivelAmenaza) {
		this.nivelAmenaza = nivelAmenaza;
	}
	public String getDebilidad() {
		return debilidad;
	}
	public void setDebilidad(String debilidad) {
		this.debilidad = debilidad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Ias [nombre=" + nombre + ", lenguaje=" + lenguaje + ", nivelAmenaza=" + nivelAmenaza + ", debilidad="
				+ debilidad + ", pais=" + pais + ", precision=" + precision + ", tipo=" + tipo + ", id=" + id + "]";
	}
}
