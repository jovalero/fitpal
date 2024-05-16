package modelo;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Cliente extends Persona{
	
	private int id_cliente;
	private String objetivo;
	private int id_entrenador;
	private int id_dieta;
	private int id_rutina;
	private String estado_sus;
	private int puntos;
	private Date Fechavenc;
	private double peso;
	private double altura;
	
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

	public Date getFechavenc() {
		return Fechavenc;
	}

	public void setFechavenc(Date fechavenc) {
		Fechavenc = fechavenc;
	}


	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", objetivo=" + objetivo + ", id_entrenador=" + id_entrenador
				+ ", id_dieta=" + id_dieta + ", id_rutina=" + id_rutina + ", estado_sus=" + estado_sus + ", puntos="
				+ puntos + ", Fechavenc=" + Fechavenc + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getTelefono()=" + getTelefono() + ", getId_sucursal()=" + getId_sucursal()
				+ ", getDNI()=" + getDNI() + ", getContrasena()=" + getContrasena() + ", getUsuario()=" + getUsuario()
				+ "]";
	}

	@Override
	public void Menu() {
		 String[] opciones = {"Rutinas", "Dieta","Suscripcion", "Incentivo","Salir"};
	        String[] op_suscripcion = {"Suscribirme", "Quitar suscripcion"};
	        String Elegida = ""; 
	        int contador=0,pt=0+contador;
	  
	        do {

	        Elegida = (String) JOptionPane.showInputDialog(null, "Que seccion deseas acceder:", "Menu secciones", JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);
	        
	        switch (Elegida) {
	        case "Rutinas":
	        
	        JOptionPane.showMessageDialog(null, " Lunes: Piernas / Abs "
	        +"\n Martes: Pecho"
	        +"\n Miercoles: Espalda/ Abs"
	        +"\n Jueves: Descanso"
	        +"\n Viernes: Brazos"
	        +"\n Sabado: Descanso");
	        break;
	        
	        case "Dieta":
	        JOptionPane.showMessageDialog(null, " Lunes: Batido de frutas con leche desnatada o yogur griego bajo en grasa."
	        +"\n Martes: Tazón de avena con rodajas de plátano y un puñado de nueces."
	        +"\n Miercoles: Tortilla de claras de huevo con espinacas y tomates."
	        +"\n Jueves: Batido de proteínas con espinacas, plátano y leche de almendras."
	        +"\n Viernes: Yogur griego bajo en grasa con frutas frescas y un puñado de almendras."
	        +"\n Sabado: Tostadas integrales con aguacate y huevos revueltos.");
	        break;
	        
	        case "Suscripcion":
	        Elegida = (String) JOptionPane.showInputDialog(null, "Que seccion deseas acceder:", "Menu secciones", JOptionPane.DEFAULT_OPTION, null, op_suscripcion, op_suscripcion[0]);
	        switch (Elegida) {
	        case "Suscribirme":
	        contador=0+100;
	        JOptionPane.showMessageDialog(null, " Ganaste 100 puntos por haberte suscripto:"+"\n Puntos: "+contador);
	        break;
	        
	        case "Quitar suscripcion":
	        JOptionPane.showMessageDialog(null, " DESEO QUE VUELVAS PRONTO");
	        
	        break;
	        
	        }
	        case "Incentivo":
	        JOptionPane.showMessageDialog(null, "Tus puntos: "+pt);
	        break;
	        
	        }
	} while (!Elegida.equalsIgnoreCase("Salir"));
	}
}
	
