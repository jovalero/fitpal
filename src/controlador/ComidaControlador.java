package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import interfaces.ComidaRepository;
import modelo.Comida;

public class ComidaControlador implements ComidaRepository{
	public final Connection connection;
	
	public ComidaControlador() {
		this.connection = DatabaseConnection.getInstance().getConnection();
	}
	@Override
	public LinkedList<Comida> getAllComidas() {
		 LinkedList<Comida> comidas = new LinkedList<>();
		 try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comida");
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("ID_Comida");
	                String nombre = resultSet.getString("Nombre");
	                String descripcion = resultSet.getString("Descripcion");

	                Comida comida = new Comida(nombre, descripcion, id);
	                comidas.add(comida);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return comidas;
	    }

	@Override
	public Comida getComidaByID(int id) {
		 Comida comida = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM comida WHERE ID_Comida = ?");
	            statement.setInt(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                String nombre = resultSet.getString("Nombre");
	                String descripcion = resultSet.getString("Descripcion");

	                comida = new Comida(nombre, descripcion, id);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return comida;
	    }

	@Override
	public void addComida(Comida comida) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO comida (Nombre, Descripcion) VALUES (?, ?)");
	            statement.setString(1, comida.getNombre());
	            statement.setString(2, comida.getDescripcion());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Comida insertada exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public void updateComida(Comida comida) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("UPDATE comida SET Nombre = ?, Descripcion = ? WHERE ID_Comida = ?");
	            statement.setString(1, comida.getNombre());
	            statement.setString(2, comida.getDescripcion());
	            statement.setInt(3, comida.getID_Comida());

	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Comida actualizada exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public void deleteComida(int id) {
		  try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM comida WHERE ID_Comida = ?");
	            statement.setInt(1, id);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Comida eliminada exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
}
