/**
 *
 */
package com.mevenk.webapp.to;

/**
 * @author venky
 *
 */
public class ApplicationExceptionTO {

	public static final String APPLICATION_ERROR_IDENTIFIER = "applicationErrorIdentifier";
	public static final String APPLICATION_ERROR_MESSAGE = "applicationErrorMessage";

	private String applicationErrorIdentifier;
	private String applicationErrorMessage;

	/**
	 * @param applicationErrorIdentifier
	 * @param applicationErrorMessage
	 */
	public ApplicationExceptionTO(String applicationErrorIdentifier, String applicationErrorMessage) {
		super();
		this.applicationErrorIdentifier = applicationErrorIdentifier;
		this.applicationErrorMessage = applicationErrorMessage;
	}

	/**
	 * @return the applicationErrorIdentifier
	 */
	public String getApplicationErrorIdentifier() {
		return applicationErrorIdentifier;
	}

	/**
	 * @return the applicationErrorMessage
	 */
	public String getApplicationErrorMessage() {
		return applicationErrorMessage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApplicationExceptionTO [applicationErrorIdentifier=" + applicationErrorIdentifier
				+ ", applicationErrorMessage=" + applicationErrorMessage + "]";
	}

}
