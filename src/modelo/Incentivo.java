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
		// Lista estática para almacenar las comidas
    
    
		//crear incentivo
		public static void crearIncentivo() {
			IncentivoControlador controlador = new IncentivoControlador();
			LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivos();
			int Costo = Integer.parseInt(JOptionPane.showInputDialog(null, "Precio:"));
			String Descripcion = JOptionPane.showInputDialog("Ingrese la descripcion:");
			int ID_Incentivo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID:"));
			
			Incentivo nuevoIncentivo = new Incentivo(Costo,Descripcion,ID_Incentivo);
			listaincentivo.add(nuevoIncentivo);
			JOptionPane.showMessageDialog(null,"Incentivo creado exitosamente!");
		}
		
		//modifcar incentivo
		public static void modificarIncentivo() {
			IncentivoControlador controlador = new IncentivoControlador();
			LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivos();
			int ID_Incentivo  = Integer.parseInt(JOptionPane.showInputDialog("ingrese el ID:"));
			Incentivo incentivoAModificar = null;
			
			for (Incentivo incentivo : listaincentivo) {
				if (incentivo.getID_Incentivo() == ID_Incentivo) {
					incentivoAModificar = incentivo;
					break;
				}
			}
			if (incentivoAModificar != null) {
				int nuevoCosto = Integer.parseInt(JOptionPane.showInputDialog("Precio:",incentivoAModificar.getCosto()));
				String nuevaDescripcion = JOptionPane.showInputDialog("Ingrese la nueva descripcion:",incentivoAModificar.getDescripcion());
				
				incentivoAModificar.setCosto(nuevoCosto);
				incentivoAModificar.setDescripcion(nuevaDescripcion);
				
				JOptionPane.showMessageDialog(null,"Comida modificada exitosamente! :)");
			} else {
				JOptionPane.showMessageDialog(null,"Comida no encontrada: ");
			}
		}
		// mostrar incentivo
		 public static void mostrarIncentivo() {
			 IncentivoControlador controlador = new IncentivoControlador();
			 LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivos();
		     StringBuilder nota = new StringBuilder("Lista:\n");
		        
		        for (Incentivo incentivo : listaincentivo) {
		            nota.append(incentivo.toString()).append("\n");
		        }
		        
		        JOptionPane.showMessageDialog(null, nota.toString());
		    }
		 //borrar incentivo
		public static void borrarComida() {
			IncentivoControlador controlador = new IncentivoControlador();
			LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivos();
			int ID_Incentivo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID que desee borrar: "));
			Incentivo IncentivoABorrar = null;
			
			for (Incentivo incentivo : listaincentivo) {
				if (incentivo.getID_Incentivo() == ID_Incentivo) {
				IncentivoABorrar = incentivo;
				break;
			  }
			}

			if (IncentivoABorrar != null) {
				listaincentivo.remove(IncentivoABorrar);
				JOptionPane.showMessageDialog(null,"borrado exitosamente! :)");
			}else {
				JOptionPane.showMessageDialog(null,"no encontrado :(");
			}
		 
	}
}
