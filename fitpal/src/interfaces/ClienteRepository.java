package interfaces;

import java.util.LinkedList;
import java.util.List;

import modelo.Cliente;



public interface ClienteRepository {
	
	LinkedList<Cliente> getAllClientesBySucursal(int sucursal);

	LinkedList<Cliente> getAllClientes();
	
    Cliente getClienteByDNI(int DNI); //llama solo a uno, por su id
    
    void addCliente(Cliente cliente); //añade usuarios a la bdd
    
    void updateCliente(Cliente Cliente); //actualiza los usuarios de la bdd
    
    void deleteCliente(int Cliente); //eliminar usuarios de la bdd
}
