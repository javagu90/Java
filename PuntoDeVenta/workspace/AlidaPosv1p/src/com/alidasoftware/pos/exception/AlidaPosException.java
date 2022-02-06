package com.alidasoftware.pos.exception;

public class AlidaPosException extends Exception {

	private static final long serialVersionUID = -3565189729227880876L;

	public AlidaPosException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlidaPosException(Throwable cause) {
		super(cause);
	}

	public AlidaPosException(String message) {
		super(message);
	}

	public AlidaPosException() {
	}

}
