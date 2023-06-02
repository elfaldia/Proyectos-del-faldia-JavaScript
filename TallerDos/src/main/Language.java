package main;

import java.util.Arrays;

public class Language {
	private String id;
	private String[] languages;
	
	
	public Language(String id, String[] languages) {
		this.id = id;
		this.languages = languages;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String[] getLanguage() {
		return languages;
	}


	public void setLanguage(String[] language) {
		this.languages = language;
	}



	public String toString() {
		return "Languages [id=" + id + ", lenguajes=" + Arrays.toString(languages) + "]";
	}
	

}
