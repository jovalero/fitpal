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
	public LinkedList<Rutina> getAllRutinas() {
		LinkedList<Rutina> rutinas= new LinkedList<Rutina>();
		
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM Rutina");
			ResultSet resultset= statement.executeQuery();
			
			while (resultset.next()) {
				Rutina rutina=new Rutina(resultset.getInt("ID_Rutina"),resultset.getString("Estado"),resultset.getString("Descripcion"),resultset.getString("Objetivo"));
				rutinas.add(rutina);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return rutinas;
	}

	@Override
	public Rutina getRutinaByid(int id) {
		Rutina rutina= null;
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM admistrador WHERE ID_Rutina=?");
			statement.setInt(1, id);
			
			ResultSet resultset= statement.executeQuery();
			if (resultset.next()) {
				 rutina=new Rutina (resultset.getInt("ID_Rutina"),resultset.getString("Estado"),resultset.getString("Descripcion"),resultset.getString("Objetivo"));
			}
		} catch (SQLException e) {
			  e.printStackTrace();
		}
		return rutina;
	}

	@Override
	public boolean addRutina(Rutina Rutina) {
		try {
			PreparedStatement statement= connection.prepareStatement("INSERT INTO rutina (ID_Rutina, Estado, Descripcion, Objetivo) VALUES (?, ?, ?, ?)");
			statement.setInt(1, Rutina.getIdRutina());
			statement.setString(2, Rutina.getEstado());
			statement.setString(3, Rutina.getDescripcion());
			statement.setString(4, Rutina.getObjetivo());
			
			
			int rowsinsert= statement.executeUpdate();
			if (rowsinsert>0) {
				JOptionPane.showMessageDialog(null, "Rutina  creada");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo añadir la rutina");
		}
		return false;
		
	}

	@Override
	public boolean updateRutina(Rutina rutina) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE rutina SET ID_Rutina = ?, Estado = ?, Descripcion = ?, Objetivo WHERE id = ?");
			statement.setInt(1, rutina.getIdRutina());
			statement.setString(2, rutina.getEstado());
			statement.setString(3, rutina.getDescripcion());
			statement.setString(4,rutina.getObjetivo());
			
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
		
	}

    @Override
    public void deleteRutina(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM rutina WHERE ID_Rutina = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Rutina eliminada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la rutina");
        }
    }
    public List<Rutina> getallRutinabySucursal(int Sucursal) {

	       List<Rutina> RutinaList = new LinkedList<>();
	        
	        try {
	PreparedStatement statement = connection.prepareStatement("SELECT * FROM areas WHERE ID_Sucursal =? " );
	statement.setInt(1, Sucursal);
	ResultSet resultSet = statement.executeQuery() ;

		while (resultSet.next()) {
			Rutina rutina = new Rutina(
     resultSet.getInt("ID_Rutina"),
     resultSet.getString("Estado"),
     resultSet.getString("Descripcion"),
     resultSet.getString("Objetivo")
					);
			RutinaList.add(rutina);
		}
		}
	 catch (SQLException e) {
	JOptionPane.showMessageDialog(null, "ERROR PARCE");
	}
	         
	       
	  return RutinaList;
	    
		
	}
	public boolean RegistrarRutina(String estado, String descripcion, String objetivo) {
		// TODO Auto-generated method stub
		return false;
	}
    
    
}

