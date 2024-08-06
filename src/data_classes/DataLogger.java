package data_classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataLogger {
	
	// DataLogger fields
	private final Logger logger;
	private final String logFileName;
	
	
	// constructor
	public DataLogger(String className, String logFileName) {
		logger = Logger.getLogger(className);
		this.logFileName = logFileName;
		initializeLogger();
	}
	
	
	// method to initialize logger
	private void initializeLogger() {
		try {
            // Create logs directory if it does not exist
            Files.createDirectories(Paths.get("logs"));
			// specifies path to logs directory
			Handler fileHandler = new FileHandler("logs/" + logFileName, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);
			logger.setUseParentHandlers(false);
		}
		catch(IOException e) {
			logger.log(Level.SEVERE, "Failed to nitialize logger handler.", e);
		}
	}

	
	// method to log BankAccount transactions
	public void logTransaction(BankAccount account, String action, double amount) { 
		String message = String.format("Account No: %d - %s: $%.2f, New Balance: $%.2f",
				account.getAccNumber(), action, amount, account.getBalance());
		logger.log(Level.INFO, message);
	}

}
