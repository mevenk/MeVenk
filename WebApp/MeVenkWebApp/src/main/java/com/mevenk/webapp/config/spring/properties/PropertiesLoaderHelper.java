/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.util.MeVenkWebAppStringUtil.isAnyStringEmptyOrNull;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.loadPropertiesDependantStaticUtilData;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;

/**
 * @author venky
 *
 */
public abstract class PropertiesLoaderHelper {

	private static final Logger LOG = getLogger(PropertiesLoaderHelper.class);

	private PropertiesLoaderHelper() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @param classPropertiesLoader
	 * @param propertyName
	 * @param propertyValue
	 */
	protected static void logPropertyDetail(Class<?> classPropertiesLoader, String propertyName, Object propertyValue) {
		LOG.debug(classPropertiesLoader + "|Property '" + propertyName + "' loaded with value:" + propertyValue);
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 * @throws Exception
	 */
	public static String validateStringProperty(String propertyName, String propertyValue) {
		propertyValue = propertyValue.trim();
		if (isAnyStringEmptyOrNull(propertyValue)) {
			throw new EmptyPropertyException(propertyName);
		}

		return propertyValue;
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static int validateIntegerProperty(String propertyName, String propertyValue) {

		validateStringProperty(propertyName, propertyValue);

		try {
			return parseInt(propertyValue);
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidNumberException(propertyName, propertyValue);
		}
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static double validateDoubleProperty(String propertyName, String propertyValue) {

		validateStringProperty(propertyName, propertyValue);

		try {
			return parseDouble(propertyValue);
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidNumberException(propertyName, propertyValue);
		}
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	public static double validateLongProperty(String propertyName, String propertyValue) {

		validateStringProperty(propertyName, propertyValue);

		try {
			return parseLong(propertyValue);
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidNumberException(propertyName, propertyValue);
		}
	}

	/**
	 *
	 * @author venky
	 *
	 */
	private static final class EmptyPropertyException extends IllegalArgumentException {

		/**
		 *
		 */
		private static final long serialVersionUID = -2172445220067155324L;

		/**
		 *
		 * @param propertyName
		 */
		protected EmptyPropertyException(String propertyName) {
			super("Value for property [" + propertyName + "] is empty");
		}

	}

	/**
	 *
	 * @author venky
	 *
	 */
	private static final class InvalidNumberException extends IllegalArgumentException {

		/**
		 *
		 */
		private static final long serialVersionUID = -4264134462776424790L;

		/**
		 *
		 * @param propertyName
		 * @param propertyValue
		 */
		protected InvalidNumberException(String propertyName, String propertyValue) {
			super("Invalid number[" + propertyValue + "] set for property [" + propertyName + "]");
		}

	}

	/**
	 *
	 */
	public static void loadPropertiesDependantStaticData() {

		loadPropertiesDependantStaticUtilData();

	}

}

/**
 *
 * @author venky
 *
 */
final class InvalidPropertyValueException extends IllegalArgumentException {

	/**
	 *
	 */
	private static final long serialVersionUID = -2766904879229170160L;

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 * @param errorMessage
	 */
	protected InvalidPropertyValueException(String propertyName, String propertyValue, String errorMessage) {
		super("Invalid number[" + propertyValue + "] set for property [" + propertyName + "] - " + errorMessage);
	}

}