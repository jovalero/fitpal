package interfaces;

import java.util.LinkedList;
import java.util.List;
import modelo.Progreso;

public interface ProgresoRepository {
	
    LinkedList<Progreso> getAllProgresos();
    
    Progreso getProgresoById(int id);
    
    void addProgreso(Progreso progreso);
    
    void updateProgreso(Progreso progreso);
    
    void deleteProgreso(int id);
}

