/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author vkolisetty
 *
 */
public class ExcelProperties {

	private String author;
	private String title;

	private final Map<String, CustomPropertyValue> customProperties = new LinkedHashMap<String, ExcelProperties.CustomPropertyValue>();

	/**
	 * @param author
	 */
	public ExcelProperties(String author) {
		this.author = author;
	}

	/**
	 * 
	 * @param title
	 * @return
	 */
	public ExcelProperties addTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * 
	 * @param properties
	 * @return
	 */
	public ExcelProperties addProperties(Properties properties) {
		for (String key : properties.stringPropertyNames()) {
			customProperties.put(key, new CustomPropertyValue(properties.getProperty(key)));
		}
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ExcelProperties addCustomProperty(String key, String value) {
		customProperties.put(key, new CustomPropertyValue(value));
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ExcelProperties addCustomProperty(String key, double value) {
		customProperties.put(key, new CustomPropertyValue(value));
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ExcelProperties addCustomProperty(String key, int value) {
		customProperties.put(key, new CustomPropertyValue(value));
		return this;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ExcelProperties addCustomProperty(String key, boolean value) {
		customProperties.put(key, new CustomPropertyValue(value));
		return this;
	}

	/**
	 * @return the author
	 */
	public final String getAuthor() {
		return author;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @return the customProperties
	 */
	public final Map<String, CustomPropertyValue> getCustomProperties() {
		return customProperties;
	}

	class CustomPropertyValue {

		private String stringValue;
		private Double doubleValue;
		private Integer intValue;
		private Boolean booleanValue;

		/**
		 * @param stringValue
		 */
		private CustomPropertyValue(String stringValue) {
			this.stringValue = stringValue;
		}

		/**
		 * @param doubleValue
		 */
		private CustomPropertyValue(Double doubleValue) {
			this.doubleValue = doubleValue;
		}

		/**
		 * @param intValue
		 */
		private CustomPropertyValue(Integer intValue) {
			this.intValue = intValue;
		}

		/**
		 * @param booleanValue
		 */
		private CustomPropertyValue(Boolean booleanValue) {
			this.booleanValue = booleanValue;
		}

		public final Object getValue() {
			if (stringValue != null) {
				return stringValue;
			} else if (doubleValue != null) {
				return doubleValue;
			} else if (intValue != null) {
				return intValue;
			} else {
				return booleanValue;
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return getValue().toString();
		}

	}

}
