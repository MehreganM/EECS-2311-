package Hospital;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;

import Hospital.DatabaseHelper;
import javax.swing.*;

public class PatientGUI extends JFrame {
	
	private Hospital hospital;
	private DatabaseHelper database;
	private DatabaseOps dataOps;
	private Patient patient;
	
	private JTextArea patientInfoArea;
	private JButton DisplayPatients, RecordVitals, RetrieveVitals, Labs_Meds;
	
	public PatientGUI(Hospital hospital) {
		this.hospital = hospital;
		}
	
	public PatientGUI (){
		
		  
		// Create the database 
    	DBSetup.ensureAllTablesExist();
    	

		database = new DatabaseHelper();
		dataOps = new DatabaseOps();
		patient = new Patient();
		
		setTitle("Patient Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);

		// Create JTextArea to display patient info
		patientInfoArea = new JTextArea(10,40);
		patientInfoArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(patientInfoArea);
		
		
		// Create the buttons objects
		RecordVitals = new JButton("Record Vital Signs");
		RetrieveVitals = new JButton("Retrieve Vital Signs");
		Labs_Meds = new JButton("View Labs and Meds");
		DisplayPatients	= new JButton("Display All Patients");
		
		// Display all the patients
		DisplayPatients.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				patientInfoArea.setText("");
				patientInfoArea.append(dataOps.getAllPatients());
			}
		});
		
		// Display patient by their ID 
		JButton getPatientByID = new JButton("Get patient by their ID");
		getPatientByID.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int patientId = Integer.parseInt(JOptionPane.showInputDialog("Enter patient ID:"));
				patientInfoArea.setText("");
				patientInfoArea.append(dataOps.getPatientById(patientId));				
			}
		});
		
		// Display patient by their name
		JButton getPatientByName = new JButton("Get patient by their Name");
		getPatientByName.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter patient name:");
				patientInfoArea.setText("");
				patientInfoArea.append(dataOps.searchPatientsByName(name));				
			}
		});

	
		// Create a button to record vital signs
		RecordVitals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VitalSignsGUI NewVitals = new VitalSignsGUI();
				NewVitals.setVisible(true);
			}
		});
//		RecordVitals.setEnabled(false); // INITIALLY disabled
		
		// Create a button to retrieve vital signs
		RetrieveVitals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VitalSignsDisplayGUI RetrieveVitals = new VitalSignsDisplayGUI(database);
				RetrieveVitals.setVisible(true);
			}
		});		
//		RetrieveVitals.setEnabled(false); // initially disabled
		
		Labs_Meds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PatientInformationGUI PatientInfo = new PatientInformationGUI(hospital);
				PatientInfo.setVisible(true);
				
			}
		});
		
		// Create a panel to hold the buttons
		JPanel mainPanel = new JPanel();
		JPanel panel = new JPanel();
		mainPanel.add(DisplayPatients);
		mainPanel.add(getPatientByName);
		mainPanel.add(getPatientByID);
		panel.add(RecordVitals);
		panel.add(RetrieveVitals);
		panel.add(Labs_Meds);
		add(mainPanel);
		add(mainPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	PatientGUI panel = new PatientGUI();
            panel.setVisible(true);
        });
    }
}
