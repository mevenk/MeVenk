/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.Properties;

/**
 * @author vkolisetty
 *
 */
public class ExcelProperties {

	private String author;
	private String title;;

	private Properties properties;

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
		this.properties = properties;
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
	 * @return the properties
	 */
	public final Properties getProperties() {
		return properties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelProperties [author=" + author + ", title=" + title + ", properties=" + properties + "]";
	}

}
