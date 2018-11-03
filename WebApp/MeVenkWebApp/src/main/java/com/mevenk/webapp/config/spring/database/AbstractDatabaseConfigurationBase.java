package com.mevenk.webapp.config.spring.database;

import static com.mevenk.webapp.config.spring.properties.HibernateProperties.getCacheRegionFactoryClass;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.getDialect;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.getHbm2ddlAuto;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isFormatSQL;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isGenerateStatistics;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isNonContextualCreation;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isShowSQL;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isUseQueryCache;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isUseSQLComments;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.isUseSecondLevelCache;

import java.util.Properties;

import com.mevenk.webapp.config.spring.properties.DataSourceProperties;

public abstract class AbstractDatabaseConfigurationBase {

	protected AbstractDatabaseConfigurationBase() {

	}

	/**
	 *
	 * @return
	 */
	protected static String getDriverClassName() {
		return DataSourceProperties.getDriverClassName();
	}

	/**
	 *
	 * @return
	 */
	protected static String getJdbcUrl() {
		return DataSourceProperties.getJdbcUrl();
	}

	/**
	 *
	 * @return
	 */
	protected static String getUsername() {
		return DataSourceProperties.getUsername();
	}

	/**
	 *
	 * @return
	 */
	protected static String getPassword() {
		return DataSourceProperties.getPassword();
	}

	/**
	 *
	 * @return
	 */
	protected static int getMinPoolSize() {
		return DataSourceProperties.getMinPoolSize();
	}

	/**
	 *
	 * @return
	 */
	protected static int getMaxPoolSize() {
		return DataSourceProperties.getMaxPoolSize();
	}

	/**
	 *
	 * @return
	 */
	protected static int getIdleConnectionTestPeriod() {
		return DataSourceProperties.getIdleConnectionTestPeriod();
	}

	/**
	 *
	 * @return
	 */
	protected static int getMaxIdleTime() {
		return DataSourceProperties.getMaxIdleTime();
	}

	/**
	 *
	 * @return
	 */
	protected static int getAcquireRetryDelay() {
		return DataSourceProperties.getAcquireRetryDelay();
	}

	/**
	 *
	 * @return
	 */
	protected static int getAcquireRetryAttempts() {
		return DataSourceProperties.getAcquireRetryAttempts();
	}

	/**
	 *
	 * @return
	 */
	protected static int getTimeoutConnectionUnReturned() {
		return DataSourceProperties.getTimeoutConnectionUnReturned();
	}

	/**
	 *
	 * @return
	 */
	protected static int getTimeoutCheckout() {
		return DataSourceProperties.getTimeoutCheckout();
	}

	/*
	 * Loading hibernate properties to enable caching
	 */

	protected static final String MIN_CNCTN_SIZE = "hibernate.c3p0.min_size";
	protected static final String MAX_CNCTN_SIZE = "hibernate.c3p0.max_size";
	protected static final String CNCTN_TIMEOUT = "hibernate.c3p0.timeout";
	protected static final String CNCTN_TEST = "hibernate.c3p0.idle_test_period";

	protected static Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", getDialect());
		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", String.valueOf(isNonContextualCreation()));
		properties.setProperty("hibernate.show_sql", String.valueOf(isShowSQL()));
		properties.setProperty("hibernate.format_sql", String.valueOf(isFormatSQL()));
		properties.setProperty("hibernate.use_sql_comments", String.valueOf(isUseSQLComments()));
		properties.setProperty("hibernate.hbm2ddl.auto", getHbm2ddlAuto());
		properties.setProperty("hibernate.generate_statistics", String.valueOf(isGenerateStatistics()));
		properties.setProperty("hibernate.cache.use_second_level_cache", String.valueOf(isUseSecondLevelCache()));
		properties.setProperty("hibernate.cache.use_query_cache", String.valueOf(isUseQueryCache()));
		properties.setProperty("hibernate.cache.region.factory_class", getCacheRegionFactoryClass());
		return properties;
	}
}