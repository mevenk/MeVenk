/**
 * 
 */
package org.mevenk.webservices;

import java.util.LinkedHashSet;
import java.util.Set;

import org.mevenk.webservices.util.MeVenkWebServicesUtil;

/**
 * @author vkolisetty
 *
 */
final class ApplicationPreRequisitesRunner {

	private static final Set<String> PRE_LOAD_CLASSES = new LinkedHashSet<String>();

	static {
		try {
			runApplicationPreRequisites();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private ApplicationPreRequisitesRunner() throws Throwable {

		// Prevent instantiation
		MeVenkWebServicesUtil.throwExceptionInstantiationNotAllowed(ApplicationPreRequisitesRunner.class);

	}

	/**
	 * 
	 * @throws Throwable
	 */
	private static void runApplicationPreRequisites() throws Throwable {

		preLoadRequiredClasses();

	}

	/**
	 * 
	 * @throws Throwable
	 */
	private static void preLoadRequiredClasses() throws Throwable {

		addRequiredClasses();

		Class<?> classLoaded;
		for (String claszz : PRE_LOAD_CLASSES) {

			classLoaded = Class.forName(claszz);

			System.out.println("Class loaded: " + classLoaded);

		}

	}

	/**
	 * 
	 */
	private static void addRequiredClasses() {

		System.out.println("Adding required classes");

		PRE_LOAD_CLASSES.add("org.mevenk.webservices.logger.LoggerDirectory");

		System.out.println("Classes to be loaded: " + PRE_LOAD_CLASSES);

	}

}
