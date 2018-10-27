/**
 *
 */
package com.mevenk.webapp.config.servlet.listener;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.VERTICAL_BAR;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

/**
 * @author venky
 *
 */

@WebListener("MeVenkWebAppHTTPSessionListener")
@Component
public class MeVenkWebAppHTTPSessionListener implements HttpSessionListener {

	private static final Logger LOG = getLogger(MeVenkWebAppHTTPSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		LOG.info("HTTP Session Created:" + session.getId() + VERTICAL_BAR + new Date(session.getCreationTime())
				+ VERTICAL_BAR + session.getMaxInactiveInterval() + VERTICAL_BAR + session);

		populateRequiredSessionAttributes(session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		LOG.info("HTTP Session Destroyed:" + session.getId() + VERTICAL_BAR + new Date(session.getCreationTime())
				+ VERTICAL_BAR + new Date(session.getLastAccessedTime()) + VERTICAL_BAR
				+ session.getMaxInactiveInterval() + VERTICAL_BAR + session);
	}

	/**
	 *
	 * @param session
	 */
	private void populateRequiredSessionAttributes(HttpSession session) {

		populateThreadContextRequiredSessionAttributes(session);

		Enumeration<String> attributeNamesHTTPSession = session.getAttributeNames();
		String sessionAttributeName = null;
		while (attributeNamesHTTPSession.hasMoreElements()) {
			sessionAttributeName = attributeNamesHTTPSession.nextElement();
			LOG.info("HTTP Session attributes created during Session initialization: {}[{}]", sessionAttributeName,
					session.getAttribute(sessionAttributeName));
		}
	}

	/**
	 *
	 * @param session
	 */
	private void populateThreadContextRequiredSessionAttributes(HttpSession session) {
		ThreadContext.put(THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID, session.getId());
	}

}
