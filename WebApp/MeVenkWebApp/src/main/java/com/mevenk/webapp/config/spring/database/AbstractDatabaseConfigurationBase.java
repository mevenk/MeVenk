package com.mevenk.webapp.config.spring.database;

import java.util.Properties;

public abstract class AbstractDatabaseConfigurationBase {

	protected static final String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	protected static final String URL = "jdbc:postgresql://VENKATESH-NUC:5432/mevenk?application_name=webapp";
	protected static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

	protected static final String USERNAME = "venkatesh";
	protected static final String PASSWORD = "venkatesh";

	protected static final String SHOWSQL = "true";
	protected static final int CONNECTION_TIMEOUT = 1000;
	protected static final int CONNECTION_TEST = 1000;
	protected static final int ACQUIRE_RETRY_ATTEMPTS = 5;
	protected static final int ACQUIRE_RETRY_DELAY = 1000;
	protected static final int UNRETURNED_CONNECTION_TIMEOUT = 1000;
	protected static final int CHECKOUT_TIMEOUT = 1000;
	protected static final int MAX_IDLE_TIME = 2000;

	/*
	 * Loading hibernate properties to enable caching
	 */
	protected static final String GENERATE_STATICS = "true";

	protected static final String HIBERNATE_DIALECT = "hibernate.dialect";
	protected static final String SHOW_SQL = "hibernate.show_sql";
	protected static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	protected static final String MIN_CNCTN_SIZE = "hibernate.c3p0.min_size";
	protected static final String MAX_CNCTN_SIZE = "hibernate.c3p0.max_size";
	protected static final String CNCTN_TIMEOUT = "hibernate.c3p0.timeout";
	protected static final String CNCTN_TEST = "hibernate.c3p0.idle_test_period";
	protected static final String HIBERNATE_GENERATE_STATICS = "hibernate.generate_statistics";

	protected static Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty(HIBERNATE_DIALECT, DIALECT);
		properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");
		properties.setProperty(SHOW_SQL, SHOWSQL);
		properties.setProperty("hibernate.format_sql", "true");
		properties.setProperty("hibernate.use_sql_comments", "true");
		properties.setProperty(HBM2DDL_AUTO, "validate");
		properties.setProperty(HIBERNATE_GENERATE_STATICS, GENERATE_STATICS);
		properties.setProperty("hibernate.cache.use_second_level_cache", "true");
		properties.setProperty("hibernate.cache.use_query_cache", "true");
		properties.setProperty("hibernate.cache.region.factory_class",
				"org.hibernate.cache.ehcache.EhCacheRegionFactory");
		return properties;
	}
}