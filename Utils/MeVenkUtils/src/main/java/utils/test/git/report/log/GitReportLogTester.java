/**
 * 
 */
package utils.test.git.report.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.mevenk.utils.git.report.log.GitLogReport;
import org.mevenk.utils.git.report.log.GitLogReportGenerator;
import org.mevenk.utils.git.report.log.GitLogReportGenerator.GitLogReportType;

/**
 * @author vkolisetty
 *
 */
public class GitReportLogTester {

	private static final String COMMIT_URL_PREFIX = "https://github.com/mevenk/MeVenk/commit";

	private static final String URL_REPO_MEVENK = "https://github.com/mevenk/MeVenk.git";
	private static final String GIT_DIR_PATH_LOCAL_MEVENK = "/home/vkolisetty/RABOTA/MeVenk/.git";

	private static final GitLogReportType REPORT_TYPE_TEXT = GitLogReportType.TEXT;
	private static final GitLogReportType REPORT_TYPE_HTML = GitLogReportType.HTML;

	private static final String TREE_MASTER = "master";
	private static final int ABBREVIATED_COMMIT_LENGTH = 7;

	private static Date SINCE = null;
	private static Date UNTIL = null;

	private static final File GIT_DIR = new File(GIT_DIR_PATH_LOCAL_MEVENK);

	private static OutputStream outputStreamGitLogReportFile = null;
	private static OutputStream outputStreamGitLogReportFileHTML = null;

	static {
		try {
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2019, 3, 8, 0, 0, 0);
			
			SINCE = calendar.getTime();
			
			calendar.set(2019, 3, 9, 23, 59, 59);
			UNTIL = calendar.getTime();
			
			outputStreamGitLogReportFile = new FileOutputStream(
					"/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReport.txt");
			outputStreamGitLogReportFileHTML = new FileOutputStream(
					"/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReportHTML.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 */
	private static OutputStream getDiffFileOutputStream() throws FileNotFoundException {
		return new FileOutputStream("/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReport_"
				+ new SimpleDateFormat("d-M-y_H-m-s").format(new Date()) + ".diff");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		generateReportFromGitDirectory();
		generateReportFromGitDirectory(15);

	}

	private static void generateReportFromGitDirectory(int maxNoOfCommits) throws Exception {

		GitLogReportGenerator.generateReport(REPORT_TYPE_HTML, outputStreamGitLogReportFileHTML, GIT_DIR, "1a74a5f42e1fe78d0c8516d4cc84b90fe511f02e",
				ABBREVIATED_COMMIT_LENGTH, getDiffFileOutputStream(), maxNoOfCommits, COMMIT_URL_PREFIX);

	}

	private static void generateReportFromGitDirectory() throws Exception {

		GitLogReportGenerator.generateReport(REPORT_TYPE_TEXT, outputStreamGitLogReportFile, GIT_DIR, TREE_MASTER,
				ABBREVIATED_COMMIT_LENGTH, getDiffFileOutputStream(), SINCE, COMMIT_URL_PREFIX);
	}

}
