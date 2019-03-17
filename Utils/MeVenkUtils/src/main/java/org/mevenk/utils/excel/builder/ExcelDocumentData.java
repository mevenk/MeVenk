/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * @author venky
 *
 */
public class ExcelDocumentData {

	private LinkedList<ExcelSheet> sheets;
	private LinkedHashSet<String> columnsAutoSize = new LinkedHashSet<String>();

	private String fileName;

	/**
	 * @param sheets
	 */
	public ExcelDocumentData(LinkedList<ExcelSheet> sheets) {
		this.sheets = sheets;
	}

	/**
	 * @param sheets
	 * @param columnsAutoSize
	 */
	public ExcelDocumentData(LinkedList<ExcelSheet> sheets, LinkedHashSet<String> columnsAutoSize) {
		this(sheets);
		this.columnsAutoSize.addAll(columnsAutoSize);
	}

	/**
	 * @return the sheets
	 */
	public final LinkedList<ExcelSheet> getSheets() {
		return sheets;
	}

	/**
	 * @return the columnsAutoSize
	 */
	public final LinkedHashSet<String> getColumnsAutoSize() {
		return columnsAutoSize;
	}

	/**
	 * @return the fileName
	 */
	public final String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelDocumentData [sheets=" + sheets + ", columnsAutoSize=" + columnsAutoSize + "]";
	}

}
