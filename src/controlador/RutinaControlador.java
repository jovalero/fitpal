package controlador;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

import interfaces.RutinaRepository;
import modelo.rutina;
import modelo.Rutina;
import modelo.Sucursal;

public class RutinaControlador implements RutinaRepository {
    private final Connection connection;
	private Object rutina;

    public RutinaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

	@Override
	public LinkedList<Rutina> getAllRutina() {
		LinkedList<Rutina> rutinas= new LinkedList<Rutina>();
		
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM rutinaistrador");
			ResultSet resultset= statement.executeQuery();
			
			while (resultset.next()) {
				Rutina Rutina=new rutina(resultset.getString("ID_Rutina"),resultset.getString("Estado"),resultset.getInt("Descripcion"),resultset.getInt("Objetivo"));
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
            PreparedStatement statement = connection.prepareStatement("UPDATE rutina SET ID_Rutina = ?, Estado = ?, Descripcion = ?, Objetivo WHERE id = ?");
			statement.setInt(1, rutina.getId_sucursal());
			statement.setInt(2, rutina.getTelefono());
			statement.setString(3, rutina.getApellido());
			statement.setInt(4,rutina.getDNI());
			
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


    
    

	@Override
	public LinkedList<Rutina> getAllRutina() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rutina getrutinaByRutina(int Rutina) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRutina(Rutina Rutina) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRutina(Rutina Rutina) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRutina(int Rutina) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRutina(rutina rutina) {
		// TODO Auto-generated method stub
		
	}
}