package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.DietaComidaRepository;
import modelo.DietaComida;

public class DietaComidaControlador implements DietaComidaRepository {
    private final Connection connection;

    public DietaComidaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
	public Connection getConnection() {
		return connection;
	}


    @Override
    public List<DietaComida> getAllDietaComida() {
        List<DietaComida> dietaComidaList = new LinkedList<>();
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dieta_comida");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                DietaComida dietaComida = new DietaComida(
                    resultSet.getInt("ID_Dieta_Comida"),
                    resultSet.getInt("ID_Dieta"),
                    resultSet.getInt("ID_Comida"),
                    resultSet.getDouble("Cantidad")
                );
                dietaComidaList.add(dietaComida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las dietas-comidas");
        }
        return dietaComidaList;
    }

    @Override
    public DietaComida getDietaComidaById(int id) {
        DietaComida dietaComida = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dieta_comida WHERE ID_Dieta_Comida = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                dietaComida = new DietaComida(
                    resultSet.getInt("ID_Dieta_Comida"),
                    resultSet.getInt("ID_Dieta"),
                    resultSet.getInt("ID_Comida"),
                    resultSet.getDouble("Cantidad")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dietaComida;
    }

    @Override
    public void addDietaComida(DietaComida dietaComida) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dieta_comida (ID_Dieta_Comida, ID_Dieta, ID_Comida, Cantidad) VALUES (?, ?, ?, ?)");
            statement.setInt(1, dietaComida.getIdDietaComida());
            statement.setInt(2, dietaComida.getIdDieta());
            statement.setInt(3, dietaComida.getIdComida());
            statement.setDouble(4, dietaComida.getCantidad());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "DietaComida creada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir la dieta-comida");
        }
    }

    @Override
    public void updateDietaComida(DietaComida dietaComida) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE dieta_comida SET ID_Dieta = ?, ID_Comida = ?, Cantidad = ? WHERE ID_Dieta_Comida = ?");
            statement.setInt(1, dietaComida.getIdDieta());
            statement.setInt(2, dietaComida.getIdComida());
            statement.setDouble(3, dietaComida.getCantidad());
            statement.setInt(4, dietaComida.getIdDietaComida());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "DietaComida actualizada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la dieta-comida");
        }
    }

    @Override
    public void deleteDietaComida(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM dieta_comida WHERE ID_Dieta_Comida = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "DietaComida eliminada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la dieta-comida");
        }
    }
}
