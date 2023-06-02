package main;

public class Users  {

	private String user;
	private String password;
	private int id;
	
	public Users(String usuario, String contraseña, int id) {
		this.user = usuario;
		this.password = contraseña;
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String usuario) {
		this.user = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String contraseña) {
		this.password = contraseña;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "login [usuario=" + user + ", contraseña=" + password + "]";
	}
}
