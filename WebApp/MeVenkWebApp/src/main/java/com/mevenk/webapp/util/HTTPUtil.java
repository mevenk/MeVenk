/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author venky
 *
 */
public final class HTTPUtil {

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private HTTPUtil() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @return
	 */
	public static HttpServletRequest getHTTPRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 *
	 * @return
	 */
	public static HttpSession getHTTPSession(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getSession(false);
	}

	/**
	 *
	 * @return
	 */
	public static HttpSession getHTTPSession() {
		return getHTTPSession(getHTTPRequest());
	}

	/**
	 *
	 * @param httpSession
	 * @return
	 */
	public static String getHTTPSessionId(HttpSession httpSession) {
		if (httpSession == null) {
			return null;
		}
		return httpSession.getId();
	}

	/**
	 *
	 * @param httpServletRequest
	 * @return
	 */
	public static String getHTTPSessionId(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = getHTTPSession(httpServletRequest);
		if (httpSession == null) {
			return null;
		}
		return getHTTPSessionId(httpSession);
	}

	/**
	 *
	 * @return
	 */
	public static String getHTTPSessionId() {
		HttpSession httpSession = getHTTPSession();
		if (httpSession == null) {
			return null;
		}
		return getHTTPSessionId(httpSession);
	}
}
