package main;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {

	public static void main(String[] args)throws IOException {
		
		Scanner leer = new Scanner(System.in);
		int verificacion = txtVacio();
		if(verificacion == 0) {
			System.out.println("\n----------- EL PROGRAMA ESTA DAÑADO, VUELVA MAS TARDE -----------");
		}else {

			loginCompleto(leer);
		
			File archivoDebilidades = new File("debilidades.txt"); File archivoIa = new File("ia.txt"); File archivoUsuario = new File("usuarios.txt"); File archivoProgramadores = new File("programadores.txt"); File archivoPaises = new File("paises.txt");
			listaPersonas personas = new listaPersonas(filas(archivoUsuario));
			listaLenguajes lenguas = new listaLenguajes(filas(archivoProgramadores));
			listaIas ias = new listaIas(filas(archivoIa));
			listaDebilidades debilidades = new listaDebilidades(filas(archivoDebilidades));
			rellenarPersonas(personas); rellenarLenguajes(lenguas, filas(archivoPaises)); rellenarIas(ias); rellenarDebilidades(debilidades);
			
			int id = loginFinalizado(personas,leer);
			
			if(id == -1) {
				System.out.println("\n-> | linea reservada para el menu de administrador. |");
				menuAdmin(leer);
			} else {
				System.out.println("\n-> | Se ha activado el menu de usuario. |");
				 menuUsuario(id,leer,ias,debilidades,personas,lenguas);
			}
		}leer.close();
	}
	
	// ------------------------------------------- FUNCIONES -----------------------------------------------
	
	// -------------------------------------- MENU ADMINISTRADOR -------------------------------------------
	
	public static void menuAdmin(Scanner leer) {
		
		String seleccion = "";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL MENU DE ADMINISTRADOR |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t\t   | INGRESE LA OPCIÓN DE SU PREFERENCIA |");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Ver todos los programadores\n2) Ver todas las IA.\n3) Editar datos Programador.\n4) Editar datos IA.\n5) Editar datos de Usuario.\n6) Crear y visualizar debilidades.\n7) Crear una IA, programador, País\n8) Dar estadísticas por países\n9) Salir");
			System.out.println("\n---------------------------------------------------------------------------");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("Ver todos los programadores") || seleccion.equalsIgnoreCase("1")) {
				System.out.println("yan");
			}
			else if(seleccion.equalsIgnoreCase("Ver todas las IA") || seleccion.equalsIgnoreCase("2")) {
				System.out.println("yan");
			}
			else if(seleccion.equalsIgnoreCase("Editar datos Programador") || seleccion.equalsIgnoreCase("3")) {
				System.out.println("chamito");
			}
			else if(seleccion.equalsIgnoreCase("Editar datos IA") || seleccion.equalsIgnoreCase("4")) {
				System.out.println("chamito");
			}
			else if(seleccion.equalsIgnoreCase("Editar datos de Usuario") || seleccion.equalsIgnoreCase("5")) {
				System.out.println("chamito");
			}
			else if(seleccion.equalsIgnoreCase("Crear y visualizar debilidades") || seleccion.equalsIgnoreCase("6")) {
				System.out.println("chamito");
			}
			else if(seleccion.equalsIgnoreCase("Crear una IA, programador, País") || seleccion.equalsIgnoreCase("7")) {
				System.out.println("chamito");
			}
			else if(seleccion.equalsIgnoreCase("Dar estadísticas por países") || seleccion.equalsIgnoreCase("8")) {
				System.out.println("yan");
			}
			else if(seleccion.equalsIgnoreCase("salir") || seleccion.equalsIgnoreCase("9")) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n\t\t     | NOS VEMOS UNA PROXIMA VEZ :) |");
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
			
		}
	}
	
	
	
	// ----------------------------------------- MENU USUARIOS ---------------------------------------------
	
	// FUNCION QUE CONTENDRA EL MENU DE USUARIO
	public static void menuUsuario(int idUsuario,Scanner leer,listaIas ias,listaDebilidades debilidades,listaPersonas usuarios,listaLenguajes programador) throws IOException{
		
		String seleccion = "";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL MENU DE USUARO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t\t   | INGRESE LA OPCIÓN DE SU PREFERENCIA |");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Agregar debilidad a la IA.\n2) Modificar datos usuario.\n3) Modificar precisión de una IA.\n4) Ver IAs.\n5) Ver tipo de IAs.\n6) Salir.");
			System.out.println("\n---------------------------------------------------------------------------");
			seleccion = leer.nextLine();
			
			// 1RA OPCION, AGREGAR DEBILIDADES A LAS IA.
			if(seleccion.equalsIgnoreCase("Agregar debilidad a la IA") || seleccion.equalsIgnoreCase("1")) {
				primeraOpcion(leer,ias,debilidades); 
			}
			// 2DA OPCION, AGREGAR DEBILIDADES A LAS IA.
			else if(seleccion.equalsIgnoreCase("Modificar datos usuario") || seleccion.equalsIgnoreCase("2")) {
				segundaOpcion(idUsuario,leer,usuarios); 
			}
			// 3RA OPCION, AGREGAR DEBILIDADES A LAS IA.
			else if(seleccion.equalsIgnoreCase("Modificar precisión de una IA") || seleccion.equalsIgnoreCase("3")) {
				terneraOpcion(leer,ias,programador,idUsuario); 
			}
			else if(seleccion.equalsIgnoreCase("ver ias") || seleccion.equalsIgnoreCase("4")) {
				cuartaOpcion(leer,ias);
			}
			else if(seleccion.equalsIgnoreCase("ver tipo de ias") || seleccion.equalsIgnoreCase("5")) {
				quintaOpcion(leer,ias);
			}				
			// CIERRE DEL MENU (Y DEL BUCLE)
			else if(seleccion.equalsIgnoreCase("salir") || seleccion.equalsIgnoreCase("6")) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n\t\t     | NOS VEMOS UNA PROXIMA VEZ :) |");
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
	}

	// FUNCION PARA LA 1RA OPCIÓN
	public static void primeraOpcion(Scanner leer, listaIas ias, listaDebilidades debilidades) throws IOException{
		
		File archivo = new File("ia.txt"); File archivoDos = new File("debilidades.txt");
		int[] listaComprobacion = new int[filas(archivo)]; String[] debilidadesDisponibles = new String[filas(archivoDos)];
		int cont = 0, contDos = 0;
		int indice = 0;
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		int id = 0; String debilidadSeleccionada = "";
		System.out.println("\n-> | En esta opción podras agregarle una debilidad a culquier IA de la lista, solo debes indicar el ID del mismo: |\n");
		
		for(int i = 0; i < ias.getMax(); i++) {
			if(ias.getDebilidad(i).equalsIgnoreCase("desconocida")) {
				System.out.println(".- Nombre IA: " + ias.getNombre(i) + " el ID de este IA es " + ias.getId(i));
				listaComprobacion[cont] = ias.getId(i);
				cont++;
			}
		}	
		
		System.out.println("\n-> | Introduzca el ID del IA que desea modificar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verificador = 0;
			id = Integer.parseInt(leer.nextLine());
			for(int i = 0; i < cont; i++) {
				if(id == listaComprobacion[i]) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				break;
			}else {
				System.out.println("\n-> | El ID ingresado no es valido |");
			}
		}
		
		for(int i = 0; i < ias.getMax() ; i++) {
			if(ias.getId(i) == id) {
				indice = i;
				break;
			}
		}
		
		System.out.println("\n-> | Ahora se desplegará la lista de debilidades validas para este usuario, escoja una sabiamente |\n");
		
		if(ias.getNivelAmenaza(indice) == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
		}else {		
			for(int i = 0; i < debilidades.getMax(); i++) {
				if(ias.getNivelAmenaza(indice) >= debilidades.getJerarquia(i)) {
					System.out.println(".- Debilidad " + debilidades.getDebilidad(i) + ".");
					debilidadesDisponibles[contDos] = debilidades.getDebilidad(i);
					contDos++;
				}
			}
		}
		System.out.println("\n-> | Introduzca la debilidad que desea seleccionar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verificador = 0;
			debilidadSeleccionada = leer.nextLine();
			for(int i = 0; i < contDos; i++) {
				if(debilidadesDisponibles[i].equalsIgnoreCase(debilidadSeleccionada)) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				break;
			}else {
				System.out.println("\n-> | La debilidad seleccionada no es valida o no existe. |");
			}
		}
		matriz[indice][3] = debilidadSeleccionada;
		actualizarDatos(matriz, archivo);
		System.out.println("\n | LA DEBILIDAD HA SIDO IMPLEMENTADA EN LA IA " + ias.getNombre(indice) + " |");
		
		enter(leer);
	}

	// FUNCION PARA LA 2DA OPCION
	public static void segundaOpcion(int idUsuario,Scanner leer, listaPersonas usuarios)throws IOException {
		
		File archivo = new File("usuarios.txt");
		String seleccion = ""; int indice = 0; String nombreNuevo = "", contraseñaNueva = "";
		String[][] matriz = matrizMadre(archivo, filas(archivo),columnas(archivo));
		
		for(int i = 0; i < usuarios.getMax(); i++) {
			if(idUsuario == usuarios.getId(i)) {
				indice = i;
				break;
			}
		}
		
		System.out.println("\n-> | En esta opción podras editar tu nombre de usuario o tu contraseña |");
		System.out.println("\n1) Nombre Usuario.\n2) Contraseña.");
		System.out.println("\n-> | Introduzca tu opción |");
		while(true) {
			
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("Nombre Usuario") || seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Contraseña") || seleccion.equalsIgnoreCase("2")) {
				break;
			}else{
				System.out.println("\n-> | El menu que ingreso no es valido. |");
			}
		}
		
		if(seleccion.equalsIgnoreCase("Nombre Usuario") || seleccion.equalsIgnoreCase("1")) {
			
			System.out.println("\n-> | Ingrese su nuevo nombre de usuario |");
			nombreNuevo = leer.nextLine();
			String idDelUsuario = Integer.toString((int)(Math.random()*(99999 - 10000) + 10000));
			String nombreCompleto = nombreNuevo + "#" + idDelUsuario;
			
			matriz[indice][0] = nombreCompleto; actualizarDatos(matriz, archivo);
			System.out.println("\n | EL NOMBRE DE USUARIO FUE ACTUALIZADO CON EXITO |");
			
		}else if(seleccion.equalsIgnoreCase("Contraseña") || seleccion.equalsIgnoreCase("2")) {
			
			System.out.println("\n-> | Ingrese su nueva contraseña |");
			contraseñaNueva = leer.nextLine();
			
			matriz[indice][1] = contraseñaNueva; actualizarDatos(matriz, archivo);
			System.out.println("\n | LA CONTRASEÑA DEL USUARIO FUE ACTUALIZADO CON EXITO |");
		}
		enter(leer);
	}
	
	// FUNCION PARA LA 3RA OPCION
	public static void terneraOpcion(Scanner leer, listaIas ias,listaLenguajes programador, int id)throws IOException{
		
		File archivo = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String[] lenguajesDominados = programador.getLenguaje(programador.getIndice(id));
		int[] idLenguajesCompatibles = new int[programador.getMax()];
		int cont = 0;
		System.out.println("\n-> | En esta opción podras editar los IA que fueron desarrollados con los lenguajes que dominas |");
		System.out.println("\n-> | Se desplegara una lista con los IA que puedes editar |");
		
		for(int i = 0; i < programador.getMax(); i++) {
			for(String lenguaje : lenguajesDominados) {
				if(lenguaje.equalsIgnoreCase(ias.getLenguaje(i))) {
					idLenguajesCompatibles[cont] = ias.getId(i);
					cont++; break;
				}
			}
		}
		int verificador = 0;
		for(int ids : idLenguajesCompatibles) {
			if(ids == 0) {
				verificador++;
			}else {
				System.out.println("\n--------------------------------------\n.- Nombre del IA: " + ias.getNombre(ias.getIndice(ids)) + "\n.- Presición: " + ias.getPrecision(ias.getIndice(ids)) + "\n.- ID: " + ias.getId(ias.getIndice(ids)) + "\n--------------------------------------");
			}
		}
		
		if(verificador != programador.getMax()) {
			int idIa = 0,aux = 0;
			System.out.println("\n--------------------------------------------------------");
			System.out.println("\n-> | INGRESA EL ID DE LA IA QUE DESEA EDITAR |");
			System.out.println("\n--------------------------------------------------------");
			
			while(true) {
				aux = 0;
				idIa = Integer.parseInt(leer.nextLine());
				for(int ids : idLenguajesCompatibles) {
					if( ids == idIa) {
						if(ids == 0) {
							break;
						}else {
							idIa = ids;
							aux++;
							break;
						}
					}
				}if(aux == 1) {
					break;
				}else {
					System.out.println("\n-> | EL ID QUE IONGRESO NO PERTENECE A NINGUNA IA |");
					System.out.println("\n--------------------------------------------------------");
				}
			}
			
			System.out.println("\n-> | Ingrese la nueva precisión a la IA |");
			System.out.println("\n-> | RECUERDE QUE NO PUEDE EXCEDER EL MAXIMO DE 100 |");
			System.out.println("\n--------------------------------------------------------");
			int precision = 0;
			
			while(true) {
				precision = Integer.parseInt(leer.nextLine());
				if(precision <= 100 && precision >= 0) {
					break;
				}else{
					System.out.println("\n-> | LA PRESICIÓN QUE INGRESO NO ES VALIDA |");
					System.out.println("\n--------------------------------------------------------");
				}
			}
			String precisionFinal = Integer.toString(precision) + "%";
			matriz[ias.getIndice(idIa)][5] = precisionFinal; actualizarDatos(matriz, archivo);
			System.out.println("\n--------------------------------------------------------"); System.out.println("\n | LA CONTRASEÑA DEL USUARIO FUE ACTUALIZADO CON EXITO |");	
			enter(leer);
		}else {
			System.out.println("\n--------------------------------------------------------------------------");
			System.out.println("\n-> | NO EXISTE NINGUNA IA DESEÑADA CON LOS LEGUAJES QUE MANEJAS |");
			System.out.println("\n--------------------------------------------------------------------------");enter(leer);
		}
	}
	
	// FUNCION PARA LA 4TA OPCIÓN
	private static void cuartaOpcion(Scanner leer, listaIas ias) {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("\n\t-> | BASE DE DATOS DE IAS REGISTRADAS |");
		System.out.println("\n--------------------------------------------------------");
		for(int i = 0; i < ias.getMax(); i++) {
			ias.getImpresion4taOpcion(i);

			while(true) {
				System.out.println("\n--------------------------------------------------------");
				System.out.println("\n\t| PRESIONA ENTER PARA VER LA SIGUIENTE |");
				System.out.println("\n--------------------------------------------------------");
				String scout = leer.nextLine();

				if(scout.equalsIgnoreCase("")) {
					break;
				}else {
					continue;
				}
			}
		}
		enter(leer);
	}	
	
	// FUNCION PARA LA 5TA OPCIÓN
	private static void quintaOpcion(Scanner leer, listaIas ias) {
		
		//PEQUEÑO FRAGMENTO DE CODIGO PARA CREAR UNA LISTA SIN REPETICION DE LOS TIPOS DE IA SEGUN EL TXT
		boolean existe = false;
		String[] lista = new String[5];
		for(int i = 0; i < lista.length; i++) {
			for(int j = 0; j <ias.getMax();j++) {
				if(i == 0) {lista[i] = ias.getTipo(j);}
				else {
					existe = false;
					for(int x = 0; x < i;x++) {
						if(lista[x].equals(ias.getTipo(j))) {existe = true;}}
					if(existe == false) {
						lista[i] = ias.getTipo(j);}}}}
		
		//System.out.println(Arrays.toString(lista));
		//INICIO DEL MENU COMO TAL

	System.out.println("\n--------------------------------------------------------"); System.out.println("\n-> | INGRESE EL TIPO DE IA QUE QUIERE REVISAR |"); System.out.println("\n--------------------------------------------------------");
	for(int i = 0; i < lista.length; i++) {
		System.out.println("\n.- Tipo de IA: "+ lista[i].toUpperCase());
	}System.out.println("\n--------------------------------------------------------");
	System.out.println("");
	String scout = leer.nextLine();
	int x = 0;
	while(x == 0) {
		for(int i = 0; i < lista.length; i++) {
			//System.out.println(scout+" "+lista[i]);
			if(scout.equalsIgnoreCase(lista[i])){x++;}}
		if(x == 0) {System.out.println("\n-> | ERROR EN LA SINTAXIS ESCRIBE TAL CUAL COMO SE MUESTRA EN LA PANTALLA |");
		System.out.println("\n--------------------------------------------------------");
			scout = leer.nextLine();	}
	}
	//PRINTEO DE LAS IAS QUE SE MOSTRARAN POR CONSOLA
	System.out.println("\n--------------------------------------------------------");
	System.out.println("\n-> | LOS IA DE ESTE TIPO SON LOS SIGUIENTES |");
	System.out.println("\n--------------------------------------------------------");
	for(int i = 0; i < ias.getMax(); i++) {
		if(scout.equals(ias.getTipo(i))) {
			System.out.println("\n.- Nombre: "+ias.getNombre(i));}}
	
	System.out.println("\n--------------------------------------------------------"); System.out.println("\n\t | PRESIONA ENTER PARA IR AL MENÚ |"); System.out.println("\n--------------------------------------------------------");
	scout = leer.nextLine();}
	
	
	// -----------------------------------------------------------------------------------------------------------
	
	// ------------------------------------------- LOGIN / COMPLEMENTOS -------------------------------------------
	
	//CIERRE DE OPCIÓN
	public static void enter(Scanner leer) {
		while(true) {
			System.out.println("\n---------------------------------------------------------");
			System.out.println("\n\t[PRESIONA ENTER PARA IR AL MENÚ]");
			String scout = leer.nextLine();
			System.out.println("\n---------------------------------------------------------");

			if(scout.equalsIgnoreCase("")) {
				break;
			}else {
				continue;
			}
		}
	}
	
	// RELLENAR CONTENEDOR DE LOS IA
	public static void rellenarDebilidades(listaDebilidades lista) throws IOException{
			
			Scanner arch = new Scanner(new File("debilidades.txt"));	
			for(int i = 0; i < lista.getMax(); i++){		
				String[] linea = arch.nextLine().split(",");
				lista.ingresar(new Debilidades(linea[0],Integer.parseInt(linea[1])));
			}
		}
	
	// FUNCION PARA IMPRIMIR MATRIZ
	public static void imprimirMatriz(String[][] matriz) {
	
		for(int i = 0; i < matriz.length; i++) {
			for(int j = 0; j < matriz[0].length; j++) {
				if(j != matriz[0].length - 1) {
					System.out.print(matriz[i][j] + ",");
				}else {
					System.out.println(matriz[i][j]);
				}
			}System.out.println("");
		}
	}
	
	// RELLENAR CONTENEDOR DE LOS IA
	public static void rellenarIas(listaIas lista) throws IOException{
		
		Scanner arch = new Scanner(new File("ia.txt"));	
		for(int i = 0; i < lista.getMax(); i++){		
			String[] linea = arch.nextLine().split(",");
			lista.ingresar(new Ias(linea[0],linea[1],Integer.parseInt(linea[2]),linea[3],linea[4],linea[5],linea[6],Integer.parseInt(linea[7])));
		}
	}
	
	// RELLENAR EL CONTENEDOR DE DATOS DE LOS PROGRAMADORES.
	public static void rellenarLenguajes(listaLenguajes lenguas, int paiseslimite) throws IOException {
		
		Scanner arch = new Scanner(new File("programadores.txt"));
		for(int i = 0; i < lenguas.getMax(); i++) {
			String[] linea = arch.nextLine().split(",");
			String[] lineal = obtenerlinea(linea, paiseslimite);
			lenguas.ingresar(new Lenguajes(linea[0], lineal));
			//DESCOMENTALO PARA VER EL CONTENEDOR
		}

	}

	// COMPLEMENTO QUE NOS DA LA LISTAS DE LOS LENGUAJES DE PROGRAMACION DOMINADOS POR CADA PERSONA.
	public static String[] obtenerlinea(String[] linea, int indicepais) throws IOException {
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

	// RELLENAR EL CONTENEDOR DE USUARIOS.		
	public static void rellenarPersonas(listaPersonas contenedor) throws IOException{
		
		Scanner arch = new Scanner(new File("usuarios.txt"));

		for(int i = 0; i < contenedor.getMax(); i++) {
			String[] linea = arch.nextLine().split(",");
			Personas datos = new Personas(linea[0],linea[1],Integer.parseInt(linea[2]));
			contenedor.ingresar(datos);
			
		}
	}
	
	// FUNCION QUE TERMINARA EL INGRESO AL SISTEMA.
	public static int loginFinalizado(listaPersonas lista, Scanner leer) {
		
		String nombreFinal = "";
		int indice = 0;
		String[] nombres = new String[lista.getCantidad()];
		for(int i = 0; i < lista.getCantidad(); i++) {

			String nombre = lista.getNombre(i);
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
			
			if(contraseña.equalsIgnoreCase(lista.getContraseña(indice))) {
				break;
			}else {
				System.out.println("\n- Contraseña erronea intente nuevamente.");
			}
			
		}

		return lista.getId(indice);	
	}
	
	// SELECCIÓN DEL MENÚ, SI ESTE DESEA INGRESAR AL SISTEMA O REGISTRAR OTRO USUARIO.
 	public static void loginCompleto(Scanner leer) throws IOException {
		
		String verificador = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL SISTEMA |");
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n- ¿Desea registrarse o inciar sesión?\n1) Sing in.\n2) Log in.\n");
		
		while(true) {
			verificador = leer.nextLine();
			if(verificador.equalsIgnoreCase("1") || verificador.equalsIgnoreCase("2")|| verificador.equalsIgnoreCase("sing in")|| verificador.equalsIgnoreCase("log in")) {
				break;
			}else {
				System.out.println("\n-- La opción ingresada es invalida, intente nuevamente.");
				continue;
			}
		}
		
		// SI LA OPCIÓN ES SING IN DEBEMOS SEGUIR PARA AGREGAR NUEVOS DATOS A LOS ARCHIVOS TXT 
		// PARA ASI PODER PROSEGUIR CON ARMAR LOS USUARIOS
		if(verificador.equalsIgnoreCase("1") || verificador.equalsIgnoreCase("sing in")) {
			
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
			String idUsuario = Integer.toString((int)(Math.random()*(99999 - 10000) + 10000));
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
	
	// LISTA DE REGIONES SEGUN EL PAIS ESCOGIDO.
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
	
	// LISTA DE TODOS LOS LENGUAJES DE PROGRAMACION.
 	public static String[] listaProgramadores() {
		
		String[] lista = {"c","c++","c#","Java","Python","PHP","SQL","Ruby","Visual Basic NET","R" ,"TypeScript","Swift","Rust","Go","Kotlin","Postscript","Scheme","Erlang","Elixir","Pascal","Scala","Objective-C"};
		return lista;
	}
	
	// MATRIZ QUE CONTIENE LOS DATOS DE LOS DATOS ACTUALIZADOS HASTA EL MOMENTO.
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
	
	// SACAR LAS FILAS PARA LA MATRIZ.
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
	
	// SACAR LAS FILAS PARA LA MATRIZ.
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
	
	// LISTA DEL LARGO DE LAS COLUMNAS.
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
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DEL ARCHIVO TXT PROGRAMADOR.
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
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DE CUALQUIER TXT.
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

	// FUNCION DE COMPROBACIÓN DE DATOS, EN EL CASO QUE LOS TXT ESTEN VACIOS.
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
	
	// SUMA DATOS DE LOS TXT (APOYO DE LA FUNCION TXTVACIO).
	public static int comprobacionVacio(String archivo) throws IOException{
		
		Scanner arch = new Scanner(new File(archivo));
		int cont = 0;
		while(arch.hasNextLine()) {
			arch.nextLine();
			cont++;
		}return cont;
	}
	
	// -----------------------------------------------------------------------------------------------------------
	
}
 
