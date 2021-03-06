/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.ANGLE_BRACKET_CLOSE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.ANGLE_BRACKET_OPEN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.COMMA_AND_SPACE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.EMPTY_STRING;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.PARENTHESES_CLOSE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.PARENTHESES_OPEN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.POUND_SIGN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SQUARE_BRACKET_CLOSE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SQUARE_BRACKET_OPEN;
import static java.lang.System.lineSeparator;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static java.util.UUID.randomUUID;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.mevenk.webapp.config.spring.properties.BaseProperties;

/**
 * @author venky
 *
 */
public abstract class MeVenkWebAppUtil {

	public static final String LINE_SEPARATOR = lineSeparator();

	private static final Class<?>[] baseClasses = { String.class, Byte.class, Short.class, Integer.class, Long.class,
			Float.class, Double.class, Boolean.class };

	private static final String ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private static final int INT_MAX_VALUE_LENGTH = String.valueOf(Integer.MAX_VALUE).length();

	public static final IllegalAccessException ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS = new IllegalAccessException(
			"Utility class");
	
	private static final NullPointerException NULL_POINTER_EXCEPTION = new NullPointerException();
	
	private static final Random RANDOM = new Random();

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private MeVenkWebAppUtil() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @return
	 */
	public static String randomUUIDString() {
		return randomUUID().toString();
	}

	/**
	 *
	 * @return
	 */
	public static Date randomDate() {
		return randomFutureDate();
	}

	/**
	 *
	 * @return
	 */
	public static Date randomPastDate() {

		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(YEAR);
		calendar.set(YEAR, currentYear - RANDOM.nextInt(10));
		calendar.set(MONTH, currentYear - RANDOM.nextInt(11));
		calendar.set(DAY_OF_MONTH, currentYear - RANDOM.nextInt(26));
		calendar.set(HOUR, currentYear - RANDOM.nextInt(11));
		calendar.set(MINUTE, currentYear - RANDOM.nextInt(59));
		calendar.set(SECOND, currentYear - RANDOM.nextInt(59));
		calendar.set(MILLISECOND, currentYear - RANDOM.nextInt(998));
		return calendar.getTime();
	}

