package ECTE331_Project;

public class DroneSystemDriver {
	public static void main(String[] args) {
		Sensor sensorA = new Sensor("A");
		Sensor sensorB = new Sensor("B");
		Sensor sensorC = new Sensor("C");
		int previousAltitude = 100 ; //fallbak value for now
		int failureCount = 0;
		boolean run = true; 
		try {
			int value = sensorA.readSensor();
			System.out.println(value);
		}
		catch(SensorReadException e){
			System.out.println(e.getMessage());
		}
	}
}
