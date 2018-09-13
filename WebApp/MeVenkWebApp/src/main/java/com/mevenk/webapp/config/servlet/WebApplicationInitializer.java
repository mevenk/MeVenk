/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import java.util.Date;

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

	public static void runInitialActivities(ServletConfig servletConfig) throws InterruptedException {

		LOG.log(CONFIG, "Application initiated|" + servletConfig);

		ApplicationContext applicationContext = getRequiredWebApplicationContext(servletConfig.getServletContext());

		LOG.log(CONFIG, "Spring ApplicationContext[WebApplicationContext]|" + applicationContext);

		loadCacheMasterData(applicationContext);

	}

	private static void loadCacheMasterData(ApplicationContext applicationContext) throws InterruptedException {
		LOG.log(CONFIG, "Spring Application Context:" + applicationContext);

		int loopCounter = 5;
		while (loopCounter > -1) {
			System.out.println("Sleeping " + new Date());
			Thread.sleep(2000);
			System.out.println("Remaining loops:" + --loopCounter);
		}

	}

}
