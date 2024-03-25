package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLabRequestGUI extends JFrame {
    private JTextField testTypeField, patientIDField, testDescriptionField;
    private JButton submitButton;
    private Laboratory laboratory; // Assuming you have access to your Laboratory class instance

    public AddLabRequestGUI() {
        setTitle("Add Lab Request");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Test Type:"));
        testTypeField = new JTextField();
        add(testTypeField);

        add(new JLabel("Patient ID:"));
        patientIDField = new JTextField();
        add(patientIDField);

        add(new JLabel("Description:"));
        testDescriptionField = new JTextField();
        add(testDescriptionField);

        submitButton = new JButton("Submit");
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitLabRequest();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void submitLabRequest() {
        // Implement submission logic here
        // Example:
        String testType = testTypeField.getText();
        int patientID = Integer.parseInt(patientIDField.getText());
        String description = testDescriptionField.getText();

        // Assuming labTest is a constructor that takes (Patient patient, String type)
        // and you have a way to retrieve or create a Patient instance from patientID
        Patient patient = new Patient(); // Simplified, adjust based on your Patient class
        labTest request = new labTest(patient, testType);
        request.addResult(""); // Assuming results are added later, and the constructor does not include results

        boolean success = laboratory.addTestRequest(request, description);
        if (success) {
            JOptionPane.showMessageDialog(this, "Lab request submitted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to submit lab request.");
        }
    }
}
