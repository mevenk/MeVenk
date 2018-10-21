/**
 *
 */
package com.mevenk.webapp.util.http.client;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.LINE_SEPARATOR;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.SPACE_AROUND_DOUBLE_COLUN;
import static eu.bitwalker.useragentutils.UserAgent.parseUserAgentString;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppClientUtil {

	private static final Logger LOG = getLogger(MeVenkWebAppClientUtil.class);

	/**
	 * @throws IllegalAccessException
	 *
	 */
	private MeVenkWebAppClientUtil() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @param httpServletRequest
	 */
	public static void logClientDetails(HttpServletRequest httpServletRequest) {

		UserAgent userAgent = parseUserAgentString(httpServletRequest.getHeader("User-Agent"));

		LOG.info("Client{}{}{}{}{}{}", SPACE_AROUND_DOUBLE_COLUN, httpServletRequest.getRemoteHost(),
				SPACE_AROUND_DOUBLE_COLUN, userAgent.getId(), SPACE_AROUND_DOUBLE_COLUN, userAgent.toString());

		StringBuilder clientDetailsStringBuffer = new StringBuilder();

		Enumeration<?> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String paramName = (String) headerNames.nextElement();
			String paramValue = httpServletRequest.getHeader(paramName);
			clientDetailsStringBuffer.append(paramName + SPACE_AROUND_DOUBLE_COLUN + paramValue + LINE_SEPARATOR);
		}

		LOG.info("Client Details from Headers...{}{}", LINE_SEPARATOR, clientDetailsStringBuffer.toString());

	}

}
