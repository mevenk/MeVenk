/**
 *
 */
package com.mevenk.webapp.validator.sampleform;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import com.mevenk.webapp.modelattribute.SampleForm;
import com.mevenk.webapp.service.BaseService;
import com.mevenk.webapp.validator.MeVenkWebAppValidator;

/**
 * @author venky
 *
 */
public class SampleFormValidator extends MeVenkWebAppValidator {

	public SampleFormValidator(Errors errors, Object form, HttpServletRequest request) {
		super(errors, form, request);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object form, Errors errors) {

		BaseService baseService = (BaseService) getBean(BaseService.class, null);

		SampleForm sampleForm = (SampleForm) form;

		if (sampleForm.getHiddenString() == null) {
			rejectFormFieldValue("hiddenString", "NULL Not Allowed");
		}

	}

}
