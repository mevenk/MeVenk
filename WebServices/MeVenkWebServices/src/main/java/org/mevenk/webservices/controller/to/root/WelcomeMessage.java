/**
 * 
 */
package org.mevenk.webservices.controller.to.root;

import static eu.bitwalker.useragentutils.UserAgent.parseUserAgentString;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * @author vkolisetty
 *
 */
public class WelcomeMessage {

	private static final String WELCOME_MESSAGE = "It's working!!!";
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT_WELCOME_MESSAGE = new SimpleDateFormat(
			"dd MMM yyyy - HH:mm:ss.S");

	private String message = WELCOME_MESSAGE;
	private String date;

	private int id;
	private String operatingSystemName;
	private String operatingSystemGroupName;
	private String deviceType;

	/**
	 * 
	 * @param request
	 */
	public WelcomeMessage(HttpServletRequest request) {
		this.date = SIMPLE_DATE_FORMAT_WELCOME_MESSAGE.format(new Date());
		UserAgent userAgent = parseUserAgentString(request.getHeader("User-Agent"));

		this.id = userAgent.getId();
		OperatingSystem operatingSystem = userAgent.getOperatingSystem();
		operatingSystemName = operatingSystem.getName();
		operatingSystemGroupName = operatingSystem.getGroup().getName();
		deviceType = operatingSystem.getDeviceType().getName();

	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @return the date
	 */
	public final String getDate() {
		return date;
	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return the operatingSystemName
	 */
	public final String getOperatingSystemName() {
		return operatingSystemName;
	}

	/**
	 * @return the operatingSystemGroupName
	 */
	public final String getOperatingSystemGroupName() {
		return operatingSystemGroupName;
	}

	/**
	 * @return the deviceType
	 */
	public final String getDeviceType() {
		return deviceType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WelcomeMessage [message=" + message + ", date=" + date + ", id=" + id + ", operatingSystemName="
				+ operatingSystemName + ", operatingSystemGroupName=" + operatingSystemGroupName + ", deviceType="
				+ deviceType + "]";
	}

}
