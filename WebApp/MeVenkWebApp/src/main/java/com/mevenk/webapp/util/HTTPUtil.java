/**
 *
 */
package com.mevenk.webapp.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mevenk.webapp.to.user.UserTO;

public final class HTTPUtil {

	public static final String SESSION_ATTRIBUTE_NAME_USER = "SESSION_ATTRIBUTE_NAME_USER";

	public static enum SessionAttributes {

		USER(SESSION_ATTRIBUTE_NAME_USER);

		private String attributeName;

		private SessionAttributes(String attributeName) {
			this.attributeName = attributeName;
		}

		public final String getAttributeName() {
			return this.attributeName;
		}
	}

	private HTTPUtil() throws IllegalAccessException {
		throw MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	public static HttpServletRequest getHTTPRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static HttpSession createHTTPSession(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getSession();
	}

	public static HttpSession createHTTPSession() {
		return createHTTPSession(getHTTPRequest());
	}

	public static HttpSession getHTTPSession(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getSession(false);
	}

	public static HttpSession getHTTPSession() {
		return getHTTPSession(getHTTPRequest());
	}

	public static String getHTTPSessionId(HttpSession httpSession) {
		if (httpSession == null) {
			return null;
		}
		return httpSession.getId();
	}

	public static String getHTTPSessionId(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = getHTTPSession(httpServletRequest);
		if (httpSession == null) {
			return null;
		}
		return getHTTPSessionId(httpSession);
	}

	public static String getHTTPSessionId() {
		HttpSession httpSession = getHTTPSession();
		if (httpSession == null) {
			return null;
		}
		return getHTTPSessionId(httpSession);
	}

	public static final Object addSessionAttribute(String name, Serializable serializable, HttpSession session) {
		session.setAttribute(name, serializable);
		return getSessionAttribute(name, session);
	}

	public static final Object addSessionAttribute(String name, Serializable serializable, HttpServletRequest request) {
		return addSessionAttribute(name, serializable, getHTTPSession(request));
	}

	public static final Object addSessionAttribute(String name, Serializable serializable) {
		return addSessionAttribute(name, serializable, getHTTPSession());
	}

	public static final Object getSessionAttribute(String name, HttpSession session) {
		return session.getAttribute(name);
	}

	public static final Object getSessionAttribute(String name, HttpServletRequest request) {
		return getSessionAttribute(name, getHTTPSession(request));
	}

	public static final Object getSessionAttribute(String name) {
		return getSessionAttribute(name, getHTTPSession());
	}

	public static final UserTO addUserToSession(UserTO userTO, HttpServletRequest request) {
		return (UserTO) addSessionAttribute(HTTPUtil.SessionAttributes.USER.getAttributeName(), userTO, request);
	}

	public static final UserTO addUserToSession(UserTO userTO) {
		return addUserToSession(userTO, getHTTPRequest());
	}

	public static final UserTO getUserFromSession(HttpServletRequest request) {
		return (UserTO) getSessionAttribute(HTTPUtil.SessionAttributes.USER.getAttributeName(), request);
	}

	public static final UserTO getUserFromSession() {
		return (UserTO) getUserFromSession(getHTTPRequest());
	}
}
