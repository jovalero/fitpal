package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import interfaces.IncentivoRepository;
import modelo.Incentivo;

public class IncentivoControlador implements IncentivoRepository{
	private final Connection connection;

	  public IncentivoControlador() {
	        this.connection = DatabaseConnection.getInstance().getConnection();
	    
	  }
		public Connection getConnection() {
			return connection;
		}

	@Override
	public LinkedList<Incentivo> getAllIncentivo() {
		LinkedList<Incentivo> incentivos= new LinkedList<Incentivo>();
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM incentivos");
			ResultSet resultSet= statement.executeQuery();
			
		while (resultSet.next()) {
			Incentivo incentivo=new Incentivo (resultSet.getInt ("Costo"),resultSet.getString("Descripcion"),resultSet.getInt("ID_Incentivo"));
			incentivos.add(incentivo);
		}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return incentivos;
	}

	@Override
	public Incentivo getIncentivoByid(int id) {
		Incentivo incentivo = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Incentivo WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	incentivo= new Incentivo (resultSet.getInt ("Costo"),resultSet.getString("Descripcion"),resultSet.getInt("ID_Incentivo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	return incentivo;
}

	@Override
	public void addIncentivo(Incentivo Incentivo) {
		try {
            PreparedStatement statement = connection.prepareStatement
            		("INSERT INTO Incentivo (Costo, Descripcion, ID_Incentivo) "
            				+ "VALUES (?, ?, ?)");
          
            statement.setInt(1, Incentivo.getID_Incentivo());
            statement.setInt(2, Incentivo.getCosto());
            statement.setString(3, Incentivo.getDescripcion());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Usuario insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateIncentivo(Incentivo Incentivo) {
		try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Incentivo SET Costo= ?, Descripcion= ?, ID_Incentivo= ?  WHERE id = ?");
            
            statement.setInt(1, Incentivo.getID_Incentivo());
            statement.setInt(2, Incentivo.getCosto());
            statement.setString(3, Incentivo.getDescripcion());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteIncentivo(int Incentivo) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from Entrenador where ID_Incentivo= ? ");
			statement.setInt(1, Incentivo);
			int rowsDeleted= statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No  se pudo eliminar el Incentivo");
		}
	}
}
