/**
 *
 */
package com.mevenk.webapp.entity;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;

/**
 * @author venky
 *
 */
public final class EntitiesMetadata {

	public static final String TABLE_NAME_SEQUENCE = "SEQUENCE";
	public static final String COLUMN_NAME_SEQUENCE_NAME = "SEQUENCE_NAME";
	public static final String COLUMN_NAME_SEQUENCE_VALUE = "SEQUENCE_VALUE";

	public static final String SEQUENCE_NAME_APPLICATION_EXCEPTION_ID = "APPLICATION_EXCEPTION_ID";
	public static final String SEQUENCE_NAME_USER_IDENTIFICATION_NUMBER = "USER_IDENTIFICATION_NUMBER";

	/**
	 * @throws IllegalAccessException
	 *
	 */
	private EntitiesMetadata() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

}
