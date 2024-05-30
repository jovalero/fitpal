package interfaces;

import java.util.List;
import modelo.Dieta;

public interface DietaRepository {
    List<Dieta> getAllDietas();
    
    Dieta getDietaById(int id);
    
    void addDieta(Dieta dieta);
    
    void updateDieta(Dieta dieta);
    
    void deleteDieta(int id);
}
