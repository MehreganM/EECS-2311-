package Hospital;

import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NursePatientAddFrame extends JFrame {
    Nurse nurse; // The nurse to which patients will be added
    DatabaseOps databaseOps = new DatabaseOps();
    private JTextField firstNameField, lastNameField, ageField, genderField, addressField;
    private JButton addButton, returnButton; // Added returnButton
  //  private JTextArea patientDetailsArea;
 //   private JButton extractButton;
    
    public NursePatientAddFrame(Nurse nurse) {
        super("Add Patient to Nurse");
        this.nurse = nurse;// Initialize a Nurse object
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        // Create text fields
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        ageField = new JTextField(20);
        genderField = new JTextField(20);
        addressField = new JTextField(20);
    //    patientDetailsArea = new JTextArea(10, 30);
    //    patientDetailsArea.setEditable(false);
    //    JScrollPane scrollPane = new JScrollPane(patientDetailsArea);
        
        
        // Create and configure the add button
        addButton = new JButton("Add Patient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatientToNurse();
            }
        });
        
        returnButton = new JButton("Return to Dashboard");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToDashboard();
            }
        });
        
     //   extractButton = new JButton("Extract Patient Details");
     //   extractButton.addActionListener(new ActionListener() {
 //           @Override
     //       public void actionPerformed(ActionEvent e) {
    //            extractAndDisplayPatientDetails();
     //       }
     //   });
        
        // Add components to the frame
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
        add(addButton);
        add(returnButton);
    //    add(scrollPane); // Add scroll pane instead of text area directly
    //    add(extractButton);
        
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void addPatientToNurse() {
        // Extract data from fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String gender = genderField.getText();
        String address = addressField.getText();
        
        // Create a new Patient and add to the Nurse
        Patient newPatient = new Patient(firstName, lastName, age, gender, address);
        newPatient.setAssignedNurse(nurse);
        boolean added = nurse.addPatient(newPatient);
		databaseOps.addPatient(newPatient);
        
        if (added) {
            JOptionPane.showMessageDialog(this, "Patient added successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add patient. Nurse's list may be full.");
        }
    }
    
    private void returnToDashboard() {
        this.dispose(); // Close the current frame
        EventQueue.invokeLater(() -> {
            NurseGUI nurseGUI = new NurseGUI(nurse); // Re-open NurseGUI with the same nurse object
            nurseGUI.setVisible(true);
        });
    }
    
  /*  private void extractAndDisplayPatientDetails() {
        List<Patient> extractedPatients = nurse.extractPatientDetail();
        StringBuilder detailsBuilder = new StringBuilder();

        for (Patient patient : extractedPatients) {
            // Assuming Patient class has getters for its fields
            String patientInfo = String.format("ID: %d, First Name: %s, Last Name: %s, Age: %d, Gender: %s, Address: %s\n",
                    patient.getPatientID(), patient.getFName(), patient.getLName(),
                    patient.getAge(), patient.getGender(), patient.getAddress());
            detailsBuilder.append(patientInfo);
        }

        patientDetailsArea.setText(detailsBuilder.toString()); // Display extracted patient details in text area
    } */
    
}
