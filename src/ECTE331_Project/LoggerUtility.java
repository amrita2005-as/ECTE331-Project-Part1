package ECTE331_Project;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Utility class used to write timestamped events
 * to the application log file.
 *
 * @author Amritaa
 * @version 1.0
 */
public class LoggerUtility {
	// Format used for timestamps in the log
	private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 // Create a unique log file for each program run
	private static final String fileName = "logs/log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))+ ".txt";
	/**
	     * Writes a timestamped message to the log file.
	     *
	     * @param message event to be written to the log
	     */
	public static void log(String message) {
		try {
			FileWriter writer = new FileWriter(fileName,true);
			writer.write("[" + LocalDateTime.now().format(FORMAT) + "]" + message + "\n");
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Error writing to log file");
		}
	}
}
