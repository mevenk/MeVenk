/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author vkolisetty
 *
 */
public class ExcelSheet {

	private String name;
	private LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> data;
	private int[] freezePoint;

	/**
	 * @param name
	 * @param data
	 */
	public ExcelSheet(String name, LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> data) {
		this.name = name.trim();
		this.data = data;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the data
	 */
	public final LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> getData() {
		return data;
	}

	/**
	 * @return the freezePoint
	 */
	public final int[] getFreezePoint() {
		return freezePoint;
	}

	/**
	 * 
	 * @param colSplit
	 * @param rowSplit
	 * @return
	 */
	public ExcelSheet freezePane(int colSplit, int rowSplit) {
		return freezePane(colSplit, rowSplit, colSplit, rowSplit);
	}

	/**
	 * 
	 * @param colSplit
	 * @param rowSplit
	 * @param leftmostColumn
	 * @param topRow
	 * @return
	 */
	public ExcelSheet freezePane(int colSplit, int rowSplit, int leftmostColumn, int topRow) {
		freezePoint = new int[] { colSplit, rowSplit, leftmostColumn, topRow };
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelSheet [name=" + name + ", data=" + data + ", freezePoint=" + Arrays.toString(freezePoint) + "]";
	}

}
