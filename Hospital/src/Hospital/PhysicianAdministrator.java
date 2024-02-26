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

public class PhysicianAdministrator {
    // Specialty of the PhysicianAdministrator, such as Immunology, Dermatology, or Neurology.
    private String specialty;
    List<String> Specialties = List.of("Radiology", "Dermatology", "Neurology", "Cardiology", "Immunology", "Gynecology", "Ophthalmology","Pathology", "Urology","Hematology");
    
    // List of physicians managed by the PhysicianAdministrator
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
    	 super(); 
    }
  
    
    
    /**
     * Sets the specialty type for the administrator if it is valid.
     * Specialties are "Radiology", Dermatology, "Neurology", "Cardiology", "Immunology", "Gynecology", "Ophthalmology","Pathology", "Urology","Hematology".
     *
     * @param specialty The specialty to be set for the administrator.
     * @throws IllegalArgumentException if the provided specialty is not valid.
     */
    

    public void setAdminSpecialtyType(String specialty) throws IllegalArgumentException {
       if (Specialties.contains(specialty)) {
            this.specialty = specialty;
        } 
        else {
            throw new IllegalArgumentException("Invalid specialty");
        }
    }
    
    /**
     * Retrieves the specialty type of the administrator.
     *
     * @return The specialty type of the administrator.
     */
    public String getAdminSpecialtyType() {
        return specialty;
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
    	
    	 if (physician.getSpecialty().equals(this.specialty) && !physicians.contains(physician) && physicians.size() < 25) {
             physicians.add(physician);
            physician.setAdmin(this);
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
    	
    	if (physicians.contains(physician)) {
            physicians.remove(physician);
            physician.setAdmin(null);
            return true;
        }
        return false;
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
