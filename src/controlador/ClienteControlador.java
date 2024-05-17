
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Date;

import interfaces.UserRepository;
import modelo.Cliente;

public class ClienteControlador implements UserRepository {
    private final Connection connection;

    public ClienteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

	@Override
	public LinkedList<Cliente> getAllClientes() {
		LinkedList<Cliente> users = new LinkedList<Cliente>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente ");
            ResultSet resultSet = statement.executeQuery();
       
            while (resultSet.next()) {
            	Cliente user = new Cliente(resultSet.getString("Nombre"),resultSet.getString("Apellido"),resultSet.getInt("Telefono"),resultSet.getInt("ID_sucursal"),resultSet.getInt("DNI"),resultSet.getInt("ID_cliente"),resultSet.getString("Correo_electronico"),resultSet.getString("Contrasenia"),resultSet.getString("Objetivo"),resultSet.getDouble("Peso"),resultSet.getDouble("Altura"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
	}

	@Override
	public Cliente getClienteByDNI(int DNI) {
	       Cliente user = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE DNI = ?");
	            statement.setInt(1, DNI);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	            	user = new Cliente(resultSet.getString("Nombre"),resultSet.getString("Apellido"),resultSet.getInt("Telefono"),resultSet.getInt("ID_sucursal"),resultSet.getInt("DNI"),resultSet.getInt("ID_cliente"),resultSet.getString("Correo_electronico"),resultSet.getString("Contrasenia"),resultSet.getString("Objetivo"),resultSet.getDouble("Peso"),resultSet.getDouble("Altura"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return user;
	}

	@Override
	public void addCliente(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cliente (ID_cliente, ID_Entrenador, ID_Dieta, ID_sucursal, DNI, Objetivooo, Puntos, Estado_sus, Peso, Altura, Contrasenia, Fecha_venc_sus, Telefono, Correo_electronico, Apellido, Nombre ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            statement.setInt(1, cliente.getId_cliente());
            statement.setInt(2, cliente.getId_entrenador());
            statement.setInt(3, cliente.getId_sucursal());
            statement.setInt(4, cliente.getId_dieta());
            statement.setInt(5, cliente.getDNI());
            statement.setString(6, cliente.getObjetivo());
            statement.setInt(7, cliente.getPuntos());
            statement.setString(8, cliente.getEstado_sus());
            statement.setDouble(9,cliente.getPeso());
            statement.setDouble(10,cliente.getAltura());
            statement.setString(11,cliente.getContrasena());
            statement.setDate(12,cliente.getFechavenc());
            statement.setInt(13, cliente.getTelefono());
            statement.setString(14,cliente.getUsuario());
            statement.setString(15,cliente.getNombre());
            statement.setString(16,cliente.getApellido());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void updateCliente(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE cliente SET Nombre = ?, Apellido = ?, Objetivooo= ?, Contrasenia = ?, Telefono = ?, Correo_Electronico = ?,Fecha_venc_sus = ?, Puntos= ?,Estado_sus= ?,ID_Entrenador= ?,ID_Dieta= ? WHERE id = ?");
            
            statement.setString(1,cliente.getNombre());
            statement.setString(2,cliente.getApellido());
            statement.setString(3,cliente.getObjetivo());
            statement.setString(4,cliente.getContrasena());
            statement.setInt(5,cliente.getTelefono());
            statement.setString(6,cliente.getUsuario());
            statement.setDate(7, cliente.getFechavenc());
            statement.setInt(8,cliente.getPuntos());
            statement.setString(9,cliente.getEstado_sus());
            statement.setInt(10,cliente.getId_entrenador());
            statement.setInt(11,cliente.getId_dieta());
            
            
            statement.setInt(12,cliente.getId_cliente());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteCliente(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from cliente where ID_Cliente= ? ");
			statement.setInt(1, id);
			int rowsDeleted= statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No  se pudo eliminar el usuario");
		}

		
	}

  
}
