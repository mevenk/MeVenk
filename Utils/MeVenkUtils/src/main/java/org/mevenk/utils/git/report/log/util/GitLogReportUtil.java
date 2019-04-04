/**
 * 
 */
package org.mevenk.utils.git.report.log.util;

import java.io.OutputStream;

/**
 * @author vkolisetty
 *
 */
public final class GitLogReportUtil {

	public static final String LINE_SEPARATOR = System.lineSeparator();

	/**
	 * 
	 * @throws IllegalAccessException
	 */
	private GitLogReportUtil() throws IllegalAccessException {
		throw new IllegalAccessException("Utility class");
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
