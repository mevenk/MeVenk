/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import org.mevenk.utils.git.report.log.data.GitDiffData;
import org.mevenk.utils.git.report.log.data.GitDiffData.ChangeType;
import org.mevenk.utils.git.report.log.data.GitLogData;

/**
 * @author vkolisetty
 *
 */
public class GitLogReportGenerator {
	
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private static final String URL_REPO_MEVENK = "https://github.com/mevenk/MeVenk.git";
	private static final String GIT_DIR_PATH_LOCAL_MEVENK = "/home/vkolisetty/RABOTA/MeVenk/.git";

	/**
	 * 
	 * @param gitLogDatas
	 * @param outputStream
	 * @throws Exception 
	 */
	private static final void printLogData(LinkedHashSet<GitLogData> gitLogDatas, OutputStream outputStream)
			throws Exception {

		write(LINE_SEPARATOR + new Date() + LINE_SEPARATOR + LINE_SEPARATOR, outputStream);

		for (GitLogData log : gitLogDatas) {

			write("commit " + log.getAbbreviatedCommit() + "	[" + log.getCommit() + "]" + LINE_SEPARATOR,
					outputStream);
			write("Author: " + log.getAuthorName() + " <" + log.getAuthorEmail() + ">" + LINE_SEPARATOR, outputStream);
			write("Date: " + log.getWhen() + LINE_SEPARATOR, outputStream);
			write(LINE_SEPARATOR, outputStream);
			write("	" + log.getCommitFullMessage() + LINE_SEPARATOR, outputStream);
			write(LINE_SEPARATOR, outputStream);

			for (GitDiffData diff : log.getGitDiffDatas()) {

				ChangeType changeType = diff.getChangeType();

				switch (changeType) {
				case RENAME:
					write(MessageFormat.format("{0}		{1}  {2}", changeType, diff.getOldPath(), diff.getNewPath()),
							outputStream);
					write(LINE_SEPARATOR, outputStream);
					break;

				default:
					write(MessageFormat.format("{0}		{1}", changeType, diff.getNewPath()), outputStream);
					write(LINE_SEPARATOR, outputStream);
					break;
				}

			}

			write(LINE_SEPARATOR, outputStream);

		}

	}

	/**
	 * 
	 * @param string
	 * @param outputStream
	 * @throws Exception
	 */
	private static final void write(String string, OutputStream outputStream) throws Exception {
		outputStream.write(string.getBytes());
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		generateFromFile();

	}

	private static void generateFromFile() throws Exception {
		Date since = GitLogReport.SIMPLE_DATE_FORMAT_COMMIT_TIME.parse("2019-03-12");

		File fileDiff = new File("/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReport_"
				+ new SimpleDateFormat("d-M-y_H-m-s").format(new Date()) + ".diff");

		LinkedHashSet<GitLogData> generateLogReport = GitLogReport.generateLogReport(
				new File(GIT_DIR_PATH_LOCAL_MEVENK), "master", 7, since, new Date(), new FileOutputStream(fileDiff));
		printLogData(generateLogReport, System.out);

	}

}
