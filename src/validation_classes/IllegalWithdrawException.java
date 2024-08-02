package validation_classes;

@SuppressWarnings("serial")
public class IllegalWithdrawException extends IllegalArgumentException  {

	public IllegalWithdrawException(String message) {
		super(message);
	}
	
	public IllegalWithdrawException(Throwable cause) {
		super(cause);
	}
	
	public IllegalWithdrawException(String message, Throwable cause) {
		super(message, cause);
	}
}
