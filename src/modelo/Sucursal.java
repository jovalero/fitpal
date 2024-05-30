package modelo;

import java.util.LinkedList;

public class Sucursal {
	private int id_sucursal;
	private String Direccion;
	private int Telefono;
<<<<<<< HEAD
=======

	
	

>>>>>>> franco-
	private Admin administrador;
	private LinkedList<Cliente> clientes= new LinkedList<>();
	private LinkedList<Entrenador> entrenadores= new LinkedList<>();
	public Sucursal(int id_sucursal, String direccion, int telefono, Admin administrador, LinkedList<Cliente> clientes,
			LinkedList<Entrenador> entrenadores) {
		super();
		this.id_sucursal = id_sucursal;
		Direccion = direccion;
		Telefono = telefono;
		this.administrador = administrador;
		this.clientes = clientes;
		this.entrenadores = entrenadores;
	}
<<<<<<< HEAD
=======

>>>>>>> franco-
	
	
}
