/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.logPropertyDetail;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateStringProperty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author venky
 *
 */
public class DatabaseProperties {

	private static final Class<DatabaseProperties> CLASS_DATABASE_PROPERTIES = DatabaseProperties.class;

	public static final String BEAN_DATABASE_PROPERTIES = "BEAN_DATABASE_PROPERTIES";

	private static final String PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION = "database.statistics.logging.polling.cronExpression";
	@Value("${" + PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION + "}")
	private String propertyValueDatabaseStatisticsLoggingPollingCronExpression;
	private static String databaseStatisticsLoggingPollingCronExpression;

	/**
	 *
	 */
	@PostConstruct
	private void loadDatabaseProperties() {

		loadStatisticsProperties(propertyValueDatabaseStatisticsLoggingPollingCronExpression);

	}

	/**
	 *
	 * @param propertyValueDatabaseStatisticsLoggingPollingCronExpression
	 */
	private static void loadStatisticsProperties(String propertyValueDatabaseStatisticsLoggingPollingCronExpression) {
		databaseStatisticsLoggingPollingCronExpression = validateStringProperty(
				PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION,
				propertyValueDatabaseStatisticsLoggingPollingCronExpression);
		logDatabaseProperty(CLASS_DATABASE_PROPERTIES,
				PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION,
				databaseStatisticsLoggingPollingCronExpression);

	}

	/**
	 * @return the databaseStatisticsLoggingPollingCronExpression
	 */
	public static final String getDatabaseStatisticsLoggingPollingCronExpression() {
		return databaseStatisticsLoggingPollingCronExpression;
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 */
	protected static void logDatabaseProperty(Class<?> classDatabaseProperties, String propertyName,
			Object propertyValue) {
		logPropertyDetail(classDatabaseProperties, propertyName, propertyValue);
	}

}
