/**
 * 
 */
package org.mevenk.utils.zip;

import static org.mevenk.utils.helper.MeVenkUtilsHelper.verifyIfDirectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author vkolisetty
 *
 */
public final class ZipFunctions {

	/**
	 * 
	 */
	private ZipFunctions() {
		// Prevent instantiation
	}

	/**
	 * 
	 * @param outputStream
	 * @param zipEntities
	 * @throws Exception
	 */
	public static void generateZip(OutputStream outputStream, boolean allowEmpty, ZipEntity[] zipEntities)
			throws Exception {

		if (!allowEmpty && (zipEntities == null || zipEntities.length == 0)) {
			throw new IllegalArgumentException("Entries empty");
		}

		ZipActivities.generateZip(outputStream, zipEntities);

	}

	/**
	 * 
	 * @param outputStream
	 * @param directory
	 * @param allowEmpty
	 * @throws Exception
	 */
	public static void generateZip(OutputStream outputStream, File directory, boolean allowEmpty) throws Exception {

		verifyIfDirectory(directory, true);
		File[] files = directory.listFiles();
		int lengthFiles = files.length;
		if (!allowEmpty && lengthFiles == 0) {
			throw new IllegalArgumentException(directory.getName() + " is empty");
		}

		ZipEntity[] zipEntities = new ZipEntity[lengthFiles];
		int indexZipEntity = -1;
		for (File file : files) {
			zipEntities[++indexZipEntity] = new ZipEntity(file.getName(), new FileInputStream(file));
		}

		generateZip(outputStream, true, zipEntities);

	}

	/**
	 * 
	 * @param inputStreamZip
	 * @return
	 * @throws Exception
	 */
	public static InputStream[] unzip(InputStream inputStreamZip) throws Exception {
		return null;
	}

	/**
	 * 
	 * @param inputStreamZip
	 * @param destinationDirectory
	 * @throws Exception
	 */
	public static void unzip(InputStream inputStreamZip, File destinationDirectory) throws Exception {
		verifyIfDirectory(destinationDirectory, true);
	}

}
