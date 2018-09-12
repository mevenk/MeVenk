/**
 *
 */
package com.mevenk.webapp.config.spring.database;

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

/**
 * @author venky
 *
 */
@Configuration
@ComponentScan(basePackages = BASE_PACKAGE)
@Import(MeVenkWebAppRootConfiguration.class)
@EnableTransactionManagement
public class MeVenkDatabaseConfiguration extends AbstractDatabaseConfigurationBase {

	@Bean
	@DependsOn("dataSource")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
		sessionBuilder.addProperties(getHibernateProperties());

		return sessionBuilder.buildSessionFactory();
	}

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(DRIVER_CLASS_NAME);
			comboPooledDataSource.setJdbcUrl(URL);
			comboPooledDataSource.setUser(USERNAME);
			comboPooledDataSource.setPassword(PASSWORD);
			comboPooledDataSource.setMinPoolSize(10);
			comboPooledDataSource.setMaxPoolSize(50);
			comboPooledDataSource.setIdleConnectionTestPeriod(CONNECTION_TEST);
			comboPooledDataSource.setMaxIdleTime(MAX_IDLE_TIME);
			comboPooledDataSource.setAcquireRetryDelay(ACQUIRE_RETRY_DELAY);
			comboPooledDataSource.setAcquireRetryAttempts(ACQUIRE_RETRY_ATTEMPTS);
			comboPooledDataSource.setUnreturnedConnectionTimeout(UNRETURNED_CONNECTION_TIMEOUT);
			comboPooledDataSource.setCheckoutTimeout(CHECKOUT_TIMEOUT);

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

	@Bean
	@DependsOn("sessionFactory")
	public PlatformTransactionManager transactionManager() {

		return new HibernateTransactionManager(sessionFactory());

	}

}
