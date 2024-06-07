package interfaces;

import java.util.LinkedList;

import modelo.Admin;

public interface AdminRepository {
	
	LinkedList<Admin> getAllAdmin(); // llama a todos los Admis de la bdd
    
	LinkedList<Admin> getAllAdminBySucursal(int sucursal);
	
    Admin getAdminByid(int id); //llama solo a uno, por su id
    
    void addAdmin(Admin Admin); //añade usuarios a la bdd
    
    void updateAdmin(Admin Admin); //actualiza los usuarios de la bdd
    
    void deleteAdmin(int Admin); //eliminar usuarios de la bdd
}
