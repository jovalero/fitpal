package tes;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import org.junit.Test;

import controlador.AdminControlador;
import controlador.ClienteControlador;
import controlador.EntrenadorControlador;
import modelo.Admin;
import modelo.Cliente;
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
    
    @Test 
    public void RegistrarClienteV() {
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	int total=0;
    	int totaldesp=0;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			total++;
		}
    	Admin.RegistrarCliente();
    	for (Cliente Cliente : controlador.getAllClientes()) {
			totaldesp++;
		}
         assertEquals(true, total < totaldesp);
    	
    }
    @Test
    public void RegistrarClienteF() {
    	JOptionPane.showMessageDialog(null, "Falso");
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	int total=0;
    	int totaldesp=0;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			total++;
		}
    	Admin.RegistrarCliente();
    	for (Cliente Cliente : controlador.getAllClientes()) {
			totaldesp++;
		}
         assertEquals(true, total == totaldesp);
    	
    }
    @Test
    public void ModificarclienteV() {
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	Cliente cliente= controlador.getAllClientesBySucursal(Admin.getId_sucursal()).get(0);
    	Admin.ModificarCliente();

         assertEquals(true,!Admin.clientesiguales(cliente,controlador.getAllClientesBySucursal(Admin.getId_sucursal()).get(0)) );
    	
    }
    @Test 
    public void MostrarRutinasV() {
        RutinaControlador controlador = new RutinaControlador();
        AdminControlador Admins = new AdminControlador();
        Admin Admin = Admins.getAllAdmin().get(0);
        int total = 0;
        int totaldesp = 0;
        for (Rutina rutina : controlador.getAllRutinas()) {
            total++;
        }
        Admin.MostrarRutinas();
        for (Rutina rutina : controlador.getAllRutinas()) {
            totaldesp++;
        }
        assertEquals(true, total < totaldesp);   
    }

    @Test
    public void MostrarRutinasF() {
        JOptionPane.showMessageDialog(null, "Falso");
        RutinaControlador controlador = new RutinaControlador();
        AdminControlador Admins = new AdminControlador();
        Admin Admin = Admins.getAllAdmin().get(0);
        int total = 0;
        int totaldesp = 0;
        for (Rutina rutina : controlador.getAllRutinas()) {
            total++;
        }
        Admin.MostrarRutinas();
        for (Rutina rutina : controlador.getAllRutinas()) {
            totaldesp++;
        }
        assertEquals(true, total == totaldesp);   
    }
    @Test
    public void BorrarRutinasV() {
        RutinaControlador controlador = new RutinaControlador();
        AdminControlador Admins = new AdminControlador();
        Admin Admin = Admins.getAllAdmin().get(0);
        
        int totalAntes = controlador.getAllRutinas().size();
        
        if (totalAntes > 0) {
            Rutina rutinaABorrar = controlador.getAllRutinas().get(0);
            
            Admin.BorrarRutinas();
            
            int totalDespues = controlador.getAllRutinas().size();
            
            assertTrue(totalDespues < totalAntes);
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void BorrarRutinasF() {
        RutinaControlador controlador = new RutinaControlador();
        AdminControlador Admins = new AdminControlador();
        Admin Admin = Admins.getAllAdmin().get(0);
        
        for (Rutina rutina : controlador.getAllRutinas()) {
            controlador.deleteRutina(rutina.getId());
        }
        
        Admin.BorrarRutinas();
        
        assertEquals(0, controlador.getAllRutinas().size());
    @Test
    public void ModificarclienteF() {
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	Cliente cliente= controlador.getAllClientesBySucursal(Admin.getId_sucursal()).get(0);
    	Admin.ModificarCliente();

         assertEquals(true,Admin.clientesiguales(cliente,controlador.getAllClientesBySucursal(Admin.getId_sucursal()).get(0)) );
    	
    }
    @Test
    public void BorrarClienteV() { 
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	int total=0;
    	int totaldesp=0;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			total++;
		}
    	Admin.BorrarClientes();;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			totaldesp++;
		}
    	assertEquals(true, total>totaldesp);
    }
    @Test
    public void BorrarClienteF() { 
    	ClienteControlador controlador = new ClienteControlador();
    	AdminControlador Admins= new AdminControlador();
    	Admin Admin=Admins.getAllAdmin().get(0);
    	int total=0;
    	int totaldesp=0;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			total++;
		}
    	Admin.BorrarClientes();;
    	for (Cliente Cliente : controlador.getAllClientes()) {
			totaldesp++;
		}
    	assertEquals(true, total==totaldesp);
    }
}