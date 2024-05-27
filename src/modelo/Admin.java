package modelo;

import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
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
		LinkedList<Cliente> Clientes = controlador.getAllClientes();
		Cliente [] ArrayClientes= Clientes.toArray(new Cliente[0]);
		String[] Opciones= {"Nombre","Apellido","Email","Contraseña","DNI","Suscripcion","Puntos","Salir"};
		
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
		
	}
	
	

	@Override
	public void Menu() {
        String[] opciones = {"Clientes", "Entrenadores", "Rutinas", "Incentivos", "Dietas", "Areas"};
        String[] op_acciones = {"Crear", "Modificar", "Mostrar", "Borrar"};
        String[] op_beneficios = {"agregar suscripcion", "suspender suscripcion", "quitar suscripcion"};
        String[] entrenadores = {"Entrenador 1", "Entrenador 2", "Entrenador 3"};
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
                        break;

                    case "Borrar":
                        String clienteBorrar = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente que desea borrar:");
                        int confirmacionBorrar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea borrar al cliente " + clienteBorrar + "?", "Confirmar borrado", JOptionPane.YES_NO_OPTION);
                        if (confirmacionBorrar == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "Cliente borrado exitosamente.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Operación cancelada.");
                        }
                        break;
                }
                break;

            case "Entrenadores" :
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

            case "Rutinas":
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
        }
	}
	

}
