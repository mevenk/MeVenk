/**
 * 
 */
package com.mevenk.webapp.to.email;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
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

	private static final FileTypeMap DEFAULT_FILE_TYPE_MAP = ConfigurableMimeFileTypeMap.getDefaultFileTypeMap();

	private String attachmentFileName;
	private String contentType;

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
		this.contentType = DEFAULT_FILE_TYPE_MAP.getContentType(attachmentFileName);
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param data
	 * @throws IOException
	 */
	public EmailAttachmentTO(String attachmentFileName, String data) throws IOException {
		this(attachmentFileName,
				new ByteArrayDataSource(data, DEFAULT_FILE_TYPE_MAP.getContentType(attachmentFileName)));
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param is
	 * @throws IOException
	 */
	public EmailAttachmentTO(String attachmentFileName, InputStream is) throws IOException {
		this(attachmentFileName, new ByteArrayDataSource(is, DEFAULT_FILE_TYPE_MAP.getContentType(attachmentFileName)));
	}

	/**
	 * 
	 * @param attachmentFileName
	 * @param data
	 */
	public EmailAttachmentTO(String attachmentFileName, byte[] data) {
		this(attachmentFileName,
				new ByteArrayDataSource(data, DEFAULT_FILE_TYPE_MAP.getContentType(attachmentFileName)));
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
	 * @return the contentType
	 */
	public final String getContentType() {
		return contentType;
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
		return attachmentFileName.equalsIgnoreCase(other.attachmentFileName);
	}

}
