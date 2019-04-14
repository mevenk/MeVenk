/**
 * 
 */
package org.mevenk.utils.zip;

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

	static final String ZIP_DIRECTORY_NAME_SUFFIX = "/";

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

		for (ZipEntity entity : zipEntities) {

			zipEntry = new ZipEntry(entity.getName());
			inputStreamEntry = null;
			bytesEntry = null;

			bytesEntry = entity.getBytes();
			inputStreamEntry = entity.getInputStream();

			zipOutputStream.putNextEntry(zipEntry);

			if (zipEntry.isDirectory()) {
				zipOutputStream.closeEntry();
				continue;
			}

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

		LinkedHashSet<ZipEntity> zipEntities = new LinkedHashSet<ZipEntity>();

		ByteArrayOutputStream byteArrayOutputStreamZipEntry = null;

		ZipEntry zipEntry = zipInputStream.getNextEntry();
		ZipEntity zipEntity = null;
		while (zipEntry != null) {
			if (zipEntry.isDirectory()) {
				zipEntity = new ZipEntity(zipEntry.getName());
			} else {
				byteArrayOutputStreamZipEntry = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len;
				while ((len = zipInputStream.read(buffer)) > 0) {
					byteArrayOutputStreamZipEntry.write(buffer, 0, len);
				}
				byteArrayOutputStreamZipEntry.close();
				zipEntity = new ZipEntity(zipEntry.getName(), byteArrayOutputStreamZipEntry.toByteArray());
			}

			zipEntities.add(zipEntity);
			zipEntry = zipInputStream.getNextEntry();
		}
		zipInputStream.closeEntry();
		zipInputStream.close();

		return zipEntities;
	}

}
