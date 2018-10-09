/**
 *
 */
package com.mevenk.webapp.cache.application;

import static com.mevenk.webapp.cache.CacheUtil.preValidateCacheObject;

import java.util.Set;

import com.mevenk.webapp.config.spring.SpringConfiguration;

/**
 * @author venky
 *
 */
public abstract class ApplicationCache {

	/* SpringConfiguration */

	private static SpringConfiguration springConfiguration;

	/**
	 * @return the springConfiguration
	 */
	public static final SpringConfiguration getSpringConfiguration() {
		return springConfiguration;
	}

	/**
	 * @param springConfiguration the springConfiguration to set
	 * @throws Exception
	 */
	public static final void setSpringConfiguration(SpringConfiguration springConfiguration) throws Exception {
		preValidateCacheObject(ApplicationCache.springConfiguration);
		ApplicationCache.springConfiguration = springConfiguration;
	}

	/* SpringConfiguration - END */

	/* urisApplication */

	private static Set<String> urisApplication;

	/**
	 * @return the urisApplication
	 */
	public static final Set<String> getUrisApplication() {
		return urisApplication;
	}

	/**
	 * @param urisApplication the urisApplication to set
	 * @throws Exception
	 */
	public static final void setUrisApplication(Set<String> urisApplication) throws Exception {
		preValidateCacheObject(ApplicationCache.urisApplication);
		ApplicationCache.urisApplication = urisApplication;
	}

	/* urisApplication - END */

}
