/**
 *
 */
package com.mevenk.webapp.config.spring.interceptor.login;

import static com.mevenk.webapp.util.HTTPUtil.getUserFromSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mevenk.webapp.to.user.UserTO;

/**
 * @author venky
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestURI = request.getRequestURI();
		boolean isLoginRequest = requestURI.endsWith("/login.mevenk") || requestURI.endsWith("/loginUser.mevenk");
		if (isLoginRequest) {
			return super.preHandle(request, response, handler);
		}

		UserTO userFromSession = getUserFromSession(request);
		if (userFromSession == null || userFromSession.getUserIdentificationNumber() == null) {
			LOG.info("User not available in Session. Redirecting to Login page");
			response.sendRedirect("login.mevenk");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
