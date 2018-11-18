/**
 *
 */
package com.mevenk.webapp.controller.advice;

import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_IDENTIFIER;
import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_MESSAGE;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mevenk.webapp.service.BaseService;
import com.mevenk.webapp.to.ApplicationExceptionTO;

/**
 * @author venky
 *
 */
@ControllerAdvice
public class MeVenkWebAppControllerAdvice {

	private static final Logger LOG = getLogger(MeVenkWebAppControllerAdvice.class);

	@Autowired
	private BaseService baseService;

	@ExceptionHandler(value = Throwable.class)
	public ModelAndView mevenkWebAppExceptionHandler(Throwable throwable, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOG.error("Error!!-{}", throwable.getClass());
		ModelAndView modelAndViewApplicationException = new ModelAndView("applicationException");
		ApplicationExceptionTO applicationExceptionTO = baseService.saveApplicationException(throwable, request,
				session);
		LOG.info("Exception saved @ {}", applicationExceptionTO);
		modelAndViewApplicationException.addObject("applicationExceptionPageTitle",
				"ERROR - " + applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_IDENTIFIER,
				applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_MESSAGE,
				applicationExceptionTO.getApplicationErrorMessage());
		return modelAndViewApplicationException;
	}

}
