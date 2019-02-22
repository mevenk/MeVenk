/**
 *
 */
package com.mevenk.webapp.validator.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

import com.mevenk.webapp.service.login.LoginService;
import com.mevenk.webapp.to.user.UserLoginTO;
import com.mevenk.webapp.util.MeVenkWebAppStringUtil;
import com.mevenk.webapp.validator.MeVenkWebAppValidator;

/**
 * @author venky
 *
 */
public class LoginUserValidator extends MeVenkWebAppValidator {

	private LoginService loginService;

	/**
	 * @param errors
	 * @param form
	 * @param request
	 */
	public LoginUserValidator(Errors errors, Object form, HttpServletRequest request) {
		super(errors, form, request);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		UserLoginTO userLoginTO = (UserLoginTO) target;

		if (MeVenkWebAppStringUtil.isAnyStringEmptyOrNull(userLoginTO.getUid())) {
			errors.rejectValue("uid", null, "UID Required");
		}

		if (MeVenkWebAppStringUtil.isAnyStringEmptyOrNull(userLoginTO.getPassword())) {
			errors.rejectValue("password", null, "Password Required");
		}

		if (hasErrors()) {
			resetAttributes(userLoginTO);
			return;
		}

		loginService = (LoginService) getBean(LoginService.class, null);

		String uid = userLoginTO.getUid();
		if (!loginService.isValidUId(uid)) {
			errors.rejectValue("uid", null, "UID Invalid");
		}

		if (!hasErrors() && !loginService.isValidPassword(uid, userLoginTO.getPassword())) {

			errors.rejectValue("password", null, "Invalid Password");

		}

		resetAttributes(userLoginTO);

	}

	/**
	 * @param userLoginTO
	 */
	private static void resetAttributes(UserLoginTO userLoginTO) {
		userLoginTO.setPassword(null);
	}

}
