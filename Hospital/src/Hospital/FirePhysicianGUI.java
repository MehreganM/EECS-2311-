package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirePhysicianGUI extends JFrame {
    private JComboBox<String> physicianComboBox;
    private JButton removeButton;
    private Hospital hospital;
    private DatabaseOps databaseOps = new DatabaseOps();
    private Map<String, Physician> nameToPhysicianMap = new HashMap<>();

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
        nameToPhysicianMap.clear(); // Clear the mapping
        ArrayList<Physician> physicians = databaseOps.getAllPhysicians(); // Assume this method exists
        for (Physician physician : physicians) {
            String physicianName = physician.getName() + " - Age: " + physician.getAge(); 
            nameToPhysicianMap.put(physicianName, physician);
            physicianComboBox.addItem(physicianName);
        }

  }
    

    private void removeSelectedPhysician() throws IllegalArgumentException {
        String selectedPhysicianName = (String) physicianComboBox.getSelectedItem();
        if (selectedPhysicianName != null && nameToPhysicianMap.containsKey(selectedPhysicianName)) {
            Physician physician = nameToPhysicianMap.get(selectedPhysicianName);
            databaseOps.deletePhysician(physician.getFirstName());
            try {
				hospital.resignPhysician(physician);
			} catch (NoSpecialtyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Use the hospital's method to remove the physician
            populatePhysiciansComboBox(); // Refresh list
            // Optionally, remove the physician from the database as well
            
        }
    }

}
