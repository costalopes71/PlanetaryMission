package br.com.elo7.planetarymission.exceptions;

public class LandingException extends Exception {

	private static final long serialVersionUID = 3894148991914177039L;
	private static final String DEFAULT_MESSAGE = "Could not land! Coordenates are probabily occupied!";
	
	public LandingException() {
		super(DEFAULT_MESSAGE);
	}

	public LandingException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
	}

	public LandingException(String message, Throwable arg1) {
		super(message, arg1);
	}

	public LandingException(String message) {
		super(message);
	}

	public LandingException(Throwable arg0) {
		super(arg0);
	}
	
}
