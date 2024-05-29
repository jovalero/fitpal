package interfaces;

import java.util.LinkedList;

import modelo.Admin;
import modelo.Rutina;


public interface RutinaRepository {
LinkedList<Rutina> getAllRutina(); // llama a todos los Admis de la bdd
    
    Rutina getRutinabyID(int Rutina ); //llama solo a uno, por su id
    
    void addRutina(Rutina Rutina); //añade usuarios a la bdd
    
    void updateRutina(Rutina Rutina); //actualiza los usuarios de la bdd
    
    void deleteRutina(int Rutina); //eliminar usuarios de la bdd

	void updateAdmin(Admin Admin);

	void addRutina(Admin Admin);

	

	
}
