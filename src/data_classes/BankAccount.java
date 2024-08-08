package data_classes;


import validation_classes.IllegalWithdrawException;
import validation_classes.InputValidator;


public class BankAccount {
	
	// BankAccount DataLogger instance
	private final DataLogger logger = new DataLogger(BankAccount.class.getName(), "BankAccount.log");
	
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
		InputValidator.validateString(accType);
		this.accType = accType;
		InputValidator.validateString(firstName);
		this.firstName = firstName;
		InputValidator.validateString(lastName);
		this.lastName = lastName;
		validateInteger(age);
		validateAge(age);
		this.age = age;
		InputValidator.validateString(address);
		this.address = address;
		validateDouble(balance);
		this.accBalance = balance;
	}

	
	// class validation methods
	
	// integer validation method
	private static void validateInteger(Object obj) {
		if(obj instanceof Integer) {
			int number = (Integer) obj;
			if(number < 0) {
				throw new IllegalArgumentException("Invalid Integer: " + number + ". Value must be must be more thn zero");
			}
		}
		else {
			throw new IllegalArgumentException("Expected an Integer but got: " + obj.getClass().getName());
		}
	}
	
	// double validation method
	private static void validateDouble(Object obj) {
		if(obj instanceof Double) {
			double number = (double) obj;
			if(number < 0) {
				throw new IllegalArgumentException("Invalid Double: " + number);
			}
		}
		else {
			throw new IllegalArgumentException("Expected a Double but got: " + obj.getClass().getName());
		}
	}
	
	// minimum age validation method
	private static void validateAge(Integer age) { 
		validateInteger(age);
		if(age < minimumAge) {
			throw new IllegalArgumentException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	// BankAccount validation method used for BankAccount object being passed in transfer method
	private static void validateBankAccount(Object obj) {
		if(!(obj instanceof BankAccount)) {
			throw new IllegalArgumentException("Expected a BankAccout object but got: " + obj.getClass().getName());
		}
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
		InputValidator.validateString(newAddress);
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
		logger.logTransaction(this, "Deposit", amount);
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		validateDouble(amount);
		if(this.accBalance - amount >= 0) {
			this.accBalance -= amount;
	        String message = String.format("Account No: %d - Withdrawal: $%.2f. New Balance: $%.2f",
	                this.accNumber, amount, this.getBalance());
	        System.out.println(message + "\n");
			logger.logTransaction(this, "Withdrawal", amount);
		}
		else {
			throw new IllegalWithdrawException("Insufficient funds. Current balance: $" + this.accBalance);
		}	
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount recipient) {
		validateDouble(amount);
		validateBankAccount(recipient);	
		if(this.accBalance >= amount) {
			this.accBalance -= amount;
			recipient.depositAmount(amount);
			String message = String.format("Account No: %d - Transfer: $%.2f to Account No: %d. New Balance $%.2f",
					this.accNumber, amount, recipient.getAccNumber(), this.getBalance());
			System.out.println(message + "\n");
			logger.logTransaction(this, String.format("Transfer to %d", recipient.getAccNumber()), amount);

		}
		else {
			String errorMsg = String.format("Insufficient funds for transfer. Current balance: $%.2f", this.accBalance);
			logger.logTransaction(this, errorMsg, amount);
			System.out.println("Transaction failed: Insufficient funds.\n");
			throw new IllegalWithdrawException(errorMsg);
		}
	}
	
	
	// toString method
	
	@Override
	public String toString() {
		return "\n\nAccount Information\n"
				+ "----------------------------------------------------------------------\n"
				+ "Account Number:    			" + accNumber 
				+ "\nAccount Type:    			" + accType 
				+ "\nLast Name:       			" + lastName 
				+ "\nFirst Name:      			" + firstName
				+ "\nAge:             			" + age 
				+ "\nAddress:         			" + address
				+ "\nBalance:         			" + accBalance + "\n"
				+ "----------------------------------------------------------------------\n\n";
	}
	
}
