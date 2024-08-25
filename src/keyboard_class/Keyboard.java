/**
 * The Keyboard class is responsible for handling user input from the console.
 * It provides methods to read and validate basic input types (e.g., integers, strings) 
 * directly from the user. The validation here ensures that the input is in the correct 
 * format (e.g., an integer is actually an integer) but does not enforce any business 
 * logic rules.
 * 
 * Usage Example:
 * int userInput = keyboard.readInteger("Enter a number: ", "Please enter a valid number.");
 * 
 * Responsibilities:
 * - Gather input from the user.
 * - Ensure input is in the correct data format.
 * - Return the validated input for further processing.
 * 
 * This class is part of the external validation process, focusing on ensuring
 * that the user's input can be correctly parsed into the desired type.
 */


package keyboard_class;

import java.util.Scanner;

import data_classes.BankAccount;
import internal_validation_classes.Validator;

public class Keyboard {
	
	// keyboard fields
	private static Scanner input;
	BankAccount account;
	
	
	/**
	 * Constructor for the Keyboard class.
	 * 
	 * Initializes the Scanner object to read input from the console.
	 */
	public Keyboard() {
		input = new Scanner(System.in);
	}
	
	
	/**
	 * Prompts the user for integer input, validates the input, and returns the valid integer.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid integer is entered. If the user enters an invalid integer (i.e., the input
	 * cannot be parsed as an integer), the method displays an error message and prompts the 
	 * user to try again. The method returns the valid integer once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid integer.
	 * @return the integer value entered by the user.
	 */
	public int readInteger(String promptMsg, String errorMsg) {
		
		int num = 0;
		String strInput;
		boolean valid = false;
		
		while(valid == false) {
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				num = Integer.parseInt(strInput);
				valid = true;
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	/**
	 * Prompts the user for integer input within a specified range, validates the input, 
	 * and returns the valid integer.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid integer within the specified range is entered. If the user enters an 
	 * invalid integer (i.e., the input cannot be parsed as an integer or is outside the range), 
	 * the method displays an error message and prompts the user to try again. The method returns 
	 * the valid integer once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid integer or is out of range.
	 * @param low the lower bound of the valid range (inclusive).
	 * @param high the upper bound of the valid range (inclusive).
	 * @return the integer value entered by the user within the specified range.
	 */
	public int readInteger(String promptMsg, String errorMsg, int low, int high) {
		
		int num = 0;
		String strInput;
		boolean valid = false;
		
		while(!valid) {
			
			System.out.println(promptMsg);
			input.nextLine();
			strInput = input.nextLine();
			
			try {
				num = Integer.parseInt(strInput);
				if(num >= low && num <= high) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	/**
	 * Prompts the user for integer input within a specified range, validates the input, 
	 * and returns the valid integer.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid integer within the specified range is entered. If the user enters an 
	 * invalid integer (i.e., the input cannot be parsed as an integer or is outside the range), 
	 * the method displays an error message and prompts the user to try again. The method returns 
	 * the valid integer once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid integer or is out of range.
	 * @param low the lower bound of the valid range (inclusive).
	 * @param high the upper bound of the valid range (inclusive).
	 * @return the integer value entered by the user within the specified range.
	 */
	public int readInteger(String errorMsg, int low, int high) {
		
		int num = 0;
		String strInput;
		boolean valid = false;
		
		while(!valid) {
		
			input.nextLine();
			strInput = input.nextLine();
			
			try {
				num = Integer.parseInt(strInput);
				if(num >= low && num <= high) {
					valid = true;
				}
				else {
					System.out.println(errorMsg);
				}
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	
	
	
	
	
	public Double readDouble(String promptMsg, String errorMsg) {
		
		Double num = 0.0;
		String strInput;
		boolean valid = false;
		
		while(valid == false) {
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				num = Double.parseDouble(strInput);
				valid = true;
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	/**
	 * Prompts the user for string input, validates the input, and returns the valid string.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid string is entered. A valid string is one that is not null or blank. 
	 * If the user enters an invalid string (i.e., the input is null or blank), the method 
	 * displays an error message and prompts the user to try again. The method returns the 
	 * valid string once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid string.
	 * @return the string entered by the user.
	 */
	public String readString(String promptMsg, String errorMsg) {
		
		String strInput = null;
		boolean valid = false;
		
		while(!valid) { 
			
			System.out.println(promptMsg);
			strInput = input.nextLine();	

			if(!(strInput == null) && !strInput.isBlank()) {
				valid = true;
			}
			else {
				System.out.println(errorMsg);
			}
		}
		return strInput;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// these violate what the class is actually meant to do. this logic doesnt belong here
	
	
	
	/**
	 * Prompts the user for an account number, validates the input, and returns the valid account number.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid account number is entered. A valid account number is one that is at least
	 * 4 digits long and greater than 1000. If the user enters an invalid account number (i.e., 
	 * the input cannot be parsed as an integer or does not meet the criteria), the method displays 
	 * an error message and prompts the user to try again. The method returns the valid account number 
	 * once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid account number.
	 * @return the account number entered by the user.
	 */
	public int readAccountNumber(String promptMsg, String errorMsg) {
		
		int num = 0;
		String strInput;
		boolean valid = false; // refine this to use readString()
		
		while(!valid) {
			
			System.out.println(promptMsg);
			strInput = input.nextLine();
			
			try {
				if(strInput.length() >= 4) {
					num = Integer.parseInt(strInput);
					if(num > 1000) {  // this logic belongs it bank account (account.validateAccountNumber)
						valid = true;
					}
				}
			}
			catch(NumberFormatException e) {
				System.out.println(errorMsg);
			}
		}
		return num;
	}
	
	
	
	/**
	 * Prompts the user for an account type, validates the input, and returns the valid account type.
	 * 
	 * This method continuously prompts the user for input using the provided prompt message
	 * until a valid account type is entered. A valid account type is either "savings" or 
	 * "checking", case insensitive. If the user enters an invalid account type, the method 
	 * displays an error message and prompts the user to try again. The method returns the 
	 * valid account type once entered by the user.
	 *
	 * @param promptMsg the message to display to prompt the user for input.
	 * @param errorMsg the error message to display if the input is not a valid account type.
	 * @return the account type entered by the user ("savings" or "checking").
	 */
	public String readAccountType(String promptMsg, String errorMsg) {
		
		String strInput = null;
		boolean valid = false;
		
		while(!valid){
			
			System.out.println(promptMsg);
			strInput = input.nextLine();	
			
			if(BankAccount.isValidAccountType(strInput)) { // this logic belongs in (account.validateAccountType())
				valid = true;
			}
			else {
				System.out.println(errorMsg);
			}
		}
		return strInput;
	}
	
	


}


