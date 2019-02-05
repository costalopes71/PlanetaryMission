package br.com.elo7.planetarymission.exceptions;

public class MovementException extends Exception {

	private static final long serialVersionUID = -2558016983423639041L;
	
	private static final String DEFAULT_MESSAGE = "Invalid movement!";
	
	public MovementException() {
		super(DEFAULT_MESSAGE);
	}

	public MovementException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MovementException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovementException(String arg0) {
		super(arg0);
	}

	public MovementException(Throwable arg0) {
		super(arg0);
	}

}
