package Hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

//Need to run the function: DBSetup.ensureTableExists(); in the application to create the database

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
            "consent BOOLEAN," +
            "family_doctor VARCHAR(255)" +
            ");";
   
   private static final String CREATE_LAB_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS laboratory (" +
		   "patient_id INT REFERENCES patients(id)," +
           "test_name VARCHAR(255)," +
           "test_description VARCHAR(255)," +
           "test_result VARCHAR(255)" +
           ");";
    
 /*   private static final String CREATE_PATIENTS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS patients (" +
            "id SERIAL PRIMARY KEY," +
            "Fname VARCHAR(255)," +
            "Lname VARCHAR(255)," +
            "age INT," +
            "address VARCHAR(255)," +
            "gender VARCHAR(255)," +
            "doctor INT," +
            "nurse INT," +
            "consent BOOLEAN," +
            "family_doctor VARCHAR(255)," +
            "FOREIGN KEY (doctor) REFERENCES physicians(id)," +
            "FOREIGN KEY (nurse) REFERENCES nurses(id)" +
            ");"; */


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
    
    private static final String CREATE_FAMDOC_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS famdoc (" +
            "id INT REFERENCES patients(id)," +
            "name VARCHAR(100)," +
            "specialty VARCHAR(50)," +
            "email VARCHAR(150)," +
            "phone VARCHAR(15)" +
            ");";
    
  /*  private static final String CREATE_DIRECTOR_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS director (" +
    		"id SERIAL PRIMARY KEY," +
            "firstName VARCHAR(50)," +
            "lastName VARCHAR(50)," +
            "age INT," +
            "gender VARCHAR(10)," +
            "address VARCHAR(100)" +
            ");";*/

    private static final String CREATE_VITALSIGNS_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS vital_signs ("
    		+ "patient_id INT REFERENCES patients(id),"
    		+ "temperature DOUBLE PRECISION,"
    		+ "systolic_pressure INT,"
    		+ "diastolic_pressure INT,"
    		+ "heart_rate INT,"
    		+ "respiratory_rate INT"
    		+ ");";

    public static void ensureAllTablesExist() {
        ensureTableExists(CREATE_PATIENTS_TABLE_QUERY);
        ensureTableExists(CREATE_PHYSICIANS_TABLE_QUERY);
        ensureTableExists(CREATE_NURSES_TABLE_QUERY);
        ensureTableExists(CREATE_FAMDOC_TABLE_QUERY);
        ensureTableExists(CREATE_VITALSIGNS_TABLE_QUERY);
        ensureTableExists(CREATE_LAB_TABLE_QUERY);
       // ensureTableExists(CREATE_DIRECTOR_TABLE_QUERY);
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
