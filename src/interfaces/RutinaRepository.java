package interfaces;

import java.util.List;
import modelo.Rutina;

public interface RutinaRepository {
	
    List<Rutina> getAllRutinas();
    List<Rutina> getallRutinabySucursal(int Sucursal);
    
    Rutina getRutinaByid(int id);
    
    boolean addRutina(Rutina rutina);
    
    boolean updateRutina(Rutina rutina);
    
    void deleteRutina(int Rutina);

	
}

