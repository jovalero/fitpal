package interfaces;

import java.util.LinkedList;

import modelo.Ejercicio;

public interface EjercicioRepository {
	 LinkedList <Ejercicio> getAllEjercicio();// Llama a todos los Ejercicios de la bdd
	 
	  Ejercicio getEjercicioByID(int id); // Llama solo a uno, por su id
	    
	    void addEjercicio(Ejercicio ejercicio); // Añade ejercicios a la bdd
	    
	    void updateEjercicio(Ejercicio ejercicio); // Actualiza los ejercicios de la bdd
	    
	    void deleteEjercicio(int id); // Elimina ejercicios de la bdd
	 
	 
}
