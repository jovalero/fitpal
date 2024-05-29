package interfaces;

import java.util.List;
import modelo.EjercicioRutina;

public interface EjercicioRutinaRepository {
	
    List<EjercicioRutina> getAllEjercicioRutina();
    
    EjercicioRutina getEjercicioRutinaById(int id);
    
    void addEjercicioRutina(EjercicioRutina ejercicioRutina);
    
    void updateEjercicioRutina(EjercicioRutina ejercicioRutina);
    
    void deleteEjercicioRutina(int id);
    
}
