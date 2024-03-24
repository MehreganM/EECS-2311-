package Hospital;import javax.swing.*;
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

        retrieveRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add code to retrieve lab requests
            }
        });

        fulfillRequestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new FulfillLabRequestsFrame().setVisible(true);
                });
            }
        });

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
    private JButton searchButton;
    private JTextArea testResultsArea;
    private JButton inputResultsButton;

    public FulfillLabRequestsFrame() {
        setTitle("Fulfill Lab Requests");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        patientIDLabel = new JLabel("Enter Patient ID:");
        patientIDField = new JTextField(10);
        searchButton = new JButton("Search");
        testResultsArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(testResultsArea);
        inputResultsButton = new JButton("Input Results");

        JPanel panel = new JPanel();
        panel.add(patientIDLabel);
        panel.add(patientIDField);
        panel.add(searchButton);
        panel.add(inputResultsButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int patientID = Integer.parseInt(patientIDField.getText());
                Laboratory lab = new Laboratory();
                String testResults = lab.getAllTestsForPatientAsString(new Patient());
                if (testResults != null && !testResults.isEmpty()) {
                    testResultsArea.setText(testResults);
                } else {
                    testResultsArea.setText("No test results found for patient ID: " + patientID);
                }
            }
        });

        inputResultsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display "Happy Birthday" message
                JOptionPane.showMessageDialog(FulfillLabRequestsFrame.this, "Happy Birthday!");
            }
        });
    }
}
