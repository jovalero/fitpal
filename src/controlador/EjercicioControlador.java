package controlador;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import interfaces.EjercicioRepository;
import modelo.Ejercicio;

public class EjercicioControlador implements EjercicioRepository{
	 private final Connection connection;
	 
	 public EjercicioControlador() {
		 this.connection = DatabaseConnection.getInstance().getConnection();
	 }
		public Connection getConnection() {
			return connection;
		}

	@Override
	public LinkedList<Ejercicio> getAllEjercicio() {
		 LinkedList<Ejercicio> ejercicios = new LinkedList<>();
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio");
	            ResultSet resultSet = statement.executeQuery();

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
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ejercicios;
	    }
	

	@Override
	public Ejercicio getEjercicioByID(int id) {
		 Ejercicio ejercicio = null;
	        try {
	            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ejercicio WHERE ID_Ejercicio = ?");
	            statement.setInt(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                String nombre = resultSet.getString("Nombre");
	                String maquina = resultSet.getString("Maquina");
	                String musculo = resultSet.getString("Musculo");
	                String descripcion = resultSet.getString("Descripcion");
	                String video = resultSet.getString("Video");
	                int idArea = resultSet.getInt("ID_Area");

	                ejercicio = new Ejercicio(id, nombre, maquina, musculo, descripcion, video, idArea);
	            }
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return ejercicio;
	    }

	@Override
	public void addEjercicio(Ejercicio ejercicio) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO ejercicio (Nombre, Maquina, Musculo, Descripcion, Video, ID_Area) VALUES (?, ?, ?, ?, ?, ?)");
	            statement.setString(1, ejercicio.getNombre());
	            statement.setString(2, ejercicio.getMaquina());
	            statement.setString(3, ejercicio.getMusculo());
	            statement.setString(4, ejercicio.getDescripcion());
	            statement.setString(5, ejercicio.getVideo());
	            statement.setInt(6, ejercicio.getID_Area());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Ejercicio insertado exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	@Override
	public void updateEjercicio(Ejercicio ejercicio) {
		try {
            PreparedStatement statement = connection.prepareStatement("UPDATE ejercicio SET Nombre = ?, Maquina = ?, Musculo = ?, Descripcion = ?, Video = ?, ID_Area = ? WHERE ID_Ejercicio = ?");
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
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void deleteEjercicio(int id) {
		 try {
	            PreparedStatement statement = connection.prepareStatement("DELETE FROM ejercicio WHERE ID_Ejercicio = ?");
	            statement.setInt(1, id);

	            int rowsDeleted = statement.executeUpdate();
	            if (rowsDeleted > 0) {
	                System.out.println("Ejercicio eliminado exitosamente");
	            }
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
=======

public class EjercicioControlador implements EjercicioRepository {
	private final Connetion connetion;
 
 	public EjercicioControlador() {
     this.connection = DatabaseConnection.getInstance().getConnection();
 }
 	@Override
	public LinkedList<Ejercicio> getAllEjercicio() {
		LinkedList<Ejercicio> Ejercicios= new LinkedList<Ejercicio>();
		
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM administrador");
			ResultSet resultset= statement.executeQuery();
			
			while (resultset.next()) {
				Ejercicio Ejercicio=new Ejercicio(resultset.getString("ID_Ejercicio"),resultset.getString("Nombre"),resultset.getString("Maquina"),resultset.getString("Musculo"),resultset.getString("Descripcion"),resultset.getString("Video"),resultset.getString("ID_Area"));
				Ejercicios.add(Ejercicio);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocurrio un error al mostrar");
		}
		return Ejercicios;
	}

	@Override
	public Ejercicio getEjercicioByid(int id) {
		Ejercicio Ejercicio= null;
		try {
			PreparedStatement statement= connection.prepareStatement("SELECT * FROM ejercicio WHERE ID_Ejercicio=?");
			statement.setInt(1, id);
			
			ResultSet resultset= statement.executeQuery();
			if (resultset.next()) {
				 Ejercicio=new Ejercicio(resultset.getString("Nombre"),resultset.getString("Apellido"),resultset.getInt("Telefono"),resultset.getInt("ID_Sucursal"),resultset.getInt("DNI"),resultset.getInt("ID_Ejercicioistrador"),resultset.getString("Contrasenia"));
			}
		} catch (SQLException e) {
			  e.printStackTrace();
		}
		return Ejercicio;
	}

	@Override
	public void addEjercicio(Ejercicio Ejercicio) {
		try {
			PreparedStatement statement= connection.prepareStatement("INSERT INTO Ejercicioistrador (ID_Admnistrador, ID_Sucursal, Telefono, Apellido, DNI, Contrasenia, Nombre) VALUES (?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, Ejercicio.getId_Ejercicio());
			statement.setInt(2, Ejercicio.getId_sucursal());
			statement.setInt(3, Ejercicio.getTelefono());
			statement.setString(4, Ejercicio.getApellido());
			statement.setInt(5,Ejercicio.getDNI());
			statement.setString(6, Ejercicio.getContrasena());
			statement.setString(7, Ejercicio.getNombre());
			
			int rowsinsert= statement.executeUpdate();
			if (rowsinsert>0) {
				JOptionPane.showMessageDialog(null, "Ejercicio creado");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pudo añadir el Ejercicio");
		}
		
	}

	@Override
	public void updateEjercicio(Ejercicio Ejercicio) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Ejercicioistrador SET ID_Sucursal = ?, Telefono = ?, Apellido = ?, DNI = ?, Contrasenia = ?, Nombre = ? WHERE id = ?");
			statement.setInt(1, Ejercicio.getId_sucursal());
			statement.setInt(2, Ejercicio.getTelefono());
			statement.setString(3, Ejercicio.getApellido());
			statement.setInt(4,Ejercicio.getDNI());
			statement.setString(5, Ejercicio.getContrasena());
			statement.setString(6, Ejercicio.getNombre());
			statement.setInt(7, Ejercicio.getId_Ejercicio());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Usuario actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void deleteEjercicio(int Ejercicio) {
		try {
			PreparedStatement statement = connection.prepareStatement("DELETE from Ejercicioistrador where ID_Ejercicioistrador= ? ");
			statement.setInt(1, Ejercicio);
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

>>>>>>> franco-
}
