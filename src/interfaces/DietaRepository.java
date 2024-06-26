package interfaces;

import java.util.LinkedList;

import modelo.Dieta;

public interface DietaRepository {
	
	LinkedList<Dieta> getAllDieta(); 
    
	Dieta getDietaById(int id); 
    
    void addDieta(Dieta Dieta); 
    
    void updateDieta(Dieta Dieta); 
    
    void deleteDieta(int Dieta); 
    
}
