/**
 * 
 */
package utils.test.git.report.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mevenk.utils.git.report.log.GitLogReport;
import org.mevenk.utils.git.report.log.GitLogReportGenerator;

/**
 * @author vkolisetty
 *
 */
public class GitReportLogTester {

	private static final String URL_REPO_MEVENK = "https://github.com/mevenk/MeVenk.git";
	private static final String GIT_DIR_PATH_LOCAL_MEVENK = "/home/vkolisetty/RABOTA/MeVenk/.git";

	private static final String TREE_MASTER = "master";
	private static final int ABBREVIATED_COMMIT_LENGTH = 7;

	private static Date SINCE = null;

	static {
		try {
			SINCE = GitLogReport.SIMPLE_DATE_FORMAT_COMMIT_TIME.parse("2019-02-12");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		generateReportFromGitDirectory();

	}

	private static void generateReportFromGitDirectory() throws Exception {

		OutputStream outputStreamDiff = new FileOutputStream("/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReport_"
				+ new SimpleDateFormat("d-M-y_H-m-s").format(new Date()) + ".diff");

		OutputStream outputStreamReport = null;
		outputStreamReport = new FileOutputStream("/home/vkolisetty/RABOTA/Temporary/GitDiffs/GitLogReport.txt");
		// outputStreamReport = System.out;

		GitLogReportGenerator.generateReport(outputStreamReport, new File(GIT_DIR_PATH_LOCAL_MEVENK), TREE_MASTER,
				ABBREVIATED_COMMIT_LENGTH, outputStreamDiff, SINCE);
	}

}
