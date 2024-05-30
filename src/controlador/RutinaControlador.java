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
	public LinkedList<Rutina> getAllRutina() {
		LinkedList<Rutina> rutinas= new LinkedList<Rutina>();
		
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM rutinaistrador");
			ResultSet resultset= statement.executeQuery();
			
			while (resultset.next()) {
				Rutina Rutina=new Rutina(resultset.getString("ID_Rutina"),resultset.getString("Estado"),resultset.getInt("Descripcion"),resultset.getInt("Objetivo"));
				Rutina.add(rutina);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return rutina;
	}

	@Override
	public Rutina getRutinabyID(int Rutina) {
		Rutina Rutina1= null;
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM admistrador WHERE ID_rutinaistrador=?");
			statement.setInt(1, Rutina);
			
			ResultSet resultset= statement.executeQuery();
			if (resultset.next()) {
				 Rutina=new rutina(resultset.getString("ID_Rutina"),resultset.getString("Estado"),resultset.getInt("Descripcion"),resultset.getInt("Objetivo"));
			}
		} catch (SQLException e) {
			  e.printStackTrace();
		}
		return rutina;
	}

	@Override
	public void addRutina(Rutina Rutina) {
		try {
			PreparedStatement statement= connection.prepareStatement("INSERT INTO rutina (ID_Rutina, Estado, Descripcion, Objetivo) VALUES (?, ?, ?, ?)");
			statement.setInt(1, rutina.getId_Rutina());
			statement.setInt(2, Rutina.getEstado());
			statement.setInt(3, Rutina.getDescripcion());
			statement.setString(4, Rutina.getObjetivo());
			
			
			int rowsinsert= statement.executeUpdate();
			if (rowsinsert>0) {
				JOptionPane.showMessageDialog(null, "Rutina  creada");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo añadir la rutina");
		}
		
	}

	@Override
	public void updaterutina(rutina rutina) {
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
                rutinaList.add(rutina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al mostrar las rutinas");
        }
        return rutinaList;
    }

    @Override
    public Rutina getRutinaById(int id) {
        Rutina rutina = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rutina WHERE ID_Rutina = ?");
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
	public void deleteRutina(int Rutina) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from rutinaistrador where ID_rutinaistrador= ? ");
			statement.setInt(1, (int) rutina);
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
