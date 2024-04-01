package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPhysicianGUI extends JFrame {
	private Hospital hospital;
	private DatabaseOps databaseOps = new DatabaseOps();

	private JTextField firstNameField, lastNameField, ageField, genderField, addressField, usernameField, passwordField;
	private JComboBox<String> specialtyComboBox;
	private JButton addButton, returnButton;

	public AddPhysicianGUI(Hospital hospital) {
		super("Add Physician");
		this.hospital = hospital;
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		initializeFields();
		addComponentsToFrame();

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void initializeFields() {
		firstNameField = new JTextField(20);
		lastNameField = new JTextField(20);
		ageField = new JTextField(20);
		genderField = new JTextField(20);
		addressField = new JTextField(20);
		usernameField = new JTextField(20);
		passwordField = new JTextField(20);

		String[] specialties = { "Radiology", "Dermatology", "Neurology", "Cardiology", "Hematology", "Immunology" };
		specialtyComboBox = new JComboBox<>(specialties);

		addButton = new JButton("Add Physician");
		addButton.addActionListener(e -> addPhysician());

		returnButton = new JButton("Return to Dashboard");
		returnButton.addActionListener(e -> returnToDashboard());
	}

	private void addComponentsToFrame() {
		add(new JLabel("First Name:"));
		add(firstNameField);
		add(new JLabel("Last Name:"));
		add(lastNameField);
		add(new JLabel("Age:"));
		add(ageField);
		add(new JLabel("Gender:"));
		add(genderField);
		add(new JLabel("Address:"));
		add(addressField);
		add(new JLabel("Username:"));
		add(usernameField);
		add(new JLabel("Password:"));
		add(passwordField);
		add(new JLabel("Specialty:"));
		add(specialtyComboBox);
		add(addButton);
		add(returnButton);
	}

	private void addPhysician() {
		String firstName = firstNameField.getText().trim();
		String lastName = lastNameField.getText().trim();
		String ageText = ageField.getText().trim();
		String gender = genderField.getText().trim();
		String address = addressField.getText().trim();
		String username = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		String specialty = (String) specialtyComboBox.getSelectedItem();

		// Check if any of the fields are empty
		if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || gender.isEmpty() || address.isEmpty()
				|| username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Information",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Validate first name and last name to ensure they contain only letters (and
		// possibly spaces, apostrophes, or hyphens)
		if (!firstName.matches("[a-zA-Z-' ]+") || !lastName.matches("[a-zA-Z-' ]+")) {
			JOptionPane.showMessageDialog(this, "First name and last name must contain only letters.", "Input Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Validate gender field to ensure it is either "Male" or "Female"
		if (!(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))) {
			JOptionPane.showMessageDialog(this, "Gender must be either 'Male' or 'Female'.", "Input Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Parse the age field
		int age;
    try {
        age = Integer.parseInt(ageText);
        if (age <= 0) { // Check if age is positive
            throw new NumberFormatException("Age must be a positive number.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid age. Please enter a positive number.", "Input Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

		Physician newPhysician = new Physician(firstName, lastName, age, gender, address);
		newPhysician.setUser(username);
		newPhysician.setPass(password);
		try {
			newPhysician.setSpecialty(specialty);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Error: Invalid specialty.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		hospital.hirePhysician(newPhysician);
		databaseOps.addPhysician(newPhysician);

		JOptionPane.showMessageDialog(this, "Physician added successfully.");
		clearFields();
	}

	private void clearFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		ageField.setText("");
		genderField.setText("");
		addressField.setText("");
		usernameField.setText("");
		passwordField.setText("");
		specialtyComboBox.setSelectedIndex(0); // Reset to first specialty in the list
	}

	private void returnToDashboard() {
		this.dispose();
		// Open or return to the main Admin GUI or dashboard
		EventQueue.invokeLater(() -> {
			AdminGUI adminGUI = new AdminGUI(hospital);
			adminGUI.setVisible(true);
		});
	}
}
