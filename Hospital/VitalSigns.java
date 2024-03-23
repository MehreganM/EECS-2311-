
package Hospital;


/**
 * The class is vitalSigns class that is responsible for storing and retrieving the vital signs of the 
 * patient. It stores patient's temperature, systolic Pressure, diastolic Pressure, heartRate, and 
 * RespiratoryRate.
 * 
 * @author Amira Mohamed
 */
public class VitalSigns {
	
	// Class attributes
	private double Temperature;
	private int systolicPressure;
	private int diastolicPressure; 
	private int heartRate;
	private int RespiratoryRate;
	
	/**
	 * This is the overloaded constructor the takes the values for the vital signs as parameters
	 *  and sets them in their corresponding attributes
	 * @param temperature is the temperature of the patient 
	 * @param systolicPressure is the systolic pressure of the patient 
	 * @param diastolicPressure is the diastolic pressure of the patient 
	 * @param heartRate is the heart rate of the patient 
	 * @param respiratoryRate is the respiratory rate of the patient 
	 */
	public VitalSigns(double temperature, int systolicPressure, int diastolicPressure, int heartRate,
			int respiratoryRate) {
		Temperature = temperature;
		this.systolicPressure = systolicPressure;
		this.diastolicPressure = diastolicPressure;
		this.heartRate = heartRate;
		RespiratoryRate = respiratoryRate;
	}

	/**
	 * This method return the temperature of the patient 
	 * @return the temperature of the current patient 
	 */
	public double getTemperature() {
		return Temperature;
	}


	/**
	 * This method sets the temperature of the patient 
	 * @param temperature is the temperature that we want to set for the patient
	 */
	public void setTemperature(double temperature) {
		Temperature = temperature;
	}

	/**
	 * This method is to return the systolic pressure value of the patient 
	 * @return the systolic pressure of the patient 
	 */
	public int getSystolicPressure() {
		return systolicPressure;
	}

	/**
	 * This method sets the systolic pressure of the patient 
	 * @param systolicPressure is the value of systolic pressure that we want to set for the patient
	 */
	public void setSystolicPressure(int systolicPressure) {
		this.systolicPressure = systolicPressure;
	}

	/**
	 * This method is to return the diastolic pressure value of the patient 
	 * @return the diastolic pressure of the patient 
	 */
	public int getDiastolicPressure() {
		return diastolicPressure;
	}

	/**
	 * This method sets the diastolic pressure of the patient 
	 * @param systolicPressure is the value of diastolic pressure that we want to set for the patient
	 */
	public void setDiastolicPressure(int diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}


	/**
	 * This method is to return the heart rate of the patient 
	 * @return the heart rate of the current patient
	 */
	public int getHeartRate() {
		return heartRate;
	}

	/**
	 * This method sets the heart rate of the patient 
	 * @param heartRate is the value of heart rate that we want to set for the patient
	 */
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	
	/**
	 * This method is to return the respiratory rate of the patient 
	 * @return the respiratory rate of the current patient
	 */
	public int getRespiratoryRate() {
		return RespiratoryRate;
	}

	/**
	 * This method sets the respiratory rate of the patient 
	 * @param respiratoryRate is the value of respiratory rate that we want to set for the patient
	 */
	public void setRespiratoryRate(int respiratoryRate) {
		RespiratoryRate = respiratoryRate;
	} 
	
	// Method to display vital signs information
	/**
	 * This method is to display the vital signs of the patient
	 */
    public void displayVitalSigns() {
        System.out.println("Temperature: " + Temperature);
        System.out.println("Systolic Pressure: " + systolicPressure);
        System.out.println("Diastolic Pressure: " + diastolicPressure);
        System.out.println("Blood Pressure: " + systolicPressure + "/" + diastolicPressure);
        System.out.println("Heart Rate: " + heartRate);
        System.out.println("Respiratory Rate: " + RespiratoryRate);
    }
	
	
	

}
