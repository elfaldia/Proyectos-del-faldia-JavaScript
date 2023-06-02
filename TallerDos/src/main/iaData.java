package main;

public class iaData {
	
	private int amount, max;
	private Ias[] list;
	
	public iaData(int max) {
		this.max = max;
		this.amount = 0;
		this.list = new Ias[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public int getIndex(int id) {
		
		int index = 0;
		for(int i = 0; i < amount; i++) {
			if(id == list[i].getId()) {
				index = i;
				break;
			}
		}return index;
	}
	
	public Ias getIas(int index) {	
		if(index < amount) {
			return list[index];
	
		}return null;
	}
	
	public String getLanguage(int i) {
		if(i < amount) {
			return list[i].getLanguage();
		}else {
			return null;
		}
	}
	public int getThreat(int index) {
		if(index < amount) {
			return list[index].getThreat();
		}else {
			return 0;
		}
	}	

	public String getName(int i) {
		if(i < amount) {
			return list[i].getName();
		}else {
			return null;
		}
	}
	
	public int getId(int index) {
		if(index < amount) {
			return list[index].getId();
		}else {
			return 0;
		}	
	}
	
	public String getDebility(int i) {
		if(i < amount) {
			return list[i].getDebility();
		}else {
			return null;
		}
	}
	public String getIa(int i) {
		if(i < amount) {
			return list[i].getDebility();
		}else {
			return null;
		}
	}
	
	public String getCountry(int i) {
		if(i < amount) {
			return list[i].getCountry();
		}else {
			return null;
		}
	}
	
	public String getPrecision(int i) {
		if(i < amount) {
			return list[i].getPrecision();
		}else {
			return null;
		}
	}
	
	public String getType(int i) {
		if(i < amount) {
			return list[i].getType();
		}else {
			return null;
		}
	}
	
	public void aux4rdOption(int i) {
		
		System.out.println("\n--------------------------------------" + "\n.- El nombre del IA es: " + list[i].getName() + "\n.- Su lenguaje es: " + list[i].getLanguage() + "\n.- El nivel de amenaza: " + list[i].getThreat() + "\n.- debilidad: " + list[i].getDebility() + "\n.- Pais de creación: " + list[i].getCountry() + "\n.- Precisión: " + list[i].getPrecision() + "\n.- El tipo del IA: " + list[i].getType() + "\n.- ID: "+ list[i].getId() + "\n--------------------------------------" ); 
	}

	public boolean setterDebility(int i, String debility) {
		if(i < amount) {
			list[i].setDebility(debility);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterPresicion(int i, String precision) {
		if(i < amount) {
			list[i].setPrecision(precision);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterName(int i, String name) {
		if(i < amount) {
			list[i].setName(name);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean setterId(int i, int id) {
		if(i < amount) {
			list[i].setId(id);
			return true;
		}else {
			return false;
		}
	}
	public boolean getInto(Ias ia) {
		if(amount < max) {
			this.list[amount] = ia;
			this.amount++;
			return true;
		}else {
			return false;
		}
	}
	public boolean setterThreat(int i, int level) {
		if(i < amount) {
			list[i].setThreat(level);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterCounty(int i, String country) {
		if(i < amount) {
			list[i].setCountry(country);
			return true;
		}else {
			return false;
		}
	}
	public void setterMax(int newMax) {
		Ias[] auxList = new Ias[max];
		auxList = list;
		this.max = newMax;
		list = new Ias[max];
		for(int i = 0; i < amount; i++) {
			list[i] = auxList[i];
		}
	}
}
