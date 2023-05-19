package curso;

public class varargs {

	// Argumentos Variables
	
	public static void main(String[] args) {
		
		//Imprimimos varios números
		imprimirNumeros("pedro","luis","pablo","donovan");
		
	}
	public static void imprimirNumeros(String... palabras) {
		//Recorremos cada elemento
		String elemento;
		for(int i = 0; i < palabras.length; i++) {
			elemento = palabras[i];
			System.out.println(elemento);
		}
	}
}
