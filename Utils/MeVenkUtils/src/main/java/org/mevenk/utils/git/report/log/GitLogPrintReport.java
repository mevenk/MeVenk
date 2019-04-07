/**
 * 
 */
package org.mevenk.utils.git.report.log;

import static org.mevenk.utils.git.report.log.util.GitLogReportUtil.LINE_SEPARATOR;
import static org.mevenk.utils.git.report.log.util.GitLogReportUtil.writeToStream;

import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import org.mevenk.utils.git.report.log.data.GitDiffData;
import org.mevenk.utils.git.report.log.data.GitDiffData.ChangeType;
import org.mevenk.utils.git.report.log.data.GitLogData;

/**
 * @author vkolisetty
 *
 */
abstract class GitLogPrintReport {

	/**
	 * 
	 */
	private GitLogPrintReport() {
		// Prevent instantiation
	}

	/**
	 * 
	 * @param gitLogDatas
	 * @param outputStream
	 * @throws Exception
	 */
	static final void printLogData(LinkedHashSet<GitLogData> gitLogDatas, OutputStream outputStream) throws Exception {

		writeToStream(LINE_SEPARATOR + new Date() + LINE_SEPARATOR + LINE_SEPARATOR, outputStream);

		for (GitLogData log : gitLogDatas) {

			writeToStream("commit " + log.getAbbreviatedCommit() + "	[" + log.getCommit() + "]" + LINE_SEPARATOR,
					outputStream);
			writeToStream("Author: " + log.getAuthorName() + " <" + log.getAuthorEmail() + ">" + LINE_SEPARATOR,
					outputStream);
			writeToStream("Date: " + log.getWhen() + LINE_SEPARATOR, outputStream);
			writeToStream(LINE_SEPARATOR, outputStream);
			writeToStream("	" + log.getCommitFullMessage() + LINE_SEPARATOR, outputStream);
			writeToStream(LINE_SEPARATOR, outputStream);

			for (GitDiffData diff : log.getGitDiffDatas()) {

				ChangeType changeType = diff.getChangeType();

				switch (changeType) {
				case RENAME:
					writeToStream(MessageFormat.format("{0}		{1}  {2}", changeType, diff.getOldPath(),
							diff.getNewPath()), outputStream);
					writeToStream(LINE_SEPARATOR, outputStream);
					break;

				case DELETE:
					writeToStream(MessageFormat.format("{0}		{1}", changeType, diff.getOldPath()), outputStream);
					writeToStream(LINE_SEPARATOR, outputStream);
					break;

				default:
					writeToStream(MessageFormat.format("{0}		{1}", changeType, diff.getNewPath()), outputStream);
					writeToStream(LINE_SEPARATOR, outputStream);
					break;
				}

			}

			writeToStream(LINE_SEPARATOR, outputStream);

		}

	}

}
