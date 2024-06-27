package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.IncentivoRepository;
import modelo.Incentivo;

public class IncentivoControlador implements IncentivoRepository {
    private final Connection connection;

    public IncentivoControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public LinkedList<Incentivo> getAllIncentivo() {
        LinkedList<Incentivo> incentivos = new LinkedList<Incentivo>();
        try {
            // Corregido el nombre de la tabla en la consulta
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Incentivo");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Incentivo incentivo = new Incentivo(
                    resultSet.getDouble("Costo"),
                    resultSet.getString("Descripcion"),
                    resultSet.getInt("ID_Incentivo")
                );
                incentivos.add(incentivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar los incentivos.");
        }
        return incentivos;
    }

    @Override
    public Incentivo getIncentivoByid(int id) {
        Incentivo incentivo = null;
        try {
            // Corregido el nombre de la columna en la consulta
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Incentivo WHERE ID_Incentivo = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                incentivo = new Incentivo(
                    resultSet.getDouble("Costo"),
                    resultSet.getString("Descripcion"),
                    resultSet.getInt("ID_Incentivo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incentivo;
    }

    @Override
    public void addIncentivo(Incentivo incentivo) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                // Corregido el orden de los parámetros en la consulta
                "INSERT INTO Incentivo (Costo, Descripcion, ID_Incentivo) VALUES (?, ?, ?)"
            );

            // Corregido el orden de los parámetros
            statement.setDouble(1, incentivo.getCosto());
            statement.setString(2, incentivo.getDescripcion());
            statement.setInt(3, incentivo.getID_Incentivo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Incentivo insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateIncentivo(Incentivo incentivo) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                // Corregido el orden de los parámetros en la consulta
                "UPDATE Incentivo SET Costo = ?, Descripcion = ? WHERE ID_Incentivo = ?"
            );

            // Corregido el orden de los parámetros
            statement.setDouble(1, incentivo.getCosto());
            statement.setString(2, incentivo.getDescripcion());
            statement.setInt(3, incentivo.getID_Incentivo());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Incentivo actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteIncentivo(int id) {
        try {
            // Corregido el nombre de la tabla en la consulta
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Incentivo WHERE ID_Incentivo = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Incentivo eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el incentivo.");
        }
    }
}

