/**
 *
 */
package com.mevenk.webapp.validator.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.mevenk.webapp.service.login.LoginService;
import com.mevenk.webapp.to.user.UserLoginTO;
import com.mevenk.webapp.util.MeVenkWebAppStringUtil;
import com.mevenk.webapp.validator.MeVenkWebAppValidator;

/**
 * @author venky
 *
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LoginUserValidator extends MeVenkWebAppValidator {

	@Autowired
	private LoginService loginService;

	/**
	 * 
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return UserLoginTO.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		executePreValidationActivities(errors);
		
		UserLoginTO userLoginTO = (UserLoginTO) target;

		if (MeVenkWebAppStringUtil.isAnyStringEmptyOrNull(userLoginTO.getUid())) {
			rejectFormFieldValue("uid", "UID Required");
		}

		if (MeVenkWebAppStringUtil.isAnyStringEmptyOrNull(userLoginTO.getPassword())) {
			rejectFormFieldValue("password", "Password Required");
		}

		if (hasErrors()) {
			resetAttributes(userLoginTO);
			return;
		}

		String uid = userLoginTO.getUid();
		if (!loginService.isValidUId(uid)) {
			rejectFormFieldValue("uid", "UID Invalid");
		}

		if (!hasErrors() && !loginService.isValidPassword(uid, userLoginTO.getPassword())) {
			rejectFormFieldValue("password", "Invalid Password");

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
