package Hospital;
import java.util.ArrayList;
import java.util.List;


/**
 * The PhysicianAdministrator class extends the Administrator class and is responsible for
 * managing a list of physicians with a specific specialty. It allows for adding and removing
 * physicians as long as they share the same specialty as the administrator.
 * 
 * @author Parmoun Khalkhali Sharifi
 */

public class PhysicianAdministrator extends Administrator {
    // Specialty of the PhysicianAdministrator, such as Immunology, Dermatology, or Neurology.
    private String specialty = "";
    
    // List of physicians managed by the PhysicianAdministrator.
    private ArrayList<Physician> physicians = new ArrayList<Physician>();

    
    
    /**
     * Default constructor for PhysicianAdministrator which calls the parent class constructor.
     */
    
    public PhysicianAdministrator() {
        super();
    }

    
    
    /**
     * Overloaded constructor for PhysicianAdministrator that sets the administrator's details.
     *
     * @param firstName The first name of the administrator.
     * @param lastName  The last name of the administrator.
     * @param age       The age of the administrator.
     * @param gender    The gender of the administrator.
     * @param address   The address of the administrator.
     */
    
    public PhysicianAdministrator(String firstName, String lastName, int age, String gender, String address) {
        super(firstName, lastName, age, gender, address);
    }

    
    
    /**
     * Sets the specialty type for the administrator if it is valid.
     * Valid specialties are Immunology, Dermatology, or Neurology.
     *
     * @param specialty The specialty to be set for the administrator.
     * @throws IllegalArgumentException if the provided specialty is not valid.
     */
    
    public void setAdminSpecialtyType(String specialty) {
        if (specialty.equals("Immunology") || specialty.equals("Dermatology") || specialty.equals("Neurology")) {
            this.specialty = specialty;
        } else {
            throw new IllegalArgumentException("Specialty must be either Immunology, Dermatology, or Neurology.");
        }
    }

    
    
    /**
     * Retrieves the specialty type of the administrator.
     *
     * @return The specialty type of the administrator.
     */
    public String getAdminSpecialtyType() {
        return this.specialty;
    }

    
    
    
    /**
     * Attempts to add a physician to the list of managed physicians. A physician can only be
     * added if their specialty matches the administrator's, they are not already managed by
     * this administrator, and the list of managed physicians has fewer than 25 entries.
     *
     * @param physician The physician to add.
     * @return true if the physician was successfully added, false otherwise.
     */
     
    public boolean addPhysician(Physician physician) {
        if (!physicians.contains(physician) && physicians.size() < 25 && physician.getSpecialty().equals(this.specialty)) {
            physicians.add(physician);
            physician.setAdministrator(this);
            return true;
        }
        return false;
    }

    
    
    /**
     * Removes a physician from the list of managed physicians.
     *
     * @param physician The physician to remove.
     * @return true if the physician was in the list and was removed, false otherwise.
     */
    
    public boolean deletePhysician(Physician physician) {
        return physicians.remove(physician);
    }

    
    
    /**
     * Extracts and returns a new list containing all the managed physicians.
     * This provides a copy of the list to prevent external modification.
     *
     * @return A new list containing all managed physicians.
     */
    
    public List<Physician> extractPhysician() {
        return new ArrayList<>(physicians);
    }
}
