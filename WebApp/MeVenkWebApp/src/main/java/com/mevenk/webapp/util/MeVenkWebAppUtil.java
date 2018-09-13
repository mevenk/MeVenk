/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.POUND_SIGN;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppUtil {

	private MeVenkWebAppUtil() {
		throw new IllegalStateException("Utility class");
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
}
