/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.util.LinkedList;

/**
 * @author venky
 *
 */
public class ExcelDocumentData {

	private LinkedList<ExcelSheet> sheets;
	private ExcelProperties excelProperties;
	private String fileName;

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
	 * @return the sheets
	 */
	public final LinkedList<ExcelSheet> getSheets() {
		return sheets;
	}

	/**
	 * @return the excelProperties
	 */
	public final ExcelProperties getExcelProperties() {
		return excelProperties;
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
		return "ExcelDocumentData [sheets=" + sheets + ", excelProperties=" + excelProperties + ", fileName=" + fileName
				+ "]";
	}

}
