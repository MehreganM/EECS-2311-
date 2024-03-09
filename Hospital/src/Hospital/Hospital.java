<<<<<<< HEAD
=======
package Hospital;

import Hospital.Patient;

>>>>>>> d6167dc2a3a7d2ed8d26456e3833a33aef5ea12c


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



/**
 * this is a class that implements a virtual hospital scenario with 1 director
 * 3 administrators and up to a maximum of 25
 * physicians for each physician Administrator with a total of 70 with a maximum of . this hospital also accepts patients
 * to a maximum of 8 per physician and 500 in total. 
 * the assignment of physicians and physician administrator is based on specialty 
 * hospital has a director an arraylist for physicians an arraylist for
 * physician administrator and an arraylist for patients 
 * @author mehregan Mesgari
 *
 */


public class Hospital {
	private Director director;
	private ArrayList<Physician> physicianList=new ArrayList<Physician>();
	private ArrayList<Nurse> nurseList=new ArrayList<Nurse>();
	private ArrayList<PhysicianAdministrator> adminList=new ArrayList<PhysicianAdministrator>();
	private ArrayList<Patient> patientList=new ArrayList<Patient>();
	public  final static Labratory laboratory = new Labratory();

	/**
	 * this overloaded constructor that makes a hospital and assigns the director that it receives as an input
	 * @pre only one director is accepted for the hospital.director is not null
	 * @param director is an object of type Director and is the director of the hospital
	 */
	public Hospital(Director director) {
		this.director=director;
		
	}	
	/**
	 * this method returns an object of type director which is the director of the hodpital
	 * @pre the hospital already has a director and director is not null
	 * @return the director of the hospital
	 */

	public Director getHospDirector() {
		return director;
	}
	/**
	 * this method sets the hospital director to a new director 
	 * @pre director is not null
	 * @param director is an object of type Director which we want to set as the hospital director
	 */
	public void setHospDirector(Director director) {
		this.director=director;
	}
	/**
	 * this method receives a Physician administrator and calls the director to
	 * assign the administrator and returns if the administrator was successfully
	 * assigned or not and also adds the administrator to the administrator list 
	 * of the hospital if the assignment was successful
	 * @pre each specialty can only have one administrator,admin is not null
	 * @param admin is an object of type PhysicianAdministrator and is the administrator that we want to add to the hospital
	 * @return true if the administrator could be added and false otherwise
	 */

	public boolean addAdministrator(PhysicianAdministrator admin) {
		//we call a method which returns true if it was able to add the administrator
		//without any problem and false otherwise and then we add the administrator
		//to the hospital list
		boolean flag=director.assignAdministrator(admin);
		if(flag) {
			adminList.add(admin);
		}
		return flag;
	}
	/**
	 * this method receives a physician and checks if the number of physicians in the hospital
	 * are less than 70 and if the physician administrator of this physician's
	 * specialty has less than 25 physicians and if these conditions apply and the
	 * physician doesn't already exist in the hospital it returns true and adds
	 * the physician to the hospital physician list and to the physician administrator
	 * of its specialty otherwise returns false
	 * @pre physician is not null
	 * @param physician is an object of type Physician and is the physician that we want to hire
	 * @return true if we were able to add the physician to the list based on the conditions
	 * stated and false otherwise
	 */

