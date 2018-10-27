/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppStringUtil {

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private MeVenkWebAppStringUtil() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @param string
	 * @return
	 */
	private static boolean isStringEmptyOrNull(String string) {
		return isEmpty(string) || !hasLength(string.trim());
	}

	/**
	 *
	 * @param strings
	 * @return
	 */
	public static boolean isAnyStringEmptyOrNull(String... strings) {
		for (String string : strings) {
			if (isStringEmptyOrNull(string)) {
				return true;
			}
		}
		return false;
	}

}
