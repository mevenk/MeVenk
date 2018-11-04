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
public class BaseProperties {

	private static final Class<BaseProperties> CLASS_BASE_PROPERTIES = BaseProperties.class;

	public static final String BEAN_BASE_PROPERTIES = "BEAN_BASE_PROPERTIES";

	private static final String PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN = "correlationIdDateFormatPattern";
	@Value("${" + PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN + "}")
	private String propertyValueCorrelationIdDateFormatPattern;
	private static String correlationIdDateFormatPattern;
	private static int correlationIdDateFormatPatternLength;

	private static final String PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN = "timelyLoggerDateFormatPattern";
	@Value("${" + PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN + "}")
	private String propertyValueTimelyLoggerDateFormatPattern;
	private static String timelyLoggerDateFormatPattern;

	private static final String PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN = "sourceBeanDateFormatPattern";
	@Value("${" + PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN + "}")
	private String propertyValueSourceBeanDateFormatPattern;
	private static String sourceBeanDateFormatPattern;

	private static final String PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN = "miscDateFormatPattern";
	@Value("${" + PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN + "}")
	private String propertyValueMiscDateFormatPattern;
	private static String miscDateFormatPattern;

	private static final String PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION = "datePollingCronExpression";
	@Value("${" + PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION + "}")
	private String propertyValueDatePollingCronExpression;
	private static String datePollingCronExpression;

	@PostConstruct
	private void loadBaseProperties() {

		loadDateFormatPatterns(propertyValueCorrelationIdDateFormatPattern, propertyValueTimelyLoggerDateFormatPattern,
				propertyValueSourceBeanDateFormatPattern, propertyValueMiscDateFormatPattern);

		loadCronExpressions(propertyValueDatePollingCronExpression);

	}

	/**
	 *
	 * @param propertyValueCorrelationIdDateFormatPattern
	 * @param propertyValueTimelyLoggerDateFormatPattern
	 * @param propertyValueSourceBeanDateFormatPattern
	 * @param propertyValueMiscDateFormatPattern
	 */
	private static void loadDateFormatPatterns(String propertyValueCorrelationIdDateFormatPattern,
			String propertyValueTimelyLoggerDateFormatPattern, String propertyValueSourceBeanDateFormatPattern,
			String propertyValueMiscDateFormatPattern) {

		correlationIdDateFormatPattern = validateStringProperty(PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN,
				propertyValueCorrelationIdDateFormatPattern);
		logBaseProperty(PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN, correlationIdDateFormatPattern);
		correlationIdDateFormatPatternLength = correlationIdDateFormatPattern.length();

		timelyLoggerDateFormatPattern = validateStringProperty(PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN,
				propertyValueTimelyLoggerDateFormatPattern);
		logBaseProperty(PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN, timelyLoggerDateFormatPattern);

		sourceBeanDateFormatPattern = validateStringProperty(PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN,
				propertyValueSourceBeanDateFormatPattern);
		logBaseProperty(PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN, sourceBeanDateFormatPattern);

		miscDateFormatPattern = validateStringProperty(PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN,
				propertyValueMiscDateFormatPattern);
		logBaseProperty(PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN, miscDateFormatPattern);

	}

	/**
	 *
	 * @param propertyValueDatePollingCronExpression
	 */
	private static void loadCronExpressions(String propertyValueDatePollingCronExpression) {

		datePollingCronExpression = validateStringProperty(PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION,
				propertyValueDatePollingCronExpression);
		logBaseProperty(PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION, datePollingCronExpression);

	}

	/**
	 * @return the correlationIdDateFormatPattern
	 */
	public static final String getCorrelationIdDateFormatPattern() {
		return correlationIdDateFormatPattern;
	}

	/**
	 *
	 * @return
	 */
	public static final int getCorrelationIdDateFormatPatternLength() {
		return correlationIdDateFormatPatternLength;
	}

	/**
	 * @return the timelyLoggerDateFormatPattern
	 */
	public static final String getTimelyLoggerDateFormatPattern() {
		return timelyLoggerDateFormatPattern;
	}

	/**
	 * @return the sourceBeanDateFormatPattern
	 */
	public static final String getSourceBeanDateFormatPattern() {
		return sourceBeanDateFormatPattern;
	}

	/**
	 * @return the miscDateFormatPattern
	 */
	public static final String getMiscDateFormatPattern() {
		return miscDateFormatPattern;
	}

	/**
	 * @return the datePollingCronExpression
	 */
	public static final String getDatePollingCronExpression() {
		return datePollingCronExpression;
	}

	/**
	 *
	 * @param propertyName
	 * @param propertyValue
	 */
	private static void logBaseProperty(String propertyName, String propertyValue) {
		logPropertyDetail(CLASS_BASE_PROPERTIES, propertyName, propertyValue);
	}

}
