package modelo;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
import controlador.ComidaControlador;
import controlador.DietaControlador;
import controlador.EjercicioControlador;
import controlador.EntrenadorControlador;
import controlador.IncentivoControlador;
import controlador.ProgresoControlador;
import controlador.RutinaControlador;
import interfaces.VerificacionesRepository;
import vistas.HomeAdmin;


public class Admin extends Persona implements VerificacionesRepository{
	
	private int id_admin;
	
	public Admin(String nombre, String apellido, int telefono, int id_sucursal, int dNI, int id_admin,
			String contrasena) {
		super(nombre, apellido, telefono, id_sucursal, dNI,contrasena,"Admin");
		this.id_admin = id_admin;
	}
	
	public Admin(String contrasena) {
		super("","",1234,1,443,contrasena,"Admin");
		this.id_admin=0;
		
	}

	public int getId_admin() {
		return id_admin;
	}


	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}
	
	
	
	@Override
	public String toString() {
		return "Admin [id_admin=" + id_admin + ", getNombre()=" + getNombre() + ", getApellido()=" + getApellido()
				+ ", getTelefono()=" + getTelefono() + ", getId_sucursal()=" + getId_sucursal() + ", getDNI()="
				+ getDNI() + ", getContrasena()=" + getContrasena() + ", getUsuario()=" + getUsuario() + "]";
	}
	
	
	
	public boolean RegistrarCliente(int sucursal,String Nombre, String Apellido, String Email,int DNI, int Telefono,String Contrasena,double Peso,double Altura) {
		ClienteControlador controlador;
		Cliente nuevocliente=null;

	    try {
	        controlador = new ClienteControlador();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return false;  
	    }

	    if (controlador.getConnection() == null) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return false; 
	    }
		
			
		nuevocliente= new Cliente(Nombre,Apellido,Telefono,sucursal,DNI,Email,Contrasena,"Nuevo",Peso,Altura);
		controlador.addCliente(nuevocliente);
		return true;
									
		}
			
	

		public static void crearEjercicio() {
			LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
			EjercicioControlador controlador = new EjercicioControlador();
			
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
		
		public static void modificarEjercicio() {
			LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
			EjercicioControlador controlador = new EjercicioControlador();
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
		
		
			public static void mostrarEjercicios() {
				LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
				EjercicioControlador controlador = new EjercicioControlador();
				
				StringBuilder nota = new StringBuilder("Lista de ejercicios:\n");
				
				for (Ejercicio ejercicio : listaEjercicios) {
					nota.append(ejercicio.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, nota.toString());
				
			}
		
		public static void borrarEjercicio() {
			LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
			EjercicioControlador controlador = new EjercicioControlador();
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
		
		public static void crearComida() {
			LinkedList<Comida> listacomidas = new LinkedList<>();
			ComidaControlador controlador = new ComidaControlador ();
			
			String Nombre = JOptionPane.showInputDialog("Ingrese el nombre de la comida :");
			String Descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la comida:");
			int ID_Comida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la Comida:"));
			
			Comida nuevaComida = new Comida(Nombre,Descripcion,ID_Comida);
			listacomidas.add(nuevaComida);
			JOptionPane.showMessageDialog(null,"Comida creada exitosamente!");
		}
		
		
		public static void modificarComida() {
			LinkedList<Comida> listacomidas = new LinkedList<>();
			ComidaControlador controlador = new ComidaControlador ();
			
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
		
		
		 public static void mostrarComidas() {
			 LinkedList<Comida> listacomidas = new LinkedList<>();
				ComidaControlador controlador = new ComidaControlador ();
				
		        StringBuilder nota = new StringBuilder("Lista de comidas:\n");
		        
		        for (Comida comida : listacomidas) {
		            nota.append(comida.toString()).append("\n");
		        }
		        
		        JOptionPane.showMessageDialog(null, nota.toString());
		    }
		 
		public static void borrarComida() {
			LinkedList<Comida> listacomidas = new LinkedList<>();
			ComidaControlador controlador = new ComidaControlador ();
			
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
			public void MostrarDietas() {
			    DietaControlador controlador = new DietaControlador();
			    LinkedList<Dieta> dietas = controlador.getAllDietas();
			    String nota = "Lista de dietas: \n";

			    for (Dieta dieta : dietas) {
			        nota += dieta.toString() + "\n";
			    }
			    JOptionPane.showMessageDialog(null, nota);
			}
	
			public static void crearIncentivo() {
				IncentivoControlador controlador = new IncentivoControlador();
				LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivo();
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
				LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivo();
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
					
					JOptionPane.showMessageDialog(null,"Incentivo modificad exitosamente! :)");
				} else {
					JOptionPane.showMessageDialog(null,"Incentivo no encontrada: ");
				}
			}
			// mostrar incentivo
			 public static void mostrarIncentivo() {
				 IncentivoControlador controlador = new IncentivoControlador();
				 LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivo();
			     StringBuilder nota = new StringBuilder("Lista:\n");
			        
			        for (Incentivo incentivo : listaincentivo) {
			            nota.append(incentivo.toString()).append("\n");
			        }
			        
			        JOptionPane.showMessageDialog(null, nota.toString());
			    }
			 //borrar incentivo
			public static void borrarIncentivo() {
				IncentivoControlador controlador = new IncentivoControlador();
				LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivo();
				int ID_Incentivo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el incentivo que desee borrar: "));
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
			
			

	@Override
	public void Menu() {
		HomeAdmin admin=new HomeAdmin(this);
       
	}

	
}
