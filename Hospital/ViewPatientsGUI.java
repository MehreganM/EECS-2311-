package Hospital;

import javax.swing.*;
import java.awt.*;

public class ViewPatientsGUI extends JFrame {
    private JTextArea patientInfoArea;
    private Nurse nurse;
    private Hospital hospital;
    private DatabaseOps dataOps;

    public ViewPatientsGUI(Nurse nurse, Hospital hospital) {
        this.nurse = nurse;
        this.hospital = hospital;
        this.dataOps = new DatabaseOps();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("View Patients");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        patientInfoArea = new JTextArea();
        patientInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(patientInfoArea);
        add(scrollPane, BorderLayout.CENTER);

        refreshPatientList();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> dispose());
        add(backButton, BorderLayout.SOUTH);
    }

    public void refreshPatientList() {
        
        patientInfoArea.setText(""); // Clear previous text
        patientInfoArea.append(dataOps.getPatientByNurseId(dataOps.getNurseIdByName(nurse.getFirstName(), nurse.getLastName()))); // Append new patient list
    }
}
