package curso;

public class forEach {

	public static void main(String[] args) {

		imprimirNumerosForEach("pablo",true,5,23,43,453,23,4);

	}
	public static void imprimirNumerosForEach(String nombre, boolean valido, int... numeros) {
		
		System.out.println("Nombre: " + nombre);
		System.out.println("Validación: " + valido);
		
		// Usamos un ForEach en lugar  de un for normla
		for(int numero : numeros) {
			System.out.println("Gastos: " + numero);
		}
	}
}
