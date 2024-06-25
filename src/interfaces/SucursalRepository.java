package interfaces;
import java.util.LinkedList;

import modelo.Sucursal;


public interface SucursalRepository {
 
LinkedList<Sucursal> getAllSucursal(); // llama a todos los Sucursals de la bdd
    
   Sucursal getSucursalByid(int id); //llama solo a uno, por su id
    
    void addSucursal(Sucursal Sucursal); //añade usuarios a la bdd
    
    void updateSucursal(Sucursal Sucursal); //actualiza los usuarios de la bdd
    
    void deleteSucursal(int Sucursal); //eliminar usuarios de la bdd
}
