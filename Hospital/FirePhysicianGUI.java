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

	private void removeSelectedPhysician() {
		String selectedPhysicianName = (String) physicianComboBox.getSelectedItem();
		if (selectedPhysicianName != null && nameToPhysicianMap.containsKey(selectedPhysicianName)) {
			Physician physician = nameToPhysicianMap.get(selectedPhysicianName);
			databaseOps.deletePhysician(physician.getFirstName());
			try {
				hospital.resignPhysician(physician);
				JOptionPane.showMessageDialog(this, "Physician removed successfully.", "Removal Successful",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (NoSpecialtyException e) {
				JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} finally {
				populatePhysiciansComboBox(); // Refresh list even if there's an exception
			}
			// Optionally, remove the physician from the database as well
		} else {
			JOptionPane.showMessageDialog(this, "Please select a physician to remove.", "Removal Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
