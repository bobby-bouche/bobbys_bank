package internal_validation_classes;

public class Validator {
	
	// string validation method
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
	
	// integer validation method
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
	
	// double validation method
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
