/**
 *
 */
package com.mevenk.webapp.validator;

import static org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils.qualifiedBeanOfType;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

		this.errors = errors;

		if (hasErrors()) {
			return;
		}

		this.request = request;
		applicationContext = getRequiredWebApplicationContext(request.getServletContext());
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

	protected final Object getBean(Class<?> requiredType, String qualifier) {
		Map<String, ?> beansOfType = applicationContext.getBeansOfType(requiredType);
		if (qualifier == null) {
			return applicationContext.getBean(requiredType);
		} else {
			return qualifiedBeanOfType(applicationContext, requiredType, qualifier);
		}
	}

	protected final void rejectFormFieldValue(String field, String message) {
		errors.rejectValue(field, null, message);
	}

	public final boolean hasErrors() {
		return errors.hasErrors();
	}

	protected final void setRequestAttribute(String name, Object object) {
		request.setAttribute(name, object);
	}

	protected final Object getRequestAttribute(String name) {
		return request.getAttribute(name);
	}

}
