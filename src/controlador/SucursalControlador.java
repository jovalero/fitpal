package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import interfaces.SucursalRepository;
import modelo.Sucursal;

public class SucursalControlador implements SucursalRepository {
    private final Connection connection;

    public SucursalControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public LinkedList<Sucursal> getAllSucursales() {
        LinkedList<Sucursal> sucursales = new LinkedList<Sucursal>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sucursal");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sucursal sucursal = new Sucursal(
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getString("Direccion"),
                    resultSet.getString("Telefono"),
                    
                );
                sucursales.add(sucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las sucursales");
        }
        return sucursales;
    }

    @Override
    public Sucursal getSucursalById(int id) {
        Sucursal sucursal = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sucursal WHERE ID_Sucursal = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sucursal = new Sucursal(
                    resultSet.getInt("ID_Sucursal"),
                    resultSet.getString("Direccion"),
                    resultSet.getString("Telefono"),
                  
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursal;
    }

    @Override
    public void addSucursal(Sucursal sucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO sucursal (ID_Sucursal, Nombre, Direccion, Telefono) VALUES (?, ?, ?, ?)");
            statement.setInt(1, sucursal.getid_sucursal());
            statement.setString(3, sucursal.getDireccion());
            statement.setString(4, sucursal.getTelefono());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Sucursal creada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo añadir la sucursal");
        }
    }

    @Override
    public void updateSucursal(Sucursal sucursal) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE sucursal SET Nombre = ?, Direccion = ?, Telefono = ? WHERE ID_Sucursal = ?");
            statement.setString(1, sucursal.getid_sucursal());
            statement.setString(2, sucursal.getDireccion());
            statement.setString(3, sucursal.getTelefono());
           

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Sucursal actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
                
    @Override
    public void deleteSucursal(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM sucursal WHERE ID_Sucursal = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Sucursal eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la sucursal");
        }
        
        
     
    }

	@Override
	public LinkedList<Sucursal> getAllSucursal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sucursal getSucursalByID(int ID_Sucursal) {
		// TODO Auto-generated method stub
		return null;
	}
}
