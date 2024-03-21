package Hospital;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;

import org.junit.jupiter.api.io.TempDir;

/**
 * This the Vital Signs GUI that stores the vital signs to the database.
 * The purpose is to record and maintain the vital signs entered by the nurse.
 * @ author Amira Mohamed
 */
public class VitalSignsGUI extends JFrame{
	
	DatabaseHelper database;
	
	// GUI components for the Vital Signs
	private JTextField temperatureField; 
	private JTextField systolicPressureField;
	private JTextField diastolicPressureField; 
	private JTextField heartRateField;
	private JTextField respiratoryRateField; 	
	
	// GUI components for patient information
	private JTextField patientNameField;
	private JTextField patientIdField;	
	
	
	public VitalSignsGUI() {
		database = new DatabaseHelper();
		
		setTitle("Vital Signs Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		
		JPanel mainPanel = new JPanel();
	
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		 // Patient ID Field
        mainPanel.add(new JLabel("Patient ID : "));
        patientIdField = new JTextField(10);
        mainPanel.add(patientIdField);  
        
        // Temperature Field
        mainPanel.add(new JLabel("Temperature (°C): "));
        temperatureField = new JTextField(10);
        mainPanel.add(temperatureField);
        
        // Systolic Pressure Field
        mainPanel.add(new JLabel("Systolic pressure (mmHg): "));
        systolicPressureField = new JTextField(10);
        mainPanel.add(systolicPressureField);
        
        // diastolic Pressure Field
        mainPanel.add(new JLabel("Diastolic pressure (mmHg): "));
        diastolicPressureField = new JTextField(10);
        mainPanel.add(diastolicPressureField);
        
        // Heart Rate Field
        mainPanel.add(new JLabel("Heart Rate (bpm): "));
        heartRateField = new JTextField(10);
        mainPanel.add(heartRateField);
        
        
        // Respiratory Rate Field
        mainPanel.add(new JLabel("Respiratory Rate (breaths/min): "));
        respiratoryRateField = new JTextField(10);
        mainPanel.add(respiratoryRateField);
        
        JButton recordButton = new JButton("Record Vital Signs");
        
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	validateInput();
            }
        });
        

        mainPanel.add(recordButton);
        
        mainPanel.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		validateInput();	
        	}
		});

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);

	}
	
	private void validateInput() {
		try {
			int PatientID = Integer.parseInt(patientIdField.getText());
			double temperature = Double.parseDouble(temperatureField.getText());
			int systolicPressure = Integer.parseInt(systolicPressureField.getText());
			int diastolicPressure = Integer.parseInt(diastolicPressureField.getText());
			int heartRate = Integer.parseInt(heartRateField.getText()); 		
			int respiratoryRate = Integer.parseInt(respiratoryRateField.getText());
			
			// Validate each input field 
			if (validatePatientID(PatientID)  && validateTemperatureInput(temperature) && validatePressureInput(systolicPressure, diastolicPressure) && 
					validateHeartRateInput(heartRate) && validateRespiratoryRateInput(respiratoryRate)){
				recordVitalSigns(PatientID, temperature, systolicPressure, diastolicPressure, heartRate, respiratoryRate);
			}
			
		}
		catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this, "Please enter valid numeric claues for all field", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private boolean validatePatientID(int patientID) {
		if (patientID < 0) {
			System.out.println("Invalid Patient ID");
			JOptionPane.showMessageDialog(VitalSignsGUI.this, "Please enter valid value for Patient ID (greater than 0)", "Invalid Patient ID", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validateTemperatureInput(double temp) {
		if (temp < 30 || temp > 45) {
			System.out.println("Invalid Temperature");
			JOptionPane.showMessageDialog(VitalSignsGUI.this, "Please enter valid values for temperature (30-45 °C)", "Invalid Temperature", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
		
	}

	private boolean validatePressureInput(int systolicPressure, int diastolicPressure) {
		if (systolicPressure < 0 || systolicPressure > 200 || diastolicPressure < 0 || diastolicPressure > 200) {
			System.out.println("Invalid Pressure");
			JOptionPane.showMessageDialog(VitalSignsGUI.this, "Please enter valid values for systolic (0-200 mmHg) and diastolic (0-200 mmHg) pressures", "Invalid Pressure", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean validateHeartRateInput(int heartRate) {
		if (heartRate < 0 || heartRate > 200) {
			System.out.println("Invalid Heart Rate");
			JOptionPane.showMessageDialog(VitalSignsGUI.this, "Please enter valid values for Heart rate (0-200 bpm)", "Invalid Heart Rate", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean validateRespiratoryRateInput(int respiratoryRate) {
		if (respiratoryRate < 10 || respiratoryRate > 60) {
			System.out.println("Invalid Respiratory Rate");
			JOptionPane.showMessageDialog(VitalSignsGUI.this, "Please enter valid values for Respiratory rate (10-60 bpm)", "Invalid Respiratory Rate", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void recordVitalSigns(int PatientID, double temperature, int systolicPressure, int diastolicPressure, int heartRate, int respiratoryRate) {
		
		database.storeVitalSigns(PatientID, temperature, systolicPressure, diastolicPressure, heartRate, respiratoryRate);
	    JOptionPane.showMessageDialog(this, "Vital signs recorded successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Vital signs recorded: ");
		System.out.println("Temperature: " + temperature + "°C");
		System.out.println("Systolic Pressure: " + systolicPressure + " mmHg");
		System.out.println("Diastolic Pressure: " + diastolicPressure + " mmHg");
		System.out.println("Blood Pressure: " + + systolicPressure + "/" + diastolicPressure + " mmHg");
		System.out.println("Heart Rate: " + heartRate + " beats/minute");
		System.out.println("Respiartory Rate: " + respiratoryRate + " breaths/min");
		
				
		}
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	VitalSignsGUI panel = new VitalSignsGUI();
	            panel.setVisible(true);
	        });
	    }
	
	
}
