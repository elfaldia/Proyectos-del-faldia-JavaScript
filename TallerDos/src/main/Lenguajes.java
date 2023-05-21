package main;

import java.util.Arrays;

public class Lenguajes {
	private String id;
	private String[] lenguajes;
	
	
	public Lenguajes(String id, String[] lenguajes) {
		this.id = id;
		this.lenguajes = lenguajes;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String[] getLenguajes() {
		return lenguajes;
	}


	public void setLenguajes(String[] lenguajes) {
		this.lenguajes = lenguajes;
	}



	public String toString() {
		return "Lenguajes [id=" + id + ", lenguajes=" + Arrays.toString(lenguajes) + "]";
	}
	

}
