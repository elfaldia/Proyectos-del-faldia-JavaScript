package main;

public class userInformationData {

	private int amount,max;
	private userInformation[] list;
	
	public userInformationData(int max) {
		this.amount = 0;
		this.max = max;
		this.list = new userInformation[max];
	}
	public int getAmount() {
		return this.amount;
	}
	public int getMax() {
		return this.max;
	}
	public boolean getInto(userInformation data) {
		if(amount < max) {
			this.list[amount] = data;
			this.amount++;
			return true;
		}else {
			return false;
		}
	}
	public userInformation getData(int index) {
		if(index < amount) {
			return list[index];
		}else {
			return null;
		}
	}
	public String getCountry(int index) {
		if(index < amount) {
			return list[index].getCountry();
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
	public String getCity(int index) {
		if(index < amount) {
			return list[index].getCity();
		}else {
			return null;
		}
	}
	public String getName(int index) {
		if(index < amount) {
			return list[index].getName();
		}else {
			return null;
		}
	}
	public String getLastName(int index) {
		if(index < amount) {
			return list[index].getLastName();
		}else {
			return null;
		}
	}
	public int getExperience(int i) {
		if(i < amount) {
			return list[i].getExperience();
		}else {
			return 0;
		}
	}
	public int getIndex(int id) {
		int i;
		for(i = 0; i < amount;i++) {
			if(id == list[i].getId()) {
				break;
			}
		}if(i == amount) {
			return 0;
		}else {
			return i;
		}	
	}
	public boolean setterYears(int i,int year) {
		if(i < amount){
			list[i].setExperience(year);
			System.out.println(list[i].getExperience());
			return true;
		}else {
			return false;
		}
	}
	public boolean setterCountry(int i,String country) {
		if(i < amount){
			list[i].setCountry(country);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterCity(int i,String city) {
		if(i < amount){
			list[i].setCity(city);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterId(int i,int id) {
		if(i < amount){
			list[i].setId(id);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterName(int i,String name) {
		if(i < amount){
			list[i].setName(name);
			return true;
		}else {
			return false;
		}
	}
	public boolean setterLastName(int i,String lastName) {
		if(i < amount){
			list[i].setLastName(lastName);
			return true;
		}else {
			return false;
		}
	}
	public void setterMax(int newMAx) {
		userInformation[] listAux = new userInformation[max];
		listAux = list;
		this.max = newMAx;
		list = new userInformation[max];
		for(int i = 0; i < amount; i++) {
			list[i] = listAux[i];
		}
	}
}
