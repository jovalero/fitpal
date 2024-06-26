package modelo;

public class Sucursal {
	private int id_sucursal;
	private String Direccion;
	private int Telefono;
	public Sucursal(int id_sucursal, String direccion, int telefono) {
		super();
		this.id_sucursal = id_sucursal;
		this.Direccion = direccion;
		this.Telefono = telefono;
	}
	
	public int getId_sucursal() {
		return id_sucursal;
	}

	public void setId_sucursal(int id_sucursal) {
		this.id_sucursal = id_sucursal;
	}

	public String getDireccion() {
		return Direccion;
	}
	
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	public int getTelefono() {
		return Telefono;
	}
	
	public void setTelefono(int telefono) {
		Telefono = telefono;
	}
	
	
	
}




