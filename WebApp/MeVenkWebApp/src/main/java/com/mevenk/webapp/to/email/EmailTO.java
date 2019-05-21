/**
 * 
 */
package com.mevenk.webapp.to.email;

import static com.mevenk.webapp.config.spring.email.MeVenkWebAppMailSender.validateEmailAddress;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.mail.internet.AddressException;

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

	private Set<EmailAttachmentTO> emailAttachments;

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String to, String text) throws AddressException {
		this(subject, new String[] { to }, text);
	}

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param cc
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String to, String cc, String text) throws AddressException {
		this(subject, new String[] { to }, new String[] { cc }, text);
	}

	/**
	 * 
	 * @param subject
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String to, String cc, String bcc, String text) throws AddressException {
		this(subject, new String[] { to }, new String[] { cc }, new String[] { bcc }, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String[] to, String text) throws AddressException {
		this(subject, to, null, null, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param cc
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String[] to, String[] cc, String text) throws AddressException {
		this(subject, to, cc, null, text);
	}

	/**
	 * @param subject
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param text
	 * @throws AddressException
	 */
	public EmailTO(String subject, String[] to, String[] cc, String[] bcc, String text) throws AddressException {
		if (StringUtils.isAnyEmpty(subject)) {
			throw new IllegalArgumentException("Subject to cannot be null or empty");
		}
		validateEmailAddress(to);
		if (cc != null) {
			validateEmailAddress(cc);
		}
		if (bcc != null) {
			validateEmailAddress(bcc);
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
	 * 
	 * @param emailAttachments
	 * @return
	 */
	public EmailTO setEmailAttachments(Set<EmailAttachmentTO> emailAttachments) {
		this.emailAttachments = emailAttachments;
		return this;
	}

	public EmailTO addEmailAttachments(EmailAttachmentTO... emailAttachmentTOs) {
		if (emailAttachments == null) {
			emailAttachments = new LinkedHashSet<EmailAttachmentTO>();
		}
		emailAttachments.addAll(Arrays.asList(emailAttachmentTOs));
		return this;
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
	 * @return the emailAttachments
	 */
	public final Set<EmailAttachmentTO> getEmailAttachments() {
		return emailAttachments;
	}

}
