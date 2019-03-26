/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.io.File;
import java.io.FileOutputStream;
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

	private static final String URL_REPO_MEVENK = "https://github.com/mevenk/MeVenk.git";
	private static final String GIT_DIR_PATH_LOCAL_MEVENK = "/home/vkolisetty/RABOTA/MeVenk/.git";

	private static final void printLogData(LinkedHashSet<GitLogData> gitLogDatas) {

		for (GitLogData log : gitLogDatas) {

			System.out.println("commit " + log.getAbbreviatedCommit() + "	[" + log.getCommit() + "]");
			System.out.println("Author: " + log.getAuthorName() + " <" + log.getAuthorEmail() + ">");
			System.out.println("Date: " + log.getWhen());
			System.out.println();
			System.out.println("	" + log.getCommitFullMessage());
			System.out.println();

			for (GitDiffData diff : log.getGitDiffDatas()) {

				ChangeType changeType = diff.getChangeType();

				switch (changeType) {
				case RENAME:
					System.out.println(
							MessageFormat.format("{0}	{1}  {2}", changeType, diff.getOldPath(), diff.getNewPath()));
					break;

				default:
					System.out.println(MessageFormat.format("{0}	{1}", changeType, diff.getNewPath()));
					break;
				}

			}

			System.out.println();

		}

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
		printLogData(generateLogReport);

	}

}
