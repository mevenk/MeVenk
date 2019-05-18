/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.LINE_SEPARATOR;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author vkolisetty
 *
 */
public class MeVenkWebAppMailSender {

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

	private static void send(SimpleMailMessage simpleMailMessage) {
		javaMailSender.send(simpleMailMessage);
	}

	public static final void send(EmailTO emailTO) {
		SimpleMailMessage simpleMailMessage = emailTO.generateSimpleMailMessage();
		simpleMailMessage.setSentDate(new Date());
		simpleMailMessage.setFrom(fromEmail);
		simpleMailMessage.setText(generateEmailText(emailTO.getText()));
		send(simpleMailMessage);
	}

	private static final String generateEmailText(String text) {
		StringBuilder emailText = new StringBuilder(LINE_SEPARATOR);

		emailText.append(
				"<html>" + LINE_SEPARATOR + LINE_SEPARATOR + text + LINE_SEPARATOR + LINE_SEPARATOR + "</html>");

		return emailText.toString();
	}

}
