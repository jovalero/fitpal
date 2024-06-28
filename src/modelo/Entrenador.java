package modelo;

import javax.swing.JOptionPane;

import vistas.HomeEntrenador;

public class Entrenador extends Persona {
	
	private int id_entrenador;
	private int numentrenados;
	private String email;
	
	public Entrenador() {
		super("", "", 0, 0, 0, "", "");
	}
	
	public Entrenador(String nombre, String apellido, int telefono, int id_sucursal, int dNI, int identrenador, String email, String contrasena, int numentrenados) {
		super(nombre, apellido, telefono, id_sucursal, dNI, contrasena, email);
		this.id_entrenador = identrenador;
		this.numentrenados = numentrenados;
		this.email = email;
	}

	public Entrenador(String email, String contrasena) {
		super("", "", 123, 0, 555, contrasena, email);
		this.id_entrenador = 0;
		this.numentrenados = 0;
		this.email = email;
	}
	
	public Entrenador(String nombre, String apellido, int telefono, int id_sucursal, int dNI, String email, String contrasena, int numentrenados) {
		super(nombre, apellido, telefono, id_sucursal, dNI, contrasena, email);
		this.id_entrenador = -1;
		this.numentrenados = numentrenados;
	}

	public int getId_entrenador() {
		return id_entrenador;
	}

	public void setId_entrenador(int id_entrenador) {
		this.id_entrenador = id_entrenador;
	}

	public int getNumentrenados() {
		return numentrenados;
	}

	public void setNumentrenados(int numentrenados) {
		this.numentrenados = numentrenados;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Entrenador [id_entrenador=" + id_entrenador + ", numentrenados=" + numentrenados + ", getNombre()="
				+ getNombre() + ", getApellido()=" + getApellido() + ", getTelefono()=" + getTelefono()
				+ ", getId_sucursal()=" + getId_sucursal() + ", getDNI()=" + getDNI() + ", getContrasena()="
				+ getContrasena() + ", getEmail()=" + getEmail() + "]";
	}
	@Override
	public void Menu() {
		new HomeEntrenador(this);
	}
}

		
