/**
 * 
 */
package com.mevenk.webapp.config.spring.email;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;
import com.mevenk.webapp.config.spring.properties.EmailProperties;

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
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Bean("MeVenkWebAppJavaMailSender")
	JavaMailSender meVenkWebAppJavaMailSender() {
		JavaMailSenderImpl meVenkWebAppJavaMailSender = new JavaMailSenderImpl();
		meVenkWebAppJavaMailSender.setHost(emailProperties.getHost());
		meVenkWebAppJavaMailSender.setPort(emailProperties.getPort());
		meVenkWebAppJavaMailSender.setUsername(emailProperties.getUsername());
		meVenkWebAppJavaMailSender.setPassword(emailProperties.getPassword());
		meVenkWebAppJavaMailSender.getJavaMailProperties().putAll(MAIL_SENDER_PROPERTIES);
		return meVenkWebAppJavaMailSender;
	}

	@Bean("MeVenkWebAppMailSender")
	@DependsOn("MeVenkWebAppJavaMailSender")
	MeVenkWebAppMailSender meVenkWebAppMailSender() {
		return new MeVenkWebAppMailSender(meVenkWebAppJavaMailSender(), emailProperties.getFrom());
	}

}
