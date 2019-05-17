/**
 *
 */
package com.mevenk.webapp.config.spring;

import static com.mevenk.webapp.config.spring.properties.BaseProperties.BEAN_BASE_PROPERTIES;
import static com.mevenk.webapp.config.spring.properties.DataSourceProperties.BEAN_DATA_SOURCE_PROPERTIES;
import static com.mevenk.webapp.config.spring.properties.DatabaseProperties.BEAN_DATABASE_PROPERTIES;
import static com.mevenk.webapp.config.spring.properties.EmailProperties.BEAN_EMAIL_PROPERTIES;
import static com.mevenk.webapp.config.spring.properties.HibernateProperties.BEAN_HIBERNATE_PROPERTIES;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.mevenk.webapp.config.spring.properties.BaseProperties;
import com.mevenk.webapp.config.spring.properties.DataSourceProperties;
import com.mevenk.webapp.config.spring.properties.DatabaseProperties;
import com.mevenk.webapp.config.spring.properties.EmailProperties;
import com.mevenk.webapp.config.spring.properties.HibernateProperties;

/**
 * @author venky
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
@PropertySource("${mevenkWebappPropertiesFileSource}")
@PropertySource("${mevenkWebappBasePropertiesFileSource}")
@PropertySource("${mevenkWebappDatabasePropertiesFileSource}")
public class MeVenkWebAppPropertiesConfiguration {

	@Bean(name = BEAN_BASE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public BaseProperties baseProperties() {
		return new BaseProperties();
	}

	@Bean(name = BEAN_DATA_SOURCE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = BEAN_HIBERNATE_PROPERTIES)
	@DependsOn(BEAN_DATA_SOURCE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public HibernateProperties hibernateProperties() {
		return new HibernateProperties();
	}

	@Bean(name = BEAN_DATABASE_PROPERTIES)
	@DependsOn(BEAN_HIBERNATE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public DatabaseProperties databaseProperties() {
		return new DatabaseProperties();
	}
	
	@Bean(name = BEAN_EMAIL_PROPERTIES)
	public EmailProperties emailProperties() {
		return new EmailProperties();
	}
	
}
