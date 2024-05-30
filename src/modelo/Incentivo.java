package modelo;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.IncentivoControlador;

public class Incentivo {
	private int Costo;
	private String Descripcion;
	private int ID_Incentivo;
	
	public Incentivo(int Costo, String Descripcion, int ID_Incentivo) {
		this.Costo = Costo;
		this.Descripcion = Descripcion;
		this.ID_Incentivo = ID_Incentivo;
	}

	public int getCosto() {
		return Costo;
	}

	public void setCosto(int costo) {
		Costo = costo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getID_Incentivo() {
		return ID_Incentivo;
	}

	public void setID_Incentivo(int iD_Incentivo) {
		ID_Incentivo = iD_Incentivo;
	}
	@Override
	public String toString() {
		return "Incentivo{" +
                "nombre='" + Costo + '\'' +
                ", descripcion='" + Descripcion + '\'' +
                ", id_comida=" + ID_Incentivo +
                '}';		
	}		
}
