package interfaces;

import java.util.LinkedList;
import modelo.Incentivo;

public interface IncentivoRepository {
LinkedList<Incentivo> getAllIncentivo(); // llama a todos los Entrenadores de la bdd
    
	Incentivo getIncentivoByid(int id); //llama solo a uno, por su id
    
    void addIncentivo(Incentivo cliente); //añade usuarios a la bdd
    
    void updateIncentivo(Incentivo Incentivo); //actualiza los usuarios de la bdd
    
    void deleteIncentivo(int Incentivo); //eliminar usuarios de la bdd
}


