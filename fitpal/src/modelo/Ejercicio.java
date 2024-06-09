package modelo;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Ejercicio {
		
	private int ID_Ejercicio;
	private String Nombre;
	private String Maquina;
	private String Musculo;
	private String Descripcion;
	private String Video;
	private int ID_Area;
	
	private static LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
	
	public Ejercicio(int ID_Ejercicio,String Nombre,String Maquina,String Musculo,String Descripcion,String Video,int ID_Area) {
		this.ID_Ejercicio = ID_Ejercicio;
		this.Maquina = Maquina;
		this.Musculo = Musculo;
		this.Descripcion = Descripcion;
		this.Video = Video;
		this.ID_Area = ID_Area;
	}

	public int getID_Ejercicio() {
		return ID_Ejercicio;
	}

	public void setID_Ejercicio(int iD_Ejercicio) {
		ID_Ejercicio = iD_Ejercicio;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getMaquina() {
		return Maquina;
	}

	public void setMaquina(String maquina) {
		Maquina = maquina;
	}

	public String getMusculo() {
		return Musculo;
	}

	public void setMusculo(String musculo) {
		Musculo = musculo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getVideo() {
		return Video;
	}

	public void setVideo(String video) {
		Video = video;
	}

	public int getID_Area() {
		return ID_Area;
	}

	public void setID_Area(int iD_Area) {
		ID_Area = iD_Area;
	}

	@Override
	public String toString() {
		return "Ejercicio [ID_Ejercicio=" + ID_Ejercicio + ", Nombre=" + Nombre + ", Maquina=" + Maquina + ", Musculo="
				+ Musculo + ", Descripcion=" + Descripcion + ", Video=" + Video + ", ID_Area=" + ID_Area + "]";
	}
	
	
	
}

