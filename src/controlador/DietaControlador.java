package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.DietaRepository;
import modelo.Dieta;
import modelo.Dieta;

public class DietaControlador implements DietaRepository {
    private final Connection connection;

    public DietaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
	public Connection getConnection() {
		return connection;
	}

	@Override
	public LinkedList<Dieta> getAllDieta() {
		LinkedList<Dieta> dietas= new LinkedList<Dieta>();
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM Dieta");
			ResultSet resultset= statement.executeQuery();
			 
			while (resultset.next()) {
				Dieta Dieta=new Dieta(resultset.getInt("ID_Dieta"),resultset.getString("Nombre_Dieta"),resultset.getString("Descripcion_Dieta"));
				dietas.add(Dieta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return dietas;
		
	}

	@Override
	public Dieta getDietaById(int id) {
		Dieta Dieta=null;
		try {
			PreparedStatement statement  =connection.prepareStatement("SELECT * FROM Dieta WHERE ID_Dieta=?");
			statement.setInt(1, id);
			
			ResultSet resultset= statement.executeQuery();
			if (resultset.next()) {
				Dieta=new Dieta(resultset.getInt("ID_Dieta"),resultset.getString("Nombre_Dieta"),resultset.getString("Descripcion_Dieta"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Dieta;
	}

	@Override
	public void addDieta(Dieta Dieta) {
		try {
			PreparedStatement statement= connection.prepareStatement("INSERT TO Dieta (ID_Dieta, Nombre_Dieta, Descripcion_Dieta) VALUES (?, ?, ?)");
			statement.setInt(1, Dieta.getID_Dieta());
			statement.setString(2, Dieta.getNombre_Dieta());
			statement.setString(3,Dieta.getDescripcion_Dieta());
			
			int resultset = statement.executeUpdate();
			
			if (resultset > 0) {
				JOptionPane.showMessageDialog(null, "Dieta creada");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo crear la dieta");
		}
		
	}

	@Override
	public void updateDieta(Dieta Dieta) {
		try {
			PreparedStatement statement= connection.prepareStatement("UPDATE Dieta  SET Nombre_Dieta= ?, Descripcion_Dieta = ?  WHERE ID_Dieta= ?");
			statement.setString(1, Dieta.getNombre_Dieta());
			statement.setString(2, Dieta.getDescripcion_Dieta());
			statement.setInt(3, Dieta.getID_Dieta());
			
			int resultset = statement.executeUpdate();
			
			if (resultset>0) {
				 System.out.println("Dieta actualizada correctamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteDieta(int Dieta) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from Dieta where ID_Dieta= ? ");
			statement.setInt(1, Dieta);
			int rowsDeleted= statement.executeUpdate();
			if (rowsDeleted>0) {
				System.out.println("Dieta eliminado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No  se pudo eliminar la dieta");
		}
		
	}

}
