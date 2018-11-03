/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateIntegerProperty;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateStringProperty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author venky
 *
 */
public class DataSourceProperties extends DatabaseProperties {

	private static final Class<DataSourceProperties> CLASS_DATA_SOURCE_PROPERTIES = DataSourceProperties.class;

	public static final String BEAN_DATA_SOURCE_PROPERTIES = "BEAN_DATA_SOURCE_PROPERTIES";

	private static final String PROPERTY_NAME_DRIVER_CLASS_NAME = "database.dataSource.driver.class.name";
	@Value("${" + PROPERTY_NAME_DRIVER_CLASS_NAME + "}")
	private String propertyValueDriverClassName;
	private static String driverClassName;

	private static final String PROPERTY_NAME_JDBC_URL = "database.dataSource.jdbcURL";
	@Value("${" + PROPERTY_NAME_JDBC_URL + "}")
	private String propertyValueJDBCURL;
	private static String jdbcUrl;

	private static final String PROPERTY_NAME_USERNAME = "database.dataSource.username";
	@Value("${" + PROPERTY_NAME_USERNAME + "}")
	private String propertyValueUsername;
	private static String username;

	private static final String PROPERTY_NAME_PASSWORD = "database.dataSource.password";
	@Value("${" + PROPERTY_NAME_PASSWORD + "}")
	private String propertyValuePassword;
	private static String password;

	private static final String PROPERTY_NAME_IDLE_CONNECTION_TEST_PERIOD = "database.dataSource.idle.connection.test.period";
	@Value("${" + PROPERTY_NAME_IDLE_CONNECTION_TEST_PERIOD + "}")
	private String propertyValueIdleConnectionTestPeriod;
	private static int idleConnectionTestPeriod;

	private static final String PROPERTY_NAME_MIN_POOL_SIZE = "database.dataSource.minPoolSize";
	@Value("${" + PROPERTY_NAME_MIN_POOL_SIZE + "}")
	private String propertyValueMinPoolSize;
	private static int minPoolSize;

	private static final String PROPERTY_NAME_MAX_POOL_SIZE = "database.dataSource.maxPoolSize";
	@Value("${" + PROPERTY_NAME_MAX_POOL_SIZE + "}")
	private String propertyValueMaxPoolSize;
	private static int maxPoolSize;

	private static final String PROPERTY_NAME_MAX_IDLE_TIME = "database.dataSource.maxIdleTime";
	@Value("${" + PROPERTY_NAME_MAX_IDLE_TIME + "}")
	private String propertyValueMaxIdleTime;
	private static int maxIdleTime;

	private static final String PROPERTY_NAME_ACQUIRE_RETRY_DELAY = "database.dataSource.acquireRetryDelay";
	@Value("${" + PROPERTY_NAME_ACQUIRE_RETRY_DELAY + "}")
	private String propertyValueAcquireRetryDelay;
	private static int acquireRetryDelay;

	private static final String PROPERTY_NAME_ACQUIRE_RETRY_ATTEMPTS = "database.dataSource.acquireRetryAttempts";
	@Value("${" + PROPERTY_NAME_ACQUIRE_RETRY_ATTEMPTS + "}")
	private String propertyValueAcquireRetryAttempts;
	private static int acquireRetryAttempts;

	private static final String PROPERTY_NAME_TIMEOUT_CONNECTION_UN_RETURNED = "database.dataSource.timeout.connection.unReturned";
	@Value("${" + PROPERTY_NAME_TIMEOUT_CONNECTION_UN_RETURNED + "}")
	private String propertyValueTimeoutConnectionUnReturned;
	private static int timeoutConnectionUnReturned;

	private static final String PROPERTY_NAME_TIMEOUT_CHECKOUT = "database.dataSource.timeout.checkout";
	@Value("${" + PROPERTY_NAME_TIMEOUT_CHECKOUT + "}")
	private String propertyValueTimeoutCheckout;
	private static int timeoutCheckout;

	/**
	 *
	 */
	@PostConstruct
	private void loadDataSourceProperties() {

		loadDriverProperties(propertyValueDriverClassName, propertyValueJDBCURL);
		loadAuthenticationProperties(propertyValueUsername, propertyValuePassword);
		loadConfigurationProperties(propertyValueIdleConnectionTestPeriod, propertyValueMinPoolSize,
				propertyValueMaxPoolSize, propertyValueMaxIdleTime, propertyValueAcquireRetryDelay,
				propertyValueAcquireRetryAttempts);
		loadTimeOutProperties(propertyValueTimeoutConnectionUnReturned, propertyValueTimeoutCheckout);

	}

	/**
	 *
	 * @param propertyValueDriverClassName
	 * @param propertyValueJDBCURL
	 */
	private static void loadDriverProperties(String propertyValueDriverClassName, String propertyValueJDBCURL) {
		driverClassName = validateStringProperty(PROPERTY_NAME_DRIVER_CLASS_NAME, propertyValueDriverClassName);
		logDataSourceProperty(PROPERTY_NAME_DRIVER_CLASS_NAME, driverClassName);

		jdbcUrl = validateStringProperty(PROPERTY_NAME_JDBC_URL, propertyValueJDBCURL);
		logDataSourceProperty(PROPERTY_NAME_JDBC_URL, jdbcUrl);
	}

