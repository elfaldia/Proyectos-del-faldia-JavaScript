package main;

public class usersData {
	
	private int amount, max;
	private Users[] list;
	
	public usersData(int max) {
		this.max = max;
		this.amount = 0;
		this.list = new Users[max];
	}
	
	public int getMax() {
		return this.max;
	}
	
	public int getCant() {
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
	
	public Users getUser(int index) {	
		if(index < amount) {
			return list[index];
	
		}return null;
	}
	
	public String getName(int i) {
		if(i < amount) {
			return list[i].getUser();
		}else {
			return null;
		}
	}
	public String getPassword(int index) {
		if(index < amount) {
			return list[index].getPassword();
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
	
	public int getIndexWithName(String name) {
		int index = 0;
		for(int i = 0; i < getMax(); i++) {
			if(name.equalsIgnoreCase(list[i].getPassword())) {
				index = i;
				break;
			}
		}return index;
	}
	
	public int getIdWithName(String name) {
		int index = 0;
		for(int i = 0; i < getMax(); i++) {
			if(name.equalsIgnoreCase(list[i].getPassword())) {
				index = list[i].getId();
				break;
			}
		}return index;
	}
	
	public boolean getInto(Users user) {
		if(amount < max) {
			this.list[amount] = user;
			this.amount++;
			return true;
		}else {
			return false;
		}
	}
	public boolean setterUser(int i, String user) {
		if(i < amount) {
			list[i].setUser(user);
			return true;
		}else {
			return false;
		}
	}
	public String obteinUser(int i, String user) {
		if(i < amount) {
			int number = (int)(Math.random()*(99999 - 10000) + 10000);
			String finalUser = user + "#" + Integer.toString(number);
			return finalUser;
		}else {
			return null;
		}
	}
	public boolean setterPassword(int i, String password) {
		if(i < amount) {
			list[i].setPassword(password);
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
	public String getElem(int i) {
		if(i < amount) {
			return list[i].toString();
		}else {
			return null;
		}
	}
	public String getJustName(int i) {
		if(i < amount) {
			String name = list[i].getUser();
			String[] parts = name.split("#");
			return parts[0];
		}else {
			return null;
		}
	}
	public void setterMax(int newMax) {
		Users[] auxList = new Users[max];
		auxList = list;
		this.max = newMax;
		list = new Users[max];
		for(int i = 0; i < amount; i++) {
			list[i] = auxList[i];
		}
	}
}
