package tes;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

import controlador.AdminControlador;
import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import modelo.Comida;
import modelo.Persona;

public class Prueba {

    @Test
    public void IncioSesionV() {
    	ClienteControlador clientes= new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	EntrenadorControlador entrenadores =new EntrenadorControlador();
    	
    	LinkedList<Persona> personas= new LinkedList<Persona>();
    	personas.addAll(Admins.getAllAdmin());
    	personas.addAll(clientes.getAllClientes());
    	personas.addAll(entrenadores.getAllEntrenadores());
    	boolean flag;
    	int indice=Persona.Iniciarsesion("Admin", "adminpass1", personas);
    	
        assertEquals(true, indice!=-1);
    }
    
    @Test
    public void IncioSesionF() {
    	ClienteControlador clientes= new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	EntrenadorControlador entrenadores =new EntrenadorControlador();
    	
    	LinkedList<Persona> personas= new LinkedList<Persona>();
    	personas.addAll(Admins.getAllAdmin());
    	personas.addAll(clientes.getAllClientes());
    	personas.addAll(entrenadores.getAllEntrenadores());
    	boolean flag;
    	int indice=Persona.Iniciarsesion("Joaco@gmail.com", "adminpass1", personas);
    	
        assertEquals(true, indice==-1);
    }
    
}