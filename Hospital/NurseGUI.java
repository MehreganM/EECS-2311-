package Hospital;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class NurseGUI extends JFrame {
	    private JButton addPatientButton;
	    private JButton viewPatientsButton;
	    private JLabel familyDoctorLabel;
	    private JTextField familyDoctorField;
	    private JButton assignFamilyDoctorButton;
	    Nurse nurse; // Nurse who logged in
	    Hospital hospital;
	    
	    
	    public NurseGUI(Nurse nurse, Hospital hospital) {
	        this.nurse = nurse;
		this.hospital = hospital;
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

	        addPatientButton.addActionListener(e -> {
				try {
					openNursePatientFrame(e);
				} catch (NoSpaceException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			});
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
	        String searchQuery = JOptionPane.showInputDialog(NurseGUI.this, "Enter Doctor's name, email, or phone number:");
	        List<FamilyDoctor> foundDoctors = nurse.searchFamilyDoctors(searchQuery);

	        if (!foundDoctors.isEmpty()) {
	            String[] options = new String[foundDoctors.size()];
	            for (int i = 0; i < foundDoctors.size(); i++) {
	                options[i] = foundDoctors.get(i).getName() + " - " + foundDoctors.get(i).getEmail();
	            }
	            int selected = JOptionPane.showOptionDialog(null, "Select a Family Doctor", 
	                    "Choose Doctor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
	                    null, options, options[0]);

	            if (selected >= 0) {
	                FamilyDoctor selectedDoctor = foundDoctors.get(selected);
	                familyDoctorField.setText(selectedDoctor.getName());
	            }
	        } else {
	            JOptionPane.showMessageDialog(NurseGUI.this, "No matching family doctors found. Assigning default.", "Info", JOptionPane.INFORMATION_MESSAGE);
	            // Proceed to assign default doctor or handle differently
	        }
	    }

/**
 * Parmoun Khalkhali -> Consent form user story 
 */
	    private void submitConsentForm(ActionEvent e) {
	        String patientIDStr = JOptionPane.showInputDialog(NurseGUI.this, "Enter Patient ID:", "Patient ID", JOptionPane.QUESTION_MESSAGE);
	        try {
	            int patientID = Integer.parseInt(patientIDStr);
	            int confirmed = JOptionPane.showConfirmDialog(NurseGUI.this, 
	                "Do you confirm the submission of the consent form for Patient ID: " + patientID + "?", "Confirm",
	                JOptionPane.YES_NO_OPTION);

	            if (confirmed == JOptionPane.YES_OPTION) {
	                DatabaseHelper dbHelper = new DatabaseHelper(); 
	                dbHelper.updateConsentFormStatus(patientID, true); 
	                JOptionPane.showMessageDialog(NurseGUI.this, "Consent form submitted successfully for Patient ID: " + patientID);
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(NurseGUI.this, "Invalid Patient ID entered", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(NurseGUI.this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

	    private void openNursePatientFrame(ActionEvent e) throws NoSpaceException {
	        NursePatientAddFrame addPatientFrame = new NursePatientAddFrame(nurse, hospital);
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



