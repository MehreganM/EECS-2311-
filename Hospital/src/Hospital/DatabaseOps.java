package Hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOps {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (name, address, doctor, nurse, family_doctor) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	pstmt.setInt(0, patient.getPatientID());
        	pstmt.setString(1, patient.getName());
            pstmt.setString(2, patient.getAddress());
            pstmt.setString(3, patient.getDoctor());
            pstmt.setString(4, patient.getNurse());
            pstmt.setString(5, patient.getFamilyDoctor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setNurse(rs.getString("nurse"));
                patient.setFamilyDoctor(rs.getString("family_doctor"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }
    
    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE id = ?";
        Patient patient = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setNurse(rs.getString("nurse"));
                patient.setFamilyDoctor(rs.getString("family_doctor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patient;
    }

    public List<Patient> searchPatientsByName(String name) {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setName(rs.getString("name"));
                patient.setAddress(rs.getString("address"));
                patient.setDoctor(rs.getString("doctor"));
                patient.setNurse(rs.getString("nurse"));
                patient.setFamilyDoctor(rs.getString("family_doctor"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patients;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE patients SET name = ?, address = ?, doctor = ?, nurse = ?, family_doctor = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Set parameters for the prepared statement based on the patient object
        	pstmt.setInt(1, patient.getId());
        	pstmt.setString(2, patient.getName());
            pstmt.setString(3, patient.getAddress());
            pstmt.setString(4, patient.getDoctor());
            pstmt.setString(5, patient.getNurse());
            pstmt.setString(6, patient.getFamilyDoctor());
            
            
            // Execute the update
            int affectedRows = pstmt.executeUpdate();
            
            // Check if the update was successful
            if (affectedRows > 0) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("A patient with the ID " + patient.getId() + " was not found.");
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
