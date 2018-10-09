/**
 *
 */
package com.mevenk.webapp.cache;

/**
 * @author venky
 *
 */
public abstract class CacheUtil {

	private static final IllegalAccessException ILLEGAL_ACCESS_EXCEPTION_SPRING_CONFIGURATION_ALREADY_LOADED = new IllegalAccessException(
			"CACHE_OBJECT_ALREADY_LOADED");

	/**
	 *
	 */
	private CacheUtil() {

	}

	public static void preValidateCacheObject(Object object) throws Exception {
		if (object != null) {
			throw ILLEGAL_ACCESS_EXCEPTION_SPRING_CONFIGURATION_ALREADY_LOADED;
		}
	}

}
