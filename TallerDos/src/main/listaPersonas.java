package main;

public class listaPersonas {
	
	private int cant, max;
	private Personas[] lista;
	
	public listaPersonas(int max) {
		this.max = max;
		this.cant = 0;
		this.lista = new Personas[max];
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
	
	public Personas getUsario(int indice) {	
		if(indice < cant) {
			return lista[indice];
	
		}return null;
	}
	
	public String getNombre(int i) {
		if(i < cant) {
			return lista[i].getUsuario();
		}else {
			return null;
		}
	}
	public String getContraseña(int indice) {
		if(indice < cant) {
			return lista[indice].getContraseña();
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
	
	public int getIdConNombre(String nombre) {
		int indice = 0;
		for(int i = 0; i < getMax(); i++) {
			if(nombre.equalsIgnoreCase(lista[i].getContraseña())) {
				indice = lista[i].getId();
				break;
			}
		}return indice;
	}
	
	public boolean ingresar(Personas persona) {
		if(cant < max) {
			this.lista[cant] = persona;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}
}
