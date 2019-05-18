/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author vkolisetty
 *
 */
public class MeVenkWebAppMailSender {

	static final String LINE_BREAK = "<br/>";

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
		emailTO.populateMimeMessageHelper(mimeMessageHelper);
		mimeMessageHelper.setSentDate(new Date());
		mimeMessageHelper.setFrom(fromEmail);
		mimeMessageHelper.setText(generateEmailText(emailTO.getText()), true);
		send(mimeMessage);
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	private static final String generateEmailText(String text) {
		StringBuilder emailText = new StringBuilder(LINE_BREAK);

		emailText.append(LINE_BREAK + LINE_BREAK);

		emailText.append(text);

		emailText.append(LINE_BREAK + LINE_BREAK);

		return emailText.toString();
	}

}
