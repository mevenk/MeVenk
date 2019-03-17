/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author venky
 *
 */
public class ExcelDocumentBuilder {

	public static final String EXCEL_FILE_NAME_SUFFIX = ".xslx";

	private XSSFWorkbook workbook;

	private LinkedList<ExcelSheet> sheets;
	private LinkedHashSet<String> columnsAutoSize;

	/**
	 * 
	 * @param excelDocumentData
	 */
	private ExcelDocumentBuilder(ExcelDocumentData excelDocumentData) throws Exception {

		this.sheets = excelDocumentData.getSheets();
		this.columnsAutoSize = excelDocumentData.getColumnsAutoSize();

		this.workbook = new XSSFWorkbook();
		build();
	}

	private final void build() throws Exception {

		XSSFSheet currentSheet = null;
		LinkedHashMap<ExcelCell, LinkedList<ExcelCell>> sheetData = null;

		for (ExcelSheet sheet : sheets) {

			currentSheet = workbook.createSheet(sheet.getName());

			sheetData = sheet.getData();

			XSSFRow rowColumnHeader = null;

			int columnIndex = -1;
			for (Map.Entry<ExcelCell, LinkedList<ExcelCell>> entry : sheetData.entrySet()) {

				columnIndex++;

				int rowIndex = -1;

				rowColumnHeader = currentSheet.getRow(++rowIndex);
				if (rowColumnHeader == null) {
					rowColumnHeader = currentSheet.createRow(rowIndex);
				}

				ExcelCell excelCellColumnHeader = entry.getKey();

				XSSFCell cellHeader = rowColumnHeader.createCell(columnIndex);
				setCellValue(cellHeader, excelCellColumnHeader);

				currentSheet.autoSizeColumn(columnIndex);

				XSSFRow rowData = null;
				LinkedList<ExcelCell> columnValues = entry.getValue();
				XSSFCell cellData = null;
				for (ExcelCell excelCellData : columnValues) {

					rowData = currentSheet.getRow(++rowIndex);
					if (rowData == null) {
						rowData = currentSheet.createRow(rowIndex);
					}

					cellData = rowData.createCell(columnIndex);
					setCellValue(cellData, excelCellData);

					if (columnsAutoSize.contains(cellHeader.getStringCellValue())) {
						currentSheet.autoSizeColumn(columnIndex);
					}

				}

			}

		}

	}

	/**
	 * 
	 * @param cell
	 * @param excelCell
	 */
	private static final void setCellValue(XSSFCell cell, ExcelCell excelCell) {

		String stringValue = excelCell.getStringValue();
		if (stringValue != null) {
			cell.setCellValue(stringValue);
			return;
		}
		Double doubleValue = excelCell.getDoubleValue();
		if (doubleValue != null) {
			cell.setCellValue(doubleValue);
			return;
		}

		Boolean booleanValue = excelCell.getBooleanValue();
		if (booleanValue != null) {
			cell.setCellValue(booleanValue);
			return;
		}

		Calendar calendarValue = excelCell.getCalendarValue();
		if (calendarValue != null) {
			cell.setCellValue(calendarValue);
			return;
		}

		Date dateValue = excelCell.getDateValue();
		if (dateValue != null) {
			cell.setCellValue(dateValue);
			return;
		}

		RichTextString richTextStringValue = excelCell.getRichTextStringValue();
		if (richTextStringValue != null) {
			cell.setCellValue(richTextStringValue);
			return;
		}

	}

	/**
	 * 
	 * @param excelDocumentData
	 * @return
	 * @throws Exception
	 */
	private static final XSSFWorkbook generateWorkbook(ExcelDocumentData excelDocumentData) throws Exception {
		return new ExcelDocumentBuilder(excelDocumentData).workbook;
	}

	/**
	 * 
	 * @param excelDocumentData
	 * @return
	 * @throws Exception
	 */
	public static final XSSFWorkbook build(ExcelDocumentData excelDocumentData) throws Exception {
		return generateWorkbook(excelDocumentData);
	}

	/**
	 * 
	 * @param excelDocumentData
	 * @param outputStream
	 * @throws Exception
	 */
	public static final void build(ExcelDocumentData excelDocumentData, OutputStream outputStream) throws Exception {
		build(excelDocumentData).write(outputStream);
	}

}