	/**
	 *
	 * @param propertyValueUsername
	 * @param propertyValuePassword
	 */
	private static void loadAuthenticationProperties(String propertyValueUsername, String propertyValuePassword) {

		username = validateStringProperty(PROPERTY_NAME_USERNAME, propertyValueUsername);
		logDataSourceProperty(PROPERTY_NAME_USERNAME, username);

		password = validateStringProperty(PROPERTY_NAME_PASSWORD, propertyValuePassword);
		logDataSourceProperty(PROPERTY_NAME_PASSWORD, password);

	}

	/**
	 *
	 * @param propertyValueIdleConnectionTestPeriod
	 * @param propertyValueMinPoolSize
	 * @param propertyValueMaxPoolSize
	 * @param propertyValueMaxIdleTime
	 * @param propertyValueAcquireRetryDelay
	 * @param propertyValueAcquireRetryAttempts
	 */
	private static void loadConfigurationProperties(String propertyValueIdleConnectionTestPeriod,
			String propertyValueMinPoolSize, String propertyValueMaxPoolSize, String propertyValueMaxIdleTime,
			String propertyValueAcquireRetryDelay, String propertyValueAcquireRetryAttempts) {

		idleConnectionTestPeriod = validateIntegerProperty(PROPERTY_NAME_IDLE_CONNECTION_TEST_PERIOD,
				propertyValueIdleConnectionTestPeriod);
		logDataSourceProperty(PROPERTY_NAME_IDLE_CONNECTION_TEST_PERIOD, idleConnectionTestPeriod);

		minPoolSize = validateIntegerProperty(PROPERTY_NAME_MIN_POOL_SIZE, propertyValueMinPoolSize);
		logDataSourceProperty(PROPERTY_NAME_MIN_POOL_SIZE, minPoolSize);

		maxPoolSize = validateIntegerProperty(PROPERTY_NAME_MAX_POOL_SIZE, propertyValueMaxPoolSize);

		if (maxPoolSize < minPoolSize) {
			throw new InvalidPropertyValueException(PROPERTY_NAME_MAX_POOL_SIZE, propertyValueMaxPoolSize,
					"Max pool size is cannot be less than Min pool size(" + minPoolSize + ")");
		}

		logDataSourceProperty(PROPERTY_NAME_MAX_POOL_SIZE, maxPoolSize);

		maxIdleTime = validateIntegerProperty(PROPERTY_NAME_MAX_IDLE_TIME, propertyValueMaxIdleTime);
		logDataSourceProperty(PROPERTY_NAME_MAX_IDLE_TIME, maxIdleTime);

		acquireRetryDelay = validateIntegerProperty(PROPERTY_NAME_ACQUIRE_RETRY_DELAY, propertyValueAcquireRetryDelay);
		logDataSourceProperty(PROPERTY_NAME_ACQUIRE_RETRY_DELAY, acquireRetryDelay);

		acquireRetryAttempts = validateIntegerProperty(PROPERTY_NAME_ACQUIRE_RETRY_ATTEMPTS,
				propertyValueAcquireRetryAttempts);
		logDataSourceProperty(PROPERTY_NAME_ACQUIRE_RETRY_ATTEMPTS, acquireRetryAttempts);

	}

	/**
	 *
	 * @param propertyValueTimeoutConnectionUnReturned
	 * @param propertyValueTimeoutCheckout
	 */
	private static void loadTimeOutProperties(String propertyValueTimeoutConnectionUnReturned,
			String propertyValueTimeoutCheckout) {

		timeoutConnectionUnReturned = validateIntegerProperty(PROPERTY_NAME_TIMEOUT_CONNECTION_UN_RETURNED,
				propertyValueTimeoutConnectionUnReturned);
		logDataSourceProperty(PROPERTY_NAME_TIMEOUT_CONNECTION_UN_RETURNED, timeoutConnectionUnReturned);

		timeoutCheckout = validateIntegerProperty(PROPERTY_NAME_TIMEOUT_CHECKOUT, propertyValueTimeoutCheckout);
		logDataSourceProperty(PROPERTY_NAME_TIMEOUT_CHECKOUT, timeoutCheckout);

	}

	/**
	 * @return the driverClassName
	 */
	public static final String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @return the jdbcUrl
	 */
	public static final String getJdbcUrl() {
		return jdbcUrl;
	}

	/**
	 * @return the username
	 */
	public static final String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public static final String getPassword() {
		return password;
	}

	/**
	 * @return the idleConnectionTestPeriod
	 */
	public static final int getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
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
	 * @return the maxIdleTime
	 */
	public static final int getMaxIdleTime() {
		return maxIdleTime;
	}

	/**
	 * @return the acquireRetryDelay
	 */
	public static final int getAcquireRetryDelay() {
		return acquireRetryDelay;
	}

	/**
	 * @return the acquireRetryAttempts
	 */
	public static final int getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}

	/**
	 * @return the timeoutConnectionUnReturned
	 */
	public static final int getTimeoutConnectionUnReturned() {
		return timeoutConnectionUnReturned;
	}

	/**
	 * @return the timeoutCheckout
	 */
	public static final int getTimeoutCheckout() {
		return timeoutCheckout;
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 */
	private static void logDataSourceProperty(String propertyName, Object propertyValue) {
		logDatabaseProperty(CLASS_DATA_SOURCE_PROPERTIES, propertyName, propertyValue);
	}

}
