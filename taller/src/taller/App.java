package taller;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;

public class App {
 
	public static void main(String[] args)throws IOException {
		
		// Datos eliminación
		String[][] matrizUno = armarMatriz("datos_usuarios.txt");
		String[][] matrizDos = armarMatriz("datos_creadores.txt");
		String[][] matrizTres = armarMatriz("datos_ia.txt");
		File fileUno = new File("datos_usuarios.txt"); File fileDos = new File("datos_creadores.txt"); File fileTres = new File("datos_ia.txt");
		
		eliminarDatos(matrizUno,matrizDos,matrizTres);		
		
		// listas usuarios 
		String[] creadores = new String[100];
		String[] usuarios = new String[100];
		String[] contraseñas = new String[100];
		String[] categorias = new String[100];
		
		//listas de creadores.
		String[] especialidades = new String[100];
		int[] añosExperiencia = new int[100];
		int[] edadCreadores = new int[100];
		
		//Listas de IA.
		String[] nombreIa = new String[100];
		float[] velocidadDeAprendizaje = new float[100];
		String[] tipoDeIa = new String[100];
		int[] cantidadDeMejoras = new int[100];
		int[] añoDeCreacion = new int[100];
		
		//variables
		int[] contIngresosDeDatos = new int[1];
		
		// Inicio del programa.
		int indiceGeneral = rellenarListas(edadCreadores,añosExperiencia,añoDeCreacion,creadores,usuarios,contraseñas,categorias,especialidades,nombreIa,velocidadDeAprendizaje,tipoDeIa,cantidadDeMejoras);
		if (indiceGeneral == 0) {
			System.out.println("\n------------------------------------------------------------------------------------------------\n\tEl programa no puede iniciarse ya que la base de datos se encuentra vacia.\n------------------------------------------------------------------------------------------------");
		}else {
			int indice = (int) (Math.random()*indiceGeneral);
			// Este es la variable que nos ayudara a ingresar en los menú.
			String categoriaEspecífica = login(indice,usuarios,contraseñas,categorias,creadores,indiceGeneral);
			menuGeneral(matrizUno,matrizDos,matrizTres,edadCreadores,añosExperiencia,categoriaEspecífica,indiceGeneral,indice,especialidades,nombreIa,cantidadDeMejoras,tipoDeIa,velocidadDeAprendizaje,contIngresosDeDatos,añoDeCreacion,creadores,categorias,usuarios,contraseñas);
		}
		// matriz para la corrupción
		corromperMatriz("datos_usuarios.txt",fileUno,filas("datos_usuarios.txt"),columnas("datos_usuarios.txt"),contIngresosDeDatos);
		// segunda matriz
		corromperMatriz("datos_creadores.txt",fileDos,filas("datos_creadores.txt"),columnas("datos_creadores.txt"),contIngresosDeDatos);
		// tercera matriz
		corromperMatriz("datos_ia.txt",fileTres,filas("datos_ia.txt"),columnas("datos_ia.txt"),contIngresosDeDatos);
		
	}
	
	public static void eliminarDatos(String[][] matrizUno , String[][] matrizDos, String[][] matrizTres) throws IOException{
		//
		int contBorrados = 0;
		// Matrices nuevas
		String[][] modificadaUno = new String[filas("datos_usuarios.txt")][columnas("datos_usuarios.txt")]; String[][] modificadaDos = new String[filas("datos_creadores.txt")][columnas("datos_creadores.txt")]; String[][] modificadaTres = new String[filas("datos_ia.txt")][columnas("datos_ia.txt")];
		// files de los archivos
		File fileUno = new File("datos_usuarios.txt"); File fileDos = new File("datos_creadores.txt"); File fileTres = new File("datos_ia.txt");
		// columnas con los diferentes archivos txt
		int columnasUno = columnas("datos_usuarios.txt"); int columnasDos = columnas("datos_creadores.txt"); int columnasTres = columnas("datos_ia.txt");
		// filas generales
		int filas = filas("datos_usuarios.txt");
		int filasNuevaMatriz = 0;
		
		// Ciclo para armas las 3 matrices a base de los archivos txt
		for (int i = 0; i < filas; i++) {
			int verificador = verificadorExistenciaIAWINS(matrizUno,matrizDos,matrizTres,columnasUno,columnasDos,columnasTres,i);
			if(verificador == 0) {
				modificadaUno[i] = matrizUno[i];
				modificadaDos[i] = matrizDos[i];
				modificadaTres[i] = matrizTres[i];
				filasNuevaMatriz++;
			}else {
				contBorrados++;
				continue;
			}
		}
		modificadaUno = reorganizarMatriz(modificadaUno); modificadaDos = reorganizarMatriz(modificadaDos); modificadaTres = reorganizarMatriz(modificadaTres);
		
		String[][] temporalUno = new String[filasNuevaMatriz][columnas("datos_usuarios.txt")]; String[][] temporalDos = new String[filasNuevaMatriz][columnas("datos_creadores.txt")]; String[][] temporalTres = new String[filasNuevaMatriz][columnas("datos_ia.txt")];
		for(int i = 0; i < filasNuevaMatriz; i++) {
			for(int j = 0; j < columnasUno ; j++) {
				temporalUno[i][j] = modificadaUno[i][j];
			}
			for(int j = 0; j < columnasDos ; j++) {
				temporalDos[i][j] = modificadaDos[i][j];
			}
			for(int j = 0; j < columnasTres ; j++) {
				temporalTres[i][j] = modificadaTres[i][j];
			}
		}
 		corrupcionDeDatos(temporalUno,fileUno,filasNuevaMatriz,columnasUno); corrupcionDeDatos(temporalDos,fileDos,filasNuevaMatriz,columnasDos); corrupcionDeDatos(temporalTres,fileTres,filasNuevaMatriz,columnasTres);
 		
 		matrizUno = temporalUno;
 		matrizDos = temporalDos;
 		matrizTres = temporalTres;
 		
 		// Impreción de reporte
 		System.out.println("\n---------------------------------------------------------\n\t\tINFORME DE DATOS ELIMINADOS\n---------------------------------------------------------");
 		System.out.println("\n---------------------------------------------------------\n--> En el archivo -datos_usuarios.txt- se eliminaron: " + contBorrados + " --");
 		System.out.println("\n--> En el archivo -datos_creadores.txt- se eliminaron: " + contBorrados + " --\n\n--> En el archivo -datos_ia.txt- se eliminaron: " + contBorrados + " --\n---------------------------------------------------------\n");
 		
	}
	
