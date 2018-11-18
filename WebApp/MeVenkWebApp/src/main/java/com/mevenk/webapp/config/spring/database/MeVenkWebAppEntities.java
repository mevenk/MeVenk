/**
 *
 */
package com.mevenk.webapp.config.spring.database;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

import java.util.HashSet;
import java.util.Set;

import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppEntities {

	private MeVenkWebAppEntities() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 * @return
	 */
	protected static Class<?>[] getAnnotatedClassesEntitiesHibernate() {
		Set<Class<?>> annotatedClassesEntitiesHibernate = new HashSet<>();
		annotatedClassesEntitiesHibernate.addAll(populateEntitiesCache());
		return annotatedClassesEntitiesHibernate.toArray(new Class<?>[annotatedClassesEntitiesHibernate.size()]);
	}

	/**
	 *
	 * @return
	 */
	private static Set<Class<?>> populateEntitiesCache() {
		Set<Class<?>> entitiesCache = new HashSet<>();
		entitiesCache.add(MessageSource.class);
		return entitiesCache;
	}

}
