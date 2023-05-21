package main;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {

	public static void main(String[] args)throws IOException {
		
		int verificacion = txtVacio();
		if(verificacion == 0) {
			System.out.println("\n----------- EL PROGRAMA ESTA DAÑADo, VUELVA MAS TARDE -----------");
		}else {
			
			
			int contapro = contadorpro();
			int contadeb = contadordeb();
			int contaia = contadoria();
			int contausu = contadousu();
			int contapais = contadorpais();
			
			loginCompleto();
			File archivoUsuario = new File("usuarios.txt");
			Personas[] personas = new Personas[filas(archivoUsuario)];
			Lenguajes[] lenguas = new Lenguajes[contapro];
			rellenarPersonas(personas);
			rellenarLenguajes(lenguas, contapais);
			int id = loginFinalizado(personas);
			
			if(id == -1) {
				System.out.println("linea reservada para el menu de admin");
			} else {
				
				System.out.println("indice "+id+ " y activacion de menu de usuario "+ contapro);
				
			}
		}
	}
	


	// ------------------------------------------- FUNCIONES ------------------------------------------- 
	
	// RELLENAR EL CONTENEDOR DE USUARIOS
	
		private static int contadorpais() throws IOException{
			int i = 0;
			Scanner arch = new Scanner(new File("paises.txt"));
			while(arch.hasNextLine()) {
				String[] parte = arch.nextLine().split(",");
				i++;
			} arch.close();
		return i;

	}

	private static int contadousu() throws IOException{
		int i = 0;
		Scanner arch = new Scanner(new File("usuarios.txt"));
		while(arch.hasNextLine()) {
			String[] parte = arch.nextLine().split(",");
			i++;
		} arch.close();
	return i;
	}

	private static int contadoria() throws IOException{
		int i = 0;
		Scanner arch = new Scanner(new File("ia.txt"));
		while(arch.hasNextLine()) {
			String[] parte = arch.nextLine().split(",");
			i++;
		} arch.close();
	return i;

	}

	private static int contadordeb() throws IOException{
		int i = 0;
		Scanner arch = new Scanner(new File("debilidades.txt"));
		while(arch.hasNextLine()) {
			String[] parte = arch.nextLine().split(",");
			i++;
		} arch.close();
	return i;
	}
	
	private static void rellenarLenguajes(Lenguajes[] lenguas, int paiseslimite) throws IOException {
		
		Scanner arch = new Scanner(new File("programadores.txt"));
		for(int i = 0; i < lenguas.length; i++) {
			String[] linea = arch.nextLine().split(",");
			String[] lineal = obtenerlinea(linea, paiseslimite);
			lenguas[i] = new Lenguajes(linea[0], lineal);
			System.out.println(lenguas[i].toString());
			//DESCOMENTALO PARA VER EL CONTENEDOR
		}

	}

	private static String[] obtenerlinea(String[] linea, int indicepais) throws IOException {
			Scanner pais = new Scanner(new File("paises.txt"));
			String[] listapais = new String[indicepais];
			int paisindex = 0;
			while(pais.hasNextLine()) {
				String[] parte = pais.nextLine().split(",");
				listapais[paisindex] = parte[0];
				paisindex++;}
			int veri = 4; //como empieza por la posicion 5 del txt
			int diferencia = -4; //para sacer el verdadero indice de la lista
			int indice = 0; //indice de la lista
			boolean parar = false;
			//EN ESTA WEA ME DEMORE			
			for(int i = veri; i < linea.length; i++) {
				for(int j = 0; j < indicepais; j++) {
					if(linea[veri].equals(listapais[j])) {
						parar = true;
					}
				}
				if(parar == true) {
					break;
				}
				veri++;
			}
	
			indice = veri + diferencia;
			String[] listafinal = new String[indice];
			for(int i = 0; i < indice; i++) {
				listafinal[i] = linea[i+4];
			}
		return listafinal;
	}

	private static int contadorpro() throws IOException {
			int i = 0;
			Scanner arch = new Scanner(new File("programadores.txt"));
			while(arch.hasNextLine()) {
				String[] parte = arch.nextLine().split(",");
				i++;
			} arch.close();
		return i;
	}

	//RECIEN AÑADIDOS POR YAN
		
	public static void rellenarPersonas(Personas[] contenedor) throws IOException{
		
		Scanner arch = new Scanner(new File("usuarios.txt"));
		
		for(int i = 0; i < contenedor.length; i++) {
			String[] linea = arch.nextLine().split(",");
			for(int j = 0; j < linea.length; j++) {				
				contenedor[i] = new Personas(linea[0],linea[1],Integer.parseInt(linea[2]));
				
			}
		}
	}
	
	// FUNCION QUE TERMINARA EL INGRESO AL SISTEMA
	public static int loginFinalizado(Personas[] lista) {
		
		Scanner leer = new Scanner(System.in);
		String nombreFinal = "";
		int indice = 0;
		String[] nombres = new String[lista.length];
		for(int i = 0; i < lista.length; i++) {
			String nombre = lista[i].getUsuario();
			String[] dividir = nombre.split("#");
			nombres[i] = dividir[0];
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| LOG IN - INICIO DE SESIÓN |");
		System.out.println("\n---------------------------------------------------------------------------");
		boolean adminlog = false;
		while(true) {
			System.out.println("\n-> Ingrese su nombre de usuario: ");
			nombreFinal = leer.nextLine();
			int cont = 0;
			int i = 0;
			if(nombreFinal.equals("empanadasconchapalele")) {
				adminlog = true;
				break;
			}
				for(String nombre : nombres) {
				if(nombre.equalsIgnoreCase(nombreFinal)) {
					indice = i;
					cont++;
					break;
				}else {
					i++;
					continue;
				}
			}
			if(cont == 1) {
				break;
			}else {
				System.out.println("\n- El nombre de usuario ingresado es invalido.");
			}
		}
		while(true) {
			String contraseña = "";
			System.out.println("\n-> Ingrese su contraseña: ");
			contraseña = leer.nextLine();
			if(adminlog == true && contraseña.equals("suricatarabiosa")) {
				return -1;	
			}
			
			if(contraseña.equalsIgnoreCase(lista[indice].getContraseña())) {
				break;
			}else {
				System.out.println("\n- Contraseña erronea intente nuevamente.");
			}
			
		}

		return lista[indice].getId();	
	}
	
	// SELECCIÓN DEL MENÚ, SI ESTE DESEA INGRESAR AL SISTEMA O REGISTRAR OTRO USUARIO
 	public static void loginCompleto() throws IOException {
		
		Scanner leer = new Scanner(System.in);
		String verificador = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL SISTEMA |");
		System.out.println("\n---------------------------------------------------------------------------");
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
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n- || Para poder registrarte necesitamos que nos des tu perfil de programador ||: ");
			System.out.println("\n---------------------------------------------------------------------------");
			
			File archivoUsuario = new File("usuarios.txt");
			File archivoProgramador = new File("programadores.txt");
			File archivo = new File("paises.txt");

			String[][] matrizUsuarios = matrizMadre(archivoUsuario,(filas(archivoUsuario) + 1),columnas(archivoUsuario));
			String[][] matrizProgramadores = matrizMadre(archivoProgramador, (filas(archivoUsuario) + 1), 100);
			
			// CREAR UN NUEVO USUARIO
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese su nombre: ");
			String nombre = leer.nextLine();
			// CREO EL ID DE 4 DIGITOS
			String idUsuario = Integer.toString((int)(Math.random()*99999));
			// UNIMOS EL ID COMPLETO.
			String usuario = nombre + "#" + idUsuario;
			
			// CREAR TU APELLIDO
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese su apellido: ");
			String apellido = leer.nextLine();
			
			// CREAR LA CONTRASEÑA
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese la contraseña: ");
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
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Acontinuación nos dirá uno a uno los lenguajes de programación que sabe, si ya ingreso todos coloque (listo): ");
			while(true) {
				int comprobacion = 0;
				System.out.println("\n\t- Ingrese lenguaje: ");
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
					System.out.println("\n\t- La opción ingresada no es un lenguaje de programación, intente nuevamente.");
				}	
			}
			// INGRESAMOS LOS PAISES
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Introduzca su pais de residencia: ");
			
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
					System.out.println("\n\t- El pais que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS REGION
			String[] regiones = regiones(pais);
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Introduzca la región donde vive: ");
			
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
					System.out.println("\n\t- La region que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS EL NUEVO USUARIO EN EL ARCHIVO DE TEXTO
			
			matrizUsuarios[ultimaFila][0] = usuario; matrizUsuarios[ultimaFila][1] = contraseña; matrizUsuarios[ultimaFila][2] = Integer.toString(matrizUsuarios.length);
			
			// ACTUALIZAR EL TXT
			actualizarDatos(matrizUsuarios, archivoUsuario);
			actualizarDatosProgramador(matrizProgramadores, archivoProgramador);
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- SIGN IN FINALIZADO!.\n\t- Ahota puedes pasar al Log in.");
		}
		
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
	
	// LISTA DEL LARGO DE LAS COLUMNAS
	public static int[] listaColumnas(String[][] matriz){
			
		int[] lista = new int[matriz.length];
		int cont = 0;
		int indice = 0;
		
		for(int i = 0; i < matriz.length; i++) {
			cont = 0;
			for(int j = 0; j < matriz[0].length; j++) {
				if(matriz[i][j] == null || j == matriz[0].length - 1 ) {
					lista[indice] = cont;
					indice++;
					break;
				}else {
					cont++;
				}
			}
		}return lista;
	}
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DEL ARCHIVO TXT PROGRAMADOR
	public static void actualizarDatosProgramador(String[][] matriz, File archivo) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    int[] columna = listaColumnas(matriz);
	    for (int i = 0; i < matriz.length; i++) {
	        for (int j = 0; j < columna[i]; j++) {
	        	if (j != columna[i] - 1) {
        			writer.write(matriz[i][j] + ",");
        		}else {
        			writer.write(matriz[i][j]);	        		
        		}
	        }
	        if(i != matriz.length -1) {
	        	writer.newLine();
	        }
	    } writer.close();
	}
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DE CUALQUIER TXT
	public static void actualizarDatos(String[][] matriz, File archivo) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    for (int i = 0; i < matriz.length; i++) {
	        for (int j = 0; j < matriz[0].length; j++) {
	            writer.write(matriz[i][j]);
	            if (j !=  matriz[0].length - 1) {
	                writer.write(",");
	            }
	        }if(i != matriz.length - 1) {
	        writer.newLine();
	        }
	    }
	    writer.close();
	}

	// FUNCION DE COMPROBACIÓN DE DATOS, EN EL CASO QUE LOS TXT ESTEN VACIOS
	public static int txtVacio()throws IOException{
		
		int ver = comprobacionVacio("usuarios.txt");
		if(ver == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS USUARIOS ESTA VACIO");
		}
		ver = comprobacionVacio("programadores.txt");
		if(ver == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS PROGRAMADORES ESTA VACIO");
		}
		ver = comprobacionVacio("ia.txt");
		if(ver == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS IA ESTA VACIO");
		}
		ver = comprobacionVacio("paises.txt");
		if(ver == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS PAISES ESTA VACIO");
		}
		ver = comprobacionVacio("debilidades.txt");
		if(ver == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LAS DEBILIDADES ESTA VACIO");
		}
		return ver;
	}
	
	// SUMA DATOS DE LOS TXT (APOYO DE LA FUNCION TXTVACIO)
	public static int comprobacionVacio(String archivo) throws IOException{
		
		Scanner arch = new Scanner(new File(archivo));
		int cont = 0;
		while(arch.hasNextLine()) {
			arch.nextLine();
			cont++;
		}return cont;
	}
	
}
 












