package ECTE331_Project;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtility {
	private static final String fileName = "logs/log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))+ ".txt";
	public static void log(String message) {
		try {
			FileWriter writer = new FileWriter(fileName,true);
			writer.write("[" + LocalDateTime.now() + "]" + message + "\n");
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Error writing to log file");
		}
	}
}
