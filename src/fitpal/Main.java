package fitpal;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
    	LinkedList<Persona> personas= new LinkedList<>();
    	
    	Admin administrador= new Admin("123");
    	Cliente cliente=new Cliente("juan", "123");
    	Entrenador entrenador= new Entrenador("Paco", "123");
    	
    	personas.add(entrenador);
    	personas.add(cliente);
    	personas.add(administrador);
    	iniciarSesion(personas);
        
    }

    public static void iniciarSesion(LinkedList<Persona> personas) {
    	
    	String usuario=JOptionPane.showInputDialog("Ingresa usuario: ");
    	String contrasena=JOptionPane.showInputDialog("Ingresa contrasena");
    	
    	for (Persona persona : personas) {
    		if (persona.getUsuario().equalsIgnoreCase(usuario) && persona.getContrasena().equalsIgnoreCase(contrasena)) {
    			persona.Menu();
				}
			}
		}   	
}