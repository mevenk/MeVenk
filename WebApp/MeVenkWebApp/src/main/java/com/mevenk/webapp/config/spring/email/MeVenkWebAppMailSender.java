/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import static com.mevenk.webapp.to.email.EmailTO.LINE_BREAK;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.LINE_SEPARATOR;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.mevenk.webapp.to.email.EmailAttachmentTO;
import com.mevenk.webapp.to.email.EmailTO;

/**
 * @author vkolisetty
 *
 */
public class MeVenkWebAppMailSender {

	private static final String HEADER_IMAGE = "headerImage";
	private static final String FOOTER_IMAGE = "footerImage";

	private static final ClassPathResource RESOURCE_HEADER_IMAGE = new ClassPathResource(
			"/com/mevenk/webapp/config/spring/email/emailHeader.png");
	private static final ClassPathResource RESOURCE_FOOTER_IMAGE = new ClassPathResource(
			"/com/mevenk/webapp/config/spring/email/emailFooter.png");

	private static JavaMailSender javaMailSender;
	private static String fromEmail;

	/**
	 * 
	 * @param javaMailSender
	 * @param from
	 */
	MeVenkWebAppMailSender(JavaMailSender javaMailSender, String from) {
		MeVenkWebAppMailSender.javaMailSender = javaMailSender;
		fromEmail = from;
	}

	@PostConstruct
	private void testEmail() {
		if (javaMailSender == null || fromEmail == null || fromEmail.trim().isEmpty()) {
			throw new IllegalArgumentException("All parameters required !!!");
		}
	}

	/**
	 * 
	 * @param mimeMessage
	 */
	private static void send(MimeMessage mimeMessage) {
		javaMailSender.send(mimeMessage);
	}

	/**
	 * 
	 * @param emailTO
	 * @throws Exception
	 */
	public static final void send(EmailTO emailTO) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		populateMimeMessageHelper(emailTO, mimeMessageHelper);
		mimeMessageHelper.setSentDate(new Date());
		mimeMessageHelper.setFrom(fromEmail);
		send(mimeMessage);
	}

	/**
	 * 
	 * @param emailTO
	 * @param mimeMessageHelper
	 * @throws Exception
	 */
	private static final void populateMimeMessageHelper(EmailTO emailTO, MimeMessageHelper mimeMessageHelper)
			throws Exception {

		mimeMessageHelper.setTo(emailTO.getTo());
		String[] cc = emailTO.getCc();
		if (cc != null && cc.length > 0) {
			mimeMessageHelper.setCc(cc);
		}
		String[] bcc = emailTO.getBcc();
		if (bcc != null && bcc.length > 0) {
			mimeMessageHelper.setBcc(bcc);
		}

		mimeMessageHelper.setSubject(emailTO.getSubject());

		mimeMessageHelper.setText(generateEmailText(emailTO.getText()), true);
		mimeMessageHelper.addInline(HEADER_IMAGE, RESOURCE_HEADER_IMAGE);
		mimeMessageHelper.addInline(FOOTER_IMAGE, RESOURCE_FOOTER_IMAGE);

		setAttachments(emailTO, mimeMessageHelper);

	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	private static final String generateEmailText(String text) {
		StringBuilder emailText = new StringBuilder(LINE_BREAK);

		emailText.append("<html>" + LINE_SEPARATOR + "<body>" + LINE_SEPARATOR);

		emailText.append("<table max-width=\"70%\" align=\"center\" border=\"0\">");

		emailText.append(LINE_SEPARATOR + "<tr>" + LINE_SEPARATOR + "<td>");

		emailText.append(LINE_SEPARATOR + "<img src=\"cid:" + HEADER_IMAGE
				+ "\" alt=\"MeVenk\" title=\"MeVenk\" width=\"700px\" />" + LINE_SEPARATOR);

		emailText.append(LINE_SEPARATOR + "</td>" + LINE_SEPARATOR + "</tr>");

		emailText.append(LINE_SEPARATOR + "<tr>" + LINE_SEPARATOR + "<td>");

		emailText.append(LINE_BREAK);

		emailText.append(LINE_SEPARATOR + text + LINE_SEPARATOR);

		emailText.append(LINE_BREAK);

		emailText.append(LINE_SEPARATOR + "</td>" + LINE_SEPARATOR + "</tr>");

		emailText.append(LINE_SEPARATOR + "<tr>" + LINE_SEPARATOR + "<td>");

		emailText.append(LINE_SEPARATOR + "<img align=\"right\" src=\"cid:" + FOOTER_IMAGE
				+ "\" alt=\"MeVenk\" title=\"MeVenk\" />" + LINE_SEPARATOR);

		emailText.append(LINE_SEPARATOR + "</td>" + LINE_SEPARATOR + "</tr>");

		emailText.append(LINE_SEPARATOR + "</table>" + LINE_SEPARATOR + "</body>" + LINE_SEPARATOR + "</html>");

		return emailText.toString();
	}

	/**
	 * 
	 * @param emailTO
	 * @param mimeMessageHelper
	 * @throws MessagingException
	 * @throws IOException
	 */
	private static final void setAttachments(EmailTO emailTO, MimeMessageHelper mimeMessageHelper)
			throws IOException, MessagingException {

		Set<EmailAttachmentTO> emailAttachments = emailTO.getEmailAttachments();
		if (emailAttachments == null || emailAttachments.isEmpty()) {
			return;
		}

		String attachmentFileName = null;

		File file = null;
		DataSource dataSource = null;
		InputStreamSource inputStreamSource = null;

		for (EmailAttachmentTO attachment : emailAttachments) {

			attachmentFileName = attachment.getAttachmentFileName();

			file = attachment.getFile();
			if (file != null) {
				mimeMessageHelper.addAttachment(attachmentFileName, file);
				continue;
			}

			inputStreamSource = attachment.getInputStreamSource();
			if (inputStreamSource != null) {
				mimeMessageHelper.addAttachment(attachmentFileName, inputStreamSource, attachment.getContentType());
				continue;
			}

			dataSource = attachment.getDataSource();
			if (dataSource != null) {
				mimeMessageHelper.addAttachment(attachmentFileName, dataSource);
				continue;
			}

		}

	}

}
