package br.com.elo7.planetarymission.exceptions;

public class SerializePhotosException extends Exception {

	private static final long serialVersionUID = -1358514050241156301L;

	private static final String DEFAULT_MESSAGE = "Error while trying to serialize photos back to Earth!";
	
	public SerializePhotosException() {
		super(DEFAULT_MESSAGE);
	}

	public SerializePhotosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SerializePhotosException(String message, Throwable cause) {
		super(message, cause);
	}

	public SerializePhotosException(String message) {
		super(message);
	}

	public SerializePhotosException(Throwable cause) {
		super(cause);
	}
	
}
