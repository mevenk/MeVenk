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
		String databaseTimeFormatted = baseService.databaseTimeFormatted();

		SampleForm sampleForm = (SampleForm) form;

		if (sampleForm.getHiddenString() == null) {
			rejectFormFieldValue("hiddenString", "Hidden String - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getHiddenNumber() == null) {
			rejectFormFieldValue("hiddenNumber", "Hidden Number - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getHiddenBoolean() == null) {
			rejectFormFieldValue("hiddenBoolean", "Hidden Boolean - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getCheckBoxBoolean() == null) {
			rejectFormFieldValue("checkBoxBoolean", "Checkbox Boolean - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getRadioButton() == null) {
			rejectFormFieldValue("radioButton", "Radio Number - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors("radioButton") && sampleForm.getRadioButton() == 10) {
			rejectFormFieldValue("radioButton", "Radio Number - 10 Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getName() == null) {
			rejectFormFieldValue("name", "Name - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (sampleForm.getNumber() == null) {
			rejectFormFieldValue("number", "Number - NULL Not Allowed " + databaseTimeFormatted);
		}

	}

}
