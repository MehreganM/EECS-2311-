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

    public LoginGUI(Hospital hospital) throws NoSpaceException {
        this.hospital = hospital;
        hospital.InitializeEmployees();
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
        
       // hospital.InitializeEmployees();
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
                PatientGUI patientInfoGUI = new PatientGUI(hospital);
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
    

}
