/**
 *
 */
package com.mevenk.webapp.config.servlet;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static com.mevenk.webapp.util.DateTimeUtil.getStartDateEndDateDifferences;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.web.context.support.WebApplicationContextUtils.getRequiredWebApplicationContext;

import java.util.Date;

import javax.servlet.ServletConfig;

import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mevenk.webapp.cache.service.CacheDataService;
import com.mevenk.webapp.config.spring.database.MeVenkDatabaseConfiguration;

/**
 * @author venky
 *
 */
public final class WebApplicationInitializer {

	private static final Logger LOG = getLogger(WebApplicationInitializer.class);

	private WebApplicationInitializer() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	public static void runInitialActivities(ServletConfig servletConfig) throws Exception {

		LOG.log(CONFIG, "Application initiated|{}", servletConfig);

		ApplicationContext applicationContext = getRequiredWebApplicationContext(servletConfig.getServletContext());

		LOG.log(CONFIG, "Spring ApplicationContext[WebApplicationContext]|{}", applicationContext);

		loadCacheMasterData(applicationContext);

	}

	private static void loadCacheMasterData(ApplicationContext applicationContext) throws Exception {
		LOG.log(CONFIG, "Spring Application Context:{}", applicationContext);

		Date dateCacheLoadStart = new Date();
		LOG.log(CONFIG, "Starting Cache Load@{}", dateCacheLoadStart);

		PlatformTransactionManager platformTransactionManagerCacheLoad = null;
		TransactionStatus transactionStatusCacheLoad = null;

		try {
			platformTransactionManagerCacheLoad = (PlatformTransactionManager) applicationContext
					.getBean(MeVenkDatabaseConfiguration.BEAN_NAME_TRANSACTION_MANAGER);
			DefaultTransactionDefinition defaultTransactionDefinitionCacheDataLoad = new DefaultTransactionDefinition();
			defaultTransactionDefinitionCacheDataLoad.setName("CACHE_LOAD");
			defaultTransactionDefinitionCacheDataLoad
					.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
			defaultTransactionDefinitionCacheDataLoad.setReadOnly(true);

			transactionStatusCacheLoad = platformTransactionManagerCacheLoad
					.getTransaction(defaultTransactionDefinitionCacheDataLoad);

			LOG.log(CONFIG, "Transaction created for Cache load:{}", transactionStatusCacheLoad);
			LOG.log(CONFIG, "Is new Transaction for Cache load?{}", transactionStatusCacheLoad.isNewTransaction());

			applicationContext.getBean(CacheDataService.class).loadCacheMasterData();

			platformTransactionManagerCacheLoad.commit(transactionStatusCacheLoad);
			LOG.log(CONFIG, "Is Transaction completed?{}", transactionStatusCacheLoad.isCompleted());

		} catch (Exception exception) {
			LOG.error("Error loading Cache from DB {}", exception);
			if (platformTransactionManagerCacheLoad != null) {
				platformTransactionManagerCacheLoad.rollback(transactionStatusCacheLoad);
			}

			throw exception;
		}

		Date dateCacheLoadComplete = new Date();
		LOG.log(CONFIG, "Cache Load Complete@{}", dateCacheLoadComplete);

		LOG.log(CONFIG, "Cache Load Time : {}",
				getStartDateEndDateDifferences(dateCacheLoadStart, dateCacheLoadComplete));

	}

}
