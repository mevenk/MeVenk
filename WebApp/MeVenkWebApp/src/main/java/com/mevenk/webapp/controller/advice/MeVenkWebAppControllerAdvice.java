/**
 *
 */
package com.mevenk.webapp.controller.advice;

import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_IDENTIFIER;
import static com.mevenk.webapp.to.ApplicationExceptionTO.APPLICATION_ERROR_MESSAGE;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.EXCEPTION_OCCURED_ATTRIBUTE_NAME;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.mevenk.webapp.service.BaseService;
import com.mevenk.webapp.to.ApplicationExceptionTO;

/**
 * @author venky
 *
 */
@ControllerAdvice
public class MeVenkWebAppControllerAdvice {

	private static final Logger LOG = getLogger(MeVenkWebAppControllerAdvice.class);

	private static final Set<Class<? extends Throwable>> EXCEPTIONS_SKIP_SAVE_DETAIL = new HashSet<>();

	static {

		EXCEPTIONS_SKIP_SAVE_DETAIL.add(NoHandlerFoundException.class);

	}

	@Autowired
	private BaseService baseService;

	@ExceptionHandler(value = Throwable.class)
	public ModelAndView mevenkWebAppExceptionHandler(Throwable throwable, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		LOG.error("Error!!-{}", throwable.getClass());
		return generateModelAndView(throwable, request, session);

	}

	/**
	 *
	 * @param throwable
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	private final ModelAndView generateModelAndView(Throwable throwable, HttpServletRequest request,
			HttpSession session) {

		boolean isSkipException = EXCEPTIONS_SKIP_SAVE_DETAIL.contains(throwable.getClass());
		String viewName = isSkipException ? "unknownError" : "applicationException";

		ModelAndView modelAndViewApplicationException = new ModelAndView(viewName);

		if (!isSkipException) {
			saveExceptionDetail(throwable, request, session, modelAndViewApplicationException);
		}

		modelAndViewApplicationException.addObject(EXCEPTION_OCCURED_ATTRIBUTE_NAME, throwable);
		return modelAndViewApplicationException;

	}

	/**
	 * @param throwable
	 * @param request
	 * @param session
	 * @param modelAndViewApplicationException
	 */
	private final void saveExceptionDetail(Throwable throwable, HttpServletRequest request, HttpSession session,
			ModelAndView modelAndViewApplicationException) {
		ApplicationExceptionTO applicationExceptionTO = baseService.saveApplicationException(throwable, request,
				session);
		LOG.info("Exception saved @ {}", applicationExceptionTO);
		modelAndViewApplicationException.addObject("applicationExceptionPageTitle",
				"ERROR - " + applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_IDENTIFIER,
				applicationExceptionTO.getApplicationErrorIdentifier());
		modelAndViewApplicationException.addObject(APPLICATION_ERROR_MESSAGE,
				applicationExceptionTO.getApplicationErrorMessage());
	}

}
