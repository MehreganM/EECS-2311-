package Hospital.src.Hospital;

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
        nameToNurseMap.clear(); // Clear the mapping
        ArrayList<Nurse> nurses = databaseOps.getAllNurses(); // Assume this method exists
        for (Nurse nurse : nurses) {
            String nurseName = nurse.getName() + " - Age: " + nurse.getAge(); 
            nameToNurseMap.put(nurseName, nurse);
            nurseComboBox.addItem(nurseName);
        }

  }
    

    private void removeSelectedNurse() throws IllegalArgumentException {
        String selectedNurseName = (String) nurseComboBox.getSelectedItem();
        if (selectedNurseName != null && nameToNurseMap.containsKey(selectedNurseName)) {
            Nurse nurse = nameToNurseMap.get(selectedNurseName);
            databaseOps.deleteNurse(nurse.getFirstName());
			hospital.resignNurse(nurse);				
            populateNursesComboBox(); // Refresh list
            // Optionally, remove the nurse from the database as well
            
        }
    }

}
