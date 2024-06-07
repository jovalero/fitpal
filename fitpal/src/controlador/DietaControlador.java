package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.DietaRepository;
import modelo.Dieta;

public class DietaControlador implements DietaRepository {
    private final Connection connection;

    public DietaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public LinkedList<Dieta> getAllDietas() {
    	LinkedList<Dieta> dietaList = new LinkedList<>();
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dieta");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Dieta dieta = new Dieta(
                    resultSet.getInt("ID_Dieta"),
                    resultSet.getString("Nombre_Dieta"),
                    resultSet.getString("Descripcion_Dieta")
                );
                dietaList.add(dieta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las dietas");
        }
        return dietaList;
    }

    @Override
    public Dieta getDietaById(int id) {
        Dieta dieta = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM dieta WHERE ID_Dieta = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                dieta = new Dieta(
                    resultSet.getInt("ID_Dieta"),
                    resultSet.getString("Nombre_Dieta"),
                    resultSet.getString("Descripcion_Dieta")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dieta;
    }

    @Override
    public void addDieta(Dieta dieta) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO dieta (Nombre_Dieta, Descripcion_Dieta) VALUES (?, ?)");
            statement.setString(1, dieta.getNombreDieta());
            statement.setString(2, dieta.getDescripcionDieta());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Dieta creada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir la dieta");
        }
    }

    @Override
    public void updateDieta(Dieta dieta) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE dieta SET Nombre_Dieta = ?, Descripcion_Dieta = ? WHERE ID_Dieta = ?");
            statement.setString(1, dieta.getNombreDieta());
            statement.setString(2, dieta.getDescripcionDieta());
            statement.setInt(3, dieta.getIdDieta());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Dieta actualizada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la dieta");
        }
    }

    @Override
    public void deleteDieta(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM dieta WHERE ID_Dieta = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Dieta eliminada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la dieta");
        }
    }
}

