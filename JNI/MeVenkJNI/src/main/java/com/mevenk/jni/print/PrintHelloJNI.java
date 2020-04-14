/**
 *
 */
package com.mevenk.jni.print;

import static com.mevenk.jni.library.loader.SystemLibraryLoader.loadPrintHelloLibrary;

/**
 * @author venky
 *
 */
public class PrintHelloJNI {

	static {
		loadPrintHelloLibrary();
	}

	private static native void printHello();

	public static void main(String[] args) {
		printHello();
	}

}
