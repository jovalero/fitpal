package test;
import org.junit.Test;

import controlador.ComidaControlador;
import modelo.Admin;
import modelo.Comida;

public class Prueba {
	@Test
	public void PruebaAdmin() {
		ComidaControlador controlador = new ComidaControlador();
		boolean flag = false;
		for (Comida comida : controlador.getAllComidas()) {
			if (comida.mostrarComidas()== true) {
				flag=true;
				break;
			} else {
				
			}
		}
	
	
	}
}
