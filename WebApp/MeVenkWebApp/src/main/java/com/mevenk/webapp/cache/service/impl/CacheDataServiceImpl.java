/**
 *
 */
package com.mevenk.webapp.cache.service.impl;

import static com.mevenk.webapp.cache.application.ApplicationCache.setSpringConfiguration;
import static com.mevenk.webapp.cache.application.ApplicationCache.setUrisApplication;
import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.config.spring.SpringConfiguration.loadSpringConfiguration;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashMap;
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

import com.mevenk.webapp.cache.dao.CacheDataDao;
import com.mevenk.webapp.cache.service.CacheDataService;
import com.mevenk.webapp.config.spring.SpringConfiguration;
import com.mevenk.webapp.config.spring.messagesource.MessageSourceStaticData;
import com.mevenk.webapp.entity.cache.MessageSource;

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

	@Autowired
	private CacheDataDao cacheDataDao;

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
		loadMessageSourceData();

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

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private void loadMessageSourceData() throws IllegalAccessException {

		Iterable<Integer> messageIds = cacheDataDao.getMessageIds();

		Map<Integer, Map<Integer, String>> messageSourceMasterData = new HashMap<>();

		Map<Integer, String> messages = null;
		Iterable<MessageSource> messageSources = null;

		for (Integer messageId : messageIds) {
			
			messageSources = cacheDataDao.getMessageSource(messageId);
			messages = new HashMap<>();

			for (MessageSource messageSource : messageSources) {
				messages.put(messageSource.getLocaleId(), messageSource.getMessage());
			}

			messageSourceMasterData.put(messageId, messages);

		}

		MessageSourceStaticData.setMessagesMasterData(messageSourceMasterData);

	}

}