	public static String[][] reorganizarMatriz(String[][] matriz) {
	    int filas = matriz.length;
	    int columnas = matriz[0].length;
	    
	    for (int i = 0; i < filas; i++) {
	        boolean tieneNulos = false;
	        for (int j = 0; j < columnas; j++) {
	            if (matriz[i][j] == null) {
	                tieneNulos = true;
	                break;
	            }
	        }
	        if (tieneNulos) {
	            // Busca la siguiente fila con valores no nulos
	            for (int k = i+1; k < filas; k++) {
	                boolean tieneValores = false;
	                for (int l = 0; l < columnas; l++) {
	                    if (matriz[k][l] != null) {
	                        tieneValores = true;
	                        break;
	                    }
	                }
	                if (tieneValores) {
	                    // Intercambia las filas i y k
	                    String[] filaAux = matriz[i];
	                    matriz[i] = matriz[k];
	                    matriz[k] = filaAux;
	                    break;
	                }
	            }
	        }
	    }
	    return matriz;
	}
	
	public static String[][] armarMatriz(String archivo) throws IOException {
		
		Scanner arch = new Scanner(new File(archivo));
		String[][] matriz = new String[filas(archivo)][columnas(archivo)];
		int filas = 0;
		int columnas = 0;
		
		while (arch.hasNextLine()) {
			String[] linea = arch.nextLine().split(",");
			columnas = linea.length;
			
			for(int i = 0; i < columnas; i++) {
				matriz[filas][i] = linea[i];
			}filas++;
		}
		return matriz;
	}

	public static int filas(String archivo)  throws IOException{
		
		Scanner arch = new Scanner(new File(archivo));
		int cont = 0;
		while (arch.hasNextLine()) {
			arch.nextLine();
			cont++;
		}arch.close()
;		return cont;
	}
		
	public static int columnas(String archivo)  throws IOException{
		
		Scanner arch = new Scanner(new File(archivo));
		int cont = 0;
		while (arch.hasNextLine()) {
			String[] partes = arch.nextLine().split(",");
			cont = partes.length;
		}return cont;
	}
		
