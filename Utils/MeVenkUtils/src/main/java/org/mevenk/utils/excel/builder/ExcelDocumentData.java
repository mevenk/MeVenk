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

	private ExcelProperties excelProperties;

	/**
	 * 
	 * @param author
	 * @param sheets
	 */
	public ExcelDocumentData(String author, LinkedList<ExcelSheet> sheets) {
		this.excelProperties = new ExcelProperties(author);
		this.sheets = sheets;
	}

	/**
	 * 
	 * @param author
	 * @param sheets
	 * @param columnsAutoSize
	 */
	public ExcelDocumentData(String author, LinkedList<ExcelSheet> sheets, LinkedHashSet<String> columnsAutoSize) {
		this(author, sheets);
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

	/**
	 * @return the excelProperties
	 */
	public final ExcelProperties getExcelProperties() {
		return excelProperties;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelDocumentData [sheets=" + sheets + ", columnsAutoSize=" + columnsAutoSize + ", fileName=" + fileName
				+ ", excelProperties=" + excelProperties + "]";
	}

}
