/**
 * 
 */
package org.mevenk.utils.zip;

import java.io.InputStream;

/**
 * @author vkolisetty
 *
 */
public class ZipEntity {

	private String name;
	private InputStream inputStream;
	private byte[] bytes;

	/**
	 * @param name
	 * @param inputStream
	 */
	public ZipEntity(String name, InputStream inputStream) {
		this.name = name;
		this.inputStream = inputStream;
	}

	/**
	 * @param name
	 * @param bytes
	 */
	public ZipEntity(String name, byte[] bytes) {
		this.name = name;
		this.bytes = bytes;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return the inputStream
	 */
	public final InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @return the bytes
	 */
	public final byte[] getBytes() {
		return bytes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ZipEntity [name=" + name + ", inputStream=" + (inputStream != null) + ", bytes=" + (bytes != null)
				+ "]";
	}

}
