/**
 *
 */
package com.mevenk.webapp.polling;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.POLLING;
import static com.mevenk.webapp.util.ThreadContextUtil.setCorrelationIdThreadContext;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.VERTICAL_BAR;
import static com.mevenk.webapp.util.expressions.MeVenkWebAppExpressions.DATE_POLLING_CRON_EXPRESSION;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author venky
 *
 */
@Component
public class TimelyDatePolling {

	private static final Logger LOG = getLogger(TimelyDatePolling.class);

	@Value("${timelyLoggerDateFormatPattern}")
	private String timelyLoggerDateFormatPattern;

	@Scheduled(cron = DATE_POLLING_CRON_EXPRESSION)
	public void timelyDatePollingFromCron() {
		String formattedDate = new SimpleDateFormat(timelyLoggerDateFormatPattern).format(new Date());
		setCorrelationIdThreadContext("com.mevenk.webapp.polling.TimelyDatePolling.timelyDatePollingFromCron()"
				+ VERTICAL_BAR + formattedDate);
		LOG.log(POLLING, "Polling..... | {}", formattedDate);
	}
}
