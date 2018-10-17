/**
 *
 */
package com.mevenk.webapp.config.spring;

import static com.mevenk.webapp.config.spring.properties.BaseProperties.BEAN_BASE_PROPERTIES;
import static com.mevenk.webapp.config.spring.properties.DatabaseProperties.BEAN_DATABASE_PROPERTIES;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.mevenk.webapp.config.spring.properties.BaseProperties;
import com.mevenk.webapp.config.spring.properties.DatabaseProperties;

/**
 * @author venky
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
@PropertySource("${mevenkWebappBasePropertiesFileSource}")
@PropertySource("${mevenkWebappDatabasePropertiesFileSource}")
public class MeVenkWebAppPropertiesConfiguration {

	@Bean(name = BEAN_BASE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public BaseProperties baseProperties() {
		return new BaseProperties();
	}

	@Bean(name = BEAN_DATABASE_PROPERTIES)
	@Scope(scopeName = SCOPE_SINGLETON)
	public DatabaseProperties databaseProperties() {
		return new DatabaseProperties();
	}

}
