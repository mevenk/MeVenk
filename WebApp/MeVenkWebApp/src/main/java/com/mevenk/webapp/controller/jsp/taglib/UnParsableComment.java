/**
 *
 */
package com.mevenk.webapp.controller.jsp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author venky
 *
 */
public class UnParsableComment extends BodyTagSupport {

	private String id;
	private String simpleComment;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the simpleComment
	 */
	public String getSimpleComment() {
		return simpleComment;
	}

	/**
	 * @param simpleComment the simpleComment to set
	 */
	public void setSimpleComment(String simpleComment) {
		this.simpleComment = simpleComment;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -823782674774542256L;

	@Override
	public int doStartTag() throws JspException {

		try {
			System.out.println(id + "|" + simpleComment);
			pageContext.getOut().println("");
		} catch (IOException ioException) {
			throw new JspException("Exception reading  un parsable comment: " + ioException.getMessage());
		}
		return EVAL_PAGE;
	}
}
