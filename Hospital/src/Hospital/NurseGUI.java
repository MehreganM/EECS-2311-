package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NurseGUI extends JFrame {
    private JButton addPatientButton;
    private JButton viewPatientsButton;
    Nurse nurse; // Nurse who logged in

    public NurseGUI(Nurse nurse) {
        this.nurse = nurse;
        setTitle("Nurse Dashboard");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        addPatientButton = new JButton("Add Patient");
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNursePatientFrame();
            }
        });

        viewPatientsButton = new JButton("View Patients");
        viewPatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                extractAndDisplayPatientDetails();
            }
        });

        add(addPatientButton);
        add(viewPatientsButton);
    }

    private void openNursePatientFrame() {
        // Assuming NursePatientFrame allows adding a patient
        NursePatientAddFrame addPatientFrame = new NursePatientAddFrame(this.nurse); // Pass the nurse object
        addPatientFrame.setVisible(true);
        this.setVisible(false);
    }

    private void extractAndDisplayPatientDetails() {
        // Display a new JFrame or dialog with patient details
        // Assuming a method in Nurse that returns a formatted String of patient details
        List<Patient> patientDetails = nurse.extractPatientDetail();
        
        if(patientDetails.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "No patients assigned!", "Patient Details", JOptionPane.INFORMATION_MESSAGE);
        }
        else {        	
        JOptionPane.showMessageDialog(this, patientDetails, "Patient Details", JOptionPane.INFORMATION_MESSAGE);
    }
    }
}
