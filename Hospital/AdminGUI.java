package Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // Import statement for ArrayList
import java.util.List;

public class AdminGUI extends JFrame implements ActionListener{
	
	private JButton addPhysician;
	private JButton addNurse;
	private JButton firePhysician;
	private JButton fireNurse;
	private JButton backButton;
	private Hospital hospital;
	static DatabaseOps dbOps = new DatabaseOps();
	
	public AdminGUI(Hospital hospital) {
	this.hospital = hospital;
	setTitle("Admin Page");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 150);
    setLocationRelativeTo(null);
    
    setLayout(new GridLayout(5, 1));
    
    addPhysician = new JButton("Hire Physician");
    addPhysician.addActionListener(this);
  
    
    addNurse = new JButton("Hire Nurse");
    addNurse.addActionListener(this);
    
    firePhysician = new JButton("Resign Physician");
    firePhysician.addActionListener(this);
    
    fireNurse = new JButton("Resign Nurse");
    fireNurse.addActionListener(this);
    
    backButton = new JButton("Sign out");
    backButton.addActionListener(this);
    
    
    add(addPhysician);
	add(addNurse);
	add(firePhysician);
	add(fireNurse);
	add(backButton);
	}
	
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
	        Object source = e.getSource();
	        
	        if (source == addPhysician) {
	            AddPhysicianGUI addPhysicianGUI = new AddPhysicianGUI(hospital);
	            addPhysicianGUI.setVisible(true);
	            this.dispose();
	        } else if (source == addNurse) {
	            AddNurseGUI addNurseGUI = new AddNurseGUI(hospital);
	            addNurseGUI.setVisible(true);
	            this.dispose();
	        } else if (source == firePhysician) {
	            FirePhysicianGUI firePhysicianGUI = new FirePhysicianGUI(hospital);
	            firePhysicianGUI.setVisible(true);
	        }  else if (source == fireNurse) {
	            FireNurseGUI fireNurseGUI = new FireNurseGUI(hospital);
	            fireNurseGUI.setVisible(true);
	        } 
	        
	        else if (source == backButton) {
	        	
	        	RoleSelectionGUI loginGUI;
				loginGUI = new RoleSelectionGUI(hospital);
				loginGUI.setVisible(true);
			this.dispose();
	      
	        }
	    } 
		
}


 class AdminGUIMain {

    public static void main(String[] args) {
        // Create a Hospital object
        Hospital hospital = new Hospital(null);

        // Create an instance of AdminGUI
        AdminGUI adminGUI = new AdminGUI(hospital);

        // Make the AdminGUI visible
        adminGUI.setVisible(true);
    }
}

