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
	 * @param directory
	 * @throws IllegalArgumentException
	 */
	public static final void verifyIfDirectory(File directory) throws IllegalArgumentException {
		if (!directory.isDirectory()) {
			throw new IllegalArgumentException(directory.getName() + " is not a directory");
		}
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
