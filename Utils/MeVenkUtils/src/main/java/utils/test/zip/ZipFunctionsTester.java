/**
 * 
 */
package utils.test.zip;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mevenk.utils.zip.ZipFunctions;

/**
 * @author vkolisetty
 *
 */
public class ZipFunctionsTester {

	private static final String DIR_TEMPORARY = "/home/vkolisetty/RABOTA/Temporary";
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_ZIP = new SimpleDateFormat("d-M-y_H-m-s");
	private static final Date DATE_TODAY = new Date();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		generateZipFile();

	}

	private static void generateZipFile() throws Exception {

		ZipFunctions.generateZip(
				new FileOutputStream(new File(DIR_TEMPORARY + "/ZipTester/zips/" + "ZIP_"
						+ SIMPLE_DATE_FORMAT_ZIP.format(DATE_TODAY) + ".zip")),
				new File(DIR_TEMPORARY + "/ExcelBuilder"), false);

	}

}
