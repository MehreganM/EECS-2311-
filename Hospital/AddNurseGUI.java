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
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String ageText = ageField.getText().trim();
        String gender = genderField.getText().trim();
        String address = addressField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Check if any fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || gender.isEmpty() || address.isEmpty()
                || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Information",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate first name and last name to ensure they contain only letters
        if (!firstName.matches("[a-zA-Z-' ]+") || !lastName.matches("[a-zA-Z-' ]+")) {
            JOptionPane.showMessageDialog(this, "First name and last name must contain only letters.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate gender field to ensure it is either "Male" or "Female"
        if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
            JOptionPane.showMessageDialog(this, "Gender must be either 'Male' or 'Female'.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Parse the age field
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid age. Please enter a valid number.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Nurse newNurse = new Nurse(firstName, lastName, age, gender, address);
        newNurse.setUser(username);
        newNurse.setPass(password);
        
        hospital.hireNurse(newNurse);
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
    
    private void returnToDashboard() {
        this.dispose();
        // Open or return to the main Admin GUI or dashboard
        EventQueue.invokeLater(() -> {
            AdminGUI adminGUI = new AdminGUI(hospital); // Adjust with actual logic to return to dashboard
            adminGUI.setVisible(true);
        });
    }
}
