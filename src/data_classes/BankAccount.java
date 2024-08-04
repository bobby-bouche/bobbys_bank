package data_classes;

import validation_classes.AgeValdationException;
import validation_classes.DoubleValidationException;
import validation_classes.IllegalWithdrawException;
import validation_classes.IntegerValidationException;
import validation_classes.InvalidDepositException;
import validation_classes.StringValidationException;

import java.io.IOException;
import java.util.logging.*;


public class BankAccount {
	
	// class error logger instance 
	private final static Logger logger = Logger.getLogger(BankAccount.class.getName());
	
	
	// static block for logger initialization
	static {
		try {
			// specifies path to logs directory
			Handler fileHandler = new FileHandler("logs/BankAccount.log", true);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			
			// console handler for logging to console
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL);
			logger.addHandler(consoleHandler);
			
			logger.setLevel(Level.ALL);
			logger.setUseParentHandlers(false); // disables default console handler
		}
		catch(IOException e) {
			logger.log(Level.SEVERE, "Failed to initialize logger handler.", e);
		}
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
	final static Integer validAge = 16;
	
	
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
	private static void validateInteger(int number) {
		if(number < 0) {
			logger.log(Level.SEVERE, "Invalid Integer: " + number);
			throw new IntegerValidationException("Invalid Integer: " + number);
		}
	}
	
	private static void validateString(String value) {
		if(value == null || value.isBlank()) {
			logger.log(Level.SEVERE, "Invalid String: " + value);
			throw new StringValidationException("Invalid String: " + value);
		}
	}
	
	private static void validateDouble(double number) {
		if(number < 0) {
			logger.log(Level.SEVERE, "Invalid Double: " + number);
			throw new DoubleValidationException("Invalid Double: " + number);
		}
	}
	
	private static void validateAge(Integer age) { 
		if(age < validAge) {
			logger.log(Level.SEVERE, "Invalid Age: " + age);
			throw new AgeValdationException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	// common validation method for deposit and withdraw
	private void validateAmount(double amount, String action) {
		if(amount <= 0) {
			logAndThrow(new InvalidDepositException("Invalid amount to " + action + ": " + amount + ". Amount moust be more than zero."));
		}
	}
	
	// common method to log and throw exceptions
	private void logAndThrow(RuntimeException e) {
		logger.log(Level.SEVERE, e.getMessage(), e);
		throw e;
	}
	
	// common method to log transaction
	private void logTransaction(String action, double amount) {
		String message = action + " " + amount + ". New Balance: " + this.accBalance;
		logger.log(Level.INFO, message);
		System.out.println(message);
	}
	
	
	
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
	
	
	
	// method to deposit amount
	public void depositAmount(double amount) {
		validateAmount(amount, "deposit");
		this.accBalance += amount;
		logTransaction("Account no: " + this.accNumber + "Deposited", amount);
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		validateAmount(amount, "withdraw");
		if(this.accBalance - amount < 0) {
			logAndThrow(new IllegalWithdrawException("Insufficient funds: " + this.accBalance));
		}
		this.accBalance -= amount;
		logTransaction("Account no: " + this.accNumber + ": Withdrew", amount);
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount account) {
		
		try {
			this.withdrawAmount(amount);
			account.depositAmount(amount);
			logger.log(Level.INFO, "Transferred " + amount + " to account " + account.getAccNumber());
		}
		catch(IllegalWithdrawException e){
			logger.log(Level.SEVERE, "Transfer failed: " + e.getMessage(), e);
			throw e;
		}
		catch(IllegalArgumentException e){
			logger.log(Level.SEVERE, "Transfer failed: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", accType=" + accType + ", accBalance=" + accBalance
				+ ", Fname=" + firstName + ", LName=" + lastName + ", age=" + age + ", address=" + address + "]";
	}
	
}
