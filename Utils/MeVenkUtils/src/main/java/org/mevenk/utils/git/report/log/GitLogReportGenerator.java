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

	/**
	 * 
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param maxNoOfCommits
	 * @throws Exception
	 */
	public static final void generateReport(OutputStream outputStreamReport, File gitDir, String tree,
			int abbreviatedCommitLength, OutputStream outputStreamDiff, int maxNoOfCommits) throws Exception {

		LinkedHashSet<GitLogData> generateLogReport = GitLogReport.generateLogReport(gitDir, tree,
				abbreviatedCommitLength, maxNoOfCommits, outputStreamDiff);
		GitLogPrintReport.printLogData(generateLogReport, outputStreamReport);

	}

	/**
	 * 
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param since
	 * @param until
	 * @throws Exception
	 */
	public static final void generateReport(OutputStream outputStreamReport, File gitDir, String tree,
			int abbreviatedCommitLength, OutputStream outputStreamDiff, Date since, Date until) throws Exception {

		LinkedHashSet<GitLogData> generateLogReport = GitLogReport.generateLogReport(gitDir, tree,
				abbreviatedCommitLength, since, until, outputStreamDiff);
		GitLogPrintReport.printLogData(generateLogReport, outputStreamReport);

	}

	/**
	 * 
	 * @param outputStreamReport
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param since
	 * @throws Exception
	 */
	public static final void generateReport(OutputStream outputStreamReport, File gitDir, String tree,
			int abbreviatedCommitLength, OutputStream outputStreamDiff, Date since) throws Exception {

		generateReport(outputStreamReport, gitDir, tree, abbreviatedCommitLength, outputStreamDiff, since, new Date());

	}

}
