package validation_classes;

@SuppressWarnings("serial")
public class AgeValdationException extends IllegalArgumentException {

	public AgeValdationException(String message) {
		super(message);
	}
	
	public AgeValdationException(Throwable cause) {
		super(cause);
	}
	
	public AgeValdationException(String message, Throwable cause) {
		super(message, cause);
	}
}
