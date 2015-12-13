package dolmisani.test.accesswrapper;

public class AccessException extends RuntimeException {

	
	private static final long serialVersionUID = -5768765512324542894L;

	
	public AccessException() {
		
	}

	public AccessException(String message) {
		super(message);
	}

	public AccessException(Throwable cause) {
		super(cause);
	}

	public AccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
