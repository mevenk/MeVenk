/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.config.spring.properties.PropertiesLoaderHelper.loadPropertiesDependantStaticData;
import static com.mevenk.webapp.util.DateTimeUtil.getStartDateEndDateDifferences;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import java.util.Date;

import javax.servlet.ServletConfig;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.mevenk.webapp.cache.service.CacheDataService;

/**
 * @author venky
 *
 */
public final class WebApplicationInitializer {

	private static final Logger LOG = getLogger(WebApplicationInitializer.class);

	private WebApplicationInitializer() {

	}

	public static void runInitialActivities(ServletConfig servletConfig) throws Exception {

		LOG.log(CONFIG, "Application initiated|" + servletConfig);

		loadStaticData();

		ApplicationContext applicationContext = getRequiredWebApplicationContext(servletConfig.getServletContext());

		LOG.log(CONFIG, "Spring ApplicationContext[WebApplicationContext]|" + applicationContext);

		loadCacheMasterData(applicationContext);

	}

	/**
	 *
	 */
	private static void loadStaticData() {

		loadPropertiesDependantStaticData();

	}

	private static void loadCacheMasterData(ApplicationContext applicationContext) throws Exception {
		LOG.log(CONFIG, "Spring Application Context:" + applicationContext);

		Date dateCacheLoadStart = new Date();
		LOG.log(CONFIG, "Starting Cache Load@" + dateCacheLoadStart);

		applicationContext.getBean(CacheDataService.class).loadCacheMasterData();

		Date dateCacheLoadComplete = new Date();
		LOG.log(CONFIG, "Cache Load Complete@" + dateCacheLoadComplete);

		LOG.log(CONFIG,
				"Cache Load Time : " + getStartDateEndDateDifferences(dateCacheLoadStart, dateCacheLoadComplete));

	}

}
