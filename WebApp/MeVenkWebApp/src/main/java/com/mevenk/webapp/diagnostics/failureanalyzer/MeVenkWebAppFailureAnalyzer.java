/**
 * 
 */
package com.mevenk.webapp.diagnostics.failureanalyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import com.mevenk.webapp.exception.MeVenkWebAppException;

/**
 * @author vkolisetty
 *
 */
public class MeVenkWebAppFailureAnalyzer extends AbstractFailureAnalyzer<MeVenkWebAppException> {

	@Override
	protected FailureAnalysis analyze(Throwable rootFailure, MeVenkWebAppException cause) {
		return new FailureAnalysis(cause.getDescription(), cause.getAction(), rootFailure);
	}

}
