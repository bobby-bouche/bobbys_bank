package internal_validation_classes;

@SuppressWarnings("serial")
public class IllegalWithdrawException extends IllegalArgumentException  {

	/**
	 * Constructs a new IllegalWithdrawException with the specified detail message.
	 *
	 * @param message the detail message.
	 */
	public IllegalWithdrawException(String message) {
		super(message);
	}
	
	/**
	 * Constructs a new IllegalWithdrawException with the specified cause.
	 *
	 * @param cause the cause of the exception.
	 */
	public IllegalWithdrawException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructs a new IllegalWithdrawException with the specified detail message and cause.
	 *
	 * @param message the detail message.
	 * @param cause the cause of the exception.
	 */
	public IllegalWithdrawException(String message, Throwable cause) {
		super(message, cause);
	}
}
