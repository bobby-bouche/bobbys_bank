package data_classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DataLogger {
	
	private final Logger logger;
	private FileHandler fileHandler;
	
	
	// Constructor
	@SuppressWarnings("unused")
	public DataLogger(int accountNumber) {
	    logger = Logger.getLogger("BankAccountLogger_" + accountNumber);
	    String logFileName = "logs/Account_" + accountNumber + ".log";
	    FileHandler tempFileHandler = null;

	    try {
	        // Create logs directory if it does not exist
	        Files.createDirectories(Paths.get("logs"));

	        // Specify path to logs directory
	        tempFileHandler = new FileHandler(logFileName, true);
	        tempFileHandler.setFormatter(new SimpleFormatter());

	        // Add handler and configure logger
	        logger.addHandler(tempFileHandler);
	        logger.setLevel(Level.INFO);
	        logger.setUseParentHandlers(false); // Disable console logging

	    } catch (IOException e) {
	        // Ensure that tempFileHandler is closed if it was created
	        if (tempFileHandler != null) {
	            tempFileHandler.close();
	        }
	        logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
	    }

	    // Assign the initialized fileHandler to the field
	    this.fileHandler = tempFileHandler;
	}


	
	// method to log BankAccount transactions
	public void logTransaction(BankAccount account, String action, double amount) { 
		String message = String.format("Account No: %d - %s: $%.2f, New Balance: $%.2f",
				account.getAccNumber(), action, amount, account.getBalance());
		logger.log(Level.INFO, message);
	}
	
	
	// close the logger 
	public void close() {
		if(fileHandler != null) {
			fileHandler.close();
		}
	}

}