	/**
	 *
	 * @return
	 */
	public static Date randomFutureDate() {

		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(YEAR);
		calendar.set(YEAR, currentYear + RANDOM.nextInt(10));
		calendar.set(MONTH, currentYear + RANDOM.nextInt(11));
		calendar.set(DAY_OF_MONTH, currentYear + RANDOM.nextInt(26));
		calendar.set(HOUR, currentYear + RANDOM.nextInt(11));
		calendar.set(MINUTE, currentYear + RANDOM.nextInt(59));
		calendar.set(SECOND, currentYear + RANDOM.nextInt(59));
		calendar.set(MILLISECOND, currentYear + RANDOM.nextInt(998));
		return calendar.getTime();
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static final String getDateFormatted(Date date) {
		return new SimpleDateFormat(BaseProperties.getMiscDateFormatPattern()).format(date);
	}

	/**
	 *
	 * @return
	 */
	public static final String getDateFormattedCurrentDate() {
		return getDateFormatted(new Date());
	}

	/**
	 *
	 * @return
	 */
	public static int randomPositiveNumber() {
		return randomPositiveNumber(5);
	}

	/**
	 *
	 * @param numberSize
	 * @return
	 */
	public static int randomPositiveNumber(int numberSize) {
		if (numberSize > INT_MAX_VALUE_LENGTH) {
			numberSize = INT_MAX_VALUE_LENGTH;
		}

		StringBuilder stringBuilderRandomPositiveNumber = new StringBuilder();
		for (int index = 0; index < numberSize; index++) {
			stringBuilderRandomPositiveNumber.append(RANDOM.nextInt(10));
		}
		return Integer.valueOf(stringBuilderRandomPositiveNumber.toString());
	}

	/**
	 *
	 * @return
	 */
	public static String randomString() {
		return randomString(10);
	}

	/**
	 *
	 * @param requiredStringLength
	 * @return
	 */
	public static String randomString(int requiredStringLength) {

		StringBuilder stringBuilderRandomString = new StringBuilder();
		for (int index = 0; index < requiredStringLength; index++) {
			stringBuilderRandomString.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length() - 1)));
		}
		return stringBuilderRandomString.toString();
	}

	/**
	 *
	 * @param objects
	 * @return
	 */
	public static String methodArgumentsAsString(Object... objects) {
		if (objects.length <= 0) {
			return EMPTY_STRING;
		}
		StringBuilder stringBuilderObjects = new StringBuilder();
		stringBuilderObjects.append(PARENTHESES_OPEN);
		for (Object object : objects) {
			if(object == null) {
				stringBuilderObjects.append("null" + COMMA_AND_SPACE);
				continue;
			}
			stringBuilderObjects.append(object.toString() + COMMA_AND_SPACE);

		}
		stringBuilderObjects.delete(stringBuilderObjects.lastIndexOf(COMMA_AND_SPACE), stringBuilderObjects.length());
		stringBuilderObjects.append(PARENTHESES_CLOSE);
		return stringBuilderObjects.toString();
	}

	/**
	 *
	 * @param objects
	 * @return
	 */
	public static String objectArrayAsString(Object... objects) {
		if (objects.length <= 0) {
			return EMPTY_STRING;
		}
		StringBuilder stringBuilderObjects = new StringBuilder();
		stringBuilderObjects.append(SQUARE_BRACKET_OPEN);
		for (Object object : objects) {
			if (object instanceof String) {
				stringBuilderObjects.append(object.toString() + COMMA_AND_SPACE);
			} else {
				stringBuilderObjects.append(
						ANGLE_BRACKET_OPEN + object.getClass().getSimpleName() + ANGLE_BRACKET_CLOSE + COMMA_AND_SPACE);
			}
		}
		stringBuilderObjects.delete(stringBuilderObjects.lastIndexOf(COMMA_AND_SPACE), stringBuilderObjects.length());
		stringBuilderObjects.append(SQUARE_BRACKET_CLOSE);
		return stringBuilderObjects.toString();
	}

	/**
	 *
	 * @param typeRequired
	 * @param objects
	 * @return
	 */
	public static String argumentsAsAppendableString(boolean typeRequired, Object... objects) {
		StringBuilder stringBuilderObjects = new StringBuilder();
		for (Object object : objects) {
			if (object != null && isBaseClass(object)) {
				stringBuilderObjects.append(POUND_SIGN + String.valueOf(object));
			} else if (typeRequired) {
				stringBuilderObjects.append(
						POUND_SIGN + SQUARE_BRACKET_OPEN + object.getClass().getSimpleName() + SQUARE_BRACKET_CLOSE);
			}
		}
		return stringBuilderObjects.toString();
	}

	/**
	 *
	 * @param object
	 * @return
	 */
	private static boolean isBaseClass(Object object) {
		for (Class<?> currentClass : baseClasses) {
			if (object.getClass().equals(currentClass)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * @param prefix
	 * @return
	 */
	public static String appendSuffixPoundSign(String prefix) {
		return prefix + POUND_SIGN;
	}

	/**
	 *
	 * @param exception
	 * @return
	 */
	public static String exceptionStactTraceAsString(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
	
	/**
	 * 
	 * @param throwException
	 * @param objects
	 * @return
	 */
	public static final boolean validateNotNull(boolean throwException, Object[] objects) {
		if (objects == null) {
			throw NULL_POINTER_EXCEPTION;
		}
		for (Object obj : objects) {
			if (obj == null) {
				if (throwException) {
					throw NULL_POINTER_EXCEPTION;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param objects
	 * @return
	 */
	public static final boolean validateNotNull(Object... objects) {
		return validateNotNull(true, objects);
	}

}
