package main;

public class datosUsuario {

	private int id;
	private String nombre, apellido;
	private int añosExperiencia;
	private String pais,ciudad;
	
	public datosUsuario(int id, String nombre, String apellido, int añosExperiencia, String pais, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.añosExperiencia = añosExperiencia;
		this.pais = pais;
		this.ciudad = ciudad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getAñosExperiencia() {
		return añosExperiencia;
	}

	public void setAñosExperiencia(int añosExperiencia) {
		this.añosExperiencia = añosExperiencia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String toString() {
		return "datosUsuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", añosExperiencia="
				+ añosExperiencia + ", pais=" + pais + ", ciudad=" + ciudad + "]";
	}
}
