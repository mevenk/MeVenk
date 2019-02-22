/**
 *
 */
package com.mevenk.webapp.controller.jsp.taglib.restrict;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

/**
 * @author venky
 *
 */
public class WelcomePageRestriction extends ConditionalTagSupport {

	private static final String VARIABLE_NAME_HAS_GRANT_ACCESS_WELCOME_PAGE = "hasGrantAccessWelcomePage";

	/**
	 *
	 */
	private static final long serialVersionUID = 2687648794772229712L;

	private boolean grantAccess;

	/**
	 *
	 */
	public WelcomePageRestriction() {
		super();
		init();
	}

	public void release() {
		super.release();
		init();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.servlet.jsp.jstl.core.ConditionalTagSupport#condition()
	 */
	@Override
	protected boolean condition() throws JspTagException {

		// Value can be set to required scope to prevent repeated validation
		Boolean hasWelcomePageHasGrantAccess = (Boolean) pageContext.getRequest()
				.getAttribute(VARIABLE_NAME_HAS_GRANT_ACCESS_WELCOME_PAGE);
		if (hasWelcomePageHasGrantAccess != null) {
			return hasWelcomePageHasGrantAccess;
		}
		// Validation impmelentation

		// Set variable name and scope for result
		setVar(VARIABLE_NAME_HAS_GRANT_ACCESS_WELCOME_PAGE);
		setScope("request");
		return grantAccess;
	}

	/**
	 * @return the grantAccess
	 */
	public final boolean isGrantAccess() {
		return grantAccess;
	}

	/**
	 * @param grantAccess the grantAccess to set
	 */
	public final void setGrantAccess(boolean grantAccess) {
		this.grantAccess = grantAccess;
	}

	private void init() {
		grantAccess = false;
	}

}
