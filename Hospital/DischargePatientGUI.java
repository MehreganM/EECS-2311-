package Hospital;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class is to discharge patients from the hospital
 * @author Amira Mohamed
 */
public class DischargePatientGUI extends JFrame {
	
	// Declaring the variables of the class
	JComboBox<String> patientsList;
	JButton dischargeButton;
	Hospital hospital; 
	Physician physician;
	
	// DatabaseOps object to connect to database and its operations
	DatabaseOps databaseOps = new DatabaseOps();
	
	public DischargePatientGUI(Hospital hospital, Physician physician) throws NoSpaceException{
		// Initializing the hospital and the database
		this.hospital = hospital;
		this.physician = physician;
		hospital.InitializeEmployees();
		
		// Set up of the display
		setTitle("Discharge Patient");
		setSize(700, 300);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		// Creating the button and the ComboBox
		patientsList = new JComboBox<String>();
		populatePatientsComboBox();

		
		// Discharge button functionality to discharge patient from the hospital
		dischargeButton = new JButton("Discharge Selected Patient");
		dischargeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePatient();			
			}
		});
		
		// Return button to close the window
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            dispose(); // Close the window
        });
		
		// Adding the patient list, discharge button and return button to the panel
		add(patientsList);
		add(dischargeButton);
		add(returnButton);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);	
	}
	
	// This method is to fill the ComBox with the patient list 
	private void populatePatientsComboBox() {
	patientsList.removeAllItems(); // Remove all the patients
	
	// Go to database and extract all the patients and add them to the ComBox 
	// (to get the updated ones after the discharge process). 
	String patientsInfo = databaseOps.getPatientByPhysicianId2(databaseOps.getPhysicianIdByName(physician.getFirstName(), physician.getLastName()));
	if(!patientsInfo.isEmpty()) {
		String[] patientLines = patientsInfo.split("\n"); 
		
		// Iterate through all the patients in the database and add it to the comboBox
		for (String patient : patientLines) {
			 
			// Split the patients list to have each patient separately
			String parts [] = patient.split(",");
			
			// Parse the patient information retrieved from the database to use this information 
			int patientID = Integer.parseInt(parts[0].split(": ")[1]);
			String firstname = parts[1].split(": ")[1];
			String lastname = parts[2].split(": ")[1];
			int age = Integer.parseInt(parts[3].split(": ")[1]);
			String address = parts[4].split(": ")[1];
	        String gender = parts[5].split(": ")[1];
	 
	        // Adding patient information as a string and add it to the ComBox
	        String patientList = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d, Address: %s, Gender: %s ",
	        		patientID, firstname, lastname, age, address, gender );
	     //   System.out.println(patientList.toString()); // Debugging statement
	        patientsList.addItem(patientList);
			}
		}
		// If no more patients left, disable the discharge functionality and display informative message
		else {
			dischargeButton.setEnabled(false);
			JOptionPane.showMessageDialog(DischargePatientGUI.this, "No remaining patients in the hospital to discharge.");
			System.out.println("No remaining patients in the hospital to discharge.");
		}
}
		

	// This method is to remove the patient from the database
	private void removePatient() {
	    String selectedPatientInfo = (String) patientsList.getSelectedItem();

	  //  System.out.println("Selected patient: " + selectedPatientInfo); // Debugging statement
	   
	    // If patient is selected, extract their information and discharge the patient
	    if (selectedPatientInfo != null) {
	        // Extract the patient ID from the selected patient information string
	        String[] parts = selectedPatientInfo.split(", ");
	        if (parts.length >= 1) {
	            String idStr = parts[0].split(": ")[1];
	            	try {
	              int id = Integer.parseInt(idStr);
	              String firstname = parts[1].split(": ")[1];
	              String lastname = parts[2].split(": ")[1];
	              int age = Integer.parseInt(parts[3].split(": ")[1]);
	      
	              //System.out.println("Patient ID: " + id); // Debugging statement
	      
	              // Attempt to remove the patient from the database and display informative messages
	              databaseOps.deletePatient(firstname, lastname, age);
//	              if (databaseOps.deletePatientB(id)) {
	                  JOptionPane.showMessageDialog(DischargePatientGUI.this, "Patient discharged successfully.");
	                  System.out.println("Patient discharged successfully.");
	                //  Patient patient = databaseOps.getPatientByIds(id);
	              	populatePatientsComboBox(); // Refresh the combo box after successful removal
	                 // hospital.dischargePatient(patient);
	      
//	              } else {
//	                  JOptionPane.showMessageDialog(DischargePatientGUI.this, "Failed to discharge the patient. Please try again.");
//	              }
	          } catch (NumberFormatException e) {
	              JOptionPane.showMessageDialog(DischargePatientGUI.this, "Invalid patient ID format.");
	          }
	        } else {
	            JOptionPane.showMessageDialog(DischargePatientGUI.this, "Invalid patient information format.");
	        }
	    } else {
	        JOptionPane.showMessageDialog(DischargePatientGUI.this, "No patient selected for discharge.");
	        System.out.println("No patient is selected.");
	    }
	}
}

