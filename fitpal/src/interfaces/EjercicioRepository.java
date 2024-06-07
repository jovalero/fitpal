
import java.util.LinkedList;
=======
public interface EjercicioRepository {

LinkedList<Ejercicio> getAllEjercicio(); // llama a todos los Admis de la bdd
    
    Ejercicio getEjercicioByid(int id); //llama solo a uno, por su id
    
    void addEjercicio(Ejercicio Ejercicio); //añade usuarios a la bdd
    
    void updateEjercicio(Ejercicio Ejercicio); //actualiza los usuarios de la bdd
    
    void deleteEjercicio(int Ejercicio); //eliminar usuarios de la bdd
>>>>>>> franco-

import modelo.Ejercicio;

public interface EjercicioRepository {
	 LinkedList <Ejercicio> getAllEjercicio();// Llama a todos los Ejercicios de la bdd
	 
	  Ejercicio getEjercicioByID(int id); // Llama solo a uno, por su id
	    
	    void addEjercicio(Ejercicio ejercicio); // Añade ejercicios a la bdd
	    
	    void updateEjercicio(Ejercicio ejercicio); // Actualiza los ejercicios de la bdd
	    
	    void deleteEjercicio(int id); // Elimina ejercicios de la bdd
	 
	 
}
  