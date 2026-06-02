package ECTE331_Project;

public class DroneSystemDriver {
	public static void main(String[] args) {
		Sensor sensorA = new Sensor("A");
		Sensor sensorB = new Sensor("B");
		Sensor sensorC = new Sensor("C");
		int pastAltitude = 100 ; //fallback value for now
		int failureCount = 0;
		boolean run = true; 
		boolean failureA = false;
		boolean failureB = false;
		boolean failureC = false;
		int a = -2, b = -2, c = -2; //invalid initial value given
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
		if ((a==b)& validValueA & validValueB) {
			presentAltitude = a;
			majority = true;
			System.out.printf("Majority voting successful! Sensor A = " + a + ", Sensor B = " + b + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			LoggerUtility.log("Majority voting successful! Sensor A = " + a + ", Sensor B = " + b + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
		}
		else if ((a==c)& validValueA & validValueC) {
			presentAltitude = c;
			majority = true;
			System.out.printf("Majority voting successful! Sensor A = " + a + ", Sensor C = " + c + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			LoggerUtility.log("Majority voting successful! Sensor A = " + a + ", Sensor C = " + c + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
		}
		else if ((b==c)& validValueB & validValueC) {
			presentAltitude = b;
			majority = true;
			System.out.printf("Majority voting successful! Sensor B = " + b + ", Sensor C = " + c + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
			LoggerUtility.log("Majority voting successful! Sensor B = " + b + ", Sensor C = " + c + ". FINAL ALTITUDE SELECTED: " + presentAltitude + "m");
		}
	}
}
