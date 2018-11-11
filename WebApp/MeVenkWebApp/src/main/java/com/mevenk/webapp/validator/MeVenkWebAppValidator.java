/**
 *
 */
package com.mevenk.webapp.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mevenk.webapp.util.HTTPUtil;
import com.mevenk.webapp.util.MeVenkWebAppStringUtil;

/**
 * @author venky
 *
 */
public abstract class MeVenkWebAppValidator implements Validator {

	private ApplicationContext applicationContext;

	private Errors errors;
	private Object form;
	private HttpServletRequest request;

	/**
	 * @param request
	 * @param errors
	 */
	public MeVenkWebAppValidator(Errors errors, Object form, HttpServletRequest request) {

		this.form = form;
		this.errors = errors;

		if (hasErrors()) {
			return;
		}

		if (request == null) {
			request = HTTPUtil.getHTTPRequest();
		}
		this.request = request;
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
		validate(form, errors);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return form.getClass().isAssignableFrom(clazz);
	}

	/**
	 *
	 * @param requiredType
	 * @param qualifier
	 * @return
	 */
	protected final Object getBean(Class<?> requiredType, String qualifier) {
		if (qualifier == null) {
			return applicationContext.getBean(requiredType);
		} else {
			return BeanFactoryAnnotationUtils.qualifiedBeanOfType(applicationContext, requiredType, qualifier);
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
		errors.rejectValue(field, null, message);
	}

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValue(String field, int messageId) {
		errors.rejectValue(field, getMessageIdAsString(messageId));
	}

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValueIfEmpty(String field, int messageId) {
		ValidationUtils.rejectIfEmpty(errors, field, getMessageIdAsString(messageId));
	}

	/**
	 *
	 * @param field
	 * @param messageId
	 */
	protected final void rejectFormFieldValueIfEmptyOrWhitespace(String field, int messageId) {
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
		return errors.hasErrors();
	}

	/**
	 *
	 * @param field
	 * @return
	 */
	protected final boolean hasFormFieldErrors(String field) {
		return errors.hasFieldErrors(field);
	}

	/**
	 *
	 * @param name
	 * @param object
	 */
	protected final void setRequestAttribute(String name, Object object) {
		request.setAttribute(name, object);
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	protected final Object getRequestAttribute(String name) {
		return request.getAttribute(name);
	}

}
