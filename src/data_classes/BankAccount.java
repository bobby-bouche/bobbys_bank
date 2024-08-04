package data_classes;

import validation_classes.AgeValidationException;
import validation_classes.DoubleValidationException;
import validation_classes.IllegalWithdrawException;
import validation_classes.IntegerValidationException;
import validation_classes.StringValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;


public class BankAccount {
	
	
	
	// transction logger and method will be moved to it own class and called within BankAccount class and Bank class
	// Each class will have its own log file
	
	// class transaction logger instance 
	private final static Logger logger = Logger.getLogger(BankAccount.class.getName());
	
	
	// static block for Transaction logger initialization
	static {
		try {
            // Create logs directory if it does not exist
            Files.createDirectories(Paths.get("logs"));
            
			// specifies path to logs directory
			Handler fileHandler = new FileHandler("logs/BankAccount.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.INFO);
		}
		catch(IOException e) {
			logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
		}
	}
	
	// common method to log transactions
	private void logTransaction(String action, double amount) {
		String message = String.format("Account No: %d - %s: $%.2f, New Balance: $%.2f",
				this.accNumber, action, amount, this.accBalance);
		logger.log(Level.INFO, message);
	}
	
	
	
	
	
	// bankAccount fields
	private int 	accNumber;
	private String 	accType;
	private double 	accBalance;
	private String 	firstName;
	private String 	lastName;
	private int 	age;
	private String 	address;
	
	
	// symbolic constants
	final static Integer minimumAge = 16;
	
	
	// constructor
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, double balance) {
		super();
		validateInteger(accNum);
		this.accNumber 	= accNum;
		validateString(accType);
		this.accType = accType;
		validateString(firstName);
		this.firstName = firstName;
		validateString(lastName);
		this.lastName = lastName;
		validateInteger(age);
		validateAge(age);
		this.age = age;
		validateString(address);
		this.address = address;
		validateDouble(balance);
		this.accBalance = balance;
	}

	
	
	// validation methods
	
	// intger validation method
	private static void validateInteger(Object obj) {
		if(obj instanceof Integer) {
			int number = (Integer) obj;
			if(number < 0) {
				throw new IntegerValidationException("Invalid Integer: " + number + ". Value must be must be more thn zero");
			}
		}
		else {
			throw new IntegerValidationException("Expected an Integer but got: " + obj.getClass().getName());
		}
	}
	
	// string validation method
	private static void validateString(String value) {
		if(value == null || value.isBlank()) {
			throw new StringValidationException("Invalid String: " + value);
		}
	}
	
	// double validation method
	private static void validateDouble(double number) {
		if(number < 0) {
			throw new DoubleValidationException("Invalid Double: " + number);
		}
	}
	
	// minimim age validation method
	private static void validateAge(Integer age) { 
		if(age < minimumAge) {
			throw new AgeValidationException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	// BankAccount validation for object bankaccount need for transfer method
		
	
	// getters and setters
	public int getAccNumber() {
		return this.accNumber;
	}
	
	public String getAccType() {
		return this.accType;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setNewAddress(String newAddress) {
		validateString(newAddress);
		this.address = newAddress;
	}
	
	public double getBalance() {
		return this.accBalance;
	}
	
	
	
	// Transaction methods
	
	// method to deposit amount
	public void depositAmount(double amount) {
		validateDouble(amount);
		this.accBalance += amount;
		String message = String.format("Account No: %d - Deposit: $%.2f. New Balance: $%.2f",
				this.accNumber, amount, this.getBalance());
		System.out.println(message + "\n");
		logTransaction("Deposit", amount);
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		validateDouble(amount);;
		if(this.accBalance - amount >= 0) {
			this.accBalance -= amount;
	        String message = String.format("Account No: %d - Withdrawal: $%.2f. New Balance: $%.2f",
	                this.accNumber, amount, this.getBalance());
	        System.out.println(message + "\n");
			logTransaction("Withdrawal", amount);
		}
		else {
			throw new IllegalWithdrawException("Insufficient funds. Current balance: $" + this.accBalance);
		}	
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount recipient) {
		
		validateDouble(amount);
		//validateBankAccount(account);
	
		if(this.accBalance >= amount) {
			this.accBalance -= amount;
			recipient.depositAmount(amount);
			String message = String.format("Account No; %d - Transferred $%.2f to Account No: %d. New Balance $%.2f",
					this.accNumber, amount, recipient.getAccNumber(), this.getBalance());
			System.out.println(message + "\n");
			logTransaction("Transfer", amount);
		}
		else {
			String errorMsg = String.format("Insufficient funds for transfer. Current balance: $%.2f", this.accBalance);
			logger.log(Level.SEVERE, errorMsg);
			System.out.println("Transaction failed: Insufficient funds.\n");
			throw new IllegalWithdrawException("Insufficient funds for transfer.");
		}
	}
	
	
	// toString method
	
	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", accType=" + accType + ", accBalance=" + accBalance
				+ ", Fname=" + firstName + ", LName=" + lastName + ", age=" + age + ", address=" + address + "]";
	}
	
}
