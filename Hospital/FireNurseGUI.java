
package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireNurseGUI extends JFrame {
    private JComboBox<String> nurseComboBox;
    private JButton removeButton;
    private Hospital hospital;
    private DatabaseOps databaseOps = new DatabaseOps();
    private Map<String, Nurse> nameToNurseMap = new HashMap<>();

    public FireNurseGUI(Hospital hospital) {
        this.hospital = hospital;
        setTitle("Remove Nurse");
        setSize(400, 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        nurseComboBox = new JComboBox<>();
        populateNursesComboBox();

        removeButton = new JButton("Remove Selected Nurse");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedNurse();
            }
        });

        add(nurseComboBox);
        add(removeButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void populateNursesComboBox() {
        nurseComboBox.removeAllItems(); // Clear current items
        String nursesInfo = databaseOps.getAllNurses1();
    	if(!nursesInfo.isEmpty()) {
    		String[] nurseLines = nursesInfo.split("\n");
    	
    	
    	System.out.println(nurseLines);
    	
    	// Iterate through all the nurses in the database and add it to the comboBox
    	for (String nurse : nurseLines) {
    		String parts [] = nurse.split(",");
    		
    		int nurseID = Integer.parseInt(parts[0].split(": ")[1]);
    		String firstname = parts[1].split(": ")[1];
    		String lastname = parts[2].split(": ")[1];
    		int age = Integer.parseInt(parts[3].split(": ")[1]);
     
            String nurseList = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d", nurseID, firstname, lastname, age );
            System.out.println(nurseList.toString());
            nurseComboBox.addItem(nurseList);
    		}
    	}
    }    

    private void removeSelectedNurse() {
        String selectedNurseInfo = (String) nurseComboBox.getSelectedItem();
        if (selectedNurseInfo != null) {
            // Extract the nurse ID from the selected item
                int nurseID = Integer.parseInt(selectedNurseInfo.split(",")[0].split(":")[1].trim());
                // Use the extracted ID to delete the nurse from the database
                databaseOps.deleteNurse(nurseID);
                JOptionPane.showMessageDialog(FireNurseGUI.this, "Nurse resigned successfully.");
                System.out.println("Nurse resigned successfully.");
                populateNursesComboBox();            
        }
    }

}
