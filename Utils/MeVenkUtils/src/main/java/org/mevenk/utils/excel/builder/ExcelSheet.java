/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author vkolisetty
 *
 */
public class ExcelSheet {

	private String name;
	private LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> data;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelSheet [name=" + name + ", data=" + data + "]";
	}

}
