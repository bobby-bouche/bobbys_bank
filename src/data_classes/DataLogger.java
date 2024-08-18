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
	private final String logFileName;
	private FileHandler fileHandler;
	
	
	// constructor
	public DataLogger(int accountNumber) {
		logger = Logger.getLogger("BankAccountLogger");
		this.logFileName = "logs/Account_" + accountNumber + ".log";
		initializeLogger();
	}
	
	
	// method to initialize logger
	private void initializeLogger() {
		try {
            // Create logs directory if it does not exist
            Files.createDirectories(Paths.get("logs"));
			// specifies path to logs directory
			fileHandler = new FileHandler(logFileName, true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);
			logger.setUseParentHandlers(false);
		}
		catch(IOException e) {
			logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
		}
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
