package data_classes;

import java.util.HashMap;
import java.util.Map;

import keyboard_classes.Keyboard;
import validation_classes.InputValidator;

public class Bank {
	
	// bank fields
	private Map<String, BankAccount> accounts;
	private String name;
	private Keyboard kb;
	
	
	// constructor
	public Bank(String name) {
		accounts = new HashMap<>();
		kb 		 = new Keyboard();
		this.name = name;
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
