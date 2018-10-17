/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.logPropertyDetail;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateIntegerProperty;

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

	}

}
