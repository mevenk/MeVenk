/**
 *
 */
package com.mevenk.webapp.config.spring.database;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.mevenk.webapp.entity.audit.ApplicationException;
import com.mevenk.webapp.entity.cache.MessageSource;
import com.mevenk.webapp.entity.user.UserPasswordVW;
import com.mevenk.webapp.entity.user.UserVW;

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
		annotatedClassesEntitiesHibernate.addAll(populateEntitiesAuth());
		annotatedClassesEntitiesHibernate.addAll(populateEntitiesCache());
		annotatedClassesEntitiesHibernate.addAll(populateEntitiesAudit());
		return annotatedClassesEntitiesHibernate.toArray(new Class<?>[annotatedClassesEntitiesHibernate.size()]);
	}

	/**
	 *
	 * @return
	 */
	private static Collection<? extends Class<?>> populateEntitiesAuth() {
		Set<Class<?>> entitiesAuth = new HashSet<>();
		entitiesAuth.add(UserVW.class);
		entitiesAuth.add(UserPasswordVW.class);
		return entitiesAuth;
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

	/**
	 *
	 * @return
	 */
	private static Set<Class<?>> populateEntitiesAudit() {
		Set<Class<?>> entitiesAudit = new HashSet<>();
		entitiesAudit.add(ApplicationException.class);
		return entitiesAudit;
	}

}
