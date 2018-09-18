/**
 *
 */
package com.mevenk.webapp.controller.advice;

import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_IDENTIFIER;
import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_MESSAGE;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mevenk.webapp.to.ApplicationExceptionTO;

/**
 * @author venky
 *
 */
@ControllerAdvice
public class MeVenkWebAppControllerAdvice {

	private static final Logger LOG = getLogger(MeVenkWebAppControllerAdvice.class);

	@ExceptionHandler(value = Throwable.class)
	public ModelAndView mevenkWebAppExceptionHandler(Throwable throwable) {
		LOG.error("Error!!", throwable);
		ModelAndView modelAndViewApplicationException = new ModelAndView("applicationException");
		ApplicationExceptionTO applicationExceptionTO = new ApplicationExceptionTO(
				String.valueOf(System.currentTimeMillis()), throwable.getMessage());
		modelAndViewApplicationException.addObject("applicationExceptionPageTitle",
				"ERROR - " + applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_IDENTIFIER,
				applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_MESSAGE,
				applicationExceptionTO.getApplicationErrorMessage());
		return modelAndViewApplicationException;
	}

}
