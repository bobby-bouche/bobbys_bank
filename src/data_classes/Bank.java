package data_classes;

import java.util.HashMap;
import java.util.Map;

import keyboard_classes.Keyboard;

public class Bank {
	
	// bank fields
	private Map<String, BankAccount> accounts;
	private Keyboard kb;
	
	
	// constructor
	public Bank() {
		accounts = new HashMap<>();
		kb 		 = new Keyboard();
	}


	
	// method to open new account
	
	
	// method to delete account
	
	
	// method to display account information
	
	
	// method to dsplay all accounts
	
	
	// method to save account informtion to a file
	
	

	
	@Override
	public String toString() {
		return "Bank [accounts=" + accounts + "]";
	}


}
