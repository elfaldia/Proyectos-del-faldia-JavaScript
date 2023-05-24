package main;

public class listaDebilidades {
	private int cant, max;
	private Debilidades[] lista;
	
	public listaDebilidades(int max) {
		this.max = max;
		this.cant = 0;
		this.lista = new Debilidades[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getCantidad() {
		return this.cant;
	}
	
	public Debilidades getDebilidades(int indice) {	
		if(indice < cant) {
			return lista[indice];
	
		}return null;
	}
	
	public String getDebilidad(int i) {
		if(i < cant) {
			return lista[i].getDebilidad();
		}else {
			return null;
		}
	}	
	
	public int getJerarquia(int indice) {
		if(indice < cant) {
			return lista[indice].getJerarquia();
		}else {
			return 0;
		}	
	}
	
	public boolean ingresar(Debilidades debilidad) {
		if(cant < max) {
			this.lista[cant] = debilidad;
			this.cant++;
			return true;
		}else {
			return false;
		}
	}
}
