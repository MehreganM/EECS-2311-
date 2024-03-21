package Hospital.src.Hospital; // Make sure your package declaration matches your project structure

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalDashboard extends JFrame {
    private JPanel mainPanel;
    private JButton searchStaffButton;
    private JButton hireStaffButton;
    private JButton resignStaffButton;

    public HospitalDashboard() {
        super("Hospital Administration Dashboard");
        setSize(600, 400); // Adjusted for larger buttons
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, 10px vertical and horizontal gaps

        searchStaffButton = new JButton("Search Staff");
        hireStaffButton = new JButton("Hire Staff");
        resignStaffButton = new JButton("Resign Staff");

        searchStaffButton.setFont(new Font("Arial", Font.BOLD, 20));
        hireStaffButton.setFont(new Font("Arial", Font.BOLD, 20));
        resignStaffButton.setFont(new Font("Arial", Font.BOLD, 20));

        mainPanel.add(searchStaffButton);
        mainPanel.add(hireStaffButton);
        mainPanel.add(resignStaffButton);

        // Modify this section to open the StaffSearchGUI
        searchStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Opening the StaffSearchGUI when the Search Staff button is clicked
                StaffSearchGUI searchGUI = new StaffSearchGUI(); // Assuming StaffSearchGUI is accessible
                searchGUI.setVisible(true);
            }
        });

        hireStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HospitalDashboard.this, "Hire Staff functionality not implemented.");
            }
        });

        resignStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HospitalDashboard.this, "Resign Staff functionality not implemented.");
            }
        });

        this.add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HospitalDashboard().setVisible(true);
            }
        });
    }
}
