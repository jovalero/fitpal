package interfaces;

import java.util.LinkedList;

import modelo.Ejercicio;

public interface EjercicioRepository {
	 LinkedList <Ejercicio> getAllEjercicio();
	 
	 Ejercicio getID_Ejercicio(int ID_Ejercicio);
	 
	 
}
