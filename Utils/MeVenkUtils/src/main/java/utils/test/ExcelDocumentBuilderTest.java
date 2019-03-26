/**
 * 
 */
package utils.test;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.mevenk.utils.excel.builder.ExcelCell;
import org.mevenk.utils.excel.builder.ExcelColumn;
import org.mevenk.utils.excel.builder.ExcelComment;
import org.mevenk.utils.excel.builder.ExcelDocumentBuilder;
import org.mevenk.utils.excel.builder.ExcelDocumentData;
import org.mevenk.utils.excel.builder.ExcelProperties;
import org.mevenk.utils.excel.builder.ExcelSheet;

/**
 * @author vkolisetty
 *
 */
public class ExcelDocumentBuilderTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LinkedList<ExcelSheet> sheets = new LinkedList<ExcelSheet>();

		for (int i = 1; i <= 10; i++) {
			String sheetName = "Sheet - " + i;
			LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>> data = new LinkedHashMap<ExcelColumn, LinkedList<ExcelCell>>();

			for (int j = 1; j <= 25; j++) {
				
				ExcelColumn column = new ExcelColumn("Column " + i + " - " + j);
				if (j % 2 == 0) {
					column.addLeftBorder();
				}
				if (j % 5 == 0) {
					column.addRightBorder();
				}
				if (j % 9 == 0) {
					column.autoSize();
				}
				LinkedList<ExcelCell> dataRows = new LinkedList<ExcelCell>();
				for (int k = 1; k <= 132; k++) {
					if(j % 2 == 0) {
						continue;
					}
					if(k % 3 == 0) {
						dataRows.add(null);
						continue;
					}
					if(k % 5 == 0) {
						dataRows.add(new ExcelCell(System.currentTimeMillis()).addExcelComment(new ExcelComment("double - " + i + " - " + j + " - " + k, "VK", k % 5 == 0)));
						continue;
					}
					if(k % 11 == 0) {
						dataRows.add(new ExcelCell().addExcelComment(new ExcelComment("Empty - " + i + " - " + j + " - " + k, "VK", k % 5 == 0)));
						continue;
					}
					if(k % 13 == 0) {
						dataRows.add(new ExcelCell(k % 18 == 0).addExcelComment(new ExcelComment("boolean - " + i + " - " + j + " - " + k, "VK", k % 5 == 0)));
						continue;
					}
					if(k % 17 == 0) {
						dataRows.add(new ExcelCell(Calendar.getInstance()).addExcelComment(new ExcelComment("Calendar - " + i + " - " + j + " - " + k, "VK", k % 5 == 0)));
						continue;
					}
					if(k % 19 == 0) {
						dataRows.add(new ExcelCell(new Date()).addExcelComment(new ExcelComment("Date - " + i + " - " + j + " - " + k, "VK", k % 5 == 0)));
						continue;
					}
					dataRows.add(new ExcelCell(i + " - " + j + " - " + k + " - " + System.currentTimeMillis() + " - " + System.currentTimeMillis())
							.addExcelComment(new ExcelComment(i + " - " + j + " - " + k, "VK", k % 5 == 0)));
					// dataRows.add(new ExcelCell(i + j + k));
					//dataRows.add(new ExcelCell());
				}
				data.put(column, dataRows);
			}

			ExcelSheet sheet = new ExcelSheet(sheetName, data);
			if(i % 2 == 0) {
				sheet.freezePane(2, 5, 7, 9);
			}
			if(i % 3 == 0) {
				sheet.freezePane(2, 5);
			}
			sheets.add(sheet);
		}

		FileOutputStream fileOutputStream = new FileOutputStream("/home/vkolisetty/RABOTA/Temporary/ExcelBuilder/"
				+ new SimpleDateFormat("d-M-y_H-m-s").format(new Date()) + ".xlsx");

		ExcelDocumentData excelDocumentData = new ExcelDocumentData("mevenk - Excel Builder", sheets);
		ExcelProperties excelProperties = excelDocumentData.getExcelProperties();
		excelProperties.addTitle("Built by mevenk - Excel Builder");
		excelProperties.addCustomProperty("HOSTNAME", "VENKATESH-NUC");
		excelProperties.addCustomProperty("IS automated file generation", true);
		excelProperties.addCustomProperty("Random int", 5);
		excelProperties.addCustomProperty("Random double", 10.5);
		excelProperties.addProperties(System.getProperties());
		ExcelDocumentBuilder.build(excelDocumentData, fileOutputStream);

	}

}
