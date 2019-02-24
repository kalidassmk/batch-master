package com.friendsurance.exception;


/**
 * The type App exception.
 */
public class AppException  extends Exception{

	/**
	 * Instantiates a new App exception.
	 */
	public AppException() {
		super();
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	/**
	 * Instantiates a new App exception.
	 *
	 * @param message the message
	 */
	public AppException(String message) {
		super(message);
	}

}
