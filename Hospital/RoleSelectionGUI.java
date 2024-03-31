package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleSelectionGUI extends JFrame {
    private JButton nurseButton;
    private JButton doctorButton;
    private JButton adminRole;
    private JButton labButton;
    private Hospital hospital;

    public RoleSelectionGUI(Hospital hospital) {
        this.hospital = hospital;
        setTitle("Select Role");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        nurseButton = new JButton("Login as Nurse");
        doctorButton = new JButton("Login as Doctor");
        adminRole = new JButton("Admin Login");
        labButton = new JButton("Lab Login");

        nurseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        LoginGUI loginGUI = new LoginGUI(hospital);
                        //loginGUI.setRole("Nurse");
                        loginGUI.setVisible(true);
                    } catch (NoSpaceException e1) {
                        e1.printStackTrace();
                    }
                });
                dispose();
            }
        });

        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        LoginGUI loginGUI = new LoginGUI(hospital);
                        //loginGUI.setRole("Doctor");
                        loginGUI.setVisible(true);
                    } catch (NoSpaceException e1) {
                        e1.printStackTrace();
                    }
                });
                dispose();
            }
        });

        adminRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        LoginGUI loginGUI = new LoginGUI(hospital);
                        //loginGUI.setRole("Admin");
                        loginGUI.setVisible(true);
                    } catch (NoSpaceException e1) {
                        e1.printStackTrace();
                    }
                });
                dispose();
            }
        });

        labButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        LoginGUI loginGUI = new LoginGUI(hospital);
                        //loginGUI.setRole("Lab");
                        loginGUI.setVisible(true);
                    } catch (NoSpaceException e1) {
                        e1.printStackTrace();
                    }
                });
                dispose();
            }
        });

        add(nurseButton);
        add(doctorButton);
        add(adminRole);
        add(labButton);
    }

    public static void main(String[] args) throws NoSpaceException {
        DBSetup.ensureAllTablesExist();
        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        hospital.InitializeEmployees();
        EventQueue.invokeLater(() -> {
            new RoleSelectionGUI(hospital).setVisible(true);
        });
    }
}
