package modelo;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import interfaces.VerificacionesRepository;


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
	
	public void RegistrarCliente() {
		ClienteControlador controlador= new ClienteControlador();
		int DNI;
		String Nombre=VerificacionesRepository.Sololetras("Escribe el nombre del cliente: ");
		if (Nombre != null) {
			String Apellido=VerificacionesRepository.Sololetras("Escribe el apellido del cliente");
			if (Apellido !=null) {
				String Email=VerificacionesRepository.Mail();
				if (Email!=null) {
					int Telefono=VerificacionesRepository.SoloEnteros("Ingresa el telefono del cliente: ");
					if (Telefono!=-1) {
						int sucursal= this.getId_sucursal();
						do {
							DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI del cliente");
						} while (VerificacionesRepository.DNIExistente(DNI));
						if (DNI!=-1) {
							String Contrasena= "Primeracontrasena!";
							Double Peso=VerificacionesRepository.SoloDoubles("Ingresa peso: ");
							if (Peso!=-1) {
								Double Altura=VerificacionesRepository.SoloDoubles("Ingresa altura: ");
								if (Altura!=-1) {
									Cliente nuevocliente= new Cliente(Nombre,Apellido,Telefono,sucursal,DNI,Email,Contrasena,"Nuevo",Peso,Altura);
									controlador.addCliente(nuevocliente);
								}
							}
						}
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Has cancelado la operacion");
		}

	}
	
	public void ModificarCliente() {
		String otramodificacion;
		ClienteControlador controlador = new ClienteControlador();
		LinkedList<Cliente> Clientes = controlador.getAllClientes(this.getId_sucursal());
		Cliente [] ArrayClientes= Clientes.toArray(new Cliente[0]);
		String[] Opciones= {"Nombre","Apellido","Email","Contraseña","DNI","Suscripcion","Puntos","Salir"};
		do {
			Cliente opcion=(Cliente)JOptionPane.showInputDialog(null,"Que cliente quieres modificar: ","Seleccionador cliente",JOptionPane.DEFAULT_OPTION,null,ArrayClientes,ArrayClientes[0]);
			Cliente nuevocliente= opcion;
			do {
				int accion= JOptionPane.showOptionDialog(null, "Que deseas hacer?", "accion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opciones, Opciones[0]);
				
				switch (accion) {
				case 0:
					String Nombre=VerificacionesRepository.Sololetras("Tu antiguo nombre es: " + nuevocliente.getNombre() +" Coloca el nuevo: ");
					if (Nombre!=null) {
						nuevocliente.setNombre(Nombre);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					
					break;
				case 1:
					String Apellido=VerificacionesRepository.Sololetras("Tu antiguo Apellido es: " + nuevocliente.getApellido() +" Coloca el nuevo: ");
					if (Apellido!=null) {
						nuevocliente.setApellido(Apellido);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				case 2:
					String Email=VerificacionesRepository.Mail();
					if (Email!=null) {
						nuevocliente.setUsuario(Email);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				case 3:
					String Contrasena="Nuevacontra!";
					nuevocliente.setContrasena(Contrasena);
					break;
				case 4:
					int DNI=VerificacionesRepository.SoloEnteros("El antiguo dni es: " + nuevocliente.getDNI()+ " Coloca el nuevo:");
					if (DNI!=-1) {
						nuevocliente.setDNI(DNI);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				case 5:
					String [] opcionesnuevas= {"Estado de suscripcion","Fecha de vencimiento","Salir"};
					int elegida=JOptionPane.showOptionDialog(null, "Que deseas modificar de la suscripcion: ", "Menu suscripcion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesnuevas, opcionesnuevas[0]);
					switch (elegida) {
					case 0:
						String [] opcionesestado= {"Desactivada","Activada","Suspendida"};
						do {
							elegida=JOptionPane.showOptionDialog(null, "Que estado deseas que este: ", "Menu suscripcion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesestado, opcionesestado[0]);
							if ((elegida==0 && !nuevocliente.getEstado_sus().equalsIgnoreCase("Desactivada")) || (elegida==1 && !nuevocliente.getEstado_sus().equalsIgnoreCase("Activada")) || (elegida==2 && !nuevocliente.getEstado_sus().equalsIgnoreCase("Suspendida"))) {
								switch (elegida) {
								case 0:
									nuevocliente.setEstado_sus("Desactivada");
									break;
								case 1:
									LocalDate fechanueva=null;
									nuevocliente.setEstado_sus("Activada");
									do {
										fechanueva=VerificacionesRepository.pedirFecha("Coloca la fecha de vencimiento");
										if (fechanueva!=null) {
											nuevocliente.setFechavenc(fechanueva);
										}
										else {
											JOptionPane.showMessageDialog(null, "Coloca la fecha porfavor");
										}
									} while (fechanueva!=null);

									
									break;
								case 2:
									nuevocliente.setEstado_sus("Suspendida");
									break;

								default:
									break;
								}
								break;
							}
							else {
								JOptionPane.showMessageDialog(null, "Ya esta en ese estado");
							}
							otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea realizar otra modificacion al cliente?");
						} while (otramodificacion.equalsIgnoreCase("Si"));
						

					case 1:
						LocalDate fechanueva=null;
						do {
							fechanueva=VerificacionesRepository.pedirFecha("Coloca la nueva fecha de vencimiento");
							if (fechanueva!=null) {
								nuevocliente.setFechavenc(fechanueva);
							}
							else {
								JOptionPane.showMessageDialog(null, "Coloca la fecha porfavor");
							}
						} while (fechanueva!=null);
						break;
					default:
						break;
					}
					break;
				case 6:
					String[] opcionesnuevas2= {"Restar","Sumar"};
					do {
						elegida=JOptionPane.showOptionDialog(null, "Que Operaciond desea hacer?: ", "Menu puntos", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcionesnuevas2, opcionesnuevas2[0]);
						 int puntosaoperar=VerificacionesRepository.SoloEnteros("Ingresa los puntos que quieres operar los puntos del cliente son: " + nuevocliente.getPuntos());
						 int puntostotales;
						 if (elegida==0) {
							puntostotales=nuevocliente.getPuntos()-puntosaoperar;
							if (puntostotales<0) {
								JOptionPane.showMessageDialog(null, "No se puede tener puntos negativos");
								puntostotales=0;
							}
							nuevocliente.setPuntos(puntostotales);
							JOptionPane.showMessageDialog(null, "Los puntos del cliente ahora son: " + puntostotales);
							
							
						}
						 else if (elegida==1) {
							puntostotales=nuevocliente.getPuntos()+puntosaoperar;
							nuevocliente.setPuntos(puntostotales);
							JOptionPane.showMessageDialog(null, "Los puntos del cliente ahora son: " + puntostotales);

						}
						 else {
							JOptionPane.showMessageDialog(null, "Operacion cancelada");
						}
						otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea sumar/restar mas puntos?");
					} while (otramodificacion.equalsIgnoreCase("Si"));
					 
					 break;
					
				default:
					break;
				}
				
					otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea realizar otra modificacion al cliente?");
				
			} while (otramodificacion.equalsIgnoreCase("Si"));
			
			if (opcion==nuevocliente) {
				JOptionPane.showMessageDialog(null, "No se modifico nada");
			}
			else {
				controlador.updateCliente(nuevocliente);
				JOptionPane.showMessageDialog(null, "Se actualizo el cliente");
			}
			otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea modificar otro cliente?");
		} while (otramodificacion.equalsIgnoreCase("Si"));
		
	}
	
	public void MostrarClientes(){
		ClienteControlador controlador = new ClienteControlador();
		LinkedList<Cliente> Clientes = controlador.getAllClientes(this.getId_sucursal());
		String nota="Lista de clientes: \n";
		
		for (Cliente cliente : Clientes) {
			nota+= cliente.toString() + "\n";String otramodificacion;
		
		}
		JOptionPane.showMessageDialog(null, nota);
	}
	public void BorrarClientes() {
		ClienteControlador controlador = new ClienteControlador();
		int DNI;
		String mensaje="";
		String otramodificacion;
		do {
			mensaje="";
			DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI de cliente a borrar: ");
			LinkedList<Cliente> Clientes = controlador.getAllClientes(this.getId_sucursal());
			
			for (Cliente cliente : Clientes) {
				if (cliente.getDNI()==DNI) {
					controlador.deleteCliente(cliente.getId_cliente());
					mensaje="Se elimino el cliente" + cliente.toString();
					JOptionPane.showMessageDialog(null, mensaje);
				}
			}
			if (mensaje.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "No se consiguio ningun cliente con ese DNI");
			}
			otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea borrar algun otro usuario?");
		} while (otramodificacion.equalsIgnoreCase("Si"));
		

	}
	public void CrearEntrenadores() {
		EntrenadorControlador controlador= new EntrenadorControlador();
		int DNI;
		String Nombre=VerificacionesRepository.Sololetras("Escribe el nombre del Entrenador: ");
		if (Nombre != null) {
			String Apellido=VerificacionesRepository.Sololetras("Escribe el apellido del Entrenador");
			if (Apellido !=null) {
				String Email=VerificacionesRepository.Mail();
				if (Email!=null) {
					int Telefono=VerificacionesRepository.SoloEnteros("Ingresa el telefono del Entrenador: ");
					if (Telefono!=-1) {
						int sucursal= this.getId_sucursal();
						do {
							DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI del Entrenador");
						} while (VerificacionesRepository.DNIExistente(DNI));
						if (DNI!=-1) {
							String Contrasena= "Primeracontrasena!";
							int numentrenados=0;
							Entrenador nuevoentrenador=new Entrenador(Nombre,Apellido,Telefono,sucursal,DNI,Email,Contrasena,numentrenados);
							controlador.addEntrenador(nuevoentrenador);
						}
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Has cancelado la operacion");
		}

		
	}
	
	public void ModificarEntrenador() {
		String otramodificacion;
	EntrenadorControlador controlador = new EntrenadorControlador();
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadores(this.getId_sucursal());
		Entrenador [] ArrayEntrenadores= Entrenadores.toArray(new Entrenador[0]);
		String[] Opciones= {"Nombre","Apellido","Email","Contraseña","DNI","Salir"};
		do {
			Entrenador opcion=(Entrenador)JOptionPane.showInputDialog(null,"Que Entrenador quieres modificar: ","Seleccionador Entrenador",JOptionPane.DEFAULT_OPTION,null,ArrayEntrenadores,ArrayEntrenadores[0]);
			Entrenador nuevoentrenador= opcion;
			do {
				int accion= JOptionPane.showOptionDialog(null, "Que deseas hacer?", "accion", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, Opciones, Opciones[0]);
				
				switch (accion) {
				case 0:
					String Nombre=VerificacionesRepository.Sololetras("Tu antiguo nombre es: " + nuevoentrenador.getNombre() +" Coloca el nuevo: ");
					if (Nombre!=null) {
						nuevoentrenador.setNombre(Nombre);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					
					break;
				case 1:
					String Apellido=VerificacionesRepository.Sololetras("Tu antiguo Apellido es: " + nuevoentrenador.getApellido() +" Coloca el nuevo: ");
					if (Apellido!=null) {
						nuevoentrenador.setApellido(Apellido);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				case 2:
					String Email=VerificacionesRepository.Mail();
					if (Email!=null) {
						nuevoentrenador.setUsuario(Email);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				case 3:
					String Contrasena="Nuevacontra!";
					nuevoentrenador.setContrasena(Contrasena);
					break;
				case 4:
					int DNI=VerificacionesRepository.SoloEnteros("El antiguo dni es: " + nuevoentrenador.getDNI()+ " Coloca el nuevo:");
					if (DNI!=-1) {
						nuevoentrenador.setDNI(DNI);
					}
					else {
						JOptionPane.showMessageDialog(null, "Se cancelo la operacion");
					}
					break;
				default:
					break;
				}
				
					otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea realizar otra modificacion al cliente?");
				
			} while (otramodificacion.equalsIgnoreCase("Si"));
			
			if (opcion==nuevoentrenador) {
				JOptionPane.showMessageDialog(null, "No se modifico nada");
			}
			else {
				controlador.updateEntrenador(nuevoentrenador);
				JOptionPane.showMessageDialog(null, "Se actualizo el cliente");
			}
			otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea modificar otro cliente?");
		} while (otramodificacion.equalsIgnoreCase("Si"));
		
	}
	
	public void MostrarEntrenadores() {
		EntrenadorControlador controlador = new EntrenadorControlador();
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadores(this.getId_sucursal());
		String nota="Lista de Entrenadores: \n";
		
		for (Entrenador Entrenador : Entrenadores) {
			nota+= Entrenador.toString() + "\n";
		
		}
		JOptionPane.showMessageDialog(null, nota);
	}
	public void BorrarEntrenador() {
		EntrenadorControlador controlador = new EntrenadorControlador();
		int DNI;
		String mensaje="";
		String otramodificacion;
		do {
			mensaje="";
			DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI de Entrenador a borrar: ");
			LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadores(this.getId_sucursal());
			
			for (Entrenador Entrenador : Entrenadores) {
				if (Entrenador.getDNI()==DNI) {
					controlador.deleteEntrenador(Entrenador.getId_entrenador());
					mensaje="Se elimino el cliente" + Entrenador.toString();
					JOptionPane.showMessageDialog(null, mensaje);
				}
			}
			if (mensaje.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "No se consiguio ningun Entrenador con ese DNI");
			}
			otramodificacion=VerificacionesRepository.solicitarConfirmacion("Desea borrar algun otro usuario?");
		} while (otramodificacion.equalsIgnoreCase("Si"));
	}
	
	public void AsignarEntrenador() {
		EntrenadorControlador controlador = new EntrenadorControlador();
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadores(this.getId_sucursal());
		LinkedList<Entrenador>EntrenadoresDisponibles= new LinkedList<Entrenador>();
		
		for (Entrenador entrenador : Entrenadores) {
			if (entrenador.getNumentrenados()<50) {
				EntrenadoresDisponibles.add(entrenador);
			}
		}
		Entrenador [] arrayEntrenadores= EntrenadoresDisponibles.toArray(new Entrenador[0]);
		Entrenador opcion= (Entrenador)JOptionPane.showInputDialog(null,"A que entrenador deseas configurar asignados:  ","Seleccionador Entrenador",JOptionPane.DEFAULT_OPTION,null,arrayEntrenadores,arrayEntrenadores[0]);
		
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
	            controlador.deleteRutina(rutinaAEliminar.getId());
	            JOptionPane.showMessageDialog(null, "La rutina ha sido eliminada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró la rutina seleccionada.");
	        }
	    }
	}
	
	
	private static LinkedList<Ejercicio> listaEjercicios = new LinkedList<>();
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
		
		
			public static void mostrarEjercicios() {
				
				StringBuilder nota = new StringBuilder("Lista de ejercicios:\n");
				
				for (Ejercicio ejercicio : listaEjercicios) {
					nota.append(ejercicio.toString()).append("\n");
				}
				JOptionPane.showMessageDialog(null, nota.toString());
				
			}
		
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
		
		public static void crearComida() {
			String Nombre = JOptionPane.showInputDialog("Ingrese el nombre de la comida :");
			String Descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de la comida:");
			int ID_Comida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de la Comida:"));
			
			Comida nuevaComida = new Comida(Nombre,Descripcion,ID_Comida);
			listacomidas.add(nuevaComida);
			JOptionPane.showMessageDialog(null,"Comida creada exitosamente!");
		}
		
		
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
		
		private static LinkedList<Comida> listacomidas = new LinkedList<>();
		 public static void mostrarComidas() {
		        StringBuilder nota = new StringBuilder("Lista de comidas:\n");
		        
		        for (Comida comida : listacomidas) {
		            nota.append(comida.toString()).append("\n");
		        }
		        
		        JOptionPane.showMessageDialog(null, nota.toString());
		    }
		 
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

	@Override
	public void Menu() {
        String[] opciones = {"Clientes", "Entrenadores", "Rutinas", "Incentivos", "Dietas", "Areas","Comida","Ejercicios"};
        String[] op_acciones = {"Crear", "Modificar", "Mostrar", "Borrar"};
        String[] op_acciones2 = {"Crear", "Modificar", "Mostrar", "Borrar","Asignar"};
        String[] op_acciones3 = {"Mostrar", "Borrar"};

        String Elegida = "";

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
            	
            case "Incentivos":
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

            case "Dietas":
            	Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_acciones3, op_acciones3[0]);

                switch (Elegida) {
                    case "Mostrar":
                        break;

                    case "Borrar":
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
	}
}
