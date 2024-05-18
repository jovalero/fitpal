package modelo;

public class Dieta {
	private int ID_Dieta;
	private String Nombre_Dieta;
	private String Descripcion_Dieta;
	
	public Dieta(int id,String nombre, String descripcion) {
		this.ID_Dieta=id;
		this.Nombre_Dieta=nombre;
		this.Descripcion_Dieta=descripcion;
	}

	public int getID_Dieta() {
		return ID_Dieta;
	}

	public void setID_Dieta(int iD_Dieta) {
		ID_Dieta = iD_Dieta;
	}

	public String getNombre_Dieta() {
		return Nombre_Dieta;
	}

	public void setNombre_Dieta(String nombre_Dieta) {
		Nombre_Dieta = nombre_Dieta;
	}

	public String getDescripcion_Dieta() {
		return Descripcion_Dieta;
	}

	public void setDescripcion_Dieta(String descripcion_Dieta) {
		Descripcion_Dieta = descripcion_Dieta;
	}

	@Override
	public String toString() {
		return "Dieta [ID_Dieta=" + ID_Dieta + ", Nombre_Dieta=" + Nombre_Dieta + ", Descripcion_Dieta="
				+ Descripcion_Dieta + "]";
	}
	
	

}
