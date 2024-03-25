package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientInformationGUI extends JFrame {
    private JLabel patientIDLabel;
    private JTextField patientIDField;
    private JButton searchButton;
    private JTextArea testResultsArea;

    public PatientInformationGUI() {
        setTitle("Patient Information");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        patientIDLabel = new JLabel("Enter Patient ID:");
        patientIDField = new JTextField(10);
        searchButton = new JButton("Search");
        testResultsArea = new JTextArea(15, 35);
        testResultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(testResultsArea);

        JPanel panel = new JPanel();
        panel.add(patientIDLabel);
        panel.add(patientIDField);
        panel.add(searchButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            String patientIDStr = patientIDField.getText().trim();
            if (!patientIDStr.isEmpty()) {
                try {
                    int patientID = Integer.parseInt(patientIDStr);
                    Laboratory lab = new Laboratory();
                    String labResults = lab.getAllTestsForPatientAsString(patientID);

                    if (!labResults.isEmpty()) {
                        testResultsArea.setText(labResults);
                    } else {
                        testResultsArea.setText("No lab results found for patient ID: " + patientID);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid patient ID format. Please enter a valid number.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a patient ID.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientInformationGUI().setVisible(true));
    }
}
