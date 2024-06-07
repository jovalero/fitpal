package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.EjercicioRutinaRepository;
import modelo.EjercicioRutina;

public class EjercicioRutinaControlador implements EjercicioRutinaRepository {
    private final Connection connection;

    public EjercicioRutinaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
	public Connection getConnection() {
		return connection;
	}

    @Override
    public List<EjercicioRutina> getAllEjercicioRutina() {
        List<EjercicioRutina> ejercicioRutinaList = new LinkedList<>();
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio_rutina");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                EjercicioRutina ejercicioRutina = new EjercicioRutina(
                    resultSet.getInt("ID_Ejercicio_Rutina"),
                    resultSet.getInt("ID_Ejercicio"),
                    resultSet.getInt("ID_Rutina"),
                    resultSet.getInt("Num_Series"),
                    resultSet.getInt("Repeticiones")
                );
                ejercicioRutinaList.add(ejercicioRutina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar los ejercicios de la rutina");
        }
        return ejercicioRutinaList;
    }

    @Override
    public EjercicioRutina getEjercicioRutinaById(int id) {
        EjercicioRutina ejercicioRutina = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio_rutina WHERE ID_Ejercicio_Rutina = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                ejercicioRutina = new EjercicioRutina(
                    resultSet.getInt("ID_Ejercicio_Rutina"),
                    resultSet.getInt("ID_Ejercicio"),
                    resultSet.getInt("ID_Rutina"),
                    resultSet.getInt("Num_Series"),
                    resultSet.getInt("Repeticiones")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejercicioRutina;
    }

    @Override
    public void addEjercicioRutina(EjercicioRutina ejercicioRutina) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ejercicio_rutina (ID_Ejercicio, ID_Rutina, Num_Series, Repeticiones) VALUES (?, ?, ?, ?)");
            statement.setInt(1, ejercicioRutina.getIdEjercicio());
            statement.setInt(2, ejercicioRutina.getIdRutina());
            statement.setInt(3, ejercicioRutina.getNumSeries());
            statement.setInt(4, ejercicioRutina.getRepeticiones());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "EjercicioRutina creado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir el ejercicio de la rutina");
        }
    }

    @Override
    public void updateEjercicioRutina(EjercicioRutina ejercicioRutina) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ejercicio_rutina SET ID_Ejercicio = ?, ID_Rutina = ?, Num_Series = ?, Repeticiones = ? WHERE ID_Ejercicio_Rutina = ?");
            statement.setInt(1, ejercicioRutina.getIdEjercicio());
            statement.setInt(2, ejercicioRutina.getIdRutina());
            statement.setInt(3, ejercicioRutina.getNumSeries());
            statement.setInt(4, ejercicioRutina.getRepeticiones());
            statement.setInt(5, ejercicioRutina.getIdEjercicioRutina());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "EjercicioRutina actualizado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el ejercicio de la rutina");
        }
    }

    @Override
    public void deleteEjercicioRutina(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ejercicio_rutina WHERE ID_Ejercicio_Rutina = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "EjercicioRutina eliminado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el ejercicio de la rutina");
        }
    }
}

