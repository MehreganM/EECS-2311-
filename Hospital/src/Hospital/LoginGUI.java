package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import statement for ArrayList
import java.util.List;

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private Hospital hospital;
	private Nurse loggedInNurse;
	private Physician loggedInPhysician;
	static DatabaseOps dbOps = new DatabaseOps();
	private boolean isAdminLoggedIn = false;

    public LoginGUI(Hospital hospital) {
        this.hospital = hospital;
        setTitle("Hospital App Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 2));

        add(new JLabel("User:"));
        userField = new JTextField(20);
        add(userField);

        add(new JLabel("Password:"));
        passField = new JPasswordField(20);
        add(passField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(new JLabel("")); 
        add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
      /*  Object loggedInUser = null; // To store the logged-in user, either Physician or Nurse
        Physician loggedInPhysician = null;
        Nurse loggedInNurse = null; */

        if ("admin".equals(user) && "pass".equals(pass)) {
            isAdminLoggedIn = true;
        } else {
            // Existing checks for Physician and Nurse
            for (Physician physician : hospital.extractAllPhysicianDetails()) {
                if (user.equals(physician.getUser()) && pass.equals(physician.getPass())) {
                    loggedInPhysician = physician;
                    break;
                }
            }
            if (loggedInPhysician == null) { // Check for Nurse if no Physician found
                for (Nurse nurse : hospital.extractAllNurseDetails()) {
                    if (user.equals(nurse.getUser()) && pass.equals(nurse.getPass())) {
                        loggedInNurse = nurse;
                        break;
                    }
                }
            }
        }
        
        if (isAdminLoggedIn) {
            EventQueue.invokeLater(() -> {
                AdminGUI adminGUI = new AdminGUI(hospital); // Assuming AdminGUI exists
                adminGUI.setVisible(true);
            });
            this.setVisible(false); // Hide the login screen
        } else
        

        // Decide what to do based on the type of user logged in
        if (loggedInPhysician != null ) {
            EventQueue.invokeLater(() -> {
                PatientInformationGUI patientInfoGUI = new PatientInformationGUI(hospital);
                patientInfoGUI.setVisible(true);
            });
            this.setVisible(false);
        } else if (loggedInNurse != null) {
        	EventQueue.invokeLater(() -> {
        		NurseGUI nurseDashboard = new NurseGUI(loggedInNurse,hospital); // loggedInNurse is the Nurse object that logged in
        	    nurseDashboard.setVisible(true);
        	});
        	this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    


    public static void main(String[] args) {
    
    	DBSetup.ensureAllTablesExist();
    	
   	
		
	
    	Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
    
        
        
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        
        hospital.InitializeEmployees();
        
        Physician physician = new Physician("DR.AL", "kp", 35, "Male", "202 Oak St");
        physician.setSpecialty("Immunology");
        hospital.hirePhysician(physician);
        
        physician.user=("AL");
        physician.pass=("123");
        
        Nurse nurse = new Nurse("Mary", "Poppins",121,"Fairy", "Your moms house");
        hospital.hireNurse(nurse);
        nurse.user=("Nur");
        nurse.pass=("123");
        
        
        
        Patient patient = new Patient("John", "Smith", 30, "Male", "123 Main St");
        Patient patient2 = new Patient("Ali", "Bakhshi", 30, "Male", "123 Main St");
        Patient patient3 = new Patient("Sarah", "Lance", 30, "female", "123 Main St");
        Patient patient4 = new Patient("Kim", "k", 30, "Female", "123 Main St");
  
        
        try {
        	hospital.admitPatient(patient);
        	hospital.admitPatient(patient2);
        	hospital.admitPatient(patient3);
        	hospital.admitPatient(patient4);
		} catch (NoSpaceException e) {
			e.printStackTrace();
		}
       
        
        Laboratory lab = new Laboratory();
        labTest test1 = new labTest(patient,"blood");
        physician.LabReq(lab, test1);
        test1.addResult("good");
        
        
        physician.prescripe(patient, "advil");
        physician.prescripe(patient, "codeine");
        physician.updateLab(patient, lab);
        
        
        EventQueue.invokeLater(() -> {
            new LoginGUI(hospital).setVisible(true);
        });
    }
}
