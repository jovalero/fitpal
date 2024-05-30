package modelo;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Comida {
	 
	private String Nombre;
	private String Descripcion;
	private int ID_Comida;
	
	  // Lista estática para almacenar las comidas
    private static LinkedList<Comida> listacomidas = new LinkedList<>();
	
	public Comida(String Nombre, String Descripcion, int ID_Comida) {
		this.Nombre = Nombre;
		this.Descripcion = Descripcion;
		this.ID_Comida = ID_Comida;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getID_Comida() {
		return ID_Comida;
	}

	public void setID_Comida(int iD_Comida) {
		ID_Comida = iD_Comida;
	}
	@Override
	public String toString() {
		return "Comida{" +
                "nombre='" + Nombre + '\'' +
                ", descripcion='" + Descripcion + '\'' +
                ", id_comida=" + ID_Comida +
                '}';
		
	}
	
	
}
}

