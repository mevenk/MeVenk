/**
 * 
 */
package org.mevenk.utils.excel.builder;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.POIXMLProperties.CoreProperties;
import org.apache.poi.ooxml.POIXMLProperties.CustomProperties;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mevenk.utils.excel.builder.ExcelProperties.CustomPropertyValue;

/**
 * @author venky
 *
 */
public class ExcelDocumentBuilder {

	public static final String EXCEL_FILE_NAME_SUFFIX = ".xslx";

	private static final String DATE_FORMAT_DATE = "DD/MM/YY HH:MM";
	private static final String DATE_FORMAT_CALENDAR = "DD/MM/YY";

	private static final short BLACK = HSSFColor.HSSFColorPredefined.BLACK.getIndex();
	private static final short LIGHT_BLUE = HSSFColor.HSSFColorPredefined.PALE_BLUE.getIndex();
	private static final short LIGHT_ORANGE = HSSFColor.HSSFColorPredefined.LIGHT_ORANGE.getIndex();

	private XSSFWorkbook workbook;

	private LinkedList<ExcelSheet> sheets;

	/**
	 * 
	 * @param excelDocumentData
	 */
	private ExcelDocumentBuilder(ExcelDocumentData excelDocumentData) throws Exception {

		this.sheets = excelDocumentData.getSheets();

		this.workbook = new XSSFWorkbook();

		setProperties(workbook, excelDocumentData.getExcelProperties());
		build();
	}

	private final void build() throws Exception {

		XSSFSheet currentSheet = null;
		LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> sheetData = null;

		XSSFCellStyle headerStyle = createHeaderStyle(workbook);
		XSSFCellStyle dataStyle = createDataStyle(workbook);

		for (ExcelSheet sheet : sheets) {

			currentSheet = workbook.createSheet(sheet.getName());

			sheetData = sheet.getData();

			XSSFRow rowColumnHeader = null;

			int rowVlauesSizeMax = rowVlauesSizeMax(sheetData.values());
			int columnIndexMax = sheetData.size() - 1;

			int columnIndex = -1;
			for (Map.Entry<ExcelColumn, LinkedList<ExcelCell>> entry : sheetData.entrySet()) {

				columnIndex++;

				int rowIndex = -1;

				rowColumnHeader = currentSheet.getRow(++rowIndex);
				if (rowColumnHeader == null) {
					rowColumnHeader = currentSheet.createRow(rowIndex);
				}

				ExcelColumn excelCellColumnHeader = entry.getKey();

				XSSFCell cellHeader = rowColumnHeader.createCell(columnIndex);
				setCellValue(cellHeader, excelCellColumnHeader);

				boolean leftBorderRequired = excelCellColumnHeader.isLeftBorderRequired();
				boolean rightBorderRequired = excelCellColumnHeader.isRightBorderRequired();

				setCellStyle(headerStyle, cellHeader, leftBorderRequired, rightBorderRequired);

				currentSheet.autoSizeColumn(columnIndex);

				XSSFRow rowData = null;
				LinkedList<ExcelCell> columnValues = entry.getValue();
				int columnRowValuesIndexMax = columnValues.size() - 1;
				XSSFCell cellData = null;
				ExcelCell excelCellData = null;

				XSSFCellStyle dataStyleTemp = null;

				int rowIndexValuesMax = rowVlauesSizeMax - 1;

				for (int rowIndexValues = 0; rowIndexValues < rowVlauesSizeMax; rowIndexValues++) {

					excelCellData = null;
					if (rowIndexValues <= columnRowValuesIndexMax) {
						excelCellData = columnValues.get(rowIndexValues);
					}

					rowData = currentSheet.getRow(++rowIndex);
					if (rowData == null) {
						rowData = currentSheet.createRow(rowIndex);
					}

					cellData = rowData.createCell(columnIndex);
					setCellValue(cellData, excelCellData);
					cellData.setCellStyle(dataStyle);

					if (columnIndex == 0) {
						dataStyleTemp = workbook.createCellStyle();
						dataStyleTemp.cloneStyleFrom(dataStyle);
						dataStyleTemp.setBorderLeft(BorderStyle.THICK);
						cellData.setCellStyle(dataStyleTemp);
					}

					if (columnIndex == columnIndexMax) {
						dataStyleTemp = workbook.createCellStyle();
						dataStyleTemp.cloneStyleFrom(dataStyle);
						dataStyleTemp.setBorderRight(BorderStyle.THICK);
						cellData.setCellStyle(dataStyleTemp);
					}

					if (rowIndexValues == rowIndexValuesMax) {
						dataStyleTemp = workbook.createCellStyle();
						dataStyleTemp.cloneStyleFrom(dataStyle);
						dataStyleTemp.setBorderBottom(BorderStyle.THICK);
						if (columnIndex == 0) {
							dataStyleTemp.setBorderLeft(BorderStyle.THICK);
						}
						if (columnIndex == columnIndexMax) {
							dataStyleTemp.setBorderRight(BorderStyle.THICK);
						}
						cellData.setCellStyle(dataStyleTemp);
					}

					setCellStyle(cellData.getCellStyle(), cellData, leftBorderRequired, rightBorderRequired);

				}
				
				if (excelCellColumnHeader.isAutoSizeRequired()) {
					currentSheet.autoSizeColumn(columnIndex);
				}
				
			}

			int[] freezePoint = sheet.getFreezePoint();
			if (freezePoint != null) {
				currentSheet.createFreezePane(freezePoint[0], freezePoint[1], freezePoint[2], freezePoint[3]);
			}else {
				// Freeze 1st row (header)
				currentSheet.createFreezePane(0,1);
			}

		}

	}

