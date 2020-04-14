/**
 *
 */
package com.mevenk.jni.library.loader;

import static java.lang.System.loadLibrary;

/**
 * @author venky
 *
 */
public final class SystemLibraryLoader {

	private static final String MEVENK_JNI_LIBRARY_NAME_PREFIX = "MeVenkJNI";

	/* loadPrintHelloLibrary */

	private static final String PRINT_HELLO = "PrintHello";

	/* loadPrintHelloLibrary - END */

	static {
		loadSystemLibrary();
	}

	/**
	 *
	 * @param libraries
	 */
	private static final void loadSystemLibrary(String... libraries) {
		for (String library : libraries) {
			loadLibrary(MEVENK_JNI_LIBRARY_NAME_PREFIX + "_" + library);
		}
	}

	/**
	 *
	 */
	public static final void loadPrintHelloLibrary() {
		loadSystemLibrary(PRINT_HELLO);
	}

}
