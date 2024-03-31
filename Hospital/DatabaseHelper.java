package Hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.io.TempDir;


/**
 * This is the database for the patients. It stores patient information such as first name, last name,
 * age, gender, and address. It manages storing patient information, retrieving patient information, 
 * storing vital signs, and retrieving vital signs from the database. 
 * @author Amira Mohamed
 */
public class DatabaseHelper {

	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";

	
	public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
         
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
	
	public void updateConsentFormStatus(int patientID, boolean consentFormSigned) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    try {
	        connection = getConnection(); 
	        String sql = "UPDATE patients SET consent = ? WHERE id = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setBoolean(1, consentFormSigned); 
	        statement.setInt(2, patientID);
	        int affectedRows = statement.executeUpdate();
	        if (affectedRows > 0) {
	            System.out.println("Patient consent form status updated successfully for Patient ID: " + patientID);
	        } else {
	            System.out.println("No matching patient found for Patient ID: " + patientID);
	        }
	    } catch (SQLException e) {
	        System.err.println("Error updating consent form status: " + e.getMessage());
	        e.printStackTrace();
	    } 
	    finally {

	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException ex) {
	            System.err.println("Error closing resources: " + ex.getMessage());
	        }
	    }
	}
    /**
     * This method is to store patient information in the database
     * @param firstName is the first name of the patient 
     * @param lastName is the last name of the patient 
     * @param age is the age of the patient 
     * @param gender is the gender of the patient 
     * @param address is the address of the patient
     * @author Amira Mohamed
     */
	public void storePatientData(String firstName, String lastName, int age, String address, String gender) {
		try( Connection connection = getConnection()){
			String sql = "INSERT INTO patients(fname, lname, age, address, gender)" + "VALUES(?,?,?,?,?)";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				statement.setInt(3, age);
				statement.setString(4, address);
				statement.setString(5, gender);
				
				statement.executeUpdate();
				System.out.println("Patient data stored sucessfully");
				
			
		}
			
		}catch (SQLException e) {
			System.err.println("Error storing patient data: " + e.getMessage());
			e.printStackTrace();
		}
				
		
	}

	/**
	 * This method is to retrieve patient information from the database
	 * @param firstName is the first name of the patient by which the information will be retrieved
	 * @author Amira Mohamed
	 */
	public void retievePatientData(String firstname) {
		try( Connection connection = getConnection()){
			String sql = "SELECT * FROM patients WHERE fname = ?";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(2, firstname);
				
				ResultSet resultset = statement.executeQuery();
				while(resultset.next()) {
					int patientID = resultset.getInt("id");
					String firstName = resultset.getString("fname");
					String lastName = resultset.getString("lname");
					int age = resultset.getInt("age");
					String address = resultset.getString("address");
					String gender = resultset.getString("gender");
					
					
					System.out.println("Patient ID: " + patientID);
					System.out.println("First Name:" + firstName);
					System.out.println("Last Name: " + lastName);
					System.out.println("Age: " + age);
					System.out.println("Gender: " + gender);
					System. out. println("Address: " + address);
				}
				
			}
			
			
		} catch (SQLException e) {
			System.err.println("ERROR retrieving patient data: " + e.getMessage());
			e.printStackTrace();
		}
				
		
	}

	/**
	 * This method is to retrieve patient information from the database
	 * @param id is the id of the patient by which the information will be retrieved
	 * @author Amira Mohamed
	 */
	public void retievePatientData(int id) {
		try( Connection connection = getConnection()){
			String sql = "SELECT * FROM patients WHERE id = ?";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setInt(1, id);
				
				ResultSet resultset = statement.executeQuery();
				while(resultset.next()) {
					int patientID = resultset.getInt("id");
					String firstName = resultset.getString("fname");
					String lastName = resultset.getString("lname");
					int age = resultset.getInt("age");
					String address = resultset.getString("address");
					String gender = resultset.getString("gender");

					
					System.out.println("Patient ID: " + patientID);
					System.out.println("First Name:" + firstName);
					System.out.println("Last Name: " + lastName);
					System.out.println("Age: " + age);
					System.out.println("Gender: " + gender);
					System. out. println("Address: " + address);
				}
				
			}
			
			
		} catch (SQLException e) {
			System.err.println("ERROR retrieving patient data: " + e.getMessage());
			e.printStackTrace();
		}
				
		
	}
	

	/**
	 * This method is to check if there is a vital signs recorded for the patient. If there is, it will
	 * update it to the new ones. Otherwise, it will add the vital signs as a new record.
	 * @param patientID is the patient ID for which the vital signs will be retrieved
	 * @param temperature is the temperature of the patient 
	 * @param systolicPressure is the systolic pressure of the patient 
	 * @param diastolicPressure is the diastolic pressure of the patient 
	 * @param heartRate is the heart rate of the patient 
	 * @param respiratoryRate is the respiratory rate of the patient 
	 */
	public void storeVitalSigns(int patientID, double temperature, int systolicPressure, int diastolicPressure, int heartRate, int respiratoryRate) {
		try( Connection connection = getConnection()){
			String checksql = "SELECT * FROM vital_signs WHERE patient_id = ?";
			try(PreparedStatement checkstatement = connection.prepareStatement(checksql)){
				checkstatement.setInt(1, patientID);
				ResultSet resultset = checkstatement.executeQuery();
				
				// If a record exists, update it 
				if(resultset.next()) {
					updateVitalSigns(patientID, temperature, systolicPressure, diastolicPressure, heartRate, respiratoryRate);
				}
				else { 
					// If no record exists, insert a new record
					insertVitalSigns(patientID, temperature, systolicPressure, diastolicPressure, heartRate, respiratoryRate);
				}
				
				System.out.println("Vital signs validated sucessfully");
			
		}
			
		}catch (SQLException e) {
	        System.err.println("Error storing Vital signs: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method is to add new vital signs for the patient.
	 * @param patientID is the patient ID for which the vital signs will be retrieved
	 * @param temperature is the temperature of the patient 
	 * @param systolicPressure is the systolic pressure of the patient 
	 * @param diastolicPressure is the diastolic pressure of the patient 
	 * @param heartRate is the heart rate of the patient 
	 * @param respiratoryRate is the respiratory rate of the patient 
	 */
	public void insertVitalSigns(int patientID, double temperature, int systolicPressure, int diastolicPressure, int heartRate, int respiratoryRate) {
		try( Connection connection = getConnection()){
			String insertsql = "INSERT INTO vital_signs (patient_id, temperature, systolic_Pressure, diastolic_Pressure, heart_Rate, respiratory_Rate)" + "VALUES(?,?,?,?,?,?)";
			try(PreparedStatement insertstatement = connection.prepareStatement(insertsql)){
				insertstatement.setInt(1, patientID);
				insertstatement.setDouble(2, temperature);
				insertstatement.setInt(3, systolicPressure);
				insertstatement.setInt(4, diastolicPressure);
				insertstatement.setInt(5, heartRate);
				insertstatement.setInt(6, respiratoryRate);
				
				insertstatement.executeUpdate();
				System.out.println("Vital signs stored sucessfully");
			
		}
			
		}catch (SQLException e) {
            System.err.println("Error storing Vital signs: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is to update the recorded vital signs of the patient.
	 * It replaces the old vital signs with the updated one
	 * @param patientID is the patient ID for which the vital signs will be retrieved
	 * @param temperature is the temperature of the patient 
	 * @param systolicPressure is the systolic pressure of the patient 
	 * @param diastolicPressure is the diastolic pressure of the patient 
	 * @param heartRate is the heart rate of the patient 
	 * @param respiratoryRate is the respiratory rate of the patient 
	 */
	public void updateVitalSigns(int patientID, double temperature, int systolicPressure, int diastolicPressure, int heartRate, int respiratoryRate) {
		try( Connection connection = getConnection()){
			String updatesql = "UPDATE  vital_signs SET temperature = ?, systolic_Pressure = ? , diastolic_Pressure = ?, heart_Rate = ?, respiratory_Rate = ? WHERE patient_id = ?";
			try(PreparedStatement updatestatement = connection.prepareStatement(updatesql)){
				updatestatement.setDouble(1, temperature);
				updatestatement.setInt(2, systolicPressure);
				updatestatement.setInt(3, diastolicPressure);
				updatestatement.setInt(4, heartRate);
				updatestatement.setInt(5, respiratoryRate);
				updatestatement.setInt(6, patientID);

				updatestatement.executeUpdate();
				System.out.println("Vital signs updated sucessfully");
			
		}
			
		}catch (SQLException e) {
	        System.err.println("Error updating Vital signs: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	/**
	 * This method is to retrieve the vital signs of the patient
	 * @param patientID is the patient ID for which the vital signs will be retrieved
	 */
	public VitalSigns retrieveVitalSigns(int patientID) {
		
		VitalSigns vitalsigns = null;
		try( Connection connection = getConnection()){
			String sql = "SELECT * FROM vital_signs WHERE patient_id = ?";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setInt(1, patientID);
				
				ResultSet resultset = statement.executeQuery();
				while(resultset.next()) {
					double Temperature = resultset.getDouble("temperature");
					int systolicPressure = resultset.getInt("systolic_pressure");
					int diastolicPressure = resultset.getInt("diastolic_pressure"); 
					int heartRate = resultset.getInt("heart_rate");
					int RespiratoryRate = resultset.getInt("respiratory_rate");
					
					System.out.println("Temperature: " + Temperature + "°C");
			        System.out.println("Systolic Pressure: " + systolicPressure + " mmHg");
			        System.out.println("Diastolic Pressure: " + diastolicPressure + " mmHg");
			        System.out.println("Blood Pressure: " + systolicPressure + "/" + diastolicPressure + " mmHg");
			        System.out.println("Heart Rate: " + heartRate + " bpm/min");
			        System.out.println("Respiratory Rate: " + RespiratoryRate + " breaths/min");
			        
			        vitalsigns = new VitalSigns(Temperature, systolicPressure, diastolicPressure, heartRate, RespiratoryRate);
				
				}
				resultset.close();
				statement.close();
				connection.close();
		
		}
				
		}catch (SQLException e) {
            System.err.println("Error retrieving Vital signs: " + e.getMessage());
			e.printStackTrace();
		}
		return vitalsigns;
				
		
	}
	/**
	 * @author Parmoun
	 * Prescription
	 */

	public static boolean addPrescription(Prescription prescription) {
	    String sql = "INSERT INTO prescriptions (patient_id, physician_name, medication_name, dosage, instructions) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, prescription.getPatientId());
	        pstmt.setString(2, prescription.getPhysicianName());
	        pstmt.setString(3, prescription.getMedicationName());
	        pstmt.setString(4, prescription.getDosage());
	        pstmt.setString(5, prescription.getInstructions());

	        int affectedRows = pstmt.executeUpdate();
	        return affectedRows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	}




