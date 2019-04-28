/**
 * 
 */
package org.mevenk.webservices.logger;

import java.util.LinkedHashMap;

import org.mevenk.webservices.util.MeVenkWebServicesUtil;

/**
 * @author vkolisetty
 *
 */
public final class LoggerFactory {

	private static final LinkedHashMap<Class<?>, Logger> LOGGER_OBJECTS = new LinkedHashMap<Class<?>, Logger>();

	/**
	 * 
	 * @throws Throwable
	 */
	private LoggerFactory() throws Throwable {
		MeVenkWebServicesUtil.throwExceptionInstantiationNotAllowed(LoggerFactory.class);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getlogger(Class<?> clazz) {

		return addOrReturnLogger(clazz);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	private static Logger addOrReturnLogger(Class<?> clazz) {
		if (!LOGGER_OBJECTS.containsKey(clazz)) {
			LOGGER_OBJECTS.put(clazz, new MeVenkWebServicesLogger(clazz));
		}
		return LOGGER_OBJECTS.get(clazz);
	}

}
