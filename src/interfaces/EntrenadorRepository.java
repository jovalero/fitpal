package interfaces;

import java.util.LinkedList;

import modelo.Entrenador;

public interface EntrenadorRepository {
	
LinkedList<Entrenador> getAllEntrenadores(int sucursal); // llama a todos los Entrenadores de la bdd
    
    Entrenador getEntrenadorByid(int id); //llama solo a uno, por su id
    
    void addEntrenador(Entrenador cliente); //añade usuarios a la bdd
    
    void updateEntrenador(Entrenador Entrenador); //actualiza los usuarios de la bdd
    
    void deleteEntrenador(int Entrenador); //eliminar usuarios de la bdd
}

