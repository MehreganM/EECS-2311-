package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNurseGUI extends JFrame {
    private Hospital hospital;
    private DatabaseOps databaseOps = new DatabaseOps();
    
    private JTextField firstNameField, lastNameField, ageField, genderField, addressField, usernameField, passwordField;
    private JButton addButton, returnButton;

    public AddNurseGUI(Hospital hospital) {
        super("Add Nurse");
        this.hospital = hospital;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        initializeFields();
        addComponentsToFrame();
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void initializeFields() {
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(20);
        genderField = new JTextField(20);
        addressField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        
        addButton = new JButton("Add Nurse");
        addButton.addActionListener(e -> addNurse());
        
        returnButton = new JButton("Return to Dashboard");
        returnButton.addActionListener(e -> returnToDashboard());
    }
    
    private void addComponentsToFrame() {
        add(new JLabel("First Name:"));
        add(firstNameField);
        add(new JLabel("Last Name:"));
        add(lastNameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Gender:"));
        add(genderField);
        add(new JLabel("Address:"));
        add(addressField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(addButton);
        add(returnButton);
    }
    
    private void addNurse() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
    	
        if (!validateUsername(username) || !validatePassword(password)) {
            return; 
        }
    	
    	String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String address = addressField.getText();
 
        
        Nurse newNurse = new Nurse(firstName, lastName, age, gender, address);
        newNurse.setUser(username);
        newNurse.setPass(password);
        
        // Assuming you have a method in Hospital to add a Physician
        hospital.hireNurse(newNurse);
        // Assuming you have a method in DatabaseOps to add a Physician to the database
        databaseOps.addNurse(newNurse);
        
        JOptionPane.showMessageDialog(this, "Nurse added successfully.");
        clearFields();
    }
    
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        genderField.setText("");
        addressField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        
    }
    
    private boolean validateUsername(String username) {
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.", "No Username Given", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean validatePassword(String password) {
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a password.", "No Password Given", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void returnToDashboard() {
        this.dispose();
        // Open or return to the main Admin GUI or dashboard
        EventQueue.invokeLater(() -> {
            AdminGUI adminGUI = new AdminGUI(hospital); // Adjust with actual logic to return to dashboard
            adminGUI.setVisible(true);
        });
    }
}
