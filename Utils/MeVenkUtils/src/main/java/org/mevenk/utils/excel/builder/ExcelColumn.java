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
public class ExcelColumn extends ExcelCell {

	private boolean leftBorderRequired;
	private boolean rightBorderRequired;
	private boolean autoSizeRequired;

	/**
	 * @param booleanValue
	 */
	public ExcelColumn(boolean booleanValue) {
		super(booleanValue);
	}

	/**
	 * @param calendarValue
	 */
	public ExcelColumn(Calendar calendarValue) {
		super(calendarValue);
	}

	/**
	 * @param dateValue
	 */
	public ExcelColumn(Date dateValue) {
		super(dateValue);
	}

	/**
	 * @param doubleValue
	 */
	public ExcelColumn(double doubleValue) {
		super(doubleValue);
	}

	/**
	 * @param richTextStringValue
	 */
	public ExcelColumn(RichTextString richTextStringValue) {
		super(richTextStringValue);
	}

	/**
	 * @param stringValue
	 */
	public ExcelColumn(String stringValue) {
		super(stringValue);
	}

	/**
	 * @return the leftBorderRequired
	 */
	public final boolean isLeftBorderRequired() {
		return leftBorderRequired;
	}

	/**
	 * @return the rightBorderRequired
	 */
	public final boolean isRightBorderRequired() {
		return rightBorderRequired;
	}

	/**
	 * @return the autoSizeRequired
	 */
	public final boolean isAutoSizeRequired() {
		return autoSizeRequired;
	}

	/**
	 * 
	 * @return
	 */
	public final ExcelColumn autoSize() {
		autoSizeRequired = true;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public final ExcelColumn addLeftBorder() {
		leftBorderRequired = true;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public final ExcelColumn addRightBorder() {
		rightBorderRequired = true;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelColumn [leftBorderRequired=" + leftBorderRequired + ", rightBorderRequired=" + rightBorderRequired
				+ ", autoSizeRequired=" + autoSizeRequired + "]";
	}

}
