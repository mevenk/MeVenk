/**
 * 
 */
package com.mevenk.webapp.to.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.multipart.MultipartFile;

import com.mevenk.webapp.to.BaseTO;

/**
 * @author vkolisetty
 *
 */
public class EmailAttachmentTO extends BaseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144389974603783944L;

	private String attachmentFileName;

	private File file;
	private DataSource dataSource;
	private InputStreamSource inputStreamSource;

	/**
	 * 
	 * @param attachmentFileName
	 */
	private EmailAttachmentTO(String attachmentFileName) {
		if (StringUtils.isBlank(attachmentFileName)) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		this.attachmentFileName = attachmentFileName;
	}

	/**
	 * @param file
	 */
	public EmailAttachmentTO(File file) {
		this(file.getName());
		this.file = file;
	}

	/**
	 * @param attachmentFileName
	 * @param dataSource
	 */
	public EmailAttachmentTO(String attachmentFileName, DataSource dataSource) {
		this(attachmentFileName);
		this.dataSource = dataSource;
	}

	/**
	 * @param attachmentFileName
	 * @param inputStreamSource
	 */
	public EmailAttachmentTO(String attachmentFileName, InputStreamSource inputStreamSource) {
		this(attachmentFileName);
		this.inputStreamSource = inputStreamSource;
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param data
	 * @param type
	 * @throws IOException
	 */
	public EmailAttachmentTO(String attachmentFileName, String data, String type) throws IOException {
		this(attachmentFileName, new ByteArrayDataSource(data, type));
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param is
	 * @param type
	 * @throws IOException
	 */
	public EmailAttachmentTO(String attachmentFileName, InputStream is, String type) throws IOException {
		this(attachmentFileName, new ByteArrayDataSource(is, type));
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param data
	 * @param type
	 */
	public EmailAttachmentTO(String attachmentFileName, byte[] data, String type) {
		this(attachmentFileName, new ByteArrayDataSource(data, type));
	}

	/**
	 * 
	 * @param multipartFile
	 */
	public EmailAttachmentTO(MultipartFile multipartFile) {
		this(multipartFile.getOriginalFilename(), multipartFile);
	}

	/**
	 * @return the attachmentFileName
	 */
	public final String getAttachmentFileName() {
		return attachmentFileName;
	}

	/**
	 * @return the file
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * @return the dataSource
	 */
	public final DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @return the inputStreamSource
	 */
	public final InputStreamSource getInputStreamSource() {
		return inputStreamSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(file, attachmentFileName, inputStreamSource, dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailAttachmentTO other = (EmailAttachmentTO) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (attachmentFileName == null) {
			if (other.attachmentFileName != null)
				return false;
		} else if (!attachmentFileName.equals(other.attachmentFileName))
			return false;
		if (dataSource == null) {
			if (other.dataSource != null)
				return false;
		} else if (!dataSource.equals(other.dataSource))
			return false;

		if (inputStreamSource == null) {
			if (other.inputStreamSource != null)
				return false;
		} else if (!inputStreamSource.equals(other.inputStreamSource))
			return false;
		return true;
	}

}
