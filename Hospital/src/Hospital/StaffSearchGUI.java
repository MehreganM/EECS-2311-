package Hospital.src.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StaffSearchGUI extends JFrame {
    private ArrayList<Physician> physicianList = new ArrayList<>();
    private ArrayList<Nurse> nurseList = new ArrayList<>();
    private JComboBox<String> roleComboBox;
    private JTextField searchField;
    private JTextArea resultArea;

    public StaffSearchGUI() {
        setTitle("Staff Search");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        roleComboBox = new JComboBox<>(new String[]{"Nurse", "Physician"});
        add(roleComboBox);
        
        searchField = new JTextField(20);
        add(searchField);
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStaff();
            }
        });
        add(searchButton);
        
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea));
        
        // Populate the lists with sample data for testing
        nurseList.add(new Nurse("Jane", "Doe", 28, "Female", "123 Street"));
        physicianList.add(new Physician("John", "Smith", 35, "Male", "456 Avenue"));
    }

    private void searchStaff() {
        String role = (String) roleComboBox.getSelectedItem();
        String firstName = searchField.getText();
        StringBuilder result = new StringBuilder();
        
        if ("Nurse".equals(role)) {
            for (Nurse nurse : nurseList) {
                if (nurse.getFirstName().equalsIgnoreCase(firstName)) {
                    result.append(nurse.toString()).append("\n");
                }
            }
        } else if ("Physician".equals(role)) {
            for (Physician physician : physicianList) {
                if (physician.getFirstName().equalsIgnoreCase(firstName)) {
                    result.append(physician.toString()).append("\n");
                }
            }
        }
        
        resultArea.setText(result.toString());
        if (result.toString().isEmpty()) {
            resultArea.setText("No results found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new StaffSearchGUI().setVisible(true);
            }
        });
    }
}

