package Hospital;
/**
 * 
 * @author Parmoun
 * This class gives the functionality of prescribing medicines to the patient
 * the patient will be found using his/her specific ID
 * 
 */
public class Prescription {
	
	    private int prescriptionId;
	    private int patientId;
	    private String physicianName;
	    private String medicationName;
	    private String dosage;
	    private String instructions;

	    // Constructor
	    public Prescription(int patientId, String physicianName, String medicationName, String dosage, String instructions) {
	        this.patientId = patientId;
	        this.physicianName = physicianName;
	        this.medicationName = medicationName;
	        this.dosage = dosage;
	        this.instructions = instructions;
	    }

	

		public int getPrescriptionId() {
			return prescriptionId;
		}

		public void setPrescriptionId(int prescriptionId) {
			this.prescriptionId = prescriptionId;
		}

		public int getPatientId() {
			return patientId;
		}

		public void setPatientId(int patientId) {
			this.patientId = patientId;
		}

		public String getPhysicianName() {
			return physicianName;
		}

		public void setPhysicianName(String physicianName) {
			this.physicianName = physicianName;
		}

		public String getMedicationName() {
			return medicationName;
		}

		public void setMedicationName(String medicationName) {
			this.medicationName = medicationName;
		}

		public String getDosage() {
			return dosage;
		}

		public void setDosage(String dosage) {
			this.dosage = dosage;
		}

		public String getInstructions() {
			return instructions;
		}

		public void setInstructions(String instructions) {
			this.instructions = instructions;
		}

		
	    
}
