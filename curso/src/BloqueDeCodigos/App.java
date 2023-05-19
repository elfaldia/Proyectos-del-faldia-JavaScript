package BloqueDeCodigos;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner leer = new Scanner(System.in);
		
		BloqueDeCodigo codigo = new BloqueDeCodigo();
		int idPersona = codigo.getIdPersonas();
		String nombre = codigo.getNombrePersona();
		
		System.out.println(idPersona);
		System.out.println(nombre);
		
		System.out.println("Nuevo id: ");
		codigo.setNombrePersona(leer.nextLine());
		
		System.out.println(codigo.getNombrePersona());
		
		leer.close();
	}

}
