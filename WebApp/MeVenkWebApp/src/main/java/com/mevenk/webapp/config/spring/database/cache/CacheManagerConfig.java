package com.mevenk.webapp.config.spring.database.cache;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.CONFIG;
import static net.sf.ehcache.store.MemoryStoreEvictionPolicy.LFU;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

public class CacheManagerConfig {

	private static final Logger LOG = getLogger(CacheManagerConfig.class);

	private static final String CACHE_HIBERNATE = "CACHE_HIBERNATE";
	private static final String CACHE_STATIC_DATA = "CACHE_STATIC_DATA";

	private static final int MAX_ELEMENTS_IN_MEMORY = 1000;
	private static final int DISK_POOL_BUFFER_SIZE_IN_MB = 5;

	/**
	 *
	 */
	public CacheManagerConfig() {

		LOG.log(CONFIG, "Creating Custom Caches");
		// Create a singleton CacheManager using defaults

		LOG.log(CONFIG, "Adding Caches");
		addCacheHibernate();
		addCacheStaticData();

		CacheManager cacheManager = getCacheManagerInstance();

		String[] strCacheNames = cacheManager.getCacheNames();
		for (String cache : strCacheNames) {
			LOG.log(CONFIG, "Caches Present : {} " + cache);
		}

	}

	/**
	 *
	 * @return
	 */
	private static CacheManager getCacheManagerInstance() {
		return CacheManager.getInstance();
	}

	/**
	 *
	 * @param cacheName
	 * @return
	 */
	private static Cache createCache(String cacheName) {

		return new Cache(new CacheConfiguration(cacheName, MAX_ELEMENTS_IN_MEMORY).memoryStoreEvictionPolicy(LFU)
				.eternal(true).maxElementsInMemory(MAX_ELEMENTS_IN_MEMORY).timeToIdleSeconds(0).timeToLiveSeconds(0)
				.diskSpoolBufferSizeMB(DISK_POOL_BUFFER_SIZE_IN_MB).overflowToDisk(false));

	}

	/**
	 *
	 * @param cache
	 */
	private static void addCache(Cache cache) {

		CacheManager cacheManager = getCacheManagerInstance();
		cacheManager.removeCache(cache.getName());
		cacheManager.addCache(cache);
	}

	/**
	 *
	 */
	private static void addCacheHibernate() {
		addCache(createCache(CACHE_HIBERNATE));
	}

	/**
	 *
	 */
	private static void addCacheStaticData() {
		addCache(createCache(CACHE_STATIC_DATA));
	}

	/**
	 *
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName) {
		return getCacheManagerInstance().getCache(cacheName);
	}

	/**
	 *
	 * @return
	 */
	public static Cache getCacheHibernate() {
		return getCache(CACHE_HIBERNATE);
	}

	/**
	 *
	 * @return
	 */
	public static Cache getCacheStaticData() {
		return getCache(CACHE_STATIC_DATA);
	}

	/**
	 *
	 */
	public void clearCache() {

		CacheManager cacheManager = getCacheManagerInstance();
		String[] strCacheNames = cacheManager.getCacheNames();
		for (String cacheName : strCacheNames) {
			getCache(cacheName).removeAll();
		}

		LOG.info("Second level cache cleared");

	}

	/**
	 *
	 */
	@PreDestroy
	public static void cleanUp() {
		getCacheManagerInstance().shutdown();
	}

}