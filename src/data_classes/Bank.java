package data_classes;

import java.util.HashMap;
import java.util.Map;

import internal_validation_classes.Validator;
import keyboard_class.Keyboard;

public class Bank {
	
	// bank fields
	private Map<Integer, BankAccount> accounts;
	private String name;
	private Keyboard kb;
	
	
	// constructor
	public Bank(String name) {
		this.name = name;
		accounts = new HashMap<>();
		kb 		 = new Keyboard();
	}


	// class validation methods
	private static void validateAccount(Object obj) {
		if(!(obj instanceof BankAccount)) {
			throw new IllegalArgumentException("Expected a BankAccount object but got: " + obj.getClass().getName());
		}
	}
	
	
	
	// method to open new account
	
	
	// method to add account to accounts 
	public void addAccount(BankAccount account) {
		validateAccount(account);
		if(!this.accounts.containsKey(account.getAccNumber())) {
			this.accounts.put(account.getAccNumber(), account);
		}
		else {
			throw new IllegalArgumentException("Account number: " + account.getAccNumber() + " already exsists in the system.\nPlease try a different account nummber.");
		}
	}
	
	
	// method to delete account
	public void deleteAccount(BankAccount account) {
		validateAccount(account);
		if(!this.accounts.containsKey(account.getAccNumber())) {
			this.accounts.remove(account.getAccNumber());
		}
		else {
			throw new IllegalArgumentException("Account number: " + account.getAccNumber() + " does not exsist in the system.\nPlease try a different account nummber.");
		}
	}
	
	// method to display account information
	
	
	// method to dsplay all accounts
	
	
	// method to save account informtion to a file
	
	

	
	@Override
	public String toString() {
		return "Bank [accounts=" + accounts + "]";
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
