/**
 *
 */
package com.mevenk.webapp.config.spring.properties;

import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateBooleanProperty;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.validateStringProperty;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author venky
 *
 */
public class HibernateProperties extends DatabaseProperties {

	private static final Class<HibernateProperties> CLASS_HIBERNATE_PROPERTIES = HibernateProperties.class;

	public static final String BEAN_HIBERNATE_PROPERTIES = "BEAN_HIBERNATE_PROPERTIES";

	private static final String PROPERTY_NAME_DIALECT = "database.hibernate.dialect";
	@Value("${" + PROPERTY_NAME_DIALECT + "}")
	private String propertyValueDialect;
	private static String dialect;

	private static final String PROPERTY_NAME_NON_CONTEXTUAL_CREATION = "database.hibernate.jdbc.lob.nonContextualCreation";
	@Value("${" + PROPERTY_NAME_NON_CONTEXTUAL_CREATION + "}")
	private String propertyValueNonContextualCreation;
	private static boolean nonContextualCreation;

	private static final String PROPERTY_NAME_SHOW_SQL = "database.hibernate.showSQL";
	@Value("${" + PROPERTY_NAME_SHOW_SQL + "}")
	private String propertyValueShowSQL;
	private static boolean showSQL;

	private static final String PROPERTY_NAME_FORMAT_SQL = "database.hibernate.formatSQL";
	@Value("${" + PROPERTY_NAME_FORMAT_SQL + "}")
	private String propertyValueFormatSQL;
	private static boolean formatSQL;

	private static final String PROPERTY_NAME_USE_SQL_COMMENTS = "database.hibernate.useSQLComments";
	@Value("${" + PROPERTY_NAME_USE_SQL_COMMENTS + "}")
	private String propertyValueUseSQLComments;
	private static boolean useSQLComments;

	private static final String PROPERTY_NAME_USE_QUERY_CACHE = "database.hibernate.cache.useQueryCache";
	@Value("${" + PROPERTY_NAME_USE_QUERY_CACHE + "}")
	private String propertyValueUseQueryCache;
	private static boolean useQueryCache;

	private static final String PROPERTY_NAME_USE_SECOND_LEVEL_CACHE = "database.hibernate.cache.useSecondLevelCache";
	@Value("${" + PROPERTY_NAME_USE_SECOND_LEVEL_CACHE + "}")
	private String propertyValueUseSecondLevelCache;
	private static boolean useSecondLevelCache;

	private static final String PROPERTY_NAME_CACHE_REGION_FACTORY_CLASS = "database.hibernate.cache.region.factory.class";
	@Value("${" + PROPERTY_NAME_CACHE_REGION_FACTORY_CLASS + "}")
	private String propertyValueCacheRegionFactoryClass;
	private static String cacheRegionFactoryClass;

	private static final String PROPERTY_NAME_HBM2DDL_AUTO = "database.hibernate.hbm2ddl.auto";
	@Value("${" + PROPERTY_NAME_HBM2DDL_AUTO + "}")
	private String propertyValueHbm2ddlAuto;
	private static String hbm2ddlAuto;

	private static final String PROPERTY_NAME_GENERATE_STATISTICS = "database.hibernate.statistics.generate";
	@Value("${" + PROPERTY_NAME_GENERATE_STATISTICS + "}")
	private String propertyValueGenerateStatistics;
	private static boolean generateStatistics;

	@PostConstruct
	private void loadHibernateProperties() {

		loadBasicProperties(propertyValueDialect, propertyValueNonContextualCreation);
		loadSQLProperties(propertyValueShowSQL, propertyValueFormatSQL, propertyValueUseSQLComments);
		loadCacheProperties(propertyValueUseQueryCache, propertyValueUseSecondLevelCache,
				propertyValueCacheRegionFactoryClass);
		loadMiscellaneousProperties(propertyValueHbm2ddlAuto, propertyValueGenerateStatistics);

	}

	/**
	 *
	 * @param propertyValueDialect
	 * @param propertyValueNonContextualCreation
	 */
	private static void loadBasicProperties(String propertyValueDialect, String propertyValueNonContextualCreation) {

		dialect = validateStringProperty(PROPERTY_NAME_DIALECT, propertyValueDialect);
		logHibernateProperty(PROPERTY_NAME_DIALECT, dialect);

		nonContextualCreation = validateBooleanProperty(PROPERTY_NAME_NON_CONTEXTUAL_CREATION,
				propertyValueNonContextualCreation);
		logHibernateProperty(PROPERTY_NAME_NON_CONTEXTUAL_CREATION, nonContextualCreation);

	}

