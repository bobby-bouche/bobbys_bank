package validation_classes;

@SuppressWarnings("serial")
public class AgeValidationException extends IllegalArgumentException {

	public AgeValidationException(String message) {
		super(message);
	}
	
	public AgeValidationException(Throwable cause) {
		super(cause);
	}
	
	public AgeValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
