package ForEach;

public class Persona {

	private final int idPersona;
	private static int contadorPersonas;
	private String nombre;
	
	{
		contadorPersonas = (int)(Math.random()*10);
	}
	
	Persona(String nombre){
		this.nombre = nombre;
		this.idPersona = contadorPersonas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + "]";
	}
	
}