	public static void imprimirMatriz(String[][] matriz,int filas,int columnas) {
		for (int i = 0;i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				System.out.print(matriz[i][j] + "\t");
    		}
    	System.out.println();
		}
	}
	
	public static int verificadorExistenciaIAWINS(String[][] partesUno,String[][] partesDos, String[][] partesTres, int c1 , int c2, int c3, int f) {
		int cont = 0;
		
		for(int j = 0; j < c1 ; j++) {
			if(partesUno[f][j].equalsIgnoreCase( "¡@IA¿WIN$#")) {
				cont++;
			}
		}
		for(int j = 0; j < c2 ; j++) {
			if(partesDos[f][j].equalsIgnoreCase( "¡@IA¿WIN$#")) {
				cont++;
			}
		}
		for(int j = 0; j < c3 ; j++) {
			if(partesTres[f][j].equalsIgnoreCase( "¡@IA¿WIN$#")) {
				cont++;
			}	
		}
		return cont;
	}
	
	public static int verificadorNombres(String[][] partesUno,String[][] partesDos, String[][] partesTres, int c1 , int c2, int c3, int f,String nombre) {
		int cont = 0;
		
		for(int j = 0; j < c1 ; j++) {
			if(partesUno[f][j].equalsIgnoreCase( nombre)) {
				cont++;
			}
		}
		for(int j = 0; j < c2 ; j++) {
			if(partesDos[f][j].equalsIgnoreCase(nombre)) {
				cont++;
			}
		}
		for(int j = 0; j < c3 ; j++) {
			if(partesTres[f][j].equalsIgnoreCase( nombre)) {
				cont++;
			}	
		}
		return cont;
	}
	
	public static void corromperMatriz(String arch, File archivo,int filas,int columnas, int[] contIngresosDeDatos) throws IOException{
	   
		// Matriz para traspaso de datos
		String[][] matriz = armarMatriz(arch);
		
	    // Generar valores aleatorios e insertarlos en la matriz
	    int azar = (int)(Math.random()*4);
	    if(azar == 1|| azar == 2 || azar == 3) {
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
	    // corrupcion de la matriz 
	    corrupcionDeDatos(matriz,archivo,filas,columnas);
	}
	
	public static int rellenarListas( int[] edadCreadores,int[] añosExperiencia ,int[] años,String[] creadores,String[] usuarios,String[] contraseñas,String[] categorias,String[] especialidades,String[] nombresIa,float[] velocidades,String[] tipoIas,int[] cantidadDeMejoras) throws IOException {
		
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
			System.out.println("\n-> El archivo de usuarios esta vacio.");
		}
		cont = 0;
		arch = new Scanner(new File("datos_creadores.txt"));
		while(arch.hasNextLine()) {
			String[] partes = arch.nextLine().split(",");
			String creador = partes[0];
			int experiencia = Integer.parseInt(partes[1]);
			String especialidad = partes[2];
			int edad = Integer.parseInt(partes[3]);
			cont++;
			
			for(int i = 0; i < contIndices;i++) {
				if (creador.equalsIgnoreCase(creadores[i])) {
					especialidades[i] = especialidad;
					experiencia = experiencia / 365;
					añosExperiencia[i] = experiencia;
					edadCreadores[i] = edad;
					break;
				}	
			}	
		}	
		if(cont == 0) {
			System.out.println("\n-> El archivo de creadores esta vacio.");
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
					años[i] = (int)año;
					nombresIa[i] = nombreIa;
					velocidades[i] = velocidadAprendizaje;
					tipoIas[i] = tipoIa;
					cantidadDeMejoras[i] = cantMejoras;
					break;
				}
			}
		}
		if (cont == 0) {
			System.out.println("\n-> El archivo de ia esta vacio.");
		}
		arch.close();
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
	
	public static void editarDatosIaVelocidad(String[][] matriz, int[] contIngresos)throws IOException {
		
		Scanner leer = new Scanner(System.in);
		
		String[] listaComprobacion = new String[matriz.length];
		int velocidad = 0;
		
		// Salida por pantalla
		System.out.println("\n--- Ingrese el nombre del IA a la cual desea hacerle una edición ---\n");
		for(int i = 0; i < matriz.length ; i++) {
			System.out.println((i +1) + ") -> " + matriz[i][0] + " con una velocidad de mejora de " + matriz[i][2] + " dias.");
			listaComprobacion[i] = matriz[i][0];}
		
		// ingreso por patalla
		// comprobacion de error
		String nombre = "";
	
		while(true) {
			int cont = 0;                                
			System.out.println("\n-- Introduzca el nombre --\n");
			nombre = leer.nextLine();
			
			for(int i = 0; i < listaComprobacion.length; i++) {
				if (nombre.equalsIgnoreCase(listaComprobacion[i])) {
					velocidad = Integer.parseInt(matriz[i][2]);
					cont++;
					break;
				}
			}if (cont != 0) {
				break;
			}else {
				System.out.println("\n-La opción que ingreso es erronea, ingrese nuevamente-");
				continue;
				}	
			}
		// Ingreso de datos
		int nuevaMejora = 0;
		System.out.println("\n-> El nombre del IA es " + nombre + " y posee una velocidad de mejora de " + velocidad + ". -");
		System.out.println("\n-- Tenga en cuenta que el minio de velocidad es de 10 y el maximo de 60 --");
		System.out.println("-- Aparte, la modificación que haga debe ser igual o mas rapida que la ya existente --");

		while(true) {
			System.out.println("\n- ¿Que velocidad desea colocarle al usuario? -");
			nuevaMejora = Integer.parseInt(leer.nextLine());
			if(velocidad >= nuevaMejora && nuevaMejora >= 10 && 60 >= nuevaMejora) {
				contIngresos[0]++;
				break;
			}else {
				System.out.println("\n-- La mejora que ingreso esta fuera del rango específicado --");
				continue;
			}
		}
		// Reemplazo los datos en el txt
		for(int i = 0; i < listaComprobacion.length; i++) {
			if(nombre.equalsIgnoreCase(matriz[i][0])) {
				matriz[i][2] = Integer.toString(nuevaMejora);
			}
		}
		File archivo = new File("datos_ia.txt");
		corrupcionDeDatos(matriz,archivo,filas("datos_ia.txt"),columnas("datos_ia.txt"));
		System.out.println("\n---- La velocidad fue actualizada con exito!, paso de tener " + velocidad + " en velocidad de mejora a " + nuevaMejora + " ----");
		leer.close();
	}
	
	public static void editarDatosIaMejora(String[][] matriz,int[] contIngresos)throws IOException {
		
		Scanner leer = new Scanner(System.in);
		
		String[] listaComprobacion = new String[matriz.length];
		int mejoras = 0;
		String tipoIa = "";
		
		// Salida por pantalla
		System.out.println("\n--- Ingrese el nombre del IA a la cual desea hacerle una edición ---\n");
		for(int i = 0; i < matriz.length ; i++) {
			System.out.println((i +1) + ") -> " + matriz[i][0] + " tiene " + matriz[i][5] + " mejoras y es de tipo " + matriz[i][3]);
			listaComprobacion[i] = matriz[i][0];}
		
		// ingreso por patalla
		// comprobacion de error
		String nombre = "";
	
		while(true) {
			int cont = 0;                                
			System.out.println("\n-- Introduzca el nombre --\n");
			nombre = leer.nextLine();
			
			for(int i = 0; i < listaComprobacion.length; i++) {
				if (nombre.equalsIgnoreCase(listaComprobacion[i])) {
					mejoras = Integer.parseInt(matriz[i][5]);
					tipoIa = matriz[i][3];
					cont++;
					break;
				}
			}if (cont != 0) {
				break;
			}else {
				System.out.println("\n-La opción que ingreso es erronea, ingrese nuevamente-");
				continue;
				}	
			}
		// Ingreso de datos
		int nuevaMejora = 0;
		System.out.println("\n-> El nombre del IA es " + nombre + ", este tiene " + mejoras + " mejoras, siendo asi de tipo " + tipoIa + " -");
		//Filtrado por tipo de ia
		
		// tipo simple
		if(tipoIa.equalsIgnoreCase("simple")) {
			System.out.println("\n-- Este IA no puede ser modificada ya que es de tipo simple, por lo tanto, no puede ser mejorado --"); 
		}
		
		// tipo medio
		else if(tipoIa.equalsIgnoreCase("media")) {
			
			System.out.println("\n-- Tenga en cuenta que el minimo de mejora es de 0 y el maximo de 5, ya que este ia es de tipo " + tipoIa + " --");
			System.out.println("-- Aparte, la modificación que haga debe ser igual o mayor que la ya existente --");

			while(true) {
				System.out.println("\n- ¿De cuanto será la mejora? -");
				nuevaMejora = Integer.parseInt(leer.nextLine());
				if(mejoras <= nuevaMejora && nuevaMejora >= 0 && 5 >= nuevaMejora) {
					contIngresos[0]++;
					break;
				}else {
					System.out.println("\n-- La mejora que ingreso esta fuera del rango específicado --");
					continue;
				}
			}
			// Reemplazo los datos en el txt
			for(int i = 0; i < listaComprobacion.length; i++) {
				if(nombre.equalsIgnoreCase(matriz[i][0])) {
					matriz[i][5] = Integer.toString(nuevaMejora);
				}
			}
			File archivo = new File("datos_ia.txt");
			corrupcionDeDatos(matriz,archivo,filas("datos_ia.txt"),columnas("datos_ia.txt"));
			System.out.println("\n---- Enhorabuena ahora el IA " + nombre + " fue mejorado!, paso de tener " + mejoras + " en velocidad de mejora a " + nuevaMejora + " ----");
		}
		
		// tipo avanzada
		else if(tipoIa.equalsIgnoreCase("avanzada")) {
			
			System.out.println("\n-- Tenga en cuenta que el minimo de mejora es de 0 y el maximo de 30, ya que este ia es de tipo " + tipoIa + " --");
			System.out.println("-- Aparte, la modificación que haga debe ser igual o mayor que la ya existente --");

			while(true) {
				System.out.println("\n- ¿De cuanto será la mejora? -");
				nuevaMejora = Integer.parseInt(leer.nextLine());
				if(mejoras <= nuevaMejora && nuevaMejora >= 0 && 30 >= nuevaMejora) {
					contIngresos[0]++;
					break;
				}else {
					System.out.println("\n-- La mejora que ingreso esta fuera del rango específicado --");
					continue;
				}
			}
			// Reemplazo los datos en el txt
			for(int i = 0; i < listaComprobacion.length; i++) {
				if(nombre.equalsIgnoreCase(matriz[i][0])) {
					matriz[i][5] = Integer.toString(nuevaMejora);
				}
			}
			File archivo = new File("datos_ia.txt");
			corrupcionDeDatos(matriz,archivo,filas("datos_ia.txt"),columnas("datos_ia.txt"));
			System.out.println("\n---- Enhorabuena ahora el IA " + nombre + " fue mejorado!, paso de tener " + mejoras + " en velocidad de mejora a " + nuevaMejora + " ----");
		}leer.close();
	}
	
	public static void eliminarDatosNombres(String[][] matrizUsuarios,String[][] matrizCreadores, String[][] matrizIa,int[] contIngresos)throws IOException {
		
		Scanner leer = new Scanner(System.in);
		// matrices
		String[][] modificadaUno = new String[filas("datos_usuarios.txt")][columnas("datos_usuarios.txt")]; String[][] modificadaDos = new String[filas("datos_creadores.txt")][columnas("datos_creadores.txt")]; String[][] modificadaTres = new String[filas("datos_ia.txt")][columnas("datos_ia.txt")];
		
		// columnas con los diferentes archivos txt
		int columnasUno = columnas("datos_usuarios.txt"); int columnasDos = columnas("datos_creadores.txt"); int columnasTres = columnas("datos_ia.txt");
		// filas generales
		int filas = filas("datos_usuarios.txt");
		int filasNuevaMatriz = 0;
		// lista nombres
		String[] listaComprobacion = new String[filas("datos_usuarios.txt")];
		
		System.out.println("\n--- En este apartado puedes eliminar toda la información de un creador en específico ---");
		System.out.println("--- Se desplegará una lista con todos los creadores vigentes ---\n");
		
		// lista de nombre - rellenar lista de nombres
		for(int i = 0; i < filas;i++) {
			System.out.println((i+1) + ") " + matrizCreadores[i][0]);
			listaComprobacion[i] = matrizCreadores[i][0];
		}
		
		// bucle comprobación de ingresos correcto de datos
		String nombre = "";
		while(true) {
			int cont = 0;                                
			System.out.println("\n-- Introduzca el nombre --\n");
			nombre = leer.nextLine();
			
			for(int i = 0; i < listaComprobacion.length; i++) {
				if (nombre.equalsIgnoreCase(listaComprobacion[i])) {
					cont++;
					contIngresos[0]++;
					break;
				}
			}if (cont != 0) {
				break;
			}else {
				System.out.println("\n-La opción que ingreso es erronea, ingrese nuevamente-");
				continue;
			}	
		}
		
		// crear nueva matriz
		for(int i = 0; i < filas; i++) {
			int verificador = verificadorNombres(matrizUsuarios,matrizCreadores,matrizIa,columnasUno , columnasDos,columnasTres,i,nombre);
			if(verificador == 0) {
				modificadaUno[i] = matrizUsuarios[i];
				modificadaDos[i] = matrizCreadores[i];
				modificadaTres[i] = matrizIa[i];
				filasNuevaMatriz++;
			}else {
				continue;
			}
		}
		
		//reordanizar la matriz

		modificadaUno = reorganizarMatriz(modificadaUno);modificadaDos = reorganizarMatriz(modificadaDos);modificadaTres = reorganizarMatriz(modificadaTres);
		
		// Nuevas matrices para poder hacer el paso de datos limpios evitando los null
		String[][] temporalUno = new String[filasNuevaMatriz][columnas("datos_usuarios.txt")]; String[][] temporalDos = new String[filasNuevaMatriz][columnas("datos_creadores.txt")]; String[][] temporalTres = new String[filasNuevaMatriz][columnas("datos_ia.txt")];
		// files
		File fileUno = new File("datos_usuarios.txt"); File fileDos = new File("datos_creadores.txt"); File fileTres = new File("datos_ia.txt");
		
		// Rellenar las matrices sin contar los null
		for(int i = 0; i < filasNuevaMatriz; i++) {
			for(int j = 0; j < columnasUno ; j++) {
				temporalUno[i][j] = modificadaUno[i][j];
			}
			for(int j = 0; j < columnasDos ; j++) {
				temporalDos[i][j] = modificadaDos[i][j];
			}
			for(int j = 0; j < columnasTres ; j++) {
				temporalTres[i][j] = modificadaTres[i][j];
			}
		}
		
		//ahora pasamos los datos de la matriz temporales a la original del codigo
		matrizUsuarios = temporalUno;
		matrizCreadores = temporalDos;
		matrizIa = temporalTres;
		
		//eliminar las filas en todos los archivos txt y reescribirlos nuevamente
		corrupcionDeDatos(temporalUno,fileUno,filasNuevaMatriz,columnasUno); corrupcionDeDatos(temporalDos,fileDos,filasNuevaMatriz,columnasDos); corrupcionDeDatos(temporalTres,fileTres,filasNuevaMatriz,columnasTres);
	
		//impreción
		System.out.println("\n-- Se ha borrado toda la información de " + nombre + " en todo la base de datos. --");
		leer.close();
	}
	
	public static void menuGeneral(String[][] matrizUsuario,String[][] matrizCreadores ,String[][] matrizIA, int[] edadCreadores,int[] añosExperiencia,String categoriaEspecífica ,int contGeneral, int indice, String[] especialidades,String[] nombreIas,int[] cantMejoras,String[] tipoDeIa,float[] velocidadDEMejora,int[] contIngresosDeDatos,int[] añoCreacion, String[] creadores,String[] categorias,String[] usuarios,String[] contraseñas)throws IOException {
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
					System.out.println("\n¿A cual menu deseas ingresar? (maestro ia o programador): ");
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
				System.out.println("\n-- ¿A cual submenu deseas ingresar? (IA o Usuarios): ");
				subMenu = leer.nextLine();
				if (subMenu.equalsIgnoreCase("IA") || subMenu.equalsIgnoreCase("Usuarios")) {
					break;
				}else {
					System.out.println("-- Submenu invalido, intente nuevamente -- ");
					continue;
				}
			}
			// Submenu IA
			if(subMenu.equalsIgnoreCase("ia")) {
				System.out.println("\n--- Bienvenido al submenu IA, en este apartado tienes las siguientes opciones: ---");
				System.out.println("\n- Ordenar por cualidad (Ordenar).");
				System.out.println("- Editar datos de ia (Editar).");
				String subMenuIa = "";
				// matgen de error para el menu
				while(true) {
					System.out.println("\n¿A cual opción desea ingresar?: ");
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
					System.out.println("\n-- En la opción ordenar por cualidad puedes ordenar las IA en la forma que quiera, con las siguientes opciones: --");
					System.out.println("\n- Nombre.\n- Creacion.\n- Velocidad.\n- Tipo.\n- Creador.\n- Mejora.");
					String opcion = "";
					// magen de error
					while(true) {
						System.out.println("\n-¿Cual opción eligira?-");
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
				// Submenu editar por cualidad
				else if(subMenuIa.equalsIgnoreCase("editar")) {
					System.out.println("\n-- En la opción editar datos de los ia podrás alterar la velocidad de mejora y su cantidad de mejoras: --");
					System.out.println("\n- velocidad de mejora. (velocidad)\n- cantidad de mejoras. (mejoras)");
					System.out.println("\n || RECOMENDACIÓN: al ingresar el tipo de usuario que desea, coloque  lo que se enceuntra entre parentesis, si este no posee uno, introduzca su nombre tal cual ||");
					String opcion = "";
					// magen de error
					while(true) {
						System.out.println("\n-¿Cual opción eligira?-");
						opcion = leer.nextLine();
						if(opcion.equalsIgnoreCase("velocidad") || opcion.equalsIgnoreCase("mejoras")) {
							break;
						}else {
							System.out.println("-La opción que ingreso es erronea, ingrese nuevamente-");
							continue;
						}
					}
					//editar la velocidad
					if(opcion.equalsIgnoreCase("velocidad")){
						editarDatosIaVelocidad(matrizIA,contIngresosDeDatos); }
					else if(opcion.equalsIgnoreCase("mejoras")) {
						editarDatosIaMejora(matrizIA,contIngresosDeDatos); }
				}
			}
			else if (subMenu.equalsIgnoreCase("Usuarios")) {
				System.out.println("\n--- Bienvenido al submenu Usuarios y creadores, en este apartado tienes las siguientes opciones: ---");
				System.out.println("");
				System.out.println("- Cantidad de tipo de Usuario (Cantidadxtipo).");
				System.out.println("- Añadir Usuario y/o Creador (Añadir).");
				System.out.println("- Editar datos de Usuario y/o Creador (Editar).");
				System.out.println("- Eliminar Usuario y/o Creador: (Eliminar).");
				System.out.println("\n || RECOMENDACIÓN: al ingresar el tipo de usuario que desea, coloque  lo que se enceuntra entre parentesis, si este no posee uno, introduzca su nombre tal cual ||");
				String subMenuUsuario = "";
				
				// Bucle de margen de error
				while(true) {
					System.out.println("\n¿A cual opción desea ingresar?: ");
					subMenuUsuario = leer.nextLine();
					if(subMenuUsuario.equalsIgnoreCase("Cantidadxtipo") || subMenuUsuario.equalsIgnoreCase("Añadir") || subMenuUsuario.equalsIgnoreCase("Editar") || subMenuUsuario.equalsIgnoreCase("Eliminar")) {
						break;
					}else {
						System.out.println("-- La opción ingresada es invalida, intente nuevamente --");
						continue;
					}
				}
				
				// Opcion Cantidad de tipo de Usuario (Cantidadxtipo)
				if (subMenuUsuario.equalsIgnoreCase("Cantidadxtipo")) {
					System.out.println("\n--- Ingrese el tipo de usuario que desea consultar su cantidad ---");
					System.out.println("\n- Normal\n- Administrador");
					String tipoUsuario = "";
					
					// Bucle margen de error
					while (true) {
						System.out.println("\n-- ¿Por cual tipo se decidio? --");
						tipoUsuario = leer.nextLine();
						if(tipoUsuario.equalsIgnoreCase("Normal") || tipoUsuario.equalsIgnoreCase("Administrador")) {
							break;
						}else {
							System.out.println("\n-- El tipo de usuario ingresario es erroneo o no es valido, intente nuevamente --");
						}
					}
					// Usuario tipo normal
					if(tipoUsuario.equalsIgnoreCase("normal")) {
						cantidadTiposUsuarios(categorias,"normal",contGeneral);
					}
					// Usuario tipo Administrador
					if(tipoUsuario.equalsIgnoreCase("Administrador")) {
						cantidadTiposUsuarios(categorias,"Administrador",contGeneral);
					}
				}
				// Opcion Añadir Usuario y/o Creador (Añadir)
				else if (subMenuUsuario.equalsIgnoreCase("Añadir")) {
					System.out.println("\n--- En este apartado puedes crear nuevos Usuarios o Creadores ---");
					System.out.println("\n--- Indique a que aspecto desea agregar datos ---");
					System.out.println("\n- Usuarios\n- Creadores");
					System.out.println("\n || OBSERVACIÓN: un usuario puede tener varios nombres de creadores ||");
					String archivo = "";
					
					// Bucle margen de error
					while (true) {
						System.out.println("\n-- ¿Cual archivo desea editar? --");
						archivo = leer.nextLine();
						if(archivo.equalsIgnoreCase("Usuarios") || archivo.equalsIgnoreCase("Creadores")) {
							break;
						}else {
							System.out.println("\n-- El arcivo que ingreso es erroneo o no es valido, intente nuevamente --");
						}
					}
					
					// Archivo usuario
					if(archivo.equalsIgnoreCase("Usuarios")) {

						System.out.println("\n-- Para crear un nuevo usuario necesitamos registrar los siguientes datos --");
						System.out.println("\n- Ingrese el nombre de usuario -");	
						contIngresosDeDatos[0] += 1;
						usuarios[contGeneral + 1] = leer.nextLine(); // se agrega el nombre del usuario
						
						System.out.println("\n- Ingrese la contraseña del usuario -");
						contIngresosDeDatos[0] += 1;
						contraseñas[contGeneral + 1] = leer.nextLine(); // se agrega la nueva contraseña
						
						// margen de error para las categorias
						while (true) {
							System.out.println("\n- Ingrese el tipo de categoria (Normal o Administrador) -");
							String categoria = leer.nextLine(); // se agrega la categoria
							
							if(categoria.equalsIgnoreCase("normal") || categoria.equalsIgnoreCase("administrador")) {
								contIngresosDeDatos[0] += 1;
								categorias[contGeneral + 1] = categoria; // se agrega la categoria
								break;
							} else {
								System.out.println("\n- La categoria ingresada es invalida, intente nuevamente -");
								continue;
							}
						}
						// Añadir creadores 
						System.out.println("\n- Ingrese los nombres de los creadores dueños del usuario, cuando ingrese la cantidad que usted desee, escriba (listo) para guardar los cambios -");
						// ciclo hasta que se introduzca LISTO
						while (true) {
							System.out.println("\n- Ingrese nombre -");
							String nombre = leer.nextLine();
							if(nombre.equalsIgnoreCase("listo")) {
								break;
							}else {
								if(creadores[contGeneral + 1] == null) {
									contIngresosDeDatos[0] += 1;
									creadores[contGeneral + 1] = (nombre + " - ");
								}else {
									creadores[contGeneral + 1] += (nombre + " - ");
								}
							}
						}
						// Printear resultado
						System.out.println("\n--- Se a creado con exito el usuario! el mismo posee los siguientes datos  ---\n- |Usuario| " + usuarios[contGeneral + 1] + "\n- |Contraseña| " + contraseñas[contGeneral + 1] + "\n- |categoria| " + categorias[contGeneral + 1] + "\n- |Nombres o Nombre| - " + creadores[contGeneral + 1]);
					}
					else if(archivo.equalsIgnoreCase("Creadores")) {
						
						System.out.println("\n-- Para añadir a un nuevo creador se necesitaran los siguientes datos, los cuales tendras que ir rellenando --");
						System.out.println("\n- Ingrese el nombre del creador -");	
						contIngresosDeDatos[0] += 1;
						creadores[contGeneral + 1] = leer.nextLine(); // se agrega el nombre del nuevo creador
						
						System.out.println("\n- Ingrese el tiempo que tiene de experiencia -");
						contIngresosDeDatos[0] += 1;
						añosExperiencia[contGeneral + 1] = Integer.parseInt(leer.nextLine()); // se agrega el tiempo de experiencia
						
						// margen de error
						while (true) {
							System.out.println("\n- Ingrese la especialidad del creador, las especialidades son las siguientes - \n- Maestro IA (MaestroIa)\n- Programador\n- IA Master (IaMaster)");
							System.out.println("\n || OBSERVACIÓN: un usuario puede tener varios nombres de creadores ||");
							System.out.println("\n- especialidad -");
							String especial = leer.nextLine(); // se agrega la especialidad
							if(especial.equalsIgnoreCase("maestroia") || especial.equalsIgnoreCase("programador") || especial.equalsIgnoreCase("iamaster")) {
								contIngresosDeDatos[0] += 1;
								especialidades[contGeneral + 1] = especial;
								break;
							}else {
								System.out.println("\n- La especialidad que introdujo no es valida, intente nuevamente -");
								continue;
							}
						}
						
						System.out.println("\n- Ingrese la edad del creador (todos los creadores deben ser mayores de 20 años) -");
						// Margen de error
						while (true) {
							System.out.println("\n- Edad -");
							int edad = Integer.parseInt(leer.nextLine());
							if(edad > 20 && edad < 100) {
								contIngresosDeDatos[0] += 1;
								edadCreadores[contGeneral + 1] = edad;
								break;
							}else {
								System.out.println("\n- La edad que ingreso no tiene logica, intente nuevamente -");
								continue;
							}
						}
						// Printear los datos añadidos
						System.out.println("\n--- Se a creado con exito el nuevo creador! el mismo posee los siguientes datos  ---\n- |Nombre| " + creadores[contGeneral + 1] + "\n- |Años de experiencia| " + añosExperiencia[contGeneral + 1] + "\n- |Especialidad| " + especialidades[contGeneral + 1] + "\n- |Edad| - " + edadCreadores[contGeneral + 1]);
					}
				}
				
				// Opcion editar datos dde distintos archivos (editar)
				else if (subMenuUsuario.equalsIgnoreCase("editar")) {
					// Bienvenida al codigo
					System.out.println("\n-- En este apartado puedes editar la base de dato de dos archivos, los cuales son -- \n- datos_usuarios.txt (usuarios)\n- datos_creadores.txt (creadores)");
					System.out.println("\n || RECOMENDACIÓN: al ingresar el tipo de usuario que desea, coloque  lo que se enceuntra entre parentesis, si este no posee uno, introduzca su nombre tal cual ||");
					
					// Bucle
					String opcion = "";
					while(true) {
						System.out.println("\n- Ingrese el nombre del archivo que desea editar -");
						opcion = leer.nextLine();
						if(opcion.equalsIgnoreCase("usuarios") || opcion.equalsIgnoreCase("creadores")) {
							break;
						}else {
							System.out.println("\n--- El nombre del archivo que ingreso es erroneo, intente nuevamente ---");
							continue;
						}
					}System.out.println("\n-- Acontinuación se desplegará la lista con los nombres los cuales puedes editar dichas bases de datos --\n");
					for(int i = 0; i < filas("datos_usuarios.txt") ; i++) {
						System.out.println("-> " + creadores[i]);
					}
					
					// El menu inicial
					if (opcion.equalsIgnoreCase("usuarios")) {
						editarDatosUsuarios(matrizUsuario,contIngresosDeDatos);
					}
					else if (opcion.equalsIgnoreCase("creadores")) {
						editarDatosCreadores(matrizCreadores,contIngresosDeDatos);
					}
				}
				
				// Opcion eliminar datos de usuario (eliminar)
				else if (subMenuUsuario.equalsIgnoreCase("eliminar")) {
					eliminarDatosNombres(matrizUsuario,matrizCreadores, matrizIA, contIngresosDeDatos);
				}
			}
		}leer.close();
	}
	
	public static void editarDatosCreadores(String[][] matriz, int[] ingresoDatos) throws IOException {
		
		Scanner leer = new Scanner(System.in);
		
		int fila = 0;
		String nombre = "";
		boolean encontrado = false;
		
		// Se busca el nombre en la matriz en la parte de los nombres
		while(!encontrado) {
			System.out.println("\n-- Ingrese el nombre del creador que desea editar su base de datos --");
			nombre = leer.nextLine();
			
			for(int i = 0 ; i < filas("datos_creadores.txt"); i++) {
				if(matriz[i][0].equalsIgnoreCase(nombre)) {
					fila = i;
					encontrado = true;
					break;
				}	
			}if (encontrado == false) {
				System.out.println("\n- El nombre que ingresó no es válido, intente nuevamente -");
			}
		}
		
		// Editar los datos de la matriz para posteriormente reescribirla
		System.out.println("\n- Ahora, procederemos a preguntarle y que ingrese los datos que tiene que alterar, las cuales son: dias de experiencia, categorias de creador y edad. -");
		
		// ciclo para la comprobación de ingresio de datos para los dias de experiencia 
		int experiencia = 0;
		while(true) {
			System.out.println("\n-- Ingrese los dias de experiencia que posee el creador, tenga en cuenta que el minimo de experiencia es de 2000 hasta 3000 --");
			experiencia = Integer.parseInt(leer.nextLine());
			if(experiencia >= 2000 || experiencia <= 3000) {
				break;
			}else {
				System.out.println("\n-- Los dias ingresados no son validos, intente nuevamente --");
				continue;
			}
		}		
		matriz[fila][1] = Integer.toString(experiencia); // cambio el tipo de usuario.
		ingresoDatos[0] ++;

		// ciclo para la comprobación de ingresio de categorias de creadores
		String categoria = "";
		while(true) {
			System.out.println("\n-- Ingrese la categoria del creador, tenga en cuenta que las categorias son (maestro ia) (programador) (ia master) --");
			categoria = leer.nextLine();
			if(categoria.equalsIgnoreCase("maestro ia") || categoria.equalsIgnoreCase("programador") || categoria.equalsIgnoreCase("ia master")) {
				break;
			}else {
				System.out.println("\n-- La categoria que ingresó no es valida, intente nuevamente --");
				continue;
			}
		}				
		matriz[fila][2] = categoria; // cambio el tipo de usuario.
		ingresoDatos[0] ++;		
		
		// ciclo para la comprobación de ingresio de datos para los dias de experiencia 
		int edad = 0;
		while(true) {
			System.out.println("\n-- Ingrese la edad del creador, tenga en cuenta que el minimo de edad es de 20 y el maximo es de 40 --");
			edad = Integer.parseInt(leer.nextLine());
			if(edad >= 20 || edad <= 40) {
				break;
			}else {
				System.out.println("\n-- La edad que ingresó no es valida, intente nuevamente --");
				continue;
			}
		}		
		matriz[fila][3] = Integer.toString(edad); // cambio el tipo de usuario.
		ingresoDatos[0] ++;
		
		// Reescribir el archivo txt con la matriz
		File archivo = new File("datos_creadores.txt");
		corrupcionDeDatos(matriz,archivo,filas("datos_usuarios.txt"),columnas("datos_usuarios.txt")); // se reescribió la matriz
		
		// Mensaje final		
		System.out.println("\n --- Buenisimo! los datos han sido editados correctamente. ---");
		System.out.println("\n --- Ahora " + nombre + " tiene una cantidad de " + matriz[fila][1] + " dias con experiencia, siendo este ahora de categoria " + matriz[fila][2] + " y tiene una " + matriz[fila][3] + " años de edad ---");
		
		leer.close();
	}
	
	public static void editarDatosUsuarios(String[][] matriz,int[] ingresoDatos) throws IOException{
		
		Scanner leer = new Scanner(System.in);
		
		int fila = 0;
		String nombre = "";
		boolean encontrado = false;
		
		// Se busca el nombre en la matriz en la parte de los nombres
		while(!encontrado) {
			System.out.println("\n-- Ingrese el nombre del creador que desea editar su base de datos --");
			nombre = leer.nextLine();
			
			for(int i = 0 ; i < filas("datos_usuarios.txt"); i++) {
				if(matriz[i][3].equalsIgnoreCase(nombre)) {
					fila = i;
					encontrado = true;
					break;
				}	
			}if (encontrado == false) {
				System.out.println("\n- El nombre que ingresó no es válido, intente nuevamente -");
			}
		}
		
		// Editar los datos de la matriz para posteriormente reescribirla
		System.out.println("\n- Ahora, procederemos a preguntarle y que ingrese los datos que tiene que alterar, las cuales son: usuario, contraseña y tipo de cuenta. -");
		System.out.println("\n-- Ingrese el nuevo usuario de ingreso --");
		matriz[fila][0] = leer.nextLine(); // cambio usuario en la matriz
		ingresoDatos[0] ++;
		
		System.out.println("\n-- Ingrese la nueva contraseña --");
		matriz[fila][1] = leer.nextLine(); // cambio la contraseña
		ingresoDatos[0] ++;
		
		// ciclo para la comprobación de ingresio de datos para el tipo de usuario 
		String tipoUsuario = "";
		while(true) {
			System.out.println("\n-- Ingrese el nuevo tipo de usuario del creador, tenga en cuenta que los tipos de usuario son (administrador) (normal) --");
			tipoUsuario = leer.nextLine();
			if(tipoUsuario.equalsIgnoreCase("administrador") || tipoUsuario.equalsIgnoreCase("normal")) {
				break;
			}else {
				System.out.println("\n-- El tipo de usuario que ingresó es invalida, intente nuevamente --");
				continue;
			}
		}
		
		matriz[fila][2] = tipoUsuario; // cambio el tipo de usuario.
		ingresoDatos[0] ++;

		// Reescribir el archivo txt con la matriz
		File archivo = new File("datos_usuarios.txt");
		corrupcionDeDatos(matriz,archivo,filas("datos_usuarios.txt"),columnas("datos_usuarios.txt")); // se reescribió la matriz
		
		// Mensaje final		
		System.out.println("\n --- Buenisimo! los datos han sido editados correctamente. ---");
		System.out.println("\n --- Ahora " + nombre + " tiene nombre de usuario como " + matriz[fila][0] + " con la contraseña " + matriz[fila][1] + " y el tipo de usuario " + matriz[fila][2] + " ---");
		
		leer.close();
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
		}leer.close();
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
		}leer.close();
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
		}leer.close();
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
 	
 	public static void cantidadTiposUsuarios(String[] tipos, String tipo, int indice) {
 		int cont = 0;

 		for ( int i = 0; i < indice ; i++) {
 			if(tipo.equalsIgnoreCase(tipos[i])){
 				cont++;
 			}
 		}
 		float porcentaje = (cont * 100)/indice;
		System.out.println("\n- El tipo de Usuario " + tipo + " -");
		System.out.println("\n-- Tiene un porcentaje de " + porcentaje + "% respecto al total --");
 	}
  	
}
