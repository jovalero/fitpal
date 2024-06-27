package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import interfaces.RutinaRepository;
import modelo.Rutina;

public class RutinaControlador implements RutinaRepository {

    private final Connection connection;

    public RutinaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public List<Rutina> getAllRutinas() {
        List<Rutina> rutinas = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rutina");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Rutina rutina = new Rutina(
                    resultSet.getInt("ID_Rutina"),
                    resultSet.getString("Estado"),
                    resultSet.getString("Descripcion"),
                    resultSet.getString("Objetivo")
                );
                rutinas.add(rutina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las rutinas");
        }
        return rutinas;
    }

    @Override
    public Rutina getRutinaById(int id) {
        Rutina rutina = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rutina WHERE ID_Rutina=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rutina = new Rutina(
                    resultSet.getInt("ID_Rutina"),
                    resultSet.getString("Estado"),
                    resultSet.getString("Descripcion"),
                    resultSet.getString("Objetivo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rutina;
    }

    @Override
    public void addRutina(Rutina rutina) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO rutina (Estado, Descripcion, Objetivo) VALUES (?, ?, ?)");
            statement.setString(1, rutina.getEstado());
            statement.setString(2, rutina.getDescripcion());
            statement.setString(3, rutina.getObjetivo());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Rutina creada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir la rutina");
        }
    }

    @Override
    public void updateRutina(Rutina rutina) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE rutina SET Estado = ?, Descripcion = ?, Objetivo = ? WHERE ID_Rutina = ?");
            statement.setString(1, rutina.getEstado());
            statement.setString(2, rutina.getDescripcion());
            statement.setString(3, rutina.getObjetivo());
            statement.setInt(4, rutina.getIdRutina());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Rutina actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo actualizar la rutina");
        }
    }

    @Override
    public void deleteRutina(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM rutina WHERE ID_Rutina = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Rutina eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la rutina");
        }
    }

	

	
	
}

