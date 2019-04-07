/**
 * 
 */
package org.mevenk.utils.git.report.log;

import static org.mevenk.utils.git.report.log.util.GitLogReportUtil.LINE_SEPARATOR;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.text.StringSubstitutor;
import org.mevenk.utils.git.report.log.data.GitDiffData;
import org.mevenk.utils.git.report.log.data.GitDiffData.ChangeType;
import org.mevenk.utils.git.report.log.data.GitLogData;
import org.mevenk.utils.git.report.log.util.GitLogReportUtil;

/**
 * @author vkolisetty
 *
 */
abstract class GitLogHTMLReport {

	private static enum HTMLReportTemplate {
		BASE("reportBase.html"), COMMIT_DETAILS("reportCommitDetails.html"),
		CHANGED_FILE_DETAILS("reportChangedFileDetails.html"), COMMIT_DIVIDER("reportCommitDivider.html");

		private String fileName;

		private HTMLReportTemplate(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return the fileName
		 */
		public final String getFileName() {
			return fileName;
		}

	}

	/**
	 * 
	 */
	private GitLogHTMLReport() {
		// Prevent instantiation
	}

	/**
	 * 
	 * @param gitlogs
	 * @param commitURLPrefix
	 * @return
	 * @throws Exception
	 */
	private static final String generateHTMLSource(LinkedHashSet<GitLogData> gitlogs, String commitURLPrefix)
			throws Exception {

		StringBuilder logsHTMLSource = new StringBuilder();

		int gitlogsSize = gitlogs.size();
		int logCounter = 0;
		for (GitLogData log : gitlogs) {

			String abbreviatedCommit = log.getAbbreviatedCommit();
			String commit = log.getCommit();
			String commitUrl = commitURLPrefix == null ? "#" + commit : commitURLPrefix + "/" + commit;
			String authorName = log.getAuthorName();
			String authorEmail = log.getAuthorEmail();
			Date when = log.getWhen();
			String commitFullMessage = log.getCommitFullMessage();

			Map<String, String> commitDetails = new HashMap<String, String>(6);
			commitDetails.put("ABBREVIATED_COMMIT", abbreviatedCommit);
			commitDetails.put("COMMIT_URL", commitUrl);
			commitDetails.put("COMMIT", commit);
			commitDetails.put("AUTHOR", authorName + " &lt;" + authorEmail + "&gt;");
			commitDetails.put("DATE", when.toString());
			commitDetails.put("COMMENTS", commitFullMessage);

			logsHTMLSource.append(LINE_SEPARATOR);
			logsHTMLSource.append(replacePlaceholders(HTMLReportTemplate.COMMIT_DETAILS, commitDetails));
			logsHTMLSource.append(LINE_SEPARATOR);

			Map<String, String> changedFileDetails = null;
			for (GitDiffData diff : log.getGitDiffDatas()) {

				changedFileDetails = new HashMap<String, String>(2);

				ChangeType changeType = diff.getChangeType();
				String filePath = null;
				switch (changeType) {
				case RENAME:
					filePath = MessageFormat.format("{0}  {1}", diff.getOldPath(), diff.getNewPath());
					break;

				default:
					filePath = diff.getNewPath();
					break;
				}

				changedFileDetails.put("CHANGE_TYPE", changeType.name());
				changedFileDetails.put("FILE_PATH", filePath);

				logsHTMLSource.append(LINE_SEPARATOR);
				logsHTMLSource.append(replacePlaceholders(HTMLReportTemplate.CHANGED_FILE_DETAILS, changedFileDetails));
				logsHTMLSource.append(LINE_SEPARATOR);

			}

			if (++logCounter < gitlogsSize) {
				logsHTMLSource.append(LINE_SEPARATOR);
				logsHTMLSource.append(getTemplateContent(HTMLReportTemplate.COMMIT_DIVIDER));
				logsHTMLSource.append(LINE_SEPARATOR);
			}

		}

		Map<String, String> sourceHTMLReport = new HashMap<String, String>(1);
		sourceHTMLReport.put("COMMITS_LIST", logsHTMLSource.toString());

		return replacePlaceholders(HTMLReportTemplate.BASE, sourceHTMLReport);

	}

	/**
	 * 
	 * @param template
	 * @param values
	 * @return
	 * @throws IOException
	 */
	private static final String replacePlaceholders(HTMLReportTemplate htmlReportTemplate, Map<String, String> values)
			throws IOException {
		return StringSubstitutor.replace(getTemplateContent(htmlReportTemplate), values, "${", "}");
	}

	/**
	 * 
	 * @param htmlReportTemplate
	 * @return
	 */
	private static final String getTemplateContent(HTMLReportTemplate htmlReportTemplate) {
		StringBuilder fileContent = new StringBuilder();
		InputStream resourceAsStream = GitLogHTMLReport.class
				.getResourceAsStream("/org/mevenk/utils/git/report/log/" + htmlReportTemplate.getFileName());
		Scanner scanner = new Scanner(resourceAsStream);
		while (scanner.hasNext()) {
			fileContent.append(scanner.nextLine());
		}
		scanner.close();
		return fileContent.toString();
	}

	/**
	 * 
	 * @param outputStreamReport
	 * @param gitlogs
	 * @param commitURLPrefix
	 * @throws Exception
	 */
	static final void generateHTMLReport(OutputStream outputStreamReport, LinkedHashSet<GitLogData> gitlogs,
			String commitURLPrefix) throws Exception {
		GitLogReportUtil.writeToStream(generateHTMLSource(gitlogs, commitURLPrefix), outputStreamReport);
	}

	/**
	 * 
	 * @param outputStreamReport
	 * @param gitlogs
	 * @throws Exception
	 */
	static final void generateHTMLReport(OutputStream outputStreamReport, LinkedHashSet<GitLogData> gitlogs)
			throws Exception {
		generateHTMLReport(outputStreamReport, gitlogs, null);
	}

}
