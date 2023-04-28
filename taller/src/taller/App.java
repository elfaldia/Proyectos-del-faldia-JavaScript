package taller;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.util.Arrays;

public class App {
 
	public static void main(String[] args)throws IOException {

		String[] creadores = new String[100];
		String[] usuarios = new String[100];
		String[] contraseñas = new String[100];
		String[] categorias = new String[100];
		
		//listas de creadores.
		String[] especialidades = new String[100];
		
		//Listas de IA.
		String[] nombreIa = new String[100];
		float[] velocidadDeAprendizaje = new float[100];
		String[] tipoDeIa = new String[100];
		int[] cantidadDeMejoras = new int[100];
		int[] añoDeCreacion = new int[100];
		
		//variables
		int[] contIngresosDeDatos = new int[1];
		
		// Inicio del programa.
		int indiceGeneral = rellenarListas(añoDeCreacion,creadores,usuarios,contraseñas,categorias,especialidades,nombreIa,velocidadDeAprendizaje,tipoDeIa,cantidadDeMejoras);
		
		if (indiceGeneral == 0) {
			
		}else {
			int indice = (int) (Math.random()*indiceGeneral);
			// Este es la variable que nos ayudara a ingresar en los menú.
			String categoriaEspecífica = login(indice,usuarios,contraseñas,categorias,creadores,indiceGeneral);
			menuGeneral(categoriaEspecífica,indiceGeneral,indice,especialidades,nombreIa,cantidadDeMejoras,tipoDeIa,velocidadDeAprendizaje,contIngresosDeDatos,añoDeCreacion,creadores);
		}
		/*
		// matriz para la corrupción
		int filas = 0;
		int columna = 4;
		String[][] txt1 = new String[100][100];
		File archivo = new File("datos_usuarios.txt");
		rellenarMatriz(txt1,archivo,filas,columna,contIngresosDeDatos);
		// segunda matriz
		String[][] txt2 = new String[100][100];
		File archivo2 = new File("datos_creadores.txt");
		rellenarMatriz(txt2,archivo2,filas,columna,contIngresosDeDatos);
		// tercera matriz
		String[][] txt3 = new String[100][100];
		File archivo3 = new File("datos_ia.txt");
		rellenarMatriz(txt3,archivo3,filas,columna,contIngresosDeDatos);
		*/
	}
	
	public static void rellenarMatriz(String[][] matriz,File archivo,int filas,int columnas, int[] contIngresosDeDatos) throws IOException{
	    Scanner arch = new Scanner(archivo);
	    filas = 0;
	    columnas = 0;
		
	    // Leer los datos del archivo y almacenarlos en la matriz
	    int cont = 0;
	    while (arch.hasNextLine()) {
	        String[] line = arch.nextLine().split(",");
	        columnas = line.length;
	        cont++;
	        for (int j = 0; j < line.length; j++) {
	            matriz[filas][j] = line[j];
	        }
	        filas++;
	    }
	    if (cont == 0) {
	    	System.out.println("El archivo se encuentra vacio.");
	    	return;
	    }
	    arch.close(); // cerrar el scanner

	    // Generar valores aleatorios e insertarlos en la matriz
	    int azar = (int)(Math.random()*4);
	    if(azar == 1|| azar == 2) {
	    	int aux = 0;
	    	while(true) {
	    		if(contIngresosDeDatos[0] == 0) {
	    			break;
	    		}	
	    		aux++;
	    		int j = (int)(Math.random()*columnas); // generar un índice de columna aleatorio
	    		int l = (int)(Math.random()*filas); // generar un índice de fila aleatorio
	    		matriz[l][j] = "¡@IA¿WIN$#"; // insertar el valor aleatorio en la matriz
	    		if (aux == contIngresosDeDatos[0]) {
	    			break;
	    		}
	    	}
	    }
	    /*for (int i = 0;i < filas; i++) {
	    	for (int j = 0; j < columnas; j++) {
	    		System.out.print(matriz[i][j] + "\t");
	    	}
	    	System.out.println();
	    }*/
	    corrupcionDeDatos(matriz,archivo,filas,columnas);
	}
	
