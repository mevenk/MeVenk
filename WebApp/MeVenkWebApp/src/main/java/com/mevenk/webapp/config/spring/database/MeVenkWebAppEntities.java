/**
 *
 */
package com.mevenk.webapp.config.spring.database;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

import java.util.HashSet;
import java.util.Set;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppEntities {

	private MeVenkWebAppEntities() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	protected static final Set<Class<?>> ENTITIES_HIBERNATE = new HashSet<>();

	private static final Set<Class<?>> ENTITIES_COMMON = new HashSet<>();

	static {

		ENTITIES_HIBERNATE.addAll(ENTITIES_COMMON);

	}

}
