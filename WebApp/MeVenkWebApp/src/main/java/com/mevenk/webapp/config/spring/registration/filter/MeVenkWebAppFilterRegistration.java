package com.mevenk.webapp.config.spring.registration.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mevenk.webapp.config.filter.MeVenkWebAppFilter;
import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;
import com.mevenk.webapp.config.spring.registration.servlet.MeVenkWebAppServletRegistration;

/**
 * 
 * @author vkolisetty
 *
 */
@Configuration
@EnableWebMvc
@Import(MeVenkWebAppRootConfiguration.class)
public class MeVenkWebAppFilterRegistration {

	@Bean("MeVenkWebAppCharacterEncodingFilter")
	FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true);

		FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBeanCharacterEncodingFilter = new FilterRegistrationBean<CharacterEncodingFilter>(
				characterEncodingFilter);
		filterRegistrationBeanCharacterEncodingFilter
				.addUrlPatterns(MeVenkWebAppServletRegistration.URL_MAPPING_DISPATCHER_SERVLET_MEVENK);

		return filterRegistrationBeanCharacterEncodingFilter;

	}

	@Bean("MeVenkWebAppFilter")
	FilterRegistrationBean<MeVenkWebAppFilter> meVenkWebAppFilter() {

		FilterRegistrationBean<MeVenkWebAppFilter> filterRegistrationBeanMeVenkWebAppFilter = new FilterRegistrationBean<MeVenkWebAppFilter>(
				new MeVenkWebAppFilter());
		filterRegistrationBeanMeVenkWebAppFilter.addUrlPatterns("*");

		return filterRegistrationBeanMeVenkWebAppFilter;

	}

}
