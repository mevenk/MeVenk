/**
 * 
 */
package org.mevenk.utils.zip;

import static org.mevenk.utils.helper.MeVenkUtilsHelper.FILE_SEPARATOR;
import static org.mevenk.utils.helper.MeVenkUtilsHelper.verifyIfDirectory;
import static org.mevenk.utils.zip.ZipActivities.ZIP_DIRECTORY_NAME_SUFFIX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
		File[] filesFirstDirectory = directory.listFiles();
		int lengthFiles = filesFirstDirectory.length;
		if (!allowEmpty && lengthFiles == 0) {
			throw new IllegalArgumentException(directory.getName() + " is empty");
		}

		LinkedList<ZipEntity> zipEntities = new LinkedList<ZipEntity>();
		List<String> directoriesNames = new LinkedList<String>();
		generateZipEntities(filesFirstDirectory, zipEntities, directoriesNames);

		generateZip(outputStream, true, zipEntities.toArray(new ZipEntity[] {}));

	}

	/**
	 * 
	 * @param files
	 * @param zipEntities
	 * @param directoriesNames
	 * @throws FileNotFoundException
	 */
	private static void generateZipEntities(File[] files, LinkedList<ZipEntity> zipEntities,
			List<String> directoriesNames) throws FileNotFoundException {

		ZipEntity entity = null;
		String fileName = null;

		for (File file : files) {
			if (file.isHidden()) {
				continue;
			}
			fileName = file.getName();
			if (file.isDirectory()) {
				directoriesNames.add(fileName);
				entity = new ZipEntity(
						StringUtils.join(directoriesNames, ZIP_DIRECTORY_NAME_SUFFIX) + ZIP_DIRECTORY_NAME_SUFFIX);
				generateZipEntities(file.listFiles(), zipEntities, directoriesNames);
				directoriesNames.remove(directoriesNames.size() - 1);
			} else {
				entity = new ZipEntity(directoriesNames.isEmpty() ? fileName
						: StringUtils.join(directoriesNames, ZIP_DIRECTORY_NAME_SUFFIX) + ZIP_DIRECTORY_NAME_SUFFIX
								+ fileName,
						new FileInputStream(file));
			}
			zipEntities.add(entity);
		}
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
			if (newFile.isDirectory()) {
				continue;
			}
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

		if (!destinationFilePath.startsWith(destinationDirectoryPath + FILE_SEPARATOR)) {
			throw new IOException("Entry is outside of the target dir: " + fileName);
		}

		File parentFile = destinationFile.getParentFile();
		if (!parentFile.isDirectory()) {
			parentFile.mkdir();
		}

		return destinationFile;
	}
}
