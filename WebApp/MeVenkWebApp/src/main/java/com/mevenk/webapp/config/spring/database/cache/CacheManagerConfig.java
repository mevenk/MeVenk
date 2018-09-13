package com.mevenk.webapp.config.spring.database.cache;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static net.sf.ehcache.CacheManager.getInstance;
import static net.sf.ehcache.store.MemoryStoreEvictionPolicy.LFU;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

public class CacheManagerConfig {

	private static final Logger LOG = getLogger(CacheManagerConfig.class);

	public static final String CACHE_HIBERNATE = "CACHE_HIBERNATE";
	public static final String CACHE_MEVENK_WEBAPP = "CACHE_MEVENK_WEBAPP";

	private static final int MAX_ELEMENTS_IN_MEMORY = 1000;
	private static final int DISK_POOL_BUFFER_SIZE_IN_MB = 5;

	public CacheManagerConfig() {
		LOG.log(CONFIG, "Creating Custom Caches");
		// Create a singleton CacheManager using defaults
		CacheManager manager = getInstance();

		Cache cacheHibernate = createCache(CACHE_HIBERNATE);
		Cache cacheMeVenkWebApp = createCache(CACHE_MEVENK_WEBAPP);

		LOG.log(CONFIG, "Adding Caches");
		manager.removeCache(CACHE_HIBERNATE);
		manager.removeCache(CACHE_MEVENK_WEBAPP);
		manager.addCache(cacheHibernate);
		manager.addCache(cacheMeVenkWebApp);

		String[] strCacheNames = manager.getCacheNames();
		for (String cache : strCacheNames) {
			LOG.log(CONFIG, "Caches Present : {} " + cache);
		}

	}

	private Cache createCache(String cacheName) {
		Cache cache = new Cache(new CacheConfiguration(cacheName, MAX_ELEMENTS_IN_MEMORY).memoryStoreEvictionPolicy(LFU)
				.eternal(true).maxElementsInMemory(MAX_ELEMENTS_IN_MEMORY).timeToIdleSeconds(0).timeToLiveSeconds(0)
				.diskSpoolBufferSizeMB(DISK_POOL_BUFFER_SIZE_IN_MB).overflowToDisk(false)

		);

		return cache;

	}

	@PreDestroy
	public static void cleanUp() {
		getInstance().shutdown();
	}

	public void clearCache() {

		CacheManager manager = getInstance();
		manager.getCache(CACHE_HIBERNATE).removeAll();
		manager.getCache(CACHE_MEVENK_WEBAPP).removeAll();

		LOG.info("Second level cache cleared");

	}

}