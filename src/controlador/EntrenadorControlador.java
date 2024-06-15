package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.EntrenadorRepository;
import modelo.Entrenador;

public class EntrenadorControlador implements EntrenadorRepository {
    
    private final Connection connection;

    public EntrenadorControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public LinkedList<Entrenador> getAllEntrenadoresBySucursal(int sucursal) {
        LinkedList<Entrenador> entrenadores = new LinkedList<Entrenador>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrenador WHERE ID_Sucursal = ?");
            statement.setInt(1, sucursal);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Entrenador entrenador = new Entrenador(
                    resultSet.getString("Nombre"),
                    resultSet.getString("Apellido"),
                    resultSet.getInt("Telefono"),
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getInt("DNI"),
                    resultSet.getInt("ID_Entrenador"),
                    resultSet.getString("email"),
                    resultSet.getString("Contrasenia"),
                    resultSet.getInt("Num_Entrenados")
                );
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar los entrenadores");
        }
        return entrenadores;
    }

    @Override
    public Entrenador getEntrenadorByid(int id) {
        Entrenador entrenador = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrenador WHERE ID_Entrenador = ?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                entrenador = new Entrenador(
                    resultSet.getString("Nombre"),
                    resultSet.getString("Apellido"),
                    resultSet.getInt("Telefono"),
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getInt("DNI"),
                    resultSet.getInt("ID_Entrenador"),
                    resultSet.getString("email"),
                    resultSet.getString("Contrasenia"),
                    resultSet.getInt("Num_Entrenados")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al obtener el entrenador");
        }
        return entrenador;
    }

    @Override
    public void addEntrenador(Entrenador entrenador) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO entrenador (ID_Sucursal, DNI, Contrasenia, Telefono, email, Apellido, Nombre, Num_Entrenados) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setInt(1, entrenador.getId_sucursal());
            statement.setInt(2, entrenador.getDNI());
            statement.setString(3, entrenador.getContrasena());
            statement.setInt(4, entrenador.getTelefono());
            statement.setString(5, entrenador.getEmail());
            statement.setString(6, entrenador.getApellido());
            statement.setString(7, entrenador.getNombre());
            statement.setInt(8, 0); // Inicialmente, Num_Entrenados es 0

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Entrenador insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al insertar el entrenador");
        }
    }

    @Override
    public void updateEntrenador(Entrenador entrenador) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE entrenador SET ID_Sucursal = ?, DNI = ?, Contrasenia = ?, Telefono = ?, email = ?, Apellido = ?, Nombre = ?, Num_Entrenados = ? WHERE ID_Entrenador = ?"
            );

            statement.setInt(1, entrenador.getId_sucursal());
            statement.setInt(2, entrenador.getDNI());
            statement.setString(3, entrenador.getContrasena());
            statement.setInt(4, entrenador.getTelefono());
            statement.setString(5, entrenador.getEmail());
            statement.setString(6, entrenador.getApellido());
            statement.setString(7, entrenador.getNombre());
            statement.setInt(8, entrenador.getNumentrenados()); // Aquí se incluye el campo Num_Entrenados
            statement.setInt(9, entrenador.getId_entrenador());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Entrenador actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al actualizar el entrenador");
        }
    }

    @Override
    public void deleteEntrenador(int entrenadorId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM entrenador WHERE ID_Entrenador = ?");
            statement.setInt(1, entrenadorId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Entrenador eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el entrenador");
        }
    }

    @Override
    public LinkedList<Entrenador> getAllEntrenadores() {
        LinkedList<Entrenador> entrenadores = new LinkedList<Entrenador>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM entrenador");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Entrenador entrenador = new Entrenador(
                    resultSet.getString("Nombre"),
                    resultSet.getString("Apellido"),
                    resultSet.getInt("Telefono"),
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getInt("DNI"),
                    resultSet.getInt("ID_Entrenador"),
                    resultSet.getString("email"),
                    resultSet.getString("Contrasenia"),
                    resultSet.getInt("Num_Entrenados")
                );
                entrenadores.add(entrenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar los entrenadores");
        }
        return entrenadores;
    }
}



