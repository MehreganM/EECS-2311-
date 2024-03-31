package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class FirePhysicianGUI extends JFrame {
    private JComboBox<String> physicianComboBox;
    private JButton removeButton;
    private Hospital hospital;
    private DatabaseOps databaseOps = new DatabaseOps();
  

    public FirePhysicianGUI(Hospital hospital) {
        this.hospital = hospital;
        setTitle("Remove Physician");
        setSize(400, 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        physicianComboBox = new JComboBox<>();
        populatePhysiciansComboBox();

        removeButton = new JButton("Remove Selected Physician");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedPhysician();
            }
        });

        add(physicianComboBox);
        add(removeButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void populatePhysiciansComboBox() {
        physicianComboBox.removeAllItems(); // Clear current items
        String physiciansInfo = databaseOps.getAllPhysicians1();
    	if(!physiciansInfo.isEmpty()) {
    		String[] physicianLines = physiciansInfo.split("\n");
    	
    	
    	System.out.println(physicianLines);
    	
    	// Iterate through all the physicians in the database and add it to the comboBox
    	for (String physician : physicianLines) {
    		String parts [] = physician.split(",");
    		
    		int physicianID = Integer.parseInt(parts[0].split(": ")[1]);
    		String firstname = parts[1].split(": ")[1];
    		String lastname = parts[2].split(": ")[1];
    		int age = Integer.parseInt(parts[3].split(": ")[1]);
     
            String physicianList = String.format("ID: %d, FirstName: %s, Last name: %s, Age: %d", physicianID, firstname, lastname, age );
            System.out.println(physicianList.toString());
            physicianComboBox.addItem(physicianList);
    		}
    	}
    }
    
    

    private void removeSelectedPhysician() {
        String selectedPhysicianInfo = (String) physicianComboBox.getSelectedItem();
        if (selectedPhysicianInfo != null) {
            // Extract the physician ID from the selected item
                int physicianID = Integer.parseInt(selectedPhysicianInfo.split(",")[0].split(":")[1].trim());
                // Use the extracted ID to delete the physician from the database
                databaseOps.deletePhysician(physicianID);
                JOptionPane.showMessageDialog(FirePhysicianGUI.this, "Physician resigned successfully.");
                System.out.println("Physician resigned successfully.");
                populatePhysiciansComboBox();

            
        }
    }


}
