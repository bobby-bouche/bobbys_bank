package data_classes;

import validation_classes.AgeValdationException;
import validation_classes.DoubleValidationException;
import validation_classes.IllegalWithdrawException;
import validation_classes.IntegerValidationException;
import validation_classes.InvalidDepositException;
import validation_classes.StringValidationException;


public class BankAccount {
	
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
			throw new IntegerValidationException("invalid integer: " + number);
		}
	}
	
	private static void validateString(String value) {
		if(value == null || value.isBlank()) {
			throw new StringValidationException("invalid string: " + value);
		}
	}
	
	private static void validateDouble(double number) {
		if(number < 0) {
			throw new DoubleValidationException("invalid double: " + number);
		}
	}
	
	private static void validateAge(Integer age) { 
		if(age < validAge) {
			throw new AgeValdationException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
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
		validateString(newAddress);
		this.address = newAddress;
	}
	
	public double getBalance() {
		return this.accBalance;
	}
	
	
	
	// method to deposit amount
	public void depositAmount(double amount) {
		if(amount <= 0) {
			throw new InvalidDepositException("Invalid amount to deposit: " + amount + ". Amount must be more than zero.");
		}
		this.accBalance += amount;
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		if(amount <= 0) {
			throw new IllegalWithdrawException("Invalid amount to withdraw: " + amount + ". Amount must be more than zero.");
		}
		if(this.accBalance - amount < 0) {
			throw new IllegalWithdrawException("Insufficient funds: " + this.accBalance);
		}
		this.accBalance -= amount;
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount account) {
		
		try {
			this.withdrawAmount(amount);
			account.depositAmount(amount);
		}
		catch(IllegalWithdrawException e){
			throw e;
		}
		catch(IllegalArgumentException e){
			throw e;
		}
	}
	
	
	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", accType=" + accType + ", accBalance=" + accBalance
				+ ", Fname=" + firstName + ", LName=" + lastName + ", age=" + age + ", address=" + address + "]";
	}
	
}
