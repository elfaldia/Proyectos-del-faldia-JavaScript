package main;


public class listaIas {
	
	private int cant, max;
	private Ias[] lista;
	
	public listaIas(int max) {
		this.max = max;
		this.cant = 0;
		this.lista = new Ias[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getCantidad() {
		return this.cant;
	}
	
	public int getIndice(int id) {
		
		int indice = 0;
		for(int i = 0; i < cant; i++) {
			if(id == lista[i].getId()) {
				indice = i;
				break;
			}
		}return indice;
	}
	
	public Ias getIas(int indice) {	
		if(indice < cant) {
			return lista[indice];
	
		}return null;
	}
	
	public String getLenguaje(int i) {
		if(i < cant) {
			return lista[i].getLenguaje();
		}else {
			return null;
		}
	}
	public int getNivelAmenaza(int indice) {
		if(indice < cant) {
			return lista[indice].getNivelAmenaza();
		}else {
			return 0;
		}
	}	

	public String getNombre(int i) {
		if(i < cant) {
			return lista[i].getNombre();
		}else {
			return null;
		}
	}
	
	public int getId(int indice) {
		if(indice < cant) {
			return lista[indice].getId();
		}else {
			return 0;
		}	
	}
	
	public String getDebilidad(int i) {
		if(i < cant) {
			return lista[i].getDebilidad();
		}else {
			return null;
		}
	}
	public String getIa(int i) {
		if(i < cant) {
			return lista[i].getDebilidad();
		}else {
			return null;
		}
	}
	
	public String getPais(int i) {
		if(i < cant) {
			return lista[i].getPais();
		}else {
			return null;
		}
	}
	
	public String getPrecision(int i) {
		if(i < cant) {
			return lista[i].getPrecision();
		}else {
			return null;
		}
	}
	
	public String getTipo(int i) {
		if(i < cant) {
			return lista[i].getTipo();
		}else {
			return null;
		}
	}
	
	public void getImpresion4taOpcion(int i) {
		
		System.out.println("\n--------------------------------------" + "\n.- El nombre del IA es: " + lista[i].getNombre() + "\n.- Su lenguaje es: " + lista[i].getLenguaje() + "\n.- El nivel de amenaza: " + lista[i].getNivelAmenaza() + "\n.- debilidad: " + lista[i].getDebilidad() + "\n.- Pais de creación: " + lista[i].getPais() + "\n.- Precisión: " + lista[i].getPrecision() + "\n.- El tipo del IA: " + lista[i].getTipo() + "\n.- ID: "+ lista[i].getId() + "\n--------------------------------------" ); 
	}

	public boolean setearDebilidad(int i, String debilidad) {
		if(i < cant) {
			lista[i].setDebilidad(debilidad);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearPrecision(int i, String precision) {
		if(i < cant) {
			lista[i].setPrecision(precision);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearNombe(int i, String nombre) {
		if(i < cant) {
			lista[i].setNombre(nombre);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean setearId(int i, int id) {
		if(i < cant) {
			lista[i].setId(id);
			return true;
		}else {
			return false;
		}
	}
	public boolean ingresar(Ias ia) {
		if(cant < max) {
			this.lista[cant] = ia;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}
	public boolean setearNivelPeligrosidad(int i, int nivel) {
		if(i < cant) {
			lista[i].setNivelAmenaza(nivel);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearPais(int i, String apis) {
		if(i < cant) {
			lista[i].setPais(apis);
			return true;
		}else {
			return false;
		}
	}
	
}
