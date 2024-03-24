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
            
            pstmt.setString(1, request.getType());
            pstmt.setString(2, description);
            pstmt.setString(3, request.getResults());
            pstmt.setInt(4, request.getPatient().getPatientID()); 
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateTestResult(labTest test, String newResult) {
        String sql = "UPDATE laboratory SET test_result = ? WHERE test_name = ? AND patient_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newResult);
            pstmt.setString(2, test.getType());
            pstmt.setInt(3, test.getPatient().getPatientID());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String getAllTestsForPatientAsString(Patient patient) {
        StringBuilder allTestsResults = new StringBuilder();
        String sql = "SELECT test_name, test_result FROM laboratory WHERE patient_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, patient.getPatientID());
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

