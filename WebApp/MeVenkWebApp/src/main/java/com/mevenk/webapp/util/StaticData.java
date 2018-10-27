/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

import java.text.SimpleDateFormat;

/**
 * @author venky
 *
 */
class StaticData {

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private StaticData() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 */
	protected static SimpleDateFormat simpleDateFormatCorrelationId;

	/**
	 *
	 */
	protected static int correlationIdDateFormatPatternLength;

}
