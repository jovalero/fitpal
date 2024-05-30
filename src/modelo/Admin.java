package modelo;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
import controlador.EjercicioControlador;
import controlador.EntrenadorControlador;
import controlador.IncentivoControlador;
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
		ClienteControlador controlador;

	    try {
	        controlador = new ClienteControlador();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return;  
	    }

	    if (controlador.getConnection() == null) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return; 
	    }
		
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
		ClienteControlador controlador;
		   try {
		        controlador = new ClienteControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
		LinkedList<Cliente> Clientes = controlador.getAllClientesBySucursal(this.getId_sucursal());
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
		ClienteControlador controlador;
		   try {
		        controlador = new ClienteControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
		LinkedList<Cliente> Clientes = controlador.getAllClientesBySucursal(this.getId_sucursal());
		String nota="Lista de clientes: \n";
		
		for (Cliente cliente : Clientes) {
			nota+= cliente.toString() + "\n";String otramodificacion;
		
		}
		JOptionPane.showMessageDialog(null, nota);
	}
	public void BorrarClientes() {
		ClienteControlador controlador;
		   try {
		        controlador = new ClienteControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
		int DNI;
		String mensaje="";
		String otramodificacion;
		do {
			mensaje="";
			DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI de cliente a borrar: ");
			LinkedList<Cliente> Clientes = controlador.getAllClientesBySucursal(this.getId_sucursal());
			
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
		   try {
		        controlador = new EntrenadorControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
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
	EntrenadorControlador controlador;
	   try {
	        controlador = new EntrenadorControlador();
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return;  
	    }

	    if (controlador.getConnection() == null) {
	        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
	        return; 
	    }
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadoresBySucursal(this.getId_sucursal());
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
		EntrenadorControlador controlador;
		   try {
		        controlador = new EntrenadorControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadoresBySucursal(this.getId_sucursal());
		String nota="Lista de Entrenadores: \n";
		
		for (Entrenador Entrenador : Entrenadores) {
			nota+= Entrenador.toString() + "\n";
		
		}
		JOptionPane.showMessageDialog(null, nota);
	}
	public void BorrarEntrenador() {
		EntrenadorControlador controlador;
		   try {
		        controlador = new EntrenadorControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }
		int DNI;
		String mensaje="";
		String otramodificacion;
		do {
			mensaje="";
			DNI=VerificacionesRepository.SoloEnteros("Ingrese DNI de Entrenador a borrar: ");
			LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadoresBySucursal(this.getId_sucursal());
			
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
		EntrenadorControlador controlador;
		ClienteControlador controladorcliente;
		   try {
		        controlador = new EntrenadorControlador();
		        controladorcliente= new ClienteControlador();
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return;  
		    }

		    if (controlador.getConnection() == null) {
		        JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos");
		        return; 
		    }

		String [] accion= {"Añadir Cliente","Quitar Cliente","Salir"};
		int seleccion2;
		Cliente clienteseleccionado;
		Entrenador opcion;
		String Seleccion;
		String otramodificacion;
		LinkedList<Entrenador> Entrenadores = controlador.getAllEntrenadoresBySucursal(this.getId_sucursal());
		Entrenador [] arrayEntrenadores= Entrenadores.toArray(new Entrenador[0]);
do {
	
	opcion= (Entrenador)JOptionPane.showInputDialog(null,"A que entrenador deseas configurar asignados:  ","Seleccionador Entrenador",JOptionPane.DEFAULT_OPTION,null,arrayEntrenadores,arrayEntrenadores[0]);

	do {
		
		LinkedList<Cliente> ClientesDisponibles= new LinkedList<Cliente>();
		LinkedList<Cliente> Clientesdelentrenador= new LinkedList<Cliente>();
		LinkedList<Cliente> Clientes=controladorcliente.getAllClientesBySucursal(this.getId_sucursal());
		for (Cliente cliente : Clientes) {
			if (cliente.getId_entrenador()==0 && !cliente.getEstado_sus().equalsIgnoreCase("Desactivada")) {
				ClientesDisponibles.add(cliente);
			}
		}
		Cliente [] ArrrayClientesDisponibles=ClientesDisponibles.toArray(new Cliente[0]);
		Cliente [] ArrrayClientesEntrenador;
		if (opcion.getNumentrenados()<50) {
			Seleccion=(String) JOptionPane.showInputDialog(null,"Que desea realizar?","Tittle",JOptionPane.DEFAULT_OPTION,null,accion,accion[0]);

		}
		else {
			String eliminar=VerificacionesRepository.solicitarConfirmacion("Desea eliminar un cliente, este entrenador ya tiene mas de 50 personas");
			if (eliminar.equalsIgnoreCase("Si")) {
				Seleccion="Quitar cliente";
			}
			else {
				Seleccion="";
			}
		}
		switch (Seleccion) {
		case "Añadir cliente":
			seleccion2=JOptionPane.showOptionDialog(null, "Que cliente desea añadirle", "Cliente", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE , null, ArrrayClientesDisponibles, ArrrayClientesDisponibles[0]);
			clienteseleccionado=ArrrayClientesDisponibles[seleccion2];
			clienteseleccionado.setId_entrenador(opcion.getId_entrenador());
			controladorcliente.updateCliente(clienteseleccionado);
			opcion.setNumentrenados(opcion.getNumentrenados()+1);
			controlador.updateEntrenador(opcion);
			break;
		case "Quitar cliente":
			
			for (Cliente cliente : Clientes) {
				if (cliente.getId_entrenador()==opcion.getId_entrenador()) {
					Clientesdelentrenador.add(cliente);
				}
			}
			ArrrayClientesEntrenador=Clientesdelentrenador.toArray(new Cliente[0]);
			seleccion2=JOptionPane.showOptionDialog(null, "Que cliente desea eliminar", "Cliente", JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE , null, ArrrayClientesEntrenador, ArrrayClientesEntrenador[0]);
			clienteseleccionado=ArrrayClientesEntrenador[seleccion2];
			clienteseleccionado.setId_entrenador(0);
			opcion.setNumentrenados(opcion.getNumentrenados()-1);
			controladorcliente.updateCliente(clienteseleccionado);
			controlador.updateEntrenador(opcion);
			break;
		default:
			break;
		}
		otramodificacion=VerificacionesRepository.solicitarConfirmacion("Deseas hacer otra modificacion a este entrenador?");
		if (otramodificacion.equalsIgnoreCase("Si") && opcion.getNumentrenados()<50) {
		
		}
	} while (otramodificacion.equalsIgnoreCase("Si") && opcion.getNumentrenados()<50);
	
	
	otramodificacion=VerificacionesRepository.solicitarConfirmacion("Deseas modificar otro entrenador?");

} while (otramodificacion.equalsIgnoreCase("Si"));
		
		
	
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

	 public static void mostrarIncentivo() {
		 IncentivoControlador controlador = new IncentivoControlador();
		 LinkedList<Incentivo> listaincentivo = controlador.getAllIncentivos();
	     StringBuilder nota = new StringBuilder("Lista:\n");
	        
	        for (Incentivo incentivo : listaincentivo) {
	            nota.append(incentivo.toString()).append("\n");
	        }
	        
	        JOptionPane.showMessageDialog(null, nota.toString());
	    }
	 
	public static void borrarIncentivo() {
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
                     break;

                 case "Modificar":
                     break;

                 case "Mostrar":
                     break;

                 case "Borrar":
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
                     break;

                 case "Modificar":
                     break;

                 case "Mostrar":
                     break;

                 case "Borrar":
                     break;
             }
                break;
        }
	}
}
