package validation_classes;

@SuppressWarnings("serial")
public class DoubleValidationException extends IllegalArgumentException{
	
	public DoubleValidationException(String message) {
		super(message);
	}
	
	public DoubleValidationException(Throwable cause) {
		super(cause);
	}
	
	public DoubleValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
