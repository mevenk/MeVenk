/**
 *
 */
package com.mevenk.webapp.config.spring.database;

import static com.mevenk.webapp.config.spring.database.MeVenkWebAppEntities.ENTITIES_HIBERNATE;
import static com.mevenk.webapp.config.spring.properties.DatabaseProperties.BEAN_DATABASE_PROPERTIES;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.BASE_PACKAGE;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;
import com.mevenk.webapp.config.spring.database.cache.CacheManagerConfig;

/**
 * @author venky
 *
 */
@Configuration
@ComponentScan(basePackages = BASE_PACKAGE)
@Import(MeVenkWebAppRootConfiguration.class)
@EnableTransactionManagement
public class MeVenkDatabaseConfiguration extends AbstractDatabaseConfigurationBase {

	public static final String BEAN_NAME_TRANSACTION_MANAGER = "transactionManager";

	@Bean
	public CacheManagerConfig cacheManagerConfig() {
		return new CacheManagerConfig();
	}

	@Bean
	@DependsOn("dataSource")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(ENTITIES_HIBERNATE.toArray(new Class<?>[ENTITIES_HIBERNATE.size()]));
		return sessionBuilder.buildSessionFactory();
	}

	@Bean
	@DependsOn(BEAN_DATABASE_PROPERTIES)
	public DataSource dataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(getDriverClassName());
			comboPooledDataSource.setJdbcUrl(getJdbcUrl());
			comboPooledDataSource.setUser(getUsername());
			comboPooledDataSource.setPassword(getPassword());
			comboPooledDataSource.setMinPoolSize(getMinPoolSize());
			comboPooledDataSource.setMaxPoolSize(getMaxPoolSize());
			comboPooledDataSource.setIdleConnectionTestPeriod(getIdleConnectionTestPeriod());
			comboPooledDataSource.setMaxIdleTime(getMaxIdleTime());
			comboPooledDataSource.setAcquireRetryDelay(getAcquireRetryDelay());
			comboPooledDataSource.setAcquireRetryAttempts(getAcquireRetryAttempts());
			comboPooledDataSource.setUnreturnedConnectionTimeout(getTimeoutConnectionUnReturned());
			comboPooledDataSource.setCheckoutTimeout(getTimeoutCheckout());

		} catch (PropertyVetoException exception) {
			throw new IllegalStateException(
					"Exception occurred while setting up database connection properties due to: "
							+ exception.getMessage(),
					exception);

		}

		return comboPooledDataSource;
	}

	@Bean
	@DependsOn("transactionManager")
	public TransactionTemplate transactionTemplate() {
		return new TransactionTemplate(transactionManager());
	}

	@Bean(name = BEAN_NAME_TRANSACTION_MANAGER)
	@DependsOn("sessionFactory")
	public PlatformTransactionManager transactionManager() {

		return new HibernateTransactionManager(sessionFactory());

	}

}
