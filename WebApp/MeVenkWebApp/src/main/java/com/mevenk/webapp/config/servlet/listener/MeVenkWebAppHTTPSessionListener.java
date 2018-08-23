/**
 *
 */
package com.mevenk.webapp.config.servlet.listener;

import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.Logger;
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
	public void sessionCreated(HttpSessionEvent se) {
		LOG.info("HTTP Session Created:" + se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		LOG.info("HTTP Session Destroyed:" + se.getSession());
	}

}
