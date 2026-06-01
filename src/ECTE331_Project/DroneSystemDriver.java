package ECTE331_Project;

public class DroneSystemDriver {
	public static void main(String[] args) {
		Sensor sensorA = new Sensor("A");
		Sensor sensorB = new Sensor("B");
		Sensor sensorC = new Sensor("C");
		int previousAltitude = 100 ; //fallbak value for now
		int failureCount = 0;
		boolean run = true; 
		boolean failureA = false;
		boolean failureB = false;
		boolean failureC = false;
		int a = -2, b = -2, c = -2;
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
		System.out.printf("The number of faults: " + failureCount);
	}
}
