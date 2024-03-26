package Hospital;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PatientGUI extends JFrame {

    private Hospital hospital;
    private DatabaseHelper database;
    private DatabaseOps dataOps;
    private Patient patient;

    private JTextArea patientInfoArea;
    private JButton DisplayPatients, RecordVitals, RetrieveVitals, Labs_Meds, dischargeButton;

    public PatientGUI(Hospital hospital) {
        this.hospital = hospital;

        // Create the database 
        DBSetup.ensureAllTablesExist();

        database = new DatabaseHelper();
        dataOps = new DatabaseOps();
        patient = new Patient();

        setTitle("Patient Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); // Increased size

        // Create JTextArea to display patient info
        patientInfoArea = new JTextArea(10,40);
        patientInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(patientInfoArea);

        // Create the buttons objects
        RecordVitals = new JButton("Record Vital Signs");
        RetrieveVitals = new JButton("Retrieve Vital Signs");
        Labs_Meds = new JButton("View Lab results");
        DisplayPatients = new JButton("Display All Patients");
	dischargeButton = new JButton("Discharge Patient");


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

        JButton requestLab = new JButton("Request Lab");
        requestLab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather necessary information for lab test request
                String patientIdInput = JOptionPane.showInputDialog("Enter patient ID:");
                
                // Check if user canceled the input dialog
                if (patientIdInput != null) {
                    try {
                        int patientId = Integer.parseInt(patientIdInput);
                        System.out.println("Patient ID entered: " + patientId); // Debugging print statement
                        
                        String testType = JOptionPane.showInputDialog("Enter test type:");
                        String description = JOptionPane.showInputDialog("Enter test description:");

                        // Retrieve patient information by ID
                        Patient patient = dataOps.getPatientByIds(patientId);
                        patient.setPatientID(patientId);
                        
                        // Check if patient exists
                        if (patient != null) {
                            System.out.println("Patient found: " + patient.getName()); // Debugging print statement
                            Laboratory lab = new Laboratory();
                            // Create lab test object
                            labTest labTestRequest = new labTest(patient, testType);

                            // Call addTestRequest method from Laboratory class
                            
                            boolean success = lab.addTestRequest(labTestRequest, description); // Passing description

                            // Display message based on success of operation
                            if (success) {
                                JOptionPane.showMessageDialog(null, "Lab test request added successfully.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to add lab test request.");
                            }
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input for patient ID. Please enter a valid integer.");
                        ex.printStackTrace(); // Print the stack trace for debugging
                    }
                }
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
        // Create a button to retrieve vital signs
        RetrieveVitals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VitalSignsDisplayGUI RetrieveVitals = new VitalSignsDisplayGUI(database);
                RetrieveVitals.setVisible(true);
            }
        });        
        Labs_Meds.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientInformationGUI PatientInfo = new PatientInformationGUI();
                PatientInfo.setVisible(true);
            }
        });

        // Discharge button to discharge the patient from the hospital
		dischargeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DischargePatientGUI dischargePatient = new DischargePatientGUI(hospital);
				dischargePatient.setVisible(true);
			}
		});

        JPanel mainPanel = new JPanel();
        JPanel panel = new JPanel();
        mainPanel.add(DisplayPatients);
        mainPanel.add(getPatientByName);
        mainPanel.add(getPatientByID);
        panel.add(RecordVitals);
        panel.add(RetrieveVitals);
        panel.add(Labs_Meds);
        panel.add(dischargeButton);
        panel.add(requestLab);
        add(mainPanel);
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

    }

   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hospital hospital = new Hospital(null); 
                PatientGUI patientGUI = new PatientGUI(hospital);
                patientGUI.setVisible(true);
            }
        });
    }
}
