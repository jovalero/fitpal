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
	
	//crear comida
	public static void crearComida() {
		String Nombre = JOptionPane.showInputDialog("Ingrese el nombre de la comida :");
		String Descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la comida:");
		int ID_Comida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la Comida:"));
		
		Comida nuevaComida = new Comida(Nombre,Descripcion,ID_Comida);
		listacomidas.add(nuevaComida);
		JOptionPane.showMessageDialog(null,"Comida creada exitosamente!");
	}
	
	//modifcar comida
	public static void modificarComida() {
		int ID_Comida = Integer.parseInt(JOptionPane.showInputDialog("ingrese el ID de la comida a modificar"));
		Comida comidaAModificar = null;
		
		for (Comida comida : listacomidas) {
			if (comida.getID_Comida() == ID_Comida) {
				comidaAModificar = comida;
				break;
			}
		}
		if (comidaAModificar != null) {
			String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la comida:",comidaAModificar.getNombre());
			String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripcion de la comida:",comidaAModificar.getDescripcion());
			
			comidaAModificar.setNombre(nuevoNombre);
			comidaAModificar.setDescripcion(nuevaDescripcion);
			
			JOptionPane.showMessageDialog(null,"Comida modificada exitosamente! :)");
		} else {
			JOptionPane.showMessageDialog(null,"Comida no encontrada :(");
		}
	}
	// mostrar comidas
	 public static boolean mostrarComidas() {
	        StringBuilder nota = new StringBuilder("Lista de comidas:\n");
	        
	        for (Comida comida : listacomidas) {
	            nota.append(comida.toString()).append("\n");
	        }
	        
	        JOptionPane.showMessageDialog(null, nota.toString());
			return false;
	    }
	 //borrar comida
	public static void borrarComida() {
		int ID_Comida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la comida que desee borrar"));
		Comida comidaABorrar = null;
		
		for (Comida comida : listacomidas) {
			if (comida.getID_Comida() == ID_Comida) {
			comidaABorrar = comida;
			break;
		  }
		}

		if (comidaABorrar != null) {
			listacomidas.remove(comidaABorrar);
			JOptionPane.showMessageDialog(null,"Comida borrada exitosamente! :)");
		}else {
			JOptionPane.showMessageDialog(null,"Comida no encontrada :(");
		}
	 
}
}

