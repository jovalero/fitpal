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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM incentivo");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Incentivo incentivo = new Incentivo(
                    resultSet.getInt("id_incentivo"),
                    resultSet.getString("recompensa"),
                    resultSet.getDouble("costo"),
                    resultSet.getString("dirigido")
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
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM incentivo WHERE id_incentivo = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                incentivo = new Incentivo(
                    resultSet.getInt("id_incentivo"),
                    resultSet.getString("recompensa"),
                    resultSet.getDouble("costo"),
                    resultSet.getString("dirigido")
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
                "INSERT INTO incentivo (recompensa, costo, dirigido) VALUES (?, ?, ?)"
            );

    
            statement.setString(1, incentivo.getRecompensa());
            statement.setDouble(2, incentivo.getCosto());
            statement.setString(3, incentivo.getDirigido());

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
                "UPDATE incentivo SET recompensa = ?, costo = ?, dirigido = ? WHERE id_incentivo = ?"
            );

            // Corregido el orden de los parámetros
            statement.setString(1, incentivo.getRecompensa());
            statement.setDouble(2, incentivo.getCosto());
            statement.setString(3, incentivo.getDirigido());
            statement.setInt(4, incentivo.getIdIncentivo());

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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM incentivo WHERE id_incentivo = ?");
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


