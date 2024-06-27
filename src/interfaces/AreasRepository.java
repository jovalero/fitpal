package interfaces;
import java.util.List;
import modelo.Areas;
public interface AreasRepository {
	


	    List<Areas> getAllAreas(); 
	    List<Areas> getallAreasbySucursal(int Sucursal);// Llama a todas las áreas de la BDD
	    
	    Areas getAreaById(int id); // Llama a un área por su ID
	    
	    void addArea(Areas area); // Añade un área a la BDD
	    
	    boolean updateArea(Areas area); // Actualiza un área de la BDD
	    
	    void deleteArea(int id); // Elimina un área de la BDD
	}


