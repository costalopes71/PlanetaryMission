package br.com.elo7.planetarymission.exceptions;

public class AutoDestroyException extends Exception {

	private static final long serialVersionUID = 3894148991914177039L;
	private static final String DEFAULT_MESSAGE = "Can not auto destroy equipment!";
	
	public AutoDestroyException() {
		super(DEFAULT_MESSAGE);
	}

	public AutoDestroyException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
	}

	public AutoDestroyException(String message, Throwable arg1) {
		super(message, arg1);
	}

	public AutoDestroyException(String message) {
		super(message);
	}

	public AutoDestroyException(Throwable arg0) {
		super(arg0);
	}
	
}
