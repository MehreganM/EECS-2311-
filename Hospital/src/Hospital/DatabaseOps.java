package Hospital;


import java.sql.*;

public class DatabaseOps {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (ID, Fname, Lname, age, address, gender, doctor, nurse, family_doctor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	pstmt.setInt(0, patient.getPatientID());
        	pstmt.setString(1, patient.getFName());
        	pstmt.setString(2, patient.getLName());
        	pstmt.setInt(3, patient.getAge());
            pstmt.setString(4, patient.getAddress());
            pstmt.setString(5, patient.getGender());
            pstmt.setString(6, patient.getAssignedPhysician().toString());
            pstmt.setString(7, patient.getNurse().toString());
          //  pstmt.setString(8, patient.getFamilyDoctor()); don't have this method yet
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllPatients() {
        StringBuilder patientsInfo = new StringBuilder();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String patientInfo = String.format("ID: %d, First Name: %s, Last Name: %s, Age: %d Address: %s, Gender: %s\n",
                        rs.getInt("id"),
                        rs.getString("Fname"),
                        rs.getString("Lname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                		rs.getString("gender"));
                patientsInfo.append(patientInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientsInfo.toString();
    }

    
    public String getPatientById(int id) {
        StringBuilder patientInfo = new StringBuilder();
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                patientInfo.append(String.format("ID: %d, First Name: %s, Last Name: %s, Age: %d Address: %s, Gender: %s",
                		rs.getInt("id"),
                        rs.getString("Fname"),
                        rs.getString("Lname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                		rs.getString("gender")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patientInfo.toString();
    }


    public String searchPatientsByName(String name) {
        StringBuilder patientsInfo = new StringBuilder();
        String sql = "SELECT * FROM patients WHERE Fname LIKE ? OR Lname LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            pstmt.setString(2, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String patientInfo = String.format("ID: %d, First Name: %s, Last Name: %s, Age: %d Address: %s, Gender: %s\n",
                		rs.getInt("id"),
                        rs.getString("Fname"),
                        rs.getString("Lname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                		rs.getString("gender"));
                patientsInfo.append(patientInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patientsInfo.toString();
    }


    public void updatePatient(Patient patient) {
        String sql = "UPDATE patients SET Fname = ?, LName = ?, address = ?, doctor = ?, nurse = ?, family_doctor = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set parameters for the prepared statement based on the patient object
        	// pstmt.setInt(0, patient.getPatientID());
        	pstmt.setString(1, patient.getFName());
        	pstmt.setString(2, patient.getLName());
        	pstmt.setInt(3, patient.getAge());
        	pstmt.setString(4, patient.getAddress());
        	pstmt.setString(5, patient.getGender());
            pstmt.setString(6, patient.getAssignedPhysician().toString());
            pstmt.setString(7, patient.getNurse().toString());
            
        //    pstmt.setString(8, patient.getFamilyDoctor());  need functionality to get Family MD
            
            
            // Execute the update
            int affectedRows = pstmt.executeUpdate();
            
            // Check if the update was successful
            if (affectedRows > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("A patient with the ID " + patient.getPatientID() + " was not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
