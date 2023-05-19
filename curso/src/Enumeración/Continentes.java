package Enumeración;

public enum Continentes {

	AFRICA(53),
	EUROPA(46),
	ASIA(44),
	AMERICA(34),
	OCEANIA(14);
	
	private int paises;
	
	Continentes(int paises){
		this.paises = paises;
	}
	
	public int getPaises() {
		return this.paises;
	}
}
