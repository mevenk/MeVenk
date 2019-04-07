/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashSet;

import org.mevenk.utils.git.report.log.data.GitLogData;

/**
 * @author vkolisetty
 *
 */
public class GitLogReportGenerator {

	public static enum GitLogReportType {
		TEXT, HTML;
	}

	/**
	 * 
	 * @param reportType
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param maxNoOfCommits
	 * @throws Exception
	 */
	public static final void generateReport(GitLogReportType reportType, OutputStream outputStreamReport, File gitDir,
			String tree, int abbreviatedCommitLength, OutputStream outputStreamDiff, int maxNoOfCommits)
			throws Exception {

		LinkedHashSet<GitLogData> generateLogReport = GitLogReport.generateLogReport(gitDir, tree,
				abbreviatedCommitLength, maxNoOfCommits, outputStreamDiff);
		generateReport(reportType, outputStreamReport, generateLogReport);

	}

	/**
	 * 
	 * @param reportType
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param since
	 * @param until
	 * @throws Exception
	 */
	public static final void generateReport(GitLogReportType reportType, OutputStream outputStreamReport, File gitDir,
			String tree, int abbreviatedCommitLength, OutputStream outputStreamDiff, Date since, Date until)
			throws Exception {

		LinkedHashSet<GitLogData> generateLogReport = GitLogReport.generateLogReport(gitDir, tree,
				abbreviatedCommitLength, since, until, outputStreamDiff);
		generateReport(reportType, outputStreamReport, generateLogReport);

	}

	/**
	 * 
	 * @param reportType
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param since
	 * @throws Exception
	 */
	public static final void generateReport(GitLogReportType reportType, OutputStream outputStreamReport, File gitDir,
			String tree, int abbreviatedCommitLength, OutputStream outputStreamDiff, Date since) throws Exception {

		generateReport(reportType, outputStreamReport, gitDir, tree, abbreviatedCommitLength, outputStreamDiff, since,
				new Date());

	}

	/**
	 * 
	 * @param reportType
	 * @param outputStreamReport
	 * @param gitlogs
	 * @throws Exception
	 */
	private static void generateReport(GitLogReportType reportType, OutputStream outputStreamReport,
			LinkedHashSet<GitLogData> gitlogs) throws Exception {

		switch (reportType) {
		case HTML:
			GitLogHTMLReport.generateHTMLReport(outputStreamReport, gitlogs);
			break;
		case TEXT:
			GitLogPrintReport.printLogData(gitlogs, outputStreamReport);
			break;

		}

	}

}
