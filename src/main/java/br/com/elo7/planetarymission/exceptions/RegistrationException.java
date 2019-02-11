package br.com.elo7.planetarymission.exceptions;

public class RegistrationException extends Exception {

	private static final long serialVersionUID = 3894148991914177039L;
	private static final String DEFAULT_MESSAGE = "Planetary Equipment is not registered!";
	
	public RegistrationException() {
		super(DEFAULT_MESSAGE);
	}

	public RegistrationException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
	}

	public RegistrationException(String message, Throwable arg1) {
		super(message, arg1);
	}

	public RegistrationException(String message) {
		super(message);
	}

	public RegistrationException(Throwable arg0) {
		super(arg0);
	}
	
}
