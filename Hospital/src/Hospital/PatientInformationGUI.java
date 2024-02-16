
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * this is the patient informaiton GUI class that is a GUI for our ITR1 - Big story 3 where all the departments can access patient information 
 * including but not limited to their full name and gender. In the upcomign phases, it will aslo include patient lab information, medication and their 
 * family doctor information including their consent forms 
 * 
 * 
 * @author: Mehregan Mesgari 
 */


public class PatientInformationGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JComboBox<String> searchTypeComboBox;
    private Hospital hospital;

    public PatientInformationGUI(Hospital hospital) {
        this.hospital = hospital;
        infoRetrivalUI();
    }
    
    
    /*
     * the infoRetrivalUI is the private method that acts as the body of the GUI. this method has a slider option to choose search by name or ID
     * a text box to write the patient name if search by name is chosen or for patient ID if the search ID option is selected in the drop box 
     * it also has a non editable tab where the patient info is displayed on 
     * 
     * @return: null
     */

    private void infoRetrivalUI() {
        setTitle("Patient Information Retrieval");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        String[] searchTypes = {"By ID", "By Name"};
        searchTypeComboBox = new JComboBox<>(searchTypes);

        panel.add(new JLabel("Search Type:"));
        panel.add(searchTypeComboBox);
        panel.add(new JLabel("Enter Search Query:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(new JScrollPane(resultArea));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                String searchType = (String) searchTypeComboBox.getSelectedItem();
                if ("By ID".equals(searchType)) {
                    searchById(searchQuery);
                } else if ("By Name".equals(searchType)) {
                    searchByName(searchQuery);
                }
            }
        });

        add(panel);
    }

    /*
     * searchbyID is a private void method for the patientinfo GUI that accepts a patientID as a String and set the text in the GUI  
     * The reason behind accepting the patientID as an String not an int is because searchField and searchTyprCombBox are methods that return String 
     * In order to not face anu complication when searchPatient is called from the hospital, the string is converted to and Integer in the body of 
     * this method 
     * 
     * @param patientID in String format
     */
    
    private void searchById(String patientID) {
        try {
            Patient patient = hospital.searchPatient(Integer.parseInt(patientID));
            if (patient != null) {
                String patientInfo = (
                        patient.getName() + " ," + patient.getAge() + " ," + patient.getGender());
                resultArea.setText(patientInfo);
            } else {
                resultArea.setText("No patient found with ID: " + patientID);
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid ID format.");
        }
    }

    /*
     * searchbyID is a private void method for the patientinfo GUI that accepts a name as a String and set the text in the GUI  
     * 
     * @param name that is the patient first name in String format
     */
    
    private void searchByName(String name) {
        Patient patient = hospital.searchPatientByName(name);
        if (patient != null) {
            String patientInfo = (patient.getPatientID()+ " " +
                    patient.getName() + " ," + patient.getAge() + " ," + patient.getGender());
            resultArea.setText(patientInfo);
        } else {
            resultArea.setText("No patient found with name: " + name);
        }
    }
    
    /*
     * this is the main method for this class where the hospital system is built and a few patients are admitted to use the functionality of the 
     * GUI
     */

    public static void main(String[] args) {
        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
    
       
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        
        Physician physician = new Physician("DR.AL", "kp", 35, "Male", "202 Oak St");
        physician.setSpecialty("Immunology");
        hospital.hirePhysician(physician);
        
        Patient patient = new Patient("John", "smith", 30, "Male", "123 Main St");
        Patient patient2 = new Patient("ali", "bakhshi", 30, "Male", "123 Main St");
        Patient patient3 = new Patient("Sarah", "lance", 30, "female", "123 Main St");
        Patient patient4 = new Patient("Kim", "k", 30, "Female", "123 Main St");
        try {
        	  System.out.println(hospital.admitPatient(patient));
        	  System.out.println(hospital.admitPatient(patient2));
        	  System.out.println(hospital.admitPatient(patient3));
        	  System.out.println(hospital.admitPatient(patient4));
		} catch (NoSpaceException e) {
			e.printStackTrace();
		}
    

        SwingUtilities.invokeLater(() -> {
            PatientInformationGUI gui = new PatientInformationGUI(hospital);
            gui.setVisible(true);
        });
    }
}