	public static int rellenarListas(int[] años,String[] creadores,String[] usuarios,String[] contraseñas,String[] categorias,String[] especialidades,String[] nombresIa,float[] velocidades,String[] tipoIas,int[] cantidadDeMejoras) throws IOException {
		
		File nombreTxt = new File("datos_usuarios.txt");
		Scanner arch = new Scanner(nombreTxt);
		int contIndices = 0;
		int cont = 0;
		while(arch.hasNextLine()) {
			String[] partes = arch.nextLine().split(",");
			String usuario = partes[0];
			String contraseña = partes[1];
			String categoria = partes[2];
			String creador = partes[3];
			cont++;

			// Corromper el arch txt
			
			creadores[contIndices] = creador;
			usuarios[contIndices] = usuario;
			contraseñas[contIndices] = contraseña;
			categorias[contIndices] = categoria;
			contIndices++;
		}
		if(cont == 0) {
			System.out.println("El archivo de usuarios esta vacio.");
		}
		cont = 0;
		arch = new Scanner(new File("datos_creadores.txt"));
		while(arch.hasNextLine()) {
			String[] partes = arch.nextLine().split(",");
			String creador = partes[0];
			String especialidad = partes[2];
			cont++;
			
			for(int i = 0; i < contIndices;i++) {
				if (creador.equalsIgnoreCase(creadores[i])) {
					especialidades[i] = especialidad;
					break;
				}
			}
		}	
		if(cont == 0) {
			System.out.println("El archivo de creadores esta vacio.");
		}
		cont = 0;
		arch = new Scanner(new File("datos_ia.txt"));
		while(arch.hasNextLine()) {
			String[] partes = arch.nextLine().split(",");
			String nombreIa = partes[0];
			int año = Integer.parseInt(partes[1]);
			float velocidadAprendizaje = Float.parseFloat(partes[2]);
			String tipoIa = partes[3];
			String creador = partes[4];
			int cantMejoras = Integer.parseInt(partes[5]);
			cont++;
			
			for(int i = 0; i < contIndices;i++) {
				if (creador.equalsIgnoreCase(creadores[i])) {
					años[i] = año;
					nombresIa[i] = nombreIa;
					velocidades[i] = velocidadAprendizaje;
					tipoIas[i] = tipoIa;
					cantidadDeMejoras[i] = cantMejoras;
					break;
				}
			}
		}
		if (cont == 0) {
			System.out.println("El archivo de ia esta vacio.");
		}
		return contIndices;
	}

	public static String login(int indice,String[] usuarios,String[] contraseñas, String[] categorias,String[] creadores,int n) {
		
		System.out.println("-> Ingrese su nombre de usuario: " + usuarios[indice]);
		System.out.println("-> Indique su contraseña: " + contraseñas[indice]);
		System.out.println(" ");
		System.out.println("---Bienvenido---");
		System.out.println(" ");
		System.out.println("-> " + creadores[indice]);
		return categorias[indice];
	}
	
