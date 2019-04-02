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
	 * @param string
	 * @param outputStream
	 * @throws Exception
	 */
	public static final void writeToStream(String string, OutputStream outputStream) throws Exception {
		outputStream.write(string.getBytes());
	}

}
