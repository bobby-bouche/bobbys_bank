package data_classes;

import validation_classes.DoubleValidationException;
import validation_classes.IntegerValidationException;
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
			throw new IllegalArgumentException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	
	// getters and setters
	public int getAccNumber() {
		return this.accNumber;
	}
	
	public String getAccTyp() {
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
		validateString(address);
		return this.address;
	}
	
	public void setNewAddress(String newAddress) {
		this.address = newAddress;
	}
	
	public double getBalance() {
		return this.accBalance;
	}
	
	
	
	// method to deposit amount
	public void depositAmount(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Invalid amount to deposit: " + amount);
		}
		this.accBalance += amount;
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Invalid amount to withdraw: " + amount);
		}
		if(this.accBalance - amount < 0) {
			throw new IllegalArgumentException("Insufficient funds: " + this.accBalance);
		}
		this.accBalance -= amount;
	}
	
	
	// method to transfer amount
	public void transferAmount(double amount, BankAccount account) {
		
		try {
			this.withdrawAmount(amount);
			account.depositAmount(amount);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	
	
	
	@Override
	public String toString() {
		return "BankAccount [accNumber=" + accNumber + ", accType=" + accType + ", accBalance=" + accBalance
				+ ", Fname=" + firstName + ", LName=" + lastName + ", age=" + age + ", address=" + address + "]";
	}
	
}
