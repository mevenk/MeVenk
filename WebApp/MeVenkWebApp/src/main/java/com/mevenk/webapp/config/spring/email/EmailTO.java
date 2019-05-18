/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

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

	public static final String LINE_BREAK = MeVenkWebAppMailSender.LINE_BREAK;

	public static final String HORIZANTAL_LINE = LINE_BREAK
			+ "<hr style=\"display: block; height: 1px; border: 0; border-top: 1px solid red; margin: 1em 0; padding: 0;\" />"
			+ LINE_BREAK;

	private String subject;
	private String to[];
	private String cc[];
	private String bcc[];
	private String text;

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

	/**
	 * 
	 * @param mimeMessageHelper
	 * @throws Exception
	 */
	final void populateMimeMessageHelper(MimeMessageHelper mimeMessageHelper) throws Exception {

		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setTo(to);
		if (cc != null && cc.length > 0) {
			mimeMessageHelper.setCc(cc);
		}
		if (bcc != null && bcc.length > 0) {
			mimeMessageHelper.setBcc(bcc);
		}

	}

}
