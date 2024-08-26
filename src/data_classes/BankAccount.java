package data_classes;

import Exception_classes.IllegalWithdrawException;

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
 */
public class BankAccount {

    // BankAccount fields
    private int accNumber;
    private String accType;
    private double accBalance;
    private String firstName;
    private String lastName;
    private int age;
    private String address;

    // Bank that this instance of BankAccount belongs to.
    private Bank bank;

    // BankAccount DataLogger instance
    private DataLogger logger;

    // Symbolic constants
    private static Integer MINIMUM_AGE;
    private static Double DEFAULT_BALANCE;

    
    // Initialization block
    {
        MINIMUM_AGE = 16;
        DEFAULT_BALANCE = 0.0;
    }

    
    // Constructors

    /**
     * Creates a new BankAccount with the specified details.
     * 
     * @param accNum The account number.
     * @param accType The type of the account (e.g., Checking, Savings).
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param age The age of the account holder.
     * @param address The address of the account holder.
     * @param balance The initial balance of the account.
     * @param bank The bank to which the account belongs.
     */
    public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final int age, String address, double balance, Bank bank) {
        super();
        validateAccountNumber(accNum);
        this.accNumber = accNum;
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

        this.logger = new DataLogger(accNumber); // Initialize a DataLogger for this specific account
    }

    /**
     * Creates a new BankAccount with a default balance.
     * 
     * @param accNum The account number.
     * @param accType The type of the account (e.g., Checking, Savings).
     * @param firstName The first name of the account holder.
     * @param lastName The last name of the account holder.
     * @param age The age of the account holder.
     * @param address The address of the account holder.
     * @param bank The bank to which the account belongs.
     */
    public BankAccount(final int accNum, final String accType, final String firstName, final String lastName, final int age, String address, Bank bank) {
        this(accNum, accType, firstName, lastName, age, address, DEFAULT_BALANCE, bank);
    }

    /**
     * Creates a new BankAccount with only a Bank reference.
     * This constructor may be used when retrieving an account from the database.
     * 
     * @param bank The bank to which the account belongs.
     */
    public BankAccount(Bank bank) {
        super();
        this.bank = bank;
        bank.addAccount(this);
        // TODO how do I initialize a logger if this constructor is called ??
        // Maybe use setLogger()??
        // Use in bank . open new account, once account with accNumber has been retrieved from database, assign logger with accNumber
    }

    
    
    // BankAccount validation methods

    /**
     * Validates the account number to ensure it is a 4-digit number.
     * 
     * @param num The account number to validate.
     * @throws IllegalArgumentException if the account number is not 4 digits.
     */
    public static void validateAccountNumber(int num) {
        if (num < 1000 || num > 9999) {
            throw new IllegalArgumentException("Invalid account number: " + num + ". Please enter a 4-digit account number\n");
        }
    }
    
    public boolean isActiveAccount(int num) {
        if (bank == null) {
            return false;
        }
        // Check if the account number exists in the bank's accounts
        return bank.getAccounts().containsKey(num);
    }
    
    /**
     * Validates the account type to ensure it is either "Checking" or "Savings".
     * 
     * @param type The account type to validate.
     * @throws IllegalArgumentException if the account type is not valid.
     */
    public static void validateAccountType(String type) {
        if (!(type.equalsIgnoreCase("checking")) && !(type.equalsIgnoreCase("savings"))) {
            throw new IllegalArgumentException("Invalid account type. Choose between 'checking' or 'savings'.\n");
        }
    }

    /**
     * Validates a name to ensure it is not blank and not longer than 30 characters.
     * 
     * @param name The name to validate.
     * @throws IllegalArgumentException if the name is blank or too long.
     */
    public static void validateName(String name) {
        if (name.isBlank() || name == null || name.length() > 30) {
            throw new IllegalArgumentException("Invalid name: " + name + ". Name must not be blank and must be less than 30 characters.");
        }
    }

    /**
     * Validates the age of the account holder to ensure it meets the minimum required age.
     * 
     * @param age The age of the account holder to validate.
     * @throws IllegalArgumentException if the age is less than the required minimum age.
     */
    public static void validateAge(int age) {
        if (age < MINIMUM_AGE) {
            throw new IllegalArgumentException("Invalid age: " + age + ". You must be over 16 to open an account with us.");
        }
    }

    /**
     * Validates an address to ensure it is not blank and not longer than 50 characters.
     * 
     * @param address The address to validate.
     * @throws IllegalArgumentException if the address is blank or too long.
     */
    public static void validateAddress(String address) {
        if (address.isBlank() || address == null || address.length() > 50) {
            throw new IllegalArgumentException("Invalid address: " + address + ". Address must not be blank and must be less than 50 characters.");
        }
    }

    /**
     * Validates the initial balance to ensure it meets the minimum required balance.
     * 
     * @param balance The initial balance to validate.
     * @throws IllegalArgumentException if the balance is less than the minimum required.
     */
    public static void validateBalance(Double balance) {
        if (balance < 100.00) {
            throw new IllegalArgumentException("Invalid amount: " + balance + ". Minimum balance of $100.00 needed to register account.");
        }
    }

    /**
     * Validates that the provided object is an instance of Bank.
     * 
     * @param obj The object to validate.
     * @throws IllegalArgumentException if the object is not an instance of Bank.
     */
    private static void validateBank(Object obj) {
        if (!(obj instanceof Bank)) {
            throw new IllegalArgumentException("Expected a Bank object but got: " + obj.getClass().getName());
        }
    }

    /**
     * Validates an amount to ensure it is greater than zero.
     * 
     * @param amount The amount to validate.
     * @throws IllegalArgumentException if the amount is not valid.
     */
    private static void validateAmount(Double amount) {
        if (amount < 0.0) {
            throw new IllegalArgumentException("Invalid amount: " + amount + ". Amount must be more than 0");
        }
    }
    
    

    // Getter methods

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
    public Double getBalance() {
        return this.accBalance;
    }
    
    public String getBankName() {
    	return bank.getName();
    }
    
    public Bank getBank() {
    	return bank;
    }
    
    

    // Setter methods

    /**
     * Sets the account number.
     * 
     * @param accNumber The new account number.
     * @throws IllegalArgumentException if the account number is invalid.
     */
    public void setAccNumber(int accNumber) {
        validateAccountNumber(accNumber);
        this.accNumber = accNumber;
    }

    /**
     * Sets the account type.
     * 
     * @param accType The new account type (e.g., Savings, Checking).
     * @throws IllegalArgumentException if the account type is invalid.
     */
    public void setAccType(String accType) {
        validateAccountType(accType);
        this.accType = accType;
    }

    /**
     * Sets the first name of the account holder.
     * 
     * @param firstName The new first name.
     * @throws IllegalArgumentException if the first name is invalid.
     */
    public void setFirstName(String firstName) {
        validateName(firstName);
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the account holder.
     * 
     * @param lastName The new last name.
     * @throws IllegalArgumentException if the last name is invalid.
     */
    public void setLastName(String lastName) {
        validateName(lastName);
        this.lastName = lastName;
    }

    /**
     * Sets the age of the account holder.
     * 
     * @param age The new age.
     * @throws IllegalArgumentException if the age is invalid.
     */
    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    /**
     * Sets the address of the account holder.
     * 
     * @param address The new address.
     * @throws IllegalArgumentException if the address is invalid.
     */
    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    /**
     * Sets the balance of the account.
     * 
     * @param balance The new balance.
     * @throws IllegalArgumentException if the balance is invalid.
     */
    public void setBalance(double balance) {
        validateBalance(balance);
        this.accBalance = balance;
    }
    
	public void setBank(Bank bank) {
		validateBank(bank);
		this.bank = bank;
	}

	
	
    // Transaction methods

    /**
     * Deposits the specified amount into this BankAccount.
     * 
     * @param amount The amount to deposit.
     * @throws IllegalArgumentException if the amount is invalid.
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
     * Withdraws the specified amount from this BankAccount.
     * 
     * @param amount The amount to withdraw.
     * @throws IllegalWithdrawException if there are insufficient funds.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public void withdrawAmount(double amount) throws IllegalWithdrawException {
        validateAmount(amount);
        if (this.accBalance < amount) {
            throw new IllegalWithdrawException("Transaction failed: Insufficient funds. Current balance: $" + this.accBalance);
        }
        this.accBalance -= amount;
        String message = String.format("Account No: %d - Withdraw: $%.2f. New Balance: $%.2f",
                this.accNumber, amount, this.getBalance());
        System.out.println(message + "\n");
        logger.logTransaction(this, "Withdraw", amount);
    }

    /**
     * Transfers the specified amount to another BankAccount.
     * 
     * @param target The target account to transfer funds to.
     * @param amount The amount to transfer.
     * @throws IllegalWithdrawException if there are insufficient funds.
     * @throws IllegalArgumentException if the amount is invalid.
     */
	public void transferAmount(double amount, BankAccount recipient) throws IllegalWithdrawException{
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

	
	
    // toString method

    /**
     * Returns a string representation of the BankAccount, displaying account number, 
     * type, holder's name, age, address, and current balance.
     * 
     * @return a string representation of the BankAccount.
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

	
	
    // Close method

    /**
     * Closes the BankAccount by performing necessary cleanup, including 
     * closing the logger.
     */
    public void close() {
        // Perform cleanup actions here if needed
        this.logger.close(); // Ensure the logger is closed when the account is closed.
    }
}