	/**
	 *
	 * @param propertyValueShowSQL
	 * @param propertyValueFormatSQL
	 * @param propertyValueUseSQLComments
	 */
	private static void loadSQLProperties(String propertyValueShowSQL, String propertyValueFormatSQL,
			String propertyValueUseSQLComments) {

		showSQL = validateBooleanProperty(PROPERTY_NAME_SHOW_SQL, propertyValueShowSQL);
		logHibernateProperty(PROPERTY_NAME_NON_CONTEXTUAL_CREATION, showSQL);

		formatSQL = validateBooleanProperty(PROPERTY_NAME_FORMAT_SQL, propertyValueFormatSQL);
		logHibernateProperty(PROPERTY_NAME_FORMAT_SQL, formatSQL);

		useSQLComments = validateBooleanProperty(PROPERTY_NAME_USE_SQL_COMMENTS, propertyValueUseSQLComments);
		logHibernateProperty(PROPERTY_NAME_USE_SQL_COMMENTS, useSQLComments);

	}

	/**
	 *
	 * @param propertyValueUseQueryCache
	 * @param propertyValueUseSecondLevelCache
	 * @param propertyValueCacheRegionFactoryClass
	 */
	private static void loadCacheProperties(String propertyValueUseQueryCache, String propertyValueUseSecondLevelCache,
			String propertyValueCacheRegionFactoryClass) {

		useQueryCache = validateBooleanProperty(PROPERTY_NAME_USE_QUERY_CACHE, propertyValueUseQueryCache);
		logHibernateProperty(PROPERTY_NAME_USE_QUERY_CACHE, useQueryCache);

		useSecondLevelCache = validateBooleanProperty(PROPERTY_NAME_USE_SECOND_LEVEL_CACHE,
				propertyValueUseSecondLevelCache);
		logHibernateProperty(PROPERTY_NAME_USE_SECOND_LEVEL_CACHE, useSecondLevelCache);

		cacheRegionFactoryClass = validateStringProperty(PROPERTY_NAME_CACHE_REGION_FACTORY_CLASS,
				propertyValueCacheRegionFactoryClass);
		logHibernateProperty(PROPERTY_NAME_CACHE_REGION_FACTORY_CLASS, cacheRegionFactoryClass);

	}

	/**
	 *
	 * @param propertyValueHbm2ddlAuto
	 * @param propertyValueGenerateStatistics
	 */
	private static void loadMiscellaneousProperties(String propertyValueHbm2ddlAuto,
			String propertyValueGenerateStatistics) {

		hbm2ddlAuto = validateStringProperty(PROPERTY_NAME_HBM2DDL_AUTO, propertyValueHbm2ddlAuto);
		logHibernateProperty(PROPERTY_NAME_HBM2DDL_AUTO, hbm2ddlAuto);

		generateStatistics = validateBooleanProperty(PROPERTY_NAME_GENERATE_STATISTICS,
				propertyValueGenerateStatistics);
		logHibernateProperty(PROPERTY_NAME_GENERATE_STATISTICS, generateStatistics);

	}

	/**
	 * @return the dialect
	 */
	public static final String getDialect() {
		return dialect;
	}

	/**
	 * @return the nonContextualCreation
	 */
	public static final boolean isNonContextualCreation() {
		return nonContextualCreation;
	}

	/**
	 * @return the showSQL
	 */
	public static final boolean isShowSQL() {
		return showSQL;
	}

	/**
	 * @return the formatSQL
	 */
	public static final boolean isFormatSQL() {
		return formatSQL;
	}

	/**
	 * @return the useSQLComments
	 */
	public static final boolean isUseSQLComments() {
		return useSQLComments;
	}

	/**
	 * @return the useQueryCache
	 */
	public static final boolean isUseQueryCache() {
		return useQueryCache;
	}

	/**
	 * @return the useSecondLevelCache
	 */
	public static final boolean isUseSecondLevelCache() {
		return useSecondLevelCache;
	}

	/**
	 * @return the cacheRegionFactoryClass
	 */
	public static final String getCacheRegionFactoryClass() {
		return cacheRegionFactoryClass;
	}

	/**
	 * @return the hbm2ddlAuto
	 */
	public static final String getHbm2ddlAuto() {
		return hbm2ddlAuto;
	}

	/**
	 * @return the generateStatistics
	 */
	public static final boolean isGenerateStatistics() {
		return generateStatistics;
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 */
	private static void logHibernateProperty(String propertyName, Object propertyValue) {
		logDatabaseProperty(CLASS_HIBERNATE_PROPERTIES, propertyName, propertyValue);
	}

}
