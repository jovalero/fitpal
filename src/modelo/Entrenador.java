package modelo;

import javax.swing.JOptionPane;

public class Entrenador extends Persona{
	
	private int id_entrenador;
	private int numentrenados;

	public Entrenador(String nombre, String apellido, int telefono, int id_sucursal, int dNI, int identrenador, String email,String contrasena, int numentrenados) {
		super(nombre, apellido, telefono, id_sucursal, dNI,contrasena,email);
		this.id_entrenador=identrenador;
		this.numentrenados=numentrenados;
	}

	public Entrenador(String email,String contrasena) {
		super("", "", 123, 0,555,contrasena,email);
		this.id_entrenador=0;
		this.numentrenados=0;
	}
	public int getId_entrenador() {
		return id_entrenador;
	}

	public void setId_entrenador(int id_entrenador) {
		this.id_entrenador = id_entrenador;
	}


	public int getNumentrenados() {
		return numentrenados;
	}

	public void setNumentrenados(int numentrenados) {
		this.numentrenados = numentrenados;
	}


	
	@Override
	public String toString() {
		return "Entrenador [id_entrenador=" + id_entrenador + ", numentrenados=" + numentrenados + ", getNombre()="
				+ getNombre() + ", getApellido()=" + getApellido() + ", getTelefono()=" + getTelefono()
				+ ", getId_sucursal()=" + getId_sucursal() + ", getDNI()=" + getDNI() + ", getContrasena()="
				+ getContrasena() + ", getUsuario()=" + getUsuario() + "]";
	}

	@Override
	public void Menu() {
		 String[] opciones = {
		            "1. Visualizar datos de entrenados",
		            "2. Asignar rutinas de entrenamiento y dietas",
		            "3. Visualizar incentivos",
		            "4. Canjear incentivos",
		            "5. Salir"
		        };
		        int opcion;
		        do {
		            opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Menú Principal", 
		                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		            switch (opcion) {
		                case 0:
		                    visualizarDatos();
		                    break;
		                case 1:
		                    asignarRutinas();
		                    break;
		                case 2:
		                    visualizarIncentivos();
		                    break;
		                case 3:
		                    canjearIncentivos();
		                    break;
		                case 4:
		                    JOptionPane.showMessageDialog(null, "CHAU");
		                    break;
		            }
		        } while (opcion != 4);
	}
	 private static void visualizarDatos() {
	        JOptionPane.showMessageDialog(null, "panchito, 1.54m, 60kg");
	    }

	    private static void asignarRutinas() {
	        JOptionPane.showMessageDialog(null, "No hay rutinas en la base de datos");
	    }

	    private static void visualizarIncentivos() {
	        JOptionPane.showMessageDialog(null, "No hay incentivos");
	    } 

	    private static void canjearIncentivos() {
	        JOptionPane.showMessageDialog(null, "No existen incentivos que canjea");
	    } 
}
