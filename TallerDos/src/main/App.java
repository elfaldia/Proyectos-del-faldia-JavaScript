package main;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class App {

	public static void main(String[] args)throws IOException {
		
		Scanner read = new Scanner(System.in);
		int verification = emptyTxt();
		if(verification == 0) {
			System.out.println("\n----------- EL PROGRAMA ESTA DAÑADO, VUELVA MAS TARDE -----------");
		}else {

			loginComplete(read);
		
			File debilitiesFile = new File("debilidades.txt"); File iaFile = new File("ia.txt"); File userFile = new File("usuarios.txt"); File programersFile = new File("programadores.txt"); File countriesFile = new File("paises.txt");
			userInformationData usersInformation = new userInformationData(row(programersFile));
			usersData users = new usersData(row(userFile));
			languageData language = new languageData(row(programersFile));
			iaData ias = new iaData(row(iaFile));
			debilitiesData debilities = new debilitiesData(row(debilitiesFile));
			fillUserData(usersInformation); fillLanguageData(language, row(countriesFile)); fillIaData(ias); fillDebilitiesData(debilities);fillUsers(users);
			
			int id = finishLogin(users,read);
			
			if(id == -1) {
				System.out.println("\n-> | linea reservada para el menu de administrador. |");
				id = users.getIdWithName("suricatarabiosa");		
				adminMenu(read,id,language,usersInformation,users,ias,debilities);
			} else {
				System.out.println("\n-> | Se ha activado el menu de usuario. |");
				userMenu(id,read,ias,debilities,users,language);
			}
		}read.close();
	}
	
	// ------------------------------------------- FUNCIONES -----------------------------------------------
	
	// -------------------------------------- MENU ADMINISTRADOR -------------------------------------------
	
	// FUNCIÓN QUE EJECUTA EL MENÚ ADMINISTRADOR
	public static void adminMenu(Scanner read,int id, languageData programers,userInformationData userInformation,usersData user, iaData ias,debilitiesData debilities)throws IOException {
		
		String selection = "";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL MENU DE ADMINISTRADOR |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			id = user.getIdWithName("suricatarabiosa");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t\t   | INGRESE LA OPCIÓN DE SU PREFERENCIA |");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Ver todos los programadores\n2) Ver todas las IA.\n3) Editar datos Programador.\n4) Editar datos IA.\n5) Editar datos de Usuario.\n6) Crear y visualizar debilidades.\n7) Crear una IA, programador, País\n8) Dar estadísticas por países\n9) Salir");
			System.out.println("\n---------------------------------------------------------------------------");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("Ver todos los programadores") || selection.equalsIgnoreCase("1")) {
				System.out.println("yan");
			}
			else if(selection.equalsIgnoreCase("Ver todas las IA") || selection.equalsIgnoreCase("2")) {
				System.out.println("yan");
			}
			else if(selection.equalsIgnoreCase("Editar datos Programador") || selection.equalsIgnoreCase("3")) {
				optionThreeAdmin(read, id,programers,userInformation,user,ias);
			}
			else if(selection.equalsIgnoreCase("Editar datos IA") || selection.equalsIgnoreCase("4")) {
				optionFourAdmin(read, id, ias, debilities,userInformation,user);
			}
			else if(selection.equalsIgnoreCase("Editar datos de Usuario") || selection.equalsIgnoreCase("5")) {
				optionFiveAdmin(read, id, ias, debilities,userInformation,user);
			}
			else if(selection.equalsIgnoreCase("Crear y visualizar debilidades") || selection.equalsIgnoreCase("6")) {
				optionSixAdmin(read,debilities);
			}
			else if(selection.equalsIgnoreCase("Crear una IA, programador, País") || selection.equalsIgnoreCase("7")) {
				optionSevenAdmin(read, userInformation, user, ias, debilities,programers);
			}
			else if(selection.equalsIgnoreCase("Dar estadísticas por países") || selection.equalsIgnoreCase("8")) {
				System.out.println("yan");
			}
			else if(selection.equalsIgnoreCase("salir") || selection.equalsIgnoreCase("9")) {
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

	public static void optionSevenAdmin(Scanner read,userInformationData userInformation, usersData user, iaData ias,debilitiesData denilities,languageData languages) throws IOException {
		
		System.out.println("\n-> | En esta opción podras Crear nuevos usuarios, programadores, Ias y paises: |");
		System.out.println("\n-> | Acontinuación se desplegará el menu: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			String selection = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Crear nuevo usuario completo.\n2) Crear pais.\n3) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("3") || selection.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Crear nuevo usuario completo")) {
				createNewGeneralUser(read, denilities, ias, user, userInformation, languages);
			}
			else if(selection.equalsIgnoreCase("2") || selection.equalsIgnoreCase("Crear pais")) {
				addCountry(read);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// FUNCION QUE NOS DEJARÁ REGISTRAR NUEVOS PAISES
	public static void addCountry(Scanner read)throws IOException{
		
		File file = new File("paises.txt");
		String[][] matrix = matrixGenerator(file,( row(file) + 1), 100);
		int lastRow = matrix.length - 1, column = 0;
		String country = "",city = "";
		
		System.out.println("\n-> | EN ESTE SEGMENTO PUEDES INGRESAR UN PAIS CON SUS RESPECTIVAS REGIONES |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		System.out.println("\n-> | INGRESE EL PAIS: |");
		System.out.println("\n---------------------------------------------------------------------------");

		while(true) {
			country = read.nextLine();
			
			if(country.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}matrix[lastRow][column] = country;column++;
		System.out.println("\n-> | PARA PARAR DE AGREGAR REGIONES INGRESE LISTO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			System.out.println("\n-> | INGRESE UNA REGION DE ESTE PAIS: |");
			city = read.nextLine();
			
			if(city.equalsIgnoreCase("")) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				if(city.equalsIgnoreCase("listo")) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					matrix[lastRow][column] = city;column++;
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}
			}
		}
		updateProgramerData(matrix, file);
		System.out.println("\n-> | SE HA REGISTRADO EL PAIS |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// FUNCION PARA LA PRIMERA OPCIÓN Y ES LA QUE CREA UN USUARIO
	public static void createNewGeneralUser(Scanner read, debilitiesData debilities, iaData ias, usersData user, userInformationData userInformation,languageData language)throws IOException{
		
		File debilityFile = new File("debilidades.txt"),userFile = new File("usuarios.txt"), userInformationFile = new File("programadores.txt"), iaFile = new File("ia.txt"), contriesFile = new File("paises.txt");
		String[][] userMatrix = matrixGenerator(userFile, (row(userFile) + 1), columns(userFile)), userInformationMatrix = matrixGenerator(userInformationFile, (row(userInformationFile) + 1), 100), iaMatrix = matrixGenerator(iaFile,(row(iaFile) + 1), columns(iaFile));
		String[] existingLanguages = programingLanguagesList(), inputLanguages = new String[100], countries = contries(row(contriesFile));
		int lastRow = userMatrix.length-1;
		int languageIndex = 4, identification = (int)(Math.random()*(99999 - 10000) + 10000);
		String name = "",lastName = "", password = "", inputLanguage ="",country = "", city = "";
		int id = 0, experience = 0;
		
		System.out.println("\n-> | En esta opción podras crear un usuario general: |");
		System.out.println("\n-> | Primero tomaremos los datos de la persona: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		// ------------------------------- NOMBRE USUARIO --------------------------------------------------------------
		
		System.out.println("\n-> | Ingresa tu nombre: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			name = read.nextLine();
			if(name.equalsIgnoreCase("")) {
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
			lastName = read.nextLine();
			if(lastName.equalsIgnoreCase("")) {
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
			password = read.nextLine();
			if(password.equalsIgnoreCase("")) {
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
			id = Integer.parseInt(read.nextLine());
			int i;
			for(i = 0; i < user.getCant();i++) {
				if(id == user.getId(i)) {
					break;
					}
			}if(i == user.getCant()) {
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
			experience = Integer.parseInt(read.nextLine());
			
			if(experience > 0 && experience < 100) {
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
		int index = 0;
		
		while(true) {
			int comprobation = 0;
			System.out.println("\n\t- Ingrese lenguaje: ");
			inputLanguage = read.nextLine();
			
			if(inputLanguage.equalsIgnoreCase("listo")) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				if(inputLanguage.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacia |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					for(String i : existingLanguages) {
						if(inputLanguage.equalsIgnoreCase(i)) {
							comprobation++;
							break;
						}else {
							continue;
						}
					}		
					if(comprobation == 1) {
						userInformationMatrix[lastRow][languageIndex] = inputLanguage;
						inputLanguages[index] = inputLanguage; index++;
						languageIndex++;		System.out.println("\n---------------------------------------------------------------------------");
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
			int comprobation = 0;
			country = read.nextLine();
			
			if(country.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String i: countries) {
					if(country.equalsIgnoreCase(i)) {
						comprobation++;
						break;
					}else {
						continue;
					}
				}if(comprobation == 1) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					System.out.println("\n | El pais que ingreso no esta en la base de datos. | ");
					System.out.println("\n---------------------------------------------------------------------------");
				}
			}
		}
		
		// ------------------------------- REGION USUARIO --------------------------------------------------------------
		
		String[] cities = cities(country);
		System.out.println("\n-> | Ingrese su región: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			int comprobation = 0;
			city = read.nextLine();
			
			if(city.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String i: cities) {
					if(city.equalsIgnoreCase(i)) {
						comprobation++;
						break;
					}else {
						continue;
					}
				}if(comprobation == 1) {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}else {
					System.out.println("\n | La región no corresponde al pais. | ");
					System.out.println("\n---------------------------------------------------------------------------");
				}
			}
		}
		user.setterMax(lastRow +1);language.setterMax(lastRow+1);userInformation.setterMax(lastRow+1);ias.setterMax(lastRow+1);
		
		// SE CREA USUARIO Y ACTUALIZA EL TXT
		userMatrix[lastRow][0] = name + "#" + Integer.toString(identification);
		userMatrix[lastRow][1] = password;
		userMatrix[lastRow][2] = Integer.toString(id); updateData(userMatrix, userFile);
		user.getInto(new Users(name + "#" + Integer.toString(identification),password,id));
		
		// SE CREAN LOS DATOS DEL USAURIO Y ACTUALIZAR EL TXT
		userInformationMatrix[lastRow][0] = Integer.toString(id);
		userInformationMatrix[lastRow][1] = name;
		userInformationMatrix[lastRow][2] = lastName;
		userInformationMatrix[lastRow][3] = Integer.toString(experience);
		userInformationMatrix[lastRow][languageIndex] = country;languageIndex++;
		userInformationMatrix[lastRow][languageIndex] = city; updateProgramerData(userInformationMatrix, userInformationFile);
		userInformation.getInto(new userInformation(id, name, lastName, experience, country, city));
		
		// SE GUARDAN LOS LENGUAJES	
		int i;
		for( i = 0; i < inputLanguages.length;i++) {
			if(inputLanguages[i] == null) {
				break;
			}
		}String[] lengaujesFinal = new String[i];
		for(int j = 0; j < lengaujesFinal.length; j++) {
			lengaujesFinal[j] = inputLanguages[j];
		}
		language.getInto(new Language(Integer.toString(id), lengaujesFinal));
		
		String[] iaTypes = iaTypesList();
		String iaName = "", iaLanguage = "", debility = "", iaType = "";
		int presicion = 0, hierarchy = 0;
		
		System.out.println("\n-> | Ahora tomaremos los datos para la IA: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		// ------------------------------- NOMBRE IA --------------------------------------------------------------
		
		System.out.println("\n-> | Ingrese el nombre: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			iaName = read.nextLine();
			if(iaName.equalsIgnoreCase("")) {
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
			int comprobation = 0;
			System.out.println("\n\t- Ingrese lenguaje: ");
			iaLanguage = read.nextLine();
			
			if(iaLanguage.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacia |");
				System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				for(String n : lengaujesFinal) {
				if(iaLanguage.equalsIgnoreCase(n)) {
						comprobation++;
						break;
					}else {
						continue;
					}
				}		
				if(comprobation == 1) {
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
			hierarchy = Integer.parseInt(read.nextLine());
			
			if(hierarchy > 0 && hierarchy < 6) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | la jerarquia se sale del rango |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		System.out.println("\n-> | Ingrese su debilidad: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		String[] debilitiesAvailable = new String[row(debilityFile)];
		int cont = 0;
		
		if(hierarchy == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
			System.out.println("\n---------------------------------------------------------------------------");
		}else {		
			for( i = 0; i < debilities.getAmount(); i++) {
				if(hierarchy >= debilities.getHierarchy(i)) {
					System.out.println("\n.- Debilidad " + debilities.getDebility(i) + ".");
					debilitiesAvailable[cont] = debilities.getDebility(i);
					cont++;
				}
			}System.out.println("\n---------------------------------------------------------------------------");
		}
		while(true) {
			int verification = 0;
			debility = read.nextLine();
			for( i = 0; i < cont; i++) {
				if(debilitiesAvailable[i].equalsIgnoreCase(debility)) {
					verification++;
					break;
				}
			}
			if(verification == 1) {
				if(debility.equalsIgnoreCase("")) {
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
			presicion = Integer.parseInt(read.nextLine());
			
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
			iaType = read.nextLine();
			for( i = 0; i < iaTypes.length; i++) {
				if(iaTypes[i].equalsIgnoreCase(iaType)) {
					verificador++;
					break;
				}
			}
			if(verificador == 1) {
				if(iaType.equalsIgnoreCase("")) {
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
		iaMatrix[lastRow][0] = iaName;
		iaMatrix[lastRow][1] = iaLanguage;
		iaMatrix[lastRow][2] = Integer.toString(hierarchy);
		iaMatrix[lastRow][3] = debility;
		iaMatrix[lastRow][4] = country;
		iaMatrix[lastRow][5] = Integer.toString(presicion) + "%";
		iaMatrix[lastRow][6] = iaType;
		iaMatrix[lastRow][7] = Integer.toString(id);updateData(iaMatrix, iaFile);
		ias.getInto(new Ias(iaName, iaLanguage, hierarchy, debility, country, Integer.toString(presicion) + "%", iaType, id));
		System.out.println("\n-> | SE HA REGISTRADO EL NUEVO USUARIO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");

	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN SEIS------------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESQUELETO DE LA OPCION SEIS
	public static void optionSixAdmin(Scanner read,debilitiesData debilities) throws IOException {
		
		System.out.println("\n-> | En esta opción podras visualizar o cread debilidades para los IA: |");
		System.out.println("\n-> | Acontinuación se desplegará el menu: |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			String selection = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Visualizar todas las debilidades.\n2) Crear debilidad.\n3) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("3") || selection.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Visualizar todas las debilidades")) {
				seeDebilities(debilities, read);
			}
			else if(selection.equalsIgnoreCase("2") || selection.equalsIgnoreCase("Crear debilidad")) {
				createNewDebility(read, debilities);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
		
	}
	
	// VISUALIZAR DEBILIDADES
	public static void seeDebilities(debilitiesData debilities,Scanner read) {
		
		System.out.println("\n-> | ACONTINUACION SE DESPLEGARÁN TODAS LAS DEBILIDADES EXISTENTES HASTA EL MOMENTO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		for(int i = 0; i < debilities.getAmount(); i++) {
			System.out.println("\n.- Nombre de la debilidad: " + debilities.getDebility(i) + "\n.- Jerarquía: " + debilities.getHierarchy(i));System.out.println("\n---------------------------------------------------------------------------");
		}enter(read);
	}
	
	// CREAR UNA DEBILIDAD
	public static void createNewDebility(Scanner read, debilitiesData debility) throws IOException{
		
		String newDebility = "";int newHierarchy = 0;
		File file = new File("debilidades.txt");
		String[][] matrix = matrixGenerator(file,( row(file) + 1), columns(file));
		int lastRow = matrix.length -1;
		System.out.println("\n-> | En esta opción podrás creas nuevas debilidades para los IA |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		System.out.println("\n-> Ingrese la nueva debilidad: ");
		
		while(true) {
			newDebility = read.nextLine();
			int i;
			for(i = 0; i < debility.getAmount();i++) {
				if(newDebility.equalsIgnoreCase(debility.getDebility(i))) {
					break;
				}
			}if(i == debility.getAmount()) {
				
				if(newDebility.equalsIgnoreCase("")) {
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
			newHierarchy = Integer.parseInt(read.nextLine());
			
			if(newHierarchy > 0 && newHierarchy < 6) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | La jerarquía que ingresó no es valida |");
				System.out.println("\n---------------------------------------------------------------------------");
				continue;
			}
		}
		debility.setterMax(lastRow + 1);
		debility.getInto(new Debilities(newDebility, newHierarchy));
		matrix[lastRow][0] = newDebility;
		matrix[lastRow][1] = Integer.toString(newHierarchy);updateData(matrix, file);
		
		System.out.println("\n-> | SE HA REGISTRADO LA NUEVA DEBILIDAD |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN CINCO-----------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESQUELETO DE LA OPCION CINCO
 	public static void optionFiveAdmin(Scanner read,int id, iaData ias, debilitiesData debility,userInformationData userInformation ,usersData user)throws IOException{
		
 		while(true) {
 			int selectedId = 0;
		
 			System.out.println("\n-> | En esta opción podras editar todos los datos de cualquier Usuario: |");
 			System.out.println("\n-> | Acontinuación se desplegarán todas las Usuario que puedes editar: |");
 			System.out.println("\n---------------------------------------------------------------------------");
		
 			for(int i = 0; i < user.getCant() ; i++) {
 				if(selectedId == user.getId(i)) {
 					continue;
 				}else {
 					System.out.println(".- Nombre de usuario: " + user.getName(i) + "\n.- Contraseña: " + user.getPassword(i)+  "\n.- ID: " + user.getId(i));
 					System.out.println("---------------------------------------------------------------------------");
 				}
			}
		
			while(true) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | SELECCIONE EL ID DEL USUARIO QUE DESEA EDITAR |");
				selectedId = Integer.parseInt(read.nextLine());
				int i;
				for(i = 0; i < user.getCant(); i++) {
					if(selectedId == user.getId(i)) {
						break;
					}
				}if(i == ias.getAmount()) {
					System.out.println("\n-> | EL ID QUE INGRESÓ NO ESTA REGISTRADO |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
				break;
				}
			}
			System.out.println("\n-> | Acontinuación se desplegará un menú con diversas aspectos que puedes editarle a los usuarios: |");
		
		
			String selection = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Editar nombre usuario.\n2) Edidar contraseña.\n3) Modificar ID.\n4) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("4") || selection.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Editar nombre usuario")) {
				changeUserName(selectedId, read, user);
			}
			else if(selection.equalsIgnoreCase("2") || selection.equalsIgnoreCase("Edidar contraseña")) {
				changePassword(selectedId, read, user);
			}
			else if(selection.equalsIgnoreCase("3") || selection.equalsIgnoreCase("Modificar ID")) {
				editUserId(selectedId, read, userInformation, user, ias);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// MODIFICAR NOMBRE DE USUARIO
	public static void changeUserName(int id, Scanner read, usersData user)throws IOException {
		
		File file = new File("usuarios.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | EL NOMBRE ACTUAL DEL USUARIO ES |");
		System.out.println("\n.- Nombre : " + user.getJustName(user.getIndex(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		
		while(true) {
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		String nombreUsuario = user.obteinUser(user.getIndex(id), selection);
		matrix[user.getIndex(id)][0] = nombreUsuario;updateData(matrix, file);
		user.setterUser(user.getIndex(id), nombreUsuario);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR CONTRASEÑA USUARIO
	public static void changePassword(int id, Scanner read, usersData user)throws IOException {
		
		File file = new File("usuarios.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | LA CONTRASEÑA ACTUAL DEL USUARIO ES |");
		System.out.println("\n.- Contraseña : " + user.getPassword(user.getIndex(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE LA NUEVA CONTASEÑA |");
		
		while(true) {
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matrix[user.getIndex(id)][1] = selection;updateData(matrix, file);
		user.setterPassword(user.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR ID
	public static void editUserId(int id, Scanner read, userInformationData userInformation, usersData user, iaData ias)throws IOException{
				
		File programerFile = new File("programadores.txt"),userFile = new File("usuarios.txt"),iaFile = new File("ia.txt");
		String[][] programerMatrix = matrixGenerator(programerFile, row(programerFile), 100), userMatrix = matrixGenerator(userFile,row(userFile), columns(userFile)),iaMatrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		int selection = 0;
				
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + user.getId(user.getIndex(id)));
			
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO ID |");
			selection = Integer.parseInt(read.nextLine());
			int i;
			for(i = 0; i < user.getCant();i++) {
				if(selection == user.getId(i)) {
					break;
					}
			}if(i == user.getCant()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;		
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
				
			programerMatrix[userInformation.getIndex(id)][0] = Integer.toString(selection);updateProgramerData(programerMatrix, programerFile);
			userMatrix[user.getIndex(id)][2] = Integer.toString(selection);updateData(userMatrix, userFile);
			iaMatrix[ias.getIndex(id)][7] = Integer.toString(selection);updateData(iaMatrix, iaFile);
			userInformation.setterId(userInformation.getIndex(id), selection);
			user.setterId(user.getIndex(id), selection);
			ias.setterId(ias.getIndex(id), selection);
			System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");	}
	
	//------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------OPCIÓN CUATRO----------------------------------------------
	
	// FUNCION QUE CONTIENE EL ESCQUELETO DE LA OPCION
	public static void optionFourAdmin(Scanner read,int id, iaData ias, debilitiesData debility,userInformationData userInformation ,usersData user)throws IOException{
		
		while(true) {
			int selectedId = 0;
		
			System.out.println("\n-> | En esta opción podras editar todos los datos de cualquier IA: |");
			System.out.println("\n-> | Acontinuación se desplegarán todas las IA que puedes editar: |");
			System.out.println("\n---------------------------------------------------------------------------");
		
			for(int i = 0; i < ias.getAmount() ; i++) {
				System.out.println(".- Nombre: " + ias.getName(i) + "\n.- Pais: " + ias.getCountry(i) + "\n.- Debilidad: " + ias.getDebility(i) + "\n.- ID: " + ias.getId(i));;
				System.out.println("---------------------------------------------------------------------------");
			}
		
			while(true) {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | SELECCIONE EL ID DEL IA QUE DESEA EDITAR |");
				selectedId = Integer.parseInt(read.nextLine());
				int i;
				for(i = 0; i < ias.getAmount(); i++) {
					if(selectedId == ias.getId(i)) {
						break;
					}
				}if(i == ias.getAmount()) {
					System.out.println("\n-> | EL ID QUE INGRESÓ NO ESTA REGISTRADO |");
					System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}
			System.out.println("\n-> | Acontinuación se desplegará un menú con diversas aspectos que puedes editarle a los IA: |");

			String selection = "";
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Editar nombre.\n2) Edidar nivel de peligrosidad y debilidad.\n3) Modificar precisión.\n4) Modificar pais.\n5) Modificar ID.\n6) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("6") || selection.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Editar nombre")) {
				changeIaName(selectedId, read, ias);
			}
			else if(selection.equalsIgnoreCase("2") || selection.equalsIgnoreCase("Edidar nivel de peligrosidad y debilidad")) {
				changeThreat(selectedId, read, ias);
				editDebility(selectedId, read, ias, debility);
			}
			else if(selection.equalsIgnoreCase("3") || selection.equalsIgnoreCase("Modificar precisión")) {
				editPresicion(selectedId, read, ias);
			}
			else if(selection.equalsIgnoreCase("4") || selection.equalsIgnoreCase("Modificar pais")) {
				changeCountry(selectedId, read, ias);
			}
			else if(selection.equalsIgnoreCase("5") || selection.equalsIgnoreCase("Modificar ID")) {
				editId(selectedId, read, userInformation, user, ias);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// EDITAR NOMBRE IA
	public static void changeIaName(int id, Scanner read, iaData ias)throws IOException {
		
		File file = new File("ia.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | EL NOMBRE ACTUAL DEL IA ES |");
		System.out.println("\n.- Nombre : " + ias.getName(ias.getIndex(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		while(true) {
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matrix[ias.getIndex(id)][0] = selection;updateData(matrix, file);
		ias.setterName(ias.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR NIVEL DE PELIGROSIDAD
	public static void changeThreat(int id, Scanner read, iaData ias)throws IOException {
		
		File file = new File("ia.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		int selection = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | NIVEL ACTUAL |");
		System.out.println("\n.- Nivel de amenaza : " + ias.getThreat(ias.getIndex(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | RECUERDA QUE EL MINIMO SERIA 1 Y EL MAXIMO NIVEL 5 |");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NIVEL |");
			selection = Integer.parseInt(read.nextLine());
			
			if(selection >= 1 &&  selection <= 5) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | NIVELES DE PELIGROSIDAD INVALIDOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		
		matrix[ias.getIndex(id)][2] = Integer.toString(selection); updateData(matrix, file);
		ias.setterThreat(ias.getIndex(id), selection);
		enter(read);
	}
	
	// EDITAR DEBILIDAD
	public static void editDebility(int id, Scanner read, iaData ias, debilitiesData debility)throws IOException{
		
		File iaFile = new File("ia.txt");File debilityFile = new File("debilidades.txt");
		String[] debilitiesAvailable = new String[row(debilityFile)];
		String[][] matrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		String selection = "";
		int index =ias.getIndex(id), cont = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | DEBILIDAD ACTUAL |");
		System.out.println("\n.- Debilidad: " + ias.getDebility(ias.getIndex(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | Ahora se desplegará la lista de debilidades validas para este usuario, escoja una sabiamente |\n");
		
		if(ias.getThreat(index) == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
			System.out.println("\n---------------------------------------------------------------------------");
		}else {		
			for(int i = 0; i < debility.getMax(); i++) {
				if(ias.getThreat(index) >= debility.getHierarchy(i)) {
					System.out.println(".- Debilidad " + debility.getDebility(i) + ".");
					debilitiesAvailable[cont] = debility.getDebility(i);
					cont++;
				}
			}System.out.println("\n---------------------------------------------------------------------------");
		}
		System.out.println("\n-> | Introduzca la debilidad que desea seleccionar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verification = 0;
			selection = read.nextLine();
			for(int i = 0; i < cont; i++) {
				if(debilitiesAvailable[i].equalsIgnoreCase(selection)) {
					verification++;
					break;
				}
			}
			if(verification == 1) {
				if(selection.equalsIgnoreCase("")) {
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
		
		ias.setterDebility(ias.getIndex(id), selection);
		matrix[index][3] = selection;updateData(matrix, iaFile);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR PRECISION
	public static void editPresicion(int id, Scanner read, iaData ias)throws IOException{
		
		File file = new File("ia.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		int selection = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | PRECISION ACTUAL |");
		System.out.println("\n.- Presición: " + ias.getPrecision(ias.getIndex(id)));
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | RECUERDA QUE EL MINIMO SERIA 0% Y EL MAXIMO NIVEL 100% |");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE NUEVA PRESICIÓN |");
			selection = Integer.parseInt(read.nextLine());
			
			if(selection >= 0 &&  selection <= 100) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | LA PRESISCION SE SALE DE SU RANGO |");
				System.out.println("\n---------------------------------------------------------------------------");
			}
		}
		String finalSelection = Integer.toString(selection) + "%";
		matrix[ias.getIndex(id)][5] = finalSelection; updateData(matrix, file);
		ias.setterPresicion(ias.getIndex(id), finalSelection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// EDITAR PRECISION
	public static void changeCountry(int id, Scanner read, iaData ias)throws IOException{
		
		File iaFile = new File("ia.txt"); File countriesFile = new File("paises.txt");
		String[][] matrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		String[] contries = contries(row(countriesFile));
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ EL PAIS ACTUAL |");
		System.out.println("\n.- Pais: " + ias.getCountry(ias.getIndex(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO PAIS DE RESIDENCIA |");
			selection = read.nextLine();
			int i;
			for(i = 0; i < contries.length; i++) {
				if(selection.equalsIgnoreCase(contries[i])) {
					break;
				}
			}if(i == contries.length) {
				System.out.println("\n-> | EL PAIS QUE INGRESO EXISTE O NO ESTA EN LA BASE DE DATOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				if(selection.equalsIgnoreCase("")) {
					System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
				}else {
					System.out.println("\n---------------------------------------------------------------------------");
					break;
				}
			}
		}
		
		matrix[ias.getIndex(id)][4] = selection; updateData(matrix, iaFile);
		ias.setterCounty(ias.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
		
	}
	
	// MODIFICAR ID
	public static void editId(int id, Scanner read, userInformationData userInformation, usersData user, iaData ias)throws IOException{
			
		File programerFile = new File("programadores.txt"),userFile = new File("usuarios.txt"),iaFile = new File("ia.txt");
		String[][] programerMatrix = matrixGenerator(programerFile, row(programerFile), 100), userMatrix = matrixGenerator(userFile,row(userFile), columns(userFile)),iaMatrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		int selection = 0;
			
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + ias.getId(ias.getIndex(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE EL NUEVO ID |");
			selection = Integer.parseInt(read.nextLine());
			int i;
			for(i = 0; i < ias.getAmount();i++) {
				if(selection == ias.getId(i)) {
					break;
				}
			}if(i == ias.getAmount()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
			
		programerMatrix[userInformation.getIndex(id)][0] = Integer.toString(selection);updateProgramerData(programerMatrix, programerFile);
		userMatrix[user.getIndex(id)][2] = Integer.toString(selection);updateData(userMatrix, userFile);
		iaMatrix[ias.getIndex(id)][7] = Integer.toString(selection);updateData(iaMatrix, iaFile);
		userInformation.setterId(userInformation.getIndex(id), selection);
		user.setterId(user.getIndex(id), selection);
		ias.setterId(ias.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	
	//--------------------------------------------OPCIÓN TRES-----------------------------------------------
	
	// TODO LO NECESARIO PARA LA FUNCIÓN TRES
	public static void optionThreeAdmin(Scanner read, int id, languageData programers, userInformationData userInformation,usersData user, iaData ias)throws IOException {
		
		System.out.println("\n-> | En esta opción podras editar los datos del programador: |");
		System.out.println("\n-> | Acontinuación se desplegará una serie de datos que se pueden editar: |");
		System.out.println("\n---------------------------------------------------------------------------");
		while(true) {
			String selection = "";
			id = user.getIdWithName("suricatarabiosa");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Agregar lenguaje.\n2) Años de experiencia.\n3) Modificar pais y region.\n4) Modificar ID.\n5) Modificar nombre.\n6) Modificar apellido.\n7) Volver al menu inicial.");System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | Ingrese la opción que desea ejecutar: |");
			selection = read.nextLine();
			
			if(selection.equalsIgnoreCase("7") || selection.equalsIgnoreCase("Volver al menu inicial")) {
				break;
			}
			else if(selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Agregar lenguaje")) {
				addLanguage(id, read, programers,userInformation);
			}
			else if(selection.equalsIgnoreCase("2") || selection.equalsIgnoreCase("Años de experiencia")) {
				editExperience(id, read, userInformation);
			}
			else if(selection.equalsIgnoreCase("3") || selection.equalsIgnoreCase("Modificar pais")) {
				changeCountryTwo(id, read, userInformation);
			}
			else if(selection.equalsIgnoreCase("4") || selection.equalsIgnoreCase("Modificar ID")) {
				changeIdTwo(id, read, userInformation,user,ias);
			}
			else if(selection.equalsIgnoreCase("5") || selection.equalsIgnoreCase("Modificar nombre")) {
				changeNameTwo(id, read, userInformation);
			}
			else if(selection.equalsIgnoreCase("6") || selection.equalsIgnoreCase("Modificar apellido")) {
				changeLastName(id, read, userInformation);
			}
			else {
				System.out.println("\n---------------------------------------------------------------------------");
				System.out.println("\n-> | EL MENÚ QUE INGRESO NO ES VALIDO, INTENTE NUEVAMENTE |");
				System.out.println("\n---------------------------------------------------------------------------");		
			}
		}
	}
	
	// SELECCION AGREGAR LENGUAJE
	public static void addLanguage(int id, Scanner read,languageData programers, userInformationData userInformation) throws IOException{
		
		File file = new File("programadores.txt");
		String[][] matrix = matrixGenerator(file, programers.getMax(), 100);	
		String[] existingLanguages = programers.getLanguages(programers.getIndex(id)), newLanguages = new String[100], lenguajes = programingLanguagesList();
		int index = programers.getIndex(id);
		int column = 4, ind = 0;
		for(int i = 0; i < existingLanguages.length; i++) {
			newLanguages[i] = existingLanguages[i];
			column++; ind++;
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE LOS NUEVS LENGUAJES QUE SABE |");System.out.println("\n-> | PARA FINALIZAR EL INGRESO DE DATOS, INGRESE (LISTO) |");
		String language = "";
		
		while(true) {
			language = read.nextLine(); int verificator = 0;
			if(language.equalsIgnoreCase("listo")) {
				break;
			}
			for(String lenguajen : existingLanguages) {
				if(lenguajen == null) {
					break;
				}else if(lenguajen.equalsIgnoreCase(language)) {
					System.out.println("-> | EL LENGUAJE QUE INGRESO YA ESTA EXISTENTE |");
					verificator++;
					break;
				}
			}if(verificator == 1) {
				continue;
			}else {
				for(String lenguajen : lenguajes) {
					if(language.equalsIgnoreCase(lenguajen)) {
						verificator++;
						break;
					}
				}
				if(verificator == 1) {
					newLanguages[ind] = language;
					matrix[index][column] = language; 
					System.out.println("\n---------------------------------------------------------------------------");System.out.println("\n-> | INGRESE LOS NUEVS LENGUAJES QUE SABE |");System.out.println("\n-> | PARA FINALIZAR EL INGRESO DE DATOS, INGRESE (LISTO) |");
					column++;ind++;

					
				}else {
					System.out.println("-> | EL LENGUAJE QUE INGRESO NO ES VALIDO |");
				}
			}
		}int i;
		for(i = 0; i < newLanguages.length;i++) {
			if(newLanguages[i] == null) {
				break;
			}
		}
		String[] languageSetter = new String[i];
		
		for(int j = 0; j < languageSetter.length; j++) {
			languageSetter[j] = newLanguages[j];
		}
		
		programers.setterLanguages(programers.getIndex(id), languageSetter);
		
		matrix[index][column] = userInformation.getCountry(index);
		column++;

		matrix[index][column] = userInformation.getCity(index); 
		
		updateProgramerData(matrix, file);System.out.println("\n---------------------------------------------------------------------------");System.out.println("\n | LOS LENGUAJES FUERON AÑADIDOS CON EXITO |");enter(read);
	}
	
	// EDITAR AÑOS DE EXPERIENCIA
	public static void editExperience(int id, Scanner read, userInformationData userInformation) throws IOException {
		
		File file = new File("programadores.txt");
		String[][] matrix = matrixGenerator(file, row(file), 100);
		int selection = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACTUALMENTE SE MOSTRARÁ LOS AÑOS DE EXPERIENCIA ACTUALES |");
		System.out.println("\n.- Año experiencia: " + userInformation.getExperience(userInformation.getIndex(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE LOS NUEVOS AÑOS DE EXPERIENCIA |");
			selection = Integer.parseInt(read.nextLine());
			
			if(selection < 0 || selection > 50) {
				System.out.println("\n-> | LOS AÑOS NO SON LOGICOS |");System.out.println("\n---------------------------------------------------------------------------");
				continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}	
		matrix[userInformation.getIndex(id)][3] = Integer.toString(selection); updateProgramerData(matrix, file);
		userInformation.setterYears(userInformation.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR PAIS
	public static void changeCountryTwo(int id, Scanner read, userInformationData userInformation)throws IOException  {
		
		File programersFile = new File("programadores.txt"); File countriesFile = new File("paises.txt");
		String[][] matrix = matrixGenerator(programersFile, row(programersFile), 100);
		String[] contries = contries(row(countriesFile));
		String selection = "",selectionTwo = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU PAIS ACTUAL |");
		System.out.println("\n.- Pais: " + userInformation.getCountry(userInformation.getIndex(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU NUEVO PAIS DE RESIDENCIA |");
			selection = read.nextLine();
			int i;
			for(i = 0; i < contries.length; i++) {
				if(selection.equalsIgnoreCase(contries[i])) {
					break;
				}
			}if(i == contries.length) {
				System.out.println("\n-> | EL PAIS QUE INGRESO EXISTE O NO ESTA EN LA BASE DE DATOS |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		int i,indice = 0;
		int verication = 0;
		for(i = 0; i < 100;i++) {
			if(matrix[userInformation.getIndex(id)][i] == null) {
				break;
			}else {
				for(String pais : contries) {
					if(matrix[userInformation.getIndex(id)][i].equalsIgnoreCase(pais)) {
						verication++;
						indice = i;
					}
				}
			}if(verication == 1) {
				break;
			}
		}
		String[] cities = cities(selection);
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁN LAS REGIONES DEL PAIS QUE ESCOGISTE |");
		System.out.println("\n-> | INGRESE EL NOMBRE QUE GUSTE |");
		for(int j = 0; j < cities.length; j++) {
			if(cities[j] == null) {
				break;
			}else {
				System.out.println("\n.- region de (" + cities[j] + ").");
			}
		}
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU NUEVA CIUDAD DE PREFERENCIA |");
			selectionTwo = read.nextLine();
			int x;
			for(x = 0; x < cities.length; x++) {
				if(selectionTwo.equalsIgnoreCase(cities[x])) {
					break;
				}
			}if(x == cities.length) {
				System.out.println("\n-> | LA REGION QUE INGRESO NO ESTA EN LA BASE DE DATOS O NO EXISTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matrix[userInformation.getIndex(id)][indice] = selection;	
		matrix[userInformation.getIndex(id)][indice + 1] = selectionTwo;	updateProgramerData(matrix, programersFile);
		userInformation.setterCountry(userInformation.getIndex(id), selection);
		userInformation.setterCity(userInformation.getIndex(id), selectionTwo);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR ID
	public static void changeIdTwo(int id, Scanner read, userInformationData userInformation, usersData user, iaData ias)throws IOException{
		
		File programersFile = new File("programadores.txt"),userFile = new File("usuarios.txt"),iaFile = new File("ia.txt");
		String[][] programersMatrix = matrixGenerator(programersFile, row(programersFile), 100), userMatrix = matrixGenerator(userFile,row(userFile), columns(userFile)),iaMatrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		int selection = 0;
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | ACONTINUACIÓN SE MOSTRARÁ SU ID ACTUAL |");
		System.out.println("\n.- ID : " + userInformation.getId(userInformation.getIndex(id)));
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n-> | INGRESE SU ID |");
			selection = Integer.parseInt(read.nextLine());
			int i;
			for(i = 0; i < userInformation.getAmount();i++) {
				if(selection == userInformation.getId(i)) {
					break;
				}
			}if(i == userInformation.getAmount()) {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}else {
				System.out.println("\n-> | EL ID QUE INGRESO YA ES EXISTENTE |");
				System.out.println("\n---------------------------------------------------------------------------");
			}	
		}
		
		programersMatrix[userInformation.getIndex(id)][0] = Integer.toString(selection);updateProgramerData(programersMatrix, programersFile);
		userMatrix[user.getIndex(id)][2] = Integer.toString(selection);updateData(userMatrix, userFile);
		iaMatrix[ias.getIndex(id)][7] = Integer.toString(selection);updateData(iaMatrix, iaFile);
		userInformation.setterId(userInformation.getIndex(id), selection);
		user.setterId(user.getIndex(id), selection);
		ias.setterId(ias.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR NOMBRE
	public static void changeNameTwo(int id, Scanner read, userInformationData userInformation)throws IOException {
		
		File file = new File("programadores.txt");
		String[][] matrix = matrixGenerator(file, row(file), 100);
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | SU NOMBRE ACTUAL ES |");
		System.out.println("\n.- Nombre : " + userInformation.getName(userInformation.getIndex(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO NOMBRE |");
		while(true) {
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matrix[userInformation.getIndex(id)][1] = selection;updateProgramerData(matrix, file);
		userInformation.setterName(userInformation.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	// MODIFICAR APELLIDO
	public static void changeLastName(int id, Scanner read, userInformationData userInformation)throws IOException {
		
		File file = new File("programadores.txt");
		String[][] matrix = matrixGenerator(file, row(file), 100);
		String selection = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | SU APELLIDO ACTUAL ES |");
		System.out.println("\n.- Nombre : " + userInformation.getLastName(userInformation.getIndex(id)));
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n-> | INGRESE SU NUEVO APELLIDO |");
		while(true) {
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("")) {
				System.out.println("\n-> | Linea vacía |");	System.out.println("\n---------------------------------------------------------------------------");continue;
			}else {
				System.out.println("\n---------------------------------------------------------------------------");
				break;
			}
		}
		
		matrix[userInformation.getIndex(id)][2] = selection;updateProgramerData(matrix, file);
		userInformation.setterLastName(userInformation.getIndex(id), selection);
		System.out.println("\n-> | LOS DATOS SE HAN ACTUALIZADO CON EXITO |");System.out.println("\n---------------------------------------------------------------------------");enter(read);System.out.println("\n---------------------------------------------------------------------------");
	}
	
	//------------------------------------------------------------------------------------------------------
	 
	// ----------------------------------------- MENU USUARIOS ---------------------------------------------
	
	// FUNCION QUE CONTENDRA EL MENU DE USUARIO
 	public static void userMenu(int userId,Scanner read,iaData ias,debilitiesData debilities,usersData user,languageData languages) throws IOException{
		
		String selection = "";
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL MENU DE USUARO |");
		System.out.println("\n---------------------------------------------------------------------------");
		
		while(true) {
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t\t   | INGRESE LA OPCIÓN DE SU PREFERENCIA |");
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n1) Agregar debilidad a la IA.\n2) Modificar datos usuario.\n3) Modificar precisión de una IA.\n4) Ver IAs.\n5) Ver tipo de IAs.\n6) Salir.");
			System.out.println("\n---------------------------------------------------------------------------");
			selection = read.nextLine();
			
			// 1RA OPCION, AGREGAR DEBILIDADES A LAS IA.
			if(selection.equalsIgnoreCase("Agregar debilidad a la IA") || selection.equalsIgnoreCase("1")) {
				firstOption(read,ias,debilities); 
			}
			// 2DA OPCION, AGREGAR DEBILIDADES A LAS IA.
			else if(selection.equalsIgnoreCase("Modificar datos usuario") || selection.equalsIgnoreCase("2")) {
				secondOption(userId,read,user); 
			}
			// 3RA OPCION, AGREGAR DEBILIDADES A LAS IA.
			else if(selection.equalsIgnoreCase("Modificar precisión de una IA") || selection.equalsIgnoreCase("3")) {
				thirdOption(read,ias,languages,userId); 
			}
			else if(selection.equalsIgnoreCase("ver ias") || selection.equalsIgnoreCase("4")) {
				fourthOption(read,ias);
			}
			else if(selection.equalsIgnoreCase("ver tipo de ias") || selection.equalsIgnoreCase("5")) {
				quintaOpcion(read,ias);
			}				
			// CIERRE DEL MENU (Y DEL BUCLE)
			else if(selection.equalsIgnoreCase("salir") || selection.equalsIgnoreCase("6")) {
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
	public static void firstOption(Scanner read, iaData ias, debilitiesData debilities) throws IOException{
		
		File iaFile = new File("ia.txt"); File debilityFile = new File("debilidades.txt");
		int[] comprobationList = new int[row(iaFile)]; String[] debilitiesAvailable = new String[row(debilityFile)];
		int cont = 0, contTwo = 0;
		int index = 0;
		String[][] iaMatrix = matrixGenerator(iaFile, row(iaFile), columns(iaFile));
		int id = 0; String selectedDebility = "";
		System.out.println("\n-> | En esta opción podras agregarle una debilidad a cualquier IA de la lista, solo debes indicar el ID del mismo: |\n");
		
		for(int i = 0; i < ias.getMax(); i++) {
			if(ias.getDebility(i).equalsIgnoreCase("desconocida")) {
				System.out.println(".- Nombre IA: " + ias.getName(i) + " el ID de este IA es " + ias.getId(i));
				comprobationList[cont] = ias.getId(i);
				cont++;
			}
		}	
		
		System.out.println("\n-> | Introduzca el ID del IA que desea modificar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verification = 0;
			id = Integer.parseInt(read.nextLine());
			for(int i = 0; i < cont; i++) {
				if(id == comprobationList[i]) {
					verification++;
					break;
				}
			}
			if(verification == 1) {
				break;
			}else {
				System.out.println("\n-> | El ID ingresado no es valido |");
			}
		}
		
		for(int i = 0; i < ias.getMax() ; i++) {
			if(ias.getId(i) == id) {
				index = i;
				break;
			}
		}
		
		System.out.println("\n-> | Ahora se desplegará la lista de debilidades validas para este usuario, escoja una sabiamente |\n");
		
		if(ias.getThreat(index) == 1) {
			System.out.println("\n | Lo siento, pero este IA no puede recibir debilidades ya que es de nivel 1. |");
		}else {		
			for(int i = 0; i < debilities.getMax(); i++) {
				if(ias.getThreat(index) >= debilities.getHierarchy(i)) {
					System.out.println(".- Debilidad " + debilities.getDebility(i) + ".");
					debilitiesAvailable[contTwo] = debilities.getDebility(i);
					contTwo++;
				}
			}
		}
		System.out.println("\n-> | Introduzca la debilidad que desea seleccionar |");
		
		// BUCLE DE COMPROBACIÓN PARA LOS ID
		while(true) {
			int verification = 0;
			selectedDebility = read.nextLine();
			for(int i = 0; i < contTwo; i++) {
				if(debilitiesAvailable[i].equalsIgnoreCase(selectedDebility)) {
					verification++;
					break;
				}
			}
			if(verification == 1) {
				break;
			}else {
				System.out.println("\n-> | La debilidad seleccionada no es valida o no existe. |");
			}
		}
		
		ias.setterDebility(ias.getIndex(id), selectedDebility);
		iaMatrix[index][3] = selectedDebility;
		updateData(iaMatrix, iaFile);
		System.out.println("\n | LA DEBILIDAD HA SIDO IMPLEMENTADA EN LA IA " + ias.getName(index) + " |");
		
		enter(read);
	}

	// FUNCION PARA LA 2DA OPCION
	public static void secondOption(int userId,Scanner read, usersData user)throws IOException {
		
		File file = new File("usuarios.txt");
		String selection = ""; int index = 0; String newName = "", newPassword = "";
		String[][] matrix = matrixGenerator(file, row(file),columns(file));
		
		for(int i = 0; i < user.getMax(); i++) {
			if(userId == user.getId(i)) {
				index = i;
				break;
			}
		}
		
		System.out.println("\n-> | En esta opción podras editar tu nombre de usuario o tu contraseña |");
		System.out.println("\n1) Nombre Usuario.\n2) Contraseña.");
		System.out.println("\n-> | Introduzca tu opción |");
		while(true) {
			
			selection = read.nextLine();
			if(selection.equalsIgnoreCase("Nombre Usuario") || selection.equalsIgnoreCase("1") || selection.equalsIgnoreCase("Contraseña") || selection.equalsIgnoreCase("2")) {
				break;
			}else{
				System.out.println("\n-> | El menu que ingreso no es valido. |");
			}
		}
		
		if(selection.equalsIgnoreCase("Nombre Usuario") || selection.equalsIgnoreCase("1")) {
			
			System.out.println("\n-> | Ingrese su nuevo nombre de usuario |");
			newName = read.nextLine();
			String idDelUsuario = Integer.toString((int)(Math.random()*(99999 - 10000) + 10000));
			String nombreCompleto = newName + "#" + idDelUsuario;
			
			user.setterUser(user.getIndex(userId), nombreCompleto);
			matrix[index][0] = nombreCompleto; updateData(matrix, file);
			System.out.println("\n | EL NOMBRE DE USUARIO FUE ACTUALIZADO CON EXITO |");
			
		}else if(selection.equalsIgnoreCase("Contraseña") || selection.equalsIgnoreCase("2")) {
			
			System.out.println("\n-> | Ingrese su nueva contraseña |");
			newPassword = read.nextLine();
			
			user.setterPassword(user.getIndex(userId), newPassword);
			matrix[index][1] = newPassword; updateData(matrix, file);
			System.out.println("\n | LA CONTRASEÑA DEL USUARIO FUE ACTUALIZADO CON EXITO |");
		}
		enter(read);
	}
	
	// FUNCION PARA LA 3RA OPCION
	public static void thirdOption(Scanner read, iaData ias,languageData language, int id)throws IOException{
		
		File file = new File("ia.txt");
		String[][] matrix = matrixGenerator(file, row(file), columns(file));
		String[] dominatedLanguages = language.getLanguages(language.getIndex(id));
		int[] idAvailable = new int[language.getMax()];
		int cont = 0;
		System.out.println("\n-> | En esta opción podras editar los IA que fueron desarrollados con los lenguajes que dominas |");
		System.out.println("\n-> | Se desplegara una lista con los IA que puedes editar |");
		
		for(int i = 0; i < language.getMax(); i++) {
			for(String lenguaje : dominatedLanguages) {
				if(lenguaje.equalsIgnoreCase(ias.getLanguage(i))) {
					idAvailable[cont] = ias.getId(i);
					cont++; break;
				}
			}
		}
		int verification = 0;
		for(int ids : idAvailable) {
			if(ids == 0) {
				verification++;
			}else {
				System.out.println("\n--------------------------------------\n.- Nombre del IA: " + ias.getName(ias.getIndex(ids)) + "\n.- Presición: " + ias.getPrecision(ias.getIndex(ids)) + "\n.- ID: " + ias.getId(ias.getIndex(ids)) + "\n--------------------------------------");
			}
		}
		
		if(verification != language.getMax()) {
			int idIa = 0,aux = 0;
			System.out.println("\n--------------------------------------------------------");
			System.out.println("\n-> | INGRESA EL ID DE LA IA QUE DESEA EDITAR |");
			System.out.println("\n--------------------------------------------------------");
			
			while(true) {
				aux = 0;
				idIa = Integer.parseInt(read.nextLine());
				for(int ids : idAvailable) {
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
				precision = Integer.parseInt(read.nextLine());
				if(precision <= 100 && precision >= 0) {
					break;
				}else{
					System.out.println("\n-> | LA PRESICIÓN QUE INGRESO NO ES VALIDA |");
					System.out.println("\n--------------------------------------------------------");
				}
			}
			String finalPrecision = Integer.toString(precision) + "%";
			
			ias.setterPresicion(ias.getIndex(idIa), finalPrecision);
			matrix[ias.getIndex(idIa)][5] = finalPrecision; updateData(matrix, file);
			System.out.println("\n--------------------------------------------------------"); System.out.println("\n | LA CONTRASEÑA DEL USUARIO FUE ACTUALIZADO CON EXITO |");	
			enter(read);
		}else {
			System.out.println("\n--------------------------------------------------------------------------");
			System.out.println("\n-> | NO EXISTE NINGUNA IA DESEÑADA CON LOS LEGUAJES QUE MANEJAS |");
			System.out.println("\n--------------------------------------------------------------------------");enter(read);
		}
	}
	
	// FUNCION PARA LA 4TA OPCIÓN
	private static void fourthOption(Scanner read, iaData ias) {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("\n\t-> | BASE DE DATOS DE IAS REGISTRADAS |");
		System.out.println("\n--------------------------------------------------------");
		for(int i = 0; i < ias.getMax(); i++) {
			ias.aux4rdOption(i);

			while(true) {
				System.out.println("\n--------------------------------------------------------");
				System.out.println("\n\t| PRESIONA ENTER PARA VER LA SIGUIENTE |");
				System.out.println("\n--------------------------------------------------------");
				String scout = read.nextLine();

				if(scout.equalsIgnoreCase("")) {
					break;
				}else {
					continue;
				}
			}
		}
		enter(read);
	}	
	
	// FUNCION PARA LA 5TA OPCIÓN
	private static void quintaOpcion(Scanner read, iaData ias) {
		
		//PEQUEÑO FRAGMENTO DE CODIGO PARA CREAR UNA LISTA SIN REPETICION DE LOS TIPOS DE IA SEGUN EL TXT
		boolean isHere = false;
		String[] list = new String[5];
		for(int i = 0; i < list.length; i++) {
			for(int j = 0; j <ias.getMax();j++) {
				if(i == 0) {list[i] = ias.getType(j);}
				else {
					isHere = false;
					for(int x = 0; x < i;x++) {
						if(list[x].equals(ias.getType(j))) {isHere = true;}}
					if(isHere == false) {
						list[i] = ias.getType(j);}}}}
		
		//System.out.println(Arrays.toString(lista));
		//INICIO DEL MENU COMO TAL

	System.out.println("\n--------------------------------------------------------"); System.out.println("\n-> | INGRESE EL TIPO DE IA QUE QUIERE REVISAR |"); System.out.println("\n--------------------------------------------------------");
	for(int i = 0; i < list.length; i++) {
		System.out.println("\n.- Tipo de IA: "+ list[i].toUpperCase());
	}System.out.println("\n--------------------------------------------------------");
	System.out.println("");
	String scout = read.nextLine();
	int x = 0;
	while(x == 0) {
		for(int i = 0; i < list.length; i++) {
			//System.out.println(scout+" "+lista[i]);
			if(scout.equalsIgnoreCase(list[i])){x++;}}
		if(x == 0) {System.out.println("\n-> | ERROR EN LA SINTAXIS ESCRIBE TAL CUAL COMO SE MUESTRA EN LA PANTALLA |");
		System.out.println("\n--------------------------------------------------------");
			scout = read.nextLine();	}
	}
	//PRINTEO DE LAS IAS QUE SE MOSTRARAN POR CONSOLA
	System.out.println("\n--------------------------------------------------------");
	System.out.println("\n-> | LOS IA DE ESTE TIPO SON LOS SIGUIENTES |");
	System.out.println("\n--------------------------------------------------------");
	for(int i = 0; i < ias.getMax(); i++) {
		if(scout.equals(ias.getType(i))) {
			System.out.println("\n.- Nombre: "+ias.getName(i));}}
	
	System.out.println("\n--------------------------------------------------------"); System.out.println("\n\t | PRESIONA ENTER PARA IR AL MENÚ |"); System.out.println("\n--------------------------------------------------------");
	scout = read.nextLine();}
	
	
	// -----------------------------------------------------------------------------------------------------------
	
	// ------------------------------------------- LOGIN / COMPLEMENTOS -------------------------------------------
	
	// LISTA DE TIPOS DE IA
	public static String[] iaTypesList(){

		String[] iaTypes = {"IA autónoma militar","IA supervisora","IA transhumanista","IA social","IA de realidad virtual"};
		return iaTypes;
	}
	
	// RELLENAR EL CONTENEDOR DE DATOS DE LOS PROGRAMADORES.
	public static void fillUserData(userInformationData list) throws IOException {
		
		Scanner file = new Scanner(new File("programadores.txt"));
		for(int i = 0; i < list.getMax(); i++) {
			String[] line = file.nextLine().split(",");
			list.getInto(new userInformation(Integer.parseInt(line[0]), line[1], line[2],Integer.parseInt(line[3]) , line[line.length - 2], line[line.length - 1]));
		}
	}
	
	//CIERRE DE OPCIÓN
	public static void enter(Scanner read) {
		while(true) {
			System.out.println("\n---------------------------------------------------------");
			System.out.println("\n\t[PRESIONA ENTER PARA IR AL MENÚ]");
			String scout = read.nextLine();
			System.out.println("\n---------------------------------------------------------");

			if(scout.equalsIgnoreCase("")) {
				break;
			}else {
				continue;
			}
		}
	}
	
	// RELLENAR CONTENEDOR DE LOS IA
	public static void fillDebilitiesData(debilitiesData list) throws IOException{
			
			Scanner file = new Scanner(new File("debilidades.txt"));	
			for(int i = 0; i < list.getMax(); i++){		
				String[] line = file.nextLine().split(",");
				list.getInto(new Debilities(line[0],Integer.parseInt(line[1])));
			}
		}
	
	// FUNCION PARA IMPRIMIR MATRIZ
	public static void seeMatrix(String[][] matrix) {
	
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(j != matrix[0].length - 1) {
					System.out.print(matrix[i][j] + ",");
				}else {
					System.out.println(matrix[i][j]);
				}
			}System.out.println("");
		}
	}
	
	// RELLENAR CONTENEDOR DE LOS IA
	public static void fillIaData(iaData list) throws IOException{
		
		Scanner file = new Scanner(new File("ia.txt"));	
		for(int i = 0; i < list.getMax(); i++){		
			String[] line = file.nextLine().split(",");
			list.getInto(new Ias(line[0],line[1],Integer.parseInt(line[2]),line[3],line[4],line[5],line[6],Integer.parseInt(line[7])));
		}
	}
	
	// RELLENAR EL CONTENEDOR DE DATOS DE LOS PROGRAMADORES.
	public static void fillLanguageData(languageData languages, int limitCountries) throws IOException {
		
		Scanner file = new Scanner(new File("programadores.txt"));
		for(int i = 0; i < languages.getMax(); i++) {
			String[] line = file.nextLine().split(",");
			String[] lineTwo = getLine(line, limitCountries);
			languages.getInto(new Language(line[0], lineTwo));
			//DESCOMENTALO PARA VER EL CONTENEDOR
		}

	}

	// COMPLEMENTO QUE NOS DA LA LISTAS DE LOS LENGUAJES DE PROGRAMACION DOMINADOS POR CADA PERSONA.
	public static String[] getLine(String[] list, int countryIndex) throws IOException {
			Scanner file = new Scanner(new File("paises.txt"));
			String[] countriesList = new String[countryIndex];
			int paisindex = 0;
			while(file.hasNextLine()) {
				String[] parte = file.nextLine().split(",");
				countriesList[paisindex] = parte[0];
				paisindex++;}
			int veri = 4; //como empieza por la posicion 5 del txt
			int difference = -4; //para sacer el verdadero indice de la lista
			int index = 0; //indice de la lista
			boolean stop = false;
			
			for(int i = veri; i < list.length; i++) {
				for(int j = 0; j < countryIndex; j++) {
					if(list[veri].equals(countriesList[j])) {
						stop = true;
					}
				}
				if(stop == true) {
					break;
				}
				veri++;
			}
	
			index = veri + difference;
			String[] finalList = new String[index];
			for(int i = 0; i < index; i++) {
				finalList[i] = list[i+4];
			}
		return finalList;
	}

	// RELLENAR EL CONTENEDOR DE USUARIOS.		
	public static void fillUsers(usersData user) throws IOException{
		
		Scanner file = new Scanner(new File("usuarios.txt"));

		for(int i = 0; i < user.getMax(); i++) {
			String[] line = file.nextLine().split(",");
			user.getInto(new Users(line[0],line[1],Integer.parseInt(line[2])));
		}
	}
	
	// FUNCION QUE TERMINARA EL INGRESO AL SISTEMA.
	public static int finishLogin(usersData list, Scanner read) {
		
		String finalName = "";
		int index = 0;
		String[] names = new String[list.getCant()];
		for(int i = 0; i < list.getCant(); i++) {

			String name = list.getName(i);
			String[] divide = name.split("#");
			names[i] = divide[0];
		}
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| LOG IN - INICIO DE SESIÓN |");
		System.out.println("\n---------------------------------------------------------------------------");
		while(true) {
			System.out.println("\n-> Ingrese su nombre de usuario: ");
			finalName = read.nextLine();
			int cont = 0;
			int i = 0;
			if(finalName.equalsIgnoreCase("empanadasconchapalele")) {
				index = i;
				break;
			}
			for(String nombre : names) {
				if(nombre.equalsIgnoreCase(finalName)) {
					index = i;
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
			String password = "";
			System.out.println("\n-> Ingrese su contraseña: ");
			password = read.nextLine();
			if(list.getPassword(list.getIndexWithName(password)).equalsIgnoreCase("suricatarabiosa")) {
				return -1;	
			}
			
			if(password.equalsIgnoreCase(list.getPassword(index))) {
				break;
			}else {
				System.out.println("\n- Contraseña erronea intente nuevamente.");
			}
			
		}

		return list.getId(index);	
	}
	
	// SELECCIÓN DEL MENÚ, SI ESTE DESEA INGRESAR AL SISTEMA O REGISTRAR OTRO USUARIO.
 	public static void loginComplete(Scanner read) throws IOException {
		
		String verification = "";
		
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n\t\t\t| BIENVENIDO AL SISTEMA |");
		System.out.println("\n---------------------------------------------------------------------------");
		System.out.println("\n- ¿Desea registrarse o inciar sesión?\n1) Sing in.\n2) Log in.\n");
		
		while(true) {
			verification = read.nextLine();
			if(verification.equalsIgnoreCase("1") || verification.equalsIgnoreCase("2")|| verification.equalsIgnoreCase("sing in")|| verification.equalsIgnoreCase("log in")) {
				break;
			}else {
				System.out.println("\n-- La opción ingresada es invalida, intente nuevamente.");
				continue;
			}
		}
		
		// SI LA OPCIÓN ES SING IN DEBEMOS SEGUIR PARA AGREGAR NUEVOS DATOS A LOS ARCHIVOS TXT 
		// PARA ASI PODER PROSEGUIR CON ARMAR LOS USUARIOS
		if(verification.equalsIgnoreCase("1") || verification.equalsIgnoreCase("sing in")) {
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n- || Para poder registrarte necesitamos que nos des tu perfil de programador ||: ");
			System.out.println("\n---------------------------------------------------------------------------");
			
			File userFile = new File("usuarios.txt");
			File programerFile = new File("programadores.txt");
			File contryFile = new File("paises.txt");

			String[][] userMatrix = matrixGenerator(userFile,(row(userFile) + 1),columns(userFile));
			String[][] programersFile = matrixGenerator(programerFile, (row(userFile) + 1), 100);
			
			// CREAR UN NUEVO USUARIO
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese su nombre: ");
			String name = read.nextLine();
			// CREO EL ID DE 4 DIGITOS
			String userId = Integer.toString((int)(Math.random()*(99999 - 10000) + 10000));
			// UNIMOS EL ID COMPLETO.
			String user = name + "#" + userId;
			
			// CREAR TU APELLIDO
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese su apellido: ");
			String lastName = read.nextLine();
			
			// CREAR LA CONTRASEÑA
			
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Ingrese la contraseña: ");
			String password = read.nextLine();
			
			// CREAR AÑOS DE EXPERIENCIA

			String experience = Integer.toString((int)(Math.random()*5));
			
			// INGRESAMOS EL NUEVO PROGRAMADOR EN EL ARCHIVO DE TEXTO
			
			int lastRow = userMatrix.length -1;
			int index = 4;
			String city = "";
			String language = "";
			String country = "";
			String[] languages =  programingLanguagesList();
			String[] countries = contries(row(contryFile));
			
			programersFile[lastRow][0] = Integer.toString(userMatrix.length);
			programersFile[lastRow][1] = name;
			programersFile[lastRow][2] = lastName;
			programersFile[lastRow][3] = experience;
			// INGRESAREMOS LOS LENGUAJES DE PROGRAMACION QUE POSEE
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Acontinuación nos dirá uno a uno los lenguajes de programación que sabe, si ya ingreso todos coloque (listo): ");
			while(true) {
				int comprobation = 0;
				System.out.println("\n\t- Ingrese lenguaje: ");
				language = read.nextLine();
				
				if(language.equalsIgnoreCase("listo")) {
					break;
				}
				
				for(String i : languages) {
					if(language.equalsIgnoreCase(i)) {
						comprobation++;
						break;
					}else {
						continue;
					}
				}
				
				if(comprobation == 1) {
					programersFile[lastRow][index] = language;
					index++;
				}else {
					System.out.println("\n\t- La opción ingresada no es un lenguaje de programación, intente nuevamente.");
				}	
			}
			// INGRESAMOS LOS PAISES
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Introduzca su pais de residencia: ");
			
			while(true) {
				country = read.nextLine();
				int cont = 0;
				for(String i : countries) {
					if(country.equalsIgnoreCase(i)) {
						cont++;
						break;
					}else {
						continue;
					}
				}
				if(cont == 1) {
					programersFile[lastRow][index] = country;
					index++;
					break;
				}else {
					System.out.println("\n\t- El pais que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS REGION
			String[] cities = cities(country);
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- Introduzca la región donde vive: ");
			
			while(true) {
				city = read.nextLine();
				int cont = 0;
				for(String i : cities) {
					if(i == null) {
						break;
					}else {
						if(city.equalsIgnoreCase(i)) {
								cont++;
								break;
						}else {
								continue;
						}
					}
				}
				if(cont == 1) {
					programersFile[lastRow][index] = city;
					index++;
					break;
				}else {
					System.out.println("\n\t- La region que ingreso es erroneo intente nuevamente.");
					continue;
				}
			}
			// INGRESAMOS EL NUEVO USUARIO EN EL ARCHIVO DE TEXTO
			
			userMatrix[lastRow][0] = user; userMatrix[lastRow][1] = password; userMatrix[lastRow][2] = Integer.toString(userMatrix.length);
			
			// ACTUALIZAR EL TXT
			updateData(userMatrix, userFile);
			updateProgramerData(programersFile, programerFile);
			System.out.println("\n---------------------------------------------------------------------------");
			System.out.println("\n\t- SIGN IN FINALIZADO!.\n\t- Ahota puedes pasar al Log in.");
		}
		
	}
	
	// LISTA DE REGIONES SEGUN EL PAIS ESCOGIDO.
	public static String[] cities(String country)throws IOException{
		
		Scanner file = new Scanner(new File("paises.txt"));
		String[] list = new String[100];
		int cont = 0;
		
		while(file.hasNextLine()) {
			
			String[] line = file.nextLine().split(",");
			if(line[0].equalsIgnoreCase(country)) {
				for(int i = 1; i < line.length; i++) {
					if(line[i] == null) {
						break;
					}else {
						list[cont] = line[i];
						cont++;
						continue;
					}
				}
			}else {
				continue;
			}
		}return list;

	}
	
	// LISTA DE PAISES
	public static String[] contries(int index)throws IOException{
		
		Scanner file = new Scanner(new File("paises.txt"));
		String[] list = new String[index];
		int cont = 0;
		
		while(file.hasNextLine()) {
			String[] line = file.nextLine().split(",");
			list[cont] = line[0];
			cont++;
		}return list;
	}
	
	// LISTA DE TODOS LOS LENGUAJES DE PROGRAMACION.
 	public static String[] programingLanguagesList() {
		
		String[] list = {"c","c++","c#","Java","Python","PHP","SQL","Ruby","Visual Basic NET","R" ,"TypeScript","Swift","Rust","Go","Kotlin","Postscript","Scheme","Erlang","Elixir","Pascal","Scala","Objective-C"};
		return list;
	}
	
	// MATRIZ QUE CONTIENE LOS DATOS DE LOS DATOS ACTUALIZADOS HASTA EL MOMENTO.
	public static String[][] matrixGenerator(File file,int row, int column) throws IOException{	 
		
		String[][] matrix = new String[row][column];
		Scanner archive = new Scanner(file);
		int cont = 0;
		
		while(archive.hasNextLine()) {
			String[] linea = archive.nextLine().split(",");
			
			for(int i = 0; i < linea.length; i++) {
				matrix[cont][i] = linea[i];
			}
			cont++;
		}

		archive.close();
		return matrix;
	}
	
	// SACAR LAS FILAS PARA LA MATRIZ.
	public static int row(File file) throws IOException{
		Scanner archive = new Scanner(file);
		int cont = 0;
		
		while(archive.hasNextLine()) {
			archive.nextLine();
			cont++;
		}
		archive.close();
		return cont;
	}
	
	// SACAR LAS FILAS PARA LA MATRIZ.
	public static int columns(File file) throws IOException{
			
		Scanner archive = new Scanner(file);
		int lon = 0;
			
		while(archive.hasNextLine()) {
				
			String[] line = archive.nextLine().split(",");
			lon = line.length;
				
		}
		archive.close();
		return lon;
	}
	
	// LISTA DEL LARGO DE LAS COLUMNAS.
	public static int[] columnList(String[][] matrix){
			
		int[] list = new int[matrix.length];
		int cont = 0;
		int index = 0;
		
		for(int i = 0; i < matrix.length; i++) {
			cont = 0;
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == null || j == matrix[0].length - 1 ) {
					list[index] = cont;
					index++;
					break;
				}else {
					cont++;
				}
			}
		}return list;
	}
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DEL ARCHIVO TXT PROGRAMADOR.
	public static void updateProgramerData(String[][] matrix, File file) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    int[] columns = columnList(matrix);
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < columns[i]; j++) {
	        	if (j != columns[i] - 1) {
        			writer.write(matrix[i][j] + ",");
        		}else {
        			writer.write(matrix[i][j]);	        		
        		}
	        }
	        if(i != matrix.length -1) {
	        	writer.newLine();
	        }
	    } writer.close();
	}
	
	// FUNCION QUE ACTUALIZARA LOS DATOS DE CUALQUIER TXT.
	public static void updateData(String[][] matrix, File file) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    for (int i = 0; i < matrix.length; i++) {
	        for (int j = 0; j < matrix[0].length; j++) {
	            writer.write(matrix[i][j]);
	            if (j !=  matrix[0].length - 1) {
	                writer.write(",");
	            }
	        }if(i != matrix.length - 1) {
	        writer.newLine();
	        }
	    }
	    writer.close();
	}

	// FUNCION DE COMPROBACIÓN DE DATOS, EN EL CASO QUE LOS TXT ESTEN VACIOS.
	public static int emptyTxt()throws IOException{
		
		int verification = vacuumEmpty("usuarios.txt");
		if(verification == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS USUARIOS ESTA VACIO");
		}
		verification = vacuumEmpty("programadores.txt");
		if(verification == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS PROGRAMADORES ESTA VACIO");
		}
		verification = vacuumEmpty("ia.txt");
		if(verification == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS IA ESTA VACIO");
		}
		verification = vacuumEmpty("paises.txt");
		if(verification == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LOS PAISES ESTA VACIO");
		}
		verification = vacuumEmpty("debilidades.txt");
		if(verification == 0) {
			System.out.println("\n- LA BASE DE DATOS DE LAS DEBILIDADES ESTA VACIO");
		}
		return verification;
	}
	
	// SUMA DATOS DE LOS TXT (APOYO DE LA FUNCION TXTVACIO).
	public static int vacuumEmpty(String file) throws IOException{
		
		Scanner archive = new Scanner(new File(file));
		int cont = 0;
		while(archive.hasNextLine()) {
			archive.nextLine();
			cont++;
		}return cont;
	}
	
	// -----------------------------------------------------------------------------------------------------------
	
}
 
