/**
 *
 */
package com.mevenk.webapp.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mevenk.webapp.config.spring.bind.GlobalBindingInitializer;
import com.mevenk.webapp.config.spring.interceptor.MeVenkWebAppInterceptor;
import com.mevenk.webapp.config.spring.interceptor.MeVenkWebAppWebRequestInterceptor;
import com.mevenk.webapp.config.spring.interceptor.login.RequestInterceptor;
import com.mevenk.webapp.config.spring.servlet.session.MeVenkWebAppSessionAttributeStore;

/**
 * @author venky
 *
 */
@Configuration
@EnableWebMvc
@Import(MeVenkWebAppRootConfiguration.class)
public class MeVenkWebAppServletConfiguration implements WebMvcConfigurer {

	@Bean(name = "meVenkWebAppWebRequestInterceptor")
	public WebRequestInterceptor meVenkWebAppWebRequestInterceptor() {
		return new MeVenkWebAppWebRequestInterceptor();
	}

	@Bean(name = "meVenkWebAppInterceptor")
	public HandlerInterceptor meVenkWebAppInterceptor() {
		return new MeVenkWebAppInterceptor();
	}

	@Bean(name = "loginInterceptor")
	public HandlerInterceptor loginInterceptor() {
		return new RequestInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(meVenkWebAppWebRequestInterceptor());
		registry.addInterceptor(meVenkWebAppInterceptor());
		registry.addInterceptor(loginInterceptor());
	}

	@Bean(name = "requestMappingHandlerAdapter")
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setWebBindingInitializer(new GlobalBindingInitializer());
		requestMappingHandlerAdapter.setSessionAttributeStore(new MeVenkWebAppSessionAttributeStore());
		return requestMappingHandlerAdapter;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Bean(name = "viewResolver")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
