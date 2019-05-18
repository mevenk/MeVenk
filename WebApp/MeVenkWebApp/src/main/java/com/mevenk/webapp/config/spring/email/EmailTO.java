/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.SimpleMailMessage;

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

	public static final String HORIZANTAL_LINE = "<hr style=\"display: block; height: 1px; border: 0; border-top: 1px solid red; margin: 1em 0; padding: 0;\" />";

	protected String subject;
	protected String to[];
	protected String cc[];
	protected String bcc[];
	protected String text;

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

	/**
	 * 
	 * @return
	 */
	protected final SimpleMailMessage generateSimpleMailMessage() {
		if (StringUtils.isAnyEmpty(subject)) {
			throw new IllegalArgumentException("Subject to cannot be null or empty");
		}
		if (StringUtils.isAnyEmpty(to)) {
			throw new IllegalArgumentException("To to cannot be null or empty");
		}
		if (StringUtils.isAnyEmpty(text)) {
			throw new IllegalArgumentException("Text to cannot be null or empty");
		}
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setTo(to);
		if (cc != null && cc.length > 0) {
			simpleMailMessage.setCc(cc);
		}
		if (bcc != null && bcc.length > 0) {
			simpleMailMessage.setBcc(bcc);
		}
		return simpleMailMessage;
	}

}
