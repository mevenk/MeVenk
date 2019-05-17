/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author vkolisetty
 *
 */
public class MeVenkWebAppMailSender {

	private JavaMailSender javaMailSender;
	private static String fromEmail;

	/**
	 * 
	 * @param javaMailSender
	 * @param from
	 */
	MeVenkWebAppMailSender(JavaMailSender javaMailSender, String from) {
		this.javaMailSender = javaMailSender;
		fromEmail = from;
	}

	@PostConstruct
	private void testEmail() {
		if (javaMailSender == null || fromEmail == null || fromEmail.trim().isEmpty()) {
			throw new IllegalArgumentException("All parameters required !!!");
		}
		Date sentDate = new Date();
		SimpleMailMessage testEmail = generateSimpleMailMessage();
		testEmail.setSentDate(sentDate);
		testEmail.setTo("");
		testEmail.setSubject("MeVenkWebApp email test @ " + sentDate);
		testEmail.setCc("");
		testEmail.setText("MeVenkWebApp test Email......" + sentDate);
		send(testEmail);
	}

	private void send(SimpleMailMessage simpleMailMessage) {
		javaMailSender.send(simpleMailMessage);
	}

	private static final SimpleMailMessage generateSimpleMailMessage() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(fromEmail);
		return simpleMailMessage;
	}

}
