package Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class Laboratory {

    public Laboratory() {
        
    }

    public boolean addTestRequest(labTest request, String description) {
        String sql = "INSERT INTO laboratory (test_name, test_description, test_result, patient_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Retrieve patient ID from the request
            int patientID = request.getPatient().getPatientID();
            
            // Set values for the prepared statement
            pstmt.setString(1, request.getType());
            pstmt.setString(2, description);
            pstmt.setString(3, request.getResults());
            pstmt.setInt(4, patientID); 
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Lab test request added successfully for patient with ID: " + patientID);
                return true;
            } else {
                System.out.println("Failed to add lab test request for patient with ID: " + patientID);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean updateTestResult(int patientID, String testName, String newResult) {
        String sql = "UPDATE laboratory SET test_result = ? WHERE patient_id = ? AND test_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newResult); 
            pstmt.setInt(2, patientID); 
            pstmt.setString(3, testName); 
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public String getAllTestsForPatientAsString(int patientID) {
        StringBuilder allTestsResults = new StringBuilder();
        String sql = "SELECT test_name, test_result FROM laboratory WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, patientID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                allTestsResults.append(rs.getString("test_name"))
                               .append(": ")
                               .append(rs.getString("test_result"))
                               .append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTestsResults.toString();
    }

    public String testResults(Patient patient, String type) {
        String sql = "SELECT test_result FROM laboratory WHERE patient_id = ? AND test_name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, patient.getPatientID());
            pstmt.setString(2, type);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("test_result");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    
}


	class labTest{
		private Patient patient; 
		private String type; 
		private String results; 
		
		  public labTest(Patient patient, String type) {
		        this.patient = patient;
		        this.type = type;
		    }
		public String getResults() {
			return this.results;
		}
		public String getType() {
			return this.type;
		}
		public Patient getPatient() {
			return this.patient;
		}
		public void addResult(String result) {
			this.results = result; 
			
		}
		
	}

