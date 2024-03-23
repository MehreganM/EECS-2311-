package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;



public class DBSetup {

    private static final String CREATE_PATIENTS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS patients (" +
            "id SERIAL PRIMARY KEY," +
            "Fname VARCHAR(255)," +
            "Lname VARCHAR(255)," +
            "age INT," +
            "address VARCHAR(255)," +
            "gender VARCHAR(255)," +
            "doctor INT," +
            "nurse INT," +
            "family_doctor VARCHAR(255)" +
            ");";

    private static final String CREATE_PHYSICIANS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS physicians (" +
            "id SERIAL PRIMARY KEY," +
            "firstName VARCHAR(50)," +
            "lastName VARCHAR(50)," +
            "age INT," +
            "gender VARCHAR(10)," +
            "address VARCHAR(100)," +
            "specialty VARCHAR(50)," +
            "username VARCHAR(50)," +
            "password VARCHAR(50)" +
            ");";

    private static final String CREATE_NURSES_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS nurses (" +
            "id SERIAL PRIMARY KEY," +
            "firstName VARCHAR(50)," +
            "lastName VARCHAR(50)," +
            "age INT," +
            "gender VARCHAR(10)," +
            "address VARCHAR(100)," +
            "username VARCHAR(50)," +
            "password VARCHAR(50)" + 
            ");";

    public static void ensureAllTablesExist() {
        ensureTableExists(CREATE_PATIENTS_TABLE_QUERY);
        ensureTableExists(CREATE_PHYSICIANS_TABLE_QUERY);
        ensureTableExists(CREATE_NURSES_TABLE_QUERY);
    }

    private static void ensureTableExists(String creationQuery) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(creationQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
