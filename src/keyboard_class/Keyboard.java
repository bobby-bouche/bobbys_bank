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

public class Keyboard {
	
	// keyboard fields
	private static Scanner input;
	
	
	// constructor
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
	
	
	
	
	

}


