package main;

public class Usuarios  {

	private String usuario;
	private String contraseña;
	
	public Usuarios(String usuario, String contraseña) {
		this.usuario = usuario;
		this.contraseña = contraseña;
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

	public String toString() {
		return "login [usuario=" + usuario + ", contraseña=" + contraseña + "]";
	}
}
