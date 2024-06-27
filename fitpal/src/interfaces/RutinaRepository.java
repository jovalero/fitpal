package interfaces;

import java.util.List;
import modelo.Rutina;

public interface RutinaRepository {
	
    List<Rutina> getAllRutinas();
    List<Rutina> getallRutinabySucursal(int Sucursal);
    
    Rutina getRutinaById(int id);
    
    void addRutina(Rutina rutina);
    
    void updateRutina(Rutina rutina);
    
    void deleteRutina(int id);
}

