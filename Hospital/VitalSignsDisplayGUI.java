package Hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VitalSignsDisplayGUI extends JFrame{
	
	private JLabel temperatureLabel; 
	private JLabel systolicPressureLabel; 
	private JLabel diastolicPressureLabel; 
	private JLabel heartRateLabel; 
	private JLabel respiratoryRateLabel; 
	
	public VitalSignsDisplayGUI(DatabaseHelper databaseHelper) {
		
		setTitle("Vital Signs Display");
		setSize(300,  300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		JLabel patientIDLabel = new JLabel("Enter Patient ID: ");
		JTextField patientIDField = new JTextField(10);
		JButton submitButton = new JButton("Submit");
		
		

		
		
	//	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
//        String patientIdInput = JOptionPane.showInputDialog(this, "Enter Patient ID:");
//        if(patientIdInput == null) {
//           // JOptionPane.showMessageDialog(this, "Please enter a valid Patient ID", "Error", JOptionPane.ERROR_MESSAGE);
//        	dispose();
//        	return; 
//        }
		
		submitButton.addActionListener(e -> {
			String patientIdInput = patientIDField.getText();
			try {
        	int PatientID = Integer.parseInt(patientIdInput);
        	
			VitalSigns vitalsigns = databaseHelper.retrieveVitalSigns(PatientID);
			
			if(vitalsigns != null) {
				temperatureLabel = new JLabel("Temperature: " + vitalsigns.getTemperature() + "°C");
				systolicPressureLabel = new JLabel("Systolic Pressure: " + vitalsigns.getSystolicPressure() + " mmHg");
				diastolicPressureLabel = new JLabel("Diastolic Pressure: " + vitalsigns.getDiastolicPressure() + " mmHg");
				heartRateLabel = new JLabel("Heart Rate: " + vitalsigns.getHeartRate() + " bpm\n");
				respiratoryRateLabel = new JLabel("Respiratory Rate: " + vitalsigns.getRespiratoryRate() + " breaths/min");
				
				panel.add(temperatureLabel);
				panel.add(systolicPressureLabel);
				panel.add(diastolicPressureLabel);
				panel.add(heartRateLabel);
				panel.add(respiratoryRateLabel);
				
				panel.revalidate();
	            panel.repaint();
			
			}
			else {
				JLabel NoVitals = new JLabel("No vital signs recorded for this patient");
				panel.add(NoVitals);
				
				panel.revalidate();
	            panel.repaint();
			}


        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid patient ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
           return;
		}
		});
		
		panel.add(patientIDLabel);
		panel.add(patientIDField);
		panel.add(submitButton);
	
		add(panel);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage:
            DatabaseHelper databaseHelper = new DatabaseHelper();
            new VitalSignsDisplayGUI(databaseHelper);
        });
	}
	
	
}
