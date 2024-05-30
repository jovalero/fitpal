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