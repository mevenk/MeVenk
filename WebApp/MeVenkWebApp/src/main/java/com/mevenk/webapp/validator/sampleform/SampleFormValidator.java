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

	private static final String FIELD_NUMBER = "number";
	private static final String FIELD_NAME = "name";
	private static final String FIELD_RADIO_BUTTON = "radioButton";
	private static final String FIELD_CHECK_BOX_BOOLEAN = "checkBoxBoolean";
	private static final String FIELD_HIDDEN_BOOLEAN = "hiddenBoolean";
	private static final String FIELD_HIDDEN_NUMBER = "hiddenNumber";
	private static final String FIELD_HIDDEN_STRING = "hiddenString";

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

		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_HIDDEN_STRING, 102);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_HIDDEN_NUMBER, 102);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_HIDDEN_BOOLEAN, 102);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_CHECK_BOX_BOOLEAN, 101);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_RADIO_BUTTON, 101);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_NAME, 101);
		rejectFormFieldValueIfEmptyOrWhitespace(FIELD_NUMBER, 101);

		if (!hasFormFieldErrors(FIELD_HIDDEN_STRING) && isEmptyOrNull(sampleForm.getHiddenString())) {
			rejectFormFieldValue(FIELD_HIDDEN_STRING, "Hidden String - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_HIDDEN_NUMBER) && isEmptyOrNull(sampleForm.getHiddenNumber())) {
			rejectFormFieldValue(FIELD_HIDDEN_NUMBER, "Hidden Number - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_HIDDEN_BOOLEAN) && isEmptyOrNull(sampleForm.getHiddenBoolean())) {
			rejectFormFieldValue(FIELD_HIDDEN_BOOLEAN, "Hidden Boolean - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_CHECK_BOX_BOOLEAN) && isEmptyOrNull(sampleForm.getCheckBoxBoolean())) {
			rejectFormFieldValue(FIELD_CHECK_BOX_BOOLEAN,
					"Checkbox Boolean - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_RADIO_BUTTON) && isEmptyOrNull(sampleForm.getRadioButton())) {
			rejectFormFieldValue(FIELD_RADIO_BUTTON, "Radio Number - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_RADIO_BUTTON) && sampleForm.getRadioButton() == 10) {
			rejectFormFieldValue(FIELD_RADIO_BUTTON, "Radio Number - 10 Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_NAME) && isEmptyOrNull(sampleForm.getName())) {
			rejectFormFieldValue(FIELD_NAME, "Name - NULL Not Allowed " + databaseTimeFormatted);
		}

		if (!hasFormFieldErrors(FIELD_NUMBER) && isEmptyOrNull(sampleForm.getNumber())) {
			rejectFormFieldValue(FIELD_NUMBER, "Number - NULL Not Allowed " + databaseTimeFormatted);
		}

	}

}
