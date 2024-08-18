/**
 * The DataLogger class is responsible for logging transactions and other account-related 
 * activities to a file. Each instance of DataLogger is associated with a specific bank 
 * account and creates a log file named after the account number. The logs are stored in 
 * the "logs" directory, which is automatically created if it does not exist.
 * 
 * Responsibilities:
 * - Create and manage log files for bank accounts.
 * - Log transactions including the action performed, the amount involved, and the new balance.
 * - Ensure proper closure of log file handlers after logging is complete.
 * 
 * Constructor:
 * - DataLogger(int accountNumber): Constructs a DataLogger object for the specified account 
 *   number. This initializes the logger, creates the necessary log file, and sets up the 
 *   logging format.
 * 
 * Methods:
 * - logTransaction(BankAccount account, String action, double amount): Logs a transaction 
 *   for the given bank account. The log entry includes details such as the account number, 
 *   the type of action (e.g., deposit, withdrawal), the transaction amount, and the new balance 
 *   after the transaction.
 * 
 * - close(): Closes the file handler associated with this logger. This method should be called 
 *   when logging is finished to ensure that the log file is properly closed and resources are 
 *   released.
 */

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
	
    /**
     * Constructs a DataLogger object for the specified account number. 
     * This initializes the logger and creates a log file in the "logs" directory.
     * 
     * @param accountNumber the unique account number for which the log file is created.
     */
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


    /**
     * Logs a transaction for the specified bank account.
     * 
     * The log entry includes the account number, the action performed (e.g., Deposit, Withdrawal),
     * the amount involved, and the new balance after the transaction.
     * 
     * @param account the BankAccount object associated with the transaction.
     * @param action the action performed (e.g., "Deposit", "Withdrawal").
     * @param amount the amount involved in the transaction.
     */
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
