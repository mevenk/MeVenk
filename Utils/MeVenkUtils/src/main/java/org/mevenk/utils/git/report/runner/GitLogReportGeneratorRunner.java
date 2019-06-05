/**
 * 
 */
package org.mevenk.utils.git.report.runner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import org.mevenk.utils.InvalidInputException;
import org.mevenk.utils.MeVenkUtilsRunner;
import org.mevenk.utils.git.report.log.GitLogReportGenerator.GitLogReportType;

/**
 * @author vkolisetty
 *
 */
public class GitLogReportGeneratorRunner implements MeVenkUtilsRunner {

	private static Scanner scanner;

	private static String commitUrlPrefix;
	private static GitLogReportType reportType;
	private static FileOutputStream fileOutputStreamReport;
	private static File gitDir;
	private static String tree;
	private static int abbreviatedCommitLength;
	private static FileOutputStream fileOutputStreamDiff;
	private static int maxNoOfCommits;

	private static void setReportType() {

		System.out.println("Select report type");
		System.out.println("1. Text");
		System.out.println("2. HTML");

		int input = scanner.nextInt();
		if (input > 2) {
			throw new InvalidInputException();
		}

		switch (input) {
		case 1:
			reportType = GitLogReportType.TEXT;
			break;
		case 2:
			reportType = GitLogReportType.HTML;
			break;
		}

	}

	private static void setFileOutputStreamReport() throws FileNotFoundException {

		System.out.println("Enter report file");

		File reportFile = new File(scanner.nextLine());
		if (reportFile.isDirectory()) {
			throw new InvalidInputException("Cannot be a directory");
		}
		if (reportFile.exists()) {
			throw new InvalidInputException("File already exists");
		}

		fileOutputStreamReport = new FileOutputStream(reportFile);
	}

	@Override
	public void run() throws Throwable {
		try {
			scanner = new Scanner(System.in);
			System.out.println("Welcome to GitLogReportGeneratorRunner");
			setReportType();
			setFileOutputStreamReport();

		} finally {
			scanner.close();
		}

	}

	@Override
	public String getDisplayDescription() {
		return "Git log report generator";
	}

}
