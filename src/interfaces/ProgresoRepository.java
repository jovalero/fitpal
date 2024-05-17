package interfaces;

import java.util.List;
import modelo.Progreso;

public interface ProgresoRepository {
	
    List<Progreso> getAllProgresos();
    
    Progreso getProgresoById(int id);
    
    void addProgreso(Progreso progreso);
    
    void updateProgreso(Progreso progreso);
    
    void deleteProgreso(int id);
}

