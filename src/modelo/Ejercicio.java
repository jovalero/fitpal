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
	
	// crear ejericico
	public static void crearEjercicio() {
		int ID_Ejercicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del ejercicio:"));
		String Nombre = JOptionPane.showInputDialog("Ingrese el nombre del ejercicio:");
		String Maquina = JOptionPane.showInputDialog("Ingrese la máquina del ejercicio:");
		String Musculo = JOptionPane.showInputDialog("Ingrese el músculo trabajado en el ejercicio:");
		String Descripcion = JOptionPane.showInputDialog("Ingrese la descripción del ejercicio:");
		String Video = JOptionPane.showInputDialog("Ingrese el enlace del video del ejercicio:");
		int ID_Area = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del área del ejercicio:"));
		
		Ejercicio nuevoEjercicio = new Ejercicio (ID_Ejercicio, Nombre, Maquina, Musculo, Descripcion, Video, ID_Area);
		listaEjercicios.add(nuevoEjercicio);
		JOptionPane.showMessageDialog(null," Ejercicio creado exitosamente! :)");
	}
	//modificar ejercicio
	public static void modificarEjercicio() {
		int ID_Ejercicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del ejercicio que desee modificar "));
		Ejercicio ejercicioAModificar = null;
		
		for(Ejercicio ejercicio:listaEjercicios) {
			if(ejercicio.getID_Ejercicio() == ID_Ejercicio) {
				ejercicioAModificar = ejercicio;
				break;
			}
		}
		if (ejercicioAModificar != null) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del ejercicio:", ejercicioAModificar.getNombre());
			String nuevaMaquina = JOptionPane.showInputDialog("Ingrese la nueva máquina del ejercicio:", ejercicioAModificar.getMaquina());
			String nuevoMusculo = JOptionPane.showInputDialog("Ingrese el nuevo músculo trabajado en el ejercicio:", ejercicioAModificar.getMusculo());
			String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripción del ejercicio:", ejercicioAModificar.getDescripcion());
			String nuevoVideo = JOptionPane.showInputDialog("Ingrese el nuevo enlace del video del ejercicio:", ejercicioAModificar.getVideo());
			int nuevoID_Area = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo ID del área del ejercicio:", ejercicioAModificar.getID_Area()));
			
			ejercicioAModificar.setNombre(nuevoNombre);
			ejercicioAModificar.setMaquina(nuevaMaquina);
			ejercicioAModificar.setMusculo(nuevoMusculo);
			ejercicioAModificar.setDescripcion(nuevaDescripcion);
			ejercicioAModificar.setVideo(nuevoVideo);
			ejercicioAModificar.setID_Area(nuevoID_Area);
			
			JOptionPane.showMessageDialog(null, "Ejercicio modificado exitosamente! :)");
		} else {
			JOptionPane.showMessageDialog(null, "Ejercicio no encontrado :(");
		}
	}
	
	// Mostrar ejercicios
		public static void mostrarEjercicios() {
			StringBuilder nota = new StringBuilder("Lista de ejercicios:\n");
			
			for (Ejercicio ejercicio : listaEjercicios) {
				nota.append(ejercicio.toString()).append("\n");
			}
			JOptionPane.showMessageDialog(null, nota.toString());
			
		}
	// Borrar ejercicio
	public static void borrarEjercicio() {
			int ID_Ejercicio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del ejercicio que desee borrar:"));
			Ejercicio ejercicioABorrar = null;
			for (Ejercicio ejercicio : listaEjercicios) {
				if(ejercicio.getID_Ejercicio()== ID_Ejercicio) {
					ejercicioABorrar = ejercicio;
					break;
				}
			}
			if (ejercicioABorrar != null) {
				listaEjercicios.remove(ejercicioABorrar);
				JOptionPane.showMessageDialog(null,"Ejercicio borrado exitosamente!");
			} else {
				JOptionPane.showMessageDialog(null,"Ejercicio no encontrado :(");
			}
	}
	
	
}
