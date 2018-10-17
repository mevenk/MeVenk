/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.POUND_SIGN;
import static java.lang.System.lineSeparator;
import static org.springframework.util.StringUtils.hasLength;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author venky
 *
 */
public abstract class MeVenkWebAppUtil {

	public static final String LINE_SEPARATOR = lineSeparator();

	public static final IllegalStateException ILLEGAL_STATE_EXCEPTION_UTILITY_CLASS = new IllegalStateException(
			"Utility class");

	private MeVenkWebAppUtil() {
		throw ILLEGAL_STATE_EXCEPTION_UTILITY_CLASS;
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
	public static String exceptionStactTraceAsString(Exception exception) {
		StringWriter stringWriter = new StringWriter();
		exception.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 *
	 * @param string
	 * @return
	 */
	public static boolean isStringEmptyOrNull(String string) {
		return hasLength(string);
	}

	/**
	 *
	 * @param strings
	 * @return
	 */
	public static boolean isAnyStringEmptyOrNull(String... strings) {
		for (String string : strings) {
			if (isStringEmptyOrNull(string)) {
				return false;
			}
		}
		return true;
	}

}
