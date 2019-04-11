/**
 * 
 */
package org.mevenk.utils.zip;

import static org.mevenk.utils.helper.MeVenkUtilsHelper.FILE_SEPARATOR;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
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

		String entityName = null;
		InputStream inputStreamEntry = null;
		byte[] bytesEntry = null;

		for (ZipEntity entity : zipEntities) {

			entityName = entity.getName();
			inputStreamEntry = null;
			bytesEntry = null;

			bytesEntry = entity.getBytes();
			inputStreamEntry = entity.getInputStream();

			if (entityName.endsWith(FILE_SEPARATOR)) {
				zipOutputStream.putNextEntry(new ZipEntry(entityName + "."));
				zipOutputStream.closeEntry();
				continue;
			}

			zipOutputStream.putNextEntry(new ZipEntry(entity.getName()));

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

	/**
	 * 
	 * @param inputStream
	 * @param destinationDirectory
	 * @throws Exception
	 */
	static LinkedHashSet<ZipEntity> unzip(InputStream inputStream) throws Exception {

		ZipInputStream zipInputStream = new ZipInputStream(inputStream);

		LinkedHashSet<ZipEntity> zipEntries = new LinkedHashSet<ZipEntity>();

		ByteArrayOutputStream byteArrayOutputStreamZipEntry = null;

		ZipEntry zipEntry = zipInputStream.getNextEntry();
		while (zipEntry != null) {
			byteArrayOutputStreamZipEntry = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = zipInputStream.read(buffer)) > 0) {
				byteArrayOutputStreamZipEntry.write(buffer, 0, len);
			}
			byteArrayOutputStreamZipEntry.close();
			zipEntries.add(new ZipEntity(zipEntry.getName(), byteArrayOutputStreamZipEntry.toByteArray()));
			zipEntry = zipInputStream.getNextEntry();
		}
		zipInputStream.closeEntry();
		zipInputStream.close();

		return zipEntries;
	}

}
