package conexion;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import controlador.AdminControlador;
import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import interfaces.VerificacionesRepository;
import modelo.*;

public class Main {

    public static void main(String[] args) {
		ClienteControlador controladorc= new ClienteControlador();
		EntrenadorControlador controladore=new EntrenadorControlador();
		AdminControlador controladora= new AdminControlador();
    	LinkedList<Persona> personas= new LinkedList<>();
    	int indice= -1;
    	
    	for (Cliente cliente : controladorc.getAllClientes()) {
			personas.add(cliente);
		}
    	for (Entrenador Entrenador : controladore.getAllEntrenadores()) {
			personas.add(Entrenador);
		}
    	for (Admin admin : controladora.getAllAdmin()) {
			personas.add(admin);
		}
    	Persona[] arraypersona=personas.toArray(new Persona[0]);  
    	 indice=Persona.Iniciarsesion(VerificacionesRepository.Sololetras("Ingresa mail"),JOptionPane.showInputDialog("Ingresa contraseña"), personas);
    	if (indice!=-1) {
    		arraypersona[indice].Menu();
    }
    	}
}