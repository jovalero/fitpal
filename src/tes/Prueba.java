package tes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modelo.Comida;
import modelo.Persona;

public class Prueba {

    @Test
    public void IncioSesion() {
        assertEquals(true, true);
    }
    
    @Test
    public void testAddComida() {
    	// Given
        Comida comida = new Comida("Pizza", "Deliciosa pizza con queso", 0);
    }
}