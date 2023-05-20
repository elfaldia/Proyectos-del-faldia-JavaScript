package main;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {

	public static void main(String[] args)throws IOException {
		
		loginCompleto();

	}
	
	// ------------------------------------------- FUNCIONES ------------------------------------------- 
	
	// SELECCIÓN DEL MENÚ, SI ESTE DESEA INGRESAR AL SISTEMA O REGISTRAR OTRO USUARIO
	public static void loginCompleto() throws IOException {
		
		Scanner leer = new Scanner(System.in);
		String verificador = "";
		
		System.out.println("------- BIENVENIDO AL SISTEMA -------");
		System.out.println("\n- ¿Desea registrarse o inciar sesión?\n1) Sing in.\n2) Log in.\n");
		
		while(true) {
			verificador = leer.nextLine();
			if(verificador.equalsIgnoreCase("1") || verificador.equalsIgnoreCase("2")) {
				break;
			}else {
				System.out.println("\n-- La opción ingresada es invalida, intente nuevamente.");
				continue;
			}
		}
		
		// SI LA OPCIÓN ES SING IN DEBEMOS SEGUIR PARA AGREGAR NUEVOS DATOS A LOS ARCHIVOS TXT 
		// PARA ASI PODER PROSEGUIR CON ARMAR LOS USUARIOS
		if(verificador.equalsIgnoreCase("1")) {
			
			System.out.println("\n- Para poder registrarte necesitamos que nos des tu perfil de programador: ");
			
			File archivoUsuario = new File("usuarios.txt");
			File archivoProgramador = new File("programadores.txt");
			File archivo = new File("paises.txt");

			String[][] matrizUsuarios = matrizMadre(archivoUsuario,(filas(archivoUsuario) + 1),columnas(archivoUsuario));
			String[][] matrizProgramadores = matrizMadre(archivoProgramador, (filas(archivoUsuario) + 1), 100);
			
			// CREAR UN NUEVO USUARIO
			
			System.out.println("\n- Ingrese su nombre: ");
			String nombre = leer.nextLine();
			// CREO EL ID DE 4 DIGITOS
			String idUsuario = Integer.toString((int)(Math.random()*99999));
			// UNIMOS EL ID COMPLETO.
			String usuario = nombre + "#" + idUsuario;
			
			// CREAR TU APELLIDO
			
			System.out.println("\n- Ingrese su apellido: ");
			String apellido = leer.nextLine();
			
			// CREAR LA CONTRASEÑA
			
			System.out.println("\n- Ingrese la contraseña: ");
			String contraseña = leer.nextLine();
			
			// CREAR AÑOS DE EXPERIENCIA

			String añosExperiencia = Integer.toString((int)(Math.random()*5));
			
			// INGRESAMOS EL NUEVO PROGRAMADOR EN EL ARCHIVO DE TEXTO
			
			int ultimaFila = matrizUsuarios.length -1;
			int indice = 4;
			String region = "";
			String lenguaje = "";
			String pais = "";
			String[] lenguajes =  listaProgramadores();
			String[] paises = paises(filas(archivo));
			
			matrizProgramadores[ultimaFila][0] = Integer.toString(matrizUsuarios.length);
			matrizProgramadores[ultimaFila][1] = nombre;
			matrizProgramadores[ultimaFila][2] = apellido;
			matrizProgramadores[ultimaFila][3] = añosExperiencia;
			// INGRESAREMOS LOS LENGUAJES DE PROGRAMACION QUE POSEE
			System.out.println("\n- Acontinuación nos dirá uno a uno los lenguajes de programación que sabe, si ya ingreso todos coloque (listo): ");
			while(true) {
				int comprobacion = 0;
				System.out.println("\n- Ingrese lenguaje: ");
				lenguaje = leer.nextLine();
				
				if(lenguaje.equalsIgnoreCase("listo")) {
					break;
				}
				
				for(String i : lenguajes) {
					if(lenguaje.equalsIgnoreCase(i)) {
						comprobacion++;
						break;
					}else {
						continue;
					}
				}
				
				if(comprobacion == 1) {
					matrizProgramadores[ultimaFila][indice] = lenguaje;
					indice++;
				}else {
					System.out.println("\n- La opción ingresada no es un lenguaje de programación, intente nuevamente.");
				}	
			}
			// INGRESAMOS LOS PAISES
			System.out.println("\n- Introduzca su pais de residencia: ");
			
			while(true) {
				pais = leer.nextLine();
				int cont = 0;
				for(String i : paises) {
					if(pais.equalsIgnoreCase(i)) {
						cont++;
						break;
					}else {
						continue;
					}
				}
				if(cont == 1) {
					matrizProgramadores[ultimaFila][indice] = pais;
					indice++;
					break;
				}else {
					System.out.println("\n- El pais que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS REGION
			String[] regiones = regiones(pais);
			System.out.println("\n- Introduzca la región donde vive: ");
			
			while(true) {
				region = leer.nextLine();
				int cont = 0;
				for(String i : regiones) {
					if(i == null) {
						break;
					}else {
						if(region.equalsIgnoreCase(i)) {
								cont++;
								break;
						}else {
								continue;
						}
					}
				}
				if(cont == 1) {
					matrizProgramadores[ultimaFila][indice] = region;
					indice++;
					break;
				}else {
					System.out.println("\n- La region que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS EL NUEVO USUARIO EN EL ARCHIVO DE TEXTO
			
			matrizUsuarios[ultimaFila][0] = usuario; matrizUsuarios[ultimaFila][1] = contraseña; matrizUsuarios[ultimaFila][2] = Integer.toString(matrizUsuarios.length);
			
			// ACTUALIZAR EL TXT
			actualizarDatos(matrizUsuarios,archivoUsuario,columnas(archivoUsuario));
			actualizarDatos(matrizProgramadores,archivoProgramador,100);
			System.out.println("\n- Te registraste con exito!.\n- Ahora pasaremos al Login.");
		}
		leer.close();
	}
	
	// LISTA DE REGIONES SEGUN EL PAIS ESCOGIDO
	public static String[] regiones(String pais)throws IOException{
		
		Scanner arch = new Scanner(new File("paises.txt"));
		String[] lista = new String[100];
		int cont = 0;
		
		while(arch.hasNextLine()) {
			
			String[] linea = arch.nextLine().split(",");
			if(linea[0].equalsIgnoreCase(pais)) {
				for(int i = 1; i < linea.length; i++) {
					if(linea[i] == null) {
						break;
					}else {
						lista[cont] = linea[i];
						cont++;
						continue;
					}
				}
			}else {
				continue;
			}
		}return lista;

	}
	
	// LISTA DE PAISES
	public static String[] paises(int indice)throws IOException{
		
		Scanner arch = new Scanner(new File("paises.txt"));
		String[] lista = new String[indice];
		int cont = 0;
		
		while(arch.hasNextLine()) {
			String[] linea = arch.nextLine().split(",");
			lista[cont] = linea[0];
			cont++;
		}return lista;
	}
	
	// LISTA DE TODOS LOS LENGUAJES DE PROGRAMACION
 	public static String[] listaProgramadores() {
		
		String[] lista = {"c","c++","c#","Java","Python","PHP","SQL","Ruby","Visual Basic NET","R" ,"TypeScript","Swift","Rust","Go","Kotlin","Postscript","Scheme","Erlang","Elixir","Pascal","Scala","Objective-C"};
		return lista;
	}
	
	// MATRIZ QUE CONTIENE LOS DATOS DE LOS DATOS ACTUALIZADOS HASTA EL MOMENTO
	public static String[][] matrizMadre(File archivo,int fila, int columna) throws IOException{
		
		String[][] matriz = new String[fila][columna];
		Scanner arch = new Scanner(archivo);
		int cont = 0;
		
		while(arch.hasNextLine()) {
			String[] linea = arch.nextLine().split(",");
			
			for(int i = 0; i < linea.length; i++) {
				matriz[cont][i] = linea[i];
			}
			cont++;
		}

		arch.close();
		return matriz;
	}
	
	// SACAR LAS FILAS PARA LA MATRIZ
	public static int filas(File archivo) throws IOException{
		
		Scanner arch = new Scanner(archivo);
		int cont = 0;
		
		while(arch.hasNextLine()) {
			arch.nextLine();
			cont++;
		}
		arch.close();
		return cont;
	}
	
	// SACAR LAS FILAS PARA LA MATRIZ
	public static int columnas(File archivo) throws IOException{
			
		Scanner arch = new Scanner(archivo);
		int largo = 0;
			
		while(arch.hasNextLine()) {
				
			String[] linea = arch.nextLine().split(",");
			largo = linea.length;
				
		}
		arch.close();
		return largo;
	}

	// FUNCION QUE ACTUALIZARA LOS DATOS DEL ARCHIVO TXT
	public static void actualizarDatos(String[][] matriz, File archivo,int columnas) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    for (int i = 0; i < matriz.length; i++) {
	        for (int j = 0; j < columnas; j++) {
	        	if(matriz[i][j] == null) {
	        		break;
	        	}else {
	        		if (j != columnas - 1) {
	        			writer.write(matriz[i][j] + ",");
	        		}else {
	        			writer.write(matriz[i][j]);	        		
	        		}
	        	}
	        } writer.newLine();
	    } writer.close();
	}
}
 












