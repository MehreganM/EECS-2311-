package Hospital.src.Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost/postgres"; // this may also need to be changed to work on existing computer
    private static final String USER = "postgres"; // will need to change this to make sure it coincides with username of system using this application
    private static final String PASSWORD = "harrish5"; // will need to change this to make sure it coincides with password of system using this application

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
