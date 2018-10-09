/**
 *
 */
package com.mevenk.webapp.config.spring;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author venky
 *
 */
public final class SpringConfiguration {

	private static boolean isObjectLoaded = false;

	private static final IllegalAccessException ILLEGAL_ACCESS_EXCEPTION_SPRING_CONFIGURATION_ALREADY_LOADED = new IllegalAccessException(
			"SPRING_CONFIGURATION_ALREADY_LOADED");

	private static SpringConfiguration springConfiguration = null;

	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	private String applicationName;
	private String displayName;
	private String[] beanDefinitionNames;
	private int beanDefinitionCount;
	private ClassLoader classLoader;
	private String id;
	private long startupDateTime;
	private Environment environment;

	/**
	 * @param applicationContext
	 * @param requestMappingHandlerMapping
	 * @param applicationName
	 * @param displayName
	 * @param beanDefinitionNames
	 * @param beanDefinitionCount
	 * @param classLoader
	 * @param id
	 * @param startupDateTime
	 * @param environment
	 */
	public SpringConfiguration(ApplicationContext applicationContext,
			RequestMappingHandlerMapping requestMappingHandlerMapping) {
		super();
		this.requestMappingHandlerMapping = requestMappingHandlerMapping;
		this.applicationName = applicationContext.getApplicationName();
		this.displayName = applicationContext.getDisplayName();
		this.beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		this.beanDefinitionCount = applicationContext.getBeanDefinitionCount();
		this.classLoader = applicationContext.getClassLoader();
		this.id = applicationContext.getId();
		this.startupDateTime = applicationContext.getStartupDate();
		this.environment = applicationContext.getEnvironment();
	}

	/**
	 * @return the requestMappingHandlerMapping
	 */
	public final RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return requestMappingHandlerMapping;
	}

	/**
	 * @return the applicationName
	 */
	public final String getApplicationName() {
		return applicationName;
	}

	/**
	 * @return the displayName
	 */
	public final String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the beanDefinitionNames
	 */
	public final String[] getBeanDefinitionNames() {
		return beanDefinitionNames;
	}

	/**
	 * @return the beanDefinitionCount
	 */
	public final int getBeanDefinitionCount() {
		return beanDefinitionCount;
	}

	/**
	 * @return the classLoader
	 */
	public final ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @return the startupDateTime
	 */
	public final long getStartupDateTime() {
		return startupDateTime;
	}

	/**
	 * @return the environment
	 */
	public final Environment getEnvironment() {
		return environment;
	}

	/**
	 *
	 * @throws Exception
	 */
	public static final SpringConfiguration loadSpringConfiguration(ApplicationContext applicationContext,
			RequestMappingHandlerMapping requestMappingHandlerMapping) throws Exception {
		if (isObjectLoaded) {
			throw ILLEGAL_ACCESS_EXCEPTION_SPRING_CONFIGURATION_ALREADY_LOADED;
		}
		springConfiguration = new SpringConfiguration(applicationContext, requestMappingHandlerMapping);
		isObjectLoaded = true;
		return springConfiguration;

	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	public static final SpringConfiguration getSpringConfiguration() throws Exception {
		return springConfiguration;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return "SpringConfiguration [requestMappingHandlerMapping=" + requestMappingHandlerMapping
				+ ", applicationName=" + applicationName + ", displayName=" + displayName + ", beanDefinitionNames="
				+ Arrays.toString(beanDefinitionNames) + ", beanDefinitionCount=" + beanDefinitionCount
				+ ", classLoader=" + classLoader + ", id=" + id + ", startupDateTime=" + startupDateTime
				+ ", environment=" + environment + "]";
	}

}
