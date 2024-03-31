package Hospital;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class is the patient portal page where you can display all the patients, get patients by their id, get patients 
 * by their name, record vital signs, retrieve vital signs, request labs, view lab results, and discharge patients
 * @author Amira Mohamed
 */
public class PatientGUI extends JFrame {
 
	// Declare local variables
    private Hospital hospital;
    private DatabaseHelper database;
    private DatabaseOps dataOps;
    private Patient patient;

    private JTextArea patientInfoArea;
    private JButton DisplayPatients, RecordVitals, RetrieveVitals, Labs_Meds, dischargeButton, backButton;

    protected DatabaseOps dbOps;
	protected DatabaseOps dbOps2;
	
	 // ... other attributes ...
    private Physician loggedInPhysician; 
    private Physician loggedInPhysician2;
    private JButton prescribeMedicationButton; 
    
    
    /**
     * @author Parmoun
     * @param loggedInPhysician2
     * @param dbOps2
     * Note: While copying, add parameters as well
     */
       public PatientGUI(Physician loggedInPhysician2, DatabaseOps dbOps2) {
   		this.loggedInPhysician2 = loggedInPhysician2;
   		this.dbOps2 = dbOps2;
   	}
       
  //  public PatientGUI(Hospital hospital) {
       public PatientGUI(Physician loggedInPhysician, Hospital hospital) {
    	   this.loggedInPhysician = loggedInPhysician; 

   	  
   	    
   	    patientInfoArea = new JTextArea(10, 40);
   	    patientInfoArea.setEditable(false);
   	    JScrollPane scrollPane = new JScrollPane(patientInfoArea);

        this.hospital = hospital;

        // Create the database 
        DBSetup.ensureAllTablesExist();

        database = new DatabaseHelper();
        dataOps = new DatabaseOps();
        patient = new Patient();

        // Set up the display
        setTitle("Patient Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600); // Increased size

        // Create JTextArea to display patient info
        patientInfoArea = new JTextArea(10,40);
        patientInfoArea.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(patientInfoArea);

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
        
        //Prescribe medication button
        prescribeMedicationButton = new JButton("Prescribe Medication");
        prescribeMedicationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrescriptionGUI prescriptionGUI = new PrescriptionGUI(loggedInPhysician, dbOps);
                prescriptionGUI.setVisible(true);
            }
        });

        // Request labs for the patient
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
        
        // Create a button to retrieve labs and medications
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
 				DischargePatientGUI dischargePatient;
 				if(dataOps.getAllPatients1().isEmpty()) {
 					JOptionPane.showMessageDialog(PatientGUI.this, "No patients in the hospital to discharge.");
 				}
 			else {
 				try {
 					dischargePatient = new DischargePatientGUI(hospital);
 					dischargePatient.setVisible(true);

 				} catch (NoSpaceException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
 			}	
 			}
 		});
        
        
        // BackButton to sign out and return back to the login page --> Harrish
        backButton = new JButton("Sign out");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToLogin();
            }
        });
     		

        // main panel to display patient information and search for patient by name and id 
        // panel to display the functionalities (record vital signs, retrieve vital signs, request labs, 
        // view lab results, and discharge patients)
        JPanel mainPanel = new JPanel();
        JPanel panel = new JPanel();
        
        // Add DisplayPatients, getPatientByName, and getPatientByID to the mainPanel
        mainPanel.add(DisplayPatients);
        mainPanel.add(getPatientByName);
        mainPanel.add(getPatientByID);
        
        // Add RecordVitals, RetrieveVitals, Labs_Meds, dischargeButton, requestLab, and backButton to the panel
        panel.add(RecordVitals);
        panel.add(RetrieveVitals);
        panel.add(Labs_Meds);
        panel.add(prescribeMedicationButton);
        panel.add(requestLab);
        panel.add(dischargeButton);
        panel.add(backButton);

        // Add main panel and panel to the frame 
        add(mainPanel);
        add(mainPanel, BorderLayout.NORTH);
        add(scrollPane1, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
  

    }

    // This method is to sign out and return back to the login page --> Harrish
    private void goBackToLogin() {
        // Dispose the current window
        this.dispose();

        // Open the LoginGUI window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginGUI loginGUI;
				try {
					loginGUI = new LoginGUI(hospital);
					loginGUI.setVisible(true);
				} catch (NoSpaceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hospital hospital = new Hospital(null); 
                PatientGUI patientGUI = new PatientGUI(null, hospital);
                patientGUI.setVisible(true);
            }
        });
    }
}
