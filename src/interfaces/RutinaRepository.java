package interfaces;

import java.util.List;
import modelo.Rutina;

public interface RutinaRepository {
	
    List<Rutina> getAllRutinas();
    
    Rutina getRutinaByid(int id);
    
    void addRutina(Rutina rutina);
    
    void updateRutina(Rutina rutina);
    
    void deleteRutina(int Rutina);

	
}

