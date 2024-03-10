package Hospital;
/**
 * @author Harrish
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NurseGUI extends JFrame {
	    private JButton addPatientButton;
	    private JButton viewPatientsButton;
	    private JLabel familyDoctorLabel;
	    private JTextField familyDoctorField;
	    private JButton assignFamilyDoctorButton;
	    Nurse nurse; // Nurse who logged in
	    
	    public NurseGUI(Nurse nurse) {
	        this.nurse = nurse;
	        initializeUI();
	    }

	    private void initializeUI() {
	        setTitle("Nurse Dashboard");
	        setSize(300, 200); 
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new FlowLayout());

	        familyDoctorLabel = new JLabel("Family Doctor:");
	        familyDoctorField = new JTextField(20);
	        familyDoctorField.setEditable(false);

	        addPatientButton = new JButton("Add Patient");
	        viewPatientsButton = new JButton("View Patients");
	        assignFamilyDoctorButton = new JButton("Assign/Find Family Doctor");

	        addPatientButton.addActionListener(this::openNursePatientFrame);
	        viewPatientsButton.addActionListener(this::extractAndDisplayPatientDetails);
	        assignFamilyDoctorButton.addActionListener(this::assignOrFindFamilyDoctor);

	        add(addPatientButton);
	        add(viewPatientsButton);
	        add(familyDoctorLabel);
	        add(familyDoctorField);
	        add(assignFamilyDoctorButton);

	        JButton submitButton = new JButton("Submit Form");
	        submitButton.addActionListener(this::submitConsentForm);
	        add(submitButton);
	    }

	    private void assignOrFindFamilyDoctor(ActionEvent e) {
	        String firstName = JOptionPane.showInputDialog(NurseGUI.this, "Enter Patient's First Name:");
	        String lastName = JOptionPane.showInputDialog(NurseGUI.this, "Enter Patient's Last Name:");
	        if (firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty()) {
	            Patient selectedPatient = nurse.getPatientByName(firstName, lastName);
	            if (selectedPatient != null) {
	                FamilyDoctor familyDoctor = selectedPatient.getFamilyDoctor();
	                if (familyDoctor == null) {
	                  
	                    familyDoctor = new FamilyDoctor("Default Doctor", "General", null, "default@hospital.com", "000-000-0000");
	              	  
	                    selectedPatient.setFamilyDoctor(familyDoctor);
	                }
	                familyDoctorField.setText(familyDoctor.getName());
	            } else {
	                JOptionPane.showMessageDialog(NurseGUI.this, "Patient not found.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(NurseGUI.this, "Invalid input. Please enter both first and last names.", "Error", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void submitConsentForm(ActionEvent e) {
	        int confirmed = JOptionPane.showConfirmDialog(NurseGUI.this, 
	            "Do you confirm the submission of the consent form?", "Confirm",
	            JOptionPane.YES_NO_OPTION);

	        if (confirmed == JOptionPane.YES_OPTION) {
	          JOptionPane.showMessageDialog(NurseGUI.this, "Consent form submitted successfully");
	        }
	    }

	    private void openNursePatientFrame(ActionEvent e) {
	        NursePatientAddFrame addPatientFrame = new NursePatientAddFrame(nurse);
	        addPatientFrame.setVisible(true);
	        NurseGUI.this.setVisible(false);
	    }

	    private void extractAndDisplayPatientDetails(ActionEvent e) {
	        List<Patient> patientDetails = nurse.extractPatientDetail();
	        if (patientDetails.isEmpty()) {
	            JOptionPane.showMessageDialog(NurseGUI.this, "No patients assigned!", "Patient Details", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Simplified display logic
	            StringBuilder details = new StringBuilder();
	            for (Patient patient : patientDetails) {
	                details.append(patient.toString()).append("\n");
	            }
	            JOptionPane.showMessageDialog(NurseGUI.this, details.toString(), "Patient Details", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
