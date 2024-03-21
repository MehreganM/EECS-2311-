package Hospital.src.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NursePatientAddFrame extends JFrame {
    Nurse nurse; // The nurse to which patients will be added
    Hospital hospital;
    DatabaseOps databaseOps = new DatabaseOps();
    
    private JTextField firstNameField, lastNameField, ageField, genderField, addressField;
    private JTextField familyDoctorNameField, familyDoctorSpecialtyField, familyDoctorNumberField, familyDoctorEmailField;
    private JButton addButton, returnButton;
    private JCheckBox famDrConsent;

    public NursePatientAddFrame(Nurse nurse, Hospital hospital) {
        super("Add Patient to Nurse");
        this.nurse = nurse;
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
        familyDoctorNameField = new JTextField(20);
        familyDoctorSpecialtyField = new JTextField(20);
        familyDoctorEmailField = new JTextField(20);
        familyDoctorNumberField = new JTextField(20);
        
        
        
        addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> {
            try {
                addPatientToNurse();
            } catch (NoSpaceException e1) {
                JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        returnButton = new JButton("Return to Dashboard");
        returnButton.addActionListener(e -> returnToDashboard());

        famDrConsent = new JCheckBox("Add Family Doctor Information");
        famDrConsent.addActionListener(e -> toggleFamilyDoctorFields(famDrConsent.isSelected()));
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
        add(famDrConsent);
        add(new JLabel("Family Doctor Name:"));
        add(familyDoctorNameField);
        add(new JLabel("Family Doctor Specialty:"));
        add(familyDoctorSpecialtyField);        
        add(new JLabel("Family Doctor Email:"));
        add(familyDoctorEmailField);
        add(new JLabel("Family Doctor Phone Number:"));
        add(familyDoctorNumberField);
        add(addButton);
        add(returnButton);
        
        // Initially set family doctor fields to be invisible
        toggleFamilyDoctorFields(famDrConsent.isSelected());
    }
    
    private void toggleFamilyDoctorFields(boolean isVisible) {
        familyDoctorNameField.setVisible(isVisible);
        familyDoctorSpecialtyField.setVisible(isVisible);
        familyDoctorNumberField.setVisible(isVisible);
        familyDoctorEmailField.setVisible(isVisible);
        pack(); // Adjust window size after toggling visibility
    }

    private void addPatientToNurse() throws NoSpaceException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String address = addressField.getText();
        
        Patient newPatient = new Patient(firstName, lastName, age, gender, address);
        // Assuming these methods exist and work as intended
        hospital.admitPatient(newPatient);      
        newPatient.setAssignedNurse(nurse);
        
        if (famDrConsent.isSelected()) {
            // Add family doctor information to the patient
            String docName = familyDoctorNameField.getText();
            String docSpecialty = familyDoctorSpecialtyField.getText();
            String docEmail = familyDoctorEmailField.getText();
            String docNumber = familyDoctorNumberField.getText();            
            // Method to set family doctor information on newPatient
            FamilyDoctor famdr = new FamilyDoctor(docName, docSpecialty, null, docEmail, docNumber);
            newPatient.setFamilyDoctor(famdr);
            // newPatient.setFamilyDoctor(new Doctor(docName, docSpecialty));
        }

        boolean added = nurse.addPatient(newPatient);
        databaseOps.addPatient(newPatient); // Make sure this method handles the family doctor details if necessary
        
        if (added) {
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add patient. Nurse's list may be full.");
        }
    }
    
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        genderField.setText("");
        addressField.setText("");
        familyDoctorNameField.setText("");
        familyDoctorSpecialtyField.setText("");
        familyDoctorNumberField.setText("");
        familyDoctorEmailField.setText("");
        famDrConsent.setSelected(false); // Optionally reset the checkbox
        toggleFamilyDoctorFields(false); // Hide family doctor fields again if needed
    }
    
    private void returnToDashboard() {
        dispose();
        EventQueue.invokeLater(() -> {
            // Assuming NurseGUI constructor accepts a Nurse and Hospital object
            NurseGUI nurseGUI = new NurseGUI(nurse, hospital);
            nurseGUI.setVisible(true);
        });
    }
}
