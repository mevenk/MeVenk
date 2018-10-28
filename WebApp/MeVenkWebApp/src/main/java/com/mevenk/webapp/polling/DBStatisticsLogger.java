/**
 *
 */
package com.mevenk.webapp.polling;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.POLLING;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.exceptionStactTraceAsString;
import static com.mevenk.webapp.util.ThreadContextUtil.resetCorrelationIdThreadContextWithCurrentDate;
import static com.mevenk.webapp.util.expressions.MeVenkWebAppExpressions.CRON_EXPRESSION_DATABASE_STATISTICS_LOGGING_POLLING;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author venky
 *
 */
@Component
public class DBStatisticsLogger {

	private static final Logger LOG = getLogger(DBStatisticsLogger.class);

	@Autowired
	private DataSource dataSource;

	@Scheduled(cron = CRON_EXPRESSION_DATABASE_STATISTICS_LOGGING_POLLING)
	public void logDatabaseStatistics() {
		resetCorrelationIdThreadContextWithCurrentDate("logDatabaseStatistics");
		try {
			ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;

			int totalConnections = comboPooledDataSource.getNumConnectionsDefaultUser();
			int busyConnections = comboPooledDataSource.getNumBusyConnectionsDefaultUser();
			int threadsAwaitingConnection = comboPooledDataSource.getNumThreadsAwaitingCheckoutDefaultUser();

			int threadPoolSize = comboPooledDataSource.getThreadPoolSize();
			int activeThreads = comboPooledDataSource.getThreadPoolNumActiveThreads();
			int tasksPending = comboPooledDataSource.getThreadPoolNumTasksPending();

			LOG.log(POLLING,
					"DB Connection Pool statistics: connections [total={}, busy={}], threadsAwaiting={}, "
							+ " worker threadpool [total={}, active={}, pendingTasks={}]",
					totalConnections, busyConnections, threadsAwaitingConnection, threadPoolSize, activeThreads,
					tasksPending);

		} catch (SQLException exception) {
			LOG.error("SQLException while logging datbase connections: {}", exceptionStactTraceAsString(exception));

		} catch (Exception exception) {
			LOG.error("Exception while logging datbase connections: {}", exceptionStactTraceAsString(exception));

		}

	}

}
