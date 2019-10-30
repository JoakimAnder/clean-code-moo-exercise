package refactored2.Integration.Jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection connection;

    private Connection(java.sql.Connection connection) {
        this.connection = connection;
    }

    public static Connection createConnection(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return new Connection(DriverManager.getConnection(url,user,password));
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparedStatement prepareStatement(String s) {
        try {
            return new PreparedStatement(connection.prepareStatement(s));
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
    }
}
