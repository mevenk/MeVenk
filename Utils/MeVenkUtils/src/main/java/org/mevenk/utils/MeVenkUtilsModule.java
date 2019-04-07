/**
 * 
 */
package org.mevenk.utils;

import org.mevenk.utils.git.report.runner.GitLogReportGeneratorRunner;

/**
 * @author vkolisetty
 *
 */
public enum MeVenkUtilsModule {

	GIT_LOG_REPORT_GENERATOR("Git Log Report Generator", new GitLogReportGeneratorRunner());

	private String displayDescription;
	private MeVenkUtilsRunner meVenkUtilsRunner;

	/**
	 * @param displayDescription
	 * @param meVenkUtilsRunner
	 */
	private MeVenkUtilsModule(String displayDescription, MeVenkUtilsRunner meVenkUtilsRunner) {
		this.displayDescription = displayDescription;
		this.meVenkUtilsRunner = meVenkUtilsRunner;
	}

	/**
	 * @return the displayDescription
	 */
	public final String getDisplayDescription() {
		return displayDescription;
	}

	/**
	 * @return the meVenkUtilsRunner
	 */
	public final MeVenkUtilsRunner getMeVenkUtilsRunner() {
		return meVenkUtilsRunner;
	}

}
