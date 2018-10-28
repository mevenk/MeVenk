/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.logPropertyDetail;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateIntegerProperty;
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

	private static final String PROPERTY_NAME_DATABASE_DATA_SOURCE_MIN_POOL_SIZE = "database.dataSource.MinPoolSize";
	@Value("${" + PROPERTY_NAME_DATABASE_DATA_SOURCE_MIN_POOL_SIZE + "}")
	private String propertyValueMinPoolSize;
	public static int minPoolSize;

	private static final String PROPERTY_NAME_DATABASE_DATA_SOURCE_MAX_POOL_SIZE = "database.dataSource.MaxPoolSize";
	@Value("${" + PROPERTY_NAME_DATABASE_DATA_SOURCE_MAX_POOL_SIZE + "}")
	private String propertyValueMaxPoolSize;
	public static int maxPoolSize;

	private static final String PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION = "database.statistics.logging.polling.cronExpression";
	@Value("${" + PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION + "}")
	private String propertyValueDatabaseStatisticsLoggingPollingCronExpression;
	private static String databaseStatisticsLoggingPollingCronExpression;

	@PostConstruct
	private void loadBaseProperties() {

		loadDatabaseProperties();

	}

	private void loadDatabaseProperties() {

		minPoolSize = validateIntegerProperty(PROPERTY_NAME_DATABASE_DATA_SOURCE_MIN_POOL_SIZE,
				propertyValueMinPoolSize);
		logPropertyDetail(CLASS_DATABASE_PROPERTIES, PROPERTY_NAME_DATABASE_DATA_SOURCE_MIN_POOL_SIZE, minPoolSize);

		maxPoolSize = validateIntegerProperty(PROPERTY_NAME_DATABASE_DATA_SOURCE_MAX_POOL_SIZE,
				propertyValueMaxPoolSize);

		if (maxPoolSize < minPoolSize) {
			throw new InvalidPropertyValueException(PROPERTY_NAME_DATABASE_DATA_SOURCE_MAX_POOL_SIZE,
					propertyValueMaxPoolSize,
					"Max pool size is cannot be less than Min pool size(" + minPoolSize + ")");
		}

		logPropertyDetail(CLASS_DATABASE_PROPERTIES, PROPERTY_NAME_DATABASE_DATA_SOURCE_MAX_POOL_SIZE, maxPoolSize);

		databaseStatisticsLoggingPollingCronExpression = validateStringProperty(
				PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION,
				propertyValueDatabaseStatisticsLoggingPollingCronExpression);
		logPropertyDetail(CLASS_DATABASE_PROPERTIES, PROPERTY_NAME_DATABASE_STATISTICS_LOGGING_POLLING_CRON_EXPRESSION,
				databaseStatisticsLoggingPollingCronExpression);

	}

	/**
	 * @return the minPoolSize
	 */
	public static final int getMinPoolSize() {
		return minPoolSize;
	}

	/**
	 * @return the maxPoolSize
	 */
	public static final int getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * @return the databaseStatisticsLoggingPollingCronExpression
	 */
	public static final String getDatabaseStatisticsLoggingPollingCronExpression() {
		return databaseStatisticsLoggingPollingCronExpression;
	}

}
