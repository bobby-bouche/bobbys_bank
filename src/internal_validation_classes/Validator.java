/**
 * The Validator class is responsible for enforcing business logic validations 
 * within the application. It provides static methods to validate that data 
 * conforms to specific rules required by the application (e.g., strings are 
 * non-empty, integers are non-negative).
 * 
 * This class is not concerned with how the data is gathered; rather, it ensures 
 * that once data is obtained, it meets the criteria necessary for the application 
 * to function correctly.
 * 
 * Usage Example:
 * Validator.validateString(accountName);
 * 
 * Responsibilities:
 * - Enforce business logic rules on data.
 * - Provide validation methods for common data types (e.g., strings, integers, doubles).
 * - Throw appropriate exceptions when data does not meet required criteria.
 * 
 * This class is part of the internal validation process, focusing on ensuring 
 * that the data used within the application adheres to the required standards.
 */


package internal_validation_classes;

public class Validator {
	
    /**
     * Validates that the provided object is a non-blank string.
     * 
     * @param obj the object to validate; expected to be of type String.
     * @throws IllegalArgumentException if the object is not a string or if it is blank.
     */
	public static void validateString(Object obj) {
		if(obj instanceof String) {
			String value = (String) obj;
			if(value.isBlank()) {
				throw new IllegalArgumentException("Invalid String: Value is blank. ");
			}
		}
		else {
			throw new IllegalArgumentException("Expected a String but got: " + obj.getClass().getName());
		}
	}
	
    /**
     * Validates that the provided object is a non-negative integer.
     * 
     * @param obj the object to validate; expected to be of type Integer.
     * @throws IllegalArgumentException if the object is not an integer or if it is negative.
     */
	public static void validateInteger(Object obj) {
		if(obj instanceof Integer) {
			int number = (Integer) obj;
			if(number < 0) {
				throw new IllegalArgumentException("Invalid Integer: " + number + ". Value must be must be more thn zero");
			}
		}
		else {
			throw new IllegalArgumentException("Expected an Integer but got: " + obj.getClass().getName());
		}
	}
	
    /**
     * Validates that the provided object is a non-negative double.
     * 
     * @param obj the object to validate; expected to be of type Double.
     * @throws IllegalArgumentException if the object is not a double or if it is negative.
     */
	public static void validateDouble(Object obj) {
		if(obj instanceof Double) {
			double number = (double) obj;
			if(number < 0) {
				throw new IllegalArgumentException("Invalid Double: " + number);
			}
		}
		else {
			throw new IllegalArgumentException("Expected a Double but got: " + obj.getClass().getName());
		}
	}
}