	/**
	 * 
	 * @param cellStyleDefault
	 * @param cell
	 * @param leftBorderRequired
	 * @param rightBorderRequired
	 */
	private void setCellStyle(XSSFCellStyle cellStyleDefault, XSSFCell cell, boolean leftBorderRequired,
			boolean rightBorderRequired) {

		if (!leftBorderRequired && !rightBorderRequired) {
			cell.setCellStyle(cellStyleDefault);
			return;
		}

		XSSFCellStyle headerStyleTemp;
		headerStyleTemp = workbook.createCellStyle();
		headerStyleTemp.cloneStyleFrom(cellStyleDefault);

		if (leftBorderRequired) {
			headerStyleTemp.setBorderLeft(BorderStyle.THICK);
		}

		if (rightBorderRequired) {
			headerStyleTemp.setBorderRight(BorderStyle.THICK);
		}

		cell.setCellStyle(headerStyleTemp);

	}

	/**
	 * 
	 * @param values
	 * @return
	 */
	private static final int rowVlauesSizeMax(Collection<LinkedList<ExcelCell>> values) {
		int rowVlauesSizeMax = -1;
		for (LinkedList<ExcelCell> value : values) {
			if (rowVlauesSizeMax < value.size()) {
				rowVlauesSizeMax = value.size();
			}
		}
		return rowVlauesSizeMax;
	}

