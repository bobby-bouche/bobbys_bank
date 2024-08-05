package data_classes;

import validation_classes.AgeValidationException;
import validation_classes.DoubleValidationException;
import validation_classes.IllegalWithdrawException;
import validation_classes.IntegerValidationException;
import validation_classes.StringValidationException;


public class BankAccount {
	
	// BankAccount DataLogger instance
	private final DataLogger logger = new DataLogger(BankAccount.class.getName());
	
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
	private static void validateString(Object obj) {
		if(obj instanceof String) {
			String value = (String) obj;
			if(value.isBlank()) {
				throw new StringValidationException("Invalid String: Value is blank. ");
			}
		}
		else {
			throw new StringValidationException("Expected a String but got: " + obj.getClass().getName());
		}
	}
	
	// double validation method
	private static void validateDouble(Object obj) {
		if(obj instanceof Double) {
			double number = (double) obj;
			if(number < 0) {
				throw new DoubleValidationException("Invalid Double: " + number);
			}
		}
		else {
			throw new DoubleValidationException("Expected a Double but got: " + obj.getClass().getName());
		}
	}
	
	// minimim age validation method
	private static void validateAge(Integer age) { 
		validateInteger(age);
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
		logger.logTransaction(this, "Deposit", amount);
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		validateDouble(amount);;
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
		//validateBankAccount(account);
	
		if(this.accBalance >= amount) {
			this.accBalance -= amount;
			recipient.depositAmount(amount);
			String message = String.format("Account No: %d - Transfer: $%.2f to Account No: %d. New Balance $%.2f",
					this.accNumber, amount, recipient.getAccNumber(), this.getBalance());
			System.out.println(message + "\n");
			logger.logTransaction(this, "Transfer", amount);
		}
		else {
			String errorMsg = String.format("Insufficient funds for transfer. Current balance: $%.2f", this.accBalance);
			logger.logTransaction(this, errorMsg, amount);
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
