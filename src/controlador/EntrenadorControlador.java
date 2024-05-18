package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.EntrenadorRepository;

import modelo.Admin;
import modelo.Cliente;
import modelo.Entrenador;

public class EntrenadorControlador implements EntrenadorRepository {
	private static final Entrenador Entrenador = null;

import modelo.Entrenador;

public class EntrenadorControlador implements EntrenadorRepository {
	 
	private final Connection connection;

	  public EntrenadorControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    
	  }

	@Override
	public LinkedList<Entrenador> getAllEntrenadores() {
		LinkedList<Entrenador> entrenadores= new LinkedList<Entrenador>();
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM entrenador");
			ResultSet resultSet= statement.executeQuery();
			
		while (resultSet.next()) {
			Entrenador entrenador=new Entrenador(resultSet.getString("Nombre"),resultSet.getString("Apellido"),resultSet.getInt("Telefono"),
					resultSet.getInt("ID_SURCUSAL"),resultSet.getInt("DNI"),resultSet.getInt("ID"),resultSet.getString("email"),resultSet.getString("Contrasenia"),resultSet.getInt("Num_Entrenados"));
			entrenadores.add(entrenador);
		}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return entrenadores;
	}

	@Override
	public Entrenador getEntrenadorByid(int id) {
		Entrenador entrenador = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrenador WHERE id = ?");
	            statement.setInt(1, id);
	            
	            ResultSet resultSet = statement.executeQuery();
	            
	            if (resultSet.next()) {
	            	entrenador= new Entrenador(resultSet.getString("Nombre"),resultSet.getString("Apellido"),resultSet.getInt("Telefono"),
	    					resultSet.getInt("ID_SURCUSAL"),resultSet.getInt("DNI"),resultSet.getInt("ID"),resultSet.getString("email"),resultSet.getString("Contrasenia"),resultSet.getInt("Num_Entrenados"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return entrenador;
	}

	@Override
	public void addEntrenador(Entrenador entrenador) {
		try {
            PreparedStatement statement = connection.prepareStatement
            		("INSERT INTO entrenador (ID_Entrenador, ID_sucursal, DNI, Contrasenia, Telefono, Correo_electronico, Apellido, Nombre ) "
            				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
          
            statement.setInt(1, entrenador.getId_entrenador());
            statement.setInt(2, entrenador.getId_sucursal());
            statement.setInt(3, entrenador.getDNI());
            statement.setString(4,entrenador.getContrasena());
            statement.setInt(5, entrenador.getTelefono());
            statement.setString(6,entrenador.getUsuario());
            statement.setString(7,entrenador.getNombre());
            statement.setString(8,entrenador.getApellido());
            statement.setString(6,entrenador.getNombre());
            statement.setString(7,entrenador.getApellido());
            statement.setString(8,entrenador.getUsuario());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
		
	}

	@Override
	public void updateEntrenador(Entrenador Entrenador) {
		try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Entrenador SET ID_sucursal= ? ID_Entrenador= ?, DNI= ?, Contrasenia= ?, Telefono= ?, Correo_electronico= ?, Apellido= ?, Nombre= ?  WHERE id = ?");
            
            statement.setString(1,Entrenador.getNombre());
            statement.setString(2,Entrenador.getApellido());
            statement.setString(3,Entrenador.getContrasena());
            statement.setInt(4,Entrenador.getTelefono());     
            statement.setInt(5,Entrenador.getId_entrenador());
            statement.setInt(6, Entrenador.getDNI());
            statement.setInt(7, Entrenador.getId_sucursal());
            statement.setString(8,Entrenador.getUsuario());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteEntrenador(int Entrenador) {
			try {
			PreparedStatement statement = connection.prepareStatement("DELETE from Entrenador where ID_entrenador= ? ");
			statement.setInt(1, Entrenador);
			int rowsDeleted= statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No  se pudo eliminar el Entrenador");
		}
		
	}
		
	}

}
