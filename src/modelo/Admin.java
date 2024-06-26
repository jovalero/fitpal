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
			
	
	public boolean clientesiguales(Cliente cliente1, Cliente cliente2) {
		if (cliente1.getDNI()!=cliente2.getDNI()) {
			return false;
		}
		if (cliente1.getNombre().equalsIgnoreCase(cliente2.getNombre()))   {
			return false;
		}
		if (cliente1.getApellido().equalsIgnoreCase(cliente2.getApellido())) {
			return false;
		}
		if (cliente1.getContrasena().equalsIgnoreCase(cliente2.getContrasena())) {
			return false;
		}
		if (cliente1.getEstado_sus().equalsIgnoreCase(cliente2.getEstado_sus())) {
			return false;
		}
		if (cliente1.getFechavenc().equals(cliente2.getFechavenc()) ) {
			return false;
		}
		if (cliente1.getPuntos()!=cliente2.getPuntos()) {
			return false;
		}
		return true;
		
	}
	
	public void MostrarRutinas(){
	    RutinaControlador controlador = new RutinaControlador();
	    LinkedList<Rutina> Rutinas = controlador.getAllRutinas();
	    String nota="Lista de rutinas: \n";
	    
	    for (Rutina rutina : Rutinas) {
	        nota+= rutina.toString() + "\n";
	    }
	    JOptionPane.showMessageDialog(null, nota);
	}

	public void BorrarRutinas() {
	    RutinaControlador controlador = new RutinaControlador();
	    LinkedList<Rutina> Rutinas = controlador.getAllRutinas();
	    
	    if (Rutinas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay rutinas para borrar.");
	        return;
	    }

	    String[] opciones = new String[Rutinas.size()];
	    for (int i = 0; i < Rutinas.size(); i++) {
	        opciones[i] = Rutinas.get(i).toString();
	    }

	    Object seleccion = JOptionPane.showInputDialog(null, "Seleccione la rutina a borrar:", "Borrar Rutina", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

	    if (seleccion != null) {
	        String rutinaSeleccionada = seleccion.toString();
	        Rutina rutinaAEliminar = null;
	        for (Rutina rutina : Rutinas) {
	            if (rutina.toString().equals(rutinaSeleccionada)) {
	                rutinaAEliminar = rutina;
	                break;
	            }
	        }
	        
	        if (rutinaAEliminar != null) {
	            controlador.deleteRutina(rutinaAEliminar.getIdRutina());
	            JOptionPane.showMessageDialog(null, "La rutina ha sido eliminada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró la rutina seleccionada.");
	        }
	    }
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
        /*/String[] opciones = {"Clientes", "Entrenadores", "Rutinas", "Incentivos", "Dietas", "Areas","Comida","Ejercicios"};
        String[] op_acciones = {"Crear", "Modificar", "Mostrar", "Borrar"};
        String[] op_acciones2 = {"Crear", "Modificar", "Mostrar", "Borrar","Asignar"};
        String[] op_acciones3 = {"Mostrar", "Borrar"};
        String otraaccion;
        String Elegida = "";
do {
	
	 Elegida = (String) JOptionPane.showInputDialog(null, "Que seccion deseas acceder:", "Menu secciones", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

     switch (Elegida) {
         case "Clientes":
             Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones, op_acciones[0]);

             switch (Elegida) {
                 case "Crear":
                 	RegistrarCliente();
                     break;

                 case "Modificar":
                 	ModificarCliente();
                     
                     break;

                 case "Mostrar":
                 	MostrarClientes();
                     break;

                 case "Borrar":
                 	BorrarClientes();
                     break;

             }
                break;
            	
            case "Incentivos":
                Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones, op_acciones[0]);

            	 switch (Elegida) {
                 case "Crear":
                	 crearIncentivo();
                     break;

                 case "Modificar":
                	 modificarIncentivo();
                     break;

                 case "Mostrar":
                	 mostrarIncentivo();
                     break;

                 case "Borrar":
                	 borrarIncentivo();
                     break;
             }
                break;
               

         case "Entrenadores" :
             Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones2, op_acciones2[0]);

             switch (Elegida) {
                 case "Crear":

                 	CrearEntrenadores();
                     break;

                 case "Modificar":
                 	ModificarEntrenador();
                     break;

                 case "Mostrar":
                 	MostrarEntrenadores();
                     break;

                 case "Borrar":
                 	BorrarEntrenador();

                     break;

                 case "Asignar":
                 	AsignarEntrenador();
                 	break;
             }
             break;

         case "Rutinas":
            
         	Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones3, op_acciones3[0]);

             switch (Elegida) {
                 case "Mostrar":
                 	MostrarRutinas();
                     break;

                 case "Borrar":
                 	BorrarRutinas();
                     break;
             }
             break;
             
         case "Comida":
         	 Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones, op_acciones[0]);

         	 switch (Elegida) {
              case "Crear":
             	 crearComida();
                  break;

              case "Modificar":
             	 modificarComida();
                  break;

              case "Mostrar":
             	 mostrarComidas();
                  break;

              case "Borrar":
             	 borrarComida();
                  break;
          }
             break;
         	

         case "Dietas":
         	Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones3, op_acciones3[0]);

             switch (Elegida) {
                 case "Mostrar":
                 	MostrarDietas();
                     break;

                 case "Borrar":
                 	BorrarDietas();
                     break;
             }   
             break;
            
         case "Areas":
             Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones, op_acciones[0]);

         	 switch (Elegida) {
              case "Crear":
                  break;

              case "Modificar":
                  break;

              case "Mostrar":
                  break;

              case "Borrar":
                  break;
          }
             break;
         case "Ejercicios":
             Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones, op_acciones[0]);

         	 switch (Elegida) {
              case "Crear":
             	 crearEjercicio();
                  break;
                  
              case "Modificar":
             	 modificarEjercicio();
                  break;
                  
              case "Mostrar":
             	 mostrarEjercicios();
                  break;
                  
              case "Borrar":
             	 borrarEjercicio();
                  break;
          }
             break;
     }
     otraaccion=VerificacionesRepository.solicitarConfirmacion("Desea hacer otra accion?");
} while (otraaccion.equalsIgnoreCase("Si"));
       /*/
	}
}
