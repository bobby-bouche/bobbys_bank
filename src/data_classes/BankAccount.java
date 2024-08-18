package data_classes;


import internal_validation_classes.IllegalWithdrawException;
import internal_validation_classes.Validator;


public class BankAccount {
	
	// bankAccount fields
	private int 	accNumber;
	private String 	accType;
	private double 	accBalance;
	private String 	firstName;
	private String 	lastName;
	private int 	age;
	private String 	address;
	
	// bank that this instance of BankAccount belongs to. 
	private Bank bank;
	
	// BankAccount DataLogger instance
	private DataLogger logger;
	
	
	// symbolic constants
	private static Integer MINIMUM_AGE;
	private static Double DEFAULT_BALANCE;
	
	// initialization block
	{
		MINIMUM_AGE		 = 16;
		DEFAULT_BALANCE  = 0.0;
	}
	
	
	// constructor
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, double balance, Bank bank) {
		super();
		Validator.validateInteger(accNum);
		this.accNumber 	= accNum;
		Validator.validateString(accType);
		this.accType = accType;
		Validator.validateString(firstName);
		this.firstName = firstName;
		Validator.validateString(lastName);
		this.lastName = lastName;
		Validator.validateInteger(age);
		validateAge(age);
		this.age = age;
		Validator.validateString(address);
		this.address = address;
		Validator.validateDouble(balance);
		this.accBalance = balance;
		validateBank(bank);
		this.bank = bank;
		bank.addAccount(this);
		this.logger = new DataLogger(accNumber); // initialize a dataLogger for this specific account
	}
	
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, Bank bank) {
		this(accNum, accType, firstName, lastName, age, address, DEFAULT_BALANCE, bank);
	}

	
	// BankAccount validation methods
	
	// minimum age validation method
	private static void validateAge(Integer age) { 
		Validator.validateInteger(age);
		if(age < MINIMUM_AGE) {
			throw new IllegalArgumentException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	// BankAccount validation method used for BankAccount object being passed in transfer method
	private static void validateBankAccount(Object obj) {
		if(!(obj instanceof BankAccount)) {
			throw new IllegalArgumentException("Expected a BankAccout object but got: " + obj.getClass().getName());
		}
	}
	
	// Bank validation method
	private static void validateBank(Object obj) {
		if(!(obj instanceof Bank)) {
			throw new IllegalArgumentException("Expected a Bank object but got: " + obj.getClass().getName());
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
		Validator.validateString(newAddress);
		this.address = newAddress;
	}
	
	public double getBalance() {
		return this.accBalance;
	}
	
	public Bank getBank() {
		return bank;
	}
	
    // Getter for logger (if needed for tests)
    public DataLogger getLogger() {
        return logger;
    }
	
	
	// Transaction methods
	
	// method to deposit amount
	public void depositAmount(double amount) {
		Validator.validateDouble(amount);	
		this.accBalance += amount;
		String message = String.format("Account No: %d - Deposit: $%.2f. New Balance: $%.2f",
				this.accNumber, amount, this.getBalance());
		System.out.println(message + "\n");
		logger.logTransaction(this, "Deposit", amount);
	}
	
	
	// method to withraw amount
	public void withdrawAmount(double amount) {
		Validator.validateDouble(amount);
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
		Validator.validateDouble(amount);
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
	
	
    // Close logger when account is closed
    public void close() {
        logger.close();
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
