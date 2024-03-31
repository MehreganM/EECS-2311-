package Hospital;

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
    private JComboBox physicianComboBox;
  
    

    public NursePatientAddFrame(Nurse nurse, Hospital hospital) throws NoSpaceException {
        super("Add Patient to Nurse");
        this.nurse = nurse;
        this.hospital = hospital;
        hospital.InitializeEmployees();
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

        physicianComboBox = new JComboBox<>();
        populatePhysiciansComboBox();

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
        add(new JLabel("Assign Physician"));
        add(physicianComboBox);
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
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String ageText = ageField.getText().trim();
        String gender = genderField.getText().trim();
        String address = addressField.getText().trim();

        // Check if any fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || gender.isEmpty() || address.isEmpty()) {
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

        // Family doctor information fields are considered optional, thus not included in the mandatory checks
        Patient newPatient = new Patient(firstName, lastName, age, gender, address);
        
        if (famDrConsent.isSelected() && !familyDoctorNameField.getText().isEmpty() && 
            !familyDoctorSpecialtyField.getText().isEmpty() && !familyDoctorEmailField.getText().isEmpty()) {
            
            FamilyDoctor familyDoctor = new FamilyDoctor(
                familyDoctorNameField.getText().trim(), 
                familyDoctorSpecialtyField.getText().trim(), 
                null, 
                familyDoctorEmailField.getText().trim(), 
                familyDoctorNumberField.getText().trim()
            );
            newPatient.setFamilyDoctor(familyDoctor);
        }

        boolean added = nurse.addPatient(newPatient);
        if (added) {
            // Assuming there's a method in DatabaseOps to add a patient along with the nurse ID and selected physician ID
            int nurseID = databaseOps.getNurseIdByName(nurse.getFirstName(), nurse.getLastName());
            databaseOps.addPatient(newPatient, nurseID, getSelectedPhysicianID());
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
            clearFields();
        } else {
            throw new NoSpaceException("Nurse's patient list is full.");
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
        toggleFamilyDoctorFields(false); 
    }
    

    private void populatePhysiciansComboBox() {
        physicianComboBox.removeAllItems(); // Clear current items
        String physiciansInfo = databaseOps.getAllPhysicians1();
    	if(!physiciansInfo.isEmpty()) {
    		String[] physicianLines = physiciansInfo.split("\n");
    	
    	
    	System.out.println(physicianLines);
    	
    	// Iterate through all the physicians in the database and add it to the comboBox
    	for (String physician : physicianLines) {
    		String parts [] = physician.split(",");
    		
    		int physicianID = Integer.parseInt(parts[0].split(": ")[1]);
    		String firstname = parts[1].split(": ")[1];
    		String lastname = parts[2].split(": ")[1];
    		int age = Integer.parseInt(parts[3].split(": ")[1]);
     
            String physicianList = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d", physicianID, firstname, lastname, age );
            System.out.println(physicianList.toString());
            physicianComboBox.addItem(physicianList);
    		}
    	}
    }
    
    private int getSelectedPhysicianID() {
        String selectedPhysicianInfo = (String) physicianComboBox.getSelectedItem();
        if (selectedPhysicianInfo != null && !selectedPhysicianInfo.isEmpty()) {
            try {
                // Assuming the format "ID: <physicianID>, FirstName: <name>, Last name: <name>, Age: <age>"
                String[] parts = selectedPhysicianInfo.split(",");
                if (parts.length > 0) {
                    // Further splitting the first part to extract the ID value
                    String idPart = parts[0].split(":")[1].trim();
                    int physicianID = Integer.parseInt(idPart);
                    
                    populatePhysiciansComboBox();

                    return physicianID; // Return the successfully extracted and used physician ID
                }
            } catch (NumberFormatException e) {
                // Handle parsing errors gracefully
                System.err.println("Error parsing physician ID: " + e.getMessage());
            }
        }
        // Return a value indicating failure to extract a valid physician ID
        return -1;
    }
    private void returnToDashboard() {
        dispose();
        EventQueue.invokeLater(() -> {
            
            NurseGUI nurseGUI = new NurseGUI(nurse, hospital);
            nurseGUI.setVisible(true);
        });
    }
}
