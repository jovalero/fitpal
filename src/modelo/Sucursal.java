package modelo;

import java.util.LinkedList;

public class Sucursal {
	private int id_sucursal;
	private String Direccion;
	private int Telefono;
	private Admin administrador;
	private LinkedList<Cliente> clientes= new LinkedList<>();
	private LinkedList<Entrenador> entrenadores= new LinkedList<>();
}
