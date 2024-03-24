package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPhysicianGUI extends JFrame {
    private Hospital hospital;
    private DatabaseOps databaseOps = new DatabaseOps();
    
    private JTextField firstNameField, lastNameField, ageField, genderField, addressField, usernameField, passwordField;
    private JComboBox<String> specialtyComboBox;
    private JButton addButton, returnButton;

    public AddPhysicianGUI(Hospital hospital) {
        super("Add Physician");
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
        
        String[] specialties = {"Radiology", "Dermatology", "Neurology", "Cardiology", "Hematology", "Immunology"};
        specialtyComboBox = new JComboBox<>(specialties);
        
        addButton = new JButton("Add Physician");
        addButton.addActionListener(e -> addPhysician());
        
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
        add(new JLabel("Specialty:"));
        add(specialtyComboBox);
        add(addButton);
        add(returnButton);
    }
    
    private void addPhysician() {
    	hospital.physicianList = databaseOps.getAllPhysicians();
        hospital.nurseList = databaseOps.getAllNurses();
        
        for (int i = 0; i < hospital.physicianList.size(); i ++) {
        	hospital.hirePhysician(hospital.physicianList.get(i));
        	hospital.physicianList.get(i).toString();
        	}
        
        for (int i = 0; i < hospital.nurseList.size(); i ++ ) {
        	hospital.hireNurse(hospital.nurseList.get(i));
        	hospital.nurseList.get(i).toString();
        }
    	
    	String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String address = addressField.getText();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String specialty = (String) specialtyComboBox.getSelectedItem();
        
        Physician newPhysician = new Physician(firstName, lastName, age, gender, address);
        newPhysician.setUser(username);
        newPhysician.setPass(password);
        try {
            newPhysician.setSpecialty(specialty);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: Invalid specialty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        hospital.hirePhysician(newPhysician);
        databaseOps.addPhysician(newPhysician);
        
        JOptionPane.showMessageDialog(this, "Physician added successfully.");
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
        specialtyComboBox.setSelectedIndex(0); // Reset to first specialty in the list
    }
    
    private void returnToDashboard() {
        this.dispose();
        // Open or return to the main Admin GUI or dashboard
        EventQueue.invokeLater(() -> {
            AdminGUI adminGUI = new AdminGUI(hospital); // Adjust with actual logic to return to dashboard
            adminGUI.setVisible(true);
        });
    }
    public static void main(String[] args) {
        Hospital hospital = new Hospital(null); // Assuming Hospital class exists
        
        // Example of creating an instance of AddPhysicianGUI
        // Replace "hospital" with your actual Hospital instance
        // The AddPhysicianGUI constructor might require a Hospital instance
        AddPhysicianGUI addPhysicianGUI = new AddPhysicianGUI(hospital);
    }
}
