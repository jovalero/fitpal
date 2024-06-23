package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import interfaces.EjercicioRepository;
import modelo.Ejercicio;

public class EjercicioControlador implements EjercicioRepository {
    private final Connection connection;

    public EjercicioControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
        try {
            // Asegúrate de que la conexión no esté en modo auto-commit
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public LinkedList<Ejercicio> getAllEjercicio() {
        LinkedList<Ejercicio> ejercicios = new LinkedList<>();
        String query = "SELECT * FROM ejercicio";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejercicios;
    }

    @Override
    public Ejercicio getEjercicioByid(int id) {
        Ejercicio ejercicio = null;
        String query = "SELECT * FROM ejercicio WHERE ID_Ejercicio = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombre = resultSet.getString("Nombre");
                    String maquina = resultSet.getString("Maquina");
                    String musculo = resultSet.getString("Musculo");
                    String descripcion = resultSet.getString("Descripcion");
                    String video = resultSet.getString("Video");
                    int idArea = resultSet.getInt("ID_Area");

                    ejercicio = new Ejercicio(id, nombre, maquina, musculo, descripcion, video, idArea);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ejercicio;
    }

    @Override
    public void addEjercicio(Ejercicio ejercicio) {
        String query = "INSERT INTO ejercicio (Nombre, Maquina, Musculo, Descripcion, Video, ID_Area) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ejercicio.getNombre());
            statement.setString(2, ejercicio.getMaquina());
            statement.setString(3, ejercicio.getMusculo());
            statement.setString(4, ejercicio.getDescripcion());
            statement.setString(5, ejercicio.getVideo());
            statement.setInt(6, ejercicio.getID_Area());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Ejercicio insertado exitosamente");
                connection.commit(); // Confirmar la transacción
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    @Override
    public void updateEjercicio(Ejercicio ejercicio) {
        String query = "UPDATE ejercicio SET Nombre = ?, Maquina = ?, Musculo = ?, Descripcion = ?, Video = ?, ID_Area = ? WHERE ID_Ejercicio = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
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
                connection.commit(); // Confirmar la transacción
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    @Override
    public void deleteEjercicio(int id) {
        String query = "DELETE FROM ejercicio WHERE ID_Ejercicio = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Ejercicio eliminado exitosamente");
                connection.commit(); // Confirmar la transacción
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }
}
