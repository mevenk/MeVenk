/**
 * 
 */
package com.mevenk.webapp.config.spring.registration.servlet;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mevenk.webapp.config.servlet.InitialContextServlet;
import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;

/**
 * @author vkolisetty
 *
 */
@Configuration
@EnableWebMvc
@Import(MeVenkWebAppRootConfiguration.class)
public class MeVenkWebAppServletRegistration implements ApplicationContextAware {

	public static final String URL_MAPPING_DISPATCHER_SERVLET_MEVENK = "*.mevenk";

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	@Bean
	DispatcherServletPath dispatcherServletPath() {
		return new DispatcherServletPath() {

			@Override
			public String getPath() {
				return URL_MAPPING_DISPATCHER_SERVLET_MEVENK;
			}

		};
	}

	@Bean("dispatcherServlet")
	ServletRegistrationBean<DispatcherServlet> dispatcherServlet() {

		DispatcherServlet dispatcherServlet = new DispatcherServlet((WebApplicationContext) applicationContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistrationBean<DispatcherServlet> servletRegistrationBeanDispatcherServlet = new ServletRegistrationBean<DispatcherServlet>(
				dispatcherServlet, URL_MAPPING_DISPATCHER_SERVLET_MEVENK);
		servletRegistrationBeanDispatcherServlet.setName("mevenk");
		servletRegistrationBeanDispatcherServlet.addInitParameter("throwExceptionIfNoHandlerFound", "true");
		servletRegistrationBeanDispatcherServlet.setLoadOnStartup(1);
		return servletRegistrationBeanDispatcherServlet;

	}

	@Bean("initialContextServlet")
	ServletRegistrationBean<InitialContextServlet> initialContextServlet() {

		ServletRegistrationBean<InitialContextServlet> servletRegistrationBeanInitialContextServlet = new ServletRegistrationBean<InitialContextServlet>(
				new InitialContextServlet(), "*.initialContextServlet");
		servletRegistrationBeanInitialContextServlet.setName("InitialContextServlet");
		servletRegistrationBeanInitialContextServlet.setLoadOnStartup(2);
		return servletRegistrationBeanInitialContextServlet;

	}

}
