package ECTE331_Project;

import java.util.Random;
public class Sensor {
	private String SensorID;
	private Random random;
	public Sensor(String SensorID){
		this.SensorID = SensorID;
		random = new Random();
	}
	public int readSensor() throws SensorReadException {
		int chance = random.nextInt(100);
		int sensorValue;
		if(chance < 15) {
			LoggerUtility.log(SensorID + "failed!!!");
			throw new SensorReadException(SensorID + " failed!!!");
		}
		else if(chance < 30) {
			int randomCorrupted;
			randomCorrupted = 201 + random.nextInt(300);
			LoggerUtility.log("Corrupted Sensor Reading of "+ randomCorrupted + " in Sensor: "+ SensorID );
			return randomCorrupted;
		}
		else {
			sensorValue = random.nextInt(201);
		}
		LoggerUtility.log(SensorID + ": " + sensorValue);
		return sensorValue;
	}
	public String getSensorID() {
		return SensorID;
	}
}
