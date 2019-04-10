/**
 * 
 */
package org.mevenk.utils.zip;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author vkolisetty
 *
 */
class ZipActivities {

	/**
	 * 
	 */
	private ZipActivities() {
		// Prevent instantiation
	}

	/**
	 * 
	 * @param outputStream
	 * @param zipEntities
	 * @throws Exception
	 */
	static void generateZip(OutputStream outputStream, ZipEntity... zipEntities) throws Exception {

		if (zipEntities == null) {
			zipEntities = new ZipEntity[] {};
		}

		ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

		ZipEntry zipEntry = null;
		InputStream inputStreamEntry = null;
		byte[] bytesEntry = null;

		for (ZipEntity entry : zipEntities) {

			inputStreamEntry = null;
			bytesEntry = null;

			zipEntry = new ZipEntry(entry.getName());
			bytesEntry = entry.getBytes();
			inputStreamEntry = entry.getInputStream();

			zipOutputStream.putNextEntry(zipEntry);

			if (bytesEntry != null) {
				zipOutputStream.write(bytesEntry);
				continue;
			}

			byte[] bytes = new byte[1024];
			int length;
			while ((length = inputStreamEntry.read(bytes)) >= 0) {
				zipOutputStream.write(bytes, 0, length);
			}
			inputStreamEntry.close();

		}

		zipOutputStream.close();

	}

}
