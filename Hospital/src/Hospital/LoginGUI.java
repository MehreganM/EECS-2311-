package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import statement for ArrayList

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private Hospital hospital;

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
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        boolean isValidUser = false;

        for (Physician physician : hospital.extractAllPhysicianDetails()) {
            if (user.equals(physician.user) && pass.equals(physician.pass)) {
                isValidUser = true;
                break;
            }
        }

        if (isValidUser) {
            EventQueue.invokeLater(() -> {
                PatientInformationGUI patientInfoGUI = new PatientInformationGUI(hospital);
                patientInfoGUI.setVisible(true);
            });
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    


    public static void main(String[] args) {
    Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
        
        
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        
        Physician physician = new Physician("DR.AL", "kp", 35, "Male", "202 Oak St");
        physician.setSpecialty("Immunology");
        hospital.hirePhysician(physician);
        
        physician.user=("AL");
        physician.pass=("123");
        
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
