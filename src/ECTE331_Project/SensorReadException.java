package ECTE331_Project;

import java.io.IOException;

/**
 * Exception thrown when a sensor fails to produce
 * a valid altitude reading.
 *
 * @author Amritaa
 * @version 1.0
 */
public class SensorReadException extends IOException {

    /**
     * Creates a new SensorReadException.
     *
     * @param message description of the sensor failure
     */
    public SensorReadException(String message) {

        super(message);

    }
}