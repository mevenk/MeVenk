package com.mevenk.webapp.controller.login;

import static com.mevenk.webapp.util.HTTPUtil.SESSION_ATTRIBUTE_NAME_USER;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mevenk.webapp.exception.login.LoginUserException;
import com.mevenk.webapp.service.login.LoginService;
import com.mevenk.webapp.to.user.UserLoginTO;
import com.mevenk.webapp.to.user.UserTO;
import com.mevenk.webapp.util.HTTPUtil;
import com.mevenk.webapp.validator.login.LoginUserValidator;

/**
 * @author venky
 *
 */
@Controller
@SessionAttributes(SESSION_ATTRIBUTE_NAME_USER)
public class LoginController {

	private static final Logger LOG = getLogger(LoginController.class);

	private static final String VIEW_NAME_LOGIN = "login";
	
	@Autowired
	private LoginUserValidator loginUserValidator;
	
	@Autowired
	private LoginService loginService;

	@ModelAttribute(SESSION_ATTRIBUTE_NAME_USER)
	public UserTO setUpUserTO() {
		return new UserTO();
	}

	@GetMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, SessionStatus sessionStatus,
			@RequestParam(required = false, defaultValue = "false") boolean isLogoutRedirect) {

		LOG.info("isLogoutRedirect?", isLogoutRedirect);
		sessionStatus.setComplete();
		ModelAndView modelAndViewLoginPage = new ModelAndView(VIEW_NAME_LOGIN);
		modelAndViewLoginPage.addObject("userLogin", new UserLoginTO());
		if (isLogoutRedirect) {
			modelAndViewLoginPage.addObject("loginStatus", "Logout Successful. Login Again!!");
		}
		return modelAndViewLoginPage;
	}

	@PostMapping(value = "/loginUser")
	public ModelAndView loginUser(@ModelAttribute("userLogin") UserLoginTO userLoginTO, BindingResult bindingResult,
			@ModelAttribute(SESSION_ATTRIBUTE_NAME_USER) UserTO userTO, HttpServletRequest request,
			HttpServletResponse response) throws LoginUserException {

		try {
			if (loginUserValidator.hasErrors(bindingResult, userLoginTO)) {
				ModelAndView modelAndViewLoginUserValidatorError = new ModelAndView();
				modelAndViewLoginUserValidatorError.addObject("userLogin", userLoginTO);
				modelAndViewLoginUserValidatorError.addObject("loginStatus", "Login Fail!!");
				modelAndViewLoginUserValidatorError.setViewName(VIEW_NAME_LOGIN);
				return modelAndViewLoginUserValidatorError;
			}

			userTO = loginService.loginUser(userLoginTO);
			userTO = HTTPUtil.addUserToSession(userTO, request);

			return new ModelAndView("redirect:./welcome.mevenk");
		} catch (Exception exception) {
			LOG.warn("Login User Error", exception);
			userLoginTO.setPassword("PASSWORD_RESET");
			LOG.info("Password has been RESET");
			throw new LoginUserException(exception, userLoginTO, bindingResult);
		}
	}

	@GetMapping(value = "/logoutUser")
	public ModelAndView logoutUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute(SESSION_ATTRIBUTE_NAME_USER) UserTO userTo, SessionStatus sessionStatus) {

		LOG.info("Logout User:", userTo);
		sessionStatus.setComplete();
		request.getSession(false).invalidate();
		return new ModelAndView("redirect:./login.mevenk?isLogoutRedirect=true");
	}

}
