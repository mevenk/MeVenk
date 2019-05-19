/**
 * 
 */
package com.mevenk.webapp.to.email;

import org.apache.commons.lang3.StringUtils;

import com.mevenk.webapp.to.BaseTO;

/**
 * @author vkolisetty
 *
 */
public class EmailTO extends BaseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6831610080606895991L;

	public static final String LINE_BREAK = "<br/>";

	private String subject;
	private String to[];
	private String cc[];
	private String bcc[];
	private String text;

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param text
	 */
	public EmailTO(String subject, String to, String text) {
		this(subject, new String[] { to }, text);
	}

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param cc
	 * @param text
	 */
	public EmailTO(String subject, String to, String cc, String text) {
		this(subject, new String[] { to }, new String[] { cc }, text);
	}

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param text
	 */
	public EmailTO(String subject, String to, String cc, String bcc, String text) {
		this(subject, new String[] { to }, new String[] { cc }, new String[] { bcc }, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param text
	 */
	public EmailTO(String subject, String[] to, String text) {
		this(subject, to, null, null, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param cc
	 * @param text
	 */
	public EmailTO(String subject, String[] to, String[] cc, String text) {
		this(subject, to, cc, null, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param text
	 */
	public EmailTO(String subject, String[] to, String[] cc, String[] bcc, String text) {
		if (StringUtils.isAnyEmpty(subject)) {
			throw new IllegalArgumentException("Subject to cannot be null or empty");
		}
		if (StringUtils.isAnyEmpty(to)) {
			throw new IllegalArgumentException("To to cannot be null or empty");
		}
		if (StringUtils.isAnyEmpty(text)) {
			throw new IllegalArgumentException("Text to cannot be null or empty");
		}
		this.subject = subject;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.text = text;
	}

	/**
	 * @return the subject
	 */
	public final String getSubject() {
		return subject;
	}

	/**
	 * @return the to
	 */
	public final String[] getTo() {
		return to;
	}

	/**
	 * @return the cc
	 */
	public final String[] getCc() {
		return cc;
	}

	/**
	 * @return the bcc
	 */
	public final String[] getBcc() {
		return bcc;
	}

	/**
	 * @return the text
	 */
	public final String getText() {
		return text;
	}

}
