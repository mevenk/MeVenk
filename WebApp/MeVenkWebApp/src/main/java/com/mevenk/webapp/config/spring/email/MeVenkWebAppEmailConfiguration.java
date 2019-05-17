/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;

/**
 * @author vkolisetty
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
class MeVenkWebAppEmailConfiguration {

	private static final Map<Object, Object> MAIL_SENDER_PROPERTIES = new HashMap<Object, Object>(4) {
		/**
		* 
		*/
		private static final long serialVersionUID = 2217412345288837529L;

		{
			put("mail.transport.protocol", "smtp");
			put("mail.smtp.auth", "true");
			put("mail.smtp.starttls.enable", "true");
			put("mail.debug", "true");
		}
	};

	@Bean("MeVenkWebAppJavaMailSender")
	JavaMailSender meVenkWebAppJavaMailSender() {
		JavaMailSenderImpl meVenkWebAppJavaMailSender = new JavaMailSenderImpl();
		meVenkWebAppJavaMailSender.setHost("smtp.gmail.com");
		meVenkWebAppJavaMailSender.setPort(587);
		meVenkWebAppJavaMailSender.setUsername("");
		meVenkWebAppJavaMailSender.setPassword("");
		meVenkWebAppJavaMailSender.getJavaMailProperties().putAll(MAIL_SENDER_PROPERTIES);
		return meVenkWebAppJavaMailSender;
	}

	@Bean("MeVenkWebAppMailSender")
	@DependsOn("MeVenkWebAppJavaMailSender")
	MeVenkWebAppMailSender meVenkWebAppMailSender() {
		return new MeVenkWebAppMailSender(meVenkWebAppJavaMailSender(), "");
	}

}
