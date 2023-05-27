package main;

public class listaDatosUsuario {

	private int cant,max;
	private datosUsuario[] lista;
	
	public listaDatosUsuario(int max) {
		this.cant = 0;
		this.max = max;
		this.lista = new datosUsuario[max];
	}
	public int getCant() {
		return this.cant;
	}
	public int getMax() {
		return this.max;
	}
	public boolean getIngresar(datosUsuario dato) {
		if(cant < max) {
			this.lista[cant] = dato;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}
	public datosUsuario getDatos(int indice) {
		if(indice < cant) {
			return lista[indice];
		}else {
			return null;
		}
	}
	public String getPais(int indice) {
		if(indice < cant) {
			return lista[indice].getPais();
		}else {
			return null;
		}
	}
	public String getCiudad(int indice) {
		if(indice < cant) {
			return lista[indice].getCiudad();
		}else {
			return null;
		}
	}
}
