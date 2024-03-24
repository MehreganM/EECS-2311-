package Hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOps {
    public void addPatient(Patient patient) {
        String sql = "INSERT INTO patients (ID, Fname, Lname, age, address, gender, doctor, nurse, family_doctor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

        	pstmt.setInt(1, patient.getPatientID());
        	pstmt.setString(2, patient.getFName());
        	pstmt.setString(3, patient.getLName());
        	pstmt.setInt(4, patient.getAge());
            pstmt.setString(5, patient.getAddress());
            pstmt.setString(6, patient.getGender());
           
            int physician = patient.getAssignedPhysician() != null ? patient.getAssignedPhysician().getEmployeeID() : 0;
            pstmt.setInt(8, physician);
            
            int nurse = patient.getNurse() != null ? patient.getNurse().getEmployeeID() : 0;
            pstmt.setInt(7, nurse);
            
            String famdr = patient.getFamDoc() != null ? patient.getFamDoc().toString() : "[None]";
            pstmt.setString(9, famdr);
            
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
    
    public String getPatientByPhysicianId(int id) {
        StringBuilder patientInfo = new StringBuilder();
        String sql = "SELECT * FROM patients WHERE doctor = ?";

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
        	// pstmt.setInt(0, patient.getPatientID()); should not really be able to change this tbh
        	pstmt.setString(2, patient.getFName());
        	pstmt.setString(3, patient.getLName());
        	pstmt.setInt(4, patient.getAge());
        	pstmt.setString(5, patient.getAddress());
        	pstmt.setString(6, patient.getGender());
            pstmt.setString(7, patient.getAssignedPhysician().toString());
            pstmt.setString(8, patient.getNurse().toString());
            pstmt.setString(9, patient.getFamDoc().toString());  
            
            
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
    
  /*  public void deletePatient2(String Fname) {
        String sql = "DELETE FROM patients WHERE Fname = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Fname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    
    public ArrayList<Physician> getAllPhysicians() {
        ArrayList<Physician> physicians = new ArrayList<>();
        String sql = "SELECT * FROM physicians";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Physician physician = new Physician(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("address")
                );
                physician.setSpecialty(rs.getString("specialty")); // Assuming setSpecialty handles exceptions internally or they are caught here
                physician.setUser(rs.getString("username"));
                physician.setPass(rs.getString("password"));
                physicians.add(physician);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting specialty: " + e.getMessage());
        }
        return physicians;
    }
    
    public String getPatientByPhysician(int id) {
        StringBuilder patientInfo = new StringBuilder();
        String sql = "SELECT * FROM patients WHERE doctor = ?";

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

    public ArrayList<Nurse> getAllNurses() {
        ArrayList<Nurse> nurses = new ArrayList<>();
        String sql = "SELECT * FROM nurses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Nurse nurse = new Nurse(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("address")
                );
                nurse.setUser(rs.getString("username"));
                nurse.setPass(rs.getString("password"));
                nurses.add(nurse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }
    
    public String getPatientByNurse(int id) {
        StringBuilder patientInfo = new StringBuilder();
        String sql = "SELECT * FROM patients WHERE nurse = ?";

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
    
    public ArrayList<Patient> initializePatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient(
                    rs.getString("fName"),
                    rs.getString("lName"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("address")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting specialty: " + e.getMessage());
        }
        return patients;
    }
    
    public void addPhysician(Physician physician) {
        String sql = "INSERT INTO physicians (id, firstName, lastName, age, gender, address, specialty, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, physician.getEmployeeID());
        	pstmt.setString(2, physician.getFirstName());
            pstmt.setString(3, physician.getLastName());
            pstmt.setInt(4, physician.getAge());
            pstmt.setString(5, physician.getGender());
            pstmt.setString(6, physician.getAddress());
            pstmt.setString(7, physician.getSpecialty());
            pstmt.setString(8, physician.getUser()); // Assuming there's a getUser method
            pstmt.setString(9, physician.getPass()); // Assuming there's a getPass method

            pstmt.executeUpdate();
            System.out.println("Physician added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding physician: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deletePhysician(String name) {
        String sql = "DELETE FROM physicians WHERE firstname = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addNurse(Nurse nurse) {
        String sql = "INSERT INTO nurses (id, firstName, lastName, age, gender, address, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, nurse.getEmployeeID());
        	pstmt.setString(2, nurse.getFirstName());
            pstmt.setString(3,nurse.getLastName());
            pstmt.setInt(4, nurse.getAge());
            pstmt.setString(5, nurse.getGender());
            pstmt.setString(6, nurse.getAddress());
            pstmt.setString(7, nurse.getUser()); // Assuming there's a getUser method
            pstmt.setString(8, nurse.getPass()); // Assuming there's a getPass method

            pstmt.executeUpdate();
            System.out.println("Nurse added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding nurse: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void deleteNurse(String name) {
        String sql = "DELETE FROM nurses WHERE firstname = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    
    
    
}


