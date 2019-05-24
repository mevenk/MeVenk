/**
 * 
 */
package com.mevenk.webapp.exception.config.dao;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.LINE_SEPARATOR;

import com.mevenk.webapp.exception.MeVenkWebAppException;

/**
 * @author vkolisetty
 *
 */
public final class DatabaseConfigException extends MeVenkWebAppException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6751381293572604084L;

	/**
	 * 
	 * @param cause
	 * @param description
	 * @param action
	 */
	public DatabaseConfigException(Throwable cause, String configurationModule, String description, String action) {
		super(cause, generateConfigurationModuleDetail(configurationModule) + description, action);
	}

	/**
	 * 
	 * @param configurationModule
	 */
	private static final String generateConfigurationModuleDetail(String configurationModule) {

		return "An exception occured while configuring Database for " + configurationModule + LINE_SEPARATOR;
	}

}
