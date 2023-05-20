package main;

public class Personas  {

	private String usuario;
	private String contraseña;
	private int id;
	
	public Personas(String usuario, String contraseña, int id) {
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "login [usuario=" + usuario + ", contraseña=" + contraseña + "]";
	}
}
