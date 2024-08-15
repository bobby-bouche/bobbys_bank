package data_classes;

import java.util.HashMap;
import java.util.Map;

import internal_validation_classes.Validator;
import keyboard_class.Keyboard;

public class Bank {
	
	// bank fields
	private Map<String, BankAccount> accounts;
	private String name;
	private Keyboard kb;
	
	
	// constructor
	public Bank(String name) {
		this.name = name;
		accounts = new HashMap<>();
		kb 		 = new Keyboard();
	}


	// class validation methods
	private static void validateMap(Object obj) {
		
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
