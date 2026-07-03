package ECTE331_Project;
/**
 * Driver class for the Fault-Tolerant Autonomous Drone Navigation System.
 *
 * This program simulates three redundant altitude sensors and uses
 * Triple Modular Redundancy (TMR) majority voting to determine the
 * final altitude. The system monitors reliability, detects sensor
 * failures and corrupted readings, logs important events, and enters
 * SAFE MODE after two consecutive reliability failures.
 *
 * @author Amritaa
 * @version 1.0
 */
public class DroneSystemDriver {
	/**
	 * Starts the drone navigation simulation.
	 *
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create the three redundant sensors
		Sensor sensorA = new Sensor("A");
		Sensor sensorB = new Sensor("B");
		Sensor sensorC = new Sensor("C");
		// Initial system values
		int pastAltitude = 100 ; //fallback value 
		boolean run = true; 
		int a = -2, b = -2, c = -2; //invalid initial value given
		int timeStep = 1;
		int consecutiveFailures = 0;
		System.out.printf("Starting the FAULT TOLERANT AUTONOMOUS DRONE NAVIGATION SYSTEM.....\n");
		LoggerUtility.log("Starting the FAULT TOLERANT AUTONOMOUS DRONE NAVIGATION SYSTEM.....");
		try {
		// Process each navigation cycle
		while (run) {
			int failureCount = 0;
			// Read the sensor values
			boolean failureA = false;
			boolean failureB = false;
			boolean failureC = false;
			System.out.printf("***"+ "Navigation Cycle: " + timeStep + "***\n");
			LoggerUtility.log("Navigation Cycle: " + timeStep);
			try {
				a = sensorA.readSensor();
				System.out.printf("Sensor A: " + a + "\n");
			}
			catch(SensorReadException e){
				failureA = true;
				failureCount++;
				System.out.println("SENSOR A FAILED!");
				LoggerUtility.log("SENSOR A FAILED!");
			}
			try {
				b = sensorB.readSensor();
				System.out.printf("Sensor B: " + b + "\n");
			}
			catch(SensorReadException e){
				failureB = true;
				failureCount++;
				System.out.println("SENSOR B FAILED!");
				LoggerUtility.log("SENSOR B FAILED!");
			}
			try {
				c = sensorC.readSensor();
				System.out.printf("Sensor C: " + c + "\n");
			}
			catch(SensorReadException e){
				failureC = true;
				failureCount++;
				System.out.println("SENSOR C FAILED!");
				LoggerUtility.log("SENSOR C FAILED!");
			}
			LoggerUtility.log("The number of faults: "+ failureCount );
			System.out.printf("The number of faults: " + failureCount + "\n");
			boolean validValueA = a >= 0 && a <= 200 && !failureA ;
			boolean validValueB = b >= 0 && b <= 200 && !failureB ;
			boolean validValueC = c >= 0 && c <= 200 && !failureC ;
			int presentAltitude = pastAltitude;
			boolean majority = false;
			boolean outlierA = false;
			boolean outlierB = false;
			boolean outlierC = false;
			// Detect corrupted sensor readings
			if (!failureA && (a < 0 || a > 200)) {
			    outlierA = true;
			    System.out.printf("OUTLIER detected: Sensor A value = " + a + "\n");
			    LoggerUtility.log("OUTLIER detected: Sensor A value = " + a + "\n");
			}
			if (!failureB && (b < 0 || b > 200)) {
			    outlierB = true;
			    System.out.printf("OUTLIER detected: Sensor B value = " + b + "\n");
			    LoggerUtility.log("OUTLIER detected: Sensor B value = " + b + "\n");
			}
			if (!failureC && (c < 0 || c > 200)) {
			    outlierC = true;
			    System.out.printf("OUTLIER detected: Sensor C value = " + c + "\n");
			    LoggerUtility.log("OUTLIER detected: Sensor C value = " + c + "\n");
			}
			// Apply majority voting
			if ((a==b)&& validValueA && validValueB) {
				presentAltitude = a;
				pastAltitude = presentAltitude;
				majority = true;
				System.out.printf("Majority voting successful! Sensor A = " + a + ", Sensor B = " + b + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m \n");
				LoggerUtility.log("Majority voting successful! Sensor A = " + a + ", Sensor B = " + b + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			}
			else if ((a==c)&& validValueA && validValueC) {
				presentAltitude = c;
				pastAltitude = presentAltitude;
				majority = true;
				System.out.printf("Majority voting successful! Sensor A = " + a + ", Sensor C = " + c + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m \n");
				LoggerUtility.log("Majority voting successful! Sensor A = " + a + ", Sensor C = " + c + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			}
			else if ((b==c)&& validValueB && validValueC) {
				presentAltitude = b;
				pastAltitude = presentAltitude;
				majority = true;
				System.out.printf("Majority voting successful! Sensor B = " + b + ", Sensor C = " + c + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m \n");
				LoggerUtility.log("Majority voting successful! Sensor B = " + b + ", Sensor C = " + c + ". | FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			}
			// Use the previous altitude if no majority is found
			if(majority == false) {
				presentAltitude = pastAltitude;
				System.out.printf("All sensor outputs differ, therefore fallback to previous altitude of: " + presentAltitude + "m \n");
				LoggerUtility.log("All sensor outputs differ, therefore fallback to previous altitude of: " + presentAltitude + "m");
			}
			// Check the overall system reliability
			int validSensorCount = 0;
			boolean reliabilityFailure = false;
			if(validValueA) {
				validSensorCount++;
			}
			if(validValueB) {
				validSensorCount++;
			}
			if(validValueC) {
				validSensorCount++;
			}
			if(validSensorCount < 2) {
				reliabilityFailure  = true;
				System.out.println("Reliability Failure: Less than 2 valid sensors!!!");
				LoggerUtility.log("Reliability Failure: Less than 2 valid sensors!!!");
			}
			else if(!majority) {
				reliabilityFailure = true;
				System.out.println("Reliability Failure: No majority found.");
				LoggerUtility.log("Reliability Failure: No majority found.");
			}
			// Keep track of consecutive reliability failures
			if(reliabilityFailure) {
				consecutiveFailures++;
				System.out.printf("Consecutive Reliability Failures: "+ consecutiveFailures + "\n");
				LoggerUtility.log("Consecutive Reliability Failures: "+ consecutiveFailures);
			}
			else {
				consecutiveFailures = 0;
				LoggerUtility.log("Reliability Status: HEALTHY!");
			}
			// Enter SAFE MODE after two consecutive failures
			if(consecutiveFailures >= 2) {
				throw new SystemReliabilityException("Navigating into SAFE MODE!");
			}
			if(timeStep>=10) {
				run = false;
				break;
			}
			timeStep++;
		}
		}
		// Handle SAFE MODE activation
		catch (SystemReliabilityException e) {
			System.out.println("\n-----SAFE MODE ACTIVATED!-----");
			System.out.println(e.getMessage());
			LoggerUtility.log("-----SAFE MODE ACTIVATED!-----");
			LoggerUtility.log(e.getMessage());
		}
		// End the simulation
		System.out.println("SYSTEM SHUTDOWN COMPLETE.");
		LoggerUtility.log("SYSTEM SHUTDOWN COMPLETE.");
		}
	}