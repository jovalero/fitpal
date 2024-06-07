package interfaces;

import java.util.List;
import modelo.DietaComida;

public interface DietaComidaRepository {
	
    List<DietaComida> getAllDietaComida();
    
    DietaComida getDietaComidaById(int id);
    
    void addDietaComida(DietaComida dietaComida);
    
    void updateDietaComida(DietaComida dietaComida);
    
    void deleteDietaComida(int id);
    
}
