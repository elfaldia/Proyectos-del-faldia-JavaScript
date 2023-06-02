package main;

public class debilitiesData {
	private int amount, max;
	private Debilities[] list;
	
	public debilitiesData(int max) {
		this.max = max;
		this.amount = 0;
		this.list = new Debilities[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public Debilities getData(int index) {	
		if(index < amount) {
			return list[index];
	
		}return null;
	}
	
	public String getDebility(int i) {
		if(i < amount) {
			return list[i].getDebility();
		}else {
			return null;
		}
	}	
	
	public int getHierarchy(int index) {
		if(index < amount) {
			return list[index].gethierarchy();
		}else {
			return 0;
		}	
	}
	
	public boolean getInto(Debilities debility) {
		if(amount < max) {
			this.list[amount] = debility;
			this.amount++;
			return true;
		}else {
			return false;
		}
	}
	public void setterMax(int newMax) {
		Debilities[] auxList = new Debilities[max];
		auxList = list;
		this.max = newMax;
		list = new Debilities[max];
		for(int i = 0; i < amount; i++) {
			list[i] = auxList[i];
		}
	}
}
