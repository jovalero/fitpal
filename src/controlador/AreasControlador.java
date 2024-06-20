
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.AreasRepository;
import modelo.Areas;

public class AreasControlador implements AreasRepository {
    private final Connection connection;

    public AreasControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public List<Areas> getAllAreas() {
       List<Areas> areaList = new LinkedList<>();
        String query = "SELECT * FROM areas"; 

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Areas area = new Areas(
                    resultSet.getInt("ID_Area"),
                    resultSet.getString("Nombre"),
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getString("Ubicacion")
                );
                areaList.add(area);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las áreas");
        }
        return areaList;
    }

    @Override
    public Areas getAreaById(int id) {
        Areas area = null;
        String query = "SELECT * FROM areas WHERE ID_Area = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    area = new Areas(
                        resultSet.getInt("ID_Area"),
                        resultSet.getString("Nombre"),
                        resultSet.getInt("ID_Sucursal"),
                        resultSet.getString("Ubicacion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return area;
    }

    @Override
    public void addArea(Areas area) {
        String query = "INSERT INTO areas (Nombre, ID_Sucursal, Ubicacion) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, area.getNombre());
            statement.setInt(2, area.getIdSucursal());
            statement.setString(3, area.getUbicacion());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Área creada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir el área");
        }
    }

    @Override
    public boolean updateArea(Areas area) {
        String query = "UPDATE areas SET Nombre = ?, ID_Sucursal = ?, Ubicacion = ? WHERE ID_Area = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, area.getNombre());
            statement.setInt(2, area.getIdSucursal());
            statement.setString(3, area.getUbicacion());
            statement.setInt(4, area.getIdArea());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Área actualizada");
                return true;  // Devuelve verdadero si se actualizó al menos una fila
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el área a actualizar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el área");
        }
        return false;  // Devuelve falso si ocurrió una excepción o no se actualizaron filas
    }


    @Override
    public void deleteArea(int id) {
        String query = "DELETE FROM areas WHERE ID_Area = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Área eliminada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el área");
        }
    }

	@Override
	public List<Areas> getallAreasbySucursal(int Sucursal) {

	       List<Areas> areaList = new LinkedList<>();
	        
	        try {
	PreparedStatement statement = connection.prepareStatement("SELECT * FROM areas WHERE ID_Sucursal =? " );
	statement.setInt(1, Sucursal);
	ResultSet resultSet = statement.executeQuery() ;

		while (resultSet.next()) {
			Areas area = new Areas(
        resultSet.getInt("ID_Area"),
        resultSet.getString("Nombre"),
        resultSet.getInt("ID_Sucursal"),
        resultSet.getString("Ubicacion")
					);
			areaList.add(area);
		}
		}
	 catch (SQLException e) {
	JOptionPane.showMessageDialog(null, "ERROR PARCE");
	}
	         
	       
	  return areaList;
	    
		
	}
}

