package interfaces;

import java.util.LinkedList;

import modelo.Comida;

public interface ComidaRepository {
	 LinkedList<Comida> getAllComidas(); // Llama a todas las Comidas de la bdd
	    
	    Comida getComidaByID(int id); // Llama solo a una, por su id
	    
	    void addComida(Comida comida); // Añade comidas a la bdd
	    
	    void updateComida(Comida comida); // Actualiza las comidas de la bdd
	    
	    void deleteComida(int id); // Elimina comidas de la bdd
	
}
