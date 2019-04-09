/**
 * 
 */
package org.mevenk.utils.helper;

import java.io.File;
import java.io.OutputStream;

/**
 * @author vkolisetty
 *
 */
public final class MeVenkUtilsHelper {

	public static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * 
	 * @throws IllegalAccessException
	 */
	private MeVenkUtilsHelper() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
	}

	/**
	 * 
	 * @param directory
	 * @param throwException
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static final boolean verifyIfDirectory(File directory, boolean throwException)
			throws IllegalArgumentException {
		boolean isDirectory = directory.isDirectory();
		if (!isDirectory && throwException) {
			throw new IllegalArgumentException(directory.getName() + " is not a directory");
		}
		return isDirectory;
	}

	/**
	 * 
	 * @param value
	 * @param outputStream
	 * @throws Exception
	 */
	public static final void writeToStream(Object value, OutputStream outputStream) throws Exception {
		outputStream.write(String.valueOf(value).getBytes());
	}

}
