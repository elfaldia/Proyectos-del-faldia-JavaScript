package ForEach;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner leer = new Scanner(System.in);
 		Persona[] personas = new Persona[5];
		
		for(int i = 0; i < 5; i++) {
			System.out.println("\n- Introduzca el nombre: ");
			personas[i] = new Persona(leer.nextLine());
		}
		
		for(Persona persona : personas) {
			System.out.println(persona);
		}
		
		leer.close();
	}

}
