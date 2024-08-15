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

}
