package main;

public class languageData {
	private int amount, max;
	private Language[] list;
	
	public languageData(int max) {
		this.max = max;
		this.amount = 0;
		this.list = new Language[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public int getIndex(int id) {
		
		int indice = 0;
		for(int i = 0; i < amount; i++) {
			if(id == Integer.parseInt(list[i].getId())) {
				indice = i;
				break;
			}
		}return indice;
	}
	
	public Language getData(int index) {	
		if(index < amount) {
			return list[index];
	
		}return null;
	}
	
	public Language getforID(int ID) {
		for(int i = 0; i < amount; i++) {
			if(ID == Integer.parseInt(list[i].getId())) {
				return list[i];
			}
		} return null;
	}
	
	public String[] getLanguages(int i) {
		if(i < amount) {
			return list[i].getLanguage();
		}else {
			return null;
		}
	}	
	
	public int getId(int index) {
		if(index < amount) {
			return Integer.parseInt(list[index].getId());
		}else {
			return 0;
		}	
	}
	
	public boolean getInto(Language language) {
		if(amount < max) {
			this.list[amount] = language;
			this.amount++;
			return true;
		}else {
			return false;
		}
	}
	public boolean setterLanguages(int i, String[] languages) {
		if(i < amount) {
			list[i].setLanguage(languages);
			return true;
		}else {
			return false;
		}
	}
	public void setterMax(int newMax) {
		Language[] auxList = new Language[max];
		auxList = list;
		this.max = newMax;
		list = new Language[max];
		for(int i = 0; i < amount; i++) {
			list[i] = auxList[i];
		}
	}


	
}

