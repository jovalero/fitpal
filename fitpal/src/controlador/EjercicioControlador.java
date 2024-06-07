package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import interfaces.EjercicioRepository;
import modelo.Ejercicio;

public class EjercicioControlador implements EjercicioRepository{
	 private final Connection connection;
	 
	 public EjercicioControlador() {
		 this.connection = DatabaseConnection.getInstance().getConnection();
	 }
		public Connection getConnection() {
			return connection;
		}

	@Override
	public LinkedList<Ejercicio> getAllEjercicio() {
		 LinkedList<Ejercicio> ejercicios = new LinkedList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio");
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                int id = resultSet.getInt("ID_Ejercicio");
	                String nombre = resultSet.getString("Nombre");
	                String maquina = resultSet.getString("Maquina");
	                String musculo = resultSet.getString("Musculo");
	                String descripcion = resultSet.getString("Descripcion");
	                String video = resultSet.getString("Video");
	                int idArea = resultSet.getInt("ID_Area");

	                Ejercicio ejercicio = new Ejercicio(id, nombre, maquina, musculo, descripcion, video, idArea);
	                ejercicios.add(ejercicio);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ejercicios;
	    }
	

	@Override
	public Ejercicio getEjercicioByID(int id) {
		 Ejercicio ejercicio = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio WHERE ID_Ejercicio = ?");
	            statement.setInt(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                String nombre = resultSet.getString("Nombre");
	                String maquina = resultSet.getString("Maquina");
	                String musculo = resultSet.getString("Musculo");
	                String descripcion = resultSet.getString("Descripcion");
	                String video = resultSet.getString("Video");
	                int idArea = resultSet.getInt("ID_Area");

	                ejercicio = new Ejercicio(id, nombre, maquina, musculo, descripcion, video, idArea);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ejercicio;
	    }

	@Override
	public void addEjercicio(Ejercicio ejercicio) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO ejercicio (Nombre, Maquina, Musculo, Descripcion, Video, ID_Area) VALUES (?, ?, ?, ?, ?, ?)");
	            statement.setString(1, ejercicio.getNombre());
	            statement.setString(2, ejercicio.getMaquina());
	            statement.setString(3, ejercicio.getMusculo());
	            statement.setString(4, ejercicio.getDescripcion());
	            statement.setString(5, ejercicio.getVideo());
	            statement.setInt(6, ejercicio.getID_Area());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Ejercicio insertado exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	@Override
	public void updateEjercicio(Ejercicio ejercicio) {
		try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ejercicio SET Nombre = ?, Maquina = ?, Musculo = ?, Descripcion = ?, Video = ?, ID_Area = ? WHERE ID_Ejercicio = ?");
            statement.setString(1, ejercicio.getNombre());
            statement.setString(2, ejercicio.getMaquina());
            statement.setString(3, ejercicio.getMusculo());
            statement.setString(4, ejercicio.getDescripcion());
            statement.setString(5, ejercicio.getVideo());
            statement.setInt(6, ejercicio.getID_Area());
            statement.setInt(7, ejercicio.getID_Ejercicio());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Ejercicio actualizado exitosamente");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void deleteEjercicio(int id) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM ejercicio WHERE ID_Ejercicio = ?");
	            statement.setInt(1, id);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Ejercicio eliminado exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}

	}
