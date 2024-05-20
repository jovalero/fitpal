package interfaces;

import java.util.LinkedList;

import modelo.Comida;

public interface ComidaRepository {
	LinkedList<Comida> getAllComida();
	
	Comida getNombre (String Nombre);
	void getDescripcion (String Descripcion);
	void getID_Comida (int ID_Comida);
	
}
