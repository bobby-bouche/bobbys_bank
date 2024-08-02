package validation_classes;

@SuppressWarnings("serial")
public class InvalidDepositException extends IllegalArgumentException{

	public InvalidDepositException(String message) {
		super(message);
	}
	
	public InvalidDepositException(Throwable cause) {
		super(cause);
	}
	
	public InvalidDepositException(String message, Throwable cause) {
		super(message, cause);
	}
}
