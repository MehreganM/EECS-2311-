package Hospital;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * This class is to retrieve and display the vital signs of the patient. 
 * @author Amira Mohamed
 */
public class VitalSignsDisplayGUI extends JFrame{
	
	private JLabel temperatureLabel, systolicPressureLabel, diastolicPressureLabel, heartRateLabel, respiratoryRateLabel; 
	

	public VitalSignsDisplayGUI(DatabaseHelper databaseHelper) {
		DatabaseOps dataOps = new DatabaseOps();
		
		// Set up of the display
		setTitle("Vital Signs Display");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// inputPanel to enter patient id and submit button
		JPanel inputPanel = new JPanel();
		JLabel patientIDLabel = new JLabel("Enter Patient ID: ");
		JTextField patientIDField = new JTextField(10);
		JButton submitButton = new JButton("Submit");

		//panel to display the vital signs
		JPanel displayPanel = new JPanel(new GridLayout(0, 1));

		// Functionality of the submit button
		submitButton.addActionListener(e -> {
			// Clear the panel from any previous information
			displayPanel.removeAll();
			
			// get and parse the value entered for the patient id
			String patientIdInput = patientIDField.getText();
			try {
				
        	int PatientID = Integer.parseInt(patientIdInput);
        	
        	if(dataOps.getPatientByIds(PatientID) == null) {
    			System.out.println("No Patient with this ID");
    			JOptionPane.showMessageDialog(VitalSignsDisplayGUI.this, "Please enter an existing Patient ID", "Invalid Patient ID", JOptionPane.ERROR_MESSAGE);
        	}
        	
        	else {
        	// Call the retrieveViatlSigns method in the databaseHelper to get the vital signs of the patient
			VitalSigns vitalsigns = databaseHelper.retrieveVitalSigns(PatientID);
			
			// If there are vital signs recorded for the patient, display it and print it in the console
			if(vitalsigns != null) {
				temperatureLabel = new JLabel("Temperature: " + vitalsigns.getTemperature() + "°C");
				systolicPressureLabel = new JLabel("Systolic Pressure: " + vitalsigns.getSystolicPressure() + " mmHg");
				diastolicPressureLabel = new JLabel("Diastolic Pressure: " + vitalsigns.getDiastolicPressure() + " mmHg");
				heartRateLabel = new JLabel("Heart Rate: " + vitalsigns.getHeartRate() + " beats/minute");
				respiratoryRateLabel = new JLabel("Respiratory Rate: " + vitalsigns.getRespiratoryRate() + " breaths/min");
				
				displayPanel.add(temperatureLabel);
				displayPanel.add(systolicPressureLabel);
				displayPanel.add(diastolicPressureLabel);
				displayPanel.add(heartRateLabel);
				displayPanel.add(respiratoryRateLabel);
				
				displayPanel.revalidate();
				displayPanel.repaint();
			
			}
			else {
				// If no vital signs recorded, print an informative message for the user
				JLabel NoVitals = new JLabel("No vital signs recorded for this patient");
				 NoVitals.setHorizontalAlignment(SwingConstants.CENTER);
				 displayPanel.add(NoVitals);
				
				 displayPanel.revalidate();
				 displayPanel.repaint();
				 }
			}
	
		// If this no/wrong input, throw an exception and display an informative message to the user
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid patient ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
		}
		});
		
		// Return button to close the window
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> {
            dispose(); // Close the window
        });
		
		// Add patientIDLabel, patientIDField, and submitButton to the inputPanel
		inputPanel.add(patientIDLabel);
		inputPanel.add(patientIDField);
		inputPanel.add(submitButton);	

		// Add the inputPanel and panel to the frame
		add(inputPanel, BorderLayout.NORTH);
		add(displayPanel, BorderLayout.CENTER);
		add(returnButton, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}

