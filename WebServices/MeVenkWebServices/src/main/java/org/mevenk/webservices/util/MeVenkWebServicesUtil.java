/**\
 * 
 */
package org.mevenk.webservices.util;

/**
 * @author vkolisetty
 *
 */
public final class MeVenkWebServicesUtil {

	private static final NullPointerException NULL_POINTER_EXCEPTION = new NullPointerException();

	/**
	 * 
	 * @throws Throwable
	 */
	private MeVenkWebServicesUtil() throws Throwable {
		throwExceptionInstantiationNotAllowed(MeVenkWebServicesUtil.class);
	}

	/**
	 * 
	 * @param classInstantiationNotAllowed
	 * @throws Throwable
	 */
	public static final void throwExceptionInstantiationNotAllowed(Class<?> classInstantiationNotAllowed)
			throws Throwable {
		throw new IllegalAccessException("Instantiation not allowed: " + classInstantiationNotAllowed);
	}

	/**
	 * 
	 * @param throwException
	 * @param objects
	 * @return
	 */
	public static final boolean validateNotNull(boolean throwException, Object... objects) {
		if (objects == null) {
			throw NULL_POINTER_EXCEPTION;
		}
		for (Object obj : objects) {
			if (obj == null) {
				if (throwException) {
					throw NULL_POINTER_EXCEPTION;
				} else {
					return false;
				}
			}

		}
		return true;
	}

}
