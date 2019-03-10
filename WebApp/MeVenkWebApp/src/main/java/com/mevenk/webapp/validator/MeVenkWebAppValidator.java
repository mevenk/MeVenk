/**
 *
 */
package com.mevenk.webapp.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mevenk.webapp.util.HTTPUtil;
import com.mevenk.webapp.util.MeVenkWebAppStringUtil;

/**
 * @author venky
 *
 */
public abstract class MeVenkWebAppValidator implements Validator {

	private Errors errors;
	private HttpServletRequest request;
	
	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * 
	 * @param errors
	 * @param form
	 * @return
	 */
	public final boolean hasErrors(Errors errors, Object form) {

		if (hasErrors(errors)) {
			return true;
		}

		validate(form, errors);
		
		this.errors = null;
		this.request = null;
		
		return hasErrors(errors);

	}
	
	/**
	 * 
	 * @param errors
	 */
	protected void executePreValidationActivities(Errors errors) {
		if (hasErrors(errors)) {
			throw new IllegalStateException(
					"Errors already added! Call com.mevenk.webapp.validator.MeVenkWebAppValidator.hasErrors(Errors, Object, HttpServletRequest) inside your controller to do validations");
		}
		this.errors = errors;
		this.request = HTTPUtil.getHTTPRequest();
	}
	
	/**
	 * 
	 */
	private final void validateErrors() {
		if (errors == null) {
			throw new IllegalStateException(
					"Errors not registered! Call com.mevenk.webapp.validator.MeVenkWebAppValidator.executePreValidationActivities(Errors) before your validations");
		}
	}
	
	/**
	 * 
	 */
	private final void validateRequest() {
		if (request == null) {
			throw new IllegalStateException(
					"Request not availabls!. Call com.mevenk.webapp.validator.MeVenkWebAppValidator.executePreValidationActivities(Errors) before your validations");
		}
	}
	
	/**
	 *
	 * @param object
	 * @return
	 */
	protected static boolean isEmptyOrNull(Object object) {
		if (object instanceof String) {
			return MeVenkWebAppStringUtil.isAnyStringEmptyOrNull((String) object);
		}

		return object == null;

	}

	/**
	 *
	 * @param field
	 * @param message
	 */
	
	protected final void rejectFormFieldValue(String field, String message) {
		validateErrors();
		errors.rejectValue(field, null, message);
	}
	 

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValue(String field, int messageId) {
		validateErrors();
		errors.rejectValue(field, getMessageIdAsString(messageId));
	}

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValueIfEmpty(String field, int messageId) {
		validateErrors();
		ValidationUtils.rejectIfEmpty(errors, field, getMessageIdAsString(messageId));
	}

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValueIfEmptyOrWhitespace(String field, int messageId) {
		validateErrors();
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, getMessageIdAsString(messageId));
	}

	/**
	 * @param messageId
	 * @return
	 */
	private String getMessageIdAsString(int messageId) {
		return String.valueOf(messageId);
	}

	/**
	 *
	 * @return
	 */
	public final boolean hasErrors() {
		validateErrors();
		return errors.hasErrors();
	}
	
	/**
	 * 
	 * @param errors
	 * @return
	 */
	public static final boolean hasErrors(Errors errors) {
		return errors.hasErrors();
	}
	
	/**
	 *
	 * @param field
	 * @return
	 */
	protected final boolean hasFormFieldErrors(String field) {
		validateErrors();
		return errors.hasFieldErrors(field);
	}

	/**
	 *
	 * @param name
	 * @param object
	 */
	protected final void setRequestAttribute(String name, Object object) {
		validateRequest();
		request.setAttribute(name, object);
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	protected final Object getRequestAttribute(String name) {
		validateRequest();
		return request.getAttribute(name);
	}

}
