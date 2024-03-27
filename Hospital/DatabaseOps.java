package Hospital;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    
    public Patient getPatientByIds(int id) {
        String sql = "SELECT * FROM patients WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setFirstName(rs.getString("Fname"));
                patient.setLastName(rs.getString("Lname"));
                patient.setAge(rs.getInt("age"));
                patient.setAddress(rs.getString("address"));
                patient.setGender(rs.getString("gender"));
                // You might need to set other attributes of the patient
                return patient;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
            

        	pstmt.setString(2, patient.getFName());
        	pstmt.setString(3, patient.getLName());
        	pstmt.setInt(4, patient.getAge());
        	pstmt.setString(5, patient.getAddress());
        	pstmt.setString(6, patient.getGender());
            pstmt.setString(7, patient.getAssignedPhysician().toString());
            pstmt.setString(8, patient.getNurse().toString());
            pstmt.setString(9, patient.getFamDoc().toString());  
            
            

            int affectedRows = pstmt.executeUpdate();
            

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
                physician.setSpecialty(rs.getString("specialty")); 
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

        /**
     * This method is to get all the patient in the hospital system
     * @return list of all the patients in the hospital
     * @author Amira Mohamed
     */
    public ArrayList<Patient> getAllPatients1() {
    	ArrayList<Patient>  patientsInfo = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        		ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	Patient patient = new Patient(
            	//		rs.getInt("id"),
                        rs.getString("Fname"),
                        rs.getString("Lname"),
                        rs.getInt("age"),
                        rs.getString("address"),
                		rs.getString("gender")
                		);
            	 	
            	patientsInfo.add(patient);
   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientsInfo;
    }
    /**
     * This method is to remove a patient from the hospital system 
     * @author Amira Mohamed
     * @param fname is the first name of the patient
     * @param lname is the last name of the patient
     * @param age is the age of the patient
     */
    public void deletePatient(String fname, String lname, int age) {
        Hospital hospital = new Hospital(null);
        Laboratory laboratory = new Laboratory(); 
        String queryDoctorAndId = "SELECT id, family_doctor FROM patients WHERE fname = ? AND lname = ? AND age = ?";
        String sqlDelete = "DELETE FROM patients WHERE fname = ? AND lname = ? AND age = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmtQuery = conn.prepareStatement(queryDoctorAndId)) {
            pstmtQuery.setString(1, fname);
            pstmtQuery.setString(2, lname);
            pstmtQuery.setInt(3, age);

            ResultSet rs = pstmtQuery.executeQuery();
            String doctorEmail = "dr.mark@gmail.com"; // Default email
            int patientId = 0;
            if (rs.next()) {
                String familyDoctor = rs.getString("family_doctor");
                patientId = rs.getInt("id"); // Correctly retrieve the patient ID
                int emailStart = familyDoctor.indexOf("email=") + "email=".length();
                int emailEnd = familyDoctor.indexOf("',", emailStart);
                doctorEmail = familyDoctor.substring(emailStart, emailEnd); // Extract email
            }

            String allTestsForPatient = "";
            if (patientId > 0) { // Check that patientId is greater than 0
                allTestsForPatient = laboratory.getAllTestsForPatientAsString(patientId);
            }

            try (PreparedStatement pstmtDelete = conn.prepareStatement(sqlDelete)) {
                hospital.sendEmail(doctorEmail, lname + " Record", "labtest " + allTestsForPatient);
                pstmtDelete.setString(1, fname);
                pstmtDelete.setString(2, lname);
                pstmtDelete.setInt(3, age);
                pstmtDelete.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public static void sendEmail(String recipientEmail, String subject, String body) {
		 String username = "50tW1tcjvD6M";
	     String password = "fxJCwpafJjP3"; 
	     String senderEmail = "eecshospital@gmail.com";

       Properties props = new Properties();
       props.put("mail.smtp.auth", "true");
       props.put("mail.smtp.starttls.enable", "true");
       props.put("mail.smtp.host", "smtp.mailsnag.com"); 
       props.put("mail.smtp.port", "2525"); 

       Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(username, password);
                   }
               });

       try {
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(senderEmail));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
           message.setSubject(subject);
           message.setText(body);

           Transport.send(message);

           System.out.println("Email sent successfully!");

       } catch (MessagingException e) {
           throw new RuntimeException(e);
       }
   }
    
    public void StoreFamDoc(int patientID, String name, String specialty, String email, String phone) {
		try( Connection connection = DatabaseConnection.getConnection()){
			String checksql = "SELECT * FROM famdoc WHERE id = ?";
			try(PreparedStatement checkstatement = connection.prepareStatement(checksql)){
				checkstatement.setInt(1, patientID);
				ResultSet resultset = checkstatement.executeQuery();
				
				// If a record exists, update it 
				if(resultset.next()) {
					updateFamDocInfo(patientID, name, specialty, email, phone);
				}
				else { 
					// If no record exists, insert a new record
					insertFamDocInfo(patientID, name, specialty, email, phone);
				}
				
				System.out.println("Fam doc validated sucessfully");
			
		}
			
		}catch (SQLException e) {
	        System.err.println("Error storing Fam doc: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void insertFamDocInfo(int patientID, String name, String specialty, String email, String phone) {
		try( Connection connection = DatabaseConnection.getConnection()){
			String insertsql = "INSERT INTO famdoc (id, name, specialty, email, phone)" + "VALUES(?,?,?,?,?)";
			try(PreparedStatement insertstatement = connection.prepareStatement(insertsql)){
				insertstatement.setInt(1, patientID);
				insertstatement.setString(2, name);
				insertstatement.setString(3, specialty);
				insertstatement.setString(4, email);
				insertstatement.setString(5, phone);
				
				insertstatement.executeUpdate();
				System.out.println("Family Doctor Updated");
			
		}
			
		}catch (SQLException e) {
            System.err.println("Error with famdoc: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void updateFamDocInfo(int patientID, String name, String specialty, String email, String phone) {
		try( Connection connection = DatabaseConnection.getConnection()){
			String updatesql = "UPDATE famdoc SET name = ?, specialty = ? , email = ?, phone = ? WHERE id = ?";
			try(PreparedStatement updatestatement = connection.prepareStatement(updatesql)){
				updatestatement.setString(1, name);
				updatestatement.setString(2, specialty);
				updatestatement.setString(3, email);
				updatestatement.setString(4, phone);
				
				updatestatement.setInt(5, patientID);

				updatestatement.executeUpdate();
				System.out.println("Famdoc updated sucessfully");
			
		}
			
		}catch (SQLException e) {
	        System.err.println("Error updating Famdoc: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String retrieveFamDocEmail(int id) {
        StringBuilder patientInfo = new StringBuilder();
        String sql = "SELECT * FROM famdoc WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                patientInfo.append(String.format("Email %s",
                		
                		rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return patientInfo.toString();
    }
	
    
    
    
}

