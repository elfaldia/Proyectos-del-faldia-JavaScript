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
			listaDatosUsuario datosUsuario = new listaDatosUsuario(filas(archivoProgramadores));
			listaPersonas personas = new listaPersonas(filas(archivoUsuario));
			listaLenguajes lenguas = new listaLenguajes(filas(archivoProgramadores));
			listaIas ias = new listaIas(filas(archivoIa));
			listaDebilidades debilidades = new listaDebilidades(filas(archivoDebilidades));
			rellenarDatosUsuario(datosUsuario); rellenarLenguajes(lenguas, filas(archivoPaises)); rellenarIas(ias); rellenarDebilidades(debilidades);rellenarPersonas(personas);
			
			int id = loginFinalizado(personas,leer);
			
			if(id == -1) {
				System.out.println("\n-> | linea reservada para el menu de administrador. |");
				id = personas.getIdConNombre("suricatarabiosa");		
				menuAdmin(leer,id,lenguas,datosUsuario,personas,ias,debilidades);
			} else {
				System.out.println("\n-> | Se ha activado el menu de usuario. |");
				menuUsuario(id,leer,ias,debilidades,personas,lenguas);
			}
		}leer.close();
	}
	
	// ------------------------------------------- FUNCIONES -----------------------------------------------
	
	// -------------------------------------- MENU ADMINISTRADOR -------------------------------------------
	
	// FUNCIÓN QUE EJECUTA EL MENÚ ADMINISTRADOR
	public static void menuAdmin(Scanner leer,int id, listaLenguajes programadores,listaDatosUsuario datosUsuarios,listaPersonas personas, listaIas ias,listaDebilidades debilidades)throws IOException {
		
		String seleccion = "";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL MENU DE ADMINISTRADOR |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			id = personas.getIdConNombre("suricatarabiosa");
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
				opcionTresAdmin(leer, id,programadores,datosUsuarios,personas,ias);
			}
			else if(seleccion.equalsIgnoreCase("Editar datos IA") || seleccion.equalsIgnoreCase("4")) {
				opcionCuatroAdmin(leer, id, ias, debilidades,datosUsuarios,personas);
			}
			else if(seleccion.equalsIgnoreCase("Editar datos de Usuario") || seleccion.equalsIgnoreCase("5")) {
				opcionCincoAdmin(leer, id, ias, debilidades,datosUsuarios,personas);
			}
			else if(seleccion.equalsIgnoreCase("Crear y visualizar debilidades") || seleccion.equalsIgnoreCase("6")) {
				opcionSeisAdmin(leer,debilidades);
			}
			else if(seleccion.equalsIgnoreCase("Crear una IA, programador, País") || seleccion.equalsIgnoreCase("7")) {
				opcionSieteAdmin(leer, datosUsuarios, personas, ias, debilidades,programadores);
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
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
	}

	//------------------------------------------OPCIÓN SIETE------------------------------------------------

	public static void opcionSieteAdmin(Scanner leer,listaDatosUsuario datosUsuario, listaPersonas usuario, listaIas ias,listaDebilidades debilidades,listaLenguajes lenguajes) throws IOException {
		
		System.out.println("\n-> | En esta opción podras Crear nuevos usuarios, programadores, Ias y paises: |");
		System.out.println("\n-> | Acontinuación se desplegará el menu: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			String seleccion = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Crear nuevo usuario completo.\n2) Crear pais.\n3) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("3") || seleccion.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Crear nuevo usuario completo")) {
				crearUnNuevoUsuarioGeneral(leer, debilidades, ias, usuario, datosUsuario, lenguajes);
			}
			else if(seleccion.equalsIgnoreCase("2") || seleccion.equalsIgnoreCase("Crear pais")) {
				añadirPaises(leer);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// FUNCION QUE NOS DEJARÁ REGISTRAR NUEVOS PAISES
	public static void añadirPaises(Scanner leer)throws IOException{
		
		File archivo = new File("paises.txt");
		String[][] matriz = matrizMadre(archivo,( filas(archivo) + 1), 100);
		int ultimaFila = matriz.length - 1, columna = 0;
		String pais = "",regiones = "";
		
		System.out.println("\n-> | EN ESTE SEGMENTO PUEDES INGRESAR UN PAIS CON SUS RESPECTIVAS REGIONES |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		System.out.println("\n-> | INGRESE EL PAIS: |");
		System.out.println("\n---------------------------------------------------------------------------");

		while(true) {
			pais = leer.nextLine();
			
			if(pais.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}matriz[ultimaFila][columna] = pais;columna++;
		System.out.println("\n-> | PARA PARAR DE AGREGAR REGIONES INGRESE LISTO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			System.out.println("\n-> | INGRESE UNA REGION DE ESTE PAIS: |");
			regiones = leer.nextLine();
			
			if(regiones.equalsIgnoreCase("")) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				if(regiones.equalsIgnoreCase("listo")) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					matriz[ultimaFila][columna] = regiones;columna++;
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}
			}
		}
		actualizarDatosProgramador(matriz, archivo);
		System.out.println("\n-> | SE HA REGISTRADO EL PAIS |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// FUNCION PARA LA PRIMERA OPCIÓN Y ES LA QUE CREA UN USUARIO
	public static void crearUnNuevoUsuarioGeneral(Scanner leer, listaDebilidades debilidades, listaIas ias, listaPersonas personas, listaDatosUsuario datosUsuarios,listaLenguajes lenguajes)throws IOException{
		
		File archivoDebilidades = new File("debilidades.txt"),archivoUsuario = new File("usuarios.txt"), archivoDatosUsuario = new File("programadores.txt"), archivoIas = new File("ia.txt"), archivoPaises = new File("paises.txt");
		String[][] matrizUsuarios = matrizMadre(archivoUsuario, (filas(archivoUsuario) + 1), columnas(archivoUsuario)), matrizDatosUsuarios = matrizMadre(archivoDatosUsuario, (filas(archivoDatosUsuario) + 1), 100), matrizIas = matrizMadre(archivoIas,(filas(archivoIas) + 1), columnas(archivoIas));
		String[] lenguajesExistentes = listaProgramadores(), lenguajesIngresados = new String[100], paises = paises(filas(archivoPaises));
		int ultimaFila = matrizUsuarios.length-1;
		int indiceLenguajes = 4, indentificacion = (int)(Math.random()*(99999 - 10000) + 10000);
		String nombre = "",apellido = "", contraseña = "", lenguajeIngresado ="",pais = "", region = "";
		int id = 0, añosExperiencia = 0;
		
		System.out.println("\n-> | En esta opción podras crear un usuario general: |");
		System.out.println("\n-> | Primero tomaremos los datos de la persona: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		// ------------------------------- NOMBRE USUARIO --------------------------------------------------------------
		
		System.out.println("\n-> | Ingresa tu nombre: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			nombre = leer.nextLine();
			if(nombre.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
				
		// ------------------------------- APELLIDO USUARIO--------------------------------------------------------------
		
		System.out.println("\n-> | Ingresa tu apellido: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			apellido = leer.nextLine();
			if(apellido.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		// ------------------------------- CONTRASEÑA USUARIO--------------------------------------------------------------
		
		System.out.println("\n-> | Crea tu contraseña: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			contraseña = leer.nextLine();
			if(contraseña.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		// ------------------------------- ID GENERAL--------------------------------------------------------------
		
		System.out.println("\n-> | Crea tu ID: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			id = Integer.parseInt(leer.nextLine());
			int i;
			for(i = 0; i < personas.getCantidad();i++) {
				if(id == personas.getId(i)) {
					break;
					}
			}if(i == personas.getCantidad()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		
		// ------------------------------- EXPERIENCIA USUARIO --------------------------------------------------------------
		
		System.out.println("\n-> | Ingresa los años de experiencia: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			añosExperiencia = Integer.parseInt(leer.nextLine());
			
			if(añosExperiencia > 0 && añosExperiencia < 100) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | LOS AÑOS QUE INGRESO NO TIENEN SENTIDO |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		
		// ------------------------------- LENGUAJES USUARIO -------------------------------------------------------------
		
		System.out.println("\n-> | Ahora ingresa los lenguajes de programación que domines: |");
		System.out.println("\n-> | Cuando ya no quiera ingresar mas lenguajes introduzca (listo): |");
		System.out.println("\n---------------------------------------------------------------------------");
		int indice = 0;
		
		while(true) {
			int comprobacion = 0;
			System.out.println("\n\t- Ingrese lenguaje: ");
			lenguajeIngresado = leer.nextLine();
			
			if(lenguajeIngresado.equalsIgnoreCase("listo")) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				if(lenguajeIngresado.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacia |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					for(String i : lenguajesExistentes) {
						if(lenguajeIngresado.equalsIgnoreCase(i)) {
							comprobacion++;
							break;
						}else {
							continue;
						}
					}		
					if(comprobacion == 1) {
						matrizDatosUsuarios[ultimaFila][indiceLenguajes] = lenguajeIngresado;
						lenguajesIngresados[indice] = lenguajeIngresado; indice++;
						indiceLenguajes++;		System.out.println("\n---------------------------------------------------------------------------");
					}else {
						System.out.println("\n\t- La opción ingresada no es un lenguaje de programación, intente nuevamente.");
						System.out.println("\n---------------------------------------------------------------------------");
					}
				}	
			}
		}
		
		// ------------------------------- PAIS USUARIO --------------------------------------------------------------

		System.out.println("\n-> | Ingrese su pais de residencia: |");
		System.out.println("\n---------------------------------------------------------------------------");

		while(true) {
			int comprobacion = 0;
			pais = leer.nextLine();
			
			if(pais.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String i: paises) {
					if(pais.equalsIgnoreCase(i)) {
						comprobacion++;
						break;
					}else {
						continue;
					}
				}if(comprobacion == 1) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					System.out.println("\n | El pais que ingreso no esta en la base de datos. | ");
					System.out.println("\n---------------------------------------------------------------------------");
				}
			}
		}
		
		// ------------------------------- REGION USUARIO --------------------------------------------------------------
		
		String[] regiones = regiones(pais);
		System.out.println("\n-> | Ingrese su región: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			int comprobacion = 0;
			region = leer.nextLine();
			
			if(region.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String i: regiones) {
					if(region.equalsIgnoreCase(i)) {
						comprobacion++;
						break;
					}else {
						continue;
					}
				}if(comprobacion == 1) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					System.out.println("\n | La región no corresponde al pais. | ");
					System.out.println("\n---------------------------------------------------------------------------");
				}
			}
		}
		personas.setearMax(ultimaFila +1);lenguajes.setearMax(ultimaFila+1);datosUsuarios.setearMax(ultimaFila+1);ias.setearMax(ultimaFila+1);
		
		// SE CREA USUARIO Y ACTUALIZA EL TXT
		matrizUsuarios[ultimaFila][0] = nombre + "#" + Integer.toString(indentificacion);
		matrizUsuarios[ultimaFila][1] = contraseña;
		matrizUsuarios[ultimaFila][2] = Integer.toString(id); actualizarDatos(matrizUsuarios, archivoUsuario);
		personas.ingresar(new Personas(nombre + "#" + Integer.toString(indentificacion),contraseña,id));
		
		// SE CREAN LOS DATOS DEL USAURIO Y ACTUALIZAR EL TXT
		matrizDatosUsuarios[ultimaFila][0] = Integer.toString(id);
		matrizDatosUsuarios[ultimaFila][1] = nombre;
		matrizDatosUsuarios[ultimaFila][2] = apellido;
		matrizDatosUsuarios[ultimaFila][3] = Integer.toString(añosExperiencia);
		matrizDatosUsuarios[ultimaFila][indiceLenguajes] = pais;indiceLenguajes++;
		matrizDatosUsuarios[ultimaFila][indiceLenguajes] = region; actualizarDatosProgramador(matrizDatosUsuarios, archivoDatosUsuario);
		datosUsuarios.getIngresar(new datosUsuario(id, nombre, apellido, añosExperiencia, pais, region));
		
		// SE GUARDAN LOS LENGUAJES	
		int i;
		for( i = 0; i < lenguajesIngresados.length;i++) {
			if(lenguajesIngresados[i] == null) {
				break;
			}
		}String[] lengaujesFinal = new String[i];
		for(int j = 0; j < lengaujesFinal.length; j++) {
			lengaujesFinal[j] = lenguajesIngresados[j];
		}
		lenguajes.ingresar(new Lenguajes(Integer.toString(id), lengaujesFinal));
		
		String[] tiposIa = listaTiposIa();
		String nombreIa = "", lenguajeIa = "", debilidad = "", tipoIa = "";
		int presicion = 0, jerarquia = 0;
		
		System.out.println("\n-> | Ahora tomaremos los datos para la IA: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		// ------------------------------- NOMBRE IA --------------------------------------------------------------
		
		System.out.println("\n-> | Ingrese el nombre: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			nombreIa = leer.nextLine();
			if(nombreIa.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		// ------------------------------- LENGUAJE IA --------------------------------------------------------------
		
		System.out.println("\n-> | Ingrese el lenguaje con el que se desarrollo: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			int comprobacion = 0;
			System.out.println("\n\t- Ingrese lenguaje: ");
			lenguajeIa = leer.nextLine();
			
			if(lenguajeIa.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String n : lengaujesFinal) {
				if(lenguajeIa.equalsIgnoreCase(n)) {
						comprobacion++;
						break;
					}else {
						continue;
					}
				}		
				if(comprobacion == 1) {
						System.out.println("\n---------------------------------------------------------------------------");
						break;
					}else {
						System.out.println("\n\t- La opción ingresada no es dominada por el usuario, intente nuevamente.");
						System.out.println("\n---------------------------------------------------------------------------");
				}	
			}
		}
		
		// ------------------------------- JERARQUIA Y DEBILIDAD IA --------------------------------------------------------------

		System.out.println("\n-> | Ingrese la jerarquia de la IA (recuerde que es de 1 a 5): |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			jerarquia = Integer.parseInt(leer.nextLine());
			
			if(jerarquia > 0 && jerarquia < 6) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | la jerarquia se sale del rango |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		System.out.println("\n-> | Ingrese su debilidad: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		String[] debilidadesDisponibles = new String[filas(archivoDebilidades)];
		int cont = 0;
		
		if(jerarquia == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
			System.out.println("\n---------------------------------------------------------------------------");
		}else {		
			for( i = 0; i < debilidades.getCantidad(); i++) {
				if(jerarquia >= debilidades.getJerarquia(i)) {
					System.out.println("\n.- Debilidad " + debilidades.getDebilidad(i) + ".");
					debilidadesDisponibles[cont] = debilidades.getDebilidad(i);
					cont++;
				}
			}System.out.println("\n---------------------------------------------------------------------------");
		}
		while(true) {
			int verificador = 0;
			debilidad = leer.nextLine();
			for( i = 0; i < cont; i++) {
				if(debilidadesDisponibles[i].equalsIgnoreCase(debilidad)) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				if(debilidad.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}else {
				System.out.println("\n-> | La debilidad seleccionada no es valida o no existe. |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		
		// ------------------------------- PRECISION IA --------------------------------------------------------------

		System.out.println("\n-> | Ingrese la presición de la IA (recuerde que es de 1 a 100): |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			presicion = Integer.parseInt(leer.nextLine());
			
			if(presicion > 0 && presicion < 101) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | la precisión se sale del rango |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		
		// ------------------------------- TIPO IA --------------------------------------------------------------

		System.out.println("\n-> | Ingrese el tipo de IA |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			int verificador = 0;
			tipoIa = leer.nextLine();
			for( i = 0; i < tiposIa.length; i++) {
				if(tiposIa[i].equalsIgnoreCase(tipoIa)) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				if(tipoIa.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}else {
				System.out.println("\n-> | El IA que ingreso no es valido. |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		
		// RELLENAR Y ACTUALIZAR DATOS
		matrizIas[ultimaFila][0] = nombreIa;
		matrizIas[ultimaFila][1] = lenguajeIa;
		matrizIas[ultimaFila][2] = Integer.toString(jerarquia);
		matrizIas[ultimaFila][3] = debilidad;
		matrizIas[ultimaFila][4] = pais;
		matrizIas[ultimaFila][5] = Integer.toString(presicion) + "%";
		matrizIas[ultimaFila][6] = tipoIa;
		matrizIas[ultimaFila][7] = Integer.toString(id);actualizarDatos(matrizIas, archivoIas);
		ias.ingresar(new Ias(nombreIa, lenguajeIa, jerarquia, debilidad, pais, Integer.toString(presicion) + "%", tipoIa, id));
		System.out.println("\n-> | SE HA REGISTRADO EL NUEVO USUARIO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");

	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN SEIS------------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESQUELETO DE LA OPCION SEIS
	public static void opcionSeisAdmin(Scanner leer,listaDebilidades debilidades) throws IOException {
		
		System.out.println("\n-> | En esta opción podras visualizar o cread debilidades para los IA: |");
		System.out.println("\n-> | Acontinuación se desplegará el menu: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			String seleccion = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Visualizar todas las debilidades.\n2) Crear debilidad.\n3) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("3") || seleccion.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Visualizar todas las debilidades")) {
				visualizarDebilidades(debilidades, leer);
			}
			else if(seleccion.equalsIgnoreCase("2") || seleccion.equalsIgnoreCase("Crear debilidad")) {
				crearDebilidad(leer, debilidades);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
		
	}
	
	// VISUALIZAR DEBILIDADES
	public static void visualizarDebilidades(listaDebilidades debilidades,Scanner leer) {
		
		System.out.println("\n-> | ACONTINUACION SE DESPLEGARÁN TODAS LAS DEBILIDADES EXISTENTES HASTA EL MOMENTO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		for(int i = 0; i < debilidades.getCantidad(); i++) {
			System.out.println("\n.- Nombre de la debilidad: " + debilidades.getDebilidad(i) + "\n.- Jerarquía: " + debilidades.getJerarquia(i));System.out.println("\n---------------------------------------------------------------------------");
		}enter(leer);
	}
	
	// CREAR UNA DEBILIDAD
	public static void crearDebilidad(Scanner leer, listaDebilidades debilidades) throws IOException{
		
		String nuevaDebilidad = "";int nuevaJerarquí = 0;
		File archivo = new File("debilidades.txt");
		String[][] matriz = matrizMadre(archivo,( filas(archivo) + 1), columnas(archivo));
		int ultimaFila = matriz.length -1;
		System.out.println("\n-> | En esta opción podrás creas nuevas debilidades para los IA |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		System.out.println("\n-> Ingrese la nueva debilidad: ");
		
		while(true) {
			nuevaDebilidad = leer.nextLine();
			int i;
			for(i = 0; i < debilidades.getCantidad();i++) {
				if(nuevaDebilidad.equalsIgnoreCase(debilidades.getDebilidad(i))) {
					break;
				}
			}if(i == debilidades.getCantidad()) {
				
				if(nuevaDebilidad.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacia |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
				
			}else {
				System.out.println("\n-> | La debilidad que quiere ingresar que existe |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}
		}
		
		System.out.println("\n-> Ingrese su jerarquía: ");
		System.out.println("\n-> | RECUERDE QUE SOLO PUEDE SER DE 1 A 5 |");
		
		while(true) {
			nuevaJerarquí = Integer.parseInt(leer.nextLine());
			
			if(nuevaJerarquí > 0 && nuevaJerarquí < 6) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | La jerarquía que ingresó no es valida |");
				System.out.println("\n---------------------------------------------------------------------------");
				continue;
			}
		}
		debilidades.setearMax(ultimaFila + 1);
		debilidades.ingresar(new Debilidades(nuevaDebilidad, nuevaJerarquí));
		matriz[ultimaFila][0] = nuevaDebilidad;
		matriz[ultimaFila][1] = Integer.toString(nuevaJerarquí);actualizarDatos(matriz, archivo);
		
		System.out.println("\n-> | SE HA REGISTRADO LA NUEVA DEBILIDAD |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN CINCO-----------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESQUELETO DE LA OPCION CINCO
 	public static void opcionCincoAdmin(Scanner leer,int id, listaIas ias, listaDebilidades debilidad,listaDatosUsuario datosUsuarios ,listaPersonas personas)throws IOException{
		
 		while(true) {
 			int idSeleccionado = 0;
		
 			System.out.println("\n-> | En esta opción podras editar todos los datos de cualquier Usuario: |");
 			System.out.println("\n-> | Acontinuación se desplegarán todas las Usuario que puedes editar: |");
 			System.out.println("\n---------------------------------------------------------------------------");
		
 			for(int i = 0; i < personas.getCantidad() ; i++) {
 				if(id == personas.getId(i)) {
 					continue;
 				}else {
 					System.out.println(".- Nombre de usuario: " + personas.getNombre(i) + "\n.- Contraseña: " + personas.getContraseña(i)+  "\n.- ID: " + personas.getId(i));
 					System.out.println("---------------------------------------------------------------------------");
 				}
			}
		
			while(true) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | SELECCIONE EL ID DEL USUARIO QUE DESEA EDITAR |");
				idSeleccionado = Integer.parseInt(leer.nextLine());
				int i;
				for(i = 0; i < personas.getCantidad(); i++) {
					if(idSeleccionado == personas.getId(i)) {
						break;
					}
				}if(i == ias.getCantidad()) {
					System.out.println("\n-> | EL ID QUE INGRESÓ NO ESTA REGISTRADO |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
				break;
				}
			}
			System.out.println("\n-> | Acontinuación se desplegará un menú con diversas aspectos que puedes editarle a los usuarios: |");
		
		
			String seleccion = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Editar nombre usuario.\n2) Edidar contraseña.\n3) Modificar ID.\n4) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("4") || seleccion.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Editar nombre usuario")) {
				modificarNombreUsuario(idSeleccionado, leer, personas);
			}
			else if(seleccion.equalsIgnoreCase("2") || seleccion.equalsIgnoreCase("Edidar contraseña")) {
				modificarContraseña(idSeleccionado, leer, personas);
			}
			else if(seleccion.equalsIgnoreCase("3") || seleccion.equalsIgnoreCase("Modificar ID")) {
				editarIdUsuario(idSeleccionado, leer, datosUsuarios, personas, ias);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// MODIFICAR NOMBRE DE USUARIO
	public static void modificarNombreUsuario(int id, Scanner leer, listaPersonas personas)throws IOException {
		
		File archivo = new File("usuarios.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | EL NOMBRE ACTUAL DEL USUARIO ES |");
		System.out.println("\n.- Nombre : " + personas.getSoloNombre(personas.getIndice(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		
		while(true) {
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		String nombreUsuario = personas.darUsuario(personas.getIndice(id), seleccion);
		matriz[personas.getIndice(id)][0] = nombreUsuario;actualizarDatos(matriz, archivo);
		personas.setearUsuario(personas.getIndice(id), nombreUsuario);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR CONTRASEÑA USUARIO
	public static void modificarContraseña(int id, Scanner leer, listaPersonas personas)throws IOException {
		
		File archivo = new File("usuarios.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | LA CONTRASEÑA ACTUAL DEL USUARIO ES |");
		System.out.println("\n.- Contraseña : " + personas.getContraseña(personas.getIndice(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE LA NUEVA CONTASEÑA |");
		
		while(true) {
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matriz[personas.getIndice(id)][1] = seleccion;actualizarDatos(matriz, archivo);
		personas.setearContraseña(personas.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR ID
	public static void editarIdUsuario(int id, Scanner leer, listaDatosUsuario datosUsuario, listaPersonas personas, listaIas ias)throws IOException{
				
		File archivo = new File("programadores.txt"),archivoDos = new File("usuarios.txt"),archivoTres = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100), matrizDos = matrizMadre(archivoDos,filas(archivoDos), columnas(archivoDos)),matrizTres = matrizMadre(archivoTres, filas(archivoTres), columnas(archivoTres));
		int seleccion = 0;
				
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + personas.getId(personas.getIndice(id)));
			
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO ID |");
			seleccion = Integer.parseInt(leer.nextLine());
			int i;
			for(i = 0; i < personas.getCantidad();i++) {
				if(seleccion == personas.getId(i)) {
					break;
					}
			}if(i == personas.getCantidad()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
				
			matriz[datosUsuario.getIndice(id)][0] = Integer.toString(seleccion);actualizarDatosProgramador(matriz, archivo);
			matrizDos[personas.getIndice(id)][2] = Integer.toString(seleccion);actualizarDatos(matrizDos, archivoDos);
			matrizTres[ias.getIndice(id)][7] = Integer.toString(seleccion);actualizarDatos(matrizTres, archivoTres);
			datosUsuario.setearId(datosUsuario.getIndice(id), seleccion);
			personas.setearId(personas.getIndice(id), seleccion);
			ias.setearId(ias.getIndice(id), seleccion);
			System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN CUATRO----------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESCQUELETO DE LA OPCION
	public static void opcionCuatroAdmin(Scanner leer,int id, listaIas ias, listaDebilidades debilidad,listaDatosUsuario datosUsuarios ,listaPersonas personas)throws IOException{
		
		while(true) {
			int idSeleccionado = 0;
		
			System.out.println("\n-> | En esta opción podras editar todos los datos de cualquier IA: |");
			System.out.println("\n-> | Acontinuación se desplegarán todas las IA que puedes editar: |");
			System.out.println("\n---------------------------------------------------------------------------");
		
			for(int i = 0; i < ias.getCantidad() ; i++) {
				System.out.println(".- Nombre: " + ias.getNombre(i) + "\n.- Pais: " + ias.getPais(i) + "\n.- Debilidad: " + ias.getDebilidad(i) + "\n.- ID: " + ias.getId(i));;
				System.out.println("---------------------------------------------------------------------------");
			}
		
			while(true) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | SELECCIONE EL ID DEL IA QUE DESEA EDITAR |");
				idSeleccionado = Integer.parseInt(leer.nextLine());
				int i;
				for(i = 0; i < ias.getCantidad(); i++) {
					if(idSeleccionado == ias.getId(i)) {
						break;
					}
				}if(i == ias.getCantidad()) {
					System.out.println("\n-> | EL ID QUE INGRESÓ NO ESTA REGISTRADO |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}
			System.out.println("\n-> | Acontinuación se desplegará un menú con diversas aspectos que puedes editarle a los IA: |");

			String seleccion = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Editar nombre.\n2) Edidar nivel de peligrosidad y debilidad.\n3) Modificar precisión.\n4) Modificar pais.\n5) Modificar ID.\n6) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("6") || seleccion.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Editar nombre")) {
				modificarNombreIa(idSeleccionado, leer, ias);
			}
			else if(seleccion.equalsIgnoreCase("2") || seleccion.equalsIgnoreCase("Edidar nivel de peligrosidad y debilidad")) {
				editarPeligrosidad(idSeleccionado, leer, ias);
				editarDebilidad(idSeleccionado, leer, ias, debilidad);
			}
			else if(seleccion.equalsIgnoreCase("3") || seleccion.equalsIgnoreCase("Modificar precisión")) {
				editarPresicion(idSeleccionado, leer, ias);
			}
			else if(seleccion.equalsIgnoreCase("4") || seleccion.equalsIgnoreCase("Modificar pais")) {
				editarPais(idSeleccionado, leer, ias);
			}
			else if(seleccion.equalsIgnoreCase("5") || seleccion.equalsIgnoreCase("Modificar ID")) {
				editarId(idSeleccionado, leer, datosUsuarios, personas, ias);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// EDITAR NOMBRE IA
	public static void modificarNombreIa(int id, Scanner leer, listaIas ias)throws IOException {
		
		File archivo = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | EL NOMBRE ACTUAL DEL IA ES |");
		System.out.println("\n.- Nombre : " + ias.getNombre(ias.getIndice(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		while(true) {
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matriz[ias.getIndice(id)][0] = seleccion;actualizarDatos(matriz, archivo);
		ias.setearNombe(ias.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR NIVEL DE PELIGROSIDAD
	public static void editarPeligrosidad(int id, Scanner leer, listaIas ias)throws IOException {
		
		File archivo = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		int seleccion = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | NIVEL ACTUAL |");
		System.out.println("\n.- Nivel de amenaza : " + ias.getNivelAmenaza(ias.getIndice(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | RECUERDA QUE EL MINIMO SERIA 1 Y EL MAXIMO NIVEL 5 |");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NIVEL |");
			seleccion = Integer.parseInt(leer.nextLine());
			
			if(seleccion >= 1 &&  seleccion <= 5) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | NIVELES DE PELIGROSIDAD INVALIDOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		
		matriz[ias.getIndice(id)][2] = Integer.toString(seleccion); actualizarDatos(matriz, archivo);
		ias.setearNivelPeligrosidad(ias.getIndice(id), seleccion);
		enter(leer);
	}
	
	// EDITAR DEBILIDAD
	public static void editarDebilidad(int id, Scanner leer, listaIas ias, listaDebilidades debilidades)throws IOException{
		
		File archivo = new File("ia.txt");File archivoDos = new File("debilidades.txt");
		String[] debilidadesDisponibles = new String[filas(archivoDos)];
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String seleccion = "";
		int indice =ias.getIndice(id), cont = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | DEBILIDAD ACTUAL |");
		System.out.println("\n.- Debilidad: " + ias.getDebilidad(ias.getIndice(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | Ahora se desplegará la lista de debilidades validas para este usuario, escoja una sabiamente |\n");
		
		if(ias.getNivelAmenaza(indice) == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
			System.out.println("\n---------------------------------------------------------------------------");
		}else {		
			for(int i = 0; i < debilidades.getMax(); i++) {
				if(ias.getNivelAmenaza(indice) >= debilidades.getJerarquia(i)) {
					System.out.println(".- Debilidad " + debilidades.getDebilidad(i) + ".");
					debilidadesDisponibles[cont] = debilidades.getDebilidad(i);
					cont++;
				}
			}System.out.println("\n---------------------------------------------------------------------------");
		}
		System.out.println("\n-> | Introduzca la debilidad que desea seleccionar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verificador = 0;
			seleccion = leer.nextLine();
			for(int i = 0; i < cont; i++) {
				if(debilidadesDisponibles[i].equalsIgnoreCase(seleccion)) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				if(seleccion.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}else {
				System.out.println("\n-> | La debilidad seleccionada no es valida o no existe. |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		
		ias.setearDebilidad(ias.getIndice(id), seleccion);
		matriz[indice][3] = seleccion;actualizarDatos(matriz, archivo);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR PRECISION
	public static void editarPresicion(int id, Scanner leer, listaIas ias)throws IOException{
		
		File archivo = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		int seleccion = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | PRECISION ACTUAL |");
		System.out.println("\n.- Presición: " + ias.getPrecision(ias.getIndice(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | RECUERDA QUE EL MINIMO SERIA 0% Y EL MAXIMO NIVEL 100% |");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE NUEVA PRESICIÓN |");
			seleccion = Integer.parseInt(leer.nextLine());
			
			if(seleccion >= 0 &&  seleccion <= 100) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | LA PRESISCION SE SALE DE SU RANGO |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		String sleccionFinal = Integer.toString(seleccion) + "%";
		matriz[ias.getIndice(id)][5] = sleccionFinal; actualizarDatos(matriz, archivo);
		ias.setearPrecision(ias.getIndice(id), sleccionFinal);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR PRECISION
	public static void editarPais(int id, Scanner leer, listaIas ias)throws IOException{
		
		File archivo = new File("ia.txt"); File archivoDos = new File("paises.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), columnas(archivo));
		String[] paises = paises(filas(archivoDos));
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ EL PAIS ACTUAL |");
		System.out.println("\n.- Pais: " + ias.getPais(ias.getIndice(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO PAIS DE RESIDENCIA |");
			seleccion = leer.nextLine();
			int i;
			for(i = 0; i < paises.length; i++) {
				if(seleccion.equalsIgnoreCase(paises[i])) {
					break;
				}
			}if(i == paises.length) {
				System.out.println("\n-> | EL PAIS QUE INGRESO EXISTE O NO ESTA EN LA BASE DE DATOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				if(seleccion.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}
		}
		
		matriz[ias.getIndice(id)][4] = seleccion; actualizarDatos(matriz, archivo);
		ias.setearPais(ias.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
		
	}
	
	// MODIFICAR ID
	public static void editarId(int id, Scanner leer, listaDatosUsuario datosUsuario, listaPersonas personas, listaIas ias)throws IOException{
			
		File archivo = new File("programadores.txt"),archivoDos = new File("usuarios.txt"),archivoTres = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100), matrizDos = matrizMadre(archivoDos,filas(archivoDos), columnas(archivoDos)),matrizTres = matrizMadre(archivoTres, filas(archivoTres), columnas(archivoTres));
		int seleccion = 0;
			
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + ias.getId(ias.getIndice(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO ID |");
			seleccion = Integer.parseInt(leer.nextLine());
			int i;
			for(i = 0; i < ias.getCantidad();i++) {
				if(seleccion == ias.getId(i)) {
					break;
				}
			}if(i == ias.getCantidad()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
			
		matriz[datosUsuario.getIndice(id)][0] = Integer.toString(seleccion);actualizarDatosProgramador(matriz, archivo);
		matrizDos[personas.getIndice(id)][2] = Integer.toString(seleccion);actualizarDatos(matrizDos, archivoDos);
		matrizTres[ias.getIndice(id)][7] = Integer.toString(seleccion);actualizarDatos(matrizTres, archivoTres);
		datosUsuario.setearId(datosUsuario.getIndice(id), seleccion);
		personas.setearId(personas.getIndice(id), seleccion);
		ias.setearId(ias.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------OPCIÓN TRES-----------------------------------------------
	
	// TODO LO NECESARIO PARA LA FUNCIÓN TRES
	public static void opcionTresAdmin(Scanner leer, int id, listaLenguajes programadores, listaDatosUsuario datosUsuario,listaPersonas personas, listaIas ias)throws IOException {
		
		System.out.println("\n-> | En esta opción podras editar los datos del programador: |");
		System.out.println("\n-> | Acontinuación se desplegará una serie de datos que se pueden editar: |");
		System.out.println("\n---------------------------------------------------------------------------");
		while(true) {
			String seleccion = "";
			id = personas.getIdConNombre("suricatarabiosa");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Agregar lenguaje.\n2) Años de experiencia.\n3) Modificar pais y region.\n4) Modificar ID.\n5) Modificar nombre.\n6) Modificar apellido.\n7) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			seleccion = leer.nextLine();
			
			if(seleccion.equalsIgnoreCase("7") || seleccion.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(seleccion.equalsIgnoreCase("1") || seleccion.equalsIgnoreCase("Agregar lenguaje")) {
				agregarLenguaje(id, leer, programadores,datosUsuario);
			}
			else if(seleccion.equalsIgnoreCase("2") || seleccion.equalsIgnoreCase("Años de experiencia")) {
				editarAñosExperiencia(id, leer, datosUsuario);
			}
			else if(seleccion.equalsIgnoreCase("3") || seleccion.equalsIgnoreCase("Modificar pais")) {
				modificarPais(id, leer, datosUsuario);
			}
			else if(seleccion.equalsIgnoreCase("4") || seleccion.equalsIgnoreCase("Modificar ID")) {
				modificarId(id, leer, datosUsuario,personas,ias);
			}
			else if(seleccion.equalsIgnoreCase("5") || seleccion.equalsIgnoreCase("Modificar nombre")) {
				modificarNombre(id, leer, datosUsuario);
			}
			else if(seleccion.equalsIgnoreCase("6") || seleccion.equalsIgnoreCase("Modificar apellido")) {
				modificarApellido(id, leer, datosUsuario);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// SELECCION AGREGAR LENGUAJE
	public static void agregarLenguaje(int id, Scanner leer,listaLenguajes programadores, listaDatosUsuario datosUsuarios) throws IOException{
		
		File archivo = new File("programadores.txt");
		String[][] matriz = matrizMadre(archivo, programadores.getMax(), 100);	
		String[] lenguajesExistentes = programadores.getLenguaje(programadores.getIndice(id)), lengueajesNuevos = new String[100], lenguajes = listaProgramadores();
		int indice = programadores.getIndice(id);
		int columna = 4, ind = 0;
		for(int i = 0; i < lenguajesExistentes.length; i++) {
			lengueajesNuevos[i] = lenguajesExistentes[i];
			columna++; ind++;
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE LOS NUEVS LENGUAJES QUE SABE |");System.out.println("\n-> | PARA FINALIZAR EL INGRESO DE DATOS, INGRESE (LISTO) |");
		String lenguaje = "";
		
		while(true) {
			lenguaje = leer.nextLine(); int verificador = 0;
			if(lenguaje.equalsIgnoreCase("listo")) {
				break;
			}
			for(String lenguajen : lenguajesExistentes) {
				if(lenguajen == null) {
					break;
				}else if(lenguajen.equalsIgnoreCase(lenguaje)) {
					System.out.println("-> | EL LENGUAJE QUE INGRESO YA ESTA EXISTENTE |");
					verificador++;
					break;
				}
			}if(verificador == 1) {
				continue;
			}else {
				for(String lenguajen : lenguajes) {
					if(lenguaje.equalsIgnoreCase(lenguajen)) {
						verificador++;
						break;
					}
				}
				if(verificador == 1) {
					lengueajesNuevos[ind] = lenguaje;
					matriz[indice][columna] = lenguaje; 
					System.out.println("\n---------------------------------------------------------------------------");System.out.println("\n-> | INGRESE LOS NUEVS LENGUAJES QUE SABE |");System.out.println("\n-> | PARA FINALIZAR EL INGRESO DE DATOS, INGRESE (LISTO) |");
					columna++;ind++;

					
				}else {
					System.out.println("-> | EL LENGUAJE QUE INGRESO NO ES VALIDO |");
				}
			}
		}int i;
		for(i = 0; i < lengueajesNuevos.length;i++) {
			if(lengueajesNuevos[i] == null) {
				break;
			}
		}
		String[] lenguasSetter = new String[i];
		
		for(int j = 0; j < lenguasSetter.length; j++) {
			lenguasSetter[j] = lengueajesNuevos[j];
		}
		
		programadores.setearLenguajes(programadores.getIndice(id), lenguasSetter);
		
		matriz[indice][columna] = datosUsuarios.getPais(indice);
		columna++;

		matriz[indice][columna] = datosUsuarios.getCiudad(indice); 
		
		actualizarDatosProgramador(matriz, archivo);System.out.println("\n---------------------------------------------------------------------------");System.out.println("\n | LOS LENGUAJES FUERON AÑADIDOS CON EXITO |");enter(leer);
	}
	
	// EDITAR AÑOS DE EXPERIENCIA
	public static void editarAñosExperiencia(int id, Scanner leer, listaDatosUsuario datosUsuarios) throws IOException {
		
		File archivo = new File("programadores.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100);
		int seleccion = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACTUALMENTE SE MOSTRARÁ LOS AÑOS DE EXPERIENCIA ACTUALES |");
		System.out.println("\n.- Año experiencia: " + datosUsuarios.getAñosExperiencia(datosUsuarios.getIndice(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE LOS NUEVOS AÑOS DE EXPERIENCIA |");
			seleccion = Integer.parseInt(leer.nextLine());
			
			if(seleccion < 0 || seleccion > 50) {
				System.out.println("\n-> | LOS AÑOS NO SON LOGICOS |");System.out.println("\n---------------------------------------------------------------------------");
				continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}	
		matriz[datosUsuarios.getIndice(id)][3] = Integer.toString(seleccion); actualizarDatosProgramador(matriz, archivo);
		datosUsuarios.setearAños(datosUsuarios.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR PAIS
	public static void modificarPais(int id, Scanner leer, listaDatosUsuario datosUsuario)throws IOException  {
		
		File archivo = new File("programadores.txt"); File archivoDos = new File("paises.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100);
		String[] paises = paises(filas(archivoDos));
		String seleccion = "",seleccionDos = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU PAIS ACTUAL |");
		System.out.println("\n.- Pais: " + datosUsuario.getPais(datosUsuario.getIndice(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU NUEVO PAIS DE RESIDENCIA |");
			seleccion = leer.nextLine();
			int i;
			for(i = 0; i < paises.length; i++) {
				if(seleccion.equalsIgnoreCase(paises[i])) {
					break;
				}
			}if(i == paises.length) {
				System.out.println("\n-> | EL PAIS QUE INGRESO EXISTE O NO ESTA EN LA BASE DE DATOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		int i,indice = 0;
		int verificador = 0;
		for(i = 0; i < 100;i++) {
			if(matriz[datosUsuario.getIndice(id)][i] == null) {
				break;
			}else {
				for(String pais : paises) {
					if(matriz[datosUsuario.getIndice(id)][i].equalsIgnoreCase(pais)) {
						verificador++;
						indice = i;
					}
				}
			}if(verificador == 1) {
				break;
			}
		}
		String[] regiones = regiones(seleccion);
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁN LAS REGIONES DEL PAIS QUE ESCOGISTE |");
		System.out.println("\n-> | INGRESE EL NOMBRE QUE GUSTE |");
		for(int j = 0; j < regiones.length; j++) {
			if(regiones[j] == null) {
				break;
			}else {
				System.out.println("\n.- region de (" + regiones[j] + ").");
			}
		}
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU NUEVA CIUDAD DE PREFERENCIA |");
			seleccionDos = leer.nextLine();
			int x;
			for(x = 0; x < regiones.length; x++) {
				if(seleccionDos.equalsIgnoreCase(regiones[x])) {
					break;
				}
			}if(x == regiones.length) {
				System.out.println("\n-> | LA REGION QUE INGRESO NO ESTA EN LA BASE DE DATOS O NO EXISTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matriz[datosUsuario.getIndice(id)][indice] = seleccion;	
		matriz[datosUsuario.getIndice(id)][indice + 1] = seleccionDos;	actualizarDatosProgramador(matriz, archivo);
		datosUsuario.setearPais(datosUsuario.getIndice(id), seleccion);
		datosUsuario.setearRegion(datosUsuario.getIndice(id), seleccionDos);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR ID
	public static void modificarId(int id, Scanner leer, listaDatosUsuario datosUsuario, listaPersonas personas, listaIas ias)throws IOException{
		
		File archivo = new File("programadores.txt"),archivoDos = new File("usuarios.txt"),archivoTres = new File("ia.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100), matrizDos = matrizMadre(archivoDos,filas(archivoDos), columnas(archivoDos)),matrizTres = matrizMadre(archivoTres, filas(archivoTres), columnas(archivoTres));
		int seleccion = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + datosUsuario.getId(datosUsuario.getIndice(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU ID |");
			seleccion = Integer.parseInt(leer.nextLine());
			int i;
			for(i = 0; i < datosUsuario.getCant();i++) {
				if(seleccion == datosUsuario.getId(i)) {
					break;
				}
			}if(i == datosUsuario.getCant()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		
		matriz[datosUsuario.getIndice(id)][0] = Integer.toString(seleccion);actualizarDatosProgramador(matriz, archivo);
		matrizDos[personas.getIndice(id)][2] = Integer.toString(seleccion);actualizarDatos(matrizDos, archivoDos);
		matrizTres[ias.getIndice(id)][7] = Integer.toString(seleccion);actualizarDatos(matrizTres, archivoTres);
		datosUsuario.setearId(datosUsuario.getIndice(id), seleccion);
		personas.setearId(personas.getIndice(id), seleccion);
		ias.setearId(ias.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR NOMBRE
	public static void modificarNombre(int id, Scanner leer, listaDatosUsuario datosUsuario)throws IOException {
		
		File archivo = new File("programadores.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100);
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | SU NOMBRE ACTUAL ES |");
		System.out.println("\n.- Nombre : " + datosUsuario.getNombre(datosUsuario.getIndice(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		while(true) {
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matriz[datosUsuario.getIndice(id)][1] = seleccion;actualizarDatosProgramador(matriz, archivo);
		datosUsuario.setearNombre(datosUsuario.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR APELLIDO
	public static void modificarApellido(int id, Scanner leer, listaDatosUsuario datosUsuario)throws IOException {
		
		File archivo = new File("programadores.txt");
		String[][] matriz = matrizMadre(archivo, filas(archivo), 100);
		String seleccion = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | SU APELLIDO ACTUAL ES |");
		System.out.println("\n.- Nombre : " + datosUsuario.getApellido(datosUsuario.getIndice(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO APELLIDO |");
		while(true) {
			seleccion = leer.nextLine();
			if(seleccion.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matriz[datosUsuario.getIndice(id)][2] = seleccion;actualizarDatosProgramador(matriz, archivo);
		datosUsuario.setearApellido(datosUsuario.getIndice(id), seleccion);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(leer);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	 
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
		System.out.println("\n-> | En esta opción podras agregarle una debilidad a cualquier IA de la lista, solo debes indicar el ID del mismo: |\n");
		
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
		
		ias.setearDebilidad(ias.getIndice(id), debilidadSeleccionada);
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
			
			usuarios.setearUsuario(usuarios.getIndice(idUsuario), nombreCompleto);
			matriz[indice][0] = nombreCompleto; actualizarDatos(matriz, archivo);
			System.out.println("\n | EL NOMBRE DE USUARIO FUE ACTUALIZADO CON EXITO |");
			
		}else if(seleccion.equalsIgnoreCase("Contraseña") || seleccion.equalsIgnoreCase("2")) {
			
			System.out.println("\n-> | Ingrese su nueva contraseña |");
			contraseñaNueva = leer.nextLine();
			
			usuarios.setearContraseña(usuarios.getIndice(idUsuario), contraseñaNueva);
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
			
			ias.setearPrecision(ias.getIndice(idIa), precisionFinal);
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
	
	// LISTA DE TIPOS DE IA
	public static String[] listaTiposIa(){

		String[] tiposIa = {"IA autónoma militar","IA supervisora","IA transhumanista","IA social","IA de realidad virtual"};
		return tiposIa;
	}
	
	// RELLENAR EL CONTENEDOR DE DATOS DE LOS PROGRAMADORES.
	public static void rellenarDatosUsuario(listaDatosUsuario lista) throws IOException {
		
		Scanner arch = new Scanner(new File("programadores.txt"));
		for(int i = 0; i < lista.getMax(); i++) {
			String[] linea = arch.nextLine().split(",");
			lista.getIngresar(new datosUsuario(Integer.parseInt(linea[0]), linea[1], linea[2],Integer.parseInt(linea[3]) , linea[linea.length - 2], linea[linea.length - 1]));
		}
	}
	
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
		while(true) {
			System.out.println("\n-> Ingrese su nombre de usuario: ");
			nombreFinal = leer.nextLine();
			int cont = 0;
			int i = 0;
			if(nombreFinal.equalsIgnoreCase("empanadasconchapalele")) {
				indice = i;
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
			if(lista.getContraseña(lista.getIndiceConNombre(contraseña)).equalsIgnoreCase("suricatarabiosa")) {
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
 