	public static void menuGeneral(String categoriaEspecífica ,int contGeneral, int indice, String[] especialidades,String[] nombreIas,int[] cantMejoras,String[] tipoDeIa,float[] velocidadDEMejora,int[] contIngresosDeDatos,int[] añoCreacion, String[] creadores) {
		Scanner leer = new Scanner(System.in);
		
		if(categoriaEspecífica.equalsIgnoreCase("normal")) {
			if (especialidades[indice].equalsIgnoreCase("mejora ia")) {
				System.out.println("---------------------------------------------------------");
				System.out.println("--- Categoria correspondiente del usuario: " + categoriaEspecífica + ". ---");
				menuMejoraDeIa(contGeneral,indice,especialidades,nombreIas,cantMejoras,tipoDeIa,velocidadDEMejora,contIngresosDeDatos);	
			}
			else if (especialidades[indice].equalsIgnoreCase("programador")) {
				System.out.println("---------------------------------------------------------");
				System.out.println("--- Categoria correspondiente del usuario: " + categoriaEspecífica + ". ---");
				menuProgramador(contGeneral,indice,especialidades, nombreIas,tipoDeIa,velocidadDEMejora, contIngresosDeDatos);
			}
			else if (especialidades[indice].equalsIgnoreCase("ia master")) {
				System.out.println("---------------------------------------------------------");
				System.out.println("--- Categoria correspondiente del usuario: " + categoriaEspecífica + ". ---");
				System.out.println("---------------------------------------------------------");
				System.out.println("-- Por ser de la categoria IA MASTER puedes acceder a los dos tipo de menu, tanto maestro ia como programador --");
				String categoria =  " ";
				
				// bucle de comprobación
				while(true) {
					System.out.println(" ");
					System.out.println("¿A cual menu deseas ingresar? (maestro ia o programador): ");
					categoria = leer.nextLine();
					if(categoria.equalsIgnoreCase("maestro ia") || categoria.equalsIgnoreCase("programador")) {
						break;
					}else {
						System.out.println("-> El tipo de menu que ingresaste no son validos, intente nuevamente.");
					}
				}
				// maestro ia
				if(categoria.equalsIgnoreCase("maestro ia")) {
					menuMejoraDeIa(contGeneral,indice,especialidades,nombreIas,cantMejoras,tipoDeIa,velocidadDEMejora,contIngresosDeDatos);	
				}
				else if(categoria.equalsIgnoreCase("programador")) {
					menuProgramador(contGeneral,indice,especialidades, nombreIas,tipoDeIa,velocidadDEMejora, contIngresosDeDatos);
				}
			}
		}
		// MENU administradores
		else if(categoriaEspecífica.equalsIgnoreCase("administrador")) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("--- Categoria correspondiente del usuario: " + categoriaEspecífica + ". ---");
			System.out.println("-----------------------------------------------------------------");

			String subMenu = "";
			while (true) {
				System.out.println(" ");
				System.out.println("-- ¿A cual submenu deseas ingresar? (IA o Usuarios y creadores): ");
				subMenu = leer.nextLine();
				if (subMenu.equalsIgnoreCase("IA") || subMenu.equalsIgnoreCase("Usuarios y creadores")) {
					break;
				}else {
					System.out.println("-- Submenu invalido, intente nuevamente -- ");
					continue;
				}
			}
			// Submenu IA
			if(subMenu.equalsIgnoreCase("ia")) {
				System.out.println(" ");
				System.out.println("--- Bienvenido al submenu IA, en este apartado tienes las siguientes opciones: ---");
				System.out.println("");
				System.out.println("- Ordenar por cualidad (Ordenar).");
				System.out.println("- Editar datos de ia (Editar).");
				String subMenuIa = "";
				// matgen de error para el menu
				while(true) {
					System.out.println(" ");
					System.out.println("¿A cual opción desea ingresar?: ");
					subMenuIa = leer.nextLine();
					if(subMenuIa.equalsIgnoreCase("ordenar") || subMenuIa.equalsIgnoreCase("editar")) {
						break;
					}else {
						System.out.println("-- La opción ingresada es invalida, intente nuevamente --");
						continue;
					}
				}
				// continuacion
				// Submenu ia ordernar por cualidad
				if(subMenuIa.equalsIgnoreCase("ordenar")) {
					System.out.println("");
					System.out.println("-- En la opción ordenar por cualidad puedes ordenar las IA en la forma que quiera, con las siguientes opciones: --");
					System.out.println("");
					System.out.println("- Nombre.\n- Creacion.\n- Velocidad.\n- Tipo.\n- Creador.\n- Mejora.");
					String opcion = "";
					// magen de error
					while(true) {
						System.out.println("");
						System.out.println("-¿Cual opción eligira?-");
						opcion = leer.nextLine();
						if(opcion.equalsIgnoreCase("nombre") || opcion.equalsIgnoreCase("creacion") || opcion.equalsIgnoreCase("velocidad") || opcion.equalsIgnoreCase("tipo") || opcion.equalsIgnoreCase("creador") || opcion.equalsIgnoreCase("mejora")) {
							break;
						}else {
							System.out.println("-La opción que ingreso es erronea, ingrese nuevamente-");
							continue;
						}
					}
					// orden por nombre
					if(opcion.equalsIgnoreCase("nombre")) {
						ordenarDeFormaAlfabetica(nombreIas,contGeneral);
					}
					// orden por año de creación
					else if (opcion.equalsIgnoreCase("creacion")) {
						ordenaPorAñoDeCreacion(nombreIas,añoCreacion,contGeneral);
					}
					// orden por velocidad de mejora
					else if(opcion.equalsIgnoreCase("velocidad")) {
						ordenarPorVelocidad(nombreIas,velocidadDEMejora,contGeneral);
					}
					// orden por tipo de IA
					else if(opcion.equalsIgnoreCase("tipo")) {
						ordenarPorTipo(nombreIas,tipoDeIa,contGeneral);
					}
					// orden por creador de la IA
					else if(opcion.equalsIgnoreCase("creador")) {
						ordenarPorCreador(nombreIas,creadores,contGeneral);
					}
					// orden por cantidad de mejoras
					else if(opcion.equalsIgnoreCase("mejora")) {
						ordenarPorMejora(nombreIas,cantMejoras,contGeneral);
					}
				}
			}
		}
	}
	
	public static void menuProgramador(int contGeneral, int indice, String[] especialidades,String[] nombreIas,String[] tipoDeIa,float[] velocidadDEMejora,int[] contIngresosDeDatos) {
		Scanner leer = new Scanner(System.in);
		System.out.println("---------------------------------------------------------");
		System.out.println("El usuario posee especialidad de tipo " + especialidades[indice] + ":");
		System.out.println("A continuación se desplegaran todos los IA con sus respectivaa velocidades: ");
		System.out.println(" ");
		
		for (int i = 0; i < contGeneral;i++) {
			int velocidad = (int) (velocidadDEMejora[i] ); // cambie el valor float a una variable int para asi poder imprimirlo entero
			System.out.println("-> " + i + ") " +nombreIas[i] + " con una velocidad de mejora de " + velocidad + " dias y siendo esta de tipo " + tipoDeIa[i]);
		}
		// En esta parte cree un bucle para corroborar si el ingreso del nombre era correcto.
		int indiceIaACambiar = -1;
		while(true) {
			System.out.println(" ");
			System.out.println("-> Ingrese el nombre de la IA que desea modificar: ");
			String IaACambiar = leer.nextLine();
		
			// se crea un ciclo for para poder buscar la posición del nombre ingresado
			for(int i = 0; i < contGeneral;i++) {
				if (nombreIas[i].equalsIgnoreCase(IaACambiar)) {
					indiceIaACambiar = i;
					break;
				}
			}
			if(indiceIaACambiar != -1) {
				break;
			}else{
				System.out.println("-> El nombre que ingreso no se encuentra en el listado, por favor ingrse nuevamente el nombre: ");
				continue;
			}
		}
		
		//Pregunta de que se se desea editar + bucle de confirmación.
		String seleccionMenu = "";
		while(true) {
			System.out.println(" ");
			System.out.println("¿Que desea editar? (velocidad o tipo): ");
			seleccionMenu = leer.nextLine();
		
			if(seleccionMenu.equalsIgnoreCase("velocidad") || seleccionMenu.equalsIgnoreCase("tipo")) {
				break;
			}else {
				System.out.println("-> El metodo del IA que ingreso es invalido, intente nuevamente.");
				continue;
			}
		}
		
		// condiciones para el menu de velocidad
		if(seleccionMenu.equalsIgnoreCase("velocidad")) {

			// comprobación para los ia que ya posean 10
			if (velocidadDEMejora[indiceIaACambiar] == 10) {
				System.out.println("- El ia ya posee el maximo de velocidad, por lo tanto, no puede ser editado.");
				
			}else {
				while(true) {
					System.out.println(" ");
					System.out.println("-> ¿Cuantos dias desea mejorar la velocidad de mejora? si actualmente posee " + (int)(velocidadDEMejora[indiceIaACambiar]) + ": ");

					float numero = Float.parseFloat(leer.nextLine());
					velocidadDEMejora[indiceIaACambiar] -= numero;
				
					if (velocidadDEMejora[indiceIaACambiar] < 10) {
						velocidadDEMejora[indiceIaACambiar] += numero;
						System.out.println("-> El maximo de dias de mejoras es de 10, y usted se excedio, intente nuevamente con valores aptos.");
						continue;					

					}else {
						contIngresosDeDatos[0] ++; // contador para la corrupción de datos
						System.out.println("- Enhorabuena se modifico la velocidad de mejora del IA " + nombreIas[indiceIaACambiar] + " con actualemnete " + (int)(velocidadDEMejora[indiceIaACambiar]) + " dias de mejora.");
						break;
					}
				}
			}
		}
		// condiciones para el menu de velocidad
		else if(seleccionMenu.equalsIgnoreCase("tipo")) {
			
			// SIMPLE
			if(tipoDeIa[indiceIaACambiar].equalsIgnoreCase("simple")) {
				// comprobación de error
				String tipoCambiado = "";
				while(true) {
					System.out.println(" ");
					System.out.println("-> Puedes cambiarlo a tipo media o avanzada, elija: ");
					tipoCambiado = leer.nextLine();
					if (tipoCambiado.equalsIgnoreCase("media") || tipoCambiado.equalsIgnoreCase("avanzada")) {
						contIngresosDeDatos[0]++;
						break;
					}else {
						System.out.println("-> El tipo que ingreso no esta registado o no es valido, ingrese nuevamente: ");
						continue;
					}
				}
				// cambio de datos.
				tipoDeIa[indiceIaACambiar] = tipoCambiado;
				System.out.println("--- Enhorabuena el IA " + nombreIas[indiceIaACambiar] + " fue cambiado de tipo simple a " + tipoDeIa[indiceIaACambiar] + ". ---");
			}
			// MEDIA
			else if(tipoDeIa[indiceIaACambiar].equalsIgnoreCase("media")) {
				
				// comprobación de error
				String tipoCambiado = "avanzada";
				tipoDeIa[indiceIaACambiar] = tipoCambiado;
				contIngresosDeDatos[0]++;
				System.out.println("--- Enhorabuena el IA " + nombreIas[indiceIaACambiar] + " fue cambiado de tipo media a " + tipoDeIa[indiceIaACambiar] + ". --");
			}
			// AVANZADA
			else if(tipoDeIa[indiceIaACambiar].equalsIgnoreCase("avanzada")) {
				
				//mensaje de error
				System.out.println("--- El ia " + nombreIas[indiceIaACambiar] + " no puede ser mejorado ya que este se encuentra en el tipo mas avanzado. ---");
			}
		}
	}
	
	public static void menuMejoraDeIa(int contGeneral,int indice, String[] especialidades,String[] nombreIas,int[] cantMejoras,String[] tipoDeIa,float[] velocidadDEMejora,int[] contIngresosDeDatos) {
		System.out.println("---------------------------------------------------------");
		System.out.println("El usuario posee especialidad de tipo " + especialidades[indice] + ":");
		System.out.println("A continuación se desplegaran todos los IA con sus respectiva cantidad de mejoras: ");
		System.out.println(" ");
		
		for (int i = 0; i < contGeneral;i++) {
			System.out.println("-> " + i + ") " +nombreIas[i] + " con una cantidad de " + cantMejoras[i] + " mejoras y de tipo " + tipoDeIa[i]);
		}
		
		Scanner leer = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("¿Desea realizar una modificación a alguna IA? (oprima si para continuas, de lo contra ingrese no): ");
		String respuesta = leer.nextLine();
		
		if(respuesta.equalsIgnoreCase("si")) {
			
			String nombre = "";
			int indiceIA = 0; 
			
			// Codigo de comprobación
			while(true) {
				System.out.println(" ");
				System.out.println("-> Indique el nombre de la IA que desea mejorar: ");
				nombre = leer.nextLine();
				int cont = 0;
				for(int i = 0; i < nombreIas.length; i++) {
					if(nombre.equalsIgnoreCase(nombreIas[i])) {
						cont++;
					}
				}
				if (cont == 1) {
					break;
				}else {
					System.out.println("-- El nombre que ingreso no es valido --");
					continue;
				}
			}
			for(int i = 0; i < nombreIas.length; i++) {
				if(nombre.equalsIgnoreCase(nombreIas[i])) {
					indiceIA = i;
					break;
				}
			}
			
			// menu simple
			if(tipoDeIa[indiceIA].equalsIgnoreCase("simple")) {
				System.out.println("-> Esta IA no es capaz de adquirir mejoras.");
			}
			// menu media
			else if(tipoDeIa[indiceIA].equalsIgnoreCase("media")) {	
				tipoDeMejora(indiceIA,cantMejoras,nombreIas,velocidadDEMejora,5,contIngresosDeDatos);
			}
			// menu avanzada
			else if(tipoDeIa[indiceIA].equalsIgnoreCase("avanzada")){	
				tipoDeMejora(indiceIA,cantMejoras,nombreIas,velocidadDEMejora,30,contIngresosDeDatos);
			}
		}else {
			System.out.println("Ok.");
		}
	}
	
	public static void tipoDeMejora(int indice, int[] cantMejoras, String[] nombreIas,float[] velocidadDEMejora,int n,int[] contIngresosDeDatos) {
		Scanner leer = new Scanner(System.in);
		
		if(cantMejoras[indice] < n) {
			while(true) {
				System.out.println(" ");
				System.out.println("¿Cuantas mejoras desea hacerle a " + nombreIas[indice] + "? :");
				int mejora = Integer.parseInt(leer.nextLine());
				cantMejoras[indice] += mejora;
				velocidadDEMejora[indice] -= (mejora*0.25);
				int horas = 24;
				int cont = 0;
				
				for (int i = 0;i < mejora;i++) {
					cont++;
					if(cont == 5) {
						horas = 24;
						cont = 1;
					}
					horas -= 6;
				}
						
				if(cantMejoras[indice] > n) {
					System.out.println("-> Se excedio el limite de mejoras, intente nuevamente.");
					cantMejoras[indice] -= mejora;
					velocidadDEMejora[indice] += (mejora*0.25);
	
				}else {
					int dias = (int)velocidadDEMejora[indice];
					System.out.println("-> Su modificación fue realizada con exito! ahora " + nombreIas[indice] + " posee " + cantMejoras[indice] + " mejoras y ahora tiene una velocidad de mejora de " + dias + " dias y " + horas + " horas.");
					contIngresosDeDatos[0]++;
					break;
				}
			}
		}else {
			System.out.println("-> Excede el limite de mejoras.");
		}
	}
	
	public static void corrupcionDeDatos(String[][] matriz, File archivo, int indice, int columnas) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    for (int i = 0; i < indice; i++) {
	        for (int j = 0; j < columnas; j++) {
	            writer.write(matriz[i][j]);
	            if (j != columnas - 1) {
	                writer.write(",");
	            }
	        }
	        writer.newLine();
	    }
	    writer.close();
	}
	
	public static void ordenarDeFormaAlfabetica(String[] lista,int indice) {
		
		//Metodo burbuja para ordenar de forma alfabetica
		for(int i = 0; i < indice - 1; i++) {
			for(int j = 0; j < indice - i - 1;j++) {
				if(lista[j].compareTo(lista[j + 1]) > 0) {
					burbujaUno(lista,j);
				}
			}
		}
		System.out.println("\n-- Los nombres de los IA ordenados de forma alfabetica serían de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + lista[i]);
		}System.out.println("\n-- De nada :) --");
	}
	
	public static void ordenaPorAñoDeCreacion(String[] nombre, int[] año, int indice) {
		
		// Metodo burbuja para ordenar la lista de int
		for(int i = 0 ; i < indice -1; i++) {
			for(int j = i +1; j < indice; j++) {
				if(año[i] > año[j]) {
					burbujaDosString(nombre,i,j);
					burbujaDosInt(año,i,j);
				}
			}
		}
		System.out.println("\n-- Los IA ordenados por año de creación serían de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + nombre[i] + ", este IA fue creado en el año " + año[i]);
		}System.out.println("\n-- De nada :) --");
	}
	
	public static void ordenarPorVelocidad(String[] nombre, float[]velocidad, int indice) {
		
		// Metodo burbuja para ordenar la lista de floats
		for(int i = 0 ; i < indice -1; i++) {
			for(int j = i +1; j < indice; j++) {
				if(velocidad[i] > velocidad[j]) {
					burbujaDosString(nombre,i,j);
					burbujaDosFloat(velocidad,i,j);
				}
			}
		}
		System.out.println("\n-- Los IA ordenados por velocidad de mejora sería de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + nombre[i] + ", este IA posee una velocidad de mejora de " + (int)(velocidad[i]) + " dias.");
		}System.out.println("\n-- De nada :) --");
	}
	
	public static void ordenarPorTipo(String[] nombre, String[] tipo, int indice) {
		
		// Metodo burbuja para poder colocarde de avanzada a media y a simple
		for(int i = 0; i < indice - 1; i++) {
			for(int j = 0; j < indice - i - 1;j++) {
				if(tipo[j].compareTo(tipo[j + 1]) > 0) {
					burbujaUno(nombre,j);
					burbujaUno(tipo,j);
				}
			}
		}
		System.out.println("\n-- Los IA ordenados por gerarquía dependiendo su tipo sería de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + nombre[i] + ", este IA es de tipo " + tipo[i] + ".");
		}System.out.println("\n-- De nada :) --");
	}
	
	public static void ordenarPorCreador(String[] nombre, String[] creador, int indice) {
		
		// Metodo burbuja para poder ordenar de forma alfabetica a los creadores de los ia
		for(int i = 0; i < indice - 1; i++) {
			for(int j = 0; j < indice - i - 1;j++) {
				if(creador[j].compareTo(creador[j + 1]) > 0) {
					burbujaUno(nombre,j);
					burbujaUno(creador,j);
				}
			}
		}
		System.out.println("\n-- Los IA ordenados según su creador sería de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + nombre[i] + ", el creador de este IA es " + creador[i] + ".");
		}System.out.println("\n-- De nada :) --");
		
	}
	
	public static void ordenarPorMejora(String[] nombre, int[] mejora, int indice) {
		
		// Metodo burbuja para ordenar la lista de int
		for(int i = 0 ; i < indice -1; i++) {
			for(int j = i +1; j < indice; j++) {
				if(mejora[i] < mejora[j]) {
					burbujaDosString(nombre,i,j);
					burbujaDosInt(mejora,i,j);
				}
			}
		}
		System.out.println("\n-- Los IA ordenados por sus cantidades de mejoras serían de la siguiente forma --\n");
		for(int i = 0; i < indice;i++) {
			System.out.println("-> " + nombre[i] + ", este IA tiene una cantidad de " + mejora[i] + " mejoras.");
		}System.out.println("\n-- De nada :) --");
	}
	
 	public static void burbujaUno(String[] lista, int indice) {
		String aux = lista[indice];
		lista[indice] = lista[indice + 1];
		lista[indice + 1] = aux;
	}
 	
 	public static void burbujaDosString(String[] lista,int indice1,int indice2) {
 		String aux =  lista[indice1];
 		lista[indice1] = lista[indice2];
 		lista[indice2] = aux;
 	}
 	
 	public static void burbujaDosInt(int[] lista,int indice1, int indice2) {
 		int aux =  lista[indice1];
 		lista[indice1] = lista[indice2];
 		lista[indice2] = aux;
 	}
 	
 	public static void burbujaDosFloat(float[] lista,int indice1, int indice2) {
 		float aux =  lista[indice1];
 		lista[indice1] = lista[indice2];
 		lista[indice2] = aux;
 	}	
}
