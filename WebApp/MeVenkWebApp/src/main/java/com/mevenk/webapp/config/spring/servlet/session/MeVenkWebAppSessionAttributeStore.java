/**
 * 
 */
package com.mevenk.webapp.config.spring.servlet.session;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.context.request.WebRequest;

/**
 * @author venky
 *
 */
public class MeVenkWebAppSessionAttributeStore implements SessionAttributeStore {

	private static final Logger LOG = getLogger(MeVenkWebAppSessionAttributeStore.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.bind.support.SessionAttributeStore#storeAttribute(org
	 * .springframework.web.context.request.WebRequest, java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public void storeAttribute(WebRequest request, String attributeName, Object attributeValue) {
		LOG.debug("Storing Session attribute {} with value {}", attributeName, attributeValue);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.bind.support.SessionAttributeStore#retrieveAttribute(
	 * org.springframework.web.context.request.WebRequest, java.lang.String)
	 */
	@Override
	public Object retrieveAttribute(WebRequest request, String attributeName) {
		Object attributeValue = request.getAttribute(attributeName, SCOPE_SESSION);
		LOG.debug("Retrieved Session attribute {} with value {}", attributeName, attributeValue);
		return attributeValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.bind.support.SessionAttributeStore#cleanupAttribute(
	 * org.springframework.web.context.request.WebRequest, java.lang.String)
	 */
	@Override
	public void cleanupAttribute(WebRequest request, String attributeName) {
		Object attributeValue = request.getAttribute(attributeName, SCOPE_SESSION);
		LOG.debug("Removing Session attribute {} with value {}", attributeName, attributeValue);
		request.removeAttribute(attributeName, SCOPE_SESSION);

	}

}
