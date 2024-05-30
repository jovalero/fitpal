package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import interfaces.RutinaRepository;
import modelo.Rutina;


public class RutinaControlador implements RutinaRepository {
    private final Connection connection;
	private Object rutina;

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
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM rutinaistrador");
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
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM admistrador WHERE ID_rutinaistrador=?");
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
	public void addRutina(Rutina Rutina) {
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
		
	}

	@Override
	public void updateRutina(Rutina rutina) {
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