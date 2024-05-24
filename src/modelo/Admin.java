package modelo;

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
		
		String Nombre=VerificacionesRepository.Sololetras("Escribe el nombre del cliente: ");
		
		
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
                        break;

                    case "Modificar":
                    	Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, op_beneficios, op_beneficios[0]);

                        switch (Elegida) {
                            case "agregar suscripcion":
                                Elegida = (String) JOptionPane.showInputDialog(null, "Que Accion desea hacer: ", "Menu Acciones", JOptionPane.DEFAULT_OPTION, null, entrenadores, entrenadores[0]);
                                break;

                            case "suspender suscripcion":
                                String clienteSuspender = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente que desea suspender:");
                                int confirmacionSuspender = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea suspender al cliente " + clienteSuspender + "?", "Confirmar suspensión", JOptionPane.YES_NO_OPTION);
                                if (confirmacionSuspender == JOptionPane.YES_OPTION) {
                                    JOptionPane.showMessageDialog(null, "Cliente suspendido exitosamente.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                                }
                                break;

                            case "quitar suscripcion":
                                String clienteQuitar = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente al que desea quitar la suscripción:");
                                int confirmacionQuitar = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea quitar la suscripción al cliente " + clienteQuitar + "?", "Confirmar quitar suscripción", JOptionPane.YES_NO_OPTION);
                                if (confirmacionQuitar == JOptionPane.YES_OPTION) {
                                    JOptionPane.showMessageDialog(null, "Suscripción del cliente quitada exitosamente.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada.");
                                }
                                break;
                        }
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

            case "Entrenadores":
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
