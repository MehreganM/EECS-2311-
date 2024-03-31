package Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Hospital.VitalSigns;

class VitalSignsTest {

	static VitalSigns vitalsigns;
	static VitalSigns vitalsigns1;
	static VitalSigns vitalsigns2;
	
	@BeforeAll
	static void SetUp() {
		// Correct values --> To be modified copy
		vitalsigns = new VitalSigns(37.5, 120, 80, 70, 18);
		
		// Correct values --> original copy
		vitalsigns1 = new VitalSigns(37.5, 120, 80, 70, 18);

		// Incorrect values: less than the lower bound
//	//	vitalsigns1 = new VitalSigns(27.5, -10, -10, -10, 7);
//		// Incorrect values: greater than the upper bound
//		vitalsigns2 = new VitalSigns(47.5, 220, 220, 220, 70);
		
	}
	
	// This test for getting the temperature
	@Test
	public void getTemperatureTest() {
        assertEquals(37.5, vitalsigns1.getTemperature(), 0.01);
	}
	
	// This test must pass
		@Test
		public void setTemperatureTest() {
			vitalsigns.setTemperature(38.5);
	        assertEquals(38.5, vitalsigns.getTemperature(), 0.01);
	}
		
	// This test for lower bound for temperature
	@Test
	public void setTemperatureTest1() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setTemperature(27.5);
			vitalsigns.getTemperature();
		});
	}		
	// This test for upper bound for temperature
	@Test
	public void setTemperatureTest2() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setTemperature(47.5);
			vitalsigns.getTemperature();
		});
	}
	
	
	// This test for getting systolic Pressure
	@Test
	public void getSystolicPressureTest() {
        assertEquals(120, vitalsigns1.getSystolicPressure());
	}
	
	// This test for setting systolic Pressure	
	@Test
	public void setSystolicPressureTest() {
		vitalsigns.setSystolicPressure(130);
        assertEquals(130, vitalsigns.getSystolicPressure());
		
	}
	
	// This test for setting systolic Pressure 	-- lower bound, throw exception
	@Test
	public void setSystolicPressureTest1() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setSystolicPressure(-10);
			vitalsigns.getSystolicPressure();
		});
	}
	
	
	// This test for setting systolic Pressure -- upper bound, throw exception
	@Test
	public void setSystolicPressureTest2() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setSystolicPressure(210);
			vitalsigns.getSystolicPressure();
		});
	}

	// This test for getting diastolic Pressure
	@Test
	public void getDiastolicPressureTest() {
        assertEquals(80, vitalsigns1.getDiastolicPressure());
	}
	
	// This test for setting diastolic Pressure	
	@Test
	public void setDiastolicPressureTest() {
		vitalsigns.setDiastolicPressure(130);
        assertEquals(130, vitalsigns.getDiastolicPressure());
		
	}
	
	// This test for setting diastolic Pressure -- lower bound, throw exception
	@Test
	public void setDiastolicPressureTest1() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setDiastolicPressure(-10);
			vitalsigns.getDiastolicPressure();
		});
	}
	
	// This test for setting diastolic Pressure	-- upper bound, throw exception
	@Test
	public void setDiastolicPressureTest2() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setDiastolicPressure(210);
			vitalsigns.getDiastolicPressure();
		});
	}
	
	
	// This test for getting heart rate
	@Test
	public void getHeartRateTest() {
        assertEquals(70, vitalsigns1.getHeartRate());
	}
	
	// This test for setting heart rate Pressure	
	@Test
	public void setHeartRateTest() {
		vitalsigns.setHeartRate(20);
        assertEquals(20, vitalsigns.getHeartRate());
		
	}
	
	// This test for setting heart rate Pressure -- lower bound, throw exception
	@Test
	public void setHeartRateTest1() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setHeartRate(-10);
			vitalsigns.getHeartRate();
		});
	}
	
	// This test for setting heart rate Pressure -- upper bound, throw exception
	@Test
	public void setHeartRateTest2() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setHeartRate(210);
			vitalsigns.getHeartRate();
		});
	}
	
	
	// This test for getting respiratory rate
	@Test
	public void getRespiratoryRate() {
        assertEquals(18, vitalsigns1.getRespiratoryRate());
	}
	
	// This test for setting respiratory rate Pressure	
	@Test
	public void setRespiratoryRateTest() {
		vitalsigns.setRespiratoryRate(30);
        assertEquals(30, vitalsigns.getRespiratoryRate());
		
	}
	
	// This test for setting respiratory rate Pressure -- lower bound, throw exception
	@Test
	public void setRespiratoryRateTest1() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setRespiratoryRate(5);
			vitalsigns.getRespiratoryRate();
		});
	}
	
	// This test for setting respiratory rate Pressure -- upper bound, throw exception
	@Test
	public void setRespiratoryRateTest2() {
		assertThrows(IllegalArgumentException.class, () -> {
			vitalsigns.setRespiratoryRate(70);
			vitalsigns.getRespiratoryRate();
		});
	}
	
	
}
