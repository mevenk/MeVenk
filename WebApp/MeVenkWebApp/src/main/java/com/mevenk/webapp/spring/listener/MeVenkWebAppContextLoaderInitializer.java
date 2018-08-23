/**
 *
 */
package com.mevenk.webapp.spring.listener;

import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;

/**
 * @author venky
 *
 */
@WebListener("MeVenkWebAppContextLoaderInitializer")
@Component
public class MeVenkWebAppContextLoaderInitializer extends ContextLoaderListener {

	private static final Logger LOG = getLogger(MeVenkWebAppContextLoaderInitializer.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		LOG.info("Context Initialized:" + event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		LOG.info("Context Destroyed:" + event);
	}

}
