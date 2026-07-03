package ECTE331_Project;

import java.util.Random;

/**
 * Simulates an altitude sensor for the autonomous drone.
 * A sensor can return a valid reading, a corrupted reading,
 * or fail by throwing a SensorReadException.
 *
 * @author Amritaa
 * @version 1.0
 */
public class Sensor {
    // Sensor identifier (A, B or C)
    private String SensorID;
    // Random number generator for simulation
    private Random random;
    /**
     * Creates a sensor with the specified identifier.
     *
     * @param SensorID sensor name
     */
    public Sensor(String SensorID) {

        this.SensorID = SensorID;
        random = new Random();
    }

    /**
     * Generates a simulated sensor reading.
     *
     * @return the sensor reading
     * @throws SensorReadException if the sensor fails
     */
    public int readSensor() throws SensorReadException {

        int chance = random.nextInt(100);
        int sensorValue;

        // Simulate a sensor failure
        if (chance < 15) {
            LoggerUtility.log(SensorID + " failed!!!");
            throw new SensorReadException(SensorID + " failed!!!");
        }
        // Simulate a corrupted reading
        else if (chance < 30) {
            int randomCorrupted;
            randomCorrupted = 201 + random.nextInt(300);
            return randomCorrupted;
        }
        // Generate a valid reading
        else {
            sensorValue = random.nextInt(201);
        }
        LoggerUtility.log("Sensor " + SensorID + ": " + sensorValue);
        return sensorValue;
    }
    /**
     * Returns the sensor identifier.
     *
     * @return sensor ID
     */
    public String getSensorID() {
        return SensorID;
    }
}
