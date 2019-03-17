/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.RichTextString;

/**
 * @author vkolisetty
 *
 */
public class ExcelCell {

	private String stringValue;
	private Double doubleValue;
	private Boolean booleanValue;
	private Calendar calendarValue;
	private Date dateValue;
	private RichTextString richTextStringValue;

	/**
	 * 
	 */
	public ExcelCell() {
	}

	/**
	 * @param stringValue
	 */
	public ExcelCell(String stringValue) {
		this.stringValue = stringValue;
	}

	/**
	 * @param doubleValue
	 */
	public ExcelCell(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	/**
	 * @param booleanValue
	 */
	public ExcelCell(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	/**
	 * @param calendarValue
	 */
	public ExcelCell(Calendar calendarValue) {
		this.calendarValue = calendarValue;
	}

	/**
	 * @param dateValue
	 */
	public ExcelCell(Date dateValue) {
		this.dateValue = dateValue;
	}

	/**
	 * @param richTextStringValue
	 */
	public ExcelCell(RichTextString richTextStringValue) {
		this.richTextStringValue = richTextStringValue;
	}

	/**
	 * @return the stringValue
	 */
	public final String getStringValue() {
		return stringValue;
	}

	/**
	 * @return the doubleValue
	 */
	public final Double getDoubleValue() {
		return doubleValue;
	}

	/**
	 * @return the booleanValue
	 */
	public final Boolean getBooleanValue() {
		return booleanValue;
	}

	/**
	 * @return the calendarValue
	 */
	public final Calendar getCalendarValue() {
		return calendarValue;
	}

	/**
	 * @return the dateValue
	 */
	public final Date getDateValue() {
		return dateValue;
	}

	/**
	 * @return the richTextStringValue
	 */
	public final RichTextString getRichTextStringValue() {
		return richTextStringValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelCell [stringValue=" + stringValue + ", doubleValue=" + doubleValue + ", booleanValue="
				+ booleanValue + ", calendarValue=" + calendarValue + ", dateValue=" + dateValue
				+ ", richTextStringValue=" + richTextStringValue + "]";
	}

}
