package Hospital;


	import java.util.*;

	/**
	 * This is a hospital management application.
	 * 
	 * @author Parmoun Khalkhali Sharifi
	 * This class encapsulates the main functionality of the hospital application, including
	 * managing directors, physicians, administrators, patients, and volunteers.
	 * This part of code is not fully implemented and only implements the administrator roles and description
	 */
	public class HospitalApp {
	    // Director of the hospital
	    private Director director;
	    // Lists to store physicians, administrators, patients, and volunteers
	    private ArrayList<Physician> physicianList = new ArrayList<>();
	    private ArrayList<PhysicianAdministrator> adminList = new ArrayList<>();
	    private ArrayList<Patient> patientList = new ArrayList<>();
	    private ArrayList<Volunteer> volunteerList = new ArrayList<>();

	    /**
	     * Constructs a HospitalApp instance with a specified director.
	     * @param director The director of the hospital.
	     */
	    public HospitalApp(Director director) {
	        this.director = director;
	    }

	    /**
	     * Returns the director of the hospital.
	     * @return The current director.
	     */
	    public Director getHospDirector() {
	        return director;
	    }

	    /**
	     * Sets the director of the hospital.
	     * @param director The new director to be set.
	     */
	    public void setHospDirector(Director director) {
	        this.director = director;
	    }

	    /**
	     * Adds an administrator to the hospital after approval from the director.
	     * @param admin The administrator to add.
	     * @return true if the administrator was successfully added, false otherwise.
	     */
	    public boolean addAdministrator(PhysicianAdministrator admin) {
	        boolean flag = director.assignAdministrator(admin);
	        if (flag) {
	            adminList.add(admin);
	        }
	        return flag;
	    }

	    /**
	     * Hires a physician if the total number of physicians is less than 70 and the physician is not already hired.
	     * @param physician The physician to hire.
	     * @return true if the physician was successfully hired, false otherwise.
	     */
	    public boolean hirePhysician(Physician physician) {
	        if (physicianList.size() < 70) {
	            for (Physician existingPhysician : physicianList) {
	                if (physician.equals(existingPhysician)) {
	                    return false;
	                }
	            }
	            boolean flag = false;
	            for (PhysicianAdministrator admin : adminList) {
	                flag = admin.hirePhysician(physician);
	                if (flag) {
	                    physicianList.add(physician);
	                    break;
	                }
	            }
	            return flag;
	        } else {
	            return false;
	        }
	    }

	    /**
	     * Admits a patient to the hospital if there is space. Each physician can have up to 8 patients.
	     * Throws NoSpaceException if there is no space for the patient.
	     * @param patient The patient to admit.
	     * @return true if the patient was successfully admitted, false otherwise.
	     * @throws NoSpaceException If the hospital or any physician can't accommodate the patient.
	     */
	    public boolean admitPatient(Patient patient) throws NoSpaceException {
	        if (patientList.size() < 500) {
	            for (Patient existingPatient : patientList) {
	                if (patient.equals(existingPatient)) {
	                    return false;
	                }
	            }
	            boolean flag = false;
	            for (Physician physician : physicianList) {
	                if (physician.getPatients().size() < 8) {
	                    flag = physician.addPatient(patient);
	                    if (flag) {
	                        patientList.add(patient);
	                        break;
	                    }
	                }
	            }
	            if (!flag) {
	                throw new NoSpaceException();
	            }
	            return flag;
	        } else {
	            throw new NoSpaceException();
	        }
	    }

	}

	/**
	 * Base class for all persons involved in the hospital system.
	 */
	abstract class Person {
	    protected String firstName;
	    protected String lastName;
	    protected int age;
	    protected String gender;
	    protected String address;
	}

	/**
	 * Base class for all employees in the hospital, extending Person.
	 */
	abstract class Employee extends Person {
	    protected static int employeeID = 99; // Static employee ID counter
	    protected int employee; // Individual employee ID
	}

	/**
	 * Represents a volunteer in the hospital, extending the Employee class.
	 */
	class Volunteer extends Employee {
	}

	/**
	 * Represents an employee with a salary, extending Employee.
	 */
	abstract class SalariedEmployee extends Employee {
	    protected double salary; // Employee's salary
	}

	/**
	 * Represents a patient in the hospital, extending Person and implementing Comparable for sorting.
	 */
	class Patient extends Person implements Comparable<Patient> {
	    private static int patientId = 999; // Static patient ID counter
	    private int patient;

	 // Individual patient ID
	    private String patientType; // Type of patient

	    @Override
	    public int compareTo(Patient other) {
	        return this.getName().compareTo(other.getName());
	    }

		private Physician getName() {
			
			return null;
		}
	}

	/**
	 * Represents an administrator with salary, specializing in managing physicians.
	 */
	abstract class Administrator extends SalariedEmployee {
	}

	/**
	 * Represents a physician administrator, capable of hiring physicians and managing patient assignments.
	 */
	class PhysicianAdministrator extends Administrator {
	    private String specialty = ""; // Administrator's specialty
	    private ArrayList<Physician> physicians = new ArrayList<>(); // List of physicians under this administrator

	    public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String address) {
	        super.firstName = firstName;
	        super.lastName = lastName;
	        super.age = age;
	        super.gender = gender;
	        super.address = address;
	        Employee.employeeID++;
	        super.employee = Employee.employeeID;
	    }

	    public void setAdminSpecialtyType(String specialty) {
	        // Only allows setting specialty if it matches predefined types
	        if (specialty.equals("Immunology") || specialty.equals("Dermatology") || specialty.equals("Neurology")) {
	            this.specialty = specialty;
	        } else {
	            throw new IllegalArgumentException("Invalid specialty type.");
	        }
	    }

	    public String getAdminSpecialtyType() {
	        return this.specialty;
	    }

	    public boolean hirePhysician(Physician physician) {
	        // Logic to hire a physician based on specialty match and capacity
	        return false; 
	    }

	    public String listAssignedStaffToPatient(Patient patient) {
	        // Logic to list staff assigned to a specific patient
	        return ""; 
	    }
	}

	/**
	 * Represents the director of the hospital, capable of assigning administrators.
	 */
	class Director extends Administrator {
	    public boolean assignAdministrator(PhysicianAdministrator admin) {
	        // Implementation logic to assign an administrator
	        return true; 
	    }
	}

	/**
	 * Represents a physician, who can admit and manage patients.
	 */
	class Physician extends SalariedEmployee implements Comparable<Physician> {
	    private String specialty = ""; // Physician's specialty
	    private ArrayList<Patient> patients = new ArrayList<>(); // List of patients under this physician
	    private PhysicianAdministrator admin; // Administrator overseeing this physician
	    private ArrayList<Volunteer> volunteers = new ArrayList<>(); // Volunteers assisting this physician

	    @Override 
	    public int compareTo(Physician other) {
	        // Logic for comparing physicians, typically by name or specialty
	        return 0;   
	    } 

	    public boolean addPatient(Patient patient) {
	        // Logic to add a patient to the physician's list
	        return true; 
	    }

	    public ArrayList<Patient> getPatients() {
	        return patients;
	    }

	    public void setAdministrator(PhysicianAdministrator admin) {
	        this.admin = admin;
	    }
	}

	/**
	 * Exception to be thrown when there is no space to admit a patient.
	 */
	class NoSpaceException extends Exception {
	}

	/**
	 * Exception to be thrown when a physician's or administrator's specialty does not match the required specialty.
	 */
	class NoSpecialtyException extends Exception {
	}

	/**
	 * Exception to be thrown when there are no volunteers available.
	 */
	class NoVolunteersException extends Exception {
	}


