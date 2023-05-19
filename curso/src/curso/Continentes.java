package curso;

public enum Continentes {
	AFRICA(53),
	EUROPA(46),
	ASIA(44),
	AMERICA(34),
	OCIANIA(14);
	
	// Atributos de cada elementos de una enumeración
	private final int paises;
	
	//Constructor de cada elemento de la enumeración
	Continentes(int paises){
		this.paises = paises;
	}
	
	public int getPaises() {
		return paises;
	}
	
}
