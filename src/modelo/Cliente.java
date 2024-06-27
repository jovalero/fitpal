package modelo;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import controlador.ClienteControlador;
import vistas.HomeCliente;
import vistas.HomeClientePremium;

public class Cliente extends Persona{
	
	private int id_cliente;
	private String objetivo;
	private int id_entrenador;
	private int id_dieta;
	private int id_rutina;
	private String estado_sus;
	private int puntos;
	private LocalDate Fechavenc;
	private double peso;
	private double altura;
	
	public Cliente() {
		super("", "", 0,0, 0, "", "");
	}
	public Cliente(String nombre, String apellido, int telefono, int id_sucursal, int dNI, int id_cliente, String email, String contrasena, String Objetivo, double peso, double altura) {
		super(nombre, apellido, telefono, id_sucursal, dNI,contrasena,email);
		this.id_cliente= id_cliente;
		this.objetivo= Objetivo;
		this.id_entrenador=0;
		this.id_dieta=0;
		this.estado_sus="Desactivada";
		this.puntos=0;
		this.Fechavenc=null;
		this.altura=altura;
		this.peso=peso;
	}
	public Cliente(String nombre, String apellido, int telefono, int id_sucursal, int dNI, String email, String contrasena, String Objetivo, double peso, double altura) {
		super(nombre, apellido, telefono, id_sucursal, dNI,contrasena,email);
		this.id_cliente= -1;
		this.objetivo= Objetivo;
		this.id_entrenador=0;
		this.id_dieta=0;
		this.estado_sus="Nuevo";
		this.puntos=0;
		this.Fechavenc=null;
		this.altura=altura;
		this.peso=peso;
	}

	public Cliente(String email, String contrasena) {
		super("", "", 123, 0, 555,contrasena,email);
		this.id_cliente=0;
		this.objetivo= "";
		this.id_entrenador=0;
		this.id_dieta=0;
		this.estado_sus="Desactivada";
		this.puntos=0;
		this.Fechavenc=null;
		this.peso=24.0;
		this.altura=1.5;
	}
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}


	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getId_entrenador() {
		return id_entrenador;
	}

	public void setId_entrenador(int id_entrenador) {
		this.id_entrenador = id_entrenador;
	}

	public int getId_dieta() {
		return id_dieta;
	}

	public void setId_dieta(int id_dieta) {
		this.id_dieta = id_dieta;
	}

	public int getId_rutina() {
		return id_rutina;
	}

	public void setId_rutina(int id_rutina) {
		this.id_rutina = id_rutina;
	}

	public int getPuntos() {
		return puntos;
	}

	public String getEstado_sus() {
		return estado_sus;
	}

	public void setEstado_sus(String estado_sus) {
		this.estado_sus = estado_sus;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public LocalDate getFechavenc() {
		return Fechavenc;
	}

	public void setFechavenc(LocalDate fechavenc) {
		Fechavenc = fechavenc;
	}


	@Override
	public String toString() {
		return "Cliente: " + this.getNombre() + " "+ this.getApellido() +" DNI: " + this.getDNI() + " Suscripcion: " + this.getEstado_sus() + " ID: " + this.getId_cliente();
	}

	@Override
	public void Menu() {
		JOptionPane.showMessageDialog(null, "Hola");
		ClienteControlador controlador= new ClienteControlador();
		
		if (this.getEstado_sus().equalsIgnoreCase("Activa") && this.getFechavenc().isBefore(LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "Su suscripcion esta vencida");
			this.setEstado_sus("Desactivada");
			controlador.updateCliente(this);
			new HomeCliente(this);
		}
		else {
			if (this.getEstado_sus().equalsIgnoreCase("Activa")) {
				new HomeClientePremium(this);
			}
			else if (this.getEstado_sus().equalsIgnoreCase("Suspendida")) {
				JOptionPane.showMessageDialog(null, "Suscripcion suspendida comunicarse con administrador");
			} 
			else {
				new HomeCliente(this);
			}
		}
	}
}
	
