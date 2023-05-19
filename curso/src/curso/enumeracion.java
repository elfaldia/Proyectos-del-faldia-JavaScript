package curso;

public class enumeracion {

	public static void main(String[] args) {

		// imprimimos los continentes
		imprimirContinentes();
		
	}
	public static void imprimirContinentes() {
		// utilizamos forEach
		for(Continentes continente : Continentes.values()) {
			System.out.println("\n-> El continente " + continente + " tiene una cantidad de " + continente.getPaises() + " paises.");
		}
	}
	
}
