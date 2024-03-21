package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleSelectionGUI extends JFrame {
    private JButton nurseButton;
    private JButton doctorButton;
    private Hospital hospital;

    public RoleSelectionGUI(Hospital hospital) {
        this.hospital = hospital;
        setTitle("Select Role");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        nurseButton = new JButton("Login as Nurse");
        doctorButton = new JButton("Login as Doctor");

        nurseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    new LoginGUI(hospital).setVisible(true);
                });
                dispose(); // Close role selection window
            }
        });

        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    new LoginGUI(hospital).setVisible(true);
                });
                dispose(); // Close role selection window
            }
        });

        add(nurseButton);
        add(doctorButton);
    }

    public static void main(String[] args) {
        // Initialize the Hospital object and other necessary components here, similar to the provided LoginGUI main method
        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
        // Add additional initialization as per the original LoginGUI.main method

        EventQueue.invokeLater(() -> {
            new RoleSelectionGUI(hospital).setVisible(true);
        });
    }
}
