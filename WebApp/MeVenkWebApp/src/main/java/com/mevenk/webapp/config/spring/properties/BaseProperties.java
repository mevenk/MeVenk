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
	public static String correlationIdDateFormatPattern;

	private static final String PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN = "timelyLoggerDateFormatPattern";
	@Value("${" + PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN + "}")
	private String propertyValueTimelyLoggerDateFormatPattern;
	public static String timelyLoggerDateFormatPattern;

	private static final String PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN = "sourceBeanDateFormatPattern";
	@Value("${" + PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN + "}")
	private String propertyValueSourceBeanDateFormatPattern;
	public static String sourceBeanDateFormatPattern;

	private static final String PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN = "miscDateFormatPattern";
	@Value("${" + PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN + "}")
	private String propertyValueMiscDateFormatPattern;
	public static String miscDateFormatPattern;

	private static final String PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION = "datePollingCronExpression";
	@Value("${" + PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION + "}")
	private String propertyValueDatePollingCronExpression;
	public static String datePollingCronExpression;

	@PostConstruct
	private void loadBaseProperties() {

		loadBasicProperties();

	}

	private void loadBasicProperties() {

		correlationIdDateFormatPattern = validateStringProperty(PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN,
				propertyValueCorrelationIdDateFormatPattern);
		logPropertyDetail(CLASS_BASE_PROPERTIES, PROPERTY_NAME_CORRELATION_ID_DATE_FORMAT_PATTERN,
				correlationIdDateFormatPattern);

		timelyLoggerDateFormatPattern = validateStringProperty(PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN,
				propertyValueTimelyLoggerDateFormatPattern);
		logPropertyDetail(CLASS_BASE_PROPERTIES, PROPERTY_NAME_TIMELY_LOGGER_DATE_FORMAT_PATTERN,
				timelyLoggerDateFormatPattern);

		sourceBeanDateFormatPattern = validateStringProperty(PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN,
				propertyValueSourceBeanDateFormatPattern);
		logPropertyDetail(CLASS_BASE_PROPERTIES, PROPERTY_NAME_SOURCE_BEAN_DATE_FORMAT_PATTERN,
				sourceBeanDateFormatPattern);

		miscDateFormatPattern = validateStringProperty(PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN,
				propertyValueMiscDateFormatPattern);
		logPropertyDetail(CLASS_BASE_PROPERTIES, PROPERTY_NAME_MISC_DATE_FORMAT_PATTERN, miscDateFormatPattern);

		datePollingCronExpression = validateStringProperty(PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION,
				propertyValueDatePollingCronExpression);
		logPropertyDetail(CLASS_BASE_PROPERTIES, PROPERTY_NAME_DATE_POLLING_CRON_EXPRESSION, datePollingCronExpression);

	}

}
