/**
 * The Bank class represents a collection of bank accounts within the system. 
 * It is responsible for managing the overall operations related to bank accounts, 
 * such as adding, deleting, and managing accounts.
 * 
 * This class also interacts with the Keyboard class to gather user input and validate 
 * that accounts conform to specified business rules before they are added to the bank's 
 * records. The Bank class uses a HashMap to store and manage the accounts, where each 
 * account is uniquely identified by its account number.
 * 
 * Usage Example:
 * Bank bank = new Bank("MyBank");
 * bank.addAccount(account);
 * 
 * Responsibilities:
 * - Store and manage a collection of bank accounts.
 * - Add, delete, and retrieve bank accounts.
 * - Ensure account numbers are unique within the bank.
 * - Validate account objects before operations.
 * 
 * The Bank class is crucial for managing the higher-level operations that involve 
 * multiple bank accounts and for maintaining the overall integrity of the system's 
 * account data.
 */

package data_classes;

import java.util.HashMap;
import java.util.Map;
import keyboard_class.Keyboard;


public class Bank {
	
	// bank fields
	private Map<Integer, BankAccount> accounts;
	private int ID;
	private String name;
	private static Keyboard kb;
	
	
	// constructor
	
	public Bank(int id, String name) {
		validateBankID(ID);
		this.ID = id;
		validateBankName(name);
		this.name = name;
		accounts = new HashMap<>();
		kb 		 = new Keyboard();
	}

	
	// validation methods
	
	/**
	 * Validates that the provided object is an instance of BankAccount.
	 * This method ensures that the object passed to it is of type BankAccount. 
	 * If the object is not a BankAccount instance, an IllegalArgumentException is thrown.
	 * 
	 * @param obj The object to validate.
	 * @throws IllegalArgumentException if the object is not an instance of BankAccount.
	 */
	public static void validateBankAccount(Object obj) {
		if(!(obj instanceof BankAccount)) {
			throw new IllegalArgumentException("Expected a BankAccout object but got: " + obj.getClass().getName());
		}
	}
	
	// validate bankID
	public static void validateBankID(int id) {
		if(id < 100) {
			throw new IllegalArgumentException("Invalid bank ID: " + id + ". Please try again..");
		}
	}
	
	// validate bankName 
	public static void validateBankName(String name) {
		if(name.isBlank() || name.length() > 15) {
			throw new IllegalArgumentException("Invalid Bank name: " + name + ". Name must not be blank and be under 15 characters.");
		}
	}
	
	
	// getter methods
	public Map<Integer, BankAccount> getAccounts() {
		return accounts;
	}
	
	/**
	 * Gets the name of the bank.
	 * 
	 * This method returns the name of the bank as a String.
	 *
	 * @return the name of the bank.
	 */
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}

	
	// setter methods
	
	/**
	 * Sets the name of the bank.
	 * 
	 * This method allows you to change the name of the bank.
	 *
	 * @param name the new name of the bank.
	 */
	public void setName(String name) {
		validateBankName(name);
		this.name = name;
	}
	
	public void setBankID(int id) {
		validateBankID(id);
		this.ID = id;
	}
	

	// TODO method to open new account
	public void openNewAccount() {
		
		BankAccount account = new BankAccount(this);
		
		String accType;
		String accTypePromptMsg = "Enter account type - 'checking' or 'savings':\n";
		accType = kb.readAccountType(accTypePromptMsg);
		account.setAccType(accType);
		
		String fName;
		String fNamePromptMsg = "Enter first name: \n";
		fName = kb.readName(fNamePromptMsg);
		account.setFirstName(fName);
		
		String lName;
		String lNamePromptMsg = "Enter last name: \n";
		lName = kb.readName(lNamePromptMsg);
		account.setLastName(lName);
		
		int age;
		String agePromptMsg = "Enter age: \n";
		age = kb.readAge(agePromptMsg);
		account.setAge(age);
		
		String address;
		String addressPromptMsg = "Enter address: \n";
		address = kb.readAddress(addressPromptMsg);
		account.setAddress(address);
		
		Double balance;
		String balancePromptMsg = "Enter balance: \n";
		balance = kb.readBalance(balancePromptMsg);
		account.setBalance(balance);
		
//		try {
//			
//			Class.forName("com.mysql.cy.jdbc.Driver");
//			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bobby's_bank", "root", "Ronalde");
//			
//			PreparedStatement stat1 = con.prepareStatement("");
//		}

		
	}
	
	
	/**
	 * Adds a new BankAccount to the bank.
	 * 
	 * This method adds the provided BankAccount to the bank's collection of accounts 
	 * if the account number is unique. The method first validates that the object is 
	 * indeed a BankAccount, then checks if an account with the same account number 
	 * already exists in the bank. If not, the account is added; otherwise, an 
	 * IllegalArgumentException is thrown.
	 *
	 * @param account the BankAccount to be added.
	 * @throws IllegalArgumentException if the account number already exists in the bank.
	 */
	public void addAccount(BankAccount account) {
		validateBankAccount(account);
		if(!this.accounts.containsKey(account.getAccNumber())) {
			this.accounts.put(account.getAccNumber(), account);
		}
		else {
			throw new IllegalArgumentException("Account number: " + account.getAccNumber() + " already exsists in the system.\nPlease try a different account nummber.");
		}
	}
	
	
	/**
	 * Removes a BankAccount from the bank.
	 * 
	 * This method removes the provided BankAccount from the bank's collection of accounts 
	 * if it exists. The method first validates that the object is a BankAccount, then checks 
	 * if an account with the given account number exists in the bank. If it does, the account 
	 * is removed; otherwise, an IllegalArgumentException is thrown.
	 *
	 * @param account the BankAccount to be removed.
	 * @throws IllegalArgumentException if the account does not exist in the bank.
	 */
	public void removeAccount(BankAccount account) {
		validateBankAccount(account);
		if(!this.accounts.containsKey(account.getAccNumber())) {
			this.accounts.remove(account.getAccNumber());
		}
		else {
			throw new IllegalArgumentException("Account number: " + account.getAccNumber() + " does not exsist in the system.\nPlease try a different account nummber.");
		}
	}
	
	
	
	// TODO method to display account information
	
	
	// TODO method to dsplay all accounts
	
	
	// TODO method to save account informtion to a file
	


	
	/**
	 * Returns a string representation of the Bank object.
	 * 
	 * This method returns a string that provides a simple representation of the bank's 
	 * state, particularly the list of accounts.
	 *
	 * @return a string representation of the Bank object.
	 */
	@Override
	public String toString() {
		return "Bank [accounts=" + accounts + "]";
	}

}
