package validation_classes;

@SuppressWarnings("serial")
public class IntegerValidationException extends IllegalArgumentException {
	
	public IntegerValidationException(String message) {
		super(message);
	}
	
	public IntegerValidationException(Throwable cause) {
		super(cause);
	}
	
	public IntegerValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
