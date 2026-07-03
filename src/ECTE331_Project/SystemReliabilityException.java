package ECTE331_Project;

/**
 * Exception thrown when the drone system enters
 * SAFE MODE due to consecutive reliability failures.
 *
 * @author Amritaa
 * @version 1.0
 */
public class SystemReliabilityException extends Exception {

    /**
     * Creates a new SystemReliabilityException.
     *
     * @param message description of the reliability failure
     */
    public SystemReliabilityException(String message) {

        super(message);

    }
}