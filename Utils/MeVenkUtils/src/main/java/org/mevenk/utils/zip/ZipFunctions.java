/**
 * 
 */
package org.mevenk.utils.zip;

import static org.mevenk.utils.helper.MeVenkUtilsHelper.verifyIfDirectory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;

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
	public static LinkedHashSet<ZipEntity> unzip(InputStream inputStreamZip) throws Exception {
		return ZipActivities.unzip(inputStreamZip);
	}

	/**
	 * 
	 * @param inputStreamZip
	 * @param destinationDirectory
	 * @throws Exception
	 */
	public static void unzip(InputStream inputStreamZip, File destinationDirectory) throws Exception {
		verifyIfDirectory(destinationDirectory, true);

		LinkedHashSet<ZipEntity> zipEntries = unzip(inputStreamZip);

		File newFile = null;
		FileOutputStream fileOutputStream = null;
		for (ZipEntity entry : zipEntries) {
			newFile = createDestinationFile(destinationDirectory, entry.getName());
			fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(entry.getBytes());
			fileOutputStream.close();
		}

	}

	/**
	 * 
	 * @param destinationDirectory
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static File createDestinationFile(File destinationDirectory, String fileName) throws Exception {
		File destinationFile = new File(destinationDirectory, fileName);

		String destinationDirectoryPath = destinationDirectory.getCanonicalPath();
		String destinationFilePath = destinationFile.getCanonicalPath();

		if (!destinationFilePath.startsWith(destinationDirectoryPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + fileName);
		}

		return destinationFile;
	}
}
