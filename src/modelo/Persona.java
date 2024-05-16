package modelo;

public abstract class Persona {
	
	private String Nombre;
	private String Apellido;
	private int Telefono;
	private int id_sucursal;
	private int DNI;
	private String contrasena;
	private String usuario;

	public Persona(String nombre, String apellido, int telefono, int id_sucursal, int dNI, String contrasena,
			String usuario) {
		super();
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		this.id_sucursal = id_sucursal;
		DNI = dNI;
		this.contrasena = contrasena;
		this.usuario = usuario;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public int getTelefono() {
		return Telefono;
	}
	public void setTelefono(int telefono) {
		Telefono = telefono;
	}
	public int getId_sucursal() {
		return id_sucursal;
	}
	public void setId_sucursal(int id_sucursal) {
		this.id_sucursal = id_sucursal;
	}
	public int getDNI() {
		return DNI;
	}
	public void setDNI(int dNI) {
		DNI = dNI;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Persona [Nombre=" + Nombre + ", Apellido=" + Apellido + ", Telefono=" + Telefono + ", id_sucursal="
				+ id_sucursal + ", DNI=" + DNI + "]";
	}
	
	public void Menu() {
		
	}
}
