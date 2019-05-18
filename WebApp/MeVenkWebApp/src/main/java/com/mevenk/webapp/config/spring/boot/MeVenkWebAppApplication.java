/**
 * 
 */
package com.mevenk.webapp.config.spring.boot;

import static com.mevenk.webapp.config.spring.email.EmailTO.LINE_BREAK;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;
import com.mevenk.webapp.config.spring.email.EmailTO;
import com.mevenk.webapp.config.spring.email.MeVenkWebAppMailSender;

/**
 * @author vkolisetty
 *
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class MeVenkWebAppApplication {

	private static boolean applicationStarted;

	/**
	 * @return the applicationStarted
	 */
	public static final boolean isApplicationStarted() {
		return applicationStarted;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				MeVenkWebAppRootConfiguration.class).run(args);

		System.out.println("Context: " + configurableApplicationContext.getDisplayName());

		ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
		String startupEmailTo = environment.getProperty("startup.email.to");
		String startupEmailCc = environment.getProperty("startup.email.cc");

		if (StringUtils.isBlank(startupEmailTo) || StringUtils.isBlank(startupEmailCc)) {
			throw new IllegalStateException("Startup email properties not proper");
		}

		sendEmailApplicationStarted(configurableApplicationContext, startupEmailTo, startupEmailCc);

		applicationStarted = true;

	}

	/**
	 * 
	 * @param applicationContext
	 * @param startupEmailTo
	 * @param startupEmailCc
	 * @throws Exception
	 */
	private static final void sendEmailApplicationStarted(ApplicationContext applicationContext, String startupEmailTo,
			String startupEmailCc) throws Exception {

		String subject = "MeVenkWebApp application started @ " + new Date();
		StringBuilder emailText = new StringBuilder(LINE_BREAK);

		emailText.append("Startup: " + applicationContext.getStartupDate() + LINE_BREAK);

		emailText.append("Name: " + applicationContext.getApplicationName() + "|" + applicationContext.getDisplayName()
				+ LINE_BREAK);
		emailText.append("Id: " + applicationContext.getId() + LINE_BREAK);

		emailText.append(LINE_BREAK);
		emailText.append(EmailTO.HORIZANTAL_LINE);
		emailText.append(LINE_BREAK);
		emailText.append("Environment: " + applicationContext.getEnvironment() + LINE_BREAK);
		emailText.append(LINE_BREAK);
		emailText.append(EmailTO.HORIZANTAL_LINE);
		emailText.append(LINE_BREAK);

		emailText.append("No of Beans: " + applicationContext.getBeanDefinitionCount() + LINE_BREAK);
		emailText.append(LINE_BREAK);
		for (String name : applicationContext.getBeanDefinitionNames()) {
			emailText.append(name + LINE_BREAK);
		}
		emailText.append(EmailTO.HORIZANTAL_LINE);
		emailText.append(LINE_BREAK);
		emailText.append(LINE_BREAK);

		MeVenkWebAppMailSender.send(new EmailTO(subject, new String[] { startupEmailTo },
				new String[] { startupEmailCc }, emailText.toString()));
	}

}
