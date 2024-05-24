package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Date;

import interfaces.AdminRepository;
import modelo.Admin;
import modelo.Cliente;

public class AdminControlador implements AdminRepository {
    private final Connection connection;

    public AdminControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

	@Override
	public LinkedList<Admin> getAllAdmin() {
		LinkedList<Admin> admins= new LinkedList<Admin>();
		
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM administrador");
			ResultSet resultset= statement.executeQuery();
			
			while (resultset.next()) {
				Admin admin=new Admin(resultset.getString("Nombre"),resultset.getString("Apellido"),resultset.getInt("Telefono"),resultset.getInt("ID_Sucursal"),resultset.getInt("DNI"),resultset.getInt("ID_Administrador"),resultset.getString("Contrasenia"));
				admins.add(admin);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return admins;
	}

	@Override
	public Admin getAdminByid(int id) {
		Admin admin= null;
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM admistrador WHERE ID_Administrador=?");
			statement.setInt(1, id);
			
			ResultSet resultset= statement.executeQuery();
			if (resultset.next()) {
				 admin=new Admin(resultset.getString("Nombre"),resultset.getString("Apellido"),resultset.getInt("Telefono"),resultset.getInt("ID_Sucursal"),resultset.getInt("DNI"),resultset.getInt("ID_Administrador"),resultset.getString("Contrasenia"));
			}
		} catch (SQLException e) {
			  e.printStackTrace();
		}
		return admin;
	}

	@Override
	public void addAdmin(Admin Admin) {
		try {
			PreparedStatement statement= connection.prepareStatement("INSERT INTO administrador (ID_Admnistrador, ID_Sucursal, Telefono, Apellido, DNI, Contrasenia, Nombre) VALUES (?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, Admin.getId_admin());
			statement.setInt(2, Admin.getId_sucursal());
			statement.setInt(3, Admin.getTelefono());
			statement.setString(4, Admin.getApellido());
			statement.setInt(5,Admin.getDNI());
			statement.setString(6, Admin.getContrasena());
			statement.setString(7, Admin.getNombre());
			
			int rowsinsert= statement.executeUpdate();
			if (rowsinsert>0) {
				JOptionPane.showMessageDialog(null, "Admin creado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo añadir el admin");
		}
		
	}

	@Override
	public void updateAdmin(Admin Admin) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE administrador SET ID_Sucursal = ?, Telefono = ?, Apellido = ?, DNI = ?, Contrasenia = ?, Nombre = ? WHERE id = ?");
			statement.setInt(1, Admin.getId_sucursal());
			statement.setInt(2, Admin.getTelefono());
			statement.setString(3, Admin.getApellido());
			statement.setInt(4,Admin.getDNI());
			statement.setString(5, Admin.getContrasena());
			statement.setString(6, Admin.getNombre());
			statement.setInt(7, Admin.getId_admin());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteAdmin(int Admin) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from administrador where ID_Administrador= ? ");
			statement.setInt(1, Admin);
			int rowsDeleted= statement.executeUpdate();
			if (rowsDeleted>0) {
				System.out.println("Usuario elimiinado");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No  se pudo eliminar el usuario");
		}
		
	}

  
}
