/**
 * 
 */
package com.mevenk.webapp.config.spring.properties;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.mevenk.webapp.util.MeVenkWebAppUtil;

/**
 * @author vkolisetty
 *
 */
@ConfigurationProperties("email")
public class EmailProperties {

	public static final String BEAN_EMAIL_PROPERTIES = "BEAN_EMAIL_PROPERTIES";

	private String from;
	private String host;
	private int port;
	private String username;
	private String password;

	/**
	 * @return the from
	 */
	public final String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public final void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the host
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public final void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public final int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public final void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	@PostConstruct
	private void validate() {

		MeVenkWebAppUtil.validateNotNull(from, host, port, username, password);
	}

}
