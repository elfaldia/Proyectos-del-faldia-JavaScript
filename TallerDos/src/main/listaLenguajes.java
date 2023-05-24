package main;

public class listaLenguajes {
	private int cant, max;
	private Lenguajes[] lista;
	
	public listaLenguajes(int max) {
		this.max = max;
		this.cant = 0;
		this.lista = new Lenguajes[max];
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
			if(id == Integer.parseInt(lista[i].getId())) {
				indice = i;
				break;
			}
		}return indice;
	}
	
	public Lenguajes getLenguajes(int indice) {	
		if(indice < cant) {
			return lista[indice];
	
		}return null;
	}
	
	public String[] getLenguaje(int i) {
		if(i < cant) {
			return lista[i].getLenguajes();
		}else {
			return null;
		}
	}	
	
	public int getId(int indice) {
		if(indice < cant) {
			return Integer.parseInt(lista[indice].getId());
		}else {
			return 0;
		}	
	}
	
	public boolean ingresar(Lenguajes lenguaje) {
		if(cant < max) {
			this.lista[cant] = lenguaje;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}
}

