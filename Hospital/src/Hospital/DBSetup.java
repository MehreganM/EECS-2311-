package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBSetup {
    private static final String CHECK_TABLE_EXISTS_QUERY = "SELECT TO REGCLASS('public.patients');";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS patients (" +
            "id SERIAL PRIMARY KEY," +
            "name VARCHAR(255)," +
            "address VARCHAR(255)," +
            "doctor VARCHAR(255)," +
            "nurse VARCHAR(255)," +
            "family_doctor VARCHAR(255)" +
            ");";

    public static void ensureTableExists() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Check if the table exists
            ResultSet rs = stmt.executeQuery(CHECK_TABLE_EXISTS_QUERY);
            if (!rs.next()) {
                // Table does not exist, create it
                stmt.executeUpdate(CREATE_TABLE_QUERY);
                System.out.println("Patients table created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

