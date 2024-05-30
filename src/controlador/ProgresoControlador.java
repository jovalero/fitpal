package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.ProgresoRepository;
import modelo.Progreso;

public class ProgresoControlador implements ProgresoRepository {
    private final Connection connection;

    public ProgresoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
	public Connection getConnection() {
		return connection;
	}

    @Override
    public List<Progreso> getAllProgresos() {
        List<Progreso> progresos = new LinkedList<>();
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM progreso");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Progreso progreso = new Progreso(
                    resultSet.getInt("ID_Progreso"),
                    resultSet.getInt("ID_Cliente"),
                    resultSet.getDate("Fecha").toLocalDate(),
                    resultSet.getString("Imagen"),
                    resultSet.getDouble("Peso")
                );
                progresos.add(progreso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar los progresos");
        }
        return progresos;
    }

    @Override
    public Progreso getProgresoById(int id) {
        Progreso progreso = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM progreso WHERE ID_Progreso = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                progreso = new Progreso(
                    resultSet.getInt("ID_Progreso"),
                    resultSet.getInt("ID_Cliente"),
                    resultSet.getDate("Fecha").toLocalDate(),
                    resultSet.getString("Imagen"),
                    resultSet.getDouble("Peso")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return progreso;
    }

    @Override
    public void addProgreso(Progreso progreso) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO progreso (ID_Progreso, ID_Cliente, Fecha, Imagen, Peso) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, progreso.getIdProgreso());
            statement.setInt(2, progreso.getIdCliente());
            statement.setDate(3, Date.valueOf(progreso.getFecha()));
            statement.setString(4, progreso.getImagen());
            statement.setDouble(5, progreso.getPeso());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Progreso creado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir el progreso");
        }
    }

    @Override
    public void updateProgreso(Progreso progreso) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE progreso SET ID_Cliente = ?, Fecha = ?, Imagen = ?, Peso = ? WHERE ID_Progreso = ?");
            statement.setInt(1, progreso.getIdCliente());
            statement.setDate(2, Date.valueOf(progreso.getFecha()));
            statement.setString(3, progreso.getImagen());
            statement.setDouble(4, progreso.getPeso());
            statement.setInt(5, progreso.getIdProgreso());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Progreso actualizado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el progreso");
        }
    }

    @Override
    public void deleteProgreso(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM progreso WHERE ID_Progreso = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Progreso eliminado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el progreso");
        }
    }
}
