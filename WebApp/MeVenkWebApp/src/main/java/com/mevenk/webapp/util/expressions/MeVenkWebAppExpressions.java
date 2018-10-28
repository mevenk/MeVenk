/**
 *
 */
package com.mevenk.webapp.util.expressions;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppExpressions {

	private MeVenkWebAppExpressions() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	public static final String DATE_POLLING_CRON_EXPRESSION = "${datePollingCronExpression}";

	public static final String CRON_EXPRESSION_DATABASE_STATISTICS_LOGGING_POLLING = "${database.statistics.logging.polling.cronExpression}";

}
