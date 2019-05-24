/**
 * 
 */
package com.mevenk.webapp.exception.config.properties;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.LINE_SEPARATOR;

import com.mevenk.webapp.exception.MeVenkWebAppException;

/**
 * @author vkolisetty
 *
 */
public class PropertiesConfigException extends MeVenkWebAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8479200537557380296L;

	private static final String DESCRIPTION_DETAIL = "An exception occured while parsing properties" + LINE_SEPARATOR;

	/**
	 * 
	 * @param cause
	 * @param description
	 */
	public PropertiesConfigException(Throwable cause, String description) {
		super(cause, DESCRIPTION_DETAIL + description, "Please check the properties file");
	}

}
