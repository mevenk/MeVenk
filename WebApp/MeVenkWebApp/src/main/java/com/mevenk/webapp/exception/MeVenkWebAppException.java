/**
 * 
 */
package com.mevenk.webapp.exception;

/**
 * @author vkolisetty
 *
 */
@SuppressWarnings("serial")
public abstract class MeVenkWebAppException extends RuntimeException {

	private String description;
	private String action;

	/**
	 * @param message
	 * @param cause
	 */
	public MeVenkWebAppException(Throwable cause, String description, String action) {
		super(description, cause, false, true);
		this.description = description;
		this.action = action;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return the action
	 */
	public final String getAction() {
		return action;
	}

}
