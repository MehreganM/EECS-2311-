package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabDashboard extends JFrame {
    private JButton retrieveRequestsButton;
    private JButton fulfillRequestsButton;

    public LabDashboard() {
        setTitle("Laboratory Dashboard");
        setSize(600, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1, 10, 10));

        retrieveRequestsButton = new JButton("Retrieve Lab Requests");
        fulfillRequestsButton = new JButton("Fulfill Lab Requests");

        retrieveRequestsButton.setPreferredSize(new Dimension(200, 100));
        fulfillRequestsButton.setPreferredSize(new Dimension(200, 100));

        retrieveRequestsButton.addActionListener(e -> {
            // Implement functionality to retrieve lab requests
            JOptionPane.showMessageDialog(this, "Retrieve Lab Requests functionality to be implemented.");
        });

        fulfillRequestsButton.addActionListener(e -> SwingUtilities.invokeLater(() -> new FulfillLabRequestsFrame().setVisible(true)));

        add(retrieveRequestsButton);
        add(fulfillRequestsButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LabDashboard().setVisible(true));
    }
}

 class FulfillLabRequestsFrame extends JFrame {
    private JLabel patientIDLabel;
    private JTextField patientIDField;
    private JButton searchButton, editLabRequestButton;
    private JTextArea testResultsArea;


    public FulfillLabRequestsFrame() {
        setTitle("Fulfill Lab Requests");
        setSize(500, 400); 
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        patientIDLabel = new JLabel("Enter Patient ID:");
        patientIDField = new JTextField(10);
        searchButton = new JButton("Search");
        editLabRequestButton = new JButton("Edit Lab Request");
        testResultsArea = new JTextArea(15, 35);
        testResultsArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(testResultsArea); 


        JPanel panel = new JPanel();
        panel.add(patientIDLabel);
        panel.add(patientIDField);
        panel.add(searchButton);
        panel.add(editLabRequestButton);

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


        editLabRequestButton.addActionListener(e -> {
            String patientIDStr = patientIDField.getText().trim();
            if (patientIDStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a patient ID.");
                return;
            }

            int patientID;
            try {
                patientID = Integer.parseInt(patientIDStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid patient ID format. Please enter a valid number.");
                return;
            }

            String testName = JOptionPane.showInputDialog(this, "Enter Test Name:");
            if (testName == null || testName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Test name cannot be empty.");
                return;
            }
            String newResult = JOptionPane.showInputDialog(this, "Enter New Result for " + testName + ":");
            if (newResult == null || newResult.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Test result cannot be empty.");
                return;
            }

            Laboratory lab = new Laboratory(); 
            boolean success = lab.updateTestResult(patientID, testName, newResult);
            if (success) {
                JOptionPane.showMessageDialog(this, "Test result updated successfully.");
                searchButton.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update test result.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FulfillLabRequestsFrame().setVisible(true));
    }
}
