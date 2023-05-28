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
	public int getId(int indice) {
		if(indice < cant) {
			return lista[indice].getId();
		}else {
			return 0;
		}
	}
	public String getCiudad(int indice) {
		if(indice < cant) {
			return lista[indice].getCiudad();
		}else {
			return null;
		}
	}
	public String getNombre(int indice) {
		if(indice < cant) {
			return lista[indice].getNombre();
		}else {
			return null;
		}
	}
	public String getApellido(int indice) {
		if(indice < cant) {
			return lista[indice].getApellido();
		}else {
			return null;
		}
	}
	public int getAñosExperiencia(int i) {
		if(i < cant) {
			return lista[i].getAñosExperiencia();
		}else {
			return 0;
		}
	}
	public int getIndice(int id) {
		int i;
		for(i = 0; i < cant;i++) {
			if(id == lista[i].getId()) {
				break;
			}
		}if(i == cant) {
			return 0;
		}else {
			return i;
		}	
	}
	public boolean setearAños(int i,int año) {
		if(i < cant){
			lista[i].setAñosExperiencia(año);
			System.out.println(lista[i].getAñosExperiencia());
			return true;
		}else {
			return false;
		}
	}
	public boolean setearPais(int i,String pais) {
		if(i < cant){
			lista[i].setPais(pais);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearRegion(int i,String region) {
		if(i < cant){
			lista[i].setCiudad(region);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearId(int i,int id) {
		if(i < cant){
			lista[i].setId(id);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearNombre(int i,String nombre) {
		if(i < cant){
			lista[i].setNombre(nombre);
			return true;
		}else {
			return false;
		}
	}
	public boolean setearApellido(int i,String apellido) {
		if(i < cant){
			lista[i].setApellido(apellido);
			return true;
		}else {
			return false;
		}
	}
}
