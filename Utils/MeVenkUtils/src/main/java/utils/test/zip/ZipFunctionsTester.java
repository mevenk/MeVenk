/**
 * 
 */
package utils.test.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mevenk.utils.zip.ZipFunctions;

/**
 * @author vkolisetty
 *
 */
public class ZipFunctionsTester {

	private static final String DIRECTORY_TO_BE_ZIPPED = "/home/vkolisetty/RABOTA/Temporary/ZipTester/zipTestFiolder";
	private static final String DIR_ZIP_TESTER = "/home/vkolisetty/RABOTA/Temporary/ZipTester";
	private static final File UNZIP_DIRECTORY = new File(DIR_ZIP_TESTER + "/unzips");
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_ZIP = new SimpleDateFormat("d-M-y_H-m-s");
	private static final Date DATE_TODAY = new Date();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		File zipFile = generateZipFile();
		unzipZipFile(zipFile);

	}

	/**
	 * 
	 * @throws Exception
	 */
	private static File generateZipFile() throws Exception {

		File zipFile = new File(
				DIR_ZIP_TESTER + "/zips/" + "ZIP_" + SIMPLE_DATE_FORMAT_ZIP.format(DATE_TODAY) + ".zip");
		ZipFunctions.generateZip(new FileOutputStream(zipFile), new File(DIRECTORY_TO_BE_ZIPPED), false);

		return zipFile;

	}

	/**
	 * 
	 * @param zipFile
	 * @throws Exception
	 */
	private static void unzipZipFile(File zipFile) throws Exception {

		File unzipDirectory = new File(
				UNZIP_DIRECTORY + "/" + zipFile.getName().substring(0, zipFile.getName().length() - 4));
		unzipDirectory.mkdirs();

		ZipFunctions.unzip(new FileInputStream(zipFile), unzipDirectory);

	}

}
