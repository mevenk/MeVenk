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

	GIT_LOG_REPORT_GENERATOR(new GitLogReportGeneratorRunner());

	private MeVenkUtilsRunner meVenkUtilsRunner;

	/**
	 * 
	 * @param meVenkUtilsRunner
	 */
	private MeVenkUtilsModule(MeVenkUtilsRunner meVenkUtilsRunner) {
		this.meVenkUtilsRunner = meVenkUtilsRunner;
	}

	/**
	 * @return the meVenkUtilsRunner
	 */
	public final MeVenkUtilsRunner getMeVenkUtilsRunner() {
		return meVenkUtilsRunner;
	}

}
