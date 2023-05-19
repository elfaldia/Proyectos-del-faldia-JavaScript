package BloqueDeCodigos;

import java.util.Scanner;

public class BloqueDeCodigo {

	private int idPersonas;
	private String nombrePersona;
	private static String NombrePersona;
	private static Scanner leer;
	
	static {
		
		leer = new Scanner(System.in);
		
		System.out.println("Ejecutar bloque estático");
		
		System.out.println("ingrese el nombre: ");
		NombrePersona = leer.nextLine();
		
	}
	
	{
		
		leer = new Scanner(System.in);
		
		System.out.println("Ejecutar bloque incializador");
		
		System.out.println("Ingrese el id de la persona: ");
		idPersonas = Integer.parseInt(leer.nextLine());		
		nombrePersona = NombrePersona;
		
		
	}
	
	BloqueDeCodigo() {
	
		System.out.println("Ejecuta constructor: ");
	}
	
	public int getIdPersonas() {
		return this.idPersonas;
	}
	public String getNombrePersona() {
		return this.nombrePersona;
	}
	public void setIdPersona(int idPersonas) {
		this.idPersonas = idPersonas;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
}
