/**
 *
 */
package com.mevenk.webapp.cache.service.impl;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.cache.application.ApplicationCache.setSpringConfiguration;
import static com.mevenk.webapp.cache.application.ApplicationCache.setUrisApplication;
import static com.mevenk.webapp.config.spring.SpringConfiguration.loadSpringConfiguration;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.mevenk.webapp.cache.service.CacheDataService;
import com.mevenk.webapp.config.spring.SpringConfiguration;

/**
 * @author venky
 *
 */
@Service
public class CacheDataServiceImpl implements CacheDataService {

	private static final Logger LOG = getLogger(CacheDataServiceImpl.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mevenk.webapp.cache.service.CacheDataService#loadCacheMasterData()
	 */
	@Override
	public void loadCacheMasterData() throws Exception {
		loadApplicationCacheData();
		loadAuthorizationCacheData();

	}

	private void loadApplicationCacheData() throws Exception {
		loadConfigurationCacheData();
	}

	private void loadAuthorizationCacheData() {
		// TODO Auto-generated method stub

	}

	private void loadConfigurationCacheData() throws Exception {

		SpringConfiguration springConfiguration = loadSpringConfiguration(applicationContext,
				requestMappingHandlerMapping);
		setSpringConfiguration(springConfiguration);
		LOG.log(CONFIG, "Spring Configuration:" + springConfiguration);

		loadHandlerMethodsCacheData();

	}

	/**
	 * @throws Exception
	 *
	 */
	private void loadHandlerMethodsCacheData() throws Exception {

		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

		Set<String> patterns = new HashSet<>();

		for (RequestMappingInfo requestMappingInfo : handlerMethods.keySet()) {
			patterns.addAll(requestMappingInfo.getPatternsCondition().getPatterns());

		}

		setUrisApplication(patterns);

		LOG.log(CONFIG, "Request Mappings:" + patterns);
	}

}
