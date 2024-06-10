package interfaces;


import java.util.LinkedList;

import modelo.Ejercicio;

public interface EjercicioRepository {

	LinkedList<Ejercicio> getAllEjercicio(); // llama a todos los Admis de la bdd
    
    Ejercicio getEjercicioByid(int id); //llama solo a uno, por su id
    
    void addEjercicio(Ejercicio Ejercicio); //añade usuarios a la bdd
    
    void updateEjercicio(Ejercicio Ejercicio); //actualiza los usuarios de la bdd
    
    void deleteEjercicio(int Ejercicio); //eliminar usuarios de la bdd

}
  