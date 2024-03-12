package Hospital.src.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PatientInformationGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField searchField;
    private JButton searchButton, viewPrescriptionsButton, viewLabsButton, dischargeButton;
    private JTextArea resultArea;
    private JComboBox<String> searchTypeComboBox;
    private Hospital hospital;
    private Patient currentPatient;

    public PatientInformationGUI(Hospital hospital) {
        this.hospital = hospital;
        this.currentPatient = null;
        infoRetrivalUI();
    }

    private void infoRetrivalUI() {
        setTitle("Patient Information Retrieval");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        viewPrescriptionsButton = new JButton("View Prescriptions");
        viewLabsButton = new JButton("View Labs");
	dischargeButton = new JButton("Discharge Patient");
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);

        String[] searchTypes = {"By ID", "By Name"};
        searchTypeComboBox = new JComboBox<>(searchTypes);

        viewPrescriptionsButton.setEnabled(false);
        viewLabsButton.setEnabled(false);
	dischargeButton.setEnabled(false);

        panel.add(new JLabel("Search Type:"));
        panel.add(searchTypeComboBox);
        panel.add(new JLabel("Enter Search Query:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JScrollPane(resultArea));
        panel.add(viewPrescriptionsButton);
        panel.add(viewLabsButton);
	panel.add(dischargeButton);

        searchButton.addActionListener(e -> {
            String searchQuery = searchField.getText();
            String searchType = (String) searchTypeComboBox.getSelectedItem();
            if ("By ID".equals(searchType)) {
                searchById(searchQuery);
            } else if ("By Name".equals(searchType)) {
                searchByName(searchQuery);
            }
        });

        viewPrescriptionsButton.addActionListener(e -> {
            if (currentPatient != null) {
                ArrayList<String> medications = currentPatient.returnMedication();
                StringBuilder medicationsInfo = new StringBuilder("\nPrescriptions:");
                if (medications.isEmpty()) {
                    medicationsInfo.append(" [No medications listed]");
                } else {
                    for (String medication : medications) {
                        medicationsInfo.append("\n- ").append(medication);
                    }
                }
                resultArea.append(medicationsInfo.toString());
            }
        });

        viewLabsButton.addActionListener(e -> {
            if (currentPatient != null) {
                String labResults = currentPatient.labs;
                if (labResults == null) {
                    resultArea.append("\nLabs: [No lab tests found]");
                } else {
                    resultArea.append("\nLabs: \n-" + labResults);
                }
            }
        });
	    
	dischargeButton.addActionListener(e -> {
        if (hospital.dischargePatient(currentPatient) == true) {
        	resultArea.append("\n Patient is discharged");
        	resultArea.append("\n Sending a summary of the visit to the family doctor");
        	
        }}
        );
        add(panel);
    }

    private void searchById(String patientID) {
        try {
            Patient patient = hospital.searchPatient(Integer.parseInt(patientID));
            if (patient != null) {
                currentPatient = patient;
                displayPatientInfo(patient);
            } else {
                resultArea.setText("No patient found with ID: " + patientID);
                disableDetailButtons();
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid ID format.");
            disableDetailButtons();
        }
    }

    private void searchByName(String name) {
        Patient patient = hospital.searchPatientByName(name);
        if (patient != null) {
            currentPatient = patient;
            displayPatientInfo(patient);
        } else {
            resultArea.setText("No patient found with name: " + name);
            disableDetailButtons();
        }
    }

    private void displayPatientInfo(Patient patient) {
        String patientInfo = (patient.getPatientID() + " " +
                patient.getName() + ", " + patient.getAge() + ", " + patient.getGender());
        resultArea.setText(patientInfo);
        enableDetailButtons();
    }

    private void enableDetailButtons() {
        viewPrescriptionsButton.setEnabled(true);
        viewLabsButton.setEnabled(true);
        dischargeButton.setEnabled(true);

    }

    private void disableDetailButtons() {
        viewPrescriptionsButton.setEnabled(false);
        viewLabsButton.setEnabled(false);
	dischargeButton.setEnabled(false);

    }

  

    public static void main(String[] args) {
        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
        
        
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        
        Physician physician = new Physician("DR.AL", "kp", 35, "Male", "202 Oak St");
        physician.setSpecialty("Immunology");
        hospital.hirePhysician(physician);
        
        
        
        Patient patient = new Patient("John", "Smith", 30, "Male", "123 Main St");
        Patient patient2 = new Patient("Ali", "Bakhshi", 30, "Male", "123 Main St");
        Patient patient3 = new Patient("Sarah", "Lance", 30, "female", "123 Main St");
        Patient patient4 = new Patient("Kim", "k", 30, "Female", "123 Main St");
  
        
        try {
        	hospital.admitPatient(patient);
        	hospital.admitPatient(patient2);
        	hospital.admitPatient(patient3);
        	hospital.admitPatient(patient4);
		} catch (NoSpaceException e) {
			e.printStackTrace();
		}
       
        
        Laboratory lab = new Laboratory();
        labTest test1 = new labTest(patient,"blood");
        physician.LabReq(lab, test1);
        test1.addResult("good");
        
        
        physician.prescripe(patient, "advil");
        physician.prescripe(patient, "codeine");
        physician.updateLab(patient, lab);
        
    

        SwingUtilities.invokeLater(() -> {
            PatientInformationGUI gui = new PatientInformationGUI(hospital);
            gui.setVisible(true);
        });
    }
}
