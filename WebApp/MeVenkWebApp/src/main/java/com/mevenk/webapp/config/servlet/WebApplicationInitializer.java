/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import javax.servlet.ServletConfig;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * @author venky
 *
 */
public final class WebApplicationInitializer {

	private static final Logger LOG = getLogger(WebApplicationInitializer.class);

	private WebApplicationInitializer() {

	}

	public static void runInitialActivities(ServletConfig servletConfig) {

		LOG.log(CONFIG, "Application initiated|" + servletConfig);

		ApplicationContext applicationContext = getRequiredWebApplicationContext(servletConfig.getServletContext());

		loadCacheMasterData(applicationContext);

	}

	private static void loadCacheMasterData(ApplicationContext applicationContext) {
		LOG.log(CONFIG, "Spring Application Context:" + applicationContext);

	}

}
