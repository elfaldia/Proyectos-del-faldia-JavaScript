package curso;

public class enumeracionDos {

	public static void main(String[] args) {
		
		Dias[] diasSemana = {Dias.LUNES,Dias.MARTES,Dias.MIERCOLES,Dias.JUEVES,Dias.VIERNES,Dias.SABADO,Dias.DOMINGO};
		
		for(Dias dia : diasSemana) {
			indicarDias(dia);
		}
		
	}
	public static void indicarDias(Dias dia) {
		switch (dia) {
		
			case LUNES:
				System.out.println("Primer dia de la semana.");
				break;
			
			case MARTES:
				System.out.println("Segundo dia de la semana.");
				break;
		
			case MIERCOLES:
				System.out.println("Tercer dia de la semana.");
				break;
			
			case JUEVES:
				System.out.println("Cuarto dia de la semana.");
				break;
			
			case VIERNES:
				System.out.println("Quinto dia de la semana.");
				break;
			
			case SABADO:
				System.out.println("Sexto dia de la semana.");
				break;
				
			case DOMINGO:
				System.out.println("Ultimo dia de la semana.");
				break;
		}
	}
}
