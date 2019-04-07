/**
 * 
 */
package org.mevenk.utils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author vkolisetty
 *
 */
public abstract class MeVenkUtils {

	private static MeVenkUtilsModule[] modulesEnumValues = MeVenkUtilsModule.values();
	private static LinkedHashMap<Integer, MeVenkUtilsModule> modules = new LinkedHashMap<Integer, MeVenkUtilsModule>(
			modulesEnumValues.length);

	static {

		int modulesSerialNo = 0;
		for (MeVenkUtilsModule module : modulesEnumValues) {
			modules.put(++modulesSerialNo, module);
		}

	}

	/**
	 * 
	 */
	private static final void run() {

		System.out.println();
		System.out.println(new Date());
		System.out.println();
		System.out.println("Please select...");
		System.out.println();

		printModules();
		System.out.println();

		Scanner scanner = new Scanner(System.in);
		int selected = scanner.nextInt();
		scanner.close();
		modules.get(selected).getMeVenkUtilsRunner().run();

	}

	/**
	 * 
	 */
	private static final void printModules() {

		for (Map.Entry<Integer, MeVenkUtilsModule> module : modules.entrySet()) {
			System.out.println(module.getKey() + ".    " + module.getValue().getDisplayDescription());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		run();

	}

}
