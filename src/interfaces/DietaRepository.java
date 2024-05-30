package interfaces;

import java.util.LinkedList;
import modelo.Dieta;

public interface DietaRepository {
	
	LinkedList<Dieta> getAllDieta(); 
	
	Dieta getDietaById(int id); 
    
    void addDieta(Dieta dieta);
    
    void updateDieta(Dieta dieta);
    
    void deleteDieta(int id);
}
