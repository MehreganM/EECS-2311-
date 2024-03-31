package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private Hospital hospital;
    private Nurse loggedInNurse;
    private Physician loggedInPhysician;
    static DatabaseOps dbOps = new DatabaseOps();
    //private String role = ""; // Role for the user logging in

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
    }

//    public void setRole(String role) {
//        this.role = role;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());
        boolean loginSuccessful = false;

        if ("lab".equals(user) && "Lab2024!".equals(pass)) {
            loginSuccessful = true;
            EventQueue.invokeLater(() -> {
                LabDashboard labDashboard = new LabDashboard();
                labDashboard.setVisible(true);
            });
            this.dispose();
        } else if ("admin".equals(user) && "pass".equals(pass)) {
            loginSuccessful = true;
            EventQueue.invokeLater(() -> {
                AdminGUI adminGUI = new AdminGUI(hospital);
                adminGUI.setVisible(true);
            });
            this.dispose();
        } else {
            for (Physician physician : hospital.extractAllPhysicianDetails()) {
                if (user.equals(physician.getUser()) && pass.equals(physician.getPass())) {
                    loggedInPhysician = physician;
                    loginSuccessful = true;
                    break;
                }
            }
            if (!loginSuccessful) {
                for (Nurse nurse : hospital.extractAllNurseDetails()) {
                    if (user.equals(nurse.getUser()) && pass.equals(nurse.getPass())) {
                        loggedInNurse = nurse;
                        loginSuccessful = true;
                        break;
                    }
                }
            }
        }

        if (loggedInPhysician != null) {
            EventQueue.invokeLater(() -> {
                PatientGUI patientInfoGUI = new PatientGUI(loggedInPhysician, hospital);
                patientInfoGUI.setVisible(true);
            });
            this.dispose();
        } else if (loggedInNurse != null) {
            EventQueue.invokeLater(() -> {
                NurseGUI nurseDashboard = new NurseGUI(loggedInNurse, hospital);
                nurseDashboard.setVisible(true);
            });
            this.dispose();
        }

        if (!loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
