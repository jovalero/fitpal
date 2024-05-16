
package fitpal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.UserRepository;
import Modelo.Usuario;

public class ClienteControlador implements UserRepository {
    private final Connection connection;

    public ClienteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

  
}
