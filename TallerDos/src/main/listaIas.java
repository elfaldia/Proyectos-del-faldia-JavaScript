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
	
	
	public boolean ingresar(Ias ia) {
		if(cant < max) {
			this.lista[cant] = ia;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}

}
