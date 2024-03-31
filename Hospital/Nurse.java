package Hospital;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoleSelectionGUI extends JFrame {
    private JButton nurseButton;
    private JButton doctorButton;
    private JButton adminRole;
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
        adminRole = new JButton("Admin Login");

        
        /////////////////////////////////////
        nurseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
						new LoginGUI(hospital).setVisible(true);
					} catch (NoSpaceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                dispose(); // Close role selection window
            }
        });

        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
						new LoginGUI(hospital).setVisible(true);
					} catch (NoSpaceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                dispose(); // Close role selection window
            }
        });
        
        adminRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
						new LoginGUI(hospital).setVisible(true);
					} catch (NoSpaceException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });
                dispose(); // Close role selection window
            }
        });
        
        

        add(nurseButton);
        add(doctorButton);
        add(adminRole);
    }

    public static void main(String[] args) throws NoSpaceException {
    	DBSetup.ensureAllTablesExist();
    	// Initialize the Hospital object and other necessary components here, similar to the provided LoginGUI main method
        Hospital hospital = new Hospital(new Director("John", "Smith", 58, "Male", "123 Main St"));
        // Add additional initialization as per the original LoginGUI.main method
       // DBSetup.ensureAllTablesExist();
    
        PhysicianAdministrator admin = new PhysicianAdministrator("Meg", "Mes", 40, "Female", "789 Pine St");
        admin.setAdminSpecialtyType("Immunology");
        hospital.addAdministrator(admin);
        
        hospital.InitializeEmployees();
        

        EventQueue.invokeLater(() -> {
            new RoleSelectionGUI(hospital).setVisible(true);
        });
    }
}
