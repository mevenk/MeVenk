/**
 * 
 */
package org.mevenk.webservices.config.spring.properties;

import org.mevenk.webservices.config.spring.MeVenkWebServicesRootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author vkolisetty
 *
 */
@Configuration
@Import(MeVenkWebServicesRootConfiguration.class)

@PropertySource("${mevenkWebServicesPropertiesFileSource}")

@PropertySource("${mevenkWebServicesBasePropertiesFileSource}")
@EnableConfigurationProperties(BaseProperties.class)

@PropertySource("${mevenkWebServicesDatabasePropertiesFileSource}")
public class MeVenkWebServicesPropertiesConfiguration {

	@Bean
	BaseProperties baseProperties() {
		return new BaseProperties();
	}

}
