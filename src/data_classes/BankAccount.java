/**
 * The BankAccount class represents an individual bank account in the system.
 * Each account has associated fields such as account number, account type, 
 * balance, and owner information (e.g., first name, last name, age, address).
 * 
 * The class handles transactions like deposits, withdrawals, and transfers, 
 * while also ensuring that all data conforms to specified business rules through 
 * the use of validation methods. It also maintains a log of transactions using 
 * the DataLogger class.
 * 
 * Usage Example:
 * BankAccount account = new BankAccount(12345, "Checking", "John", "Doe", 30, "123 Elm St", 100.0, bank);
 * account.depositAmount(50.0);
 * 
 * Responsibilities:
 * - Store and manage account-specific information.
 * - Perform transactions (deposit, withdraw, transfer) with validation.
 * - Enforce business rules on account creation and transactions.
 * - Log all transactions for auditing and record-keeping.
 * - Ensure account data integrity through validations and exception handling.
 * 
 * This class interacts with the Validator class to ensure that fields like account 
 * number, age, and balance meet the necessary criteria before they are set or updated.
 */

package data_classes;

import internal_validation_classes.IllegalWithdrawException;


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
	
	
	// constructors
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, double balance, Bank bank) {
		super();
		validateAccountNumber(accNum);
		this.accNumber 	= accNum;
		validateAccountType(accType);
		this.accType = accType;
		validateName(firstName);
		this.firstName = firstName;
		validateName(lastName);
		this.lastName = lastName;
		validateAge(age);
		this.age = age;
		validateAddress(address);
		this.address = address;
		validateBalance(balance);
		this.accBalance = balance;
		validateBank(bank);
		this.bank = bank;
		bank.addAccount(this);
		
		this.logger = new DataLogger(accNumber); // initialize a dataLogger for this specific account
	}
	
	public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final  int age, String address, Bank bank) {
		this(accNum, accType, firstName, lastName, age, address, DEFAULT_BALANCE, bank);
	}
	
	public BankAccount(Bank bank) {
		super();
		this.bank = bank;
		bank.addAccount(this);
		// TODO how do i initialize a logger if this constructor is called ??
		// maybe use setLogger()??
	}

	
	
	// BankAccount validation methods
	
	// validate accountNumber
	private static void validateAccountNumber(int num) {
		if(num < 1000 || num > 9999) {
			throw new IllegalArgumentException("Invalid account number: " + num + ". Please enter a 4 digit account number\n");
		}
	}
	
	// validate accountType
	private static void validateAccountType(String type) {
		if(!(type.equalsIgnoreCase("checking")) && !(type.equalsIgnoreCase("savings"))) {
			throw new IllegalArgumentException("Invalid account type. Choose between 'checking' or 'savings'.\n");
		}
	}
	
	// public accounttype boolean validation
	public static boolean isValidAccountType(String type) {
		if(!(type.equalsIgnoreCase("checking")) && !(type.equalsIgnoreCase("savings"))) {
			return false;
		}
		return true;
	}
	
	// validate name
	private static void validateName(String name) {
		if(name.isBlank() || name == null || name.length() > 30) {
			throw new IllegalArgumentException("Invalid name: " + name + ". Name must not be blank and msut be less than 30 characters.");
		}
	}
	
	/**
	 * Validates the age of the account holder to ensure it meets the minimum required age.
	 * This method checks if the provided age is an integer and if it is greater than 
	 * or equal to the specified minimum age. If the age is invalid, it throws an 
	 * IllegalArgumentException.
	 * 
	 * @param age The age of the account holder to validate.
	 * @throws IllegalArgumentException if the age is less than the required minimum age.
	 */
	private static void validateAge(int age) { 
		if(age < MINIMUM_AGE) {
			throw new IllegalArgumentException("Invalid age: " + age + ". You must bo over 16 to open an account with us.");
		}
	}
	
	// validate address
	private static void validateAddress(String address) {
		if(address.isBlank() || address == null || address.length() > 50) {
			throw new IllegalArgumentException("Invalid name: " + address + ". Name must not be blank and msut be less than 30 characters.");
		}
	}
	
	// validate Balance
	private static void validateBalance(Double balance) {
		if(balance <= 100.00) {
			throw new IllegalArgumentException("Invalid amount: " + balance + ". Minimum balance of $100.00 needed to register account"); // format this string
		}
	}
	
	/**
	 * Validates that the provided object is an instance of Bank.
	 * This method ensures that the object passed to it is of type Bank. 
	 * If the object is not a Bank instance, an IllegalArgumentException is thrown.
	 * 
	 * @param obj The object to validate.
	 * @throws IllegalArgumentException if the object is not an instance of Bank.
	 */
	private static void validateBank(Object obj) {
		if(!(obj instanceof Bank)) {
			throw new IllegalArgumentException("Expected a Bank object but got: " + obj.getClass().getName());
		}
	}
	
	private static void validateAmount(Double amount) {
		if(amount < 0.0) {
			throw new IllegalArgumentException("Invalid amount: " + amount + ". AMount must be more than 0");
		}
	}
	
	
	// getter methods
	
	/**
	 * Gets the account number.
	 *
	 * @return the account number of this BankAccount.
	 */
	public int getAccNumber() {
		return this.accNumber;
	}
	
	/**
	 * Gets the account type.
	 *
	 * @return the account type of this BankAccount (e.g., Savings, Checking).
	 */
	public String getAccType() {
		return this.accType;
	}
	
	/**
	 * Gets the first name of the account holder.
	 *
	 * @return the first name of the account holder.
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * Gets the last name of the account holder.
	 *
	 * @return the last name of the account holder.
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Gets the age of the account holder.
	 *
	 * @return the age of the account holder.
	 */
	public int getAge() {
		return this.age;
	}
	
	/**
	 * Gets the address of the account holder.
	 *
	 * @return the address of the account holder.
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Gets the current balance of the account.
	 *
	 * @return the current balance of this BankAccount.
	 */
	public double getBalance() {
		return this.accBalance;
	}
	
	/**
	 * Gets the Bank that the account belongs to.
	 *
	 * @return the Bank of this BankAccount.
	 */
	public Bank getBank() {
		return bank;
	}
	
    // Getter for logger (if needed for tests)
    public DataLogger getLogger() {
        return logger;
    }
    
    
    
    // setter methods
    
	public void setAccNumber(int accNumber) {
		validateAccountNumber(accNumber);
		this.accNumber 	= accNumber;
	}

	public void setAccType(String accType) {
		validateAccountType(accType);
		this.accType = accType;
	}

	public void setFirstName(String firstName) {
		validateName(firstName);
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		validateName(lastName);
		this.lastName = lastName;
	}

	public void setAge(int age) {
		validateAge(age);
		this.age = age;
	}
	
	/**
	 * Updates the address of the account holder.
	 * 
	 * This method allows you to set a new address for the account holder. 
	 * Before updating, it validates that the new address is a non-empty string. 
	 * If the validation passes, the address is updated; otherwise, an 
	 * IllegalArgumentException is thrown.
	 *
	 * @param newAddress The new address to be set for the account holder.
	 * @throws IllegalArgumentException if the new address is an empty or blank string.
	 */
	public void setNewAddress(String address) {
		validateAddress(address);
		this.address = address;
	}
	
	public void setBalance(double balance) {
		validateBalance(balance);
		this.accBalance = balance;
	}

	public void setBank(Bank bank) {
		validateBank(bank);
		this.bank = bank;
	}
	
    public void setLogger(int accNumber) {
    	validateAccountNumber(accNumber);
    	this.logger = new DataLogger(accNumber);
    }
	
	
	// Transaction methods
    
    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit, must be greater than zero.
     * @throws IllegalArgumentException if the amount is less than or equal to zero.
     */
	public void depositAmount(double amount) {	
		validateAmount(amount);
		this.accBalance += amount;
		String message = String.format("Account No: %d - Deposit: $%.2f. New Balance: $%.2f",
				this.accNumber, amount, this.getBalance());
		System.out.println(message + "\n");
		logger.logTransaction(this, "Deposit", amount);
	}
	
	
	/**
	 * Withdraws the specified amount from the account.
	 *
	 * @param amount the amount to withdraw, must be greater than zero and less than or equal to the current balance.
	 * @throws IllegalWithdrawException if the withdrawal amount exceeds the current balance.
	 */
	public void withdrawAmount(double amount) {
		validateAmount(amount);
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
	
	
	/**
	 * Transfers the specified amount from this account to another account.
	 *
	 * @param amount the amount to transfer, must be greater than zero and less than or equal to the current balance.
	 * @param targetAccount the BankAccount to which the amount will be transferred.
	 * @throws IllegalWithdrawException if the transfer amount exceeds the current balance.
	 */
	public void transferAmount(double amount, BankAccount recipient) {
		validateAmount(amount);
		Bank.validateBankAccount(recipient);	
		if(this.accBalance >= amount) {
			this.accBalance -= amount;
			recipient.depositAmount(amount);
			String message = String.format("Account No: %d - Transfer: $%.2f to Account No: %d. New Balance $%.2f",
					this.accNumber, amount, recipient.getAccNumber(), this.getBalance());
			System.out.println(message + "\n");
			logger.logTransaction(this, String.format("Transfer to %d", recipient.getAccNumber()), amount);

		}
		else {
			String errorMsg = String.format("Insufficient funds for transfer to recipient: %d Current balance: $%.2f. Transfer amount", recipient.getAccNumber(), this.accBalance);
			logger.logTransaction(this, errorMsg, amount);
			System.out.println("Transaction failed: Insufficient funds.\n");
			throw new IllegalWithdrawException(errorMsg);
		}
	}
	
	
	/**
	 * Closes the account and performs any necessary cleanup, such as closing log files.
	 * This method should be called when the account is no longer needed.
	 */
    public void close() {
        logger.close();
    }
	
    
    /**
     * Returns a string representation of the BankAccount object.
     * 
     * This method provides a detailed summary of the account's information, including the 
     * account number, account type, holder's name, age, address, and current balance. The 
     * output is formatted for easy readability, with each field clearly labeled.
     *
     * @return a formatted string that represents the account's information.
     */
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
