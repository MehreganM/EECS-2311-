package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescriptionGUI extends JFrame {
	 private JButton returnToDashboardButton;
    private JTextField patientIdField;
    private JTextField physicianNameField;
    private JTextField medicationNameField;
    private JTextField dosageField;
    private JTextArea instructionsArea;
    private JButton submitButton;
    private Physician loggedInPhysician;
    private DatabaseOps dbOps;
    Hospital hospital;
 
  
 

    public PrescriptionGUI(Physician loggedInPhysician, Hospital hospital) {
        this.loggedInPhysician = loggedInPhysician;
        this.hospital = hospital;
        dbOps = new DatabaseOps();
        setTitle("Prescribe Medication");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        add(patientIdField);

        add(new JLabel("Physician Name:"));
        physicianNameField = new JTextField();
        add(physicianNameField);

        add(new JLabel("Medication Name:"));
        medicationNameField = new JTextField();
        add(medicationNameField);

        add(new JLabel("Dosage:"));
        dosageField = new JTextField();
        add(dosageField);

        add(new JLabel("Instructions:"));
        instructionsArea = new JTextArea();
        add(new JScrollPane(instructionsArea));

        submitButton = new JButton("Submit Prescription");
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPrescription();
            }
        });
        
        returnToDashboardButton = new JButton("Return to Dashboard");
        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionGUI.this.dispose(); 
                PatientGUI patientGUI = new PatientGUI(loggedInPhysician, hospital);
                patientGUI.setVisible(true); 
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.add(returnToDashboardButton, BorderLayout.SOUTH);
    }


    private void submitPrescription() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText());
            String physicianName = physicianNameField.getText();
            String medicationName = medicationNameField.getText();
            String dosage = dosageField.getText();
            String instructions = instructionsArea.getText();

            Prescription prescription = new Prescription(patientId, physicianName, medicationName, dosage, instructions);
            
            boolean success = savePrescription(prescription);
            if (success) {
                JOptionPane.showMessageDialog(this, "Prescription saved successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save prescription.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public boolean savePrescription(Prescription prescription) {
        String sql = "INSERT INTO prescriptions (patient_id, physician_name, medication_name, dosage, instructions) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, prescription.getPatientId());
            pstmt.setString(2, prescription.getPhysicianName());
            pstmt.setString(3, prescription.getMedicationName());
            pstmt.setString(4, prescription.getDosage());
            pstmt.setString(5, prescription.getInstructions());
            
            int affectedRows = pstmt.executeUpdate();
            
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}

