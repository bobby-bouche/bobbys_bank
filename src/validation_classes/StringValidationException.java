package validation_classes;

@SuppressWarnings("serial")
public class StringValidationException extends IllegalArgumentException{

	public StringValidationException(String message) {
		super(message);
	}
	
	public StringValidationException(Throwable cause) {
		super(cause);
	}
	
	public StringValidationException(String message, Throwable cause) {
		super(message, cause);
	}
}
