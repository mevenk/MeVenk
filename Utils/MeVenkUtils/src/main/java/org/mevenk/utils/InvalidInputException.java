/**
 * 
 */
package org.mevenk.utils;

/**
 * @author vkolisetty
 *
 */
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1916272363178289393L;

	/**
	 * 
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidInputException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param writableStackTrace
	 */
	public InvalidInputException(String message, Throwable cause, boolean writableStackTrace) {
		super(message, cause, false, writableStackTrace);
	}

}
