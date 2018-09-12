package com.mevenk.webapp.config.spring.database.cache;

import static org.apache.logging.log4j.LogManager.getLogger;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

public class CacheManagerConfig {

	private static final Logger LOG = getLogger(CacheManagerConfig.class);
	private static final int MAX_ELEMENTS_IN_MEMORY = 1000;
	private static final int DISK_POOL_BUFFER_SIZE_IN_MB = 5;

	private static final String HIBERNATE_CACHE_1 = "HIBERNATE_CACHE_1";
	private static final String HIBERNATE_CACHE_2 = "HIBERNATE_CACHE_2";

	public CacheManagerConfig() {
		LOG.debug("Creating Custom Caches");
		// Create a singleton CacheManager using defaults
		CacheManager manager = CacheManager.getInstance();

		Cache customCapabilityNameCacheRegion = createCache(HIBERNATE_CACHE_1);
		Cache customCapabilityIdCacheRegion = createCache(HIBERNATE_CACHE_2);

		LOG.debug("Adding Caches");
		manager.removeCache(HIBERNATE_CACHE_1);
		manager.removeCache(HIBERNATE_CACHE_2);
		manager.addCache(customCapabilityNameCacheRegion);
		manager.addCache(customCapabilityIdCacheRegion);

		String[] strCacheNames = manager.getCacheNames();
		for (String cache : strCacheNames) {
			LOG.debug("Caches Present : {} " + cache);
		}

	}

	private Cache createCache(String cacheName) {
		Cache cache = new Cache(new CacheConfiguration(cacheName, MAX_ELEMENTS_IN_MEMORY)
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU).eternal(true)
				.maxElementsInMemory(MAX_ELEMENTS_IN_MEMORY).timeToIdleSeconds(0).timeToLiveSeconds(0)
				.diskSpoolBufferSizeMB(DISK_POOL_BUFFER_SIZE_IN_MB).overflowToDisk(false)

		);

		return cache;

	}

	@PreDestroy
	public static void cleanUp() {
		CacheManager.getInstance().shutdown();
	}

	public void clearCache() {

		CacheManager manager = CacheManager.getInstance();
		manager.getCache(HIBERNATE_CACHE_1).removeAll();
		manager.getCache(HIBERNATE_CACHE_2).removeAll();

		LOG.info("Second level cache cleared for Custom Capabilities. ");

	}

}