	public boolean hirePhysician(Physician physician) {
		//we make sure we don't already have 70 physicians 
		
		if(physicianList.size()<70) {
			//we make sure the physician does not already exist
			for(int i =0; i<physicianList.size();i++) {
				if (physician.equals(physicianList.get(i))) {
					return false;
				}
			}
			boolean flag=false;
			//we try to add the physician to each administrator using a method
			//which returns true if it was able to add the physician and false
			//otherwise.(if physicians were more than 25 or if specialty didn't match)
			for(int i = 0; i<adminList.size();i++) {
				flag=adminList.get(i).addPhysician(physician);
				if(flag) {
					break;
				}
			}
			//if we were able to add we add it to the hospital physician list
			if(flag) {
				physicianList.add(physician);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
		
	}
	
<<<<<<< HEAD
	public ArrayList<Physician> getPhysList(){
		return this.physicianList;
=======
	public boolean hireNurse(Nurse nurse) {
		//we make sure we don't already have 70 nurses 
		
		if(nurseList.size()<70) {
			//we make sure the nurse does not already exist
			for(int i =0; i<nurseList.size();i++) {
				if (nurse.equals(nurseList.get(i))) {
					return false;
				}
			}
			boolean flag=false;
			//we try to add the nurse to each administrator using a method
			//which returns true if it was able to add the nurse and false
			//otherwise.(if nurses were more than 25 or if specialty didn't match)
			for(int i = 0; i<adminList.size();i++) {
				flag=adminList.get(i).addNurse(nurse);
				if(flag) {
					break;
				}
			}
			//if we were able to add we add it to the hospital nurse list
			if(flag) {
				nurseList.add(nurse);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
		
>>>>>>> d6167dc2a3a7d2ed8d26456e3833a33aef5ea12c
	}
	/**
	 * this method returns a sorted list of the physicians based on their full name
	 * 
	 * @return a sorted list of physicians based on their full name
	 */
	

	public List<Physician> extractAllPhysicianDetails() {
		//we make a copy list to sort
		//we have already defined compareTo
		
		List<Physician> physicianSort = new ArrayList<Physician>();
		physicianSort.addAll(physicianList);
		return physicianSort;
	}
	
	public List<Nurse> extractAllNurseDetails() {
		//we make a copy list to sort
		//we have already defined compareTo
		
		List<Nurse> nurseSort = new ArrayList<Nurse>();
		nurseSort.addAll(nurseList);
		return nurseSort;
	}
	/**
	 * this method returns a sorted list of the patients based on their full name
	 * @return a sorted list of patients based on their full name
	 */

	public List<Patient> extractAllPatientDetails() {
		//we make a copy list to sort
		//we have already defined compareTo
		List<Patient> patientSort = new ArrayList<>();
		patientSort.addAll(patientList);
		Collections.sort(patientSort);
		return patientSort;
		
	}
	/**
	 * this method receives a patient and assigns patient to a physician if the total
	 * number of patients is less than 500 and there is a physician with less than
	 * 8 patients and if the patient doesn't already exist in hospital
	 * @pre patient is not null
	 * @param patient is an object of type Patient and is the patient we want to admit 
	 * @return true if we could admit the patient and false otherwise
	 * @throws NoSpaceException if the hospital has more than 500 patients
	 * or if there is no physician with less than 8 patients
	 */

	public boolean admitPatient(Patient patient) throws NoSpaceException {
		//we check to see if the hospital is not full first
		if(patientList.size()<500) {
			//we check to see if the patient already exists or not
			for(int i =0; i<patientList.size();i++) {
				if (patient.equals(patientList.get(i))) {
					return false;
				}
			}
			//we try to add the patient to physician's patient list if they have
			//less than 8 patients which returns true if added and false otherwise
			boolean flag= false;
			for(int i = 0; i<physicianList.size();i++) {
				if(physicianList.get(i).patients.size()<8) {
					flag=physicianList.get(i).addPatient(patient);
					
					break;
				}
			//	adminList.get(i).addPhysician(physician);
			}
			//we add the patient to hospital's patient list if we were able to add 
			//it to a physician's list
			if(flag) {
				patientList.add(patient);
				return true;
			}
			else {
				throw new NoSpaceException();
			}
			
		}
		else {
			throw new NoSpaceException();
		}
	}
	/**
	 * this method receives a physician that we want to resign but we can only resign it
	 * if there is another physician with the same specialty.this method also 
	 * reassigns all the patients to the first available physician
	 * @pre physician is not null and exists in hospital
	 * @param physician is an object of type Physician and is the physician that wants to resign
	 * @throws NoSpecialtyException if there is not any other physician with the same
	 * specialty
	 */

	public void resignPhysician(Physician physician) throws NoSpecialtyException {
		if(physicianList.contains(physician)!=false) {
			//we store the physicians patients and volunteers before deleting them
			ArrayList<Patient> tempArray= physician.patients;
			PhysicianAdministrator admin= physician.admin;
			//we have a method which checks if we can delete the physician and
			//either throws an exception or returns true if it could
			boolean flag= admin.deletePhysician(physician);
			//if we could delete the physician we reassign the patients and volunteers
			//using methods that use a for loop to loop through other physicians
			if(flag) {
				physicianList.remove(physician);
				for(int i=0;i<tempArray.size();i++) {
					physicianGone(tempArray.get(i));
				}
			}
			physician=null;
		}
		else {
			
		}
		
	}

	/**
	 * this method receives a patient and adds this patient to the first
	 * available physician with less than 8 patients
	 * @pre patient is not null
	 * @param patient is an object of type Patient and is the patient we want to reassign
	 */
	public void physicianGone(Patient patient) {
		//we check which physician has less than 8 patients to add this patient to
		for(int i = 0; i<physicianList.size();i++) {
			if(physicianList.get(i).patients.size()<8) {
				physicianList.get(i).addPatient(patient);
				
				break;
			}
		
		}
	}
	/**
	 * this method receives a patient we want to discharge and deletes their records from the
	 * hospital records and patient records.returns true if it was able to discharge
	 * and false otherwise
	 * @pre patient is not null and patient is in the hospital
	 * @param patient is an object of type Patient and is the patient we want to discharge
	 * @return true if it was able to discharge and false otherwise
	 */

	public boolean dischargePatient(Patient patient) {
		if(patientList.contains(patient)) {
			//we remove the patient from hospital and physicians'patient list
			patient.getPhysician().patients.remove(patient);
			FamilyDoctor famdoctor = patient.getFamDoc();
			sendEmail(famdoctor.email,patient.getName(),patient.medicationsToString());
			
			boolean flag= patientList.remove(patient);
			patient=null;
			return flag;
		}
		else {
			return false;
		}
		
	}
	
	/*
	 * this method recieves a patient ID and returns the patient if any match exists with their patient ID and returns null if doesnt 
	 * @param patientID is an int and it is the ID of the patient we need to return 
	 * @return returns the patient if it finds a match and returns null if there isnt any  
	 */
	
	public Patient searchPatient(int patientID) {
	    if (this.patientList != null) { 
	        for (Patient patient : this.patientList) { 
	            if (patient.getPatientID() == patientID) {
	                return patient; 
	            }
	        }
	    }
	    return null;
	}
	
	
	/*
	 * this method recieves a patient name and returns the patient if any match exists with their first name and returns null if doesnt 
	 * @param patient name is an String and it is the first name of the patient we need to return 
	 * @return returns the patient if it finds a match and returns null if there isnt any  
	 */

	public Patient searchPatientByName(String name) {
		 if (this.patientList != null) { 
		        for (Patient patient : this.patientList) { 
		            if (patient.getFirstName().equals(name)) {
		                return patient; 
		            }
		        }
		    }
		    return null; 
	}
	
	public static void sendEmail(String recipientEmail, String subject, String body) {
		 String username = "50tW1tcjvD6M";
	     String password = "fxJCwpafJjP3"; 
	     String senderEmail = "eecshospital@gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailsnag.com"); 
        props.put("mail.smtp.port", "2525"); 

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

	
	
	
	
}

/**
 * this class is an exception that is thrown if there is no more space left 
 * for admiting patients either because the number of patients is at 500 
 * or because there are not any physicians available
 * extends Exception
 *
 */
class NoSpaceException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * this default constructor calls the superClass of Exception
	 */
	public NoSpaceException(){
		super();
	}
	/**
	 * this overloaded constructor calls the overloaded constructor of Exception
	 * and sends a message as a String
	 * @param message a String that explains something about the Exception
	 */
	public NoSpaceException(String message){
		super(message);
	}
}
/**
 * this Class extends Exception and is used if a physician wants to resign but 
 * there is no other physician with the same specialty
 *
 */
class NoSpecialtyException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * this default constructor calls the superClass of Exception
	 */
	public NoSpecialtyException(){
		super();
	}
	/**
	 * this overloaded constructor calls the overloaded constructor of Exception
	 * and sends a message as a String
	 * @param message a String that explains something about the Exception
	 */
	public NoSpecialtyException(String message){
		super(message);
	}
}