	/**
	 * 
	 * @param workbookSetProperties
	 * @param excelProperties
	 */
	private static final void setProperties(XSSFWorkbook workbookSetProperties, ExcelProperties excelProperties) {
		POIXMLProperties poiXmlProperties = workbookSetProperties.getProperties();
		CoreProperties coreProperties = poiXmlProperties.getCoreProperties();

		coreProperties.setCreator(excelProperties.getAuthor());
		if (excelProperties.getTitle() != null) {
			coreProperties.setTitle(excelProperties.getTitle());
		}

		Map<String, CustomPropertyValue> customPropertiesFromExcelProperties = excelProperties.getCustomProperties();
		if (!customPropertiesFromExcelProperties.isEmpty()) {
			CustomProperties customProperties = poiXmlProperties.getCustomProperties();

			String key;
			Object value;
			for (Map.Entry<String, CustomPropertyValue> property : customPropertiesFromExcelProperties.entrySet()) {
				key = property.getKey();
				value = property.getValue().getValue();
				if (value instanceof String) {
					customProperties.addProperty(key, (String) value);
				} else if (value instanceof Double) {
					customProperties.addProperty(key, (double) value);
				} else if (value instanceof Integer) {
					customProperties.addProperty(key, (int) value);
				} else if (value instanceof Boolean) {
					customProperties.addProperty(key, (boolean) value);
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

		if (excelCell == null) {
			return;
		}

		XSSFSheet sheet = cell.getSheet();
		XSSFWorkbook workbookFromCell = sheet.getWorkbook();
		XSSFCreationHelper creationHelper = workbookFromCell.getCreationHelper();

		String stringValue = null;
		Double doubleValue = null;
		Boolean booleanValue = null;
		Calendar calendarValue = null;
		Date dateValue = null;
		RichTextString richTextStringValue = null;

		XSSFCellStyle dataStyleTemp = null;

		if ((stringValue = excelCell.getStringValue()) != null) {
			cell.setCellValue(stringValue);
		} else if ((doubleValue = excelCell.getDoubleValue()) != null) {
			cell.setCellValue(doubleValue);
		} else if ((booleanValue = excelCell.getBooleanValue()) != null) {
			cell.setCellValue(booleanValue);
		} else if ((calendarValue = excelCell.getCalendarValue()) != null) {
			dataStyleTemp = workbookFromCell.createCellStyle();
			dataStyleTemp.cloneStyleFrom(cell.getCellStyle());
			dataStyleTemp.setDataFormat(creationHelper.createDataFormat().getFormat(DATE_FORMAT_CALENDAR));
			cell.setCellStyle(dataStyleTemp);
			cell.setCellValue(calendarValue);
		} else if ((dateValue = excelCell.getDateValue()) != null) {
			dataStyleTemp = workbookFromCell.createCellStyle();
			dataStyleTemp.cloneStyleFrom(cell.getCellStyle());
			dataStyleTemp.setDataFormat(creationHelper.createDataFormat().getFormat(DATE_FORMAT_DATE));
			cell.setCellStyle(dataStyleTemp);
			cell.setCellValue(dateValue);
		} else if ((richTextStringValue = excelCell.getRichTextStringValue()) != null) {
			cell.setCellValue(richTextStringValue);
		}

		ExcelComment excelComment = excelCell.getExcelComment();
		if (excelComment == null) {
			return;
		}

		XSSFClientAnchor clientAnchor = creationHelper.createClientAnchor();

		CellAddress cellAddress = cell.getAddress();
		int columnIndex = cellAddress.getColumn();
		int rowIndex = cellAddress.getRow();
		clientAnchor.setCol1(columnIndex + 1);
		clientAnchor.setCol2(columnIndex + 4);
		clientAnchor.setRow1(rowIndex + 1);
		clientAnchor.setRow2(rowIndex + 3);

		XSSFComment cellComment = sheet.createDrawingPatriarch().createCellComment(clientAnchor);

		cellComment.setString(excelComment.getComment());
		cellComment.setAuthor(excelComment.getAuthor());
		cellComment.setVisible(excelComment.isVisible());

		cell.setCellComment(cellComment);

	}

	/**
	 * 
	 * @param workbook
	 * @return
	 */
	private static final XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {

		XSSFFont headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeight(13);
		headerFont.setFontName("Calibri");

		XSSFCellStyle headerStyle = workbook.createCellStyle();

		headerStyle.setFont(headerFont);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);

		headerStyle.setFillForegroundColor(LIGHT_ORANGE);
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		headerStyle.setBorderLeft(BorderStyle.THICK);
		headerStyle.setBorderRight(BorderStyle.THICK);
		headerStyle.setBorderTop(BorderStyle.THICK);
		headerStyle.setBorderBottom(BorderStyle.THICK);

		headerStyle.setLeftBorderColor(BLACK);
		headerStyle.setRightBorderColor(BLACK);
		headerStyle.setTopBorderColor(BLACK);
		headerStyle.setBottomBorderColor(BLACK);

		return headerStyle;
	}

	/**
	 * 
	 * @param workbook
	 * @return
	 */
	private static final XSSFCellStyle createDataStyle(XSSFWorkbook workbook) {

		XSSFFont dataFont = workbook.createFont();
		dataFont.setBold(false);
		dataFont.setFontHeight(11);
		dataFont.setFontName("Calibri");

		XSSFCellStyle dataStyle = workbook.createCellStyle();

		dataStyle.setFont(dataFont);
		dataStyle.setAlignment(HorizontalAlignment.LEFT);
		dataStyle.setWrapText(true);

		dataStyle.setFillForegroundColor(LIGHT_BLUE);
		dataStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		dataStyle.setBorderLeft(BorderStyle.THIN);
		dataStyle.setBorderRight(BorderStyle.THIN);
		dataStyle.setBorderTop(BorderStyle.THIN);
		dataStyle.setBorderBottom(BorderStyle.THIN);

		dataStyle.setLeftBorderColor(BLACK);
		dataStyle.setRightBorderColor(BLACK);
		dataStyle.setTopBorderColor(BLACK);
		dataStyle.setBottomBorderColor(BLACK);

		return dataStyle;
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
