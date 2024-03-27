package Hospital;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DischargePatientGUI extends JFrame {
	JComboBox<String> patientsList;
	JButton dischargeButton;
	Hospital hospital; 
	DatabaseOps databaseOps = new DatabaseOps();
	
	public DischargePatientGUI(Hospital hospital) throws NoSpaceException {
		this.hospital = hospital;
		hospital.InitializeEmployees();
		setTitle("Discharge Patient");
		setSize(700, 300);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		
		// Creating the button and the ComboBox
		patientsList = new JComboBox<String>();
		populatePatientsComboBox();
		
		dischargeButton = new JButton("Discharge Selected Patient");
		dischargeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removePatient();
			}
		});
		
		add(patientsList);
		add(dischargeButton);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void populatePatientsComboBox() {
		patientsList.removeAllItems();
		ArrayList<Patient> patients = databaseOps.getAllPatients1();
//		System.out.println(patients);
//		System.out.println("Running2");	
//		String patientsInfo = databaseOps.getAllPatients();
		for (Patient patient : patients) {
//			String patientsInfo = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d, Address: %s, Gender: %s ",

			String patientsInfo = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d, Address: %s, Gender: %s ",
					patient.getPatientID(),
					patient.getFName(),
					patient.getLName(),
					patient.getAge(),
					patient.getAddress(),
					patient.getGender()); 
			patientsList.addItem(patientsInfo);
		//	System.out.println(patientsInfo);
		}
		}
	
	
		public static void main(String[] args) {
	        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));

	        SwingUtilities.invokeLater(() -> {
	        	DischargePatientGUI panel;
				try {
					panel = new DischargePatientGUI(hospital);
					panel.setVisible(true);
				} catch (NoSpaceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	        });
	    
		
	}
	
	
	
	private void removePatient(){
		String selectedPatient = (String) patientsList.getSelectedItem();
		if(selectedPatient != null) {
			String[] parts = selectedPatient.split(", ");
			if(parts.length >= 2) {
				
				int id = Integer.parseInt(parts[0].split(": ")[1]);
				String firstname = parts[1].split(": ")[1];
				String lastname = parts[2].split(": ")[1];
				int age = Integer.parseInt(parts[3].split(": ")[1]);
				databaseOps.deletePatient(firstname, lastname, age);
				
				populatePatientsComboBox();
				JOptionPane.showMessageDialog(DischargePatientGUI.this, "Patient discharged successfully.");
				System.out.println("Patient discharged successfully.");

			}
			else {
	            JOptionPane.showMessageDialog(DischargePatientGUI.this, "Invalid format.");

			}
			
		}
		else {
			dischargeButton.setEnabled(false);
            JOptionPane.showMessageDialog(DischargePatientGUI.this, "No Patient to discharged.");
			System.out.println("No patients in the hospital");
		}
		
		
	}
	
	

}