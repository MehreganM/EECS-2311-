package Hospital;

import java.util.ArrayList;

/**
 * Encapsulates the main functionality of a hospital management application,
 * focusing on personnel management including physicians, administrators, patients, and volunteers.
 */
public class HospitalApp {
    private ArrayList<Physician> physicianList = new ArrayList<>();
    private ArrayList<PhysicianAdministrator> adminList = new ArrayList<>();
    private ArrayList<Patient> patientList = new ArrayList<>();
    private ArrayList<Volunteer> volunteerList = new ArrayList<>();

    /**
     * Attempts to hire a physician if the hospital has not reached its capacity.
     * Ensures that the physician is not already hired.
     *
     * @param physician The physician to be hired.
     * @return true if the physician was successfully hired, false otherwise.
     */
    public boolean hirePhysician(Physician physician) {
        if (physicianList.size() < 60 && !physicianList.contains(physician)) {
            boolean hired = adminList.stream().anyMatch(admin -> admin.hirePhysician(physician));
            if (hired) {
                physicianList.add(physician);
                return true;
            }
        }
        return false;
    }
}

/**
 * Base class for all individuals involved in the hospital system.
 */
abstract class Person {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected String gender;
    protected String address;
}

/**
 * Abstract base class for all employees, extending the Person class.
 */
abstract class Employee extends Person {
    protected static int employeeID = 99; // Tracks the next employee ID
    protected int employee; // Unique ID for each employee
}

/**
 * Represents a volunteer in the hospital.
 */
class Volunteer extends Employee {
    // Implementation details for a volunteer
}

/**
 * Represents a patient in the hospital.
 * Implements Comparable for sorting based on patient attributes.
 */
class Patient extends Person implements Comparable<Patient> {
    private static int patientIdCounter = 1000; // Tracks the next patient ID
    private int patientId;
    private String patientType;

    /**
     * Constructor for Patient.
     */
    public Patient() {
        this.patientId = patientIdCounter++;
    }

    @Override
    public int compareTo(Patient other) {
        // Example comparison based on patient ID
        return Integer.compare(this.patientId, other.patientId);
    }

    /**
     * Returns the full name of the patient.
     * @return A String representing the patient's full name.
     */
    public String getName() {
        return firstName + " " + lastName;
    }
}

/**
 * Represents a physician administrator responsible for hiring physicians and managing patient assignments.
 */
class PhysicianAdministrator extends Administrator {
    private String specialty;
    private ArrayList<Physician> physiciansUnderManagement = new ArrayList<>();

    public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String address) {
        super.firstName = firstName;
        super.lastName = lastName;
        super.age = age;
        super.gender = gender;
        super.address = address;
        this.employee = ++Employee.employeeID;
    }

    /**
     * Sets the specialty of the administrator if it matches predefined criteria.
     *
     * @param specialty The specialty to be set.
     * @throws IllegalArgumentException If the specialty does not match predefined types.
     */
    public void setAdminSpecialtyType(String specialty) {
        if (specialty.equals("Immunology") || specialty.equals("Dermatology") || specialty.equals("Neurology")) {
            this.specialty = specialty;
        } else {
            throw new IllegalArgumentException("Invalid specialty type.");
        }
    }

    /**
     * Attempts to hire a physician based on the hospital's criteria.
     *
     * @param physician The physician candidate to hire.
     * @return true if the hiring process is successful, false otherwise.
     */
    public boolean hirePhysician(Physician physician) {
        return false;
    }

    /**
     * Lists the assigned staff for a given patient.
     *
     * @param patient The patient whose assigned staff is to be listed.
     * @return A string listing the assigned staff.
     */
    public String listAssignedStaffToPatient(Patient patient) {
       
        return "";
    }
}

/**
 * Abstract representation of a physician, capable of admitting and managing patients.
 */
abstract class Physician extends Employee implements Comparable<Physician> {
    private PhysicianAdministrator administrator; // The admin responsible for this physician

    /**
     * Sets the administrator responsible for this physician.
     *
     * @param administrator The administrator to be assigned.
     */
    public void setAdministrator(PhysicianAdministrator administrator) {
        this.administrator = administrator;
    }

}
