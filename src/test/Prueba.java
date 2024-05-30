package test;
import static org.junit.Assert.assertEquals;

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
			if (Comida.mostrarComidas()) {
				flag=true;
				break;
				
			}
		}
	 assertEquals("NASHEIJ",flag);
		
	}
}
