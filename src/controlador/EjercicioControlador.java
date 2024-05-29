package controlador;

import java.sql.Connection;

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

}